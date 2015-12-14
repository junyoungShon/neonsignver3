package org.cobro.neonsign.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PagingBean;
import org.cobro.neonsign.vo.ReportListVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.springframework.stereotype.Service;

@Service
public class UtilServiceImpl implements UtilService{
	@Resource
	private SearchDAO searchDAO;
	@Resource
	private ReportDAO reportDAO;
	@Override
	public List<MainArticleVO> searchByKeyword(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 반려한 신고글의 리스트를 삭제
	 * @author 윤택
	 */
	public void deleteByNotify(ReportVO notifyVO) {
		// TODO Auto-generated method stub
		reportDAO.deleteByReporter(notifyVO);
		reportDAO.deleteByNotify(notifyVO);
	}
	@Override
	public int notifyCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<MainArticleVO> articleSort(String sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReportVO articleNotify(MainArticleVO mainArticleVO) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 주제글의 신고리스트를 페이징하여 ReportListVO에
	 * pagingBean과 신고리스트로 주입하여 생성한다
	 * @param pageNo
	 * @return
	 */
	public ReportListVO mainArticleReportList(int pageNo) {
		// TODO Auto-generated method stub
		PagingBean pb=null;
		ArrayList<ReportVO> list=(ArrayList<ReportVO>)reportDAO.mainArticleReportList(pageNo);
		int totalReports=reportDAO.allMianReports();
		System.out.println("totalContenrs : "+totalReports);
		if(pageNo!=0){
		pb= new PagingBean(totalReports, pageNo);
		}else{
			pb= new PagingBean(totalReports);
		}
		ReportListVO lvo=new ReportListVO(list,pb);
		return lvo;
	}

	@Override
	/**
	 *잇는글의 신고리스트를 페이징하여 ReportListVO에
	 * pagingBean과 신고리스트로 주입하여 생성한다
	 * @param pageNo
	 * @return
	 */
	public ReportListVO subArticleReportList(int pageNo) {
		// TODO Auto-generated method stub
		PagingBean pb=null;
		ArrayList<ReportVO> list=(ArrayList<ReportVO>)reportDAO.subArticleReportList(pageNo);
		int totalReports=reportDAO.allSubReports();
		System.out.println("totalContenrs : "+totalReports);
		if(pageNo!=0){
		pb= new PagingBean(totalReports, pageNo);
		}else{
			pb= new PagingBean(totalReports);
		}
		ReportListVO lvo=new ReportListVO(list,pb);
		return lvo;
	}

	@Override
	/**
	 * 신고한 회원들의 포인트를 지급해주는 메서드
	 * @author 윤택
	 */
	public void memberPointUpdate(int reportNumber) {
		// TODO Auto-generated method stub
		List<String> list=reportDAO.reporterNames(reportNumber);
		for(int i=0; i<list.size();i++){
			reportDAO.memberPointUpdate(list.get(i));
		}
	}
	@Override
	/**
	 * 신고처리현황을 신고처리로
	 * update해주는 메서드
	 * @author 윤택
	 */
	public void stagesOfProcess (int reportNumber) {
		// TODO Auto-generated method stub
		reportDAO.stagesOfProcess(reportNumber);
	}
	@Override
	/**
	 * 주제글이나 잇는글을 Block
	 * @param mainArticleVO
	 * @return
	 */
	public int articleBlock(SubArticleVO subArticleVO) {
		// TODO Auto-generated method stub
		int result=0;
		if(subArticleVO.getSubArticleNo()==0){
			//주제글을 Block
			result=reportDAO.mainArticleBlock(subArticleVO);
		}else{
			//잇는글을 Block
			result=reportDAO.subArticleBlock(subArticleVO);
		}
		return result;
	}
	@Override
	/** 사용자가 신고를 했을떄 실행되는 메서드
	 *  신고 처리 순서
		 * --주제글 신고인지 잇는글 신고인지 걸러준다
		 * 1. 신고 업데이트 (신고 업데이트가 되지 않는다면 1-1.)
		 * 1-1. 신고 생성 
		 * 2. 현재 ReportNo를 받아옴
		 * 3. 신고자 생성
		 * --만약에 현재 신고 횟수가 10이 된다면 Block 하고 
		 * 	신고자들에게 포인트를 준다
	 */
	public String articleReport(MainArticleVO mainArticleVO,
			SubArticleVO subArticleVO, MemberVO memberVO) {
		String reporterCheck="ok";
		//신고한 회원의 신고한 리포트 넘버를 받아온다
		List<Integer> reporterReportNoList=reportDAO.selectReporterReportNo(memberVO);
		//신고자의 report넘버에 대응하는 MainArticleNo가 있으면 신고를 하지않고
		//reporterCheck에 fail을 할당한다
		for(int i=0; i<reporterReportNoList.size();i++){
			System.out.println("index : "+reporterReportNoList.get(i));
			ReportVO reportVO=reportDAO.findReportByReportNoAndMainArticleNo(reporterReportNoList.get(i),mainArticleVO);
			System.out.println("reportVO : "+reportVO);
			if(reportVO!=null){
				reporterCheck="fail";
				break;
			}
		}
		System.out.println("result : "+reporterCheck);
		if(reporterCheck.equals("ok")){
		int result=0;
		//subArticleNo가 있다면 else문 수행
		if(subArticleVO.getSubArticleNo()==0){
			//주제글 신고를 업데이트
			//주제글 신고 업데이트 후 업데이트가 실패했다면 신고하는메서드 실행 (실패시 result에 0이 할당된다)
			result=reportDAO.updateMainArticleReport(mainArticleVO);
			System.out.println("reulst : "+result);
			if(result==0){
			//주제글 신고하는 메서드
				System.out.println("주제글 신고 생성");
				reportDAO.mainArticleReport(mainArticleVO);
			}
		}else{
			//잇는글 신고를 업데이트
			//잇는글 업데이트 후 업데이트가 실패했다면 신고하는메서드 실행 (실패시 result에 0이 할당된다)
			result=reportDAO.updateSubArticleReport(subArticleVO);
			System.out.println("reulst : "+result);
			if(result==0){
			//잇는글 신고 수행하는 메서드
				reportDAO.subArticleReport(subArticleVO);
			}
		}
		//현재 ReportNumber를 받아오는 메서드
		int reportNo=reportDAO.nowReportNumber();
		System.out.println("현재 리포트 넘버 : "+reportNo );
		//신고자를 추가해주는 메서드
		reportDAO.insertReporter(memberVO, reportNo);
		//신고한 report의 신고수를 받아와 10이상이되면 Block해준다
		int reportAmount=reportDAO.reportCount(reportNo);
		if(reportAmount>=10){
			//자동 블락 해주는 메서드
			int blockCheck=articleBlock(subArticleVO);//현재 객체에 있는 메서드를 재사용(Block이 된다면 1)
			if(blockCheck==1){
				//신고자들에게 포인트를 지급해주는 메서드
				memberPointUpdate(reportNo);
				ReportVO rvo=new ReportVO();
				rvo.setReportNo(reportNo);
				//Block처리가 완료된 신고자들은 신고자 목록에서 지워주는 메서드
				reportDAO.deleteByReporter(rvo);
			}
		}
		}
		return reporterCheck;
	}
	@Override
	public List<MainArticleVO> SearchOnTopMenu(String selector,String keyword) {
		System.out.println("유틸서비스:"+selector+""+keyword);
	
		if(selector.equals("제목")){
			return searchDAO.searchBytitle(keyword);
		}else if(selector.equals("내용")){
			return searchDAO.searchByContext(keyword);
		}else{
			return searchDAO.searchByNickName(keyword);
		}
		
		
	}

	@Override
	public void saveSearch(String keyword){
		 int result= searchDAO.updateSearch(keyword);
		 if( result==0){
			 searchDAO.insertSearch(keyword);
		 }
	}
	public List<HashMap<String, String>> selectReport(){
		return searchDAO.selectReport();
	}
	
}
