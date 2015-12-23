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
	/**
	 * 주제글 신고 리스트를 받아옴
	 * @author 윤택
	 */
	public List<ReportVO> mainArticleReportList(int pageNo) {
		 return sqlSessionTemplate.selectList("report.mainArticleReportList",pageNo);
	}

	@Override
	/**
	 * 잇는글 신고 리스트를 받아옴
	 * @author 윤택
	 */
	public List<ReportVO> subArticleReportList(int pageNo) {
		List<ReportVO> list=sqlSessionTemplate.selectList("report.subArticleReportList",pageNo);
		//System.out.println(list);
		return list;
	}

	@Override
	/**
	 * 해당 신고를 신고리스트에서 delete
	 * @author 윤택
	 */
	public void deleteByNotify(ReportVO notifyVO) {
		sqlSessionTemplate.delete("report.deleteByReport",notifyVO);
	}
	
	@Override
	/**
	 * 신고 리스트의 신고자들을 삭제
	 * @author 윤택
	 */
	public void deleteByReporter(ReportVO notifyVO) {
		sqlSessionTemplate.delete("report.deleteByReporter",notifyVO);
	}

	@Override
	/**
	 * stagesOfProcess 를 신고처리로 update해주는 메서드
	 * @author 윤택
	 */
	public void stagesOfProcess(int reportNumber) {
		 sqlSessionTemplate.update("report.stagesOfProcess",reportNumber);
	
	}
	
	@Override
	/**
	 * 신고한 회원들의 리스트를 받아오는 메서드
	 * @author 윤택
	 */
	public List<String> reporterNames(int reportNumber) {
		return sqlSessionTemplate.selectList("report.reporterNames",reportNumber);
	}

	@Override
	/**
	 * 회원의 포인트를 10 지급해주는 메서드 
	 * @author 윤택
	 */
	public void memberPointUpdate(String email) {
		sqlSessionTemplate.update("report.memberPointUpdate",email);
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

	/**
	 * 주제글을 신고하는 메서드
	 * @author 윤택
	 */
	@Override
	public void mainArticleReport(MainArticleVO mainArticleVO) {
		sqlSessionTemplate.insert("report.mainArticleReport",mainArticleVO);
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
	/**
	 * 신고당한 회원의 신고수를 업데이트 해주는 메서드
	 * @윤택
	 */
	@Override
	public void memberReportAmountUpdate(MemberVO memberVO) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("report.memberReportAmountUpdate",memberVO);
	}
	/**
	 * 메인아티클의 리포트 넘버를 받아옴
	 * @윤택
	 */
	@Override
	public int nowMainArticleReportNumber(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("report.nowMainArticleReportNumber",mainArticleVO);
	}
	/**
	 * 서브아티클의 리포트 넘버를 받아옴
	 * @윤택
	 */
	@Override
	public int nowSubArticleReportNumber(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("report.nowSubArticleReportNumber",subArticleVO);
	}
	/**
	 * 회원의 신고 횟수를 받아옴
	 * @윤택
	 */
	@Override
	public int getMemeberReportAmount(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("report.getMemeberReportAmount",memberVO);
	}
	/**
	 * 회원을 블락
	 */
	@Override
	public void memberBlack(MemberVO memberVO) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("report.memberBlack",memberVO);
	}
	/**
	 * 서브아티클의 중복되는 신고자를 Select
	 */
	@Override
	public ReportVO findReportByReportNoAndSubArticleNo(Integer reportNO,
			SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		HashMap<String,Integer>map=new HashMap<String, Integer>();
		map.put("reportNo", reportNO); map.put("mainArticleNo",subArticleVO.getMainArticleNo());
		map.put("subArticleNo",subArticleVO.getSubArticleNo());
		return sqlSessionTemplate.selectOne("report.findReportByReportNoAndSubArticleNo",map);
	}
	/**
	 * 회원을 신고하고 성공하면 ok 실패하면 fail을 반환
	 */
	@Override
	public String memberReport(Map<String, String> map) {
		// TODO Auto-generated method stub
		String result="ok";
		try{
		sqlSessionTemplate.insert("report.memberReport",map);
		}catch(Exception e){
			result="fail";
		}
		return result;
	}


}
 