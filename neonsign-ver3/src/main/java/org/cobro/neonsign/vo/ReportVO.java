package org.cobro.neonsign.vo;

import java.util.List;

public class ReportVO {
	private int reportNo;
	private String reportDate;
	private int mainArticleNo;
	private int subArticleNo;
	private int reportAmount;
	private String stagesOfProcess;
	private List<MainArticleVO> mainArticleVO;
	public ReportVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportVO(int reportNo, String reportDate, int mainArticleNo,
			int subArticleNo, int reportAmount, String stagesOfProcess,
			List<MainArticleVO> mainArticleVO) {
		super();
		this.reportNo = reportNo;
		this.reportDate = reportDate;
		this.mainArticleNo = mainArticleNo;
		this.subArticleNo = subArticleNo;
		this.reportAmount = reportAmount;
		this.stagesOfProcess = stagesOfProcess;
		this.mainArticleVO = mainArticleVO;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public int getMainArticleNo() {
		return mainArticleNo;
	}
	public void setMainArticleNo(int mainArticleNo) {
		this.mainArticleNo = mainArticleNo;
	}
	public int getSubArticleNo() {
		return subArticleNo;
	}
	public void setSubArticleNo(int subArticleNo) {
		this.subArticleNo = subArticleNo;
	}
	public int getReportAmount() {
		return reportAmount;
	}
	public void setReportAmount(int reportAmount) {
		this.reportAmount = reportAmount;
	}
	public String getStagesOfProcess() {
		return stagesOfProcess;
	}
	public void setStagesOfProcess(String stagesOfProcess) {
		this.stagesOfProcess = stagesOfProcess;
	}
	public List<MainArticleVO> getMainArticleVO() {
		return mainArticleVO;
	}
	public void setMainArticleVO(List<MainArticleVO> mainArticleVO) {
		this.mainArticleVO = mainArticleVO;
	}
	@Override
	public String toString() {
		return "ReportVO [reportNo=" + reportNo + ", reportDate=" + reportDate
				+ ", mainArticleNo=" + mainArticleNo + ", subArticleNo="
				+ subArticleNo + ", reportAmount=" + reportAmount
				+ ", stagesOfProcess=" + stagesOfProcess + ", mainArticleVO="
				+ mainArticleVO + "]";
	}
	
	
}
