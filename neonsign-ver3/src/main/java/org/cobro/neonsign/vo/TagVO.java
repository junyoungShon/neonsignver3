package org.cobro.neonsign.vo;

public class TagVO {
	private String tagName;
	private int searchCount;
	public TagVO() {
		super();
	}
	public TagVO(String tagName, int searchCount) {
		super();
		this.tagName = tagName;
		this.searchCount = searchCount;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
	@Override
	public String toString() {
		return "TagVO [tagName=" + tagName + ", searchCount=" + searchCount
				+ "]";
	}
}
