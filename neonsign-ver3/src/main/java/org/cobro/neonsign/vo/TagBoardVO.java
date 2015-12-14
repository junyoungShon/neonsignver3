package org.cobro.neonsign.vo;

public class TagBoardVO {
	private String tagName;
	private int mainArticleNo;
	private int useTagCount;  // 2015.12.09에 추가 resultMap 도 변경함
	public TagBoardVO() {
		super();
	}
	public TagBoardVO(String tagName, int mainArticleNo, int useTagCount) {
		super();
		this.tagName = tagName;
		this.mainArticleNo = mainArticleNo;
		this.useTagCount = useTagCount;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getMainArticleNo() {
		return mainArticleNo;
	}
	public void setMainArticleNo(int mainArticleNo) {
		this.mainArticleNo = mainArticleNo;
	}
	public int getUseTagCount() {
		return useTagCount;
	}
	public void setUseTagCount(int useTagCount) {
		this.useTagCount = useTagCount;
	}
	@Override
	public String toString() {
		return "TagBoardVO [tagName=" + tagName + ", mainArticleNo="
				+ mainArticleNo + ", useTagCount=" + useTagCount + "]";
	}
	
}
