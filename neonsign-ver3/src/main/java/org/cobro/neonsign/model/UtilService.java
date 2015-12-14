package org.cobro.neonsign.model;

import java.util.HashMap;
import java.util.List;

import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.ReportListVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.SubArticleVO;

public interface UtilService {
	public List<MainArticleVO> searchByKeyword(String tag);


	public void deleteByNotify(ReportVO notifyVO);

	public int notifyCount();

	public List<MainArticleVO> articleSort(String sort);

	public ReportVO articleNotify(MainArticleVO mainArticleVO);

	public ReportListVO mainArticleReportList(int pageNo);

	public ReportListVO subArticleReportList(int pageNo);


	public void memberPointUpdate(int reportNumber);


	public void stagesOfProcess(int reportNumber);


	public String articleReport(MainArticleVO mainArticleVO,
			SubArticleVO subArticleVO, MemberVO memberVO);


	int articleBlock(SubArticleVO subArticleVO);


	public List<MainArticleVO> SearchOnTopMenu(String selector, String keyword);


	public void saveSearch(String string);


	public List<HashMap<String, String>> selectReport();
}
