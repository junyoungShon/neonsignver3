<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.time_number {
	/*  background: url("images/time_bg.png") repeat scroll 0 0 transparent;
    position:relative;*/
	float: left;
	height: 35px;
	margin-top: 7px;
	text-align: center;
	width: 25px;
}

.time_colon {
	float: left;
	margin-top: 19px;
	text-align: center;
}

.time_number img {
	margin-top: 8px;
}
</style>
</head>
<body>

	<script type="text/javascript"
		src="${initParam.root}resources/js/jquery.js"></script>

	<div id="time_area"></div>

	<script>

$(document).ready(function() {
	window.setInterval(function(){
		//현재 시간	
		var currunt_date = new Date();
		var currunt_timestamp = Math.floor(currunt_date.getTime()/1000)
		//완결 시간(서버에서 ajax로 완결시간을 받아와야함)
		var complete_date = new Date(2015, 11-1, 25, 16, 43, 00);
		var complete_timestamp = Math.floor(complete_date.getTime()/1000);
		//투표 마감 시간
		var close_timestamp = complete_timestamp+600;
		//
		var remind_timestamp = close_timestamp-currunt_timestamp
		var remind_minutes = Math.floor(remind_timestamp/60);
		var remind_seconds = remind_timestamp%60;
		if(remind_minutes >= 1) {
			$('.time_area').html(remind_minutes+'분'+remind_seconds+'초 남았음');
		} else {
			$('.time_area').html(remind_seconds+'초 남았음');
		}
		if(remind_minutes<0){
			//alert('투표 마감되었습니다.');
		}
	}, 1000);

});
</script>
</body>
</html>