package org.cobro.neonsign.model;

import java.util.List;

import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.SubArticleVO;

public interface ReportDAO {

	public void deleteByNotify(ReportVO notifyVO);

	public void articleNotifyCount(MainArticleVO mainArticleVO);

	public ReportVO notifyByNo(MainArticleVO mainArticleVO);

	List<ReportVO> mainArticleReportList(int pageNo);

	List<ReportVO> subArticleReportList(int pageNo);

	public List<String> reporterNames(int reportNumber);

	public void memberPointUpdate(String email);

	public void deleteByReporter(ReportVO notifyVO);

	public void stagesOfProcess(int reportNumber);

	public int updateMainArticleReport(MainArticleVO mainArticleVO);

	public void mainArticleReport(MainArticleVO mainArticleVO);

	public int updateSubArticleReport(SubArticleVO subArticleVO);

	public int nowReportNumber();

	public void insertReporter(MemberVO memberVO, int reportNo);

	public int reportCount(int reportNo);

	public int mainArticleBlock(SubArticleVO subArticleVO);

	public int subArticleBlock(SubArticleVO subArticleVO);

	void subArticleReport(SubArticleVO subArticleVO);

	public int allSubReports();

	public int allMianReports();

	public List<Integer> selectReporterReportNo(MemberVO memberVO);

	public ReportVO findReportByReportNoAndMainArticleNo(Integer integer,
			MainArticleVO mainArticleVO);
}
