package org.cobro.neonsign.model;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MainArticleImgVO;
import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.RankingVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;
import org.cobro.neonsign.vo.TagBoardVO;
import org.cobro.neonsign.vo.TagVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 새로운 주제글을 인서트한다.
	 * @author junyoung
	 */
	@Override
	public void insertMainArticle(MainArticleVO mainArticleVO) {
		System.out.println(mainArticleVO);
		sqlSessionTemplate.insert("board.insertMainArticle", mainArticleVO);
	}
	/**
	 * 위 메서드와 동시에 실행된다. 태그 보드 게시판에 게시글 번호와 태그를 저장
	 * @author junyoung
	 */
	@Override
	public void insertTagBoardVO(TagBoardVO tagBoardVO) {
		sqlSessionTemplate.insert("board.insertTagBoardVO", tagBoardVO);
	}
	/**글 인서트를 위해 모달창을 클릭할 때 사용자가 태그를 선택해야 하는데
	 * 이때 태그를 인기순으로 출력해 주기 위한 메서드
	 * @author junyoung
	 */
	@Override
	public List<TagVO> selectListTagNameOrderBySearchCount(){
		return sqlSessionTemplate.selectList("board.selectListTagNameOrderBySearchCount");
	}
	@Override
	public void updateMainArticle(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		
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
		int mainArticleNo = mainArticleVO.getMainArticleNo();
		MainArticleVO resultMainArticleVO = sqlSessionTemplate.selectOne(
				"completeMainArticleByMainArticleNo", mainArticleNo);
		return resultMainArticleVO;
	}

	/**
	 * 완결된 주제글을 게시일순 리스트로 받는 메소드
	 * 
	 * @author daehyeop
	 */
	@Override
	public List<MainArticleVO> selectListCompleteMainArticleOrderByDate(int pageNo) {
		List<MainArticleVO> mainArticleList = sqlSessionTemplate
				.selectList("board.completeMainArticleOrderByDate", pageNo);
		return mainArticleList;
	}
	/**
	 * 완결된 주제글을 총 잇자순 리스트로 받는 메소드
	 * @author daehyeop
	 */
	@Override
	public List<MainArticleVO> selectListCompleteMainArticleOrderByTotalLike(int pageNo) {
		List<MainArticleVO> mainArticleList = sqlSessionTemplate
				.selectList("board.completeMainArticleOrderByTotalLike", pageNo);
		return mainArticleList;
	}
	/**
	 * 완결된 주제글을 태그순 리스트로 받는 메소드
	 * @author daehyeop
	 */
	@Override
	public List<MainArticleVO> selectListCompleteMainArticleOrderByTag(int pageNo, String getTagName){
		HashMap<String, String> map = new HashMap<String, String>();
		String strPageNo = String.valueOf(pageNo);
		map.put("pageNo", strPageNo);
		map.put("tagName", getTagName);
		List<MainArticleVO> mainArticleList = sqlSessionTemplate.selectList("board.selectListCompleteMainArticleOrderByTag", map);
		return mainArticleList;
	}
	
	/**
	 * 새로운주제글 글쓴순
	 * @author daehyeop
	 */
	@Override
	public List<MainArticleVO> selectListNotCompleteMainArticleOrderByDate(
			int pageNo) {
		System.out.println("DAO selectListNotCompleteMainArticleOrderByDate pageNo : " + pageNo);
		List<MainArticleVO> list = sqlSessionTemplate.selectList(
				"board.selectListNotCompleteMainArticleOrderByDate", pageNo);
		System.out.println("DAO selectListNotCompleteMainArticleOrderByDate size : " + list.size());
		return list;
	}
	/**
	 * 새로운주제글 태그순
	 * @author daehyeop
	 */
	@Override
	public List<MainArticleVO> selectListNotCompleteMainArticleOrderByTag(int pageNo, String getTagName){
		HashMap<String,String> map = new HashMap<String,String>();
		System.out.println("dao selectListNotCompleteMainArticleOrderByTag getTagName : " + getTagName);
		String strPageNo = String.valueOf(pageNo);
		map.put("pageNo", strPageNo);
		map.put("tagName", getTagName);
		System.out.println("map : " + map);
		System.out.println("dao!! : " + sqlSessionTemplate.selectList("board.selectListNotCompleteMainArticleOrderByTag",map));
		return sqlSessionTemplate.selectList("board.selectListNotCompleteMainArticleOrderByTag",map);
	}

	@Override
	/**
	 * 해당 MainArticle 정보를 가져 오는 메서드
	 * 	해당 MainArticle 정보 안에는 해당 MainArticle의 SubArticle의 정보 ,
	 * 작성자의 정보가 담겨져 있고 SubArticle 정보 안에는 SubArticle의 작성자
	 * 정보가 있음
	 * @author 윤택
	 */
	public MainArticleVO selectOneNotCompleteMainArticleByMainArticleNo(
			MainArticleVO mainArticleVO) {
		MainArticleVO main=sqlSessionTemplate.selectOne("board.selectOneNotCompleteMainArticleByMainArticleNo",mainArticleVO);
		return main;
	}
	@Override
	public MainArticleVO selectOneNotCompleteMainArticleByMainArticleAndSubArticleNo(
			MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		System.out.println("MainArticle받아오기 실행");
				MainArticleVO main=null;
				try{
				main=sqlSessionTemplate.selectOne("board.selectOneNotCompleteMainArticleByMainArticleAndSubArticleNo",mainArticleVO);
				}catch(Exception e){
					e.printStackTrace();
				}
		return main;
		
	}
	/**
	 * 잇는 글을 삽입한다.
	 * insertSubArticle
	 * insert into sub_article (MAIN_ARTICLE_NO, SUB_ARTICLE_NO, MEMBER_EMAIL,SUB_ARTICLE_CONTENT,IS_CONNECT,SUB_ARTICLE_DATE,SUB_ARTICLE_GRADE) 
	values(#{mainArticleNo},sub_article_seq.nextval,#{memberEmail},#{subArticleContent}, 0 , sysdate,#{subArticleGrade});
	 * @author junyoung
	 */
	@Override
	public void insertSubArticle(SubArticleVO subArticleVO) {
		System.out.println("dao insertSubArticle : "+subArticleVO);
		sqlSessionTemplate.insert("board.insertSubArticle", subArticleVO);
	}

	@Override
	public void updateSubArticle(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		
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
	public void articleDelete(MainArticleVO mavo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("board.articleDelete",mavo);
	}

	@Override
	public Object boardCount() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Main best잇는글 최신순 조회
	 * @author JeSeong Lee
	 */
	@Override
	public List<MainArticleVO> getBestMainArticleVOListOrderByDate() {
		return sqlSessionTemplate.selectList("board.getBestMainArticleVOListOrderByDate");
	}
	
	/**
	 * Main화면 가운데에 전체 Tag 불러옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<TagVO> getTagVOList() {
		return sqlSessionTemplate.selectList("board.getTagVOList");
	}
	
	/**
	 * Email로 멤버의 랭킹 정보 조회
	 * @author JeSeong Lee 
	 */
	@Override
	public RankingVO getMemberRankingByMemberEmail(MemberVO memberVO) {
		return sqlSessionTemplate.selectOne("board.getMemberRankingByMemberEmail", memberVO);
	}
	
	/**
	 * Email로 멤버 닉네임, email 등 불러옴
	 * 닉네임 눌러서 마이페이지 넘어갈 때 사용
	 * @author JeSeong Lee 
	 */
	@Override
	public MemberVO getMemberNickNameByEmail(MemberVO memberVO) {
		return sqlSessionTemplate.selectOne("board.getMemberNickNameByEmail", memberVO);
	}
	
	/**
	 * 그냥 랭킹리스트 불러옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<RankingVO> getRankingList() {
		return sqlSessionTemplate.selectList("board.getRankingList");
	}
	
	/**
	 * Email로 참여한 MainArticle No 불러옴
	 * @author JeSeong Lee 
	 */
	@Override
	public List<Integer> getJoinMainArticleNoByEmail(
			MemberVO memberVO) {
		return sqlSessionTemplate.selectList("board.getJoinMainArticleNoByEmail", memberVO);
	}
	
	/**
	 * 불러온 MainArticle No로
	 * 주제글 정보 최신순 불러옴
	 * @author JeSeong Lee 
	 */
	@Override
	public MainArticleVO getMainArticleByMainArticleNoOrderByDate(
			Integer mainArticleNo) {
		return sqlSessionTemplate.selectOne("board.getMainArticleByMainArticleNoOrderByDate", mainArticleNo);
	}
	
	/**
	 * eMail로 찜한주제글 번호 불러옴
	 * @author JeSeong Lee 
	 */
	@Override
	public List<Integer> getPickedMainArticleNoByEmail(MemberVO memberVO) {
		return sqlSessionTemplate.selectList("board.getPickedMainArticleNoByEmail", memberVO);
	}
	
	/**
	 * eMail로 작성한주제글 번호 불러옴
	 * @author JeSeong Lee 
	 */
	@Override
	public List<Integer> getWriteMainArticleNoByEmail(MemberVO memberVO) {
		return sqlSessionTemplate.selectList("board.getWriteMainArticleNoByEmail", memberVO);
	}
	
	
	/**
	 * 주제글에대한 잇자 클릭과 회원의 관계 정보를 가진 테이블에 정보를  삽입한다. 
	 * @author junyoung
	 */
	@Override
	public void insertMainItjaMember(ItjaMemberVO itjaMemberVO) {
		sqlSessionTemplate.insert("board.insertMainItjaMember", itjaMemberVO);
	}
	/**
	 * 잇자 여부를 확인하여 반환해준다. 이미 잇자햇다면 1 아니라면 0
	 * @author junyoung
	 */
	@Override
	public int checkItja(ItjaMemberVO itjaMemberVO) {
		return sqlSessionTemplate.selectOne("board.checkItja", itjaMemberVO);
	}
	/**
	 * 잇는글에 대한 잇자 클릭과 회원의 관계정보를 테이블에  저장하다.
	 * @author junyoung
	 */
	@Override
	public void insertSubItjaMember(ItjaMemberVO itjaMemberVO) {
		sqlSessionTemplate.insert("board.insertSubItjaMember", itjaMemberVO);
	}
	/**
	 * 주제게시물의 잇자수를 올려준다.
	 * @author junyoung
	 */
	@Override
	public void updateMainPlusItjaHit(ItjaMemberVO itjaMemberVO) {
		sqlSessionTemplate.update("board.updateMainPlusItjaHit", itjaMemberVO);
		
	}
	/**
	 * 잇는글의 잇자수를 올려준다.
	 * @author junyoung
	 */
	@Override
	public void updateSubPlusItjaHit(ItjaMemberVO itjaMemberVO) {
		System.out.println(itjaMemberVO);
		sqlSessionTemplate.update("board.updateSubPlusItjaHit", itjaMemberVO);
		
	}
	/**
	 * 잇는글에 대한 잇자 클릭과 회원의 관계정보를 테이블에서 삭제한다.
	 * @author junyoung
	 */
	@Override
	public void deleteItja(ItjaMemberVO itjaMemberVO) {
		sqlSessionTemplate.delete("board.deleteItja",itjaMemberVO);
		
	}
	/**
	 * 주제게시물의 잇자수를 줄여준다.
	 * @author junyoung
	 */
	@Override
	public void updateMainMinusItjaHit(ItjaMemberVO itjaMemberVO) {
		System.out.println(itjaMemberVO);
		sqlSessionTemplate.update("board.updateMainMinusItjaHit", itjaMemberVO);
		
	}
	/**
	 * 잇는글의 잇자수를 줄여준다.
	 * @author junyoung
	 */
	@Override
	public void updateSubMinusItjaHit(ItjaMemberVO itjaMemberVO) {
		sqlSessionTemplate.update("board.updateSubMinusItjaHit", itjaMemberVO);
		
	}
	/**
	 * 주제글의 총 잇자수를 줄여줍니다.
	 * @author junyoung
	 */
	@Override
	public void updateMainMinusTotalItjaHit(ItjaMemberVO itjaMemberVO) {
		sqlSessionTemplate.update("board.updateMainMinusTotalItjaHit", itjaMemberVO);
	}
	/**
	 * 주제글의 총 잇자수를 늘려줍니다.
	 * @author junyoung
	 */
	@Override
	public void updateMainPlusTotalItjaHit(ItjaMemberVO itjaMemberVO) {
		sqlSessionTemplate.update("board.updateMainPlusTotalItjaHit", itjaMemberVO);
	}
	/**
	 * 메인 주제글 단독의 잇자수를 반환한다.
	 * @author junyoung
	 */
	@Override
	public int selectMainItjaCount(ItjaMemberVO itjaMemberVO) {
		return sqlSessionTemplate.selectOne("board.selectMainItjaCount", itjaMemberVO);
	}
	/**
	 * 잇는 주제글의 잇자수를 반환한다.
	 */
	@Override
	public int selectSubItjaCount(ItjaMemberVO itjaMemberVO) {
		return sqlSessionTemplate.selectOne("board.selectSubItjaCount", itjaMemberVO);
	}
	/**
	 * 이메일에 해당하는 회원이 잇자를 누른 게시물을 모두 반환한다.
	 */
	@Override
	public List<ItjaMemberVO> getItjaListByMemberEmail(String memberEmail) {
		return sqlSessionTemplate.selectList("board.getItjaListByMemberEmail",memberEmail);
	}
	@Override
	public void articleBlock(MainArticleVO mavo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("board.articleBlock",mavo);
	}
	/**
	 * 잇는글을 Block 처리하는 메서드
	 * @author 윤택
	 */
	@Override
	public void subArticleBlock(int subArticleNumber) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("board.subArticleBlock",subArticleNumber);
	}
	/**
	 * 해당 글의 총 잇자수를 받아온다.
	 * @author junyoung
	 */
	@Override
	public int selectItjaTotalCount(ItjaMemberVO itjaMemberVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("board.selectItjaTotalCount", itjaMemberVO);
	}
	/**
	 * 해당 MainArticle의 이어진 SubArticle을 받아오는 메서드
	 */
	@Override
	public List<SubArticleVO> likingSubArticleFindByMainArticleNo(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("board.likingSubArticleFindByMainArticleNo",subArticleVO);
	}
	@Override
	/**
	 * MainArticle의 잇는 글을 받아오는 메서드
	 * @param subArticleVO
	 * @return
	 */
	public List<SubArticleVO> selectListSubArticle(
			SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("board.selectListSubArticle",subArticleVO);
	}
	/**
	 * 현재 Grade를 받아오는 메서드
	 */
	@Override
	   public int selectSubArticleCurruntGrade(SubArticleVO subArticleVO) {
		int grade=0;
		System.out.println("selectSubArticleCurruntGrade 실행됨");
		System.out.println("aa3"+subArticleVO.getSubAtricleGrade());
			SubArticleVO gradeVO=sqlSessionTemplate.selectOne("board.selectSubArticleCurruntGrade",subArticleVO);
			if(gradeVO!=null){//만약 Grade가 null이라면 0을 할당해준다
				grade=gradeVO.getSubAtricleGrade();
			}
			System.out.println("selectSubArticleCurruntGrade : "+grade);
	      return grade;
	   }
	/**
	 * itja수가 0보다 작아질 경우 초기화
	 * @author junyoung
	 */
	@Override
	public void itjaCountDefault(ItjaMemberVO itjaMemberVO) {
		if(itjaMemberVO.getSubArticleNo()==0){
			sqlSessionTemplate.update("board.mainArticleItjaCountDefault", itjaMemberVO);
		}else{
			sqlSessionTemplate.update("board.subArticleItjaCountDefault", itjaMemberVO);
		}
	}
	/**
	 * 현재 진행중인 단계에 이미 사용자가 글을 썼는지를 반환한다.
	 * select count(*) from sub_article where member_email = #{memberEmail} and SUB_ARTICLE_GRADE =#{subArticleGrade}
	 * alreadyWriteSubArticleInThisGrade
	 * @author junyoung
	 */
	@Override
	public int alreadyWriteSubArticleInThisGrade(SubArticleVO subArticleVO) {
		System.out.println("dao alreadyWriteSubArticleInThisGrade : "+subArticleVO);
		System.out.println("aa5"+subArticleVO.getSubAtricleGrade());
		int result = sqlSessionTemplate.selectOne("board.alreadyWriteSubArticleInThisGrade",subArticleVO);
		System.out.println("aa6"+subArticleVO.getSubAtricleGrade());
		System.out.println("alreadyWriteSubArticleInThisGrade : "+result);
		return  result;
	}
	/**
	 * 메인 아티클의 현재 update_date를 출력해주는 메서드
	 * 기본 값은 19700101000000
	 * @author junyoung
	 */
	@Override
	public String selectOneMainArticleUpdateDate(int mainArticleNo) {
		return sqlSessionTemplate.selectOne("board.selectOneMainArticleUpdateDate",mainArticleNo);
	}
	/**
	 * 메인 아티클의 현재 update_date를 sysdate로 업데이트 해주는 메서드
	 * @author junyoung
	 */
	@Override
	public void updateDateForMainArticle(int mainArticleNo) {
		sqlSessionTemplate.update("board.updateDateForMainArticle", mainArticleNo);
	}
	/**
	 * 새로운 메인아티클의 토탈 잇자 수가 10이 되는 순간 best로 옮겨준다.
	 * @author junyoung
	 */
	@Override
	public void moveToBest(int mainArticleNo) {
		sqlSessionTemplate.update("board.moveToBest", mainArticleNo);
	}
	/**
	 * 가장 높은 잇자를 받은 서브 아티클을 불러온다.
	 * @author junyoung
	 */
	@Override
	public List<SubArticleVO> selectListHigherLikeSubArticle(
			SubArticleVO subArticleVO) {
		return sqlSessionTemplate.selectList("board.selectListHigherLikeSubArticle", subArticleVO);
	}
	/**
	 * 베스트 글을 완결글로 옮겨 줍니다.
	 */
	@Override
	public void updateBestToCompletArticle(int mainArticleNo) {
		sqlSessionTemplate.update("board.updateBestToCompletArticle", mainArticleNo);
	}
	/**
	 * 가장 많은 잇자 글이 continue일 경우 isConnect를 수정해줍니다.
	 */
	@Override
	public void updateIsConnect(SubArticleVO subArticleVO) {
		sqlSessionTemplate.update("board.updateIsConnect", subArticleVO);
	}
	
	/**
	 * MainArticleNo로 해당 글 태그 불러옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<TagBoardVO> getMainArticleTagList(int mainArticleNo) {
		return sqlSessionTemplate.selectList("board.getMainArticleTagList", mainArticleNo);
	}
	
	/**
	 * email로 마이페이지 Tag갯수 불러옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<TagBoardVO> writeTagListbyEmail(MemberVO memberVO) {
		return sqlSessionTemplate.selectList("board.writeTagListbyEmail", memberVO);
	}
	
	/**
	 * Most Tag명 받아옴
	 * @author JeSeong Lee
	 */
	@Override
	public List<TagBoardVO> getMostWriteTagByEmail(MemberVO memberVO) {
		return sqlSessionTemplate.selectList("board.getMostWriteTagByEmail", memberVO);
	}
	
	/**2015-12-08 대협추가
	 * 글 등록시 배경이미지를 등록하는 메소드
	 * @author daehyeop
	 */
	@Override
	public void insertMainArticleImg(int articleNo, String imgName){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("articleNo", String.valueOf(articleNo));
		map.put("imgName", imgName);
		sqlSessionTemplate.insert("board.insertMainArticleImg", map);
	}
	
	/**2015-12-08 대협추가
	 * 프로필이미지를 등록하는 메소드
	 * @author daehyeop
	 */
	@Override
	public void insertProfileImg(String memberEmail, String imgName){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memberEmail", memberEmail);
		map.put("imgName", imgName);
		sqlSessionTemplate.insert("board.insertProfileImg", map);
	}
	
	/**2015-12-08 대협추가
	 * 주제글 배경이미지를 불러오는 메소드
	 * @author daehyeop
	 */
	@Override
	public MainArticleImgVO selectMainArticleImg(int articleNo){
		MainArticleImgVO mainArticleImgVO = sqlSessionTemplate.selectOne("board.selectMainArticleImg", articleNo);
		return mainArticleImgVO;
	}
	/**
	 * 글번호로 작성자의 이메일을 얻어온다.
	 * @author junyoung
	 */
	@Override
	public String selectWriterEmailByArticleNO(ItjaMemberVO itjaMemberVO) {
		String result = "";
		if(itjaMemberVO.getSubArticleNo()==0){
			result = sqlSessionTemplate.selectOne("board.selectWriterEmailByMainArticleNO",itjaMemberVO);
		}else{
			result = sqlSessionTemplate.selectOne("board.selectWriterEmailBySubArticleNO",itjaMemberVO);
		}
		return result;
	}
	
	/**
	 * email주소로 가입나이 받아오기
	 * @author Je Seong Lee
	 */
	@Override
	public int getJoinAgeByEmail(MemberVO memberVO) {
		return sqlSessionTemplate.selectOne("board.getJoinAgeByEmail", memberVO);
	}
	
	/**
	 * 게시자 email로 내가 구독하는 리스트 받기
	 * @author JeSeong Lee
	 */
	@Override
	public List<SubscriptionInfoVO> getSubscriptedInfoListByPublisherEmail(
			MemberVO memberVO) {
		return sqlSessionTemplate.selectList("board.getSubscriptedInfoListByPublisherEmail", memberVO);
	}

	/**
	 * 게시자 email로 나를 구독하는 리스트 받기
	 * @author JeSeong Lee
	 */
	@Override
	public List<SubscriptionInfoVO> getSubscriptingInfoListBySubscriberEmail(
			MemberVO memberVO) {
		return sqlSessionTemplate.selectList("board.getSubscriptingInfoListBySubscriberEmail", memberVO);
	}
	@Override
	public MainArticleVO selectMainArticleTitleByMainArticleNo(int mainArticleNo) {
		return sqlSessionTemplate.selectOne("board.selectMainArticleTitleByMainArticleNo", mainArticleNo);
	}
	@Override
	public void appendToCompleteArticle(MainArticleVO mainArticleVO) {
		System.out.println(mainArticleVO);
		sqlSessionTemplate.update("board.appendToCompleteArticle",mainArticleVO);
	}
	
}
