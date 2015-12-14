<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="temporaryPasswordView" style="margin-top: 100px; font: black">
<c:choose>
	<c:when test="${requestScope.memberVO==null}">
		비밀번호 찾기 요청이 만료되었거나 올바르지 않은 접근입니다.
	</c:when>
	<c:otherwise>
		${requestScope.memberVO.memberEmail}님이 요청하신 비밀번호 복구 요청에 다라 변경된 비밀번호는 아래와 같습니다.<br>
		변경된 비밀 번호 : ${requestScope.memberVO.memberPassword}<br>
		다시 잊어버리지 않도록 로그인 후 회원 정보 수정란에서 비밀번호를 변경해주세요!
	</c:otherwise>
</c:choose>
</div>
