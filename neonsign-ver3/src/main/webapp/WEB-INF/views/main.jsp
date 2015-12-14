<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="bestMainArticleSlide">
	<iframe id="bestMainArticleArea" src="${initParam.root}getMainBestList.neon" width="100%" height="460px;" scrolling="no" style="overflow: hidden; margin-top:50px;"></iframe>
</div>
<!-- 태그 소트 버튼 부분 -->
<div class="container tags-container">
	<c:forEach items="${requestScope.tagVOList}" var="tagList">
		<span>#${tagList.tagName}</span>
	</c:forEach>
</div>
<!--  태그 소트 버튼 끝 -->
<!-- 2015-12-14 대협추가 -->
<!-- script.js에서 게시판 종류를 구분하기위한 hidden -대협- -->
<input type="hidden" id="articleType" value="mainArticle">
<h2 class="itjaMainTitle">새로운 잇자!</h2>
<!-- 태그명을 받아 현재 선택한 태그를 표시한다. -대협 -->
<span id="getNowTagName"></span> 

<section id="pinBoot">
	<c:forEach var="newMainArticle" items="${requestScope.newMainArticleVOList}">
		<article class="white-panel newMainArticle">
			<!-- 2015-12-14 대협추가 -->
			<div class="readArticleBtn">
				<input type="hidden" id="articleType" value="mainArticle">
				<input type="hidden" id="newMainArticleNo" value="${newMainArticle.mainArticleNo}">
				<h4><a href="#">${newMainArticle.mainArticleTitle}</a></h4>
				<h6 class="category">
					${newMainArticle.tagName}
				</h6>
				<img src="${initParam.root}resources/uploadImg/articleBg/${newMainArticle.mainArticleImgVO.mainArticleImgName}" alt="">
					
				<!-- 2015-12-14 대협수정 -->
				<p class="card-content"/>
				<c:set var="newMainArticleContent" value="${newMainArticle.mainArticleContent}" />
				<p class="description">
					${newMainArticleContent}
				</p>
				<a href="mypage.neon?memberEmail=${newMainArticle.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="${newMainArticle.memberVO.memberNickName}님, ${newMainArticle.memberVO.rankingVO.memberGrade} PTS(${newMainArticle.memberVO.memberPoint} / ${newMainArticle.memberVO.rankingVO.maxPoint})" data-content="${newMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
				<span class="writersNickName">- ${newMainArticle.memberVO.memberNickName} -</span></a>
			</div>
			<div class="social-line social-line-visible" data-buttons="4" style="width:100%; margin-top:10px;">
					<button class="btn btn-social btn-pinterest" style="width:23%;">
						<span class="time_area">새로운<br>잇자!</span>
					</button>
					<button class="btn btn-social btn-twitter itja" style="width:23%;" >
						<c:set var="count" value="false" />
						<c:forEach var="itjaList" items="${sessionScope.memberVO.itjaMemberList}">
							<c:choose>
								<c:when test="${itjaList.mainArticleNo== newMainArticle.mainArticleNo}">
									<c:set var="count" value="true" />
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:forEach>
							<c:choose>
								<c:when test="${count==true}">
									<span class="itjaCount"><i class="fa fa-link"></i><br>${newMainArticle.mainArticleTotalLike }it</span>
								</c:when>
								<c:otherwise>
									<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>${newMainArticle.mainArticleTotalLike }it</span>
								</c:otherwise>
							</c:choose>
					</button>
					<!-- 2015-12-14 대협수정 -->
					<button class="btn btn-social btn-google pickBtn" style="width:23%;">
						<c:set var="breakCheck" value="false"/>
						<c:forEach var="pickCheck" items="${sessionScope.memberVO.pickedVOList}">
							<c:choose>
								<c:when test="${pickCheck.mainArticleNo == newMainArticle.mainArticleNo}">
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
						<input type="hidden" name="mainArticleNo" value="${newMainArticle.mainArticleNo}">
						<input type="hidden" name="subArticleNo" value=0>
					</form>
					<%--
					   잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
					 --%>
					<!-- 찜 정보를 전달하기 위한 폼 시작 -->
					<form name="pickInfo">
						<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
						<input type="hidden" name="mainArticleNo" value="${newMainArticle.mainArticleNo}">
					</form>
					 <!-- 찜 정보를 전달하기 위한 폼 끝 -->
					      
					      
				</div>
			<!-- end social-line social-line-visible -->
		</article>
	</c:forEach>
</section>
<!-- /container -->
