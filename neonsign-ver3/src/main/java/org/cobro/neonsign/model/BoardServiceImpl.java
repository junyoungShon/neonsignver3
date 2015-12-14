package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cobro.neonsign.utility.StoryLinker;
import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MainArticleImgVO;
import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.RankingVO;
import org.cobro.neonsign.vo.ReportListVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;
import org.cobro.neonsign.vo.TagBoardVO;
import org.cobro.neonsign.vo.TagVO;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
	@Resource
	private BoardDAO boardDAO;
	@Resource
	private UtilService utilService;
	@Resource
	private ItjaMemberBean itjaMemberBean;
	/**
	 * 메인아티클을 삽입하면서 mainArticle의 글번호를 받아온다
	 * 이후에 글번호와 태그를 Tag_Board table에 삽입한다.
	 * 트랜젝션의 대상이 되는지 고민해 볼 것!!
	 * @junyoung
	 */
	@Override
	public int pointInsertMainArticle(MainArticleVO mainArticleVO,ArrayList<String> list,TagBoardVO tagBoardVO) {
		boardDAO.insertMainArticle(mainArticleVO);
		System.out.println("boardDAO : "+mainArticleVO.getMainArticleNo());
		for(int i=0;i<list.size();i++){
			tagBoardVO.setMainArticleNo(mainArticleVO.getMainArticleNo());
			tagBoardVO.setTagName(list.get(i));
			boardDAO.insertTagBoardVO(tagBoardVO);
		}
		return 0;
	}
	/**
	 * 글 인서트를 위해 모달창을 클릭할 때 사용자가 태그를 선택해야 하는데
	 * 이때 태그를 인기순으로 출력해 주기 위한 메서드
	 * @author junyoung
	 * @return
	 */
	@Override
	public List<TagVO> selectListTagNameOrderBySearchCount(){
		return boardDAO.selectListTagNameOrderBySearchCount();
	}
	/**
	 * 
	 * @author junyoung
	 */
	public HashMap<String,Object> storyLinking(SubArticleVO subArticleVO){
		System.out.println("스토리링킨 서비스"+subArticleVO);
		int curruntGrade = boardDAO.selectSubArticleCurruntGrade(subArticleVO);
		System.out.println("스토리링킨 서비스 현재 스토리 단계"+curruntGrade);
		List<SubArticleVO> list = boardDAO.selectListHigherLikeSubArticle(subArticleVO);
		HashMap<String,Object> map = new HashMap<String, Object>();
		subArticleVO.setSubAtricleGrade(curruntGrade);
		System.out.println(list.toString());
		System.out.println("스토리링킨 서비스 관련 글 몇개 출력?"+list.size());
		//댓글이 없는 경우 자동 완결 처리 한다.
		if(list.size()==0){
			//메인 아티클의 타이틀을 가져온다.
			MainArticleVO mainArticleVO = boardDAO.selectMainArticleTitleByMainArticleNo(subArticleVO.getMainArticleNo());
			//메인 아티클의 컴플리트 여부를 수정해준다.insertMainArticle
			boardDAO.updateBestToCompletArticle(subArticleVO.getMainArticleNo());
			//베스트 글이 완결 글로 이동할 때 타이틀에 [완결]표시를 달아준다.
			String mainArticleTitle = "[완결]"+mainArticleVO.getMainArticleTitle();
			mainArticleVO.setMainArticleTitle(mainArticleTitle);
			boardDAO.appendToCompleteArticle(mainArticleVO);
			map.put("result","complete");
		}else if(list.size()==1){
			if(list.get(0).getIsEnd()==0){
				//최고 잇자 수 득표한 댓글이 계속 잇는 글일 경우 최종 수정일을 고쳐준다.
				boardDAO.updateDateForMainArticle(subArticleVO.getMainArticleNo());
				//우선 연결을 해준다.
				System.out.println("여기로 오지 ?");
				subArticleVO.setSubArticleNo(list.get(0).getSubArticleNo());
				boardDAO.updateIsConnect(subArticleVO);
				map.put("result","continue");
			}else{
				//최고 잇자 수 득표한 댓글이 그만하자는 글일 경우
				//우선 연결을 해준다.
				subArticleVO.setSubArticleNo(list.get(0).getSubArticleNo());
				boardDAO.updateIsConnect(subArticleVO);
				//메인 아티클의 컴플리트 여부를 수정해준다.
				boardDAO.updateBestToCompletArticle(subArticleVO.getMainArticleNo());
				//메인 아티클의 타이틀을 가져온다.
				MainArticleVO mainArticleVO = boardDAO.selectMainArticleTitleByMainArticleNo(subArticleVO.getMainArticleNo());
				System.out.println("서비스 : "+mainArticleVO);
				//베스트 글이 완결 글로 이동할 때 타이틀에 [완결]표시를 달아준다.
				mainArticleVO.setMainArticleTitle("[완결]"+mainArticleVO.getMainArticleTitle());
				System.out.println("서비스(수정 후) : "+mainArticleVO);
				boardDAO.appendToCompleteArticle(mainArticleVO);
				map.put("result","complete");
			}
		//동점 댓글이 여러개일 경우
		}else{
			int j = 0;
			Long max = 0L;
			//동점 댓글들 중 가장 최근의 댓글들을 찾는다.
			for(int i=0;i<list.size();i++){
				System.out.println();
				 
				if(max<Long.parseLong(list.get(i).getSubArticleDate())){
					max=Long.parseLong(list.get(i).getSubArticleDate());
					j=i;
				}
			}
			if(list.get(j).getIsEnd()==0){
				//최고 잇자 수 득표한 댓글이 계속 잇는 글일 경우 최종 수정일을 고쳐준다.
				boardDAO.updateDateForMainArticle(subArticleVO.getMainArticleNo());
				//우선 연결을 해준다.
				subArticleVO.setSubArticleNo(list.get(j).getSubArticleNo());
				boardDAO.updateIsConnect(subArticleVO);
				map.put("result","continue");
			}else{
				//최고 잇자 수 득표한 댓글이 그만하자는 글일 경우
				//우선 연결을 해준다.
				subArticleVO.setSubArticleNo(list.get(j).getSubArticleNo());
				boardDAO.updateIsConnect(subArticleVO);
				//메인 아티클의 타이틀을 가져온다.
				MainArticleVO mainArticleVO = boardDAO.selectMainArticleTitleByMainArticleNo(subArticleVO.getMainArticleNo());
				//메인 아티클의 컴플리트 여부를 수정해준다.insertMainArticle
				boardDAO.updateBestToCompletArticle(subArticleVO.getMainArticleNo());
				//베스트 글이 완결 글로 이동할 때 타이틀에 [완결]표시를 달아준다.
				String mainArticleTitle = "[완결]"+mainArticleVO.getMainArticleTitle();
				mainArticleVO.setMainArticleTitle(mainArticleTitle);
				boardDAO.appendToCompleteArticle(mainArticleVO);
				map.put("result","complete");
			}
		}
		return map;
	}
	@Override
	public int updateMainArticle(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteMainArticle(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 주제글을 번호로 검색해서 찾는메소드
	 * 
	 * @author daehyeop
	 */
	@Override
	public MainArticleVO selectOneCompleteMainArticleByMainArticleNo(
			MainArticleVO mainArticleVO) {
		MainArticleVO resultMainArticleVO = boardDAO
				.selectOneCompleteMainArticleByMainArticleNo(mainArticleVO);
		return resultMainArticleVO;
	}

	/**
	 * 완결된 주제글을 총잇자수순 리스트로 받는 메소드
	 * 
	 * @author daehyeop
	 */
	@Override
	public List<MainArticleVO> selectListCompleteMainArticle(int pageNo,
			String orderBy, String getTagName) {
		List<MainArticleVO> completeMainArticleList = null;
		//System.out.println("Service orderBy : " + orderBy);
		//System.out.println("Service PageNo : " + pageNo);
		if (orderBy.equals("like")) {
			completeMainArticleList = boardDAO
					.selectListCompleteMainArticleOrderByTotalLike(pageNo);
		} else if (orderBy.equals("date")) {
			completeMainArticleList = boardDAO
					.selectListCompleteMainArticleOrderByDate(pageNo);
		} else if (orderBy.equals("tag")) {
			completeMainArticleList = boardDAO
					.selectListCompleteMainArticleOrderByTag(pageNo, getTagName);
		}
		String tagName = "";
		for(int i = 0 ; i<completeMainArticleList.size() ; i++){
			List<TagBoardVO> tagBoardList = boardDAO.getMainArticleTagList(completeMainArticleList.get(i).getMainArticleNo());
			for(int j = 0 ; j<tagBoardList.size() ; j++){
				if(j == tagBoardList.size()-1){
					tagName += "#" + tagBoardList.get(j).getTagName();
				}else{
					tagName += "#" +  tagBoardList.get(j).getTagName() + " ";
				}
				completeMainArticleList.get(i).setTagName(tagName);
			}
			tagName = "";
		}
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int i = 0 ; i<completeMainArticleList.size() ; i++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(completeMainArticleList.get(i).getMemberVO()));
			System.out.println(rankingVOList);
			completeMainArticleList.get(i).getMemberVO().setRankingVO(rankingVOList.get(i));
		}
		return completeMainArticleList;
	}
	
	@Override
	/**
	 * 새로운 주제글 최신순 List + Tag
	 * @author JeSeong Lee
	 */
	/**
	 * 무한스크롤 적용위해 수정
	 * @param pageNo
	 * @param orderBy
	 * @param getTagName
	 * @return
	 * @author daehyeop
	 */
	public List<MainArticleVO> selectListNotCompleteMainArticle(int pageNo,
			String orderBy, String getTagName) {
		System.out.println("service selectListNotCompleteMainArticle getTagName : " + getTagName);
		List<MainArticleVO> newMainArticleList = null;
		if (orderBy.equals("date")) {
			newMainArticleList
				= boardDAO.selectListNotCompleteMainArticleOrderByDate(pageNo);
		} else if (orderBy.equals("tag")) {
			newMainArticleList
				= boardDAO.selectListNotCompleteMainArticleOrderByTag(pageNo, getTagName);
		}
		String tagName = "";
		for(int i = 0 ; i<newMainArticleList.size() ; i++){
			List<TagBoardVO> tagBoardList = boardDAO.getMainArticleTagList(newMainArticleList.get(i).getMainArticleNo());
			for(int j = 0 ; j<tagBoardList.size() ; j++){
				if(j == tagBoardList.size()-1){
					tagName += "#" + tagBoardList.get(j).getTagName();
				}else{
					tagName += "#" +  tagBoardList.get(j).getTagName() + " ";
				}
				newMainArticleList.get(i).setTagName(tagName);
			}
			tagName = "";
		}
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int i = 0 ; i<newMainArticleList.size() ; i++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(newMainArticleList.get(i).getMemberVO()));
			System.out.println(rankingVOList);
			newMainArticleList.get(i).getMemberVO().setRankingVO(rankingVOList.get(i));
		}
		return newMainArticleList;
	}
	
	
	@Override
	/**
	 * best 주제글 최신순 List 불러오는 메서드 + Tag
	 * @author JeSeong Lee 
	 */
	public List<MainArticleVO> getBestMainArticleVOListOrderByDate() {
		List<MainArticleVO> bestMainArticleList = boardDAO.getBestMainArticleVOListOrderByDate();
		String tagName = "";
		for(int i = 0 ; i<bestMainArticleList.size() ; i++){
			List<TagBoardVO> tagBoardList = boardDAO.getMainArticleTagList(bestMainArticleList.get(i).getMainArticleNo());
			for(int j = 0 ; j<tagBoardList.size() ; j++){
				if(j == tagBoardList.size()-1){
					tagName += "#" + tagBoardList.get(j).getTagName();
				}else{
					tagName += "#" +  tagBoardList.get(j).getTagName() + " ";
				}
				bestMainArticleList.get(i).setTagName(tagName);
			}
			tagName = "";
		}
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int i = 0 ; i<bestMainArticleList.size() ; i++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(bestMainArticleList.get(i).getMemberVO()));
			System.out.println(rankingVOList);
			bestMainArticleList.get(i).getMemberVO().setRankingVO(rankingVOList.get(i));
		}
		return bestMainArticleList;
	}
	
	
	@Override
	public List<MainArticleVO> selectListNotCompleteMainArticleOrderByTotalLike() {
		// TODO Auto-generated method stub
		return null;
	}

	  
	@Override
	/**
	 * 해당 MainArticle 의 정보와 
	 * 이어진 글, 잇는글 목록을  가져 오는 메서드
	 * 	
	 * @author 윤택
	 */
	public Map<String, Object> selectOneNotCompleteMainArticleByMainArticleNo(
			MainArticleVO mainArticleVO) {
		//MainArticle의 정보를 가져온다 (주제글의 정보 , 작성자의 정보)		
		MainArticleVO mainVO=boardDAO.selectOneNotCompleteMainArticleByMainArticleAndSubArticleNo(mainArticleVO);
		//현재의 스토리 단계를 받아 오는 메서드
		SubArticleVO subArticleVO=new SubArticleVO();
		subArticleVO.setMainArticleNo(mainArticleVO.getMainArticleNo());
		int grade=boardDAO.selectSubArticleCurruntGrade(subArticleVO);
		subArticleVO.setSubAtricleGrade(grade);
		//MainArticl의 이어진 글을 받아오는 메서드 
		ArrayList<SubArticleVO> likingSubArticleList=
				(ArrayList<SubArticleVO>)boardDAO.likingSubArticleFindByMainArticleNo(subArticleVO);
		//MainArticl의 잇는 글을 받아오는 메서드
		ArrayList<SubArticleVO> subArticleVOList=(ArrayList<SubArticleVO>)boardDAO.selectListSubArticle(subArticleVO);
		System.out.println("SubArticle 여부 : "+subArticleVOList);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("mainArticleVO", mainVO);map.put("likingSubArticle", likingSubArticleList);map.put("subArticleVO", subArticleVOList);
		return map;
	}
	/**
	 * 가장 최근에 이어진 글의 단계를 얻어와서 1을 더해서 세팅한다.
	 * 해당 단계에서 요청한 사용자가 이미 글을 작성하였는지 확인 하고, 이미 작성했다면 작성을 거부한다.
	 * @author junyoung
	 */
	@Override
	public boolean pointInsertSubArticle(SubArticleVO subArticleVO) {
		boolean flag =false;
		//현재 진행되는 이야기 단계를 반환
		int subArticleCurruntGrade = boardDAO.selectSubArticleCurruntGrade(subArticleVO);
		subArticleVO.setSubAtricleGrade(subArticleCurruntGrade);
		//현재 진행되는 이야기에 이미 사용자가 글을 썻는지 반환 썻으면 1 안썼으면 0
		int alreadyWriteSubArticleInThisGrade = boardDAO.alreadyWriteSubArticleInThisGrade(subArticleVO);
		System.out.println("출력안되냐:"+alreadyWriteSubArticleInThisGrade);
		if(alreadyWriteSubArticleInThisGrade==0){
			flag=true;
			boardDAO.insertSubArticle(subArticleVO);
		}
		return flag;
	}
	@Override
	public int updateSubArticle(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteSubArticle(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<SubArticleVO> selectListSubArticleByMainArticleNo(
			SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SubArticleVO> selectListSubArticleByIsConnect(
			SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SubArticleVO> selectListSubArticleBySubArticleGrade(
			SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return null;
	}
	//윤택
	@Override
	public List<MainArticleVO> searchByKeyWord(String keyword) {
		return utilService.searchByKeyword(keyword);
	}
	/**
	 *신고리스트를 받아오는 메서드
	 * @author 윤택
	 */
	@Override
	public ReportListVO mainArticleReportList(int pageNo) {
		// TODO Auto-generated method stub
		return utilService.mainArticleReportList(pageNo);
		
	}
	@Override
	/**
	 * 잇는글의 신고리스트를 받아오는 메서드
	 * @author 윤택
	 */
	public ReportListVO subArticleReportList(int pageNo) {
		// TODO Auto-generated method stub
		return utilService.subArticleReportList(pageNo);
	}
	@Override
	/**
	 * 관리자가 게시물신고를 신고처리하여
	 * 해당 게시물을 Block하는 메서드 
	 * @author 윤택
	 */
	public void articleBlock(MainArticleVO mavo, int reportNumber) {
		boardDAO.articleBlock(mavo);
		utilService.stagesOfProcess(reportNumber);
	}
	
	/**
	 * 관리자가 잇는글신고를 신고처리하여
	 * 해당 잇는글을 Block하는 메서드
	 * @author 윤택
	 */
	@Override
	public void subArticleBlock(int subArticleNumber, int articleNumber, int reportNumber) {
		// TODO Auto-generated method stub
		boardDAO.subArticleBlock(subArticleNumber);
		utilService.stagesOfProcess(reportNumber);
	}
	/**
	 * 관리자가 게시물신고를 반려처리하여 
	 * 신고 리스트에서 없애는 메서드
	 * @author 윤택
	 */
	@Override
	public void reportListDelete(ReportVO nvo) {
		utilService.deleteByNotify(nvo);
	}
	@Override
	public Map<String, Object> boardStatistics() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("notifyCount",utilService.notifyCount());
		map.put("boardCount",boardDAO.boardCount());
		return map;
	}
	@Override
	public List<MainArticleVO> articleSort(String sort) {
		return utilService.articleSort(sort);
	}
	
	@Override
	/**
	 * 게시물 신고 횟수를 증가 하고 신고횟수가 10이상이면
	 * 아티클을 delete해주는 메서드
	 */
	public void articleNotify(MainArticleVO mavo) {
		ReportVO nvo=utilService.articleNotify(mavo);
		if(nvo.getReportAmount()>=10){
			boardDAO.articleDelete(mavo);
		}
	}
	
	@Override
	/**
	 * main화면에 전체 Tag 리스트 불러옴
	 * @author JeSeong Lee
	 */
	public List<TagVO> getTagVOList() {
		return boardDAO.getTagVOList();
	}
	

	/**
	 * @author JeSeong Lee
	 * 마이페이지 찜 주제글 받기
	 * email로 리스트 받아서 MainArticleNo 받고
	 * 그것으로 해당 주제글들 조회해서
	 * MemberVO Grade정보도 넣어서 줌
	 */
	@Override
	public List<MainArticleVO> getPickedMainArticleByMemberEmailOrderByDate(
			MemberVO memberVO) {
		List<Integer> pickedMainArticleNoList = boardDAO.getPickedMainArticleNoByEmail(memberVO);
		ArrayList<MainArticleVO> pickedMainArticleVOList = new ArrayList<MainArticleVO>();
		for(int i = 0 ; i<pickedMainArticleNoList.size() ; i++){
			pickedMainArticleVOList.add(boardDAO.getMainArticleByMainArticleNoOrderByDate(pickedMainArticleNoList.get(i)));
		}
		String tagName = "";
		for(int j = 0 ; j<pickedMainArticleVOList.size() ; j++){
			List<TagBoardVO> tagBoardList = boardDAO.getMainArticleTagList(pickedMainArticleVOList.get(j).getMainArticleNo());
			for(int k = 0 ; k<tagBoardList.size() ; k++){
				if(k == tagBoardList.size()-1){
					tagName += "#" + tagBoardList.get(k).getTagName();
				}else{
					tagName += "#" +  tagBoardList.get(k).getTagName() + " ";
				}
				pickedMainArticleVOList.get(j).setTagName(tagName);
			}
			tagName = "";
		}
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int l = 0 ; l<pickedMainArticleVOList.size() ; l++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(pickedMainArticleVOList.get(l).getMemberVO())); 
			pickedMainArticleVOList.get(l).getMemberVO().setRankingVO(rankingVOList.get(l));
		}
		return pickedMainArticleVOList;
	}
	
	/**
	 * @author JeSeong Lee
	 * 해당 마이페이지 상단부 Ranking정보
	 * + 구독정보 추가함
	 * email로 받아와서 memberVO에 set
	 */
	@Override
	public MemberVO getMemberRankingByMemberEmail(MemberVO memberVO) {
		memberVO = boardDAO.getMemberNickNameByEmail(memberVO);
		RankingVO rankingVO = boardDAO.getMemberRankingByMemberEmail(memberVO);
		List<SubscriptionInfoVO> subscriptionInfoList
			= boardDAO.getSubscriptedInfoListByPublisherEmail(memberVO);
		memberVO.setRankingVO(rankingVO);
		memberVO.setSubscriptionInfoList(subscriptionInfoList);
		return memberVO;
	}
	
	
	/**
	 * @author JeSeong Lee
	 * 마이페이지 작성한 주제글 받기
	 * email로 리스트 받아서 MainArticleNo 받고
	 * 그것으로 해당 주제글들 조회해서
	 * MemberVO Grade정보도 넣어서 줌
	 */
	@Override
	public List<MainArticleVO> getWriteMainArticleByEmailOrderByDate(
			MemberVO memberVO) {
		List<Integer> writeMainArticleNoList = boardDAO.getWriteMainArticleNoByEmail(memberVO);
		ArrayList<MainArticleVO> writeMainArticleVOList = new ArrayList<MainArticleVO>();
		for(int i = 0 ; i<writeMainArticleNoList.size() ; i++){
			writeMainArticleVOList.add(boardDAO.getMainArticleByMainArticleNoOrderByDate(writeMainArticleNoList.get(i)));
		}
		String tagName = "";
		for(int j = 0 ; j<writeMainArticleVOList.size() ; j++){
			List<TagBoardVO> tagBoardList = boardDAO.getMainArticleTagList(writeMainArticleVOList.get(j).getMainArticleNo());
			for(int k = 0 ; k<tagBoardList.size() ; k++){
				if(k == tagBoardList.size()-1){
					tagName += "#" + tagBoardList.get(k).getTagName();
				}else{
					tagName += "#" +  tagBoardList.get(k).getTagName() + " ";
				}
				writeMainArticleVOList.get(j).setTagName(tagName);
			}
			tagName = "";
		}
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int l = 0 ; l<writeMainArticleVOList.size() ; l++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(writeMainArticleVOList.get(l).getMemberVO())); 
			writeMainArticleVOList.get(l).getMemberVO().setRankingVO(rankingVOList.get(l));
		}
		return writeMainArticleVOList;
	}
	
	/**
	 * @author JeSeong Lee
	 * 참여한 주제글 얻어오기
	 * email로 리스트 받아서 MainArticleNo 받고
	 * 2개 이상 잇는글이 선정 됐을 경우 방지하기 위해
	 * 중복된 No 제거(HashSet 활용)
	 * 그것으로 해당 주제글들 조회해서
	 * MemberVO Grade정보도 넣어서 줌
	 */
	@Override
	public List<MainArticleVO> getJoinMainArticleByEmailOrderByDate(
			MemberVO memberVO) {
		System.out.println("넘어온 이메일 : " + memberVO);
		ArrayList<Integer> joinMainArticleNoList = (ArrayList<Integer>) boardDAO.getJoinMainArticleNoByEmail(memberVO);
		System.out.println("joinMainArticleNoList : " + joinMainArticleNoList);
		HashSet hs = new HashSet(joinMainArticleNoList);
		ArrayList<Integer> nonDupJoinMainArticleNoList = new ArrayList<Integer>(hs);
		System.out.println("nonDupJoinMainArticleNoList : " + nonDupJoinMainArticleNoList);
		ArrayList<MainArticleVO> joinMainArticleVOList = new ArrayList<MainArticleVO>();
		for(int i = 0 ; i<nonDupJoinMainArticleNoList.size() ; i++){
			joinMainArticleVOList.add(boardDAO.getMainArticleByMainArticleNoOrderByDate(nonDupJoinMainArticleNoList.get(i)));
		}
		System.out.println("joinMainArticleVOList : " + joinMainArticleVOList);
		String tagName = "";
		for(int j = 0 ; j<joinMainArticleVOList.size() ; j++){
			List<TagBoardVO> tagBoardList = boardDAO.getMainArticleTagList(joinMainArticleVOList.get(j).getMainArticleNo());
			for(int k = 0 ; k<tagBoardList.size() ; k++){
				if(k == tagBoardList.size()-1){
					tagName += "#" + tagBoardList.get(k).getTagName();
				}else{
					tagName += "#" +  tagBoardList.get(k).getTagName() + " ";
				}
				joinMainArticleVOList.get(j).setTagName(tagName);
			}
			tagName = "";
		}
		System.out.println("* joinMainArticleVOList : " + joinMainArticleVOList);
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int l = 0 ; l<joinMainArticleVOList.size() ; l++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(joinMainArticleVOList.get(l).getMemberVO())); 
			joinMainArticleVOList.get(l).getMemberVO().setRankingVO(rankingVOList.get(l));
		}
		System.out.println("최종 : " + joinMainArticleVOList);
		return joinMainArticleVOList; 
	}
	/**
	 * @author JeSeong Lee
	 * 기본 랭킹 정보를 받아옴
	 * 이것과 해당자의 그레이드 비교해서
	 * 랭킹 이미지 불러옴 
	 */
	@Override
	public List<RankingVO> getRankingList() {
		return boardDAO.getRankingList();
	}
	
	
	
	/**
	 * 해당 글의 itja 수와, 요청한 아이디가 itja를 눌렀는지 여부를 판단해준다.
	 * 1. itjaMemberBean에 잇자 체크를 요청하고
	 * 2. Map을 통해 itja수 와 잇자를 한 것인지 취소한 것인지를 리턴한다.
	 * 3. 총 잇자수가 10을 넘는 순간 주제글의 업데이트 데이트가 null이 아닐 경우(10이 되었다 취소되는 경우 다시 업데이트 갱신되는 것을 방지)
	 * 4. 주제글의 업데이트 데이트를 sysdate로 수정해준다.
	 * @author junyoung
	 */
	@Override
	public HashMap<String, Object> pointSelectItjaState(ItjaMemberVO itjaMemberVO,SubArticleVO subArticleVO) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("itjaSuccess",itjaMemberBean.checkItja(itjaMemberVO));
		map.put("itjaCount",itjaMemberBean.itjaCount(itjaMemberVO));
		int totalCount = itjaMemberBean.itjaTotalCount(itjaMemberVO);
		map.put("itjaTotalCount",totalCount);
		if(totalCount>9){
			String defaultUpdateDate = boardDAO.selectOneMainArticleUpdateDate(itjaMemberVO.getMainArticleNo());
			if(defaultUpdateDate.equals("19700101000000")){
				boardDAO.updateDateForMainArticle(itjaMemberVO.getMainArticleNo());
				map.put("mainArticleUpdateDate",boardDAO.selectOneMainArticleUpdateDate(itjaMemberVO.getMainArticleNo()));
				boardDAO.moveToBest(itjaMemberVO.getMainArticleNo());
				map.put("moveToBest", 1);
				// Thread 적용 ?
				StoryLinker storyLinker = new StoryLinker(boardDAO,subArticleVO);
				storyLinker.start();
			}
		}
		return map;
	}
	
	@Override
	/**
	 * 신고한 회원들에게 포인트를 지급해주는 메서드
	 * @author 윤택
	 */
	public void memberPointUpdate(int reportNumber) {
		// TODO Auto-generated method stub
		utilService.memberPointUpdate(reportNumber);
	}
	/**
	 * 신고에 관한 서비스를 담당하는 메서드
	 * 게시물을 신고처리하고 
	 * @param miArticleVO
	 * @param subArticleVO
	 */
	public String articleReport(MainArticleVO mainArticleVO , SubArticleVO subArticleVO , MemberVO memberVO){
		return utilService.articleReport(mainArticleVO, subArticleVO, memberVO);
	
	}
	
	/**
	 * 작성한 태그 리스트를 불러옴 by email
	 * 마이페이지에서 작성한 태그수 확인용
	 * @author Je Seong Lee
	 */
	@Override
	public List<TagBoardVO> writeTagListbyEmail(MemberVO memberVO) {
		return boardDAO.writeTagListbyEmail(memberVO);
	}
	
	/**
	 * 작성한 태그중 가장 많이 사용한 Tag를 불러옴
	 * 새로가입한 사람 마이페이지 오류를 수정함(2015.12.10)
	 * @author JeSeong Lee
	 */
	@Override
	public TagBoardVO getMostWriteTagByEmail(MemberVO memberVO) {
		ArrayList<TagBoardVO> mostWriteTagByEmailList
		= (ArrayList<TagBoardVO>) boardDAO.getMostWriteTagByEmail(memberVO);
		TagBoardVO tagBoardVO = null;
		if(mostWriteTagByEmailList.size()!=0){
			tagBoardVO = mostWriteTagByEmailList.get(0);
		}
		return tagBoardVO;
	}

	/**2015-12-08 대협추가
	 * 글 등록시 배경이미지를 등록하는 메소드
	 * @author daehyeop
	 */
	@Override
	public void insertMainArticleImg(int articleNo, String imgName){
		boardDAO.insertMainArticleImg(articleNo, imgName);
	}
	
	/**2015-12-08 대협추가
	 * 프로필이미지를 등록하는 메소드
	 * @author daehyeop
	 */
	@Override
	public void insertProfileImg(String memberEmail, String imgName){
		boardDAO.insertProfileImg(memberEmail, imgName);
	}
	/**2015-12-08 대협추가
	 * 주제글 배경이미지를 불러오는 메소드
	 * @author daehyeop
	 */
	@Override
	public MainArticleImgVO selectMainArticleImg(int articleNo){
		MainArticleImgVO mainArticleImgVO = boardDAO.selectMainArticleImg(articleNo);
		return mainArticleImgVO;
	}
	@Override
	public List<MainArticleVO> SearchOnTopMenu(String selector, String keyword) {
		// TODO Auto-generated method stub
		return utilService.SearchOnTopMenu(selector,keyword);
	}
	
	
	/**
	 * email주소로 가입나이 받아오기
	 * @author Je Seong Lee
	 */
	@Override
	public int getJoinAgeByEmail(MemberVO memberVO) {
		return boardDAO.getJoinAgeByEmail(memberVO);
	}
	
	/**
	 * 게시자 email로 나를 구독하는 리스트 받기
	 * 구독리스트에서 구독자 이메일로 닉네임 받기
	 * @author JeSeong Lee
	 */
	@Override
	public List<MemberVO> getSubscriptedInfoListByPublisherEmail(
			MemberVO memberVO) {
		List<SubscriptionInfoVO> SubscriptedInfoList = boardDAO.getSubscriptedInfoListByPublisherEmail(memberVO);
		ArrayList<MemberVO> subscriptedMemberList = new ArrayList<MemberVO>();
		for(int i = 0 ; i<SubscriptedInfoList.size() ; i++){
			memberVO = new MemberVO();
			memberVO.setMemberEmail(SubscriptedInfoList.get(i).getSubscriber());
			subscriptedMemberList.add(boardDAO.getMemberNickNameByEmail(memberVO));
		}
		return subscriptedMemberList;
	}
	
	/**
	 * 구독자 email로 내가 구독하는 리스트 받기
	 * 구독리스트에서 구독자 이메일로 닉네임 받기
	 * @author JeSeong Lee
	 */
	@Override
	public List<MemberVO> getSubscriptingInfoListBySubscriberEmail(
			MemberVO memberVO) {
		List<SubscriptionInfoVO> SubscriptingInfoList = boardDAO.getSubscriptingInfoListBySubscriberEmail(memberVO);
		ArrayList<MemberVO> subscriptingMemberList = new ArrayList<MemberVO>();
		for(int i = 0 ; i<SubscriptingInfoList.size() ; i++){
			memberVO = new MemberVO();
			memberVO.setMemberEmail(SubscriptingInfoList.get(i).getPublisher());
			subscriptingMemberList.add(boardDAO.getMemberNickNameByEmail(memberVO));
		}
		return subscriptingMemberList;
	}
	
}
