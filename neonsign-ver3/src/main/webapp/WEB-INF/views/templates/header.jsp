<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid header">
        <div class="navbar-header"> top-menu
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          
         
          
        <a class="navbar-brand top-menu" href="#">
        <img class="logoImg" src="${initParam.root}resources/img/width_logo_BrainOnSign.png">
		</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse top-menu">
       		<div class="col-xs-4 ">
		    <div class="input-group top-menu"  style="margin-top: 10px;">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">제목</span> <span  class="caret"></span>
                    </button>
                  <ul class="dropdown-menu" role="menu" id="choose">
                      <li><a href="#" class="title" >제목</a></li>
                      <li><a href="#" >내용</a></li>
                      <li><a href="#" >작성자</a></li>
                   </ul> 
         </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control"  id="serch" name="search" placeholder="Search term...">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-default" id="serch_result"><span class="fa fa-search"></span></button>
                </span>
            </div>
        </div>
    			  
			
    		<ul class="nav navbar-nav navbar-right">
    			 <!-- 완결글 보기를 누르면 추천순으로 정렬된다. -대협 -->
    			  <li><a href="${initParam.root}getMainList.neon" class="top-menu">Main</a></li>
    			  <li><a href="${initParam.root}selectListCompleteMainArticle.neon" class="top-menu">완결 글 보기</a></li>
    			  <li><a href="#" class="openModalInsertArticleForm top-menu">글쓰기</a></li>
		  <c:choose>
			<c:when test="${sessionScope.memberVO==null}">
    			<li><a href="#" class="memberLogin top-menu"> <i class="fa fa-sign-in"></i>로그인</a></li>
    			<li><a href="#" class="memberJoinByEmailBtn top-menu"> <i class="fa fa-user-plus"></i>가입</a></li>
    		</c:when>
  	 	 	<c:otherwise>		
  	 	 		<c:choose>
  	 	 		<c:when test="${sessionScope.memberVO.memberCategory eq 'MASTER'}">
  	 	 				<li><a href="mypage.neon?memberEmail=${sessionScope.memberVO.memberEmail}" class="top-menu">마이페이지</a></li>
  	 	 		<li class="dropdown">
		          <a href="#" class="dropdown-toggle top-menu" data-toggle="dropdown"><i class="fa fa-user"></i>&nbsp;&nbsp;${sessionScope.memberVO.memberNickName} 님 
		         &nbsp; [ ${sessionScope.memberVO.memberEmail} ] </a>
		          <ul class="dropdown-menu">
		            <li><a href="#" class="memberUpate top-munu"> 회원 정보 수정</a></li>
		            <li class="divider"></li>
		            <li><a href="mypage.neon?memberEmail=${sessionScope.memberVO.memberEmail}" class="top-menu">회원 정보 보기</a></li>
		            <li class="divider"></li>
		            <li><a href="${initParam.root}memberLogout.neon" id="logout" class="top-menu">로그아웃</a></li>
		          </ul>
		        </li>
  	 	 		<span><input type="hidden" id="memberUserEmail" value="${sessionScope.memberVO.memberEmail}"></span>
     			  	<li><a href="${initParam.root}getMemberList.neon" >관리자 페이지</a></li>
  	 	 		</c:when>
  	 	 		<c:otherwise>
  	 	 		<li><a href="mypage.neon?memberEmail=${sessionScope.memberVO.memberEmail}" class="top-menu">마이페이지</a></li>
  	 	 		<li class="dropdown">
		          <a href="#" class="dropdown-toggle top-menu" data-toggle="dropdown"><i class="fa fa-user"></i>&nbsp;&nbsp;${sessionScope.memberVO.memberNickName} 님 
		         &nbsp; [ ${sessionScope.memberVO.memberEmail} ] </a>
		          <ul class="dropdown-menu">
		            <li><a href="#" class="memberUpate top-munu"> 회원 정보 수정</a></li>
		            <li class="divider"></li>
		            <li><a href="mypage.neon?memberEmail=${sessionScope.memberVO.memberEmail}" class="top-menu">회원 정보 보기</a></li>
		            <li class="divider"></li>
		            <li><a href="${initParam.root}memberLogout.neon" id="logout" class="top-menu">로그아웃</a></li>
		             <li class="divider"></li>
		               <li><a href="#" class="memberDelete top-menu">회원탈퇴</a></li>
		          </ul>
		        </li>
  	 	 		<span><input type="hidden" id="memberUserEmail" value="${sessionScope.memberVO.memberEmail}"></span>
              <!--   <li><a href="#" class="memberDelete">회원탈퇴</a></li> -->
    			  </c:otherwise>		  
    			  </c:choose>
  	 	 	</c:otherwise>
  	 	 </c:choose>
    			  <!-- 완결글 보기를 누르면 추천순으로 정렬된다. -대협 -->
    			  <span><input type="hidden" id="memberUserEmail" value="${sessionScope.memberVO.memberEmail}"></span>
     		</ul>
        </div><!--/.navbar-collapse -->
      </div>
