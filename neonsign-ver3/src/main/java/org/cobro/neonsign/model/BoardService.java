package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MainArticleImgVO;
import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.RankingVO;
import org.cobro.neonsign.vo.ReportListVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.cobro.neonsign.vo.TagBoardVO;
import org.cobro.neonsign.vo.TagVO;

public interface BoardService {
	//main article 관련 메서드
	public int pointInsertMainArticle(MainArticleVO mainArticleVO, ArrayList<String> list,
			TagBoardVO tagBoardVO);
	public int updateMainArticle(MainArticleVO mainArticleVO);
	public void deleteMainArticle(MainArticleVO mainArticleVO);
	public MainArticleVO selectOneCompleteMainArticleByMainArticleNo(MainArticleVO mainArticleVO);
	public List<MainArticleVO> selectListNotCompleteMainArticleOrderByTotalLike();
	public Map<String, Object> selectOneNotCompleteMainArticleByMainArticleNo(MainArticleVO mainArticleVO);
	public List<MainArticleVO> selectListCompleteMainArticle(int pageNo,String orderBy, String getTagName);
	public List<MainArticleVO> selectListNotCompleteMainArticle(int pageNo,	String orderBy, String getTagName);
	public List<MainArticleVO> getBestMainArticleVOListOrderByDate();
	public List<TagVO> getTagVOList();
	public List<TagVO> selectListTagNameOrderBySearchCount();
	
	//sub article 관련 메서드
	public boolean pointInsertSubArticle(SubArticleVO subArticleVO);
	public int updateSubArticle(SubArticleVO subArticleVO);
	public void deleteSubArticle(SubArticleVO subArticleVO);
	public List<SubArticleVO> selectListSubArticleByMainArticleNo(SubArticleVO subArticleVO);
	public List<SubArticleVO> selectListSubArticleByIsConnect(SubArticleVO subArticleVO);
	public List<SubArticleVO> selectListSubArticleBySubArticleGrade(SubArticleVO subArticleVO);
	public HashMap<String, Object> storyLinking(SubArticleVO subArticleVO);
	//윤택

	public List<MainArticleVO> searchByKeyWord(String keyword);
	public Map<String, Object> boardStatistics();
	public List<MainArticleVO> articleSort(String sort);
	public void articleNotify(MainArticleVO mainArticleVO);
	ReportListVO mainArticleReportList(int pageNumber);
	ReportListVO subArticleReportList(int pageNumber);
	HashMap<String, Object> pointSelectItjaState(ItjaMemberVO itjaMemberVO, SubArticleVO subArticleVO);
	void articleBlock(MainArticleVO mavo, int reportNumber);
	void reportListDelete(ReportVO nvo);
	public void subArticleBlock(int subArticleNumber, int articleNumber, int reportNumber);
	public void memberPointUpdate(int reportNumber);
	
	// MyPage 요소들
	public MemberVO getMemberRankingByMemberEmail(MemberVO memberVO);
	public List<RankingVO> getRankingList();
	public List<MainArticleVO> getPickedMainArticleByMemberEmailOrderByDate(
			MemberVO memberVO);
	public List<MainArticleVO> getWriteMainArticleByEmailOrderByDate(
			MemberVO memberVO);
	public List<MainArticleVO> getJoinMainArticleByEmailOrderByDate(
			MemberVO memberVO);
	public String articleReport(MainArticleVO mainArticleVO,
			SubArticleVO subArticleVO, MemberVO memberVO);
	public List<TagBoardVO> writeTagListbyEmail(MemberVO memberVO);
	public TagBoardVO getMostWriteTagByEmail(MemberVO memberVO);
	
	//2015-12-08 대협추가
	//이미지추가 관련 메소드
	public void insertMainArticleImg(int articleNo, String imgName);
	public void insertProfileImg(String memberEmail, String imgName);
	//이미지 로드 관련 메소드
	public MainArticleImgVO selectMainArticleImg(int articleNo);
	public List<MainArticleVO> SearchOnTopMenu(String selector, String keyword);
	
	// 가입 나이 조회
	public int getJoinAgeByEmail(MemberVO memberVO);
	// 구독 관련 메서드
	// 게시자 email로 나를 구독하는 리스트 닉네임 받기
	public List<MemberVO> getSubscriptedInfoListByPublisherEmail(
			MemberVO memberVO);
	// 구독자 email로 나를 구독하는 리스트 닉네임 받기
	public List<MemberVO> getSubscriptingInfoListBySubscriberEmail(MemberVO memberVO);
}
