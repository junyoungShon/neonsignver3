package org.cobro.neonsign;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.model.BoardDAO;
import org.cobro.neonsign.model.BoardService;
import org.cobro.neonsign.model.MemberDAO;
import org.cobro.neonsign.model.MemberService;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
*    TDD : 테스트 주도 개발(test-driven development, TDD)은 
*            매우 짧은 개발 사이클을 반복하는 소프트웨어 개발 프로세스
*            
*    JUnit: 자바 단위 테스트를 위한 TDD 프레임워크
*    
*    아래 라이브러리가 maven의 pom.xml에 추가되어야 한다. 
      <!-- spring junit  -->
 <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-test</artifactId>
           <version>${org.springframework-version}</version>            
       </dependency>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class JeSeongTest {
	@Resource
	private MemberService memberService;
	@Resource
	private BoardService boardService;
	@Resource
	private BoardDAO boardDAO;
	@Resource
	private MemberDAO memberDAO;
	

	@Test
	public void test(){
		
		
		
		
		SubscriptionInfoVO subscriptionInfoVO = new SubscriptionInfoVO();
		String publisher = "a@gmail.com";
		String subscriber = "b@gmail.com";
		subscriptionInfoVO.setPublisher(publisher);
		subscriptionInfoVO.setSubscriber(subscriber);
		// memberDAO.selectSubscriptionInfoVO(subscriptionInfoVO);
		/*if(memberDAO.selectSubscriptionInfoVO(subscriptionInfoVO) == null){
			memberDAO.insertSubscriptionInfoVO(subscriptionInfoVO);
			System.out.println("insert");
		} else{
			memberDAO.deleteSubscriptionInfoVO(subscriptionInfoVO);
			System.out.println("delete");
		}*/
		System.out.println(memberDAO.getSubscriberListByPublisherEmail(subscriptionInfoVO));
/*		List<SubscriptionInfoVO> subscriptionInfoList
		= memberDAO.getSubscriberListByPublisherEmail(subscriptionInfoVO);
		System.out.println(subscriptionInfoList);
		ArrayList<MemberVO> subscriberList = new ArrayList<MemberVO>();
		for(int i = 0 ; i<subscriptionInfoList.size() ; i++){
			subscriberList.add(memberDAO.findMemberByEmail(subscriptionInfoList.get(i).getSubscriber()));
		}
		System.out.println(subscriberList);*/
		/*String memberEmail = "y5@gmail.com";
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberEmail(memberEmail);
		System.out.println(boardDAO.getJoinAgeByEmail(memberVO));*/
		/*ArrayList<TagBoardVO> mostWriteTagByEmailList
		= (ArrayList<TagBoardVO>) boardDAO.getMostWriteTagByEmail(memberVO);
		TagBoardVO tagBoardVO = mostWriteTagByEmailList.get(0);
		
		System.out.println("최종 : " + tagBoardVO);*/
		/*ArrayList<Integer> joinMainArticleNoList = (ArrayList<Integer>) boardDAO.getJoinMainArticleNoByEmail(memberVO);
		HashSet hs = new HashSet(joinMainArticleNoList);
		ArrayList<Integer> nonDupJoinMainArticleNoList = new ArrayList<Integer>(hs);
		System.out.println("1 : " + nonDupJoinMainArticleNoList);
		ArrayList<MainArticleVO> joinMainArticleVOList = new ArrayList<MainArticleVO>();
		for(int i = 0 ; i<nonDupJoinMainArticleNoList.size() ; i++){
			joinMainArticleVOList.add(boardDAO.getMainArticleByMainArticleNoOrderByDate(nonDupJoinMainArticleNoList.get(i)));
		}
		System.out.println("2 : " + joinMainArticleVOList);
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int j = 0 ; j<joinMainArticleVOList.size() ; j++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(joinMainArticleVOList.get(j).getMemberVO())); 
			joinMainArticleVOList.get(j).getMemberVO().setRankingVO(rankingVOList.get(j));
		}
		System.out.println("3 : " + joinMainArticleVOList); */
		/*List<Integer> writeMainArticleNoList = boardDAO.getWriteMainArticleNoByEmail(memberVO);
		System.out.println("1 : " + writeMainArticleNoList);
		ArrayList<MainArticleVO> writeMainArticleVOList = new ArrayList<MainArticleVO>();
		for(int i = 0 ; i<writeMainArticleNoList.size() ; i++){
			writeMainArticleVOList.add(boardDAO.getMainArticleByMainArticleNoOrderByDate(writeMainArticleNoList.get(i)));
		}
		System.out.println(writeMainArticleVOList);
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int j = 0 ; j<writeMainArticleVOList.size() ; j++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(writeMainArticleVOList.get(j).getMemberVO())); 
			System.out.println("3 : " + rankingVOList);
			writeMainArticleVOList.get(j).getMemberVO().setRankingVO(rankingVOList.get(j));
		}
		System.out.println("최종 : " + writeMainArticleVOList); */
		/*List<Integer> pickedMainArticleNoList = boardDAO.getPickedMainArticleNoByEmail(memberVO);
		System.out.println("1 : " + pickedMainArticleNoList);
		ArrayList<MainArticleVO> pickedMainArticleVOList = new ArrayList<MainArticleVO>();
		for(int i = 0 ; i<pickedMainArticleNoList.size() ; i++){
			System.out.println("No : "+pickedMainArticleNoList.get(i));
			pickedMainArticleVOList.add(boardDAO.getMainArticleByMainArticleNoOrderByDate(pickedMainArticleNoList.get(i)));
		}
		System.out.println("2 : " + pickedMainArticleVOList);
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int j = 0 ; j<pickedMainArticleVOList.size() ; j++){
			
			System.out.println(pickedMainArticleVOList.get(j).getMemberVO().getMemberEmail());
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(pickedMainArticleVOList.get(j).getMemberVO())); 
			System.out.println(rankingVOList);
			pickedMainArticleVOList.get(j).getMemberVO().setRankingVO(rankingVOList.get(j));
		}
		System.out.println("최종 : " + pickedMainArticleVOList);*/
		/*for(int i = 0 ; i<mainArticleVOList.size() ; i++){
			mainArticleVOList = boardDAO.getJoinMainArticleByEmailOrderByDate(mainArticleVOList.get(i));
		}
		System.out.println(mainArticleVOList);*/
		/*RankingVO rankingVO = boardDAO.getMemberRankingByMemberEmail(memberVO);
		memberVO.setRankingVO(rankingVO);
		System.out.println(memberVO);
		List<RankingVO> rankingVOList = boardService.getRankingList();
		System.out.println(rankingVOList);*/
		/*List<MainArticleVO> joinMainArticleList
		= boardService.getJoinMainArticleByEmailOrderByDate(memberVO);
		System.out.println(joinMainArticleList);*/
		/*List<MainArticleVO> writeMainArticleList
		= boardService.getWriteMainArticleByEmailOrderByDate(memberVO);
		System.out.println(writeMainArticleList);*/
		// memberVO = boardDAO.getMemberNickNameByEmail(memberVO);
		// System.out.println("1. mem : " + memberVO);
		/*String memberGrade = boardDAO.getMemberRankingByMemberEmail(memberVO);
		RankingVO rankingVO = new RankingVO();
		rankingVO.setMemberGrade(memberGrade);
		memberVO.setRankingVO(rankingVO);
		System.out.println("1. ranking : " + memberVO);*/
		/*memberVO = boardService.getMemberRankingByMemberEmail(memberVO);
		System.out.println("1. ranking : " + memberVO);
		System.out.println("2. pickList : " + boardService.getPickedMainArticleByMemberEmailOrderByDate(memberVO));*/
		
		
		
		
		/*PickedVO pvo = new PickedVO();
		String memberEmail = "a@naver.com";
		int mainArticleNo = 3;
		pvo.setMainArticleNo(mainArticleNo);
		pvo.setMemberEmail(memberEmail);
		System.out.println("pvo : "+pvo);
		System.out.println(memberService.updatePickedVO(pvo));*/
		/*
		memberDAO.selectPickedVO(pvo);
		System.out.println("select : " + pvo);
		// System.out.println("insert : " + memberDAO.insertPickedVO(pvo));
		if(memberDAO.selectPickedVO(pvo) == null){
			System.out.println("null 희");
			System.out.println("insert : " + memberDAO.insertPickedVO(pvo));
		} else{
			System.out.println("not null");
			System.out.println("delete" + memberDAO.deletePickedVO(pvo));
		}*/
		// memberDAO.pickCount(pvo);
		/*List<MainArticleVO> newMainArticleVOList
		= boardDAO.selectListNotCompleteMainArticleOrderByDate();
		System.out.println(newMainArticleVOList);
		for(int i = 0 ; i<newMainArticleVOList.size() ; i++){
			List<TagBoardVO> tagList = newMainArticleVOList.get(i).getTagBoardVOList();
			for(int j = 0 ; j<tagList.size() ; j++){
				if(tagList.get(j).getMainArticleNo() == 2){
					System.out.println("tagName : " +  tagList.get(j).getTagName());
				}
			}
		}*/
		/*List<MainArticleVO> bestMainArticleVOList
		= boardDAO.selectListNotCompleteMainArticleOrderByDate();
		System.out.println(bestMainArticleVOList);
		for(int i = 0 ; i<bestMainArticleVOList.size() ; i++){
			List<TagBoardVO> tagList = bestMainArticleVOList.get(i).getTagBoardVOList();
			for(int j = 0 ; j<tagList.size() ; j++){
				if(tagList.get(j).getMainArticleNo() == 2){
					System.out.println("tagName : " +  tagList.get(j).getTagName());
				}
			}
		}*/
/*		String tagName = "";
		ArrayList<TagBoardVO> list = new ArrayList<TagBoardVO>();
		for(int i = 0 ; i<newMainArticleVOList.size() ; i++){
			list = boardDAO.getMainArticleTagList(newMainArticleVOList.get(i).getMainArticleNo());
			for(int j = 0 ; j<list.size() ; j++){
				if(j == list.size()-1){
					tagName += "#" + list.get(j).getTagName();
				}else{
					tagName += "#" +  list.get(j).getTagName() + ", ";
				}
				newMainArticleVOList.get(i).setTagName(tagName);
			}
			tagName = "";
		}*/
		
	/*	String memberEmail = "a@naver.com";
		String memberPassword = "aaaa";
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberEmail(memberEmail);
		memberVO.setMemberPassword(memberPassword);
		memberVO = memberDAO.memberLogin(memberVO);
		System.out.println(memberVO);
		System.out.println(memberVO.getPickedVOList());
		List<PickedVO> list = memberVO.getPickedVOList();
		for(int i = 0 ; i<list.size() ; i++){
			System.out.println(list.get(i).getMainArticleNo());
			if(list.get(i).getMainArticleNo()==4){
				System.out.println("찜");
			}else if(list.get(i).getMainArticleNo() ==3){
				System.out.println("뻑유");
			}else if(list.get(i).getMainArticleNo()==5){
				System.out.println("찜");
			}
		}*/
/*		for(int i = 0 ; i<memberVOList.size() ; i++){
			List<PickedVO> pList = memberVOList.get(i).getPickedVOList();
			
			System.out.println("pList : "+ pList);
			for(int j = 0 ; j<pList.size() ; j++){
				System.out.println(pList.get(j).getMainArticleNo());
				pickedList += pList.get(j).getMainArticleNo() + ", ";
				System.out.println("j for 문 : " + pickedList);
			}
		}
		// System.out.println(pickList);*/
	}

}