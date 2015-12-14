<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야함-->
<title>잊지말고! 잇자!</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- 부트스트랩 -->
<link href="${initParam.root}resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${initParam.root}resources/css/style.css" rel="stylesheet">
<script src="${initParam.root}resources/js/ie-emulation-modes-warning.js"></script>
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- 부트스트랩 사용을 위한 상단 설정 완료 -->
 <link href="${initParam.root}resources/css/font-awesome.min.css" rel="stylesheet" />
</head>
<body>
	<div class="site-wrapper">
      <div class="site-wrapper-inner">
        <div class="cover-container">
          <div class="masthead clearfix">
            <div class="inner">
           <!--    <h3 class="masthead-brand"><img src="img/logo_top.png" width="100%" width="100%"></h3> -->
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">Home</a></li>
                  <li><a href="#">App</a></li>
                  <li><a href="#">Contact</a></li>
                </ul>
              </nav>
            </div>
          </div>
          <div class="inner cover">
          	<div class="cover-logo">
          		<!-- <img src="img/logo_white1.png" width="40%" width="40%"> -->
          	</div>
            <h1 class="cover-heading">잊지말고 ! 잇자! <br> 이야기를 잇자!</h1>
            <p class="lead indexInstruction"><strong><span class="blliInst">뇌온사인</span>은 공동창작 플랫폼으로서 <br>같은 이야기를 함께 만들고 나누는 공간입니다.</strong></p>
            <p class="indexLogin">이미 회원이시라면? <a href="loginPage.jsp" >로그인</a></p>
            <p class="lead">
              <a href="${initParam.root}home.neon" class="btn btn-lg btn-warning">비회원으로 둘러보기</a><br>
              <button type="button" class="btn btn-primary btn-lg memberJoinByEmailBtn" >
              		이메일로 가입하기
              </button>
            </p>
          </div>
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
						  	<label class="control-label" for="inputSuccess2">닉네임</label>
					    	<input type="text" class="form-control" id="memberJoinInputName" placeholder="회원님의 닉네임을 입력해주세요">
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
			        
			      <div class="modal-footer" id="babyInfoForJoin">
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
          <!-- 회원가입 모달 끝 -->

        </div>
         <div class="mastfoot">
            
      	</div>
      	<div class="inner">
 				<p class="copyright copyrightOfCobro text-muted small">Copyright &copy; CoBro 2015. All Rights Reserved</p>
            </div>

    </div>
	</div>

<!-- 부트 스트랩 사용을 위한 하단 설정 -->
<!-- 현재 CDN으로 제공되는 JQuery활용 -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${initParam.root}resources/js/jquery.js"></script>
<script src="${initParam.root}resources/js/script.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${initParam.root}resources/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script	src="${initParam.root}resources/js/ie10-viewport-bug-workaround.js"></script>
<!-- 부트 스트랩 사용을 위한 하단 설정 완료 -->


</body>
</html>