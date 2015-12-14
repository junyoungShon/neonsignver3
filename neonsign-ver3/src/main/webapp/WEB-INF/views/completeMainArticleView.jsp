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
<div class="container">
   <!-- script.js에서 게시판 종류를 구분하기위한 hidden -대협- -->
   <input type="hidden" id="articleType" value="completeArticle">
   <h2 class="itjaMainTitle">완결된 잇자!</h2>
   <!-- 태그명을 받아 현재 선택한 태그를 표시한다. -대협 -->
   <span id="getNowTagName"></span>
   <!-- Example row of columns -->
   <div class="row completeItjaList">
      <c:forEach items="${requestScope.mainArticleList}" var="completeMainArticle">
         <!-- 카드 1개 -->
         <!-- name은 script.js에서 카드 현재 카드의 수를 구하기 위해 사용 -대협- -->
         <div class="card-box col-md-4" name="completeCardBox">
            <div class="card card-with-border" data-background="image"
               data-src="${initParam.root}resources/uploadImg/articleBg/${completeMainArticle.mainArticleImgVO.mainArticleImgName}">
               <div class="content">
                  <h6 class="category">
                      ${completeMainArticle.tagName}
                  </h6>
                  <br>
                  <c:set var="mainArticleContentTitle"
                     value="${completeMainArticle.mainArticleTitle}" />
                  <h5 class="title">
                     [완결]
                     <!-- 카드 간격을 맞추기위해 제목을 보여주는 글자수 제한 -대협- -->
                     <c:choose>
                        <c:when test="${fn:length(mainArticleContentTitle)>12}">
                           ${fn:substring(mainArticleContentTitle, 0, 11)} ...
                        </c:when>
                        <c:otherwise>
                           ${mainArticleContentTitle}
                        </c:otherwise>
                     </c:choose>
                  </h5>
                  <c:set var="mainArticleContentContent"
                     value="${completeMainArticle.mainArticleContent}" />
                  <p class="description">
                  <!-- 카드 간격을 맞추기위해 내용을 보여주는 글자수 제한 -대협- -->
                     <c:choose>
                        <c:when test="${fn:length(mainArticleContentContent)>18}">
                        ${fn:substring(mainArticleContentContent, 0, 15)} ...
                     </c:when>
                        <c:otherwise>
                        ${mainArticleContentContent}
                     </c:otherwise>
                     </c:choose>
                  </p>
               <a href="mypage.neon?memberEmail=${completeMainArticle.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
                     data-toggle="popover" 
                     title="${completeMainArticle.memberVO.memberNickName}님, ${completeMainArticle.memberVO.rankingVO.memberGrade} PTS(${completeMainArticle.memberVO.memberPoint} / ${completeMainArticle.memberVO.rankingVO. maxPoint})" 
                     data-content="${completeMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
                  <span class="writersNickName">- ${completeMainArticle.memberVO.memberNickName} -</span>
               </a>
                 <input type="hidden" class="mainArticleTitleNO" value="${completeMainArticle.mainArticleNo}">
                 <input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
                  <div class="actions">
                     <button class="btn btn-round btn-fill btn-neutral btn-modern"
                        data-toggle="modal" data-target="#cardDetailView">Read
                        Article</button>
                  </div>
               </div>
               <div class="social-line social-line-visible" data-buttons="4">
                  <button class="btn btn-social btn-pinterest">
                     완결된<br>잇자!
                  </button>
                   <button class="btn btn-social btn-twitter itja">
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
                            <form name="itJaInfo">
                               <input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
                               <input type="hidden" name="mainArticleNo" value="${completeMainArticle.mainArticleNo}">
                               <input type="hidden" name="subArticleNo" value=0>
                            </form>
                            
					<!-- 찜하기 -->
                  <button class="btn btn-social btn-google pickBtn">
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
                        <!-- 찜 정보를 전달하기 위한 폼 시작 -->
                        <form name="pickInfo">
                           <input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
                           <input type="hidden" name="mainArticleNo" value="${completeMainArticle.mainArticleNo}">
                      	</form>
                        <!-- 찜 정보를 전달하기 위한 폼 끝 -->
                           
                  <button class="btn btn-social btn-facebook">
                     <i class="fa fa-facebook-official"></i><br> 공유하자!
                  </button>
               </div>
               <!-- end social-line social-line-visible -->
               <div class="filter"></div>
            </div>
            <!-- end card -->
         </div>
         <!-- card-box col-md-4 -->
         <!--끝!! 카드 1개 -->
      </c:forEach>
   </div>
   <hr>
</div>
<!-- /container -->



