package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.utility.PasswordFinder;
import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.MemberListVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PagingBean;
import org.cobro.neonsign.vo.PickedVO;
import org.cobro.neonsign.vo.ServiceCenterListVO;
import org.cobro.neonsign.vo.ServiceCenterVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Resource
	private MemberDAO memberDAO;
	@Resource
	private UtilService utilService;
	@Resource
	private PasswordFinder passwordFinder;
	
	@Override
	public MemberVO findMemberByEmail(String emailComp) {
		// TODO Auto-generated method stub
		return memberDAO.findMemberByEmail(emailComp);
	}
	@Override
	public MemberVO findMemberByNickName(String nameComp) {
		// TODO Auto-generated method stub
		return memberDAO.findMemberByNickName(nameComp);
	}
	@Override
	public int pointMemberRegister(MemberVO memberVO) {
		return memberDAO.memberRegister(memberVO);
	}
	
	@Override
	public MemberVO pointMemberLogin(MemberVO memberVO) {
		MemberVO mvo=memberDAO.memberLogin(memberVO);
		if(mvo!=null){
			//최종 접속 일시를 체킹하는 메서드
			int result=memberDAO.memberLastLoginDateUpdate(memberVO);
			//만약 Update가 되지 않으면 Inert를 진행한다
			if(result==0){
				memberDAO.memberLastLoginDateInsert(memberVO);
			}
		}
		return mvo;
	}

	
	@Override
	public ArrayList<MemberVO> getNotifyMemberList(MemberVO mvo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	/**
	 * 회원 리스트를 받아오는 메서드
	 * @author 장1솔
	 */
	public MemberListVO getMemberList(int pageNo) {
		// TODO Auto-generated method stub
		PagingBean pb=null;
		ArrayList<MemberVO> members=(ArrayList<MemberVO>)memberDAO.getMemberList(pageNo);
		int totalReports=memberDAO.allMembers();
		if(pageNo!=0){
		pb= new PagingBean(totalReports, pageNo);
		}else{
			pb= new PagingBean(totalReports);
		}
		MemberListVO memberList=new MemberListVO(members,pb);
		return memberList;
	}

	/**
	 * 불량 회원 리스트를 받아오는 메서드
	 * @author 장1솔
	 */
	public MemberListVO getBlockMemberList(int pageNo){
		PagingBean pb=null;
		ArrayList<MemberVO> blokcMembers=(ArrayList<MemberVO>)memberDAO.getBlockMemberList(pageNo);
		int totalReports=memberDAO.allBlockMembers();
		if(pageNo!=0){
		pb= new PagingBean(totalReports, pageNo);
		}else{
			pb= new PagingBean(totalReports);
		}
		MemberListVO blockMemberList=new MemberListVO(blokcMembers,pb);
		return blockMemberList;
	}
	@Override
	/**
	 * 회원 이메일을 받아 그 회원을 블락 시키는 메서드
	 * @author 윤택
	 */
	public void memberBlock(String memberEmail) {
		// TODO Auto-generated method stub
		memberDAO.memberBlock(memberEmail);
		//블락 시킨뒤 회원의 블락 일시를 업데이트 또는 생성
		int result = memberDAO.memberBlackdateUpdate(memberEmail);
		//System.out.println("업데이트 여부 : "+result);
		if(result==0){
			memberDAO.memberBlackdateInsert(memberEmail);
		}
	}

	/**
	 * 회원 이메일을 받아 그 회원을 블락해제 시키는 메서드
	 * @author 윤택
	 */
	@Override
	public void memberBlockRelease(String memberEmail) {
		// TODO Auto-generated method stub
		memberDAO.memberBlockRelease(memberEmail);
	}
	/**
	 * pickedVO가 없는 초기 회원의 로그인을 위한 디폴트 로그인
	 * @author junyoung
	 */
	@Override
	public MemberVO pointDefaultMemberLogin(MemberVO memberVO) {
		MemberVO mvo=memberDAO.defaultMemberLogin(memberVO);
		if(mvo!=null){
			//최종 접속 일시를 체킹하는 메서드
			int result=memberDAO.memberLastLoginDateUpdate(memberVO);
			//만약 Update가 되지 않으면 Inert를 진행한다
			if(result==0){
				memberDAO.memberLastLoginDateInsert(memberVO);
			}
		}
		return mvo;
	}
	
	/**
	 * 찜 여부 확인 후 찜 등록 및 제거
	 * @author JeSeong Lee
	 */
	@Override
	public HashMap<String, Object> updatePickedVO(PickedVO pvo) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(memberDAO.selectPickedVO(pvo) == null){
			memberDAO.insertPickedVO(pvo);
			map.put("pickResult", "insert");
		} else{
			memberDAO.deletePickedVO(pvo);
			map.put("pickResult", "delete");
		}
		return map;
	}
	
	/**
	 * email로 찜한글 리스트 받기
	 * @author JeSeong Lee
	 */
	@Override
	public List<PickedVO> getPickListByMemberEmail(String memberEmail) {
		return memberDAO.getPickListByMemberEmail(memberEmail);
	}
	
	@Override
	public MemberVO findByPassword(String checkPassComp) {
		// TODO Auto-generated method stub
		//System.out.println("Service:"+checkPassComp);
		return memberDAO.findByPassword(checkPassComp);
	}
	
	@Override
	public int memberUpdate(MemberVO memberVO) {
		
		return memberDAO.memberUpdate(memberVO);
	} 
	
	@Override
	public void memberDelete(MemberVO memberVO) {
		// TODO Auto-generated method stub
		memberDAO.memberDelete(memberVO);
	}
	/**
	 * 비밀번호를 찾기위한 메일 요청메서드
	 * di를 세터게터로 주입
	 * @author junyoung
	 */
	@Override
	public void findPasswordMailRequest(FindPasswordVO findPasswordVO) {
		passwordFinder.setMemberDAO(memberDAO);
		passwordFinder.passFinder(findPasswordVO);
	}
	/**
	 * 비밀번호 요청 메일을 받고 제시된 링크를 클릭했을 때 메서드
	 * 랜덤 문자열과 해당 회원의 이메일 아이디를 비교하여 유효성을 판단한 뒤 임시 비밀번호를 생성해 삽입한다.
	 * @author junyoung
	 */
	@Override
	public MemberVO requestTemporaryPassword(FindPasswordVO findPasswordVO) {
		//System.out.println("멤버 서비스에 들어오나"+findPasswordVO);
		MemberVO memberVO = memberDAO.requestTemporaryPasswordCheckRandomSentence(findPasswordVO);
		String temporaryPassword = passwordFinder.randomSentenceMaker(7);
		//만료된 요청이 아닐 경우에만 동작
		if(memberVO!=null){
			memberVO.setMemberPassword(temporaryPassword);
			memberDAO.memberUpdatePassword(memberVO);
			//요청 수행 후 패스워드 파인드 테이블의 정보 삭제
			memberDAO.deletePasswordFindRequest(findPasswordVO);
			memberVO = memberDAO.findByPassword(memberVO.getMemberEmail());
		}else{
			memberVO = null;
		}
		return memberVO;
	}

	
	/**
	 * 구독정보 확인 후 insert, delete 수행 후
	 * 구독자 숫자와 함께 map에 담아서 보내기
	 * @author JeSeong Lee
	 */
	@Override
	public HashMap<String, Object> updateSubscriptionInfo(
			SubscriptionInfoVO subscriptionInfoVO) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(memberDAO.selectSubscriptionInfoVO(subscriptionInfoVO) == null){
			memberDAO.insertSubscriptionInfoVO(subscriptionInfoVO);
			map.put("subscriptionResult", "insert");
		} else{
			memberDAO.deleteSubscriptionInfoVO(subscriptionInfoVO);
			map.put("subscriptionResult", "delete");
		}
		// 해당 회원을 구독하는 현황 리스트
		List<SubscriptionInfoVO> subscriberInfoList
			= memberDAO.getSubscriberListByPublisherEmail(subscriptionInfoVO);
		map.put("subscriberCount", subscriberInfoList.size());
		return map;
	}
	
	/**
	 * 구독자 email로 구독리스트 받기
	 * 세션에 세팅해주기용
	 * @author JeSeong Lee
	 */
	@Override
	public List<SubscriptionInfoVO> getSubscriptionListBySubscriberMemberEmail(
			SubscriptionInfoVO subscriptionInfoVO) {
		return memberDAO.getSubscriptionListBySubscriberMemberEmail(subscriptionInfoVO);
	}
	
	/**
	 * 문의글 쓰기
	 * @author 재영
	 */
		@Override
		public void insertServiceCenter(ServiceCenterVO ServiceCenterVO){
			 memberDAO.insertServiceCenter(ServiceCenterVO);
	    }
		
	/**
	 * 문의글 리스트를 받아오는 메서드
	 * @author 재영
	 */
		@Override
		public ServiceCenterListVO ServiceCenterList(int pageNo){
			PagingBean pb=null;
			List<ServiceCenterVO>list=memberDAO.ServiceCenterList(pageNo);
			int totalReports=memberDAO.AllCount();
			//System.out.println(totalReports);
			if(pageNo!=0){
				pb= new PagingBean(totalReports,pageNo);
				}else{
					pb= new PagingBean(totalReports);
				}
			ServiceCenterListVO ServiceCenterListVO=new ServiceCenterListVO(list,pb);
			return ServiceCenterListVO;
		}
		/**
		 * 문의글 상세히보기 메서드
		 * @author 재영
		 */
		@Override
		public ServiceCenterVO ServiceCenterView(int ServiceCenterNo){
			return memberDAO.ServiceCenterView(ServiceCenterNo);
		}
		
		/**
		 * 회원을 신고하는 메서드
		 */
		@Override
		public HashMap<String, String> memberReport(String memberReportEmail,
				String memberReporterEmail) {
			return utilService.memberReport(memberReportEmail,memberReporterEmail);
		}
	
		
		/**
		 * 자동로그인 용 쿠키 저장
		 * @author JeSeong Lee
		 */
		@Override
		public void saveAutoLogInfo(String alIDCookie, String alKeyCookie) {
			memberDAO.saveAutoLogInfo(alIDCookie, alKeyCookie);
		}
		
		/**
		 * 난수값을 받아옴
		 * @author JeSeong Lee
		 */
		@Override
		public String getMemberAutologinMD5(String strALID) {
			return memberDAO.getMemberAutologinMD5(strALID);
		}
		
		/**
		 * 쿠키이메일로 비밀번호 받아옴
		 * @author JeSeong Lee
		 */
		@Override
		public String getMemberPasswordByCookieEmail(String strALID) {
			return memberDAO.getMemberPasswordByCookieEmail(strALID);
		}
		
		/**
		 * 로그아웃시 멤버테이블의 난수값 삭제
		 * @author JeSeong Lee
		 */
		@Override
		public void deleteMemberCookieByMemberEmail(String memberEmail) {
			memberDAO.deleteMemberCookieByMemberEmail(memberEmail);
		}
		
		
}