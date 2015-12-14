package org.cobro.neonsign.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class NeonInterceptor extends HandlerInterceptorAdapter{
	// 컨트롤러 로직 수행 전 동작된다.
		// 이 오버라이딩 메서드가 리턴값이 true 이면
		// 컨트롤러 메서드가 수행되고
		// false이면 동작되지 않는다.
		// 비 인증 상태이면 index.jsp로 redirect 시키고,
		// false
		
		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			System.out.println("interceptor 실행");
			HttpSession session = request.getSession(false);
			if(session==null || session.getAttribute("memberVO")==null){
				String ajaxCall = (String) request.getHeader("AJAX");
				if(ajaxCall.equals("true")){
					 response.sendError(901);
				}else{
					response.sendRedirect("index.neon");
				}
				return false;  // 컨트롤러 메서드 수행하지 않게 한다.
			}
			return true;
		}
}
