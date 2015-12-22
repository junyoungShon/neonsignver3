package org.cobro.neonsign.controller;


import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cobro.neonsign.model.ItjaMemberBean;
import org.cobro.neonsign.model.MemberService;
import org.cobro.neonsign.utility.SecurityUtil;
import org.cobro.neonsign.vo.FileVO;
import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MemberListVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PickedVO;
import org.cobro.neonsign.vo.ServiceCenterListVO;
import org.cobro.neonsign.vo.ServiceCenterVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@Resource
	private MemberService memberService;
	@Resource
	private ItjaMemberBean itjaMemberBean;
	
	
	/**
	 * 쿠키를 통한 자동 로그인 인덱스 적용
	 * @author JeSeong Lee 
	 */
	@RequestMapping("index.neon")
	public ModelAndView checkCookieIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 재 로그인시 자동로그인여부를 확인하여 로그인을 처리하는 부분
		ModelAndView mav = new ModelAndView();
	    String strALID = "";
	    String strALMD5 = "";
	    Cookie[] cookies = request.getCookies();
	    if(cookies != null){  // 쿠키값이 있을시, 저장해둔 email과 난수값을 받아옴
	        for (int i = 0; i < cookies.length; i++) {
	            Cookie thisCookie = cookies[i];
	            if ("COOKIE_MEMBER_EMAIL".equals(thisCookie.getName())){
	            	strALID = thisCookie.getValue();
	            }
	            if ("MEMBER_AUTOLOGIN_MD5".equals(thisCookie.getName())){
	            	strALMD5 = thisCookie.getValue();
	            }
	        }
	        // 쿠키의 이메일을 보내서 해당 이메일의 난수값 가져옴
	        String strALKey = memberService.getMemberAutologinMD5(strALID);
	        if(strALKey != null && strALKey.equals(strALMD5)){  // 쿠키와 로그인 정보 일치
	            // 쿠키정보를 업데이트한다(저장기간을 갱신한다)
	            Cookie[] cookiesUpdate = request.getCookies();
	            for (int i = 0; i < cookiesUpdate.length; i++) {
	                Cookie thisCookie = cookiesUpdate[i];
	                if ("COOKIE_MEMBER_EMAIL".equals(thisCookie.getName()) && "MEMBER_AUTOLOGIN_MD5".equals(thisCookie.getName())) {
	                    thisCookie.setMaxAge(2592000);  // 한달간 저장
	                    response.addCookie(thisCookie); 
	                }
	            }
	            // 로그인 수행
	            MemberVO memberVO = new MemberVO();
	            memberVO.setMemberEmail(strALID);
	            memberVO.setMemberPassword(memberService.getMemberPasswordByCookieEmail(strALID));
	            memberVO=memberService.pointDefaultMemberLogin(memberVO);
	            // System.out.println(memberVO);
	            request.getSession().setAttribute("memberVO", memberVO);
	            mav = new ModelAndView("redirect:getMainList.neon");
	        }else{
	            // 로그인 정보 불일치, 쿠키 삭제
	            Cookie[] cookiesDel = request.getCookies();
	            for (int i = 0; i < cookiesDel.length; i++) {
	                Cookie thisCookie = cookiesDel[i];
	                if ("COOKIE_MEMBER_EMAIL".equals(thisCookie.getName()) || "MEMBER_AUTOLOGIN_MD5".equals(thisCookie.getName())) {
	                    thisCookie.setMaxAge(0);
	                    response.addCookie(thisCookie); 
	                }
	            }
	            mav = new ModelAndView("index");
	        }
	    }else{
	    	// 쿠키값 없다. 자동로그인이 아니므로 그냥 패스.
	    	mav = new ModelAndView("index");
	    }
		return mav;
	}
	
	/**
	 *  ajax 이메일 중복확인 
	 *  @author 한솔
	 */
	@RequestMapping("findMemberByEmail.neon")
	@ResponseBody
	public boolean findMemberByEmail(String emailComp){
		//System.out.println(emailComp);
		MemberVO memberVO= memberService.findMemberByEmail(emailComp);
		//System.out.println(emailComp);
		boolean check=true;
		if(memberVO!=null){
		check=false;
		//System.out.println(check);
		}else{
		check=true;
		//System.out.println(check);
		}
		return check;	
	}
	/**
	 *  ajax 닉네임 중복확인 
	 *  @author 한솔
	 */
	@RequestMapping("findMemberByNickName.neon")
	@ResponseBody
	public boolean findMemberByNickName(String nameComp){
		//System.out.println(nameComp);
		MemberVO memberVO= memberService.findMemberByNickName(nameComp);
		//System.out.println(memberVO);
		boolean check=true;
		if(memberVO!=null){
			check=false;
			//System.out.println(check);
		}else{
			check=true;
			//System.out.println(check);
		}
		return check;	
	}
	
	/**
	 * 로그인, 자동로그인
	 */
	@RequestMapping("memberLogin.neon")
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response, 
			MemberVO memberVO, String confirmSaveLog){
		MemberVO memberVO1 = memberVO;
		memberVO=memberService.pointMemberLogin(memberVO);
		ModelAndView mav = new ModelAndView();
		if(memberVO==null){
			memberVO=memberService.pointDefaultMemberLogin(memberVO1);
		}
		if(memberVO!=null){
			List<ItjaMemberVO> list = itjaMemberBean.getItjaListByMemberEmail(memberVO);
			//0,0번째 글은 존재하지 않는다. 잇자를 누른 글이 하나도 없어도 사용자의 이메일을 얻기 위함이다.
			list.add(new ItjaMemberVO(0,0,memberVO.getMemberEmail()));
			if(list!=null){
				memberVO.setItjaMemberList(list);
			}
			request.getSession().setAttribute("memberVO", memberVO);
			mav = new ModelAndView("redirect:getMainList.neon");
			// 자동로그인 체크여부에 따라 작동
			if(confirmSaveLog!=null){
			    String MD5String = "";
			    String MD5Key    = "";
			    try{
			        // 랜덤한 키값을 생성
			        StringBuffer sbKey = new StringBuffer();
			        while(sbKey.length() < 5){
			            sbKey.append((char)((Math.random() * 26) + 65));
			        }
			        MD5Key    = sbKey.toString();
			        MD5String = SecurityUtil.getCryptoMD5String(MD5Key);
			    }catch(Exception e) {
			        e.printStackTrace();
			    }
			    // 쿠키에 아이디 저장
			    Cookie alIDCookie = new Cookie("COOKIE_MEMBER_EMAIL", memberVO.getMemberEmail());
			    alIDCookie.setMaxAge(2592000);  // 한달간 저장(최대 자동로그인 기간은 한달)
			    response.addCookie(alIDCookie);
			    // 쿠키에 암호화된 문자열 저장
			    Cookie alKeyCookie = new Cookie("MEMBER_AUTOLOGIN_MD5", MD5String);
			    alKeyCookie.setMaxAge(2592000);
			    response.addCookie(alKeyCookie);
			    // MD5 키값을 DB에 저장
			    memberService.saveAutoLogInfo(alIDCookie.getValue(), alKeyCookie.getValue());
			}
			
			//만약 블락 회원이라면 세션을 없애고 loginPage로 이동 후 블락 회원이라고 한다
			if(memberVO.getMemberCategory().equals("BLACK")){
				request.getSession().invalidate();
				String fail="해당 계정은 관리자에 의해 정지된 계정입니다";
				mav=new ModelAndView("loginPage","fail",fail);
			}
		}else{
			memberVO=memberService.pointDefaultMemberLogin(memberVO1);
			String fail="아이디와 비밀번호가 맞지 않습니다.";
			mav=new ModelAndView("loginPage","fail",fail);
		}
		return mav;
	}
	
	// 로그아웃시 해당 회원 DB의 쿠키난수값 null로 변경
	@RequestMapping("memberLogout.neon")
	public ModelAndView memberlogout(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
		if (session != null){
			memberService.deleteMemberCookieByMemberEmail(memberVO.getMemberEmail());
			session.invalidate();
		}	
		return new ModelAndView("redirect:getMainList.neon");
	}
	
	/**
	 * 글카드 배경파일을 업로드하기 위한 멤버변수
	 * 2015-12-15 대협추가
	 * @author daehyeop
	 */
	@Resource(name="profileImgUploadPath")
	private String profileImgPath; 
	
	/**
	 *  가입후 바로 로그인 가능하도록함 
	 *  @author 한솔
	 */
	@RequestMapping("memberJoinByEmail.neon")
	public ModelAndView memberRegister(FileVO fvo, HttpServletRequest request,MemberVO memberVO){
		//2015-12-15 대협추가
		System.out.println("컨트롤러 경로 : "+ profileImgPath);
		MultipartFile file = fvo.getFile();
		String fileName = null;
		if(file!=null){
		 fileName = file.getOriginalFilename();
			if(!fileName.equals("")){
				try{
					fileName = memberVO.getMemberEmail()+"_"+fileName;
					file.transferTo(new File(profileImgPath+fileName));
				}catch(Exception e){
					e.printStackTrace();
				}
			}else{
				fileName="basicImg/abok.png";
			}
		}else{
			fileName="basicImg/abok.png";
		}
		System.out.println(fileName);
		memberVO.setMemberProfileImgName(fileName);
		memberService.pointMemberRegister(memberVO);
		request.setAttribute("memberVO", memberVO);
		return new ModelAndView("forward:memberLogin.neon");
	}

/*	
	public ModelAndView memberUpdate(HttpServletRequest request){
	
	}*/

	/**
	 * 회원 이메일을 받아 그 회원을 블락 시키는 메서드
	 * @author 윤택
	 */
	@RequestMapping("memberBlock.neon")
	public ModelAndView memberBlock(HttpServletRequest request){
		String memberEmail=request.getParameter("memberEmail");
		memberService.memberBlock(memberEmail);
		return new ModelAndView("redirect:getMemberList.neon");
	}
	
	/**
	 * 회원 이메일을 받아 그 회원을 블락 시키는 메서드
	 * @author 윤택
	 */
	@RequestMapping("memberBlockRelease.neon")
	public ModelAndView memberBlockRelease(HttpServletRequest request){
		String memberEmail=request.getParameter("memberEmail");
		memberService.memberBlockRelease(memberEmail);
		return new ModelAndView("redirect:getMemberList.neon");
	}
	
	/**
	 * 관리자 페이지에서 일반&블락 회원멤버들 리스트를 출력
	 * @author 한솔
	 */
	@RequestMapping("getMemberList.neon")
	public ModelAndView getMemberList(HttpServletRequest request,MemberVO mvo){
		ModelAndView mv = new ModelAndView();
		 MemberListVO memberList=memberService.getMemberList(1);//회원 리스트를 받아온다
		 MemberListVO blockMemberList=memberService.getBlockMemberList(1);//회원 리스트를 받아온다
		 ServiceCenterListVO serviceCenterList=memberService.ServiceCenterList(1);//문의글 리스트를 받아온다
		 HashMap<String, MemberListVO> memberMap=new HashMap<String, MemberListVO>();
		 memberMap.put("memberList",memberList); memberMap.put("blokcMemberList", blockMemberList);
		mv.addObject("memberMap", memberMap);
		mv.addObject("serviceCenterList",serviceCenterList);
		mv.setViewName("forward:adminPageView.neon");
		return mv;
	}

/**
	 * 문의글 쓰기 
	 * @author 전재영
	 */
	@RequestMapping("writeServiceCenter.neon")
	public ModelAndView insertServiceCenter(ServiceCenterVO ServiceCenterVO){
		//System.out.println(ServiceCenterVO);
		memberService.insertServiceCenter(ServiceCenterVO);
		//System.out.println(ServiceCenterVO);
		return new ModelAndView("redirect:getMainList.neon");
	}
	/**
	 * 문의글 상세히 보기
	 * @author 전재영
	 */
	@RequestMapping("ServiceCenterView.neon")
	@ResponseBody
	public ServiceCenterVO ServiceCenterView(ServiceCenterVO ServiceCenterVO){
		int ServiceCenterNo= ServiceCenterVO.getServiceCenterNo();
		//System.out.println(ServiceCenterNo);
		ServiceCenterVO serviceCenterview=memberService.ServiceCenterView(ServiceCenterNo);
		//System.out.println(serviceCenterview);
		return serviceCenterview;
	}

	
	/**
	 * 관리자 페이지에서 type을 받아 그 type에 맞게
	 * Article 신고 리스트를 페이징 해주는 컨트롤러
	 * @author 윤택
	 */
	@RequestMapping("memberReportListPaging.neon")
	@ResponseBody
	public MemberListVO articleReportListPaging(String pageNo, String pageType){
		//System.out.println("AJax 연동 페이징 넘버 "+pageNo);
		//System.out.println("Ajax 연동 페이징 타입 "+pageType);
		MemberListVO memberReportList=null;
			int pageNumber=Integer.parseInt(pageNo);
			if(pageType.equals("memberList")){
				//System.out.println("memberList");
				memberReportList=memberService.getMemberList(pageNumber);
			}else{
				//System.out.println("blockMemberList ");
				memberReportList=memberService.getBlockMemberList(pageNumber);
			}
			return memberReportList;
	}
	
	/**
	 * 찜목록 수정(로그인된 세션도 수정)
	 * @author JeSeong Lee
	 */
	@RequestMapping(value="auth_updatePickedVO.neon", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> updatePickedVO(PickedVO pvo, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(session != null){
			map = memberService.updatePickedVO(pvo); 
			List<PickedVO> pickList = memberService.getPickListByMemberEmail(pvo.getMemberEmail());
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			memberVO.setPickedVOList(pickList);
			session.setAttribute("memberVO", memberVO);
		}
		return map;
	}
	
	/**
	 * 구독 정보의 모든 것
	 * @author JeSeong Lee 
	 */
	@RequestMapping(value="auth_updateSubscriptionInfo.neon", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> updateSubscriptionInfo(SubscriptionInfoVO subscriptionInfoVO, HttpServletRequest request){
		//System.out.println("넘어온 구독정보 : " + subscriptionInfoVO);
		HttpSession session = request.getSession(false);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(session != null){
			if(subscriptionInfoVO.getPublisher().equals(subscriptionInfoVO.getSubscriber())){
				map.put("subscriptionResult", "selfSubscription");
			}else{
				map = memberService.updateSubscriptionInfo(subscriptionInfoVO);
				List<SubscriptionInfoVO> subscriptionInfoList
					= memberService.getSubscriptionListBySubscriberMemberEmail(subscriptionInfoVO);
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				memberVO.setSubscriptionInfoList(subscriptionInfoList);
				session.setAttribute("memberVO", memberVO);
			}
		}
		return map;
	}
	
	
	/**
	 * 현재 내 비밀번호가 맞는지 확인
	 * @author 한솔
	 */
	@RequestMapping("findByPassword.neon")
	@ResponseBody
	public MemberVO findByPassword(String mailComp){
		//System.out.println(mailComp);
		MemberVO memberVO= memberService.findByPassword(mailComp);
		//System.out.println("member:"+memberVO);
	
		return memberVO;	
	}
	
	/**
	 * 회원정보수정
	 * 1.현재비번확인
	 * 2.닉네임변경
	 * 3.비밀번호 변경
	 * 4.변경 비밀번호 재확인
	 * @author 한솔
	 */
	@RequestMapping("memberUpate.neon")
	public String memberUpdate(FileVO fvo, HttpServletRequest request,MemberVO memberVO){
		//2015-12-15 대협추가
		MultipartFile file = fvo.getFile();
		String fileName = file.getOriginalFilename();
		if(!fileName.equals("")){
			try{
				fileName = memberVO.getMemberEmail()+"_"+fileName;
				file.transferTo(new File(profileImgPath+fileName));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			MemberVO memberVo = memberService.findMemberByEmail(memberVO.getMemberEmail());
			fileName = memberVo.getMemberProfileImgName();
		}
		memberVO.setMemberProfileImgName(fileName);
		HttpSession session = request.getSession(false);
		String path="redirect:memberLogin.neon";
		if(session!=null){
			List<ItjaMemberVO> list = itjaMemberBean.getItjaListByMemberEmail(memberVO);
			//0,0번째 글은 존재하지 않는다. 잇자를 누른 글이 하나도 없어도 사용자의 이메일을 얻기 위함이다.
			list.add(new ItjaMemberVO(0,0,memberVO.getMemberEmail()));
			if(list!=null){
				memberVO.setItjaMemberList(list);
			}
			request.getSession().setAttribute("memberVO",memberVO);
			memberService.memberUpdate(memberVO);
			session.setAttribute("memberVO", memberVO);
			path="redirect:getMainList.neon";
		}
		return path;
	}
	/**
	 * 회원가입멤버가 탈퇴를 할때
	 * @author 한솔
	 */
	@RequestMapping("memberDelete.neon")
	public String memberDelete(MemberVO memberVO){
		//System.out.println("탈퇴회원정보:"+memberVO);
		memberService.memberDelete(memberVO);
		String path="redirect:memberLogout.neon";
    	return path;
	}
	/**
	 * 비밀번호를 찾기 위한 메일 전송
	 * @author junyoung
	 */
	@RequestMapping("findPasswordMailRequest.neon")
	public String findPasswordMailRequest(FindPasswordVO findPasswordVO){
		memberService.findPasswordMailRequest(findPasswordVO);
		return "loginPage";
	}
	/**
	 * 비밀번호 요청 메일을 받고 제시된 링크를 클릭했을 때 메서드
	 * 
	 * @author junyoung
	 */
	@RequestMapping("requestTemporaryPassword.neon")
	public ModelAndView requestTemporaryPassword(FindPasswordVO findPasswordVO){
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberVO", memberService.requestTemporaryPassword(findPasswordVO));
		mav.setViewName("temporaryPasswordView");
		return mav;
	}
	
	/**
	 * 회원을 신고하는 컨트롤러
	 * @윤택
	 */
	@RequestMapping("auth_MemberReport.neon")
	@ResponseBody
	public HashMap<String, String> memberReport(String memberReportEmail, String memberReporterEmail){
		HashMap<String, String> result = new HashMap<String, String>();
		result=memberService.memberReport(memberReportEmail,memberReporterEmail);
		System.out.println("신고 : " + result);
		return result;
	}
}
