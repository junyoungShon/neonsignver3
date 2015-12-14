<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 태그 소트 버튼 부분 -->
<div class="container tags-container" style="margin-top: 100px;">
   <c:forEach items="${requestScope.tagVOList}" var="tagList">
      <span>#${tagList.tagName}</span>
   </c:forEach>
</div>
<!--  태그 소트 버튼 끝 -->
<!-- script.js에서 게시판 종류를 구분하기위한 hidden -대협- -->
<input type="hidden" id="articleType" value="completeArticle">
<h2 class="itjaMainTitle">완결된 잇자!</h2>
<!-- 태그명을 받아 현재 선택한 태그를 표시한다. -대협 -->
<span id="getNowTagName"></span>
   
<!-- 2015-12-14 대협추가 -->
   <section id="pinBoot">
	<c:forEach var="completeMainArticle" items="${requestScope.completeMainArticleList}">
		<article class="white-panel">
			<h4><a href="#">${completeMainArticle.mainArticleTitle}</a></h4>
			<h6 class="category">
				${completeMainArticle.tagName}
			</h6>
			<img src="${initParam.root}resources/uploadImg/articleBg/${completeMainArticle.mainArticleImgVO.mainArticleImgName}" alt="">
			<p class="card-content"/>
			<c:set var="completeMainArticleContent" value="${completeMainArticle.mainArticleContent}" />
			<p class="description">
				${completeMainArticleContent}
			</p>
			<a href="mypage.neon?memberEmail=${completeMainArticle.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="${completeMainArticle.memberVO.memberNickName}님, ${completeMainArticle.memberVO.rankingVO.memberGrade} PTS(${completeMainArticle.memberVO.memberPoint} / ${completeMainArticle.memberVO.rankingVO.maxPoint})" data-content="${completeMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
			<span class="writersNickName">- ${completeMainArticle.memberVO.memberNickName} -</span></a>
			<div class="social-line social-line-visible" data-buttons="4" style="width:100%; margin-top: 10px;">
					<button class="btn btn-social btn-pinterest">
						<span class="time_area">완결된<br>잇자!</span>
					</button>
					<button class="btn btn-social btn-twitter itja" style="width:23%;">
						<c:set var="count" value="false" />
						<c:forEach var="itjaList" items="${sessionScope.memberVO.itjaMemberList}">
							<c:choose>
								<c:when test="${itjaList.mainArticleNo== completeMainArticle.mainArticleNo}">
									<c:set var="count" value="true" />
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
							<c:choose>
								<c:when test="${count==true}">
									<span class="itjaCount"><i class="fa fa-link"></i><br>${completeMainArticle.mainArticleTotalLike }it</span>
								</c:when>
								<c:otherwise>
									<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>${completeMainArticle.mainArticleTotalLike }it</span>
								</c:otherwise>
							</c:choose>
					</button>
					<button class="btn btn-social btn-google pickBtn" style="width:23%;">
						<c:set var="breakCheck" value="false"/>
						<c:forEach var="pickCheck" items="${sessionScope.memberVO.pickedVOList}">
							<c:choose>
								<c:when test="${pickCheck.mainArticleNo == completeMainArticle.mainArticleNo}">
									<c:set var="breakCheck" value="true"/>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${breakCheck == true}">
								<span class="pickSpan"><i class="fa fa-heart"></i><br>찜!</span>
							</c:when>
							<c:otherwise>
								<span class="pickSpan"><i class="fa fa-heart-o"></i><br>찜하자!</span>
							</c:otherwise>
						</c:choose>
					</button>
					<button class="btn btn-social btn-facebook" style="width:23%;">
						<i class="fa fa-facebook-official"></i><br> 공유!
					</button>
					            <%--
					  잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
					  주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다.
					--%>
					
					  
					<form name="itJaInfo">
						<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
						<input type="hidden" name="mainArticleNo" value="${completeMainArticle.mainArticleNo}">
						<input type="hidden" name="subArticleNo" value=0>
					</form>
					<%--
					   잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
					 --%>
					<!-- 찜 정보를 전달하기 위한 폼 시작 -->
					<form name="pickInfo">
						<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
						<input type="hidden" name="mainArticleNo" value="${completeMainArticle.mainArticleNo}">
					</form>
					 <!-- 찜 정보를 전달하기 위한 폼 끝 -->
					      
					      
				</div>
			<!-- end social-line social-line-visible -->
		</article>
	</c:forEach>
</section>
<!-- /container -->