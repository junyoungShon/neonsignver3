<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 2015-12-19 대협수정 -->
<!-- 태그 소트 버튼 부분 -->
<div class="container tags-container" style="margin-top:100px;">
	<h2 class="itjaMainTitle">인기 태그 best5</h2>
	<c:forEach items="${requestScope.tagVOList}" var="tagList" begin="0" end="4">
		<span class="tagName">#${tagList.tagName}</span><span class="tagCount" style="display: none; color:gray">${tagList.searchCount}</span> 
	</c:forEach>
	<br><br><span id="selectedTagCount" style="display: none; color:gray">${tagList.searchCount}</span> 
</div>
	<div class="" style="width:400px; margin:0 auto;">
	    <div class="ui-widget">
	    <span class="input-group-btn">
			<input type="text" class="form-control tagSearcher"  id="search" name="search" placeholder="태그 검색해보세요!" style="display:inline; margin:0 auto; margin-top:20px;">
            <button type="button" class="btn btn-default" id="tagSerch_result"><span class="fa fa-search"></span></button>
	    </span>
			  <label for="tagSearcher">Tags: </label>
		</div>
		<span class="selectedTagName"></span>
	</div>
<!--  태그 소트 버튼 끝 -->
<%-- <a href="report.neon">검색어 순위보기</a>
<table class="table table-hover">
<thead align="center">
	<tr>
		<td>순위</td>
		<td>검색어</td>
		<td>조회수</td>
	</tr>
</thead>
<tbody align="center">
<c:forEach items="${requestScope.aoplist}" var ="aopList">	
<tr>
<td>${aopList.RANKING } </td>
<td>${aopList.KEYWORD } </td>
<td>${aopList.CNT } </td> 
</tr>
</c:forEach>
</tbody>
</table>	 --%>
<!-- 2015-12-19 대협추가 -->
<h2 class="itjaMainTitle">'${requestScope.map.selector}' 키워드로<br> '${requestScope.map.keyword}' 검색 결과</h2>
<!--  태그 소트 버튼 끝 -->
<section id="pinBoot">
	<c:forEach var="searchList" items="${requestScope.list}">
		<article class="white-panel searchMainArticle">
			<!-- 2015-12-14 대협추가 -->
			<div class="readArticleBtn">
				<input type="hidden" id="articleType" value="searchArticle">
				<!-- 2015-12-19 대협추가 -->
				<input type="hidden" id="keyword" value="${requestScope.map.keyword}">
				<input type="hidden" id="selector" value="${requestScope.map.selector}">
				<input type="hidden" id="searchMainArticleNo" value="${searchList.mainArticleNo}">
				<h4><a href="#">${searchList.mainArticleTitle}</a></h4>
				<h6 class="category">
					${searchList.tagName}
				</h6>
				<img src="${initParam.root}resources/uploadImg/articleBg/${searchList.mainArticleImgVO.mainArticleImgName}" alt="">
					
				<!-- 2015-12-14 대협수정 -->
				<p class="card-content"/>
				<c:set var="newMainArticleContent" value="${searchList.mainArticleContent}" />
				<p class="description">
					${newMainArticleContent}
				</p>
				<!-- 2015-12-19 대협수정 -->
				<a href="mypage.neon?memberEmail=${searchList.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="${searchList.memberVO.memberNickName}님, ${searchList.memberVO.rankingVO.memberGrade} PTS(${searchList.memberVO.memberPoint} / ${searchList.memberVO.rankingVO.maxPoint})" data-content="${searchList.memberVO.memberNickName}님 Click하여 페이지 보기" >
				<span class="writersNickName">- ${searchList.memberVO.memberNickName} -</span></a>
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
						<input type="hidden" name="mainArticleNo" value="${searchList.mainArticleNo}">
						<input type="hidden" name="subArticleNo" value=0>
					</form>
					<%--
					   잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
					 --%>
					<!-- 찜 정보를 전달하기 위한 폼 시작 -->
					<form name="pickInfo">
						<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
						<input type="hidden" name="mainArticleNo" value="${searchList.mainArticleNo}">
					</form>
					 <!-- 찜 정보를 전달하기 위한 폼 끝 -->
					      
					      
				</div>
			<!-- end social-line social-line-visible -->
		</article>
	</c:forEach>
</section>
<!-- /container -->
