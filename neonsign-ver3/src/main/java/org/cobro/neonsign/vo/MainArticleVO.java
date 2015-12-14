package org.cobro.neonsign.vo;

import java.util.List;

public class MainArticleVO {
	private int mainArticleNo;
	private String memberEmail;
	private String mainArticleTitle;
	private String mainArticleContent;
	private int mainArticleHIt;
	private int mainArticleLike;
	private int mainArticleTotalLike;
	private String mainArticleDate;
	private String mainArticleUpdateDate;
	private String mainArticleComplete;
	private int block;
	// MainArticleVO는 MemberVO와 SubArticleVO를 가진다.
	private MemberVO memberVO;
	private List<SubArticleVO> subArticleList;
	private List<TagBoardVO> tagBoardVOList;;
	private String tagName;
	//2015-12-08 대협추가
	private MainArticleImgVO mainArticleImgVO;

	public MainArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MainArticleVO(int mainArticleNo, String memberEmail,
			String mainArticleTitle, String mainArticleContent,
			int mainArticleHIt, int mainArticleLike, int mainArticleTotalLike,
			String mainArticleDate, String mainArticleUpdateDate,
			String mainArticleComplete, int block, MemberVO memberVO,
			List<SubArticleVO> subArticleList, List<TagBoardVO> tagBoardVOList,
			String tagName, MainArticleImgVO mainArticleImgVO) {
		super();
		this.mainArticleNo = mainArticleNo;
		this.memberEmail = memberEmail;
		this.mainArticleTitle = mainArticleTitle;
		this.mainArticleContent = mainArticleContent;
		this.mainArticleHIt = mainArticleHIt;
		this.mainArticleLike = mainArticleLike;
		this.mainArticleTotalLike = mainArticleTotalLike;
		this.mainArticleDate = mainArticleDate;
		this.mainArticleUpdateDate = mainArticleUpdateDate;
		this.mainArticleComplete = mainArticleComplete;
		this.block = block;
		this.memberVO = memberVO;
		this.subArticleList = subArticleList;
		this.tagBoardVOList = tagBoardVOList;
		this.tagName = tagName;
		this.mainArticleImgVO = mainArticleImgVO;
	}

	public int getMainArticleNo() {
		return mainArticleNo;
	}

	public void setMainArticleNo(int mainArticleNo) {
		this.mainArticleNo = mainArticleNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMainArticleTitle() {
		return mainArticleTitle;
	}

	public void setMainArticleTitle(String mainArticleTitle) {
		this.mainArticleTitle = mainArticleTitle;
	}

	public String getMainArticleContent() {
		return mainArticleContent;
	}

	public void setMainArticleContent(String mainArticleContent) {
		this.mainArticleContent = mainArticleContent;
	}

	public int getMainArticleHIt() {
		return mainArticleHIt;
	}

	public void setMainArticleHIt(int mainArticleHIt) {
		this.mainArticleHIt = mainArticleHIt;
	}

	public int getMainArticleLike() {
		return mainArticleLike;
	}

	public void setMainArticleLike(int mainArticleLike) {
		this.mainArticleLike = mainArticleLike;
	}

	public int getMainArticleTotalLike() {
		return mainArticleTotalLike;
	}

	public void setMainArticleTotalLike(int mainArticleTotalLike) {
		this.mainArticleTotalLike = mainArticleTotalLike;
	}

	public String getMainArticleDate() {
		return mainArticleDate;
	}

	public void setMainArticleDate(String mainArticleDate) {
		this.mainArticleDate = mainArticleDate;
	}

	public String getMainArticleUpdateDate() {
		return mainArticleUpdateDate;
	}

	public void setMainArticleUpdateDate(String mainArticleUpdateDate) {
		this.mainArticleUpdateDate = mainArticleUpdateDate;
	}

	public String getMainArticleComplete() {
		return mainArticleComplete;
	}

	public void setMainArticleComplete(String mainArticleComplete) {
		this.mainArticleComplete = mainArticleComplete;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public List<SubArticleVO> getSubArticleList() {
		return subArticleList;
	}

	public void setSubArticleList(List<SubArticleVO> subArticleList) {
		this.subArticleList = subArticleList;
	}

	public List<TagBoardVO> getTagBoardVOList() {
		return tagBoardVOList;
	}

	public void setTagBoardVOList(List<TagBoardVO> tagBoardVOList) {
		this.tagBoardVOList = tagBoardVOList;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public MainArticleImgVO getMainArticleImgVO() {
		return mainArticleImgVO;
	}

	public void setMainArticleImgVO(MainArticleImgVO mainArticleImgVO) {
		this.mainArticleImgVO = mainArticleImgVO;
	}

	@Override
	public String toString() {
		return "MainArticleVO [mainArticleNo=" + mainArticleNo
				+ ", memberEmail=" + memberEmail + ", mainArticleTitle="
				+ mainArticleTitle + ", mainArticleContent="
				+ mainArticleContent + ", mainArticleHIt=" + mainArticleHIt
				+ ", mainArticleLike=" + mainArticleLike
				+ ", mainArticleTotalLike=" + mainArticleTotalLike
				+ ", mainArticleDate=" + mainArticleDate
				+ ", mainArticleUpdateDate=" + mainArticleUpdateDate
				+ ", mainArticleComplete=" + mainArticleComplete + ", block="
				+ block + ", memberVO=" + memberVO + ", subArticleList="
				+ subArticleList + ", tagBoardVOList=" + tagBoardVOList
				+ ", tagName=" + tagName + ", mainArticleImgVO="
				+ mainArticleImgVO + "]";
	}
}
