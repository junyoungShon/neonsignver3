<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
      <!-- Header -->
      <tiles:insertAttribute name="header"/>
    </nav>
     	<!-- left -->
     <div class="leftList" style="margin-top: 45px;">
 	<br><br><br>
 	<tiles:insertAttribute name="left"/>
	</div>
	
 	<div class="mainList">
 	<!-- main -->
 	<tiles:insertAttribute name="main"/>
  	</div>
  	
    <div class="container footer">
    <tiles:insertAttribute name="footer"/>
    <!-- footer -->
    </div>
    
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
<script type="text/javascript">
</script>

</body>
</html>