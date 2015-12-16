package org.cobro.neonsign.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.cobro.neonsign.model.BoardDAO;
import org.cobro.neonsign.model.BoardService;
import org.cobro.neonsign.model.MemberDAO;
import org.cobro.neonsign.model.MemberService;
import org.cobro.neonsign.model.UtilService;
import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointAspect {
	private Log log=LogFactory.getLog(getClass());
	@Resource
	private MemberService memberService;
	@Resource
	private BoardService boardService;
	@Resource
	private MemberDAO memberDAO;
	@Resource
	private BoardDAO boardDAO;
	@Resource
	private UtilService utilService;

	@Around("within(org.cobro.neonsign.*.*)")
	public Object aroundLogger(ProceedingJoinPoint point) throws Throwable{
		Object retValue = null;
		String methodName = point.getSignature().getName();
		Object[] parameterArr = null;
		retValue = point.proceed();
		String logMessage = methodName +"메서드 시작 : 매개변수 - ";
		parameterArr=point.getArgs();
		if(parameterArr!=null){
			for(int i=0;i<parameterArr.length;i++){
				logMessage += ((i +" "+parameterArr[i]) + " ");
			}
		}
		if(retValue!=null){
			logMessage += "리턴 값 : "+retValue;
		}
		log.info(logMessage);
		return retValue;
	}
	
	@Around("execution(public * org.cobro.neonsign.model.*Service.Search*(..))")
	public Object aroundSearch(ProceedingJoinPoint point) throws Throwable{
		log.info("검색AOP실행");
		//메서드 실행
		Object retValue = null;	
		retValue = point.proceed();
		List<Object> list = (List<Object>)retValue;
		System.out.println(list);
		if(!list.isEmpty()){
			Object param[]=point.getArgs();// 메서드 인자값 - 매개변수
			System.out.println(param[1].toString());
			utilService.saveSearch(param[1].toString());	
		}		
		return retValue;
	}

	@Around("execution(public * org.cobro.neonsign..*Service.point*(..))")
	public Object keepScore(ProceedingJoinPoint point) throws Throwable{
		log.info("AOP 적용 완료");
		Object retValue = null;	
		Object[] parameterArr = null;
		String methodName = point.getSignature().getName();	
		//메서드 명을 기준으로 점수를 달리하거나 , 대상을 달리해준다.
		//메서드 실행
		retValue = point.proceed();
		parameterArr=point.getArgs();
		//잇는 글과 주제글 작성 시 10포인 트 추가
		if(methodName.startsWith("pointInsert")){
			if(parameterArr[0] instanceof SubArticleVO){
				memberDAO.memberPointPlusUpdater(((SubArticleVO)parameterArr[0]).getMemberEmail(),10);
			}else if(parameterArr[0] instanceof MainArticleVO){
				memberDAO.memberPointPlusUpdater(((MainArticleVO)parameterArr[0]).getMemberEmail(),10);
			}
			//잇자 버튼 클릭 시 점수 부여 및 점수 차감
		}else if(methodName.equals("pointSelectItjaState")){
			ItjaMemberVO itjaMemberVO = (ItjaMemberVO)parameterArr[0];
			HashMap<String, Object> map = (HashMap<String, Object>)retValue;
			// 잇자 취소
			if((Integer)map.get("itjaSuccess") == 1){
				memberDAO.memberPointMinusUpdater(itjaMemberVO.getMemberEmail(),1);
				//잇자 받은 게시물의 작성자를 찾아서 1점 차감해준다.
				memberDAO.memberPointMinusUpdater(boardDAO.selectWriterEmailByArticleNO(itjaMemberVO), 1);
				System.out.println(itjaMemberVO.getMemberEmail()+"와"+boardDAO.selectWriterEmailByArticleNO(itjaMemberVO)+"점수 차감");
			}else{
				memberDAO.memberPointPlusUpdater(itjaMemberVO.getMemberEmail(),1);
				//잇자 받은 게시물의 작성자를 찾아서 1점 더해준다.
				memberDAO.memberPointPlusUpdater(boardDAO.selectWriterEmailByArticleNO(itjaMemberVO), 1);
				System.out.println(itjaMemberVO.getMemberEmail()+"와"+boardDAO.selectWriterEmailByArticleNO(itjaMemberVO)+"점수 준다");
			}
		}else if(methodName.equals("pointMemberRegister")){
			//회원 가입시 50점 부여
			memberDAO.memberPointPlusUpdater(((MemberVO)parameterArr[0]).getMemberEmail(), 50);
		}else if(methodName.equals("pointDefaultMemberLogin")||methodName.equals("pointMemberLogin")){
			//출석시 포인트를 지급
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//현재 시간 객체
			String lastLoginDate=memberDAO.getLastLoginDate(((MemberVO)parameterArr[0]).getMemberEmail());//최종접속일
			System.out.println("현재 날짜 : "+sdf.format(dt).toString());
			System.out.println("최종 접속일 : "+lastLoginDate);
			if(!sdf.format(dt).toString().equals(lastLoginDate)||lastLoginDate==null){
				//최종 접속일과 현재 시간과 다르다면 10포인트를 지급
				memberDAO.memberPointPlusUpdater(((MemberVO)parameterArr[0]).getMemberEmail(),10);
			}
		}
		return retValue;
	}
		
}
