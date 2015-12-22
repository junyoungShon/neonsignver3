<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 마이페이지 -->
<div class="container-fluid">
	<!-- 마이페이지 틀 -->
	<div class="col-lg-12 col-sm-12 myprofile">
	
		<!-- 마이페이지 상단 정보 시작 -->
		<div class="profileCard hovercard">
			<!-- 마이페이지 상단 백그라운드 이미지 -->
			<div class="profileCard-background">
				<img class="profileCard-bkimg" alt="" src="http://lorempixel.com/100/100/people/9/">
			</div>
			<!-- 프로필 둥근 사진 -->
			<div class="useravatar">
				<img src="${initParam.root}resources/uploadImg/profileImg/${requestScope.rankMemberVO.memberProfileImgName}" >
			</div>
			<!-- 프로필 상단 정보 내용 -->
			<div class="profileCard-info">
				<span class="profileCard-title">
				${requestScope.rankMemberVO.memberNickName}님 [${requestScope.rankMemberVO.memberEmail}]
				</span>
			</div>
			<!-- 프로필 상단 좌측 등급 및 포인트-->
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
			<!-- 여기 -->
		</div>
		<!-- 마이페이지 상단 정보 끝 -->
		
		<!-- 우측 구독 정보 표시 -->
		<div class=subscriptionBox>
			<!-- 닉네임을 구독하는 정보 -->
			<div class="subscriptedInfo">
				<c:set var="subscriptedCount" value="${fn:length(requestScope.subscriptedInfoList)}"/>
				<span class="subscriptedList">
					<a class="btn btn-info popover3" title="" href='#'>
						${requestScope.rankMemberVO.memberNickName}님을<br>구독하는
					</a>
					<h2 class="subscriptedCountInfo">${subscriptedCount}</h2>
					<c:choose>
						<c:when test="${subscriptedCount==0}">
							<div id="subscriptedPopover" style="display: none">
								구독자가 없습니다.
							</div>
						</c:when>
						<c:otherwise>
							<div id="subscriptedPopover" style="display: none">
								<c:forEach var="subscriptedInfoList" items="${requestScope.subscriptedInfoList}">
									${subscriptedInfoList.memberNickName}<br>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
	
				</span>
			</div>
			<!-- 닉네임이 구독하는 정보 -->
			<div class="subscriptingInfo">
				<c:set var="subscriptingCount" value="${fn:length(requestScope.subscriptingInfoList)}"/>
				<span class="subscripingList">
					<a class="btn btn-danger popover4" title="" href='#'>
						${requestScope.rankMemberVO.memberNickName}님이<br>구독하는
					</a>
					<h2 class="subscriptingCount">${subscriptingCount}</h2>
					<c:choose>
						<c:when test="${subscriptingCount==0}">
							<div id="subscriptingPopover" style="display: none">
								구독자가 없습니다
							</div>
						</c:when>
						<c:otherwise>
							<div id="subscriptingPopover" style="display: none">
								<c:forEach var="subscriptingInfoList" items="${requestScope.subscriptingInfoList}">
									${subscriptingInfoList.memberNickName}<br>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
		</div>
		<!-- 우측 구독 정보 표시 끝 -->	
		
		
		<!-- 마이페이지 상단 3탭 시작 -->
		<div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
			<!-- 탭1 -->
			<div class="btn-group" role="group">
				<button type="button" id="stars" class="btn btn-primary" href="#tab1" data-toggle="tab"><i class="fa fa-th-large"></i>
					<div class="hidden-xs">잇자! Action Info</div>
				</button>
			</div>
			<!-- 탭2 -->
			<div class="btn-group" role="group">
				<button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><i class="fa fa-child"></i>
					<div class="hidden-xs">Profile</div>
				</button>
			</div>
			<!-- 탭3 -->
			<div class="btn-group" role="group">
				<button type="button" id="following" class="btn btn-default" href="#tab3" data-toggle="tab"><i class="fa fa-users"></i>
					<div class="hidden-xs">Scription Info</div>
				</button>
			</div>
		</div>
		<!-- 마이페이지 상단 3탭 끝 -->
		<!-- 마이페이지 탭 내용 틀 -->
		<div class="well">
			<!-- 마이페이지 탭 내용 틀2 -->
			<div class="tab-content">
				<!-- 마이페이지 탭1 내용 카드보여주기 -->
				<div class="tab-pane fade in active" id="tab1">
				
					<!-- ******************** 찜, 작성, 이은글 보기 ******************** -->
					<!-- 찜한 주제글 보여주기 시작 -->
					<div class="itjaSlide">
						<h2 class="itjaMainTitle">${requestScope.rankMemberVO.memberNickName}님이 찜한 주제글!<br></h2>
						<div class="container-fluid">
							<div class="gallery js-flickity"
         					data-flickity-options='{ "freeScroll": false, "wrapAround": true ,"pageDots": false, "autoPlay" : 2000}'>
								<!-- 찜 카드 for문 시작 -->
								<c:forEach var="pickMainArticle" items="${requestScope.pickedMainArticleList}">
									<!-- 찜 카드 틀 -->
									<div class="card-box col-lg-2">
										<!-- 찜 카드 이미지 틀 -->
										<div class="card card-with-border" data-background="image" data-src="${initParam.root}resources/uploadImg/articleBg/${pickMainArticle.mainArticleImgVO.mainArticleImgName}">    
											<!-- 찜카드 상단 컨텐츠들 -->
											<div class="content">
												<h6 class="category">${pickMainArticle.tagName}</h6>	<br>
												<c:set var="pickMainArticleContentTitle" value="${pickMainArticle.mainArticleTitle}" />
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
												<c:set var="pickMainArticleContentContent" value="${pickMainArticle.mainArticleContent}" />
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
												<a href="mypage.neon?memberEmail=${pickMainArticle.memberVO.memberEmail}" style="" 
												tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
												data-toggle="popover" 
												title="${pickMainArticle.memberVO.memberNickName}님, ${pickMainArticle.memberVO.rankingVO.memberGrade} PTS(${pickMainArticle.memberVO.memberPoint} / ${pickMainArticle.memberVO.rankingVO. maxPoint})" 
												data-content="${pickMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
													<span class="writersNickName">- ${pickMainArticle.memberVO.memberNickName} -</span>
												</a>
												<!-- 카드 안 히든 -->
												<input type="hidden" class="mainArticleTitleNO" value="${pickMainArticle.mainArticleNo}">
												<input type="hidden" class="loginMemberEmail" value="${sessionScope.memberVO.memberEmail}">
												<!-- 카드 안 히든 끝 -->
												<!-- 상세보기 -->
												<div class="actions">
													<button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
														Read Article
													</button>
												</div>
												<!-- 상세보기 끝 -->
											</div>
											<!-- 찜카드 상단 컨텐츠들 끝 -->
											<!-- 찜카드 하단 버튼들 -->
											<div class="social-line social-line-visible" data-buttons="4">
												<!-- 내가 찜한 글(타임체크) -->
												<button class="btn btn-social btn-pinterest">
													내가<br>	찜한글
												</button>
												<!-- 잇자 버튼 -->
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
												<%--	잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
														주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다 --%>
												<form name="itJaInfo">
													<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
													<input type="hidden" name="mainArticleNo" value="${pickMainArticle.mainArticleNo}">
													<input type="hidden" name="subArticleNo" value=0>
												</form>
												<%-- 잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝 --%>
												<!-- 찜 버튼 -->
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
												<!-- 공유 -->
												<button class="btn btn-social btn-primary btn-facebook sharePage"  
					data-toggle="modal" data-target="#shareModal" style="width:23%;">
						<i class="fa fa-facebook-official"></i><br> 공유!
					</button>
											</div>
											<!-- 찜카드 하단 버튼들 끝 -->
											<div class="filter"></div>
										</div>
										<!-- 찜 카드 이미지 틀 끝 -->
									</div>
									<!-- 찜 카드 틀 -->
								</c:forEach>
								<!-- 찜 카드 for문 끝 -->
							</div><!--  end gallery js-flickity -->
						</div><!-- end container -->
					</div><!-- end jumbotron itjaSlide -->
					<!-- 찜한 주제글 보여주기 끝 -->
					
					<!-- 작성 주제글 보여주기 시작 -->
					<div class="itjaSlide">
						<h2 class="itjaMainTitle">${requestScope.rankMemberVO.memberNickName}님이 작성한 주제글!<br></h2>
						<div class="container-fluid">
							<div class="gallery js-flickity"
         					data-flickity-options='{ "freeScroll": false, "wrapAround": true ,"pageDots": false, "autoPlay" : 2000}'>
								<!-- 작성 카드 for문 -->
								<c:forEach var="writeMainArticle" items="${requestScope.writeMainArticleList}">
									<!-- 작성 카드 틀 -->
									<div class="card-box col-lg-2">
										<!-- 작성 카드 이미지 틀 -->  
										<div class="card card-with-border" data-background="image" data-src="${initParam.root}resources/uploadImg/articleBg/${writeMainArticle.mainArticleImgVO.mainArticleImgName}">    
											<!-- 작성 카드 상단 컨텐츠들 -->
											<div class="content">
												<h6 class="category">${writeMainArticle.tagName}</h6><br>
												<c:set var="writeMainArticleContentTitle"	value="${writeMainArticle.mainArticleTitle}" />
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
												<c:set var="writeMainArticleContentContent"	value="${writeMainArticle.mainArticleContent}" />
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
												<a href="mypage.neon?memberEmail=${writeMainArticle.memberVO.memberEmail}" style="" 
												tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
												data-toggle="popover" 
												title="${writeMainArticle.memberVO.memberNickName}님, ${writeMainArticle.memberVO.rankingVO.memberGrade} PTS(${writeMainArticle.memberVO.memberPoint} / ${writeMainArticle.memberVO.rankingVO. maxPoint})" 
												data-content="${writeMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
													<span class="writersNickName">- ${writeMainArticle.memberVO.memberNickName} -</span>
												</a>
												<!-- 작성 카드 히든 -->
												<input type="hidden" class="mainArticleTitleNO" value="${writeMainArticle.mainArticleNo}">
												<input type="hidden" class="loginMemberEmail" value="${sessionScope.memberVO.memberEmail}">
												<!-- 작성 카드 히든 -->
												<!-- 상세보기 -->
												<div class="actions">
													<button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
														Read Article
													</button>
												</div>
												<!-- 상세보기 -->
											</div>
											<!-- 작성 카드 상단 컨텐츠들 -->
											<!-- 작성 카드 하단 버튼들 -->
											<div class="social-line social-line-visible" data-buttons="4">
												<!-- 내가 작성 주제글(타임체크) -->
												<button class="btn btn-social btn-pinterest">
													나의<br>주제글
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
												<%--	잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
														주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다 --%>
												<form name="itJaInfo">
													<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
													<input type="hidden" name="mainArticleNo" value="${writeMainArticle.mainArticleNo}">
													<input type="hidden" name="subArticleNo" value=0>
												</form>
												<%-- 잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝 --%>
												<!-- 찜 버튼 -->
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
												<!-- 찜 버튼 -->
												<!-- 찜 정보를 전달하기 위한 폼 시작 -->
												<form name="pickInfo">
													<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
													<input type="hidden" name="mainArticleNo" value="${writeMainArticle.mainArticleNo}">
												</form>
												<!-- 찜 정보를 전달하기 위한 폼 끝 -->
												<button class="btn btn-social btn-primary btn-facebook sharePage"  
					data-toggle="modal" data-target="#shareModal" style="width:23%;">
						<i class="fa fa-facebook-official"></i><br> 공유!
					</button>
											</div>
											<!-- 작성 카드 하단 버튼들 -->
											<div class="filter"></div>
										</div>
										<!-- 작성 카드 이미지 틀 -->
									</div>
									<!-- 작성 카드 틀 -->
								</c:forEach>
								<!-- 작성 카드 for문 끝 -->
							</div><!--  end gallery js-flickity -->
						</div><!-- end container -->
					</div><!-- end jumbotron itjaSlide -->
					<!-- 작성 주제글 보여주기 끝 -->
					
					<!-- 참여, 이은 주제글 -->
					<div class="itjaSlide">
						<h2 class="itjaMainTitle">${requestScope.rankMemberVO.memberNickName}님이 이은 주제글!<br></h2>
						<div class="container-fluid">
							<div class="gallery js-flickity"
        					 data-flickity-options='{ "freeScroll": false, "wrapAround": true ,"pageDots": false, "autoPlay" : 2000}'>
								<!-- 참여 카드 for문 -->
								<c:forEach var="joinMainArticle" items="${requestScope.joinMainArticleList}">
									<!-- 참여 카드 틀 -->
									<div class="card-box col-lg-2">  
										<!-- 참여 카드 이미지 틀 -->
										<div class="card card-with-border" data-background="image" data-src="${initParam.root}resources/uploadImg/articleBg/${joinMainArticle.mainArticleImgVO.mainArticleImgName}">    
											<!-- 참여 카드 상단 컨텐츠들 -->
											<div class="content">
												<h6 class="category">${joinMainArticle.tagName}</h6>	<br>
												<c:set var="joinMainArticleContentTitle" value="${joinMainArticle.mainArticleTitle}" />
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
												<c:set var="joinMainArticleContentContent" value="${joinMainArticle.mainArticleContent}" />
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
												<a href="mypage.neon?memberEmail=${joinMainArticle.memberVO.memberEmail}" style="" 
												tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" 
												data-toggle="popover" 
												title="${joinMainArticle.memberVO.memberNickName}님, ${joinMainArticle.memberVO.rankingVO.memberGrade} PTS(${joinMainArticle.memberVO.memberPoint} / ${joinMainArticle.memberVO.rankingVO. maxPoint})" 
												data-content="${joinMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
													<span class="writersNickName">- ${joinMainArticle.memberVO.memberNickName} -</span>
												</a>
												<!-- 참여 카드 히든 -->
												<input type="hidden" class="mainArticleTitleNO" value="${joinMainArticle.mainArticleNo}">
												<input type="hidden" class="loginMemberEmail" value="${sessionScope.memberVO.memberEmail}">
												<!-- 참여 카드 히든 -->
												<!-- 상세 보기 -->
												<div class="actions">
													<button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
														Read Article
													</button>
												</div>
												<!-- 상세 보기 -->
											</div>
											<!-- 참여 카드 상단 컨텐츠들 -->
											<!-- 참여 카드 하단 버튼들 -->
											<div class="social-line social-line-visible" data-buttons="4">
												<!-- 나의 참여글(타임 체크) -->
												<button class="btn btn-social btn-pinterest">
													나의<br>참여글
												</button>
												<!-- 잇자 버튼 -->
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
												<%--	잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
														주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다 --%>
												<form name="itJaInfo">
													<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
													<input type="hidden" name="mainArticleNo" value="${joinMainArticle.mainArticleNo}">
													<input type="hidden" name="subArticleNo" value=0>
												</form>
												<%-- 잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝 --%>
												<!-- 찜 버튼 -->
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
												<button class="btn btn-social btn-primary btn-facebook sharePage"  
					data-toggle="modal" data-target="#shareModal" style="width:23%;">
						<i class="fa fa-facebook-official"></i><br> 공유!
					</button>
											</div>
											<!-- 참여 카드 하단 버튼들 끝 -->
											<div class="filter"></div>
										</div>
										<!-- 참여 카드 이미지 틀 끝 -->
									</div>
									<!-- 참여 카드 틀 끝  -->
								</c:forEach>
								<!-- 참여 카드 for문 끝 -->
							</div><!--  end gallery js-flickity -->
						</div><!-- end container -->
					</div><!-- end jumbotron itjaSlide -->
					<!-- 끝 참여주제글 -->
					<!-- ************************* 찜, 작성, 이은글 보기 끝 ************************* -->
				</div>
				<!-- 마이페이지 탭1 내용 카드보여주기 -->
				<!-- 마이페이지 탭2 내용 개인정보 통계 -->
				<div class="tab-pane fade in" id="tab2">
					<!-- 개인 정보 카드틀 -->
					<div class="container">
						<!-- 개인 정보 카드틀2 -->
						<div class="row">
							<!-- 카드1. 구독 틀 -->
							<div class="col-sm-3">
								<!-- 카드1. 구독 -->
								<div class="hero-widget well well-sm">
									<!-- 카드1. 아이콘 -->
									<div class="icon">
										<i class="fa fa-user user"></i>
									</div>
									<!-- 카드1. 아이콘 내용 -->
									<div class="text">
										<var class="subscriptedCountInfo">${subscriptedCount}</var>
										<label class="text-muted">SUBSCRIBERs</label>
									</div>
									<!-- 카드1. 하단 버튼 -->
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
								<!-- 카드1. 구독 끝 -->
							</div>
							<!-- 카드1. 구독 틀 끝 -->
							<!-- 카드2. 잇자포인트 틀 -->
							<div class="col-sm-3">
								<!-- 카드2. 잇자포인트 -->
								<div class="hero-widget well well-sm">
									<!-- 카드2. 아이콘 -->
									<div class="icon">
										<i class="fa fa-link itja"></i>
									</div>
									<!-- 카드2. 아이콘 내용 -->
									<div class="text">
										<var>${requestScope.rankMemberVO.memberPoint}</var>
										<label class="text-muted">잇자 포인트</label>
									</div>
									<!-- 카드2. 하단 버튼 -->
									<div class="options">
										<a class="btn btn-lg btn-danger popover2" title="뇌OnSign Grade" href='#'>
										<i class="fa fa-trophy"></i>&nbsp; Next Point
										${requestScope.rankMemberVO.rankingVO.maxPoint}</a>
										<!-- 카드2. 하단 버튼 호버 -->
										<div id="rankingPopover" style="display: none">
											<table class="rankingTable">
												<tr><td>Grade</td><td>점수범위</td></tr>
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
										<!-- 카드2. 하단 버튼 호버 끝 -->
									</div>
									<!-- 카드2. 하단 버튼 끝 -->
								</div>
								<!-- 카드2. 잇자포인트 끝 -->
							</div>
							<!-- 카드2. 잇자포인트 틀 끝 -->
							<!-- 카드3. 가입 나이 틀 -->		
							<div class="col-sm-3">
								<!-- 카드3. 가입 나이 -->
								<div class="hero-widget well well-sm">
									<!-- 카드3. 가입 나이 아이콘 -->		
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
											<i class="fa fa-futbol-o"></i>
										</c:if>
										<c:if test="${requestScope.joinAge>6}">
											<i class="fa fa-linux"></i>
										</c:if>
									</div>
									<!-- 카드3. 가입 나이 아이콘 내용 -->
									<div class="text">
										<var>${requestScope.joinAge}</var>
										<label class="text-muted">가입나이</label>
									</div>
									<!-- 카드3. 가입 나이 하단 버튼 -->
									<div class="options">
										<a href="javascript:;" class="btn btn-default btn-lg">
										<i class="fa fa-fire"></i>&nbsp;&nbsp;since ${requestScope.rankMemberVO.memberJoinDate}</a>
									</div>
								</div>
								<!-- 카드3. 가입 나이 끝 -->
							</div>
							<!-- 카드3. 가입 나이 틀 끝 -->
							<!-- 카드4. 찜 정보 틀 -->
							<div class="col-sm-3">
								<!-- 카드4. 찜 정보 -->
								<div class="hero-widget well well-sm">
									<!-- 카드4. 찜 정보 아이콘 -->
									<div class="icon">
										<i class="fa fa-star pick"></i>
									</div>
									<!-- 카드4. 찜 정보 아이콘 내용 -->
									<div class="text">
										<var>${fn:length(requestScope.pickedMainArticleList)}</var>
										<label class="text-muted">찜한게시물</label>
									</div>
									<!-- 카드4. 찜 정보 하단 버튼 -->
									<div class="options">
										<a href="mypage.neon?memberEmail=${requestScope.rankMemberVO.memberEmail}" class="btn btn-default btn-lg">
										<i class="fa fa-desktop"></i>&nbsp;&nbsp;View Info</a>
									</div>
								</div>
								<!-- 카드4. 찜 정보 끝 -->
							</div>
							<!-- 카드4. 찜 정보 틀 끝 -->
							<!-- 카드5. 작성글 정보 틀 -->
							<div class="col-sm-3">
								<!-- 카드5. 작성글 정보 -->
								<div class="hero-widget well well-sm">
									<!-- 카드5. 작성글 정보 아이콘 -->
									<div class="icon">
										<i class="fa fa-lightbulb-o write"></i>
									</div>
									<!-- 카드5. 작성글 정보 아이콘 내용 -->
									<div class="text">
										<var>${fn:length(requestScope.writeMainArticleList)} </var>
										<label class="text-muted">작성한 잇는글</label>
									</div>
									<!-- 카드5. 작성글 정보 하단 버튼 -->
									<div class="options">
										<a href="mypage.neon?memberEmail=${requestScope.rankMemberVO.memberEmail}" class="btn btn-default btn-lg"><i class="fa fa-desktop"></i>&nbsp;&nbsp;View Info</a>
									</div>
								</div>
								<!-- 카드5. 작성글 정보 끝 -->
							</div>
							<!-- 카드5. 작성글 정보 틀 끝 -->
							<!-- 카드6. 참여글 정보 틀 -->
							<div class="col-sm-3">
								<!-- 카드6. 참여글 정보 -->
								<div class="hero-widget well well-sm">
									<!-- 카드6. 참여글 정보 아이콘 -->
									<div class="icon">
										<i class="fa fa-reply join"></i>
									</div>
									<!-- 카드6. 참여글 정보 아이콘 내용 -->
									<div class="text">
										<var>${fn:length(requestScope.joinMainArticleList)}</var>
										<label class="text-muted">참여한 잇는글</label>
									</div>
									<!-- 카드6. 참여글 정보 하단 버튼 -->
									<div class="options">
										<a href="mypage.neon?memberEmail=${requestScope.rankMemberVO.memberEmail}" class="btn btn-default btn-lg"><i class="fa fa-desktop"></i>&nbsp;&nbsp;View Info</a>
									</div>
								</div>
								<!-- 카드6. 참여글 정보 끝 -->
							</div>
							<!-- 카드6. 참여글 정보 틀 끝 -->
							<!-- 카드7. 태그 정보 틀 -->
							<div class="col-sm-3">
								<!-- 카드7. 태그 정보 -->
								<div class="hero-widget well well-sm">
									<!-- 카드7. 태그 정보 아이콘 -->
									<div class="icon">
										<i class="fa fa-tags tags"></i>
									</div>
									<!-- 카드7. 태그 정보 아이콘 내용 -->
									<div class="text">
										<var>${fn:length(writeTagListbyEmailList)}</var>
										<label class="text-muted">작성 TAGs</label>
									</div>
									<!-- 카드7. 태그 정보 하단 버튼 -->
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
								<!-- 카드7. 태그 정보 끝 -->
							</div>
							<!-- 카드7. 태그 정보 틀 끝 -->
		                     <!-- 카드8.  신고정보 틀  -->
		                     <div class="col-sm-3">
		                        <!-- 카드8.신고정보 -->
		                        <div class="hero-widget well well-sm">
		                           <!-- 카드8. 신고정보 아이콘 -->
		                           <div class="icon">
		                           <c:set var="memberReportCount" value="${requestScope.rankMemberVO.memberReportAmount}"/>
		                           <c:set var="reportGrade" value=""/>
		                              <!-- <i class="fa fa-exclamation-triangle ready"></i> -->
		                              <c:choose>
		                                 <c:when test="${memberReportCount<11}">
		                                    <i class="fa fa-thumbs-o-up"></i>
		                                    <c:set var="reportGrade" value="클린회원"/>   
		                                 </c:when>
		                                 <c:when test="${memberReportCount >10 && memberReportCount<50}">
		                                    <i class="fa fa-thumbs-up"></i>
		                                    <c:set var="reportGrade" value="보통회원"/>   
		                                 </c:when>
		                                 <c:when test="${memberReportCount>49}">
		                                    <i class="fa fa-bomb"></i>
		                                    <c:set var="reportGrade" value="불량회원"/>   
		                                 </c:when>
		                              </c:choose>
		                           </div>
		                           <!-- 카드8. 신고정보 아이콘 내용 -->
		                           <div class="text">
		                              <var id="memberReportCount">${memberReportCount}</var>
		                              <label class="text-muted">신고횟수 / ${reportGrade}</label>
		                           </div>
		                           <!-- 카드8. 신고정보 하단 버튼 -->
		                           <div class="options">
		                              <a href="javascript:;" class="btn btn-default btn-lg memberReport">
		                              신고하기&nbsp;&nbsp;<i class="fa fa-ban"></i></a>
		                              	<input type="hidden" id="memberReportEmail" value="${requestScope.rankMemberVO.memberEmail}">
		                              	<input type="hidden" id="memberUserEmail" value="${sessionScope.memberVO.memberEmail}">
		                           </div>
		                        </div>
		                        <!-- 카드8. 신고정보 끝 -->
		                     </div>
		                     <!-- 카드8. 신고정보 틀 끝 -->
						</div>
						<!-- 개인 정보 카드틀2 -->	
					</div>
					<!-- 개인 정보 카드틀1 -->
				</div> 
				<!-- 마이페이지 탭2 내용 개인정보 통계 끝 -->
				
				<!-- 마이페이지 탭3 구독 정보 시작 -->
				<div class="tab-pane fade in" id="tab3">
					<!-- 탭3 구독 정보 틀 -->
					<div class="row">
					
						<!-- 2015-12-14 대협추가 -->
						<!-- script.js에서 게시판 종류를 구분하기위한 hidden -대협- -->
						<input type="hidden" id="articleType" value="mainArticle">
						<!-- <h2 class="itjaMainTitle">새로운 잇자!</h2> -->
						<!-- 태그명을 받아 현재 선택한 태그를 표시한다. -대협 -->
						<span id="getNowTagName"></span> 
						<!-- 구독 글 시작 -->
						<section id="pinBoot">
							<!-- 구독 글 포문 -->
							<c:forEach var="subscriptingMainArticle" items="${requestScope.subscriptingMainArticleList}">
								<!-- 구독 글 틀 -->
								<article class="white-panel">
									<!-- 2015-12-14 대협추가 -->
									<input type="hidden" id="articleType" value="mainArticle">
									<!-- 구독 글 타이틀 -->
									<h4><a href="#">${subscriptingMainArticle.mainArticleTitle}</a></h4>
									<!-- 구독 글 태그 -->
									<h6 class="category">${subscriptingMainArticle.tagName}</h6>
									<!-- 구독 글 이미지 -->
									<img src="${initParam.root}resources/uploadImg/articleBg/${subscriptingMainArticle.mainArticleImgVO.mainArticleImgName}" alt="">
									<!-- 구독 글 버튼들 -->
									<div class="social-line social-line-visible" data-buttons="4">
										<!-- 구독글(타임체크) -->
										<button class="btn btn-social btn-pinterest">
											<span class="time_area">구독글</span>
										</button>
										<!-- 잇자 -->
										<button class="btn btn-social btn-twitter itja">
											<c:set var="count" value="false" />
											<c:forEach var="itjaList" items="${sessionScope.memberVO.itjaMemberList}">
											<c:choose>
												<c:when test="${itjaList.mainArticleNo== subscriptingMainArticle.mainArticleNo}">
													<c:set var="count" value="true" />
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
											</c:forEach>
											<c:choose>
												<c:when test="${count==true}">
													<span class="itjaCount"><i class="fa fa-link"></i><br>${subscriptingMainArticle.mainArticleTotalLike }it</span>
												</c:when>
												<c:otherwise>
													<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>${subscriptingMainArticle.mainArticleTotalLike }it</span>
												</c:otherwise>
											</c:choose>
										</button>
										<!-- 2015-12-14 대협수정 -->
										<!-- 찜 -->
										<button class="btn btn-social btn-google pickBtn">
										<c:set var="breakCheck" value="false"/>
										<c:forEach var="pickCheck" items="${sessionScope.memberVO.pickedVOList}">
										<c:choose>
											<c:when test="${pickCheck.mainArticleNo == subscriptingMainArticle.mainArticleNo}">
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
										<!-- 공유 -->
										<button class="btn btn-social btn-primary btn-facebook sharePage"  
										data-toggle="modal" data-target="#shareModal" style="width:23%;">
										<i class="fa fa-facebook-official"></i><br> 공유!
										</button>
										<%--
										  잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼
										  주제글의 잇자 클릭이므로 subArticleNo=0으로 넘어간다 --%>
										<form name="itJaInfo">
											<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
											<input type="hidden" name="mainArticleNo" value="${subscriptingMainArticle.mainArticleNo}">
											<input type="hidden" name="subArticleNo" value=0>
										</form>
										<%--
										   잇자 버튼 클릭시 전달 할 정보를 위한 히든 폼 끝
										 --%>
										<!-- 찜 정보를 전달하기 위한 폼 시작 -->
										<form name="pickInfo">
											<input type="hidden" name="memberEmail" value="${sessionScope.memberVO.memberEmail}">
											<input type="hidden" name="mainArticleNo" value="${subscriptingMainArticle.mainArticleNo}">
										</form>
										 <!-- 찜 정보를 전달하기 위한 폼 끝 -->
									</div>
									<!-- 구독 글 버튼들 -->
									<!-- 2015-12-14 대협수정 -->
									<!-- 구독 글 내용 -->
									<p class="card-content"/>
									<c:set var="subscriptingMainArticleContent" value="${subscriptingMainArticle.mainArticleContent}" />
									<p class="description">
									${subscriptingMainArticleContent}
									</p>
									<!-- 구독 글 닉네임 -->
									<a href="mypage.neon?memberEmail=${subscriptingMainArticle.memberVO.memberEmail}" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="${newMainArticle.memberVO.memberNickName}님, ${newMainArticle.memberVO.rankingVO.memberGrade} PTS(${newMainArticle.memberVO.memberPoint} / ${newMainArticle.memberVO.rankingVO.maxPoint})" data-content="${newMainArticle.memberVO.memberNickName}님 Click하여 페이지 보기" >
										<span class="writersNickName">- ${subscriptingMainArticle.memberVO.memberNickName} -</span>
									</a>
								</article>
								<!-- 구독 글 틀 끝 -->
							</c:forEach>
							<!-- 구독 글 포문 끝 -->
						</section>
						<!-- 구독 글 끝 -->
						
					</div>
					<!-- 탭3 구독 정보 틀 끝 -->
				</div>
				<!-- 마이페이지 탭3 구독 정보 시작 -->
				
			</div>
			<!-- 마이페이지 탭 내용 틀2 -->
		
		</div>
		<!-- 마이페이지 탭 내용 틀 -->
	</div>
	<!-- 마이페이지 틀 끝 -->
</div>
<!-- 마이페이지 끝 -->








