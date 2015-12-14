<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야함->
<title>부트스트랩 101 템플릿</title>

<!-- 부트스트랩 -->
<link href="${initParam.root}resources/css/bootstrap.min.css" rel="stylesheet">
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
 <style>
        
    </style>
    
</head>


<body>
  	<nav class="navbar navbar-inverse navbar-fixed-top itjaTopMenu">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">뇌  On Sign</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
       			 <form class="navbar-form navbar-left" role="search" >
			        <div class="form-group" >
			          <input type="text" class="form-control" placeholder="Search" style="width:500px;">
			        </div>
			        <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
    			  </form>
    			  
			<!-- 	<form class="navbar-form navbar-center">
           			 <div class="form-group">
             		 	<input type="text" placeholder="Email" class="form-control">
           			 </div>
          			  <div class="form-group">
             			 <input type="password" placeholder="Password" class="form-control">
           			 </div>
           			 <button type="submit" class="btn btn-success">Sign in</button>
         			 </form>
         		 -->
    		<ul class="nav navbar-nav navbar-right">
 
    			<li><a href="#" class="memberLogin"> 로그인</a></li>
    			<li><a href="#" class="memberJoinByEmailBtn"> 가입</a></li>
    			  <li><a href="#">완결 글 보기</a></li>
    			  <li><a href="#" class="writeMainArticle">글쓰기</a></li>
     		</ul>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    <!-- 회원가입 모달창 출력부분 -->
    <!-- Modal Email join   -->
			<div class="modal fade" id="memberJoinByEmailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">뇌온사인 회원가입</h4>
			      </div>
			      <div class="modal-body">
			        <form id="memberJoinByEmail">
			        	<!-- 개인정보 입력 부분 -->
			        	<div class="personInfoForJoin">
			        	<!-- 
			        		이 폼그룹은에 has-success,has-error,has-feedback,has-warning에 따라 폼의 색이 달라진다.
			        		따라서 폼 그룹의 클래스 명을 변경시켜줌으로서 입력폼의 색을 바꿔 준다.
			        		이후 메시지는 control-label(폼그룹의 has-~상태에 따라 색이 바뀐다.)에 출력시키며
			        		아이콘을 출력하는 span클래스의 이름을 바꿔줌으로서 유효성 검사를 완성한다.
			        	-->
			        	<div class="form-group has-feedback emailInput">
						  	<label class="control-label" for="inputSuccess2">이메일</label>
							<input type="email" class="form-control" id="memberJoinInputEmail" 
							  		placeholder="아이디로 사용할 이메일을 입력해주세요">
							<span class="glyphicon form-control-feedback" aria-hidden="ture"></span>
							  	<!-- 이름 입력 양식 /nameInputSuccess,nameInputFail 클래스 성공 실패의 경우를 나눠준다 -->
						</div>
			        	<div class="form-group has-feedback nameInput">
						  	<label class="control-label" for="inputSuccess2">이름</label>
					    	<input type="text" class="form-control" id="memberJoinInputName" placeholder="회원님의 성함을 입력해주세요">
					    	<span class="glyphicon form-control-feedback" aria-hidden="ture"></span>
						</div>
					   		<div class="form-group has-feedback passInput">	
						    	<label class="control-label" for="inputSuccess2">암호</label>
						    	<input type="password" class="form-control" id="memberJoinInputpassword" placeholder="암호">
						    	<span class="glyphicon form-control-feedback" aria-hidden="ture"></span>
						    	
					    	</div>
					   		<div class="form-group has-feedback rePassInput">	
						    	<label class="control-label" for="memberJoinInputpassword">암호 확인</label>
						    	<input type="password" class="form-control" id="memberJoinInputRePassword" placeholder="암호를 한번 더 입력해주세요!">
						    	<span class="glyphicon form-control-feedback" aria-hidden="ture"></span>
					    	</div>
					 	</div>
			        </form>
			      </div>
			      <div class="modal-footer">
			        
			      <div class="modal-footer">
			      	<!-- 개인정보 입력 시 출력되는  버튼-->
			      	<button type="button" class="personInfoForJoin InfoForJoinCancel btn btn-default">Close</button>
			        <button type="button" class="personInfoForJoin btn btn-primary" id="submitInfo">가입 완료</button>
			      	<div class="col-6 .col-md-offset-3">
				      	<h6>	
				      		가입과 동시에 귀하는 쿠키 사용을 포함해 
				      		이용약관과 개인정보 취급방침에 동의하는 것입니다.
				      	</h6>
			      	</div>
			        
			      </div>
			    </div>
			  </div>
			</div>
			</div>
			
	<!-- 글 내용이 출력되는 모달 창 -->
	<!-- Modal -->
	<div class="modal fade" id="cardDetailView" tabindex="-1" role="dialog"
		aria-labelledby="cardDetailViewLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cardDetailViewLabel"></h4>
				</div>
					<div class="modal-body">
						<table class="table">
							<tr class="success">
								<td colspan="5">주제글</td>
							</tr>
							<tr>
								<td class="cardDetailViewContentNo" width="5%">1</td>
								<td class="cardDetailViewContent" width="75%%"></td>
								<td class="writersNickNameAtDetail" width="10%">-고대협 병신</td>
								<td class="likeIt" width="5%">잇자!<br>100</td>
								<td class="reportIt" width="5%">신고</td>
							</tr>
							<tr>
								<td class="cardDetailViewContentNo" width="5%">2</td>
								<td class="cardDetailViewContent" width="80%"></td>
								<td class="writersNickNameAtDetail" width="10%">-고대협 병신</td>
								<td class="likeIt" width="5%">잇자!<br>100</td>
								<td class="reportIt" width="5%">신고</td>
							</tr>
							<tr class="warning">
								<td colspan="5">잇는글</td>
							</tr>
							<tr>
								<td class="cardDetailViewContentNo" width="5%">1</td>
								<td class="cardDetailViewContent" width="80%"></td>
								<td class="writersNickNameAtDetail" width="10%">-고대협 병신</td>
								<td class="likeIt" width="5%">잇자!<br>100</td>
								<td class="reportIt" width="5%">신고</td>
							</tr>
							<tr>
								<td class="cardDetailViewContentNo" width="5%">2</td>
								<td class="cardDetailViewContent" width="80%"></td>
								<td class="writersNickNameAtDetail" width="10%">-고대협 병신</td>
								<td class="likeIt" width="5%">잇자!<br>100</td>
								<td class="reportIt" width="5%">신고</td>
							</tr>
						</table>
						<DIV class="itjaWriteForm">
							<form action="" class="form-horizontal">
								<textarea class="form-control" rows="10" placeholder="잇는글을 입력해주세요 ! (200자로 제한됩니다.)"></textarea>
							<div class="limitLength">0kb/600kb</div>
							</form>
						</DIV>
						  <div class="social-line social-line-visible" data-buttons="4">
                            <button class="btn btn-social btn-pinterest">
                              	05:22<br>
                              	빨리!
                            </button>
                            <button class="btn btn-social btn-twitter">
                               	 127it<br>
                              	 잇자!
                            </button>
                            <button class="btn btn-social btn-google">
                               	<i class="fa fa-heart-o"></i><br>
                               	찜하자!
                            </button>
                            <button class="btn btn-social btn-facebook">
                           		  <i class="fa fa-facebook-official"></i><br>
                           		    공유하자!
                            </button>
                        </div>  <!-- end social-line social-line-visible -->
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!--글 내용 출력 모달창 끝 -->
	<!-- 메인 아티클을 작성하는 부분 -->
	<!-- Modal -->
	<div class="modal fade" id="writeMainArticle" tabindex="-1" role="dialog"
		aria-labelledby="writeMainArticleLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="writeMainArticleLabel">뇌 On Sign 주제글 작성하기</h4>
				</div>
				<div class="modal-body">
					<table class="table">
						<tr>
							<td>
							태그 선택
								<div class="checkbox">
									<label class="checkbox-inline">
									  <input type="checkbox" id="inlineCheckbox1" value="option1"> #스릴러
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox" id="inlineCheckbox2" value="option2"> #SF
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox" id="inlineCheckbox3" value="option3"> #로맨스
									</label>
								</div>
							</td>
						</tr>
						<tr>
							<td>
							<input type="text" class="form-control" placeholder="주제글의 제목을 입력해주세요!">
							</td>
						</tr>
						<tr>
							<td>
							<textarea class="form-control" rows="10" placeholder="주제글 입력해주세요 ! (200자로 제한됩니다.)"></textarea>
							<div class="limitLength">작성 후 잇자 10개시 베스트로 이동되며,타임체크가 발동됩니다! 0kb/600kb</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">글 남기기</button>
				</div>
			</div>
		</div>
	</div>
	<!--글 내용 출력 모달창 끝 -->
	<!-- 로그인 폼 출력되는 모달 창 -->
	<!-- Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"></div>
				<div class="modal-body">
				<form>
						<div class="form-group">
					    	<label for="InputEmail1">이메일 주소</label>
					    	<input type="email" class="form-control" id="InputEmail1" placeholder="이메일을 입력하세요">
				  		</div>
					  <div class="form-group">
					    <label for="InputPassword1">암호</label>
					    <input type="password" class="form-control" id="InputPassword1" placeholder="암호">
					  </div>
				  <div class="checkbox">
				    <label>
				      <input type="checkbox"> 입력을 기억합니다
				    </label>
				  </div>
				  <button type="submit" class="btn btn-primary">제출</button>
				</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!--로그인모달창 끝 -->
    
    
    <!-- 찜한 주제글 슬라이드-->
    <div class="itjaSlide">
      <h2 class="itjaMainTitle">찜한 주제글!</h2>
      <div class="container-fluid">
        <div class="gallery js-flickity" data-flickity-options='{ "freeScroll": true, "wrapAround": true ,"pageDots": false}'>
            <!-- el 문 및 ajax로 베스트글이 표시되는 슬라이드 지역 --> 
            <!-- 카드 1개 -->
			<div class="card-box col-lg-2">  
                <div class="card card-with-border" data-background="image" data-src="img/snow.jpg">    
                    <div class="content">
                        <h6 class="category">#소설,#로맨스,#스릴러,#호갱</h6><br>
                        <h4 class="title">[미완]군인인데 눈이 엄청왔다</h4>
                        <p class="description">
							올해 37살 되는 남자입니다. 아직 군대에 있는데 눈이 엄청나게 왔네요 정말 슬퍼요
								
						</p>
							<span class="writersNickName">-고대협병신</span>
                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
                                Read Article
                            </button>
                        </div>
                    </div>
                    <div class="social-line social-line-visible" data-buttons="4">
                            <button class="btn btn-social btn-pinterest">
                              	05:22<br>
                              	빨리!
                            </button>
                            <button class="btn btn-social btn-twitter">
                               	 127it<br>
                              	 잇자!
                            </button>
                            <button class="btn btn-social btn-google">
                               	<i class="fa fa-heart-o"></i><br>
                               	찜하자!
                            </button>
                            <button class="btn btn-social btn-facebook">
                           		  <i class="fa fa-facebook-official"></i><br>
                           		    공유하자!
                            </button>
                        </div>  <!-- end social-line social-line-visible -->
						<div class="filter"></div>
                </div> <!-- end card -->
            </div><!-- card-box col-md-4 -->
            <!--끝!! 카드 1개 -->
			
		</div><!--  end gallery js-flickity -->
      </div><!-- end container -->
    </div><!-- end jumbotron itjaSlide -->
    
    <!-- 내가 작성한 주제글 -->
     <div class="itjaSlide">
      <h2 class="itjaMainTitle">내가 작성한 주제글!</h2>
      <div class="container-fluid">
        <div class="gallery js-flickity" data-flickity-options='{ "freeScroll": true, "wrapAround": true ,"pageDots": false}'>
            <!-- el 문 및 ajax로 베스트글이 표시되는 슬라이드 지역 --> 
            <!-- 카드 1개 -->
			<div class="card-box col-lg-2">  
                <div class="card card-with-border" data-background="image" data-src="img/snow.jpg">    
                    <div class="content">
                        <h6 class="category">#소설,#로맨스,#스릴러,#호갱</h6><br>
                        <h4 class="title">[미완]군인인데 눈이 엄청왔다</h4>
                        <p class="description">
							올해 37살 되는 남자입니다. 아직 군대에 있는데 눈이 엄청나게 왔네요 정말 슬퍼요
								
						</p>
							<span class="writersNickName">-고대협병신</span>
                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
                                Read Article
                            </button>
                        </div>
                    </div>
                    <div class="social-line social-line-visible" data-buttons="4">
                            <button class="btn btn-social btn-pinterest">
                              	05:22<br>
                              	빨리!
                            </button>
                            <button class="btn btn-social btn-twitter">
                               	 127it<br>
                              	 잇자!
                            </button>
                            <button class="btn btn-social btn-google">
                               	<i class="fa fa-heart-o"></i><br>
                               	찜하자!
                            </button>
                            <button class="btn btn-social btn-facebook">
                           		  <i class="fa fa-facebook-official"></i><br>
                           		    공유하자!
                            </button>
                        </div>  <!-- end social-line social-line-visible -->
						<div class="filter"></div>
                </div> <!-- end card -->
            </div><!-- card-box col-md-4 -->
            <!--끝!! 카드 1개 -->
			
		</div><!--  end gallery js-flickity -->
      </div><!-- end container -->
    </div><!-- end jumbotron itjaSlide -->
   
   <!-- 내가 참여한 주제글 -->
      <div class="itjaSlide">
      <h2 class="itjaMainTitle">내가 참여한 주제글!</h2>
      <div class="container-fluid">
        <div class="gallery js-flickity" data-flickity-options='{ "freeScroll": true, "wrapAround": true ,"pageDots": false}'>
            <!-- el 문 및 ajax로 베스트글이 표시되는 슬라이드 지역 --> 
            <!-- 카드 1개 -->
			<div class="card-box col-lg-2">  
                <div class="card card-with-border" data-background="image" data-src="img/snow.jpg">    
                    <div class="content">
                        <h6 class="category">#소설,#로맨스,#스릴러,#호갱</h6><br>
                        <h4 class="title">[미완]군인인데 눈이 엄청왔다</h4>
                        <p class="description">
							올해 37살 되는 남자입니다. 아직 군대에 있는데 눈이 엄청나게 왔네요 정말 슬퍼요
								
						</p>
							<span class="writersNickName">-고대협병신</span>
                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern" data-toggle="modal" data-target="#cardDetailView">
                                Read Article
                            </button>
                        </div>
                    </div>
                    <div class="social-line social-line-visible" data-buttons="4">
                            <button class="btn btn-social btn-pinterest">
                              	05:22<br>
                              	빨리!
                            </button>
                            <button class="btn btn-social btn-twitter">
                               	 127it<br>
                              	 잇자!
                            </button>
                            <button class="btn btn-social btn-google">
                               	<i class="fa fa-heart-o"></i><br>
                               	찜하자!
                            </button>
                            <button class="btn btn-social btn-facebook">
                           		  <i class="fa fa-facebook-official"></i><br>
                           		    공유하자!
                            </button>
                        </div>  <!-- end social-line social-line-visible -->
						<div class="filter"></div>
                </div> <!-- end card -->
            </div><!-- card-box col-md-4 -->
            <!--끝!! 카드 1개 -->
			
		</div><!--  end gallery js-flickity -->
      </div><!-- end container -->
    </div><!-- end jumbotron itjaSlide -->

      <hr>
      <footer>
        <p>&copy; Company 2014</p>
      </footer>
  		  
  		  
            
<!-- 부트 스트랩 사용을 위한 하단 설정 -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${initParam.root}resources/js/jquery.js"></script>
<script src="${initParam.root}resources/js/script.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${initParam.root}resources/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script	src="${initParam.root}resources/js/ie10-viewport-bug-workaround.js"></script>
<!-- 부트 스트랩 사용을 위한 하단 설정 완료 -->
<!-- 힙스터 카드 js 파일 -->
<script src="${initParam.root}resources/js/hipster-cards.js"></script>
</body>
</html>