<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- 부트스트랩 -->
<link href="${initParam.root}resources/css/bootstrap.css" rel="stylesheet">
<script src="${initParam.root}resources/js/ie-emulation-modes-warning.js"></script>
 <!-- 슬라이드 쇼를 위한 flickity api -->
<script src="${initParam.root}resources/js/flickity.pkgd.min.js"></script>
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- 부트스트랩 사용을 위한 상단 설정 완료 -->
<!-- 힙스터 카드 css -->
 <link href="${initParam.root}resources/css/hipster_cards.css" rel="stylesheet"/> 
 <!-- 슬라이드 쇼를 위한 flickity api -->
<link rel="stylesheet" href="${initParam.root}resources/css/flickity.css" media="screen">
<link href="${initParam.root}resources/css/style.css" rel="stylesheet">
<!-- cutomize 하는 css -->
 <link href="${initParam.root}resources/css/main.css" rel="stylesheet"/> 
 <!-- 아이콘 만들기 api font-awesome -->
 <link href="${initParam.root}resources/css/font-awesome.min.css" rel="stylesheet" />
 
 <!-- 잇자 또는 공지를 사이트 측면에서 띄워주는 간이모달 CSS -->
 <link rel="stylesheet" type="text/css" href="${initParam.root}resources/css/toasty-min.css">

</head>


<body>
<div class="itjaSlide">
<div class="bestMainArticle">
   <h2 class="itjaMainTitle">베스트 잇자!</h2>
   <div class="container-fluid">
      <div class="gallery js-flickity"
         data-flickity-options='{ "freeScroll": false, "wrapAround": true ,"pageDots": false, "autoPlay" : 2000}'>
         <!-- el 문 및 ajax로 베스트글이 표시되는 슬라이드 지역 -->
         <!-- 카드 1개 -->
         <c:forEach var="bestMainArticle"  items="${requestScope.bestMainArticleVOListOrderByDate}">
            <div class="card-box col-lg-3">
               <div class="card card-with-border" data-background="image"
                  data-src="${initParam.root}resources/uploadImg/articleBg/${bestMainArticle.mainArticleImgVO.mainArticleImgName}">
                  <div class="content">
                     <h6 class="category">
                         ${bestMainArticle.tagName}
                     </h6>
                     <br><input type="hidden" class="bestMainArticleNo" value="${bestMainArticle.mainArticleNo}">
                     <c:set var="bestArticleContentTitle"
                     value="${bestMainArticle.mainArticleTitle}" />
                     <h5 class="title">
                     <!-- 카드 간격을 맞추기위해 제목을 보여주는 글자수 제한 -대협- -->
                     <c:choose>
                        <c:when test="${fn:length(bestArticleContentTitle)>12}">
                           ${fn:substring(bestArticleContentTitle, 0, 11)} ...
                        </c:when>
                        <c:otherwise>
                           ${bestArticleContentTitle}
                        </c:otherwise>
                     </c:choose>
                     </h5>
                     <c:set var="bestArticleContentContent"
                     value="${bestMainArticle.mainArticleContent}" />
                     <p class="description">
                     <!-- 카드 간격을 맞추기위해 내용을 보여주는 글자수 제한 -대협- -->
                     <c:choose>
                        <c:when test="${fn:length(bestArticleContentContent)>18}">
                        ${fn:substring(bestArticleContentContent, 0, 15)} ...
                     </c:when>
                        <c:otherwise>
                        ${bestArticleContentContent}
                     </c:otherwise>
                     </c:choose>
                     </p>
                     <a href="mypage.neon?memberEmail=${bestMainArticle.memberVO.memberEmail}" target="_parent" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
                     data-toggle="popover" 
                     title="${bestMainArticle.memberVO.memberNickName}님, ${bestMainArticle.memberVO.rankingVO.memberGrade} PTS(${bestMainArticle.memberVO.memberPoint} / ${bestMainArticle.memberVO.rankingVO. maxPoint})" 
                     data-content="${bestMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
                     <span class="writersNickName">- ${bestMainArticle.memberVO.memberNickName} -</span>
                     </a>
                     <input type="hidden" class="mainArticleTitleNO" value="${bestMainArticle.mainArticleNo}">
                     <input type="hidden" class="loginMemberEmail" value="${sessionScope.memberVO.memberEmail}">
                     <div class="actions">
                        <button class="btn btn-round btn-fill btn-neutral btn-modern"
                           data-toggle="modal" data-target="#cardDetailView">
                           Read Article</button>
                     </div>
                  </div>
                  <input type="hidden" value="${bestMainArticle.mainArticleUpdateDate}" class="updateDate" name="card${bestMainArticle}">
                  <div class="social-line social-line-visible" data-buttons="4">
                     <button class="btn btn-social btn-pinterest">
                        <span class="time_area"></span>
                     </button>
                     <button class="btn btn-social btn-twitter bestItja">
                                <c:set var="count" value="false" />
                        <c:forEach var="itjaList" items="${sessionScope.memberVO.itjaMemberList}">
                           <c:choose>
                              <c:when test="${itjaList.mainArticleNo== bestMainArticle.mainArticleNo}">
                                 <c:set var="count" value="true" />
                              </c:when>
                              <c:otherwise>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                        <c:choose>
                           <c:when test="${count==true}">
                              <span class="itjaCount"><i class="fa fa-link"></i><br>${bestMainArticle.mainArticleTotalLike }it</span>
                           </c:when>
                           <c:otherwise>
                              <span class="itjaCount"><i class="fa fa-chain-broken"></i><br>${bestMainArticle.mainArticleTotalLike }it</span>
                           </c:otherwise>
                        </c:choose>
                            </button>
                                <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
                               주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다.
                             --%>
                     
                              
                            <form name="itJaInfo">
                               <input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
                               <input type="hidden" name="mainArticleNo" value="${bestMainArticle.mainArticleNo}">
                               <input type="hidden" name="subArticleNo" value=0>
                            </form>
                            <%--
                               잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
                             --%>
                             
                     <button class="btn btn-social btn-google staticPick">
                           <c:set var="breakCheck" value="false"/>
                     <c:forEach var="pickCheck" items="${sessionScope.memberVO.pickedVOList}">
                     <c:choose>
                        <c:when test="${pickCheck.mainArticleNo == bestMainArticle.mainArticleNo}">
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
                           <input type="hidden" name="mainArticleNo" value="${bestMainArticle.mainArticleNo}">
                      	</form>
                        <!-- 찜 정보를 전달하기 위한 폼 끝 -->
                        
                        
                     <button class="btn btn-social btn-facebook">
                        <i class="fa fa-facebook-official"></i><br> 공유!
                     </button>
                  </div>
                  <!-- end social-line social-line-visible -->
                  <div class="filter"></div>
               </div>
               <!-- end card -->
            </div>
            <!-- card-box col-md-4 -->
         </c:forEach>
         <!--끝!! 카드 1개 -->
      </div>
      <!--  end gallery js-flickity -->
   </div>
   <!-- end container -->
   </div>
</div>
<!-- end jumbotron itjaSlide -->

<!-- 끝!! el 문 및 ajax로 베스트글이 표시되는 슬라이드 지역 -->

<!-- end jumbotron itjaSlide -->
<!-- 글 내용이 출력되는 모달 창 -->
<!-- Modal -->
<div class="modal fade" id="cardDetailView" tabindex="-1" role="dialog"
	aria-labelledby="cardDetailViewLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="cardDetailViewLabel"></h4>
				<span id="timeAreaModalBest"></span>
				<input type="hidden" name="memberEmail" value="${sesseionScope.memberVO.memberEmail}">
				<input type="hidden" name="mainArticleNo" value="">
				
			</div>
			<div class="modal-body detailView">
				<div class= "detailLeft">
					<div class="panelForDetail panelForDetail-white postForDetail panelForDetail-shadow" >
					<h4 class="modal-title cardDetailViewTitle" id="cardDetailViewLabel"></h4>
						<div class="mainArticleContentsInModal">
							<div class="mainArticleContentInModal">
								<a class="postForDetail-description mainArticleWriterDetail" data-toggle="collapse" data-target="" aria-expanded="false" aria-controls="collapseExample1">
								<span class="mainArticleContent">
								여기에 이야기가 써지고 이야기를 읽으면서 내용을 클릭하면 클쓴이의 정보를 보거나 추천,신고등을 할 수 있다.
								</span><br>
								</a>
								<div class="writersNickNameAtDetail"><br></div>
		           				<div class="collapse postForDetail-heading mainArticleWriterDetailCollapse" id="">
		                			<div class="pull-left imageForDetail">
		                    			<img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatarForDetail" alt="user profile image">
		                			</div>
		               				<div class="pull-left metaForDetail">
			                    		<div class="titleForDetail h5 ">
			                       			<a href="#" class="writersNickNameAtDetail" target="_parent"><b></b></a>
			                   			</div>
		                    			<h6 class="text-muted timeForDetail"></h6>
		                    			<a href="#" class="btn btn-default statForDetail-item mainLikeIt">
				                    	</a>
				                    	<a href="#" class="btn btn-default statForDetail-item report">
				                    	</a>
		               				</div>
		          				</div>
		          			</div>
		          				<div class="linkingSubArticleContentInModal">
		          				</div> 
          				</div>
          				<div class="detailViewModalUtility">
	          				
          				</div>
        			</div>
    		</div>
				<div class= "detailRight">
					<div class="panelForDetail panelForDetail-white postForDetail panelForDetail-shadow" >
						<div class="unLinkingSubArticleList">
							
          				</div>
						<DIV class="itjaWriteForm">
							<form action="auth_writeSubArticle.neon" class="form-horizontal">
								
							</form>
						</DIV>
					</div>
				</div>
			</div>	
				<div class="modal-footer">
					<input type="hidden" value="best" id="isComplete">
				</div>
			</div>
		</div>
	</div>
<!-- 부트 스트랩 사용을 위한 하단 설정 -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${initParam.root}resources/js/jquery.js"></script>
<script src="${initParam.root}resources/js/script.js"></script>
 <!-- 잇자 또는 공지를 사이트 측면에서 띄워주는 간이모달 js -->
<script src="${initParam.root}resources/js/toasty-min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${initParam.root}resources/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script	src="${initParam.root}resources/js/ie10-viewport-bug-workaround.js"></script>
<!-- 부트 스트랩 사용을 위한 하단 설정 완료 -->
<!-- 힙스터 카드 js 파일 -->
<script src="${initParam.root}resources/js/hipster-cards.js"></script>
<script type="text/javascript">
</script>

</body>
</html>