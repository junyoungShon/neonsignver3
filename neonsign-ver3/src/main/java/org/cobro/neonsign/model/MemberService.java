package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.MemberListVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PickedVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;

public interface MemberService {
	public MemberVO findMemberByEmail(String emailComp);
	public MemberVO findMemberByNickName(String nameComp);
	public int pointMemberRegister(MemberVO mvo);
	public int memberUpdate(MemberVO memberVO);
	public MemberVO memberLogin(MemberVO mvo);
	public void memberDelete(MemberVO mvo);
	public ArrayList<MemberVO> getNotifyMemberList(MemberVO mvo);
	public MemberListVO getMemberList(int i);
	public void memberBlock(String memberEmail);
	public void memberBlockRelease(String memberEmail);
	public HashMap<String, Object> updatePickedVO(PickedVO pvo);
	public List<PickedVO> getPickListByMemberEmail(String memberEmail);
	public MemberVO defaultMemberLogin(MemberVO memberVO);
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
}
