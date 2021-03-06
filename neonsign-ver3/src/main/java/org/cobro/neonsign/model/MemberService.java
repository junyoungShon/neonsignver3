package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.MemberListVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PickedVO;
import org.cobro.neonsign.vo.ServiceCenterListVO;
import org.cobro.neonsign.vo.ServiceCenterVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;

public interface MemberService {
	public MemberVO findMemberByEmail(String emailComp);
	public MemberVO findMemberByNickName(String nameComp);
	public int pointMemberRegister(MemberVO mvo);
	public int memberUpdate(MemberVO memberVO);
	public void memberDelete(MemberVO mvo);
	public ArrayList<MemberVO> getNotifyMemberList(MemberVO mvo);
	public MemberListVO getMemberList(int i);
	public void memberBlock(String memberEmail);
	public void memberBlockRelease(String memberEmail);
	public HashMap<String, Object> updatePickedVO(PickedVO pvo);
	public List<PickedVO> getPickListByMemberEmail(String memberEmail);
	public MemberVO findByPassword(String mailComp);
	public MemberListVO getBlockMemberList(int i);
	public void findPasswordMailRequest(FindPasswordVO findPasswordVO);
	public MemberVO requestTemporaryPassword(FindPasswordVO findPasswordVO);
	/**
	 * 구독정보 수정
	 * @author JeSeong Lee
	 */
	public HashMap<String, Object> updateSubscriptionInfo(
			SubscriptionInfoVO subscriptionInfoVO);
	public List<SubscriptionInfoVO> getSubscriptionListBySubscriberMemberEmail(
			SubscriptionInfoVO subscriptionInfoVO);
	
	public void insertServiceCenter(ServiceCenterVO serviceCenterVO);
	public ServiceCenterListVO ServiceCenterList(int i);
	public ServiceCenterVO ServiceCenterView(
			int serviceCenterNo);
	public MemberVO pointMemberLogin(MemberVO memberVO);
	public MemberVO pointDefaultMemberLogin(MemberVO memberVO1);
	public HashMap<String, String> memberReport(String memberReportEmail,
			String memberReporterEmail);
	
	/**
	 * 자동 로그인용 쿠키
	 * @author JeSeong Lee
	 */
	public void saveAutoLogInfo(String alIDCookie, String alKeyCookie);
	public String getMemberAutologinMD5(String strALID);
	public String getMemberPasswordByCookieEmail(String strALID);
	// 로그아웃시 멤버테이블의 난수값 삭제
	public void deleteMemberCookieByMemberEmail(String memberEmail);
	
}
