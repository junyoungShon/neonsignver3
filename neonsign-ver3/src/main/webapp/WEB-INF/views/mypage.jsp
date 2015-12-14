<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

   
   
   
<!-- 마이페이지  -->
<div class="container-fluid">
<div class="col-lg-12 col-sm-12 myprofile">
    <div class="profileCard hovercard">
        <div class="profileCard-background">
            <img class="profileCard-bkimg" alt="" src="http://lorempixel.com/100/100/people/9/">
            <!-- http://lorempixel.com/850/280/people/9/ -->
        </div>
        <div class="useravatar">
       		<img src="http://vignette1.wikia.nocookie.net/pokemon/images/b/b4/%EB%A1%9C%EC%82%AC%EC%9D%98_%EC%95%84%EB%B3%B4%ED%81%AC.png/revision/latest?cb=20110121121520&path-prefix=ko" >
        </div>
        <div class="profileCard-info">
         <span class="profileCard-title">
         ${requestScope.rankMemberVO.memberNickName}님 [${requestScope.rankMemberVO.memberEmail}]
         </span>
         </div>
         <div class="profileCard-titleGrade">
         <span class="profileCard-gradePoint">
         <c:forEach var="rankingList" items="${requestScope.rankingVOList}">
         <c:if test="${rankingList.memberGrade == requestScope.rankMemberVO.rankingVO.memberGrade}">
            <img class="gradeImg" src="${initParam.root}resources/img/GRADE_${requestScope.rankMemberVO.rankingVO.memberGrade}.png">
         </c:if>
      </c:forEach>
      ${requestScope.rankMemberVO.rankingVO.memberGrade} 
         PTS(${requestScope.rankMemberVO.memberPoint} / ${requestScope.rankMemberVO.rankingVO.maxPoint})
         </span>
         </div>
        <!-- 우측 팔로우 표시 -->
        <!-- 우측 팔로우 표시 끝 -->
    </div>
    
    <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button type="button" id="stars" class="btn btn-primary" href="#tab1" data-toggle="tab"><i class="fa fa-th-large"></i>
                <div class="hidden-xs">잇자! Action Info</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><i class="fa fa-child"></i>
                <div class="hidden-xs">Profile</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="following" class="btn btn-default" href="#tab3" data-toggle="tab"><i class="fa fa-users"></i>
                <div class="hidden-xs">Scription Info</div>
            </button>
        </div>
    </div>

        <div class="well">
      <div class="tab-content">
        <div class="tab-pane fade in active" id="tab1">
          <!-- 찜한 주제글 -->
          <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="itjaSlide">
      <h2 class="itjaMainTitle">${requestScope.rankMemberVO.memberNickName}이 찜한 주제글!<br></h2>
      <div class="container-fluid">
        <div class="gallery js-flickity" data-flickity-options='{ "freeScroll": true, "wrapAround": true ,"pageDots": false}'>
        <!-- 카드 1개 -->
          <c:forEach var="pickMainArticle" items="${requestScope.pickedMainArticleList}">
            <div class="card-box col-lg-2">  
                <div class="card card-with-border" data-background="image" data-src="${initParam.root}resources/uploadImg/articleBg/${pickMainArticle.mainArticleImgVO.mainArticleImgName}">    
                    <div class="content">
                        <h6 class="category">
                            ${pickMainArticle.tagName}
                        </h6>
                        <br>
                        <c:set var="pickMainArticleContentTitle"
                     value="${pickMainArticle.mainArticleTitle}" />
                        <h5 class="title">
                        <!-- 카드 간격을 맞추기위해 제목을 보여주는 글자수 제한 -대협- -->
	                     <c:choose>
	                        <c:when test="${fn:length(pickMainArticleContentTitle)>12}">
	                           ${fn:substring(pickMainArticleContentTitle, 0, 11)} ...
	                        </c:when>
	                        <c:otherwise>
	                           ${pickMainArticleContentTitle}
	                        </c:otherwise>
	                     </c:choose>
                        </h5>
                        <c:set var="pickMainArticleContentContent"
                     value="${pickMainArticle.mainArticleContent}" />
                        <p class="description">
                         <!-- 카드 간격을 맞추기위해 내용을 보여주는 글자수 제한 -대협- -->
	                     <c:choose>
	                        <c:when test="${fn:length(pickMainArticleContentContent)>18}">
	                        ${fn:substring(pickMainArticleContentContent, 0, 15)} ...
	                     </c:when>
	                        <c:otherwise>
	                        ${pickMainArticleContentContent}
	                     </c:otherwise>
	                     </c:choose>
                        </p>
                   <a href="mypage.neon?memberEmail=${pickMainArticle.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
               data-toggle="popover" 
               title="${pickMainArticle.memberVO.memberNickName}님, ${pickMainArticle.memberVO.rankingVO.memberGrade} PTS(${pickMainArticle.memberVO.memberPoint} / ${pickMainArticle.memberVO.rankingVO. maxPoint})" 
               data-content="${pickMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
                     <span class="writersNickName">- ${pickMainArticle.memberVO.memberNickName} -</span>
                     </a>
                     <input type="hidden" class="mainArticleTitleNO" value="${pickMainArticle.mainArticleNo}">
                     <input type="hidden" class="loginMemberEmail" value="${sessionScope.memberVO.memberEmail}">
                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
                                Read Article
                            </button>
                        </div>
                    </div>
                    
                    <div class="social-line social-line-visible" data-buttons="4">
                            <button class="btn btn-social btn-pinterest">
                                 내가<br>
                                 찜한글
                            </button>
                    <button class="btn btn-social btn-twitter bestItja">
                                <c:set var="count" value="false" />
                        <c:forEach var="itjaList" items="${sessionScope.memberVO.itjaMemberList}">
                           <c:choose>
                              <c:when test="${itjaList.mainArticleNo== pickMainArticle.mainArticleNo}">
                                 <c:set var="count" value="true" />
                              </c:when>
                              <c:otherwise>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                        <c:choose>
                           <c:when test="${count==true}">
                              <span class="itjaCount"><i class="fa fa-link"></i><br>${pickMainArticle.mainArticleTotalLike }it</span>
                           </c:when>
                           <c:otherwise>
                              <span class="itjaCount"><i class="fa fa-chain-broken"></i><br>${pickMainArticle.mainArticleTotalLike }it</span>
                           </c:otherwise>
                        </c:choose>
                            </button>
                                <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
                               주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다.
                             --%>
                     
                              
                            <form name="itJaInfo">
                               <input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
                               <input type="hidden" name="mainArticleNo" value="${pickMainArticle.mainArticleNo}">
                               <input type="hidden" name="subArticleNo" value=0>
                            </form>
                            <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
                             --%>
                             
                            
                        <button class="btn btn-social btn-google staticPick">
                           <c:set var="breakCheck" value="false"/>
                     <c:forEach var="pickCheck" items="${sessionScope.memberVO.pickedVOList}">
                     <c:choose>
                        <c:when test="${pickCheck.mainArticleNo == pickMainArticle.mainArticleNo}">
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
                           <input type="hidden" name="mainArticleNo" value="${pickMainArticle.mainArticleNo}">
                      	</form>
                        <!-- 찜 정보를 전달하기 위한 폼 끝 -->
                        
                        <button class="btn btn-social btn-facebook">
                               <i class="fa fa-facebook-official"></i><br>
                                 공유!
                        </button>
                    </div>  <!-- end social-line social-line-visible -->
                  <div class="filter"></div>
                </div> <!-- end card -->
            </div><!-- card-box col-md-4 -->
            </c:forEach>
            <!--끝!! 카드 1개 -->
      </div><!--  end gallery js-flickity -->
      </div><!-- end container -->
    </div><!-- end jumbotron itjaSlide -->
    <!-- 끝!! 찜한 주제글 --> 
    
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="itjaSlide">
      <h2 class="itjaMainTitle">${requestScope.rankMemberVO.memberNickName}이 작성한 주제글!<br></h2>
        <div class="container-fluid">
        <div class="gallery js-flickity" data-flickity-options='{ "freeScroll": true, "wrapAround": true ,"pageDots": false}'>
            <!-- el 문 및 ajax로 베스트글이 표시되는 슬라이드 지역 --> 
            <!-- 카드 1개 -->
          <c:forEach var="writeMainArticle" items="${requestScope.writeMainArticleList}">
            <div class="card-box col-lg-2">  
                <div class="card card-with-border" data-background="image" data-src="${initParam.root}resources/uploadImg/articleBg/${writeMainArticle.mainArticleImgVO.mainArticleImgName}">    
                    <div class="content">
                        <h6 class="category">
                            ${writeMainArticle.tagName}
                        </h6>
                        <br>
                        <c:set var="writeMainArticleContentTitle"
                     value="${writeMainArticle.mainArticleTitle}" />
                        <h5 class="title">
	 					 <!-- 카드 간격을 맞추기위해 제목을 보여주는 글자수 제한 -대협- -->
	                     <c:choose>
	                        <c:when test="${fn:length(writeMainArticleContentTitle)>12}">
	                           ${fn:substring(writeMainArticleContentTitle, 0, 11)} ...
	                        </c:when>
	                        <c:otherwise>
	                           ${writeMainArticleContentTitle}
	                        </c:otherwise>
	                     </c:choose>
                        </h5>
                        <c:set var="writeMainArticleContentContent"
                     value="${writeMainArticle.mainArticleContent}" />
                        <p class="description">
                          <!-- 카드 간격을 맞추기위해 내용을 보여주는 글자수 제한 -대협- -->
	                     <c:choose>
	                        <c:when test="${fn:length(writeMainArticleContentContent)>18}">
	                        ${fn:substring(writeMainArticleContentContent, 0, 15)} ...
	                     </c:when>
	                        <c:otherwise>
	                        ${writeMainArticleContentContent}
	                     </c:otherwise>
	                     </c:choose>
                        </p>
                     <a href="mypage.neon?memberEmail=${writeMainArticle.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
               data-toggle="popover" 
               title="${writeMainArticle.memberVO.memberNickName}님, ${writeMainArticle.memberVO.rankingVO.memberGrade} PTS(${writeMainArticle.memberVO.memberPoint} / ${writeMainArticle.memberVO.rankingVO. maxPoint})" 
               data-content="${writeMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
                     <span class="writersNickName">- ${writeMainArticle.memberVO.memberNickName} -</span>
                     </a>
                     <input type="hidden" class="mainArticleTitleNO" value="${writeMainArticle.mainArticleNo}">
                     <input type="hidden" class="loginMemberEmail" value="${sessionScope.memberVO.memberEmail}">
                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
                                Read Article
                            </button>
                        </div>
                    </div>
                    
                    <div class="social-line social-line-visible" data-buttons="4">
                            <button class="btn btn-social btn-pinterest">
                                나의<br>
                                 주제글
                            </button>
                         <button class="btn btn-social btn-twitter bestItja">
                                <c:set var="count" value="false" />
                        <c:forEach var="itjaList" items="${sessionScope.memberVO.itjaMemberList}">
                           <c:choose>
                              <c:when test="${itjaList.mainArticleNo== writeMainArticle.mainArticleNo}">
                                 <c:set var="count" value="true" />
                              </c:when>
                              <c:otherwise>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                        <c:choose>
                           <c:when test="${count==true}">
                              <span class="itjaCount"><i class="fa fa-link"></i><br>${writeMainArticle.mainArticleTotalLike }it</span>
                           </c:when>
                           <c:otherwise>
                              <span class="itjaCount"><i class="fa fa-chain-broken"></i><br>${writeMainArticle.mainArticleTotalLike }it</span>
                           </c:otherwise>
                        </c:choose>
                            </button>
                                <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
                               주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다.
                             --%>
                     
                              
                            <form name="itJaInfo">
                               <input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
                               <input type="hidden" name="mainArticleNo" value="${writeMainArticle.mainArticleNo}">
                               <input type="hidden" name="subArticleNo" value=0>
                            </form>
                            <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
                             --%>
                             
                            
                        <button class="btn btn-social btn-google staticPick">
                           <c:set var="breakCheck" value="false"/>
                     <c:forEach var="pickCheck" items="${sessionScope.memberVO.pickedVOList}">
                     <c:choose>
                        <c:when test="${pickCheck.mainArticleNo == writeMainArticle.mainArticleNo}">
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
                           <input type="hidden" name="mainArticleNo" value="${writeMainArticle.mainArticleNo}">
                      	</form>
                        <!-- 찜 정보를 전달하기 위한 폼 끝 -->
                        
                        <button class="btn btn-social btn-facebook">
                               <i class="fa fa-facebook-official"></i><br>
                                 공유!
                        </button>
                    </div>  <!-- end social-line social-line-visible -->
                  <div class="filter"></div>
                </div> <!-- end card -->
            </div><!-- card-box col-md-4 -->
            </c:forEach>
            <!--끝!! 카드 1개 -->
      </div><!--  end gallery js-flickity -->
      </div><!-- end container -->
    </div><!-- end jumbotron itjaSlide -->
    <!-- 끝 작성한 주제글 -->
    
    <!-- 참여, 이은 주제글 -->
          <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="itjaSlide">
      <h2 class="itjaMainTitle">${requestScope.rankMemberVO.memberNickName}이 이은 주제글!<br></h2>
      <div class="container-fluid">
        <div class="gallery js-flickity" data-flickity-options='{ "freeScroll": true, "wrapAround": true ,"pageDots": false}'>
            <!-- el 문 및 ajax로 베스트글이 표시되는 슬라이드 지역 --> 
            <!-- 카드 1개 -->
            <c:forEach var="joinMainArticle" items="${requestScope.joinMainArticleList}">
            <div class="card-box col-lg-2">  
                <div class="card card-with-border" data-background="image" data-src="${initParam.root}resources/uploadImg/articleBg/${joinMainArticle.mainArticleImgVO.mainArticleImgName}">    
                    <div class="content">
                        <h6 class="category">
                            ${joinMainArticle.tagName}
                        </h6>
                        <br>
                        <c:set var="joinMainArticleContentTitle"
                     value="${joinMainArticle.mainArticleTitle}" />
                        <h5 class="title">
						<!-- 카드 간격을 맞추기위해 제목을 보여주는 글자수 제한 -대협- -->
	                     <c:choose>
	                        <c:when test="${fn:length(joinMainArticleContentTitle)>12}">
	                           ${fn:substring(joinMainArticleContentTitle, 0, 11)} ...
	                        </c:when>
	                        <c:otherwise>
	                           ${joinMainArticleContentTitle}
	                        </c:otherwise>
	                     </c:choose>
                        </h5>
                        <c:set var="joinMainArticleContentContent"
                     value="${joinMainArticle.mainArticleContent}" />
                        <p class="description">
                          <!-- 카드 간격을 맞추기위해 내용을 보여주는 글자수 제한 -대협- -->
	                     <c:choose>
	                        <c:when test="${fn:length(joinMainArticleContentContent)>18}">
	                        ${fn:substring(joinMainArticleContentContent, 0, 15)} ...
	                     </c:when>
	                        <c:otherwise>
	                        ${joinMainArticleContentContent}
	                     </c:otherwise>
	                     </c:choose>
                        </p>
                     <a href="mypage.neon?memberEmail=${joinMainArticle.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
               data-toggle="popover" 
               title="${joinMainArticle.memberVO.memberNickName}님, ${joinMainArticle.memberVO.rankingVO.memberGrade} PTS(${joinMainArticle.memberVO.memberPoint} / ${joinMainArticle.memberVO.rankingVO. maxPoint})" 
               data-content="${joinMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
                     <span class="writersNickName">- ${joinMainArticle.memberVO.memberNickName} -</span>
                     </a>
                     <input type="hidden" class="mainArticleTitleNO" value="${joinMainArticle.mainArticleNo}">
                     <input type="hidden" class="loginMemberEmail" value="${sessionScope.memberVO.memberEmail}">
                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
                                Read Article
                            </button>
                        </div>
                    </div>
                    
                    <div class="social-line social-line-visible" data-buttons="4">
                            <button class="btn btn-social btn-pinterest">
                                 나의<br>
                                 참여글
                            </button>
                          <button class="btn btn-social btn-twitter bestItja">
                                <c:set var="count" value="false" />
                        <c:forEach var="itjaList" items="${sessionScope.memberVO.itjaMemberList}">
                           <c:choose>
                              <c:when test="${itjaList.mainArticleNo== joinMainArticle.mainArticleNo}">
                                 <c:set var="count" value="true" />
                              </c:when>
                              <c:otherwise>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                        <c:choose>
                           <c:when test="${count==true}">
                              <span class="itjaCount"><i class="fa fa-link"></i><br>${joinMainArticle.mainArticleTotalLike }it</span>
                           </c:when>
                           <c:otherwise>
                              <span class="itjaCount"><i class="fa fa-chain-broken"></i><br>${joinMainArticle.mainArticleTotalLike }it</span>
                           </c:otherwise>
                        </c:choose>
                            </button>
                                <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
                               주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다.
                             --%>
                            <form name="itJaInfo">
                               <input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
                               <input type="hidden" name="mainArticleNo" value="${joinMainArticle.mainArticleNo}">
                               <input type="hidden" name="subArticleNo" value=0>
                            </form>
                            <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
                             --%>
                             
                            
                        <button class="btn btn-social btn-google staticPick">
                           <c:set var="breakCheck" value="false"/>
                     <c:forEach var="pickCheck" items="${sessionScope.memberVO.pickedVOList}">
                     <c:choose>
                        <c:when test="${pickCheck.mainArticleNo == joinMainArticle.mainArticleNo}">
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
                           <input type="hidden" name="mainArticleNo" value="${joinMainArticle.mainArticleNo}">
                      	</form>
                        <!-- 찜 정보를 전달하기 위한 폼 끝 -->
                        
                        <button class="btn btn-social btn-facebook">
                               <i class="fa fa-facebook-official"></i><br>
                                 공유!
                        </button>
                    </div>  <!-- end social-line social-line-visible -->
                  <div class="filter"></div>
                </div> <!-- end card -->
            </div><!-- card-box col-md-4 -->
            </c:forEach>
            <!--끝!! 카드 1개 -->
            
      </div><!--  end gallery js-flickity -->
      </div><!-- end container -->
    </div><!-- end jumbotron itjaSlide -->
    <!-- 끝 참여주제글 -->
        </div> <!-- Tab1 끝 -->
        
        
        
        
      <!-- Tab2 개인정보 시작 -->
      <div class="tab-pane fade in" id="tab2">
      
<div class="container">
	<div class="row">
		<div class="col-sm-3">
    	    <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-user user"></i>
                </div>
                <div class="text">
                	<c:set var="subscriptedCount" value="${fn:length(requestScope.subscriptedInfoList)}"/>
                    <var class="subscriptedCount">${subscriptedCount}</var>
                    
                    <label class="text-muted">SUBSCRIBERs</label>
                </div>
                <div class="options">
                    <button class="btn btn-primary btn-lg staticSubscriptionBtn">
                    <c:set var="breakCheck" value="false"/>
                     <c:forEach var="subscriptedCheck" items="${requestScope.subscriptedInfoList}">
	                     <c:choose>
	                        <c:when test="${subscriptedCheck.memberEmail == sessionScope.memberVO.memberEmail}">
                                <c:set var="breakCheck" value="true"/>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
	                     </c:choose>
                     </c:forEach>
                     
                     <c:choose>
                        <c:when test="${breakCheck == true}">
                            <span class="subscriptionInfoSpan">
		                 	   <i class="fa fa-minus-square"></i>&nbsp;구독 취소
		                    </span>
                        </c:when>
                        <c:otherwise>
                            <span class="subscriptionInfoSpan">
		                 	   <i class="fa fa-plus-square"></i>&nbsp;구독 하기
		                    </span>
                        </c:otherwise>
                     </c:choose>
                    </button>
					<!-- 구독 정보를 전달하기 위한 폼 시작 -->
					<form name="subscriptionInfo">
						<input type="hidden" name="publisher" value="${requestScope.rankMemberVO.memberEmail}">
						<input type="hidden" name="subscriber" value="${sessionScope.memberVO.memberEmail}">
					</form>
					<!-- 구독 정보를 전달하기 위한 폼 끝 -->
                </div>
            </div>
		</div>
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                    <i class="fa fa-link itja"></i>
                </div>
                <div class="text">
                    <var>${requestScope.rankMemberVO.memberPoint}</var>
                    <label class="text-muted">잇자 포인트</label>
                </div>
				<div class="options">
				<a class="btn btn-lg btn-danger popover2" title="뇌OnSign Grade" href='#'>
						<i class="fa fa-trophy"></i>&nbsp; Next Point
						${requestScope.rankMemberVO.rankingVO.maxPoint}</a>
						
						<div id="rankingPopover" style="display: none">
						<table class="rankingTable">
							<tr>
								<td>Grade</td><td>점수범위</td>
							</tr>
							<tr>
								<td class="gradeTd">
								<img class="gradeImg" src="${initParam.root}resources/img/GRADE_UNRANKED.png">
								UNRANKED
								</td>
								<td>0 ~ 49</td>
							</tr>
							<tr>
								<td class="gradeTd">
								<img class="gradeImg" src="${initParam.root}resources/img/GRADE_BRONZE.png">
								BRONZE
								</td>
								<td>50 ~ 149</td>
							</tr>
							<tr>
								<td class="gradeTd">
								<img class="gradeImg" src="${initParam.root}resources/img/GRADE_SILVER.png">
								SILVER
								</td>
								<td>150 ~ 349</td>
							</tr>
							<tr>
								<td class="gradeTd">
								<img class="gradeImg" src="${initParam.root}resources/img/GRADE_GOLD.png">
								GOLD
								</td>
								<td>350 ~ 749</td>
							</tr>
							<tr>
								<td class="gradeTd">
								<img class="gradeImg" src="${initParam.root}resources/img/GRADE_PLATINUM.png">
								PLATINUM
								</td>
								<td>750 ~ 1549</td>
							</tr>
							<tr>
								<td class="gradeTd">
								<img class="gradeImg" src="${initParam.root}resources/img/GRADE_DIAMOND.png">
								DIAMOND
								</td>
								<td>1500 ~ 상위 10명</td>
							</tr>
						</table>
						</div>
				</div>
			</div>
		</div>
		
		<div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon age">
	                <c:if test="${requestScope.joinAge==0}">
	                	<i class="fa fa-paper-plane"></i>
	                </c:if>
	                <c:if test="${requestScope.joinAge==1}">
	                	<i class="fa fa-rocket"></i>
	                </c:if>
	                <c:if test="${requestScope.joinAge==2}">
	                	<i class="fa fa-diamond"></i>
	                </c:if>
	                <c:if test="${requestScope.joinAge==3||requestScope.joinAge==4}">
	                	<i class="fa fa-gavel"></i>
	                </c:if>
	                <c:if test="${requestScope.joinAge==5||requestScope.joinAge==6}">
	                	<i class="fa fa-mixcloud"></i>
	                </c:if>
	                <c:if test="${requestScope.joinAge>6}">
	                	<i class="fa fa-linux"></i>
	                </c:if>
                </div>
                <div class="text">
                    <var>${requestScope.joinAge}</var>
                    <label class="text-muted">가입나이</label>
                </div>
                <div class="options">
                    <a href="javascript:;" class="btn btn-default btn-lg">
                    <i class="fa fa-fire"></i>&nbsp;&nbsp;since ${requestScope.rankMemberVO.memberJoinDate}</a>
                </div>
            </div>
		</div>
		
		<div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-star pick"></i>
                </div>
                <div class="text">
                    <var>${fn:length(requestScope.pickedMainArticleList)}</var>
                    <label class="text-muted">찜한게시물</label>
                </div>
                <div class="options">
                    <a href="mypage.neon?memberEmail=${requestScope.rankMemberVO.memberEmail}" class="btn btn-default btn-lg">
                    <i class="fa fa-desktop"></i>&nbsp;&nbsp;View Info</a>
                </div>
            </div>
    	</div>
    	
       	<div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-lightbulb-o write"></i>
                </div>
                <div class="text">
                    <var>${fn:length(requestScope.writeMainArticleList)} </var>
                    <label class="text-muted">작성한 잇는글</label>
                </div>
                <div class="options">
                   <a href="mypage.neon?memberEmail=${requestScope.rankMemberVO.memberEmail}" class="btn btn-default btn-lg"><i class="fa fa-desktop"></i>&nbsp;&nbsp;View Info</a>
                </div>
            </div>
		</div>
		
		
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-reply join"></i>
                </div>
                <div class="text">
                    <var>${fn:length(requestScope.joinMainArticleList)}</var>
                    <label class="text-muted">참여한 잇는글</label>
                </div>
                <div class="options">
                    <a href="mypage.neon?memberEmail=${requestScope.rankMemberVO.memberEmail}" class="btn btn-default btn-lg"><i class="fa fa-desktop"></i>&nbsp;&nbsp;View Info</a>
                </div>
            </div>
		</div>
		
		
		
		<div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-tags tags"></i>
                </div>
                <div class="text">
                    <var>${fn:length(writeTagListbyEmailList)}</var>
                    <label class="text-muted">작성 TAGs</label>
                </div>
                <div class="options">
                    <a href="javascript:;" class="btn btn-default btn-lg">Most&nbsp;<i class="fa fa-tag"></i>&nbsp;
                    <c:choose>
                    	<c:when test="${requestScope.tagBoardVO.tagName != null}">
                    		#${requestScope.tagBoardVO.tagName} ${requestScope.tagBoardVO.useTagCount}회
                    	</c:when>
                    	<c:otherwise>
                    		없음
                    	</c:otherwise>
                    </c:choose>
                    </a>
                </div>
            </div>
		</div>
		
		
		<div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-exclamation-triangle ready"></i>
                </div>
                <div class="text">
                    <var>-</var>
                    <label class="text-muted">준비중입니다</label>
                </div>
                <div class="options">
                    <a href="javascript:;" class="btn btn-default btn-lg"><i class="fa fa-wrench"></i></a>
                </div>
            </div>
		</div>
		
</div>
</div>

      </div> <!-- Tab2 끝 -->
        
       <!-- Tab3 구독 정보 시작 -->
        <div class="tab-pane fade in" id="tab3">
        
        
 			<div class="row">
				<!-- 내가 구독중인 현황 -->
                <div class="col-xs-6">
                	<p class="lead"><span class="text-danger">${requestScope.rankMemberVO.memberNickName}님이 구독하는 사람들</span></p>
					<span class="text-danger">
                      	<h2 class="subscriptingCount">${fn:length(requestScope.subscriptingInfoList)}명</h2>
                      	<span class="subscriptingInfo">
	                      	<c:forEach var="subscriptingInfoList" items="${requestScope.subscriptingInfoList}">
	                      		${subscriptingInfoList.memberNickName}<br>
	                      	</c:forEach>
	                     </span>
					</span>
                </div>
                  <!-- 나를 구독 하는 현황 -->
                  <div class="col-xs-6">
                      	<p class="lead"><span class="text-success">${requestScope.rankMemberVO.memberNickName}님을 구독하는 사람들</span></p>
						<span class="text-success">
	                      	<h2 class="subscriptedCount">${subscriptedCount}명</h2>
	                      	<span class="subscriptedInfo">
		                      	<c:forEach var="subscriptedInfoList" items="${requestScope.subscriptedInfoList}">
		                      		${subscriptedInfoList.memberNickName}<br>
		                      	</c:forEach>
	                      	</span>
	                    </span>
                  </div>
              </div>
        
        	

        </div> <!-- Tab3 끝 -->
      </div>
    </div>
    
    </div>
</div>

<!-- 여기까지 마이프로필 -->
   
   
    
    
    
    
    
    
    