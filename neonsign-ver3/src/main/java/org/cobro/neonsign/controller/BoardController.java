package org.cobro.neonsign.controller;



import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cobro.neonsign.model.BoardService;
import org.cobro.neonsign.model.UtilService;
import org.cobro.neonsign.vo.FileVO;
import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MainArticleImgVO;
import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.MemberListVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.RankingVO;
import org.cobro.neonsign.vo.ReportListVO;
import org.cobro.neonsign.vo.ReportVO;
import org.cobro.neonsign.vo.ServiceCenterListVO;
import org.cobro.neonsign.vo.ServiceCenterVO;
import org.cobro.neonsign.vo.SubArticleVO;
import org.cobro.neonsign.vo.TagBoardVO;
import org.cobro.neonsign.vo.TagVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Resource
	private BoardService boardService;
	@Resource
	private UtilService utilService;
	
	/**
	 * No.1 goAnyWhere()-ModelAndView
	 * 설명 :  단순 페이지 이동 시 요청 유알엘과 뷰의 파일명을 맞추기만 하면 사용가능한 메서드
	 * @author junyoung
	 */
	@RequestMapping("{viewId}.neon")
	public String goAnyWhere(@PathVariable String viewId){
		return viewId;
	}
	
	/**
	 * 메인화면의 베스트, 새로운 주제글 받아오기
	 * @author JeSeong Lee
	 */
	@RequestMapping("getMainBestList.neon")
	public ModelAndView getMainBestList(){
		ModelAndView mav = new ModelAndView();
		// 베스트 주제글 날짜순 + Tag
		List<MainArticleVO> bestMainArticleVOListOrderByDate = boardService.getBestMainArticleVOListOrderByDate();
		for(int i=0; i<bestMainArticleVOListOrderByDate.size(); i++){
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(bestMainArticleVOListOrderByDate.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = bestMainArticleVOListOrderByDate.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = bestMainArticleVOListOrderByDate.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = bestMainArticleVOListOrderByDate.get(i).getTagName().substring(1);
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				bestMainArticleVOListOrderByDate.get(i).setMainArticleImgVO(
						new MainArticleImgVO(bestMainArticleVOListOrderByDate.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				bestMainArticleVOListOrderByDate.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		mav.setViewName("bestMainArticle");
		mav.addObject("bestMainArticleVOListOrderByDate", bestMainArticleVOListOrderByDate);
		return mav;
	}
	/**
	 * main.jsp에 베스트, 새로운주제글, Tag리스트 출력
	 * @author JeSeongLee
	 */
	@RequestMapping("getMainList.neon")
	public ModelAndView getMainList(String orderBy, String tagName){
		ModelAndView mav = new ModelAndView();
		// 새로운 주제글 날짜순 + Tag
		int pageNo = 1;
		if(orderBy==null){
			orderBy="date";
		}
		List<MainArticleVO> newMainArticleVOList
			= boardService.selectListNotCompleteMainArticle(pageNo, orderBy, tagName);
		//2015-12-08 대협추가
		for(int i=0; i<newMainArticleVOList.size(); i++){
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(newMainArticleVOList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//2015-12-10 대협추가
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = newMainArticleVOList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = newMainArticleVOList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = newMainArticleVOList.get(i).getTagName().substring(1);
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			System.out.println("새로운 주제글 : " + dir.isFile());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				newMainArticleVOList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(newMainArticleVOList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				newMainArticleVOList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		mav.addObject("newMainArticleVOList", newMainArticleVOList);
		// 전체 태그
		List<TagVO> tagVOList = boardService.getTagVOList();
		mav.addObject("tagVOList", tagVOList);
		mav.setViewName("home");
		return mav;
	}
	
	/**Controller9
	 * 무한스크롤을 위한 완결 주제글 메소드
	 * @author daehyeop
	 */
	@RequestMapping("getNewMainArticle.neon")
	@ResponseBody
	public HashMap<String, Object> getNewMainArticle(HttpServletRequest request, 
			int pageNo, String orderBy,String tagName) {
		HashMap<String, Object> map = memberBoardInfo(request);
		if (orderBy == null||orderBy.equals("")||orderBy.equals("undefined")) {
			orderBy = "date";
		}
		List<MainArticleVO> newMainArticleList = boardService.selectListNotCompleteMainArticle(pageNo, orderBy, tagName);
		ArrayList<MainArticleVO> newMainArticleArrayList = (ArrayList<MainArticleVO>) newMainArticleList;
		//2015-12-10 대협추가
		for(int i=0; i<newMainArticleArrayList.size(); i++){
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = newMainArticleArrayList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = newMainArticleArrayList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = newMainArticleArrayList.get(i).getTagName().substring(1);
			}
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(newMainArticleArrayList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				newMainArticleArrayList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(newMainArticleArrayList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				newMainArticleArrayList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		map.put("newMainArticleArrayList", newMainArticleArrayList);
		return map;
	}
	//main article 관련 메서드
	/**
	 * 글카드 배경파일을 업로드하기 위한 멤버변수
	 * 2015-12-08 대협추가
	 * @author daehyeop
	 */
	@Resource(name="articleImgUploadPath")
	private String articleImgPath; 
	
	/**
	 * 글카드 배경파일을 업로드하기 위한 멤버변수
	 * 2015-12-15 대협추가
	 * @author daehyeop
	 */
	@Resource(name="profileImgUploadPath")
	private String profileImgPath;
	
	/**Controller1
	 * 사용자가 주제글을 작성할 때 사용한다.
	 * 태그와 주제글 테이블 동시에 적용
	 * @param mainArticleVO
	 * @param tagAndArticleVO
	 * @return
	 */
	@RequestMapping("auth_insertNewMainArticle.neon")
	public ModelAndView insertMainArticle(FileVO fvo, MainArticleVO mainArticleVO,HttpServletRequest request,TagBoardVO tagBoardVO){
		String[] tagNameList = request.getParameterValues("tagName") ;
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<tagNameList.length;i++){
			list.add(tagNameList[i]);
		}
		boardService.pointInsertMainArticle(mainArticleVO,list,tagBoardVO);
		//2015-12-08 대협추가
		MultipartFile file = fvo.getFile();
		String fileOrgName = file.getOriginalFilename();
		String fileName = "";
		if (!fileOrgName.equals("")) {
			try {
				fileName = mainArticleVO.getMemberEmail()+"_"+fileOrgName;
				file.transferTo(new File(articleImgPath + fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//태그별 테마 배경화면을 보내준다.
			fileName = "/basicBg/"+list.get(0) + ".jpg";
		}
		boardService.insertMainArticleImg(mainArticleVO.getMainArticleNo(), fileName);
		return new ModelAndView("redirect:getMainList.neon");
	}
	/**
	 * 사용자가 주제글 작성을 위해 모달창을 열 때 태그들을 불러오는 메서드
	 * @author junyuoung
	 */
	@RequestMapping("auth_openMainArticleModal.neon")
	@ResponseBody
	public List<TagVO> selectListTagNameOrderBySearchCount(){
		List<TagVO> list = boardService.selectListTagNameOrderBySearchCount();
		return list;
	}
	/**
	 * 사용자가 주제글 작성을 위해 모달창을 열 때 태그들을 불러오는 메서드
	 * @author junyoung
	 */
	@RequestMapping("tagAutoComplete.neon")
	@ResponseBody
	public List<TagVO> selectListTagNameOrderBykeyWord(String tagName){
		List<TagVO> list = boardService.selectListTagNameOrderBykeyWord(tagName);
		return list;
	}
	/**
	 * 사용자가 주제글 작성을 위해 모달창을 열 때 태그들을 불러오는 메서드
	 */
	/*@RequestMapping("storyLinking.neon")
	@ResponseBody
	public HashMap<String, Object> storyLinking(SubArticleVO subArticleVO){
		return boardService.storyLinking(subArticleVO);
	}*/
	/**
	 * 해당 글의 itja 수와, 요청한 아이디가 itja를 눌렀는지 여부를 판단해준다.
	 * 이미 itjaClick했다면 세션과 DB에서 삭제
	 * 새로 itjaClick한것이라 세션과 db에 삽입
	 * @author junyoung
	 */
	@RequestMapping("auth_itjaClick.neon")
	@ResponseBody
	public HashMap<String,Object> itjaClick(HttpServletRequest request,ItjaMemberVO itjaMemberVO,SubArticleVO subArticleVO){
		HttpSession session = request.getSession(false);
		if(session!=null){
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			boolean flag = false;
			if(memberVO!=null){
				List <ItjaMemberVO> list = memberVO.getItjaMemberList();
				if(list.size()==0){
					list.add(itjaMemberVO);
				}else{
					for(int i =0;i<list.size();i++){
						if(itjaMemberVO.getMainArticleNo()==list.get(i).getMainArticleNo()
								&&itjaMemberVO.getSubArticleNo()==list.get(i).getSubArticleNo()
								&&itjaMemberVO.getMemberEmail().equals(list.get(i).getMemberEmail())){
								list.remove(i);
								flag = false;
								break;
						}else{
							flag= true;
						}
					}
					if(flag){
						list.add(itjaMemberVO);
					}
					memberVO.setItjaMemberList(list);
				}
			}
			session.setAttribute("memberVO",memberVO);
		}
		HashMap<String, Object> map = boardService.pointSelectItjaState(itjaMemberVO,subArticleVO);
		return map;
	}
	/**Controller2
	 * 사용자가 주제글을 수정하고자 할때 사용한다.
	 * @param mainArticleVO
	 * @param tagAndArticleVO
	 * @return
	 */
	public ModelAndView updateMainArticle(MainArticleVO mainArticleVO,TagBoardVO tagAndArticleVO){
		return null;
	}
	
	/**controller3
	 * 관리자 또는 글 작성자가 주제글을 지우고자 할때 사용한다.(신고 없이 순찰중에 하거나, 작성자가 지우므로 신고포상금과 무관)
	 * @param mainArticleVO
	 */
	public ModelAndView deleteMainArticle(MainArticleVO mainArticleVO){
		return null;
	}
	
	/**controller4
	 * 신고글 내용 및 작성자의 정보 출력,신고자 정보 출력을 위한 컨트롤러
	 * @author 윤택
	 */
	@RequestMapping("adminPageView.neon")
	public ModelAndView adminPageNotifyArticleList(HttpServletRequest request){
		Map<String,MemberListVO> memberMap=(Map<String,MemberListVO>)request.getAttribute("memberMap");
		ServiceCenterListVO serviceCenterMap=(ServiceCenterListVO)request.getAttribute("serviceCenterList");
		ReportListVO mainReportList=boardService.mainArticleReportList(1);//주제글 신고 리스트를 받아온다
		ReportListVO subReportList=boardService.subArticleReportList(1);//잇는글 신고 리스트를 받아온다
		HashMap<String,Object> map=new HashMap<String, Object>();//회원관리 리스트, 게시물 신고 리스트 를 map에 put 해준다
		map.put("mainReportList", mainReportList); map.put("subReportList", subReportList);
		 map.put("memberList",memberMap.get("memberList"));map.put("blokcMemberList",memberMap.get("blokcMemberList"));
		 map.put("serviceCenterList",serviceCenterMap);
		return new ModelAndView("adminPageView","adminList",map);
	}

	@RequestMapping("ServiceCenterList.neon")
	@ResponseBody
	public List<ServiceCenterVO> ServiceCenterListPaging(String pageNo){
		List<ServiceCenterVO> serviceCenterList=null;
			int pageNumber=Integer.parseInt(pageNo);
				serviceCenterList=boardService.serviceCenterList(pageNumber);
			return serviceCenterList;
	}
	/**
	 * 관리자 페이지에서 type을 받아 그 type에 맞게
	 * Article 신고 리스트를 페이징 해주는 컨트롤러
	 * @author 윤택
	 */
	@RequestMapping("mainreportListPaging.neon")
	@ResponseBody
	public ReportListVO articleReportListPaging(String pageNo, String pageType){
		ReportListVO articlereportList=null;
			int pageNumber=Integer.parseInt(pageNo);
			if(pageType.equals("mainArticleList")){
				articlereportList=boardService.mainArticleReportList(pageNumber);
			}else{
				articlereportList=boardService.subArticleReportList(pageNumber);
			}
			return articlereportList;
	}
	
	/**Controller5
	 * 관리자 페이지에서 신고글을 블락하거나 블락을 반력하는 메서드로서 신고 성공이므로 신고자들에게 포인트를 적립해준다.
	 * 블락이 반려될 경우 포인트가 적립되지 않는다.
	 * @author 윤택
	 */
	@RequestMapping("adminPageDeleteArticle.neon")
	public ModelAndView adminPageDeleteArticle(String reportNO, String articleNO, String subArticleNO
			, String command){
		int articleNumber=Integer.parseInt(articleNO);
		int reportNumber=Integer.parseInt(reportNO);	
		try{			
			/* try문은 subArticleNO이 문자열 상태가 아니라면 catch문으로 넘어가지 않으므로
			 * 잇는글을 Block하거나 반려한다*/
			int subArticleNumber=Integer.parseInt(subArticleNO);//만약에 형변환중 Exception이 발생하면 Catch문 수행
		if(command.equals("report")){
			boardService.subArticleBlock(subArticleNumber,articleNumber,reportNumber);
			//report에 성공하면 신고한 회원들에게 포인트 지급
			boardService.memberPointUpdate(reportNumber);
		}else{
			ReportVO reportVO=new ReportVO();		
			reportVO.setReportNo(reportNumber);
			boardService.reportListDelete(reportVO);
		}
		 // 만약에 reportNumber을 int로 형변환 할 수 없다면 ( null을 형변환 할수 없기에 )
		 // catch문에서 mainArticle 을 Block하거나 mainArticle 을 반려처리 한다 	 		
		}catch(NumberFormatException e){
			/* * Catch문은 주제글을 Block하거나 주제글의 신고를 반려처리하는데
			 * 쓰인다*/	 
			if(command.equals("report")){
				MainArticleVO mainArticleVO= new MainArticleVO();
				mainArticleVO.setMainArticleNo(articleNumber);
				boardService.articleBlock(mainArticleVO,reportNumber);
				//report에 성공하면 신고한 회원들에게 포인트 지급
				boardService.memberPointUpdate(reportNumber);
			}else{
				ReportVO reportVO=new ReportVO();		
				reportVO=new ReportVO();
				reportVO.setReportNo(reportNumber);
				boardService.reportListDelete(reportVO);
			}
		}
		
		return new ModelAndView("redirect:getMemberList.neon");
	}
	/**Controller6
	 * 완결 글보기를 클릭하면 해당 메서드가 실행된다.
	 * @param mainArticleVO
	 * @return
	 */
	public ModelAndView selectOneCompleteMainArticleByMainArticleNo(MainArticleVO mainArticleVO){
		return null;
	}
	/**Controller7
	 * 완결 글보기 잇자 추천버튼 눌렀을 때
	 * @return
	 */
	public ModelAndView updateLikeOfMainArticle(MainArticleVO mainArticleVO){
		return null;
	}
	/**Controller6
	 * 미완결 글보기를 클릭하면 해당 메서드가 실행된다.
	 * 	미완결 글의 디테일을 리턴해준다
	 * @param mainArticleVO
	 * @author 전윤택
	 */
	/**Controller6
	 * 미완결 글보기를 클릭하면 해당 메서드가 실행된다.
	 * 	미완결 글의 디테일을 리턴해준다
	 * @param mainArticleVO
	 * @author 전윤택
	 */
	@RequestMapping("selectOneNotCompleteMainArticleByMainArticleNo.neon")
	@ResponseBody
	public HashMap<String, Object> selectOneNotCompleteMainArticleByMainArticleNo(HttpServletRequest request,MainArticleVO mainArticleVO){
		HashMap<String, Object> map= memberBoardInfo(request);
		if (mainArticleVO!=null) {
			Map<String,Object> mainArticle=boardService.selectOneNotCompleteMainArticleByMainArticleNo(mainArticleVO);
			map.put("mainArticle", mainArticle.get("mainArticleVO"));
			map.put("likingSubArticle", mainArticle.get("likingSubArticle"));
			map.put("subArticleVO", mainArticle.get("subArticleVO"));
		}
		return map;
	}
	//sub article 관련 메서드
		/**Controller12
		 * 잇자 글 등록
		 * @author junyoung
		 * @param subArticleVO
		 * @return
		 */
	@RequestMapping("auth_writeSubArticle.neon")
	@ResponseBody
	public HashMap<String, Object> insertSubArticle(SubArticleVO subArticleVO){
		HashMap<String, Object> map = new HashMap<String, Object>();	
		//memberBoardInfo(request);
		boolean result = boardService.pointInsertSubArticle(subArticleVO);
		map.put("result",result);
		map.put("subArticleVO",subArticleVO);
		return map;
	}
	/**Controller7
	 * 미완결 주제글 잇자 추천버튼 눌렀을 때
	 * @return
	 */
	public ModelAndView updateLikeOfNotCompleteMainArticle(MainArticleVO mainArticleVO){
		return null;
	}
	/**Cotroller8
	 * 완결 주제글이 잇자수순으로 반환된다.
	 * session 확인 후 null일 경우 오류페이지로 보낸다.(오류페이지 미작성_수정요)
	 * @return
	 * @author daehyeop
	 */
	@RequestMapping("selectListCompleteMainArticle.neon")
	public ModelAndView selectListCompleteMainArticle(String orderBy, String tagName) {
		ModelAndView mav = new ModelAndView();
		int pageNo=1;
		if(orderBy==null){
			orderBy="like";
		}
		List<MainArticleVO> mainArticleList = boardService
				.selectListCompleteMainArticle(pageNo, orderBy, tagName);
		ArrayList<MainArticleVO> completeMainArticleList = (ArrayList<MainArticleVO>) mainArticleList;
		//2015-12-10 대협추가
		for(int i=0; i<completeMainArticleList.size(); i++){
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = completeMainArticleList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = completeMainArticleList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = completeMainArticleList.get(i).getTagName().substring(1);
			}
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(completeMainArticleList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				completeMainArticleList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(completeMainArticleList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				completeMainArticleList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		mav.addObject("completeMainArticleList", completeMainArticleList);
		mav.setViewName("completeMainArticleView");
		List<TagVO> tagVOList = boardService.getTagVOList();
		mav.addObject("tagVOList", tagVOList);
		return mav;
	}
	
	/**Controller9
	 * 무한스크롤을 위한 완결 주제글 메소드
	 * @author daehyeop
	 */
	@RequestMapping("getCompleteMainArticle.neon")
	@ResponseBody
	public HashMap<String, Object> getCompleteMainArticle(HttpServletRequest request, int pageNo, String tagName, String orderBy){
		if(orderBy==null||orderBy.equals("")||orderBy.equals("undefined")){
			orderBy="like";
		}
		if(tagName==null||tagName.equals("undefined")){
			tagName="";
		}
		HashMap<String, Object> map= memberBoardInfo(request);
		List<MainArticleVO> mainArticleList = boardService.selectListCompleteMainArticle(pageNo, orderBy, tagName);
		ArrayList<MainArticleVO> completeMainArticleArrayList = (ArrayList<MainArticleVO>) mainArticleList;
		//2015-12-10 대협추가
		for(int i=0; i<completeMainArticleArrayList.size(); i++){
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = completeMainArticleArrayList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = completeMainArticleArrayList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = completeMainArticleArrayList.get(i).getTagName().substring(1);
			}
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(completeMainArticleArrayList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				completeMainArticleArrayList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(completeMainArticleArrayList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				completeMainArticleArrayList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		map.put("completeMainArticleArrayList", completeMainArticleArrayList);
 		return map;
	}
	
	/**Cotroller9
	 * 미완결 주제글이날짜순으로 반환된다.
	 * @return
	 */
	public ModelAndView selectListNotCompleteMainArticleOrderByDate(){
		return null;
	}
	/**Cotroller10
	 * 미완결 주제글이 잇자수순으로 반환된다.
	 * @return
	 */
	public ModelAndView selectListNotCompleteMainArticleOrderByTotalLike(){
		return null;
	}
	/**Cotroller11
	 * 미완결 주제글이 잇자수순으로 반환된다.
	 * @return
	 */
	public ModelAndView selectListCompleteMainArticleByTagName(TagVO tagVO){
		return null;
	}
	
	
	/**Controller13
	 * 잇자글 잇자 버튼 클릭시 실행 메서드
	 * @param subArticleVO
	 * @return
	 */
	public ModelAndView updateLikeOfSubArticle(MainArticleVO mainArticleVO,SubArticleVO subArticleVO){
		return null;
	}
	/**Controller14
	 * 잇자글 블락
	 * @param subArticleVO
	 * @return
	 */
	public ModelAndView deleteSubArticle(MainArticleVO mainArticleVO,SubArticleVO subArticleVO){
		return null;
	}
	/**Controller15
	 * 10분 마다 실행되는 메서드로서 가장 많은 잇자수를 받은 댓글의 isEnd를 1로 바꿔준다.
	 * 그리고 새로운 잇자 글이 달릴 수 있도록 storyGrade를 +1한다.
	 * 나머지 잇는글은 모두 블락
	 * @param mainArticleVO
	 * @param subArticleVO
	 * @return
	 */
	public ModelAndView updateIsConnectOfSubArticle(MainArticleVO mainArticleVO,SubArticleVO subArticleVO){
		return null;
	}
	/**
	 * 사용자가 찜한 게시물과, 잇자를 누른 게시물 정보를 담아주는 메서드
	 * @author junyoung
	 */
	public HashMap<String,Object> memberBoardInfo(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession(false);
		if(session!=null){
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			if(memberVO!=null){
				map.put("itjaMemberList", memberVO.getItjaMemberList());
				map.put("pickedList", memberVO.getPickedVOList());
			}
		}
		return map;
	}
	
	/**
	 * myPage요소들 불러옴
	 * @author JeSeong Lee
	 */
	@RequestMapping("mypage.neon")
	public ModelAndView myPage(MemberVO memberVO){
		ModelAndView mav = new ModelAndView();
		// 마이페이지 주인 email주소로 랭킹, 구독정보 받아서 memberVO에 set
		memberVO = boardService.getMemberInfoByMemberEmail(memberVO);
		File profileDir = new File(profileImgPath+memberVO.getMemberProfileImgName());
		if(profileDir.isFile()==false){
			memberVO.setMemberProfileImgName("basicImg/not_found.png");
		}
		mav.addObject("rankMemberVO", memberVO);
		// Rank에 따른 image 따오기 위한것
		List<RankingVO> rankingVOList = boardService.getRankingList();
		mav.addObject("rankingVOList", rankingVOList);
		// email주소로 찜한글 받아오기
		List<MainArticleVO> pickedMainArticleList
			= boardService.getPickedMainArticleByMemberEmailOrderByDate(memberVO);
		for(int i=0; i<pickedMainArticleList.size(); i++){
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = pickedMainArticleList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = pickedMainArticleList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = pickedMainArticleList.get(i).getTagName().substring(1);
			}
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(pickedMainArticleList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				pickedMainArticleList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(pickedMainArticleList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				pickedMainArticleList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		mav.addObject("pickedMainArticleList", pickedMainArticleList);
		// email주소로 작성한 글 받아오기
		List<MainArticleVO> writeMainArticleList
			= boardService.getWriteMainArticleByEmailOrderByDate(memberVO);
		//2015-12-10 대협추가
		for(int i=0; i<writeMainArticleList.size(); i++){
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = writeMainArticleList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = writeMainArticleList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = writeMainArticleList.get(i).getTagName().substring(1);
			}
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(writeMainArticleList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				writeMainArticleList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(writeMainArticleList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				writeMainArticleList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		mav.addObject("writeMainArticleList", writeMainArticleList);
		// email주소로 가입일자 받아서 나이 받아오기
		int joinAge = boardService.getJoinAgeByEmail(memberVO);
		mav.addObject("joinAge", joinAge);
		// email 주소로 작성한 태그 리스트 받기 : 태그 수 확인
		List<TagBoardVO> writeTagListbyEmailList
			= boardService.writeTagListbyEmail(memberVO);
		mav.addObject("writeTagListbyEmailList", writeTagListbyEmailList);
		// email 주소로 가장 많이 작성한 태그이름 받기
		TagBoardVO tagBoardVO
			= boardService.getMostWriteTagByEmail(memberVO);
		mav.addObject("tagBoardVO", tagBoardVO);
		// 게시자 email로 나를 구독하는 리스트 닉네임 받기	
		mav.addObject("subscriptedInfoList", boardService.getSubscriptedInfoListByPublisherEmail(memberVO));
		// 구독자 email로 내가 구독하는 리스트 닉네임 받기
		mav.addObject("subscriptingInfoList", boardService.getSubscriptingInfoListBySubscriberEmail(memberVO));
		// email 주소로 참여한 글 받아오기
		List<MainArticleVO> joinMainArticleList
			= boardService.getJoinMainArticleByEmailOrderByDate(memberVO);
		for(int i=0; i<joinMainArticleList.size(); i++){
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = joinMainArticleList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = joinMainArticleList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = joinMainArticleList.get(i).getTagName().substring(1);
			}
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(joinMainArticleList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				joinMainArticleList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(joinMainArticleList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				joinMainArticleList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		mav.addObject("joinMainArticleList", joinMainArticleList);
		// 구독자 email주소로 구독중인 글 받아오기
		List<MainArticleVO> subscriptingMainArticleList
			= boardService.getSubscriptingMainArticleBySubscriberEmailOrderByDate(memberVO);
		for(int i=0; i<subscriptingMainArticleList.size(); i++){
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = subscriptingMainArticleList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = subscriptingMainArticleList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = subscriptingMainArticleList.get(i).getTagName().substring(1);
			}
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(subscriptingMainArticleList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				subscriptingMainArticleList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(subscriptingMainArticleList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				subscriptingMainArticleList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		mav.addObject("subscriptingMainArticleList", subscriptingMainArticleList);
		mav.setViewName("mypage");
		return mav;
	}
	
	
	/**
	 * 회원이 게시물을 신고할때 사용 하는 컨트롤러
	 * @param mainArticleVO
	 * @param subArticleVO
	 * @param memberVO
	 * @return
	 * @author 윤택
	 */
	@RequestMapping("auth_ArticleReport.neon")
	@ResponseBody
	public String ArticleReport(MainArticleVO mainArticleVO, SubArticleVO subArticleVO , MemberVO memberVO){
		String check=boardService.articleReport(mainArticleVO, subArticleVO, memberVO);
		return check;
		
	}
	
	/**
	 * 
	 * 검색 
	 *@author 한솔
	 */
	@RequestMapping("findBy.neon")
	public ModelAndView SearchOnTopMenu(String selector, String keyword){
		ModelAndView mv = new ModelAndView();
		//2015-12-19 대협추가
		String tagName="";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("selector", selector);
		map.put("keyword", keyword);
		mv.addObject("map", map);
		int pageNo = 1;
		ArrayList<MainArticleVO> list= boardService.SearchOnTopMenu(selector,keyword,pageNo,tagName);
		for(int i=0; i<list.size(); i++){
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(list.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = list.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = list.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = list.get(i).getTagName().substring(1);
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				list.get(i).setMainArticleImgVO(
						new MainArticleImgVO(list.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				list.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		
		List<TagVO> tagVOList = boardService.getTagVOList();
		mv.addObject("tagVOList", tagVOList);
		mv.addObject("list", list);
		mv.setViewName("findBy");
	    return mv;	
	}
	
	@RequestMapping("getSearchMainArticle.neon")
	@ResponseBody
	public HashMap<String, Object> getSearchMainArticle(
			HttpServletRequest request, int pageNo, String tagName,
			String orderBy, String keyword, String selector) {
		if(orderBy==null||orderBy.equals("")||orderBy.equals("undefined")){
			orderBy="like";
		}
		if(tagName==null||tagName.equals("undefined")){
			tagName="";
		}
		HashMap<String, Object> map= memberBoardInfo(request);
		ArrayList<MainArticleVO> searchList = boardService.SearchOnTopMenu(selector, keyword, pageNo, tagName);
		for(int i=0; i<searchList.size(); i++){
			MainArticleImgVO mainArticleImgVO =boardService.selectMainArticleImg(searchList.get(i).getMainArticleNo());
			if(mainArticleImgVO==null){
				mainArticleImgVO = new MainArticleImgVO();
			}
			//태그가 두개인지 확인 -1이면 한개
			int tagInt = searchList.get(i).getTagName().lastIndexOf(" ");
			String firstTagName = "";
			if(tagInt!=-1){
				firstTagName = searchList.get(i).getTagName().substring(1, tagInt);
			}else{
				firstTagName = searchList.get(i).getTagName().substring(1);
			}
			//파일의 경로를 담는다.
			File dir = new File(articleImgPath+mainArticleImgVO.getMainArticleImgName());
			//해당 경로에 파일이 존재하는지 확인
			if (dir.isFile() == false) {
				int random = (int) (Math.random()*18);
				searchList.get(i).setMainArticleImgVO(
						new MainArticleImgVO(searchList.get(i)
								.getMainArticleNo(), "basicBg/"+random+".jpg"));
			} else {
				searchList.get(i).setMainArticleImgVO(
						mainArticleImgVO);
			}
		}
		map.put("searchList", searchList);
		return map;
	}
	
	@RequestMapping("report.neon")
	public ModelAndView selectReport(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("findBy");
		List<HashMap<String, String>> aoplist = utilService.selectReport();
		mv.addObject("aoplist", aoplist);
		return mv;
	}

	/**
	 * 공유 페이지로 이동하는 컨트롤러
	 * @author 윤택
	 */
	@RequestMapping("sharePage.neon")
	public ModelAndView sharePage(MainArticleVO mainArticleVO){
		MainArticleVO mainArticleInfo=boardService.getMainArticleInfo(mainArticleVO);
		mainArticleInfo.setMainArticleImgVO(new MainArticleImgVO(mainArticleVO.getMainArticleNo(), "basicBg/반전.jpg"));
		return new ModelAndView("share","mainArticleInfo",mainArticleInfo);
	}
}
