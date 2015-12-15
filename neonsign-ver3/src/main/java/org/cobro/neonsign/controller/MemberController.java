package org.cobro.neonsign.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cobro.neonsign.model.ItjaMemberBean;
import org.cobro.neonsign.model.MemberService;
import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MemberListVO;
import org.cobro.neonsign.vo.MemberVO;
import org.cobro.neonsign.vo.PickedVO;
import org.cobro.neonsign.vo.SubscriptionInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@Resource
	private MemberService memberService;
	@Resource
	private ItjaMemberBean itjaMemberBean;
	
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
	@RequestMapping("memberLogin.neon")
	public ModelAndView memberLogin(HttpServletRequest request, MemberVO memberVO){
		MemberVO memberVO1 = memberVO;
		memberVO=memberService.memberLogin(memberVO);
		ModelAndView mav = new ModelAndView();
		if(memberVO==null){
			memberVO=memberService.defaultMemberLogin(memberVO1);
		}
		if(memberVO!=null){
			List<ItjaMemberVO> list = itjaMemberBean.getItjaListByMemberEmail(memberVO);
			//0,0번째 글은 존재하지 않는다. 잇자를 누른 글이 하나도 없어도 사용자의 이메일을 얻기 위함이다.
			list.add(new ItjaMemberVO(0,0,memberVO.getMemberEmail()));
			if(list!=null){
				memberVO.setItjaMemberList(list);
			}
			request.getSession().setAttribute("memberVO",memberVO);		
			mav = new ModelAndView("redirect:getMainList.neon");
		}else{
			memberVO=memberService.defaultMemberLogin(memberVO1);
			String fail="아이디와 비밀번호가 맞지 않습니다.";
			mav=new ModelAndView("loginPage","fail",fail);
		}
		return mav;
	}
	
	@RequestMapping("memberLogout.neon")
	public ModelAndView memberlogout(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		if (session != null)
			session.invalidate();
		return new ModelAndView("redirect:getMainList.neon");
	}
	
	/**
	 *  가입후 바로 로그인 가능하도록함 
	 *  @author 한솔
	 */
	@RequestMapping("memberJoinByEmail.neon")
	public ModelAndView memberRegister(HttpServletRequest request,MemberVO memberVO){
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
	 * 관리자 페이지에서 일반&블락 회원멤버들 리스트를 출력
	 * @author 한솔
	 */
	@RequestMapping("getMemberList.neon")
	public ModelAndView getMemberList(HttpServletRequest request,MemberVO mvo){
		ModelAndView mv = new ModelAndView();
		 MemberListVO memberList=memberService.getMemberList(1);//회원 리스트를 받아온다
		 MemberListVO blockMemberList=memberService.getBlockMemberList(1);//회원 리스트를 받아온다
		 HashMap<String, MemberListVO> memberMap=new HashMap<String, MemberListVO>();
		 memberMap.put("memberList",memberList); memberMap.put("blokcMemberList", blockMemberList);
		mv.addObject("memberMap", memberMap);
		mv.setViewName("forward:adminPageView.neon");
		return mv;
	}
	
	/**
	 * 관리자 페이지에서 type을 받아 그 type에 맞게
	 * Article 신고 리스트를 페이징 해주는 컨트롤러
	 * @author 윤택
	 */
	@RequestMapping("memberReportListPaging.neon")
	@ResponseBody
	public MemberListVO articleReportListPaging(String pageNo, String pageType){
		System.out.println("AJax 연동 페이징 넘버 "+pageNo);
		System.out.println("Ajax 연동 페이징 타입 "+pageType);
		MemberListVO memberReportList=null;
			int pageNumber=Integer.parseInt(pageNo);
			if(pageType.equals("memberList")){
				System.out.println("memberList");
				memberReportList=memberService.getMemberList(pageNumber);
			}else{
				System.out.println("blockMemberList ");
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
		System.out.println("넘어오는 찜 정보 : " + pvo);
		HttpSession session = request.getSession(false);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(session != null){
			map = memberService.updatePickedVO(pvo); 
			List<PickedVO> pickList = memberService.getPickListByMemberEmail(pvo.getMemberEmail());
			// System.out.println("cont pickList : " + pickList);
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			memberVO.setPickedVOList(pickList);
			//System.out.println(memberVO);
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
		System.out.println("넘어온 구독정보 : " + subscriptionInfoVO);
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
	public String memberUpdate(HttpServletRequest request,MemberVO memberVO){
		//System.out.println("업데이트비번 :"+memberVO.getMemberPassword());
		HttpSession session = request.getSession(false);
		String path="redirect:memberLogin.neon";
		if(session!=null){
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
}
