package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PickedVO;
import org.cobro.neonsign.vo.ServiceCenterVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class MemberDAOImpl implements MemberDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public MemberVO findMemberByEmail(String emailComp) {
		
		return sqlSessionTemplate.selectOne("member.findMemberByEmail", emailComp);
	}

	@Override
	public MemberVO findMemberByNickName(String nameComp) {
		
		return sqlSessionTemplate.selectOne("member.findMemberByNickName", nameComp);
	}

	@Override
	public int memberRegister(MemberVO memberVO) {
		return sqlSessionTemplate.insert("member.memberRegister",memberVO);
	}

	@Override
	public int memberUpdate(MemberVO memberVO) {
		
		//System.out.println(memberVO.getMemberPassword());
		return sqlSessionTemplate.update("member.memberUpdate",memberVO);
	}

	@Override
	public MemberVO memberLogin(MemberVO memberVO) {
		return sqlSessionTemplate.selectOne("member.memberLogin",memberVO);
	}




	@Override
	public void memberDelete(MemberVO memberVO) {
		
		 sqlSessionTemplate.update("member.memberDelete",memberVO);
	}

	@Override
	public ArrayList<MemberVO> getNotifyMemberList(MemberVO mvo) {
		
		return null;
	}

	@Override
	/**
	 * 일반 회원 리스트를 받아온다
	 * @author 윤택
	 */
	public List<MemberVO> getMemberList(int pageNo) {
		return sqlSessionTemplate.selectList("member.RegisterMemberList",pageNo);
	}

	@Override
	/**
	 * 회원의 서비스를 중지 시킨다
	 * @author 윤택
	 */
	public void memberBlock(String memberEmail) {
			sqlSessionTemplate.update("member.memberBlock",memberEmail);
	}

	@Override
	/**
	 * 불량회원 리스트를 받아온다
	 * @author 윤택
	 */
	public List<MemberVO> getBlockMemberList(int pageNo) {
		
		return sqlSessionTemplate.selectList("member.blockMemberList",pageNo);
	}

	@Override
	/**
	 * 회원의 서비스를 시작 시킨다
	 */
	public void memberBlockRelease(String memberEmail) {
		sqlSessionTemplate.update("member.memberBlockRelease",memberEmail);
	}


	/**
	 * pickedVO가 없는 초기 회원의 로그인을 위한 디폴트 로그인
	 * @author junyoung
	 */
	@Override
	public MemberVO defaultMemberLogin(MemberVO memberVO) {
		//System.out.println(memberVO);
		return sqlSessionTemplate.selectOne("member.defaultMemberLogin", memberVO);
	}
	
	/**
	 * 찜 여부 확인
	 * @author JeSeong Lee
	 */
	@Override
	public PickedVO selectPickedVO(PickedVO pvo) {
		return sqlSessionTemplate.selectOne("member.selectPickedVO", pvo);
	}
	
	/**
	 * 찜 여부 확인 후 찜 등록
	 * @author JeSeong Lee
	 */
	@Override
	public int insertPickedVO(PickedVO pvo) {
		return sqlSessionTemplate.insert("member.insertPickedVO", pvo);
	}
	/**
	 * 찜 여부 확인 후 찜 삭제
	 * @author JeSeong Lee
	 */
	@Override
	public int deletePickedVO(PickedVO pvo) {
		return sqlSessionTemplate.delete("member.deletePickedVO", pvo);
	}
	/**
	 * email로 찜리스트 받아옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<PickedVO> getPickListByMemberEmail(String memberEmail) {
		return sqlSessionTemplate.selectList("member.getPickListByMemberEmail", memberEmail);
	}

	@Override
	public MemberVO findByPassword(String checkPassComp) {
		
		return sqlSessionTemplate.selectOne("member.findByPassword",checkPassComp);
	}
	@Override
	/**
	 * 일반회원 수
	 */
	public int allMembers() {
		
		return sqlSessionTemplate.selectOne("member.allMembers");
	}

	@Override
	/**
	 * 불량회원 수
	 */
	public int allBlockMembers() {
		
		return sqlSessionTemplate.selectOne("member.allBlockMembers");
	}
	/**
	 * 비밀 번호 찾기 요청이 들어올 경우 테이블에 기록을 삽입해준다.
	 * @author junyoung
	 */
	@Override
	public void insertPasswordFindRequest(FindPasswordVO findPasswordVO) {
		sqlSessionTemplate.insert("member.insertPasswordFindRequest",findPasswordVO);
		
	}
	/**
	 * 회원의 비밀번호만을 변경해주는 dao 메서드로서 비밀번호 찾기에 사용된다.
	 * @author junyoung
	 */
	@Override
	public void memberUpdatePassword(MemberVO memberVO) {
		sqlSessionTemplate.update("member.memberUpdatePassword", memberVO);
	}
	/**
	 * 해당 이메일로 이전에 비밀번호 찾기 요청이 있었던 경우가 있는지 확인해주는 메서드
	 * @author junyoung
	 */
	@Override
	public MemberVO confirmPasswordFindRequest(FindPasswordVO findPasswordVO) {
		return sqlSessionTemplate.selectOne("member.confirmPasswordFindRequest", findPasswordVO);
	}
	/**
	 * 해당 이메일로 이전에 비밀번호 찾기 요청이 있었던 경우 테이블에서 관련 정보를 삭제해준다.
	 * @author junyoung
	 */
	@Override
	public void deletePasswordFindRequest(FindPasswordVO findPasswordVO) {
		sqlSessionTemplate.delete("member.deletePasswordFindRequest", findPasswordVO);
	}
	/**
	 * 랜덤 문자열과 요청 회원 이메일을 비교하여 유효한 요청인지 검증
	 * @author junyoung
	 */
	@Override
	public MemberVO requestTemporaryPasswordCheckRandomSentence(
			FindPasswordVO findPasswordVO) {
		return sqlSessionTemplate.selectOne("member.requestTemporaryPasswordCheckRandomSentence", findPasswordVO);
	}
	/**
	 * 회원의 이름과 점수를 받아서 점수를 플러스 업로드 해준다.
	 * @author junyoung
	 */
	@Override
	public void memberPointPlusUpdater(String memberEmail, int point) {
		Map<String, Object> map = new HashMap<String, Object>();
		//System.out.println(memberEmail+" 여기는 DAO"+point);
		map.put( "memberEmail", memberEmail );
		map.put( "memberPoint", point );
		sqlSessionTemplate.update("member.memberPointPlusUpdater",map);
	}
	/**
	 * 회원의 이름과 점수를 받아서 점수를 마이너스 업로드 해준다.
	 * @author junyoung
	 */
	@Override
	public void memberPointMinusUpdater(String memberEmail, int point) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "memberEmail", memberEmail );
		map.put( "memberPoint", point );
		sqlSessionTemplate.update("member.memberPointMinusUpdater",map);
	}
	

	/**
	 * 게시자, 구독자 email로 구독정보 반환
	 * null인지, 구독정보 확인
	 * @author JeSeong Lee
	 */
	@Override
	public SubscriptionInfoVO selectSubscriptionInfoVO(SubscriptionInfoVO subscriptionInfoVO) {
		return sqlSessionTemplate.selectOne("member.selectSubscriptionInfoVO", subscriptionInfoVO);
	}
	
	/**
	 * null인지, 구독정보 확인
	 * 구독중 아니면 insert
	 * @author JeSeong Lee
	 */
	@Override
	public int insertSubscriptionInfoVO(SubscriptionInfoVO subscriptionInfoVO) {
		return sqlSessionTemplate.insert("member.insertSubscriptionInfoVO", subscriptionInfoVO);
	}

	/**
	 * null인지, 구독정보 확인
	 * 구독중이면 delete
	 * @author JeSeong Lee
	 */
	@Override
	public int deleteSubscriptionInfoVO(SubscriptionInfoVO subscriptionInfoVO) {
		return sqlSessionTemplate.delete("member.deleteSubscriptionInfoVO", subscriptionInfoVO);
	}
	/**
	 * ajax용 게시자이메일로 구독자들리스트 받아옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<SubscriptionInfoVO> getSubscriberListByPublisherEmail(
			SubscriptionInfoVO subscriptionInfoVO) {
		return sqlSessionTemplate.selectList("member.getSubscriberListByPublisherEmail", subscriptionInfoVO);
	}
	
	/**
	 * 구독자(세션의 이메일) 이메일로 구독리스트 받아옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<SubscriptionInfoVO> getSubscriptionListBySubscriberMemberEmail(
			SubscriptionInfoVO subscriptionInfoVO) {
		return sqlSessionTemplate.selectList("member.getSubscriptionListBySubscriberMemberEmail", subscriptionInfoVO);
	}


/**
	 * 문의글쓰기
	 * @author 재영
	 */
		@Override
		public void insertServiceCenter(ServiceCenterVO ServiceCenterVO){
			 sqlSessionTemplate.insert("member.ServiceCenterinsert",ServiceCenterVO);
		}

		/**
		 * 문의글리스트
		 * @author 윤택
		 */
		@Override
		public List<ServiceCenterVO> ServiceCenterList(int pageNo){
			return  sqlSessionTemplate.selectList("member.ServiceCenterList",pageNo);
			}

		/**
		 *  총 문의글 수
		 *  @author 재영
		 */
		@Override
		public int AllCount(){
			return sqlSessionTemplate.selectOne("member.Allcount");	
		}
		/**
		 * 문의글 상세히보기
		 * @author 재영
		 */
		@Override
		public ServiceCenterVO ServiceCenterView(int serviceCenterNo) {
			return sqlSessionTemplate.selectOne("member.ServiceCenterView",serviceCenterNo);
		}
	

/**
	 * 최종 접속일을 Update하는 메서드
	 */
	@Override
	public int memberLastLoginDateUpdate(MemberVO memberVO) {
		
		return sqlSessionTemplate.update("member.memberLastLoginDateUpdate",memberVO);
	}
	/**
	 *  최종 접속일을 Insert하는 메서드
	 */
	@Override
	public void memberLastLoginDateInsert(MemberVO memberVO) {
		
		sqlSessionTemplate.insert("member.memberLastLoginDateInsert",memberVO);
	}

	@Override
	/**
	 * 회원의 정지일을 업데이트
	 * @author 윤택
	 */
	public int memberBlackdateUpdate(String memberEmail) {
		return sqlSessionTemplate.update("member.memberBlackdateUpdate",memberEmail);
	}

	@Override
	/**
	 * 회원의 정지일을 생성
	 * @author 윤택
	 */
	public void memberBlackdateInsert(String memberEmail) {
		
		sqlSessionTemplate.insert("member.memberBlackdateInsert",memberEmail);
	}

	/**
	 * 최종 접속일을 받아옴
	 * @author Youn
	 */
	@Override
	public String getLastLoginDate(String memberEmail) {
		
		return sqlSessionTemplate.selectOne("member.getLastLoginDate",memberEmail);
	}
	
	/**
	 * 자동 로그인 용 쿠키 저장
	 * @author JeSeong Lee
	 */
	@Override
	public void saveAutoLogInfo(String alIDCookie, String alKeyCookie) {
		// 2가지 정보를 담기위해 Map 생성
		HashMap<String, String> logInfoCookie= new HashMap<String, String>();
		logInfoCookie.put("alIDCookie", alIDCookie);
		logInfoCookie.put("alKeyCookie", alKeyCookie);
		sqlSessionTemplate.update("member.saveAutoLogInfo", logInfoCookie);
	}
	
	/**
	 * DB에 쿠키정보 유무 확인
	 * @author JeSeong Lee
	 */
	@Override
	public String getMemberAutologinMD5(String strALID) {
		return sqlSessionTemplate.selectOne("member.getMemberAutologinMD5", strALID);
	}
	
	/**
	 * 쿠키이메일로 비밀번호 받기
	 * @author JeSeong Lee
	 */
	@Override
	public String getMemberPasswordByCookieEmail(String strALID) {
		return sqlSessionTemplate.selectOne("member.getMemberPasswordByCookieEmail", strALID);
	}
	
	/**
	 * 로그아웃시 DB의 쿠키난수 null로 변경
	 * @author JeSeong Lee
	 */
	@Override
	public void deleteMemberCookieByMemberEmail(String memberEmail) {
		sqlSessionTemplate.update("member.deleteMemberCookieByMemberEmail", memberEmail);
	}
	
}
