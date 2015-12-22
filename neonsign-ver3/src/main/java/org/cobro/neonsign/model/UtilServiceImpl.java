package org.cobro.neonsign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PagingBean;
import org.cobro.neonsign.vo.RankingVO;
import org.cobro.neonsign.vo.ReportListVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.cobro.neonsign.vo.TagBoardVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilServiceImpl implements UtilService{
	@Resource
	private BoardDAO boardDAO;
	@Resource
	private SearchDAO searchDAO;
	@Resource
	private ReportDAO reportDAO;
	@Override
	public List<MainArticleVO> searchByKeyword(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 반려한 신고글의 리스트를 삭제
	 * @author 윤택
	 */
	@Transactional
	@Override
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
		//System.out.println("totalContenrs : "+totalReports);
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
		//System.out.println("totalContenrs : "+totalReports);
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
	
	
	/** 사용자가 신고를 했을떄 실행되는 메서드
	 *  신고 처리 순서
	 *  
		 * --주제글 신고인지 잇는글 신고인지 걸러준다
		 * 1. 신고 업데이트 (신고 업데이트가 되지 않는다면 1-1.)
		 * 1-1. 신고 생성 
		 * 2. 현재 ReportNo를 받아옴
		 * 3. 신고자 생성
		 * --만약에 현재 신고 횟수가 10이 된다면 Block 하고 
		 * 	신고자들에게 포인트를 준다
	 */
	@Transactional
	@Override
	public String articleReport(MainArticleVO mainArticleVO,
			SubArticleVO subArticleVO, MemberVO memberVO) {
		int reportNo=0;
		String reporterCheck="ok";
		//신고한 회원의 신고한 리포트 넘버를 받아온다
		List<Integer> reporterReportNoList=reportDAO.selectReporterReportNo(memberVO);
		//신고자의 report넘버에 대응하는 MainArticleNo가 있으면 신고를 하지않고
		//reporterCheck에 fail을 할당한다
		for(int i=0; i<reporterReportNoList.size();i++){
			//System.out.println("index : "+reporterReportNoList.get(i));
			ReportVO reportVO=reportDAO.findReportByReportNoAndMainArticleNo(reporterReportNoList.get(i),mainArticleVO);
			//System.out.println("reportVO : "+reportVO);
			if(reportVO!=null){
				reporterCheck="fail";
				break;
			}
		}
		//System.out.println("result : "+reporterCheck);
		if(reporterCheck.equals("ok")){
		int result=0;
		//subArticleNo가 있다면 else문 수행
		if(subArticleVO.getSubArticleNo()==0){
			//주제글 신고 전 신고자가 다시 신고를 했는지 확인
			for(int i=0; i<reporterReportNoList.size();i++){
				//System.out.println("index : "+reporterReportNoList.get(i));
				ReportVO reportVO=reportDAO.findReportByReportNoAndMainArticleNo(reporterReportNoList.get(i),mainArticleVO);
				//System.out.println("reportVO : "+reportVO);
				if(reportVO!=null){
					reporterCheck="fail";
					break;
				}
			}
			//만약 신고자가 또 신고를 했다면 업데이트나 인서트를 안한다
			if(reporterCheck.equals("ok")){//신고를 안했다면 reporterCheck 는 ok가 되고 신고를 실행한다
			//주제글 신고를 업데이트
			//주제글 신고 업데이트 후 업데이트가 실패했다면 신고하는메서드 실행 (실패시 result에 0이 할당된다)
			result=reportDAO.updateMainArticleReport(mainArticleVO);
			//System.out.println("reulst : "+result);
			if(result==0){
			//주제글 신고하는 메서드
				//System.out.println("주제글 신고 생성");
				reportDAO.mainArticleReport(mainArticleVO);
			}
			reportNo=reportDAO.nowMainArticleReportNumber(mainArticleVO);
			}
		}else{
			//잇는글 신고 전 신고자가 또 신고를 했는지 확인
			for(int i=0; i<reporterReportNoList.size();i++){
				//System.out.println("index : "+reporterReportNoList.get(i));
				ReportVO reportVO=reportDAO.findReportByReportNoAndSubArticleNo(reporterReportNoList.get(i),subArticleVO);
				//System.out.println("reportVO : "+reportVO);
				if(reportVO!=null){
					reporterCheck="fail";
					break;
				}
			}
			//만약 신고자가 또 신고를 했다면 업데이트나 인서트를 안한다
			if(reporterCheck.equals("ok")){//신고를 안했다면 reporterCheck 는 ok가 되고 신고를 실행한다
				//잇는글 신고를 업데이트
				//잇는글 업데이트 후 업데이트가 실패했다면 신고하는메서드 실행 (실패시 result에 0이 할당된다)
			result=reportDAO.updateSubArticleReport(subArticleVO);
			//System.out.println("reulst : "+result);
			if(result==0){
			//잇는글 신고 수행하는 메서드
				reportDAO.subArticleReport(subArticleVO);
			}
			reportNo=reportDAO.nowSubArticleReportNumber(subArticleVO);
			}
		}
		if(reporterCheck.equals("ok")){
		//신고 후 신고당한 회원의 신고 횟수를 업데이트
		reportDAO.memberReportAmountUpdate(memberVO);
		//만약 회원의 신고 횟수가 50이상이 된다면 블락한다
		if(reportDAO.getMemeberReportAmount(memberVO)>=50){
			reportDAO.memberBlack(memberVO);
		}
		//현재 ReportNumber를 받아오는 메서드
		//int reportNo=reportDAO.nowReportNumber();
		//System.out.println("현재 리포트 넘버 : "+reportNo );
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
		}
		return reporterCheck;
	}
	
	//2015-12-19 대협추가
	@Override
	public ArrayList<MainArticleVO> SearchOnTopMenu(String selector,String keyword, int pageNo, String tag) {
		ArrayList<MainArticleVO> list = new ArrayList<MainArticleVO>();
		if(selector.equals("제목")){
			List<MainArticleVO> listByTitle =  searchDAO.searchBytitle(keyword, pageNo, tag);
			list = (ArrayList<MainArticleVO>) listByTitle;
		}else if(selector.equals("내용")){
			List<MainArticleVO> listByContent = searchDAO.searchByContext(keyword, pageNo, tag);
			list = (ArrayList<MainArticleVO>) listByContent;
		}else if(selector.equals("작성자")){
			List<MainArticleVO> listByWriter =   searchDAO.searchByNickName(keyword, pageNo, tag);
			list = (ArrayList<MainArticleVO>) listByWriter;
		}else{
			List<MainArticleVO> listByPerson =   searchDAO.searchByPerson(keyword, pageNo, tag);
			list = (ArrayList<MainArticleVO>) listByPerson;
		}
		
		String tagName = "";
		for(int i = 0 ; i<list.size() ; i++){
			List<TagBoardVO> tagBoardList = boardDAO.getMainArticleTagList(list.get(i).getMainArticleNo());
			for(int j = 0 ; j<tagBoardList.size() ; j++){
				if(j == tagBoardList.size()-1){
					tagName += "#" + tagBoardList.get(j).getTagName();
				}else{
					tagName += "#" +  tagBoardList.get(j).getTagName() + " ";
				}
				list.get(i).setTagName(tagName);
			}
			tagName = "";
		}
		ArrayList<RankingVO> rankingVOList = new ArrayList<RankingVO>();
		for(int i = 0 ; i<list.size() ; i++){
			rankingVOList.add(boardDAO.getMemberRankingByMemberEmail(list.get(i).getMemberVO()));
			//System.out.println(rankingVOList);
			list.get(i).getMemberVO().setRankingVO(rankingVOList.get(i));
		}
		return list;
	}

	@Transactional
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
	
	@Override
	public HashMap<String, String> memberReport(String memberReportEmail,
			String memberReporterEmail) {
		//회원 신고 테이블에 Insert
		HashMap<String, String> result = new HashMap<String, String>();
		Map<String, String> map=new HashMap<String, String>();
		if(memberReportEmail.equals(memberReporterEmail)){
			result.put("result", "self");
		}else{
			map.put("memberReportEmail", memberReportEmail); map.put("memberReporterEmail", memberReporterEmail);
			result.put("result", reportDAO.memberReport(map));
			//신고후 신고 당한 회원의 신고 횟수를 업데이트
			if(result.get("result").equals("ok")){
				MemberVO memberVO=new MemberVO();
				memberVO.setMemberEmail(memberReportEmail);
				reportDAO.memberReportAmountUpdate(memberVO);
				result.put("reportCount", String.valueOf(reportDAO.getMemeberReportAmount(memberVO)));
				//만약 회원의 신고 횟수가 50이상이 된다면 블락한다
				if(reportDAO.getMemeberReportAmount(memberVO)>=50){
					reportDAO.memberBlack(memberVO);
				}
			}
		}
		return result;
	}
	
}
