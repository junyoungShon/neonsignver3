package org.cobro.neonsign.vo;

public class SearchVO {
	private String keyword;
	private int searchCount;
	public SearchVO() {
		super();
	}
	public SearchVO(String keyword, int searchCount) {
		super();
		this.keyword = keyword;
		this.searchCount = searchCount;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
	@Override
	public String toString() {
		return "SearchVO [keyword=" + keyword + ", searchCount=" + searchCount
				+ "]";
	}
	
}
