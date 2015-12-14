package org.cobro.neonsign.vo;

public class MainArticleImgVO {
	private int mainArticleNo;
	private String mainArticleImgName;

	public MainArticleImgVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MainArticleImgVO(int mainArticleNo, String mainArticleImgName) {
		super();
		this.mainArticleNo = mainArticleNo;
		this.mainArticleImgName = mainArticleImgName;
	}

	public int getMainArticleNo() {
		return mainArticleNo;
	}

	public void setMainArticleNo(int mainArticleNo) {
		this.mainArticleNo = mainArticleNo;
	}

	public String getMainArticleImgName() {
		return mainArticleImgName;
	}

	public void setMainArticleImgName(String mainArticleImgName) {
		this.mainArticleImgName = mainArticleImgName;
	}

	@Override
	public String toString() {
		return "MainArticleImgVO [mainArticleNo=" + mainArticleNo
				+ ", mainArticleImgName=" + mainArticleImgName + "]";
	}
}
