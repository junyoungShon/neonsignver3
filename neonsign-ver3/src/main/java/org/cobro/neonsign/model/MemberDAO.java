package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.List;

import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PickedVO;
import org.cobro.neonsign.vo.ServiceCenterVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;

public interface MemberDAO {
	public MemberVO findMemberByEmail(String emailComp);
	public MemberVO findMemberByNickName(String nameComp);
	public int memberRegister(MemberVO mvo);
	public int memberUpdate(MemberVO memberVO);
	public MemberVO memberLogin(MemberVO mvo);
	public void memberDelete(MemberVO memberVO);
	public ArrayList<MemberVO> getNotifyMemberList(MemberVO mvo);
	public List<MemberVO> getMemberList(int pageNo);
	public void memberBlock(String memberEmail);
	public List<MemberVO> getBlockMemberList(int pageNo);
	public void memberBlockRelease(String memberEmail);
	public PickedVO selectPickedVO(PickedVO pvo);
	public int insertPickedVO(PickedVO pvo);
	public int deletePickedVO(PickedVO pvo);
	public List<PickedVO> getPickListByMemberEmail(String memberEmail);
	public MemberVO defaultMemberLogin(MemberVO memberVO);
	public MemberVO findByPassword(String checkPassComp);
	public int allMembers();
	public int allBlockMembers();
	public void insertPasswordFindRequest(FindPasswordVO findPasswordVO);
	public MemberVO requestTemporaryPasswordCheckRandomSentence(
			FindPasswordVO findPasswordVO);
	public void memberUpdatePassword(MemberVO memberVO);
	public MemberVO confirmPasswordFindRequest(FindPasswordVO findPasswordVO);
	public void deletePasswordFindRequest(FindPasswordVO findPasswordVO);
	public void memberPointPlusUpdater(String memberEmail, int i);
	public void memberPointMinusUpdater(String memberEmail, int i);
	/**
	 * 구독하기
	 * @author JeSeong Lee
	 */
	public SubscriptionInfoVO selectSubscriptionInfoVO(SubscriptionInfoVO subscriptionInfoVO);
	public int insertSubscriptionInfoVO(SubscriptionInfoVO subscriptionInfoVO);
	public int deleteSubscriptionInfoVO(SubscriptionInfoVO subscriptionInfoVO);
	public List<SubscriptionInfoVO> getSubscriberListByPublisherEmail(
			SubscriptionInfoVO subscriptionInfoVO);
	public List<SubscriptionInfoVO> getSubscriptionListBySubscriberMemberEmail(
			SubscriptionInfoVO subscriptionInfoVO);
	
	public void insertServiceCenter(ServiceCenterVO serviceCenterVO);
	public List<ServiceCenterVO> ServiceCenterList(int pageNo);
	public int AllCount();
	public ServiceCenterVO ServiceCenterView(int serviceCenterNo);
	public int memberLastLoginDateUpdate(MemberVO memberVO);
	public void memberLastLoginDateInsert(MemberVO memberVO);
	public int memberBlackdateUpdate(String memberEmail);
	public void memberBlackdateInsert(String memberEmail);
	public String getLastLoginDate(String memberEmail);
	
	/**
	 * 자동 로그인
	 * @author JeSeong Lee
	 */
	// 로그인시 멤버테이블에 쿠키정보값 저장
	public void saveAutoLogInfo(String alIDCookie, String alKeyCookie);
	// auth_* 시 인터셉터에서 쿠키값 확인
	public String getMemberAutologinMD5(String strALID);
	// 쿠키이메일로 비밀번호 받아옴
	public String getMemberPasswordByCookieEmail(String strALID);
	// 로그아웃시 멤버테이블의 난수값 삭제
	public void deleteMemberCookieByMemberEmail(String memberEmail);
	
}
