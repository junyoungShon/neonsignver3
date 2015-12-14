<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야함->
<title>부트스트랩 101 템플릿</title>


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
<style type="text/css">
/* 로그인 페이지*/
 @import url(http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css);

body{
	background-image: url("${initParam.root}resources/img/loginbg.jpg");
}
.loginPageLoginForm{
	margin-top: 10%;
}


.myForm {
    width: 25%;
    margin: 0 auto;
    padding-top: 50px;
    height: 400px;
}

.myForm h1 {
    color: white;
    font-weight: bold;
    text-transform: uppercase;
    padding-bottom: 30px;
}

.myForm .checkbox {
    margin-bottom: 20px;
    position: relative;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none;
}

.myForm .checkbox.show:before {
    content: '\f00c';
    color: #5bc0de;
    font-size: 17px;
    margin: 1px 0 0 3px;
    position: absolute;
    pointer-events: none;
    font-family: FontAwesome;
}
.bottomMenu{
	color: WHITE;
	font-weight: bold;
}
.myForm .checkbox .mycheckbox {
    width: 25px;
    height: 25px;
    cursor: pointer;
    border-radius: 3px;
    border: 1px solid #ccc;
    vertical-align: middle;
    display: inline-block;
}

.myForm .checkbox .label {
    color: #777;
    font-size: 13px;
    font-weight: 400;
}

.myForm .forget {
    font-size: 13px;
	text-align: center;
	display: block;
}
/*    --------------------------------------------------
    :: Footer
	-------------------------------------------------- */
#footer {
    color: red;
    font-size: 12px;
    text-align: center;
}
#footer p {
    margin-bottom: 0;
}
#footer a {
    color: inherit;
}
</style>
<script src="js/ie-emulation-modes-warning.js"></script>
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- 부트스트랩 사용을 위한 상단 설정 완료 -->
</head>
<section>
	<div class="loginPageLoginForm"></div>
     <div class="row">
    	    <div class="col-xs-12">
                <div class="myForm">
                    <h1 class="text-center">NeonSign Login</h1><br>
                    <h4>${requestScope.fail}</h4>
                    <form role="form" action="memberLogin.neon" method="post" id="login-form" autocomplete="off">
                        <div class="form-group">
                            <label for="email" class="sr-only">이메일 아이디</label>
                            <input type="email" name=memberEmail id="email" class="form-control input-md" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label for="key" class="sr-only">암호</label>
                            <input type="password" name="memberPassword" id="key" class="form-control input-md" placeholder="Password">
                        </div>
                        <div>
                       
                        </div>
                        <input type="submit" id="btn-login" class="btn btn-default btn-lg btn-block" value="Log in"><br>
                        <span><a href="#" class="memberJoinByEmailBtn top-menu"><h4 class="bottomMenu">회원가입</h4></a>
                        <a href="javascript:;" class="forget" data-toggle="modal" data-target=".forget-modal"><h4 class="bottomMenu">비밀번호 복구</h4></a></span>
                    </form>
                    <hr><br><br>
                </div>
    		</div> <!-- /.col-xs-12 -->
    		   <div class="col-xs-12 text-center">
                <p></p><br><br>
                <p>Powered by <strong><a href="${initParam.root}getMainList.neon" target="_blank">NeonSign</a></strong></p>
            </div>
    	</div> <!-- /.row -->
</section>

<div class="modal fade forget-modal" tabindex="-1" role="dialog" aria-labelledby="myForgetModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true"><i class="fa fa-remove"></i></span>
					<span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">비밀 번호 복구 요청</h4>
			</div>
			<div class="modal-body">
				<p class="alertSpace">자신이 아이디로 사용하는 이메일을 입력해주세요</p>
				<input type="email" name="memberEmail" id="recovery-email" class="form-control" autocomplete="off">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-info requestTemporaryPassword">메일 요청</button>
			</div>
		</div> <!-- /.modal-content -->
	</div> <!-- /.modal-dialog -->
</div> <!-- /.modal -->

         <!-- Modal Email join   -->
			<div class="modal fade" id="memberJoinByEmailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">뇌온사인 회원가입</h4>
			      </div>
			      <div class="modal-body">
			        <form id="memberJoinByEmail" action="memberJoinByEmail.neon">
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
							<input type="email" class="form-control" id="memberJoinInputEmail" name="memberEmail"
							  		placeholder="아이디로 사용할 이메일을 입력해주세요">
							<span class="glyphicon form-control-feedback" aria-hidden="ture"></span>
							  	<!-- 이름 입력 양식 /nameInputSuccess,nameInputFail 클래스 성공 실패의 경우를 나눠준다 -->
						</div>
			        	<div class="form-group has-feedback nameInput">
						  	<label class="control-label" for="inputSuccess2">닉네임</label>
					    	<input type="text" class="form-control" id="memberJoinInputName" placeholder="회원님의 닉네임을 입력해주세요" name="memberNickName">
					    	<span class="glyphicon form-control-feedback" aria-hidden="ture"></span>
						</div>
					   		<div class="form-group has-feedback passInput">	
						    	<label class="control-label" for="inputSuccess2">암호</label>
						    	<input type="password" class="form-control" id="memberJoinInputpassword" placeholder="암호" name="memberPassword">
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

</body>
</html>