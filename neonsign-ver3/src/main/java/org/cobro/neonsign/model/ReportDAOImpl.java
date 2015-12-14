package org.cobro.neonsign.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class ReportDAOImpl implements ReportDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ReportVO> mainArticleReportList(int pageNo) {
		List<ReportVO> list=null;
		try{
		 list=sqlSessionTemplate.selectList("report.mainArticleReportList",pageNo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ReportVO> subArticleReportList(int pageNo) {
		List<ReportVO> list=sqlSessionTemplate.selectList("report.subArticleReportList",pageNo);
		System.out.println(list);
		return list;
	}

	@Override
	public void deleteByNotify(ReportVO notifyVO) {
		// TODO Auto-generated method stub
		try{
		sqlSessionTemplate.delete("report.deleteByReport",notifyVO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 현재 Report의 reportAount를 받아오는 메서드
	 */
	public int reportCount(int reportNo){
		// TODO Auto-generated method stub
		ReportVO rvo=sqlSessionTemplate.selectOne("report.reportCount",reportNo);
		int reportAount=rvo.getReportAmount();
		return  reportAount;
	}

	@Override
	public void articleNotifyCount(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReportVO notifyByNo(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 신고한 회원들의 리스트를 받아오는 메서드
	 * @author 윤택
	 */
	public List<String> reporterNames(int reportNumber) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("report.reporterNames",reportNumber);
	}

	@Override
	/**
	 * 회원의 포인트를 10 지급해주는 메서드 
	 * @author 윤택
	 */
	public void memberPointUpdate(String email) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("report.memberPointUpdate",email);

	}

	@Override
	/**
	 * 신고 리스트의 신고자들을 삭제
	 * @author 윤택
	 */
	public void deleteByReporter(ReportVO notifyVO) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete("report.deleteByReporter",notifyVO);
	}

	@Override
	/**
	 * stagesOfProcess 를 신고처리로 update해주는 메서드
	 * @author 윤택
	 */
	public void stagesOfProcess(int reportNumber) {
		// TODO Auto-generated method stub
		 sqlSessionTemplate.update("report.stagesOfProcess",reportNumber);
	}
	/**
	 * 주제글을 신고하는 메서드
	 * @author 윤택
	 */
	@Override
	public void mainArticleReport(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		System.out.println("신고 할려는 주제글 넘버 : "+mainArticleVO.getMainArticleNo());
		sqlSessionTemplate.insert("report.mainArticleReport",mainArticleVO);
		//int reportNo=sqlSessionTemplate.selectOne("report.nowReportNumber");
		//System.out.println("신고 번호 : "+reportNo);
		
	}
	/**
	 * 잇는글을 신고하는 메서드
	 * @author 윤택
	 */
	@Override
	public void subArticleReport(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("report.subArticleReport",subArticleVO);
		//int reportNo=sqlSessionTemplate.selectOne("report.nowReportNumber");
		
	}
	/**
	 * 현재 ReportNo를 받아오는 메서드
	 * @author 윤택
	 */
	@Override
	public int nowReportNumber() {
		// TODO Auto-generated method stub
		ReportVO rvo=sqlSessionTemplate.selectOne("report.nowReportNumber");
		int reportNo=rvo.getReportNo();
		return reportNo;
	}
	/**
	 * 신고자를 생성하는 메서드
	 * @author 윤택
	 */
	@Override
	public void insertReporter(MemberVO memberVO, int reportNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("memberEmail", memberVO.getMemberEmail()); map.put("reportNo",reportNo);
		sqlSessionTemplate.insert("report.insertReporter",map);
	}
	/**
	 * 잇는글 신고 횟수를 업데이트 하는 메서드
	 * @author 윤택
	 */
	@Override
	public int updateSubArticleReport(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("report.updateSubArticleReport",subArticleVO);
	}
	/**
	 * 주제글 신고 횟수를 업데이트 하는 메서드
	 * @author 윤택
	 */
	@Override
	public int updateMainArticleReport(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("report.updateMainArticleReport",mainArticleVO);
	}
	/**
	 * 주제글을 Block하는 메서드
	 * @author 윤택
	 */
	@Override
	public int mainArticleBlock(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("report.mainArticleBlock",subArticleVO);
	}
	/**
	 * 잇는글을 Block하는 메서드
	 * @author 윤택
	 */
	@Override
	public int subArticleBlock(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("report.subArticleBlock",subArticleVO);
	}
	/**
	 * 주제글의 총 신고수를 찾는 메서드
	 */
	@Override
	public int allMianReports() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("report.allMianReports");
	}

	@Override
	/**
	 * 잇는글의 총 신고수를 찾는 메서드
	 */
	public int allSubReports() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("report.allSubReports");
	}

	/**
	 * 회원이 신고한 리포트 넘버들을 받아오는 메서드
	 */
	@Override
	public List<Integer> selectReporterReportNo(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("report.selectReporterReportNo",memberVO);
	}
	
	@Override
	public ReportVO findReportByReportNoAndMainArticleNo(Integer reportNo,
			MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		HashMap<String,Integer>map=new HashMap<String, Integer>();
		map.put("reportNo", reportNo); map.put("mainArticleNo",mainArticleVO.getMainArticleNo());
		return sqlSessionTemplate.selectOne("report.findReportByReportNoAndMainArticleNo",map);
	}


}
 