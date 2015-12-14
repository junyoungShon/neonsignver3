package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cobro.neonsign.utility.PasswordFinder;
import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.MemberListVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PagingBean;
import org.cobro.neonsign.vo.PickedVO;
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
	public MemberVO memberLogin(MemberVO memberVO) {
		return memberDAO.memberLogin(memberVO);
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
	public MemberVO defaultMemberLogin(MemberVO memberVO) {
		return memberDAO.defaultMemberLogin(memberVO);
	}
	
	/**
	 * 찜 여부 확인 후 찜 등록 및 제거
	 * @author JeSeong Lee
	 */
	@Override
	public HashMap<String, Object> updatePickedVO(PickedVO pvo) {
		System.out.println(memberDAO.selectPickedVO(pvo));
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
		System.out.println("멤버 서비스에 들어오나"+findPasswordVO);
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
	 * ajax에 보여줄 정보들과
	 * map에 담아서 보내기
	 * @author JeSeong Lee
	 */
	@Override
	public HashMap<String, Object> updateSubscriptionInfo(
			SubscriptionInfoVO subscriptionInfoVO) {
		System.out.println(memberDAO.selectSubscriptionInfoVO(subscriptionInfoVO));
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(memberDAO.selectSubscriptionInfoVO(subscriptionInfoVO) == null){
			memberDAO.insertSubscriptionInfoVO(subscriptionInfoVO);
			map.put("subscriptionResult", "insert");
		} else{
			memberDAO.deleteSubscriptionInfoVO(subscriptionInfoVO);
			map.put("subscriptionResult", "delete");
		}
		// 구독된 현황 리스트
		List<SubscriptionInfoVO> subscriberInfoList
			= memberDAO.getSubscriberListByPublisherEmail(subscriptionInfoVO);
		map.put("subscriberCount", subscriberInfoList.size());
		ArrayList<MemberVO> subscriberMemberList = new ArrayList<MemberVO>();
		for(int i = 0 ; i<subscriberInfoList.size() ; i++){
			subscriberMemberList.add(memberDAO.findMemberByEmail(subscriberInfoList.get(i).getSubscriber()));
		}
		map.put("subscriberMemberList", subscriberMemberList);
		// 구독한 현황 리스트
		List<SubscriptionInfoVO> subscriptingInfoList
			= memberDAO.getSubscriptionListBySubscriberMemberEmail(subscriptionInfoVO);
		
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
	
	
	
}
