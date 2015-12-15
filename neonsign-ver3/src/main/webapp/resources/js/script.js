$(document).ready(function(){ //DOM이 준비되고
	
	
	//타이머
	window.setInterval(function(){
		//날짜 형식
		// 2015-12-03 13:05:17
		var updateDates = $('.updateDate');
		var bestMainArticleSNo = $('.bestMainArticleNo'); 
		for(var i=0;i<updateDates.length;i++){
			//현재 시간	
			var currunt_date = new Date();
			var currunt_timestamp = Math.floor(currunt_date.getTime()/1000)
			var updateYear = $(updateDates[i]).val().substring(0,4);
			var updateMonth = $(updateDates[i]).val().substring(5,7);
			var updateDay =$(updateDates[i]).val().substring(8,10);
			var updateHour=$(updateDates[i]).val().substring(11,13);;
			var updateMinute =$(updateDates[i]).val().substring(14,16);
			var second= $(updateDates[i]).val().substring(17,19);
			//완결 시간(서버에서 최종 수정시간을  받아와옴)
			var update_date = new Date(updateYear, (updateMonth-1), updateDay, updateHour, updateMinute, second);
			var update_date_timestamp = Math.floor(update_date.getTime()/1000);
			//투표 마감 시간(10분)
			var close_timestamp = update_date_timestamp+60;
			//
			var remind_timestamp = close_timestamp-currunt_timestamp
			var remind_minutes = Math.floor(remind_timestamp/60);
			var remind_seconds = remind_timestamp%60;
			if(remind_minutes >= 1 && remind_seconds>=10) {
				$('.time_area').eq(i).html(remind_minutes+':'+remind_seconds+'<br>빨리!');
			} else if(remind_minutes == 0 && remind_seconds<=9){
				$('.time_area').eq(i).html('00:0'+remind_seconds+'<br>빨리!');
			} else if(remind_minutes == 0 && remind_seconds>=10){
				$('.time_area').eq(i).html('00:'+remind_seconds+'<br>빨리!');
			}
			var bestMainArticleNo = $('.bestMainArticleNo').eq(i).val();
			if(remind_seconds==0){
				
				$.ajax({
					type : "POST",
					url : "storyLinking.neon",
					data : "mainArticleNo="+bestMainArticleNo,
					dataType:"json",
					success : function(data){
						if(data.result=="complete"){
							/*var msg = bestMainArticleNo+
					          "번이 완결되었습니다. 바로 확인 하실래요?<br/><br/>"+
					          "<center><button class='closeToast' "+
					          "onclick='detailItjaView(mainArticleNO"+bestMainArticleNo+");'>Ok</button> "+
					          "<button class='closeToast'>Cancel</button>";*/
							$('.time_area').eq(i).html('새로고침<br>필요');
						}else if(data.result="continue"){
							/*"번 주제글에 새로운 잇자 타임이 시작되었습니다. 바로 참여 하실래요?<br/><br/>"+
					          "<center><button class='closeToast' "+
					          "onclick='detailItjaView(mainArticleNO"+bestMainArticleNo+");'>Ok</button> "+
					          "<button class='closeToast'>Cancel</button>";
							alert( $('.updateDate').val());*/
							$('.time_area').eq(i).html('새로고침<br>필요');
						}
						$('iframe').attr('src', $('iframe').attr('src'));
						/*$().toasty({
						    autoHide: 2000,
						    message: msg,
						    modal: false
						});*/
					}
				});
				
			}
		}
	}, 1000);
	
	//main 부분
	//무한 스크롤 (테스트 완료 ajax와 연동 필요)
	// hipster 카드에서 동적으로 style을 입혀주지 못하는 문제점이 있어서 반드시 소스를 넣을 때 style을 수기로 기록해줘야함
	$(window).scroll(function(){
		//alert($(".card-box").length);
        if($(window).scrollTop() == $(document).height() - $(window).height()){
           setTimeout(function(){loadingItjaCard();}, 800)
        }  
	});  
	function loadingItjaCard(){
		//현재 페이지 아래 붙을 내용물을 담는다 -대협-
		var infinityScrollTestSource = "";
		//주제글 제목을 담는다 -대협-
		var mainArticleTitle = "";
		//주제글 내용을 담는다 -대협-
		var mainArticleContent = "";
		//불러올 주제글이 더이상 없을때 멈추게한다. -대협-
		if($("#articleEnd").val()!='end'){
			//불러올 주제글의 종류를 구분한다. -대협-
		if($("#articleType").val()=='completeArticle'){
			//카드박스의 갯수로 현재 화면에 있는 주제글 갯수를 파악한다. -대협-
			var cardBox=$(".white-panel").length;
			//현재 카드갯수를 9로 나누고 올림을 하여 현재페이지를 파악한다. -대협-
			var pageNo=Math.ceil((cardBox/8)+1);
			//정렬방식을 담는다. -대협-
			var orderByComp = $("#orderBy").val();
			var tagName = $("#tagName").val();
			$.ajax({
				type:"post",
				url:"getCompleteMainArticle.neon?pageNo="+pageNo+"&tagName="+tagName+"&orderBy="+orderByComp,
				dataType:"json",
				success:function(data){
					if(data.completeMainArticleArrayList.length!=0){
					for(var i=0; i<data.completeMainArticleArrayList.length; i++){
						//잇자버튼을 위한 조건문
						var mainLikeItHTML="";
						  if(data.itjaMemberList!=null){
              					var flag=true;
              					for(var j=0;j<data.itjaMemberList.length;j++){
              						if(data.itjaMemberList[j].mainArticleNo == data.completeMainArticleArrayList[i].mainArticleNo){
              							mainLikeItHTML 
              							='<button class="btn btn-social btn-twitter itja" style="width:23%;">'
              							+'<span class="itjaCount"><i class="fa fa-link"></i><br>'+data.completeMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              							+'<form name="itJaInfo" style="display:none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
              							+'"><input type="hidden" name="mainArticleNo" value="'+data.completeMainArticleArrayList[i].mainArticleNo
              							+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
              							flag=false;
              							break;
              						}	
              					}
              					if(flag){
              						mainLikeItHTML 
              						='<button class="btn btn-social btn-twitter itja" style="width:23%;">'+
              						'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.completeMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'+
              						'<form name="itJaInfo" style="display:none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
              						+'"><input type="hidden" name="mainArticleNo" value="'+data.completeMainArticleArrayList[i].mainArticleNo
              						+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
              					}
              				}
              				if(mainLikeItHTML==""){
              					mainLikeItHTML = 
              						'<button class="btn btn-social btn-twitter itja" style="width:23%;">'+
              						'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.completeMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              				}
              				//잇자버튼 조건 문 끝
              				
              			// 찜 버튼을 위한 조건문
	            			var pickMainArticleHTML="";
	            			if(data.pickedList!=null){
								var pickFlag=true;
	              			for(var j=0;j<data.pickedList.length;j++){
	              				if(data.pickedList[j].mainArticleNo == data.completeMainArticleArrayList[i].mainArticleNo){
	              					pickMainArticleHTML 
	              					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
	              						+"<span class='pickSpan'><i class='fa fa-heart'></i><br>찜!"
	              						+"</span></button>"
	              						+"<form name='pickInfo' style='display:none;'>"
	              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
	              						+"<input type='hidden' name='mainArticleNo' value='"+data.completeMainArticleArrayList[i].mainArticleNo+"'>"
	              						+"</form>";
	           						pickFlag=false;
	       							break;
	      						}	
	       					}
	        				if(pickFlag){
	        					pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
              						+"</span></button>"
              						+"<form name='pickInfo' style='display:none;'>"
              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
              						+"<input type='hidden' name='mainArticleNo' value='"+data.completeMainArticleArrayList[i].mainArticleNo+"'>"
              						+"</form>";
	           					}
	           				}
	            			if(pickMainArticleHTML==""){
	            				pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
									+"</span></button>";
	             			}
              				
						//추가될 카드 html문
						infinityScrollTestSource +=

							'<article class="white-panel"><div class="readArticleBtn">'
							+'<h4><a href="#">' + data.completeMainArticleArrayList[i].mainArticleTitle + '</a></h4>'
							+'<h6 class="category">' + data.completeMainArticleArrayList[i].tagName + '</h6>'
							+'<img src="resources/uploadImg/articleBg/'+data.completeMainArticleArrayList[i].mainArticleImgVO.mainArticleImgName+'" alt="">'
							+'<p class="card-content"/>'
							+'<p class="description">' + data.completeMainArticleArrayList[i].mainArticleContent + '</p>'
							+'<a href="mypage.neon?memberEmail=' + data.completeMainArticleArrayList[i].memberVO.memberEmail + '" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="' + data.completeMainArticleArrayList[i].memberVO.memberNickName + '님, ' + data.completeMainArticleArrayList[i].memberVO.rankingVO.memberGrade + ' PTS(' + data.completeMainArticleArrayList[i].memberVO.memberPoint + ' / ' + data.completeMainArticleArrayList[i].memberVO.rankingVO.maxPoint + ')" data-content="' + data.completeMainArticleArrayList[i].memberVO.memberNickName + '님 Click하여 페이지 보기" >'
							+'<span class="writersNickName">- ' + data.completeMainArticleArrayList[i].memberVO.memberNickName + ' -</span></a></div>'
							+'<div class="social-line social-line-visible" data-buttons="4" style="width:100%;">'
							+'<button class="btn btn-social btn-pinterest" style="width:23%;">'
							+'<span class="time_area">완결된<br>잇자!</span>'
							+'</button>'
							+mainLikeItHTML
							+pickMainArticleHTML
							+'<button class="btn btn-social btn-facebook" style="width:23%;">'
							+'<i class="fa fa-facebook-official"></i><br> 공유!'
							+'</button>'
							+'</div>'
							+'<!-- end social-line social-line-visible -->'
							+'</article>'
					}
					}else{
						infinityScrollTestSource +=
							'<hr><h4>마지막 주제글입니다!</h4>'
							+ '<input type="hidden" id="articleEnd" value="end"><hr>'
					}
					$('.ajaxLoader').fadeOut(300);
					$('#pinBoot').append(infinityScrollTestSource);
				},
			});
		}else if($("#articleType").val()=='mainArticle'){
			var cardBox=$(".white-panel").length;
			var pageNo=Math.ceil((cardBox/8)+1);
			//정렬방식을 담는다. -대협-
			var orderByComp = $("#orderBy").val();
			var tagName = $("#tagName").val();
				$.ajax({
					type:"post",
					url:"getNewMainArticle.neon?pageNo="+pageNo+"&tagName="+tagName+"&orderBy="+orderByComp,
					dataType:"json",
					success:function(data){
						if(data.newMainArticleArrayList.length!=0){
						for(var i=0; i<data.newMainArticleArrayList.length; i++){
							//잇자버튼을 위한 조건문
							var mainLikeItHTML="";
							if(data.itjaMemberList!=null){
								var flag=true;
	              			for(var j=0;j<data.itjaMemberList.length;j++){
	              				if(data.itjaMemberList[j].mainArticleNo == data.newMainArticleArrayList[i].mainArticleNo){
	              					mainLikeItHTML 
	              					='<button class="btn btn-social btn-twitter itja" style="width:100%; margin-top:10px;">'
	              					+'<span class="itjaCount"><i class="fa fa-link"></i><br>'+data.newMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
	              					+'<form name="itJaInfo" style="display:none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
	              					+'"><input type="hidden" name="mainArticleNo" value="'+data.newMainArticleArrayList[i].mainArticleNo
	           						+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
	      							flag=false;
	       							break;
	      						}	
	       					}
	        				if(flag){
	           					mainLikeItHTML 
	           					='<button class="btn btn-social btn-twitter itja" style="width:23%;">'+
	           					'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.newMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'+
	          					'<form name="itJaInfo" style="display:none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
	           					+'"><input type="hidden" name="mainArticleNo" value="'+data.newMainArticleArrayList[i].mainArticleNo
	           					+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
	           					}
	           				}
	            			if(mainLikeItHTML==""){
	            				mainLikeItHTML = 
	          					'<button class="btn btn-social btn-twitter itja" style="width:23%;">'+
	           					'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.newMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
	             			}
	            			
							// alert(data.pickedList);
	            			// 찜 버튼을 위한 조건문
	            			var pickMainArticleHTML="";
	            			if(data.pickedList!=null){
								var pickFlag=true;
	              			for(var j=0;j<data.pickedList.length;j++){
	              				if(data.pickedList[j].mainArticleNo == data.newMainArticleArrayList[i].mainArticleNo){
	              					pickMainArticleHTML 
	              					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
	              						+"<span class='pickSpan'><i class='fa fa-heart'></i><br>찜!"
	              						+"</span></button>"
	              						+"<form name='pickInfo' style='display:none;'>"
	              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
	              						+"<input type='hidden' name='mainArticleNo' value='"+data.newMainArticleArrayList[i].mainArticleNo+"'>"
	              						+"</form>";
	           						pickFlag=false;
	       							break;
	      						}	
	       					}
	        				if(pickFlag){
	        					pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
              						+"</span></button>"
              						+"<form name='pickInfo' style='display:none;'>"
              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
              						+"<input type='hidden' name='mainArticleNo' value='"+data.newMainArticleArrayList[i].mainArticleNo+"'>"
              						+"</form>";
	           					}
	           				}
	            			if(pickMainArticleHTML==""){
	            				pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
									+"</span></button>";
	             			}
							//추가될 카드 html문
							infinityScrollTestSource +=
								'<article class="white-panel"><div class="readArticleBtn">'
								+'<h4><a href="#">' + data.newMainArticleArrayList[i].mainArticleTitle + '</a></h4>'
								+'<h6 class="category">' + data.newMainArticleArrayList[i].tagName + '</h6>'
								+'<img src="resources/uploadImg/articleBg/'+data.newMainArticleArrayList[i].mainArticleImgVO.mainArticleImgName+'" alt="">'
								+'<p class="card-content"/>'
								+'<p class="description">' + data.newMainArticleArrayList[i].mainArticleContent + '</p>'
								+'<a href="mypage.neon?memberEmail=' + data.newMainArticleArrayList[i].memberVO.memberEmail + '" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="' + data.newMainArticleArrayList[i].memberVO.memberNickName + '님, ' + data.newMainArticleArrayList[i].memberVO.rankingVO.memberGrade + ' PTS(' + data.newMainArticleArrayList[i].memberVO.memberPoint + ' / ' + data.newMainArticleArrayList[i].memberVO.rankingVO.maxPoint + ')" data-content="' + data.newMainArticleArrayList[i].memberVO.memberNickName + '님 Click하여 페이지 보기" >'
								+'<span class="writersNickName">- ' + data.newMainArticleArrayList[i].memberVO.memberNickName + ' -</span></a></div>'
								+'<div class="social-line social-line-visible" data-buttons="4" style="width:100%;">'
								+'<button class="btn btn-social btn-pinterest" style="width:23%;">'
								+'<span class="time_area">새로운<br>잇자!</span>'
								+'</button>'
								+mainLikeItHTML
								+pickMainArticleHTML
								+'<button class="btn btn-social btn-facebook" style="width:23%;">'
								+'<i class="fa fa-facebook-official"></i><br> 공유!'
								+'</button>'
								+'</div>'
								+'<!-- end social-line social-line-visible -->'
								+'</article>'
						}
						}else{
							infinityScrollTestSource +=
								'<hr><h4>마지막 주제글입니다!</h4>'
								+ '<input type="hidden" id="articleEnd" value="end"><hr>'
						}
						$('.ajaxLoader').fadeOut(300);
						$('#pinBoot').append(infinityScrollTestSource);
					}
				});
		}
		}
	};
	//무한스크롤 끝
	//tag sort 버튼 활성화
	$('.tags-container>span').hover(function(){
		$(this).attr('class','tag-mouseon');
	},function(){
		$(this).attr('class','tag-mouseoff');
	});
	$('.tags-container>span').click(function(){
		var tagName = $(this);
		var infinityScrollTestSource = "";
		$("#getNowTagName").html('<h4>'+tagName.text()+' 잇자 검색결과</h4>');
		tagName.attr('class','tag-active');
		if($("#articleType").val()=="completeArticle"){
			$.ajax({
				type:"post",
				url:"getCompleteMainArticle.neon?pageNo=1"+"&tagName="+tagName.text().substring(1)+"&orderBy=tag",
				dataType:"json",
				success:function(data){
					infinityScrollTestSource += 
						'<input type="hidden" id="orderBy" value="tag"><input type="hidden" id="tagName" value="'+tagName.text().substring(1)+'">';
					for(var i=0; i<data.completeMainArticleArrayList.length; i++){
						//잇자버튼을 위한 조건문
						var mainLikeItHTML="";
						  if(data.itjaMemberList!=null){
              					var flag=true;
              					for(var j=0;j<data.itjaMemberList.length;j++){
              						if(data.itjaMemberList[j].mainArticleNo == data.completeMainArticleArrayList[i].mainArticleNo){
              							mainLikeItHTML 
              							='<button class="btn btn-social btn-twitter itja" style="width:23%;">'
              							+'<span class="itjaCount"><i class="fa fa-link"></i><br>'+data.completeMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              							+'<form name="itJaInfo" style="display: none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
              							+'"><input type="hidden" name="mainArticleNo" value="'+data.completeMainArticleArrayList[i].mainArticleNo
              							+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
              							flag=false;
              							break;
              						}	
              					}
              					if(flag){
              						mainLikeItHTML 
              						='<button class="btn btn-social btn-twitter itja" style="width:23%;">'
              						+'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.completeMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              						+'<form name="itJaInfo" style="display: none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
              						+'"><input type="hidden" name="mainArticleNo" value="'+data.completeMainArticleArrayList[i].mainArticleNo
              						+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
              					}
              				}
              				if(mainLikeItHTML==""){
              					mainLikeItHTML = 
              						'<button class="btn btn-social btn-twitter itja" style="width:23%;">'+
              						'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.completeMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              				}
              				//잇자버튼 조건 문 끝
						
              			// alert(data.pickedList);
	            			// 찜 버튼을 위한 조건문
	            			var pickMainArticleHTML="";
	            			if(data.pickedList!=null){
								var pickFlag=true;
	              			for(var j=0;j<data.pickedList.length;j++){
	              				if(data.pickedList[j].mainArticleNo == data.completeMainArticleArrayList[i].mainArticleNo){
	              					pickMainArticleHTML 
	              					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
	              						+"<span class='pickSpan'><i class='fa fa-heart'></i><br>찜!"
	              						+"</span></button>"
	              						+"<form name='pickInfo' style='display: none;'>"
	              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
	              						+"<input type='hidden' name='mainArticleNo' value='"+data.completeMainArticleArrayList[i].mainArticleNo+"'>"
	              						+"</form>";
	           						pickFlag=false;
	       							break;
	      						}	
	       					}
	        				if(pickFlag){
	        					pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
              						+"</span></button>"
              						+"<form name='pickInfo' style='display: none;'>"
              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
              						+"<input type='hidden' name='mainArticleNo' value='"+data.completeMainArticleArrayList[i].mainArticleNo+"'>"
              						+"</form>";
	           					}
	           				}
	            			if(pickMainArticleHTML==""){
	            				pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
									+"</span></button>";
	             			}
							//추가될 카드 html문
							infinityScrollTestSource +=
								'<article class="white-panel"><div class="readArticleBtn">'
								+'<h4><a href="#">' + data.completeMainArticleArrayList[i].mainArticleTitle + '</a></h4>'
								+'<h6 class="category">' + data.completeMainArticleArrayList[i].tagName + '</h6>'
								+'<img src="resources/uploadImg/articleBg/'+data.completeMainArticleArrayList[i].mainArticleImgVO.mainArticleImgName+'" alt="">'
								+'<p class="card-content"/>'
								+'<p class="description">' + data.completeMainArticleArrayList[i].mainArticleContent + '</p>'
								+'<a href="mypage.neon?memberEmail=' + data.completeMainArticleArrayList[i].memberVO.memberEmail + '" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="' + data.completeMainArticleArrayList[i].memberVO.memberNickName + '님, ' + data.completeMainArticleArrayList[i].memberVO.rankingVO.memberGrade + ' PTS(' + data.completeMainArticleArrayList[i].memberVO.memberPoint + ' / ' + data.completeMainArticleArrayList[i].memberVO.rankingVO.maxPoint + ')" data-content="' + data.completeMainArticleArrayList[i].memberVO.memberNickName + '님 Click하여 페이지 보기" >'
								+'<span class="writersNickName">- ' + data.completeMainArticleArrayList[i].memberVO.memberNickName + ' -</span></a></div>'
								+'<div class="social-line social-line-visible" data-buttons="4" style="width:100%;">'
								+'<button class="btn btn-social btn-pinterest" style="width:23%;">'
								+'<span class="time_area">완결된<br>잇자!</span>'
								+'</button>'
								+mainLikeItHTML
								+pickMainArticleHTML
								+'<button class="btn btn-social btn-facebook" style="width:23%;">'
								+'<i class="fa fa-facebook-official"></i><br> 공유!'
								+'</button>'
								+'</div>'
								+'<!-- end social-line social-line-visible -->'
								+'</article>'
								
					}
					$('.ajaxLoader').fadeOut(300);
					$('#pinBoot').html(infinityScrollTestSource);
				}
			});
		}else if($("#articleType").val()=="mainArticle"){
			$.ajax({
				type:"post",
				url:"getNewMainArticle.neon?pageNo=1"+"&tagName="+tagName.text().substring(1)+"&orderBy=tag",
				dataType:"json",
				success:function(data){
					infinityScrollTestSource += 
						'<input type="hidden" id="orderBy" value="tag"><input type="hidden" id="tagName" value="'+tagName.text().substring(1)+'">';
					for(var i=0; i<data.newMainArticleArrayList.length; i++){
						//잇자버튼을 위한 조건문
						var mainLikeItHTML="";
						  if(data.itjaMemberList!=null){
              					var flag=true;
              					for(var j=0;j<data.itjaMemberList.length;j++){
              						if(data.itjaMemberList[j].mainArticleNo == data.newMainArticleArrayList[i].mainArticleNo){
              							mainLikeItHTML 
              							='<button class="btn btn-social btn-twitter itja" style="width:23%;">'
              							+'<span class="itjaCount"><i class="fa fa-link"></i><br>'+data.newMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              							+'<form name="itJaInfo" style="display: none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
              							+'"><input type="hidden" name="mainArticleNo" value="'+data.newMainArticleArrayList[i].mainArticleNo
              							+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
              							flag=false;
              							break;
              						}	
              					}
              					if(flag){
              						mainLikeItHTML 
              						='<button class="btn btn-social btn-twitter itja" style="width:23%;">'
              						+'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.newMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              						+'<form name="itJaInfo" style="display: none;"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
              						+'"><input type="hidden" name="mainArticleNo" value="'+data.newMainArticleArrayList[i].mainArticleNo
              						+'"><input type="hidden" name="subArticleNo" value=0></form></button>'
              					}
              				}
              				if(mainLikeItHTML==""){
              					mainLikeItHTML = 
              						'<button class="btn btn-social btn-twitter itja" style="width:23%;">'+
              						'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.newMainArticleArrayList[i].mainArticleTotalLike+' it</span></button>'
              				}
              				
              				//잇자버튼 조건 문 끝
              				
              			// alert(data.pickedList);
	            			// 찜 버튼을 위한 조건문
	            			var pickMainArticleHTML="";
	            			if(data.pickedList!=null){
								var pickFlag=true;
	              			for(var j=0;j<data.pickedList.length;j++){
	              				if(data.pickedList[j].mainArticleNo == data.newMainArticleArrayList[i].mainArticleNo){
	              					pickMainArticleHTML 
	              					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
	              						+"<span class='pickSpan'><i class='fa fa-heart'></i><br>찜!"
	              						+"</span></button>"
	              						+"<form name='pickInfo' style='display: none;'>"
	              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
	              						+"<input type='hidden' name='mainArticleNo' value='"+data.newMainArticleArrayList[i].mainArticleNo+"'>"
	              						+"</form>";
	           						pickFlag=false;
	       							break;
	      						}	
	       					}
	        				if(pickFlag){
	        					pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
              						+"</span></button>"
              						+"<form name='pickInfo' style='display: none;'>"
              						+"<input type='hidden' name='memberEmail' value='"+data.pickedList[0].memberEmail+"'>"
              						+"<input type='hidden' name='mainArticleNo' value='"+data.newMainArticleArrayList[i].mainArticleNo+"'>"
              						+"</form>";
	           					}
	           				}
	            			if(pickMainArticleHTML==""){
	            				pickMainArticleHTML 
	           					="<button class='btn btn-social btn-google pickBtn' style='width:23%;'>"
		           					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
									+"</span></button>";
	             			}
	            			
							
							//추가될 카드 html문
							infinityScrollTestSource +=
								'<article class="white-panel"><div class="readArticleBtn">'
								+'<h4><a href="#">' + data.newMainArticleArrayList[i].mainArticleTitle + '</a></h4>'
								+'<h6 class="category">' + data.newMainArticleArrayList[i].tagName + '</h6>'
								+'<img src="resources/uploadImg/articleBg/'+data.newMainArticleArrayList[i].mainArticleImgVO.mainArticleImgName+'" alt="">'
								+'<p class="card-content"/>'
								+'<p class="description">' + data.newMainArticleArrayList[i].mainArticleContent + '</p>'
								+'<a href="mypage.neon?memberEmail=' + data.newMainArticleArrayList[i].memberVO.memberEmail + '" style="" tabindex="1" class="btn btn-lg btn-warning myNickDetail" role="button" data-toggle="popover" title="' + data.newMainArticleArrayList[i].memberVO.memberNickName + '님, ' + data.newMainArticleArrayList[i].memberVO.rankingVO.memberGrade + ' PTS(' + data.newMainArticleArrayList[i].memberVO.memberPoint + ' / ' + data.newMainArticleArrayList[i].memberVO.rankingVO.maxPoint + ')" data-content="' + data.newMainArticleArrayList[i].memberVO.memberNickName + '님 Click하여 페이지 보기" >'
								+'<span class="writersNickName">- ' + data.newMainArticleArrayList[i].memberVO.memberNickName + ' -</span></a></div>'
								+'<div class="social-line social-line-visible" data-buttons="4" style="width:100%;">'
								+'<button class="btn btn-social btn-pinterest" style="width:23%;">'
								+'<span class="time_area">새로운<br>잇자!</span>'
								+'</button>'
								+mainLikeItHTML
								+pickMainArticleHTML
								+'<button class="btn btn-social btn-facebook" style="width:23%;">'
								+'<i class="fa fa-facebook-official"></i><br> 공유!'
								+'</button>'
								+'</div>'
								+'<!-- end social-line social-line-visible -->'
								+'</article>'
					}
					$('.ajaxLoader').fadeOut(300);
					$('#pinBoot').html(infinityScrollTestSource);
				}
			});
		}
	});	
	
	/**cardDetailView
	 * 카드 클릭시 상세 페이지 보기위해 모달 창열기
	 */
	//디테일 뷰 잇던 자리
	
	//베스트 디테일 뷰(완) , 마이페이지(완)
	/*$('.bestMainArticle').on('click','.actions :button',function(){
		alert('1');
		$('#bestMainArticleArea',parent.document).attr('height','1000px');
		var mainArticleNO =$(this).parent().parent().siblings('input[class="mainArticleTitleNO"]').val();
		detailItjaView(mainArticleNO);
	});*/
	//베스트 디테일 뷰(완) , 마이페이지(완)
	$('.itjaSlide').on('click','.actions :button',function(){
		$('#bestMainArticleArea',parent.document).attr('height','1000px');
		var mainArticleNO =$(this).parent().siblings('input[class="mainArticleTitleNO"]').val();
		if($('#isComplete').val()=='best'){
			detailItjaView(mainArticleNO,'best');
		}else{
			detailItjaView(mainArticleNO);
		}
		
	});
	
	//새로운 글 디테일 뷰(완)
	$('.newMainArticle').on('click','.readArticleBtn',function(){
		var mainArticleNO =$(this).children('#newMainArticleNo').val();
		detailItjaView(mainArticleNO,"new");
		$('#cardDetailView').modal();
	});
	//완결 글 디테일 뷰(완)
	$('.completeMainArticle').on('click','.readArticleBtn',function(){
		var mainArticleNO =$(this).children('#completeMainArticleNo').val();
		detailItjaView(mainArticleNO,"new");
		$('#cardDetailView').modal();
	});
	
	//새로운 글 디테일 뷰(완) - 무한 스크롤 
	$('#pinBoot').on('click','.readArticleBtn',function(){
		var mainArticleNO =$(this).find(':input[name="mainArticleNo"]').eq(0).val();
		detailItjaView(mainArticleNO,"new");
		$('#cardDetailView').modal();
	});
	//완결글 디테일 뷰(완) - 무한 스크롤 
	$('#pinBoot').on('click','.readArticleBtn',function(){
		var mainArticleNO =$(this).next().children().find(':input[name="mainArticleNo"]').eq(0).val();
		detailItjaView(mainArticleNO,"complete");
		$('#cardDetailView').modal();
	});
	
	
	//아이프레임을 제자리로 바꿔줌
	$('#cardDetailView').on('hidden.bs.modal', function (e) {
		$('#bestMainArticleArea',parent.document).attr('height','460px');
	})
	//디테일 뷰 함수 정의
	function detailItjaView(mainArticleNO,isComplete){
		
		if(isComplete=="complete"){
			$('#isComplete').val(isComplete);
		}	
		var updateDates = $('.updateDate');
		var bestMainArticleSNo = $('.bestMainArticleNo'); 
		
		var i =0;
		for(i;i<updateDates.length;i++){
			if($('.bestMainArticleNo').eq(i).val() == mainArticleNO){
				break;
			}
		}
		//타이머
		if(isComplete=="best"){
			window.setInterval(function(){
				//날짜 형식
				// 2015-12-03 13:05:17
				var updateDates = $('.updateDate');
				
					//현재 시간	
					var currunt_date = new Date();
					var currunt_timestamp = Math.floor(currunt_date.getTime()/1000)
					var updateYear = $(updateDates[i]).val().substring(0,4);
					var updateMonth = $(updateDates[i]).val().substring(5,7);
					var updateDay =$(updateDates[i]).val().substring(8,10);
					var updateHour=$(updateDates[i]).val().substring(11,13);;
					var updateMinute =$(updateDates[i]).val().substring(14,16);
					var second= $(updateDates[i]).val().substring(17,19);
					//완결 시간(서버에서 최종 수정시간을  받아와옴)
					var update_date = new Date(updateYear, (updateMonth-1), updateDay, updateHour, updateMinute, second);
					var update_date_timestamp = Math.floor(update_date.getTime()/1000);
					//투표 마감 시간(10분)
					var close_timestamp = update_date_timestamp+60;
					//
					var remind_timestamp = close_timestamp-currunt_timestamp
					var remind_minutes = Math.floor(remind_timestamp/60);
					var remind_seconds = remind_timestamp%60;
					if(remind_minutes >= 1 && remind_seconds>=10) {
						$('.time_area_modal').html(remind_minutes+':'+remind_seconds+'<br>빨리!');
					} else if(remind_minutes == 0 && remind_seconds<=9){
						$('.time_area_modal').html('00:0'+remind_seconds+'<br>빨리!');
					} else if(remind_minutes == 0 && remind_seconds>=10){
						$('.time_area_modal').html('00:'+remind_seconds+'<br>빨리!');
					}
					if(remind_seconds==0){
						//alert('잇자 타임이 종료되었습니다. 재가동 됩니다.');
						detailItjaView(mainArticleNO);
				}
					//$('.time_area_modal').text($('.time_area').eq(i).text());
			}, 1000);
		}
		
		var subAtricleOrder='';
		$.ajax({
			type:"post",
			url:"selectOneNotCompleteMainArticleByMainArticleNo.neon",
			data:"mainArticleNo="+mainArticleNO,
			dataType:"json",
			success:function(data){
				var mainLikeItHTML = "";
				var modalFooterLikeHTML = "";
				var subArticleWriteFormHTML = "";
				var memberEmail=$('#memberUserEmail').val();
				//잇는 글 폼 히든 input에 데이터 할당 
				
				$('form[action="auth_writeSubArticle.neon"]').children('input[name="memberEmail"]').val(data.itjaMemberList[0].memberEmail);
				$('form[action="auth_writeSubArticle.neon"]').children('input[name="mainArticleNo"]').val(mainArticleNO);
					
				// 찜 버튼을 위한 조건문
    			var pickMainArticleHTML="";
    			if(data.pickedList!=null){
					var pickFlag=true;
      			for(var j=0;j<data.pickedList.length;j++){
      				if(data.pickedList[j].mainArticleNo ==mainArticleNO){
      					pickMainArticleHTML 
      					="<button class='btn btn-social btn-google pickBtn'>"
      						+"<span class='pickSpan'><i class='fa fa-heart'></i><br>찜!"
      						+"</span></button>"
   						pickFlag=false;
							break;
						}	
					}
				if(pickFlag){
					pickMainArticleHTML 
   					="<button class='btn btn-social btn-google pickBtn'>"
       					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
  						+"</span></button>"
   					}
   				}
    			if(pickMainArticleHTML==""){
    				pickMainArticleHTML 
   					="<button class='btn btn-social btn-google pickBtn'>"
       					+"<span class='pickSpan'><i class='fa fa-heart-o'></i><br>찜하자!"
						+"</span></button>";
     			}
				
				if(data.itjaMemberList!=null){
					//잇는글폼 활성화
					subArticleWriteFormHTML // 잇는글 쓰는 폼
					='<span class=limitLength>잇자를 누르셨기 때문에 잇는글을 작성하실 수 있습니다! 사용자님의 잇는글 이후로 글을 이어갈지 결말 지을지 정해주세요!</span><br>'
					+'<textarea class="form-control" name="subArticleContent" rows="5" placeholder="잇는글을 입력해주세요 ! (200자로 제한됩니다.)"></textarea>'
					+'<input type="radio" id="radio1" name="isEnd" value="0" checked><label for="radio1">ing</label>'
					+'<input type="radio" id="radio2" name="isEnd" value="1"><label for="radio2">end</label>'
					+'<input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail+'">'
					+'<input type="button" value="잇는글 쓰기" class="subArticleSubmit">'
					+'<input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo+'">'
					+'<div class="limitLength">작성 후 잇자 10개시 베스트로 이동되며,타임체크가 발동됩니다!<span class="userLength"></span>Byte/400Byte</div>'
					var flag=true;
					
					for(var i=0;i<data.itjaMemberList.length;i++){
						if(data.itjaMemberList[i].mainArticleNo == mainArticleNO){
							// 주제글 상단에 있는 잇자 버튼
							mainLikeItHTML 
							='<span class="itjaCount itja"><i class="fa fa-link itja"></i>'+data.mainArticle.mainArticleLike+'it</span>'
							+'<form name="itJaInfo"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
							+'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
							+'"><input type="hidden" name="subArticleNo" value=0></form>'
							
							modalFooterLikeHTML // 모달 하단에 있는 타임체크, 찜, 공유, 잇자 버튼
							='<div class="social-line social-line-visible" data-buttons="4"><button class="btn btn-social btn-pinterest">05:22<br>빨리!</button>'
							+'<button class="btn btn-social btn-twitter itja">'
							+'<span class="itjaCount"><i class="fa fa-link"></i><br>'+data.mainArticle.mainArticleTotalLike+'it</span></button>'
							+pickMainArticleHTML
							+'<button class="btn btn-social btn-facebook"><i class="fa fa-facebook-official"></i><br>공유!</button>'
							+'<form name="itJaInfo"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
							+'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
							+'"><input type="hidden" name="subArticleNo" value=0></form></div>'
							
							$('.itjaWriteForm').css('display','block');	
							flag=false;
						}	
					}
					if(flag){
						mainLikeItHTML 
						='<span class="itjaCount itja"><i class="fa fa-chain-broken"></i>'+data.mainArticle.mainArticleLike+'it</span>'
						+'<form name="itJaInfo"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
						+'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
						+'"><input type="hidden" name="subArticleNo" value=0></form>'
						
						modalFooterLikeHTML 
						='<div class="social-line social-line-visible" data-buttons="4"><button class="btn btn-social btn-pinterest">05:22<br>빨리!</button><button class="btn btn-social btn-twitter itja">'
							+'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.mainArticle.mainArticleTotalLike+'it</span></button>'
							+pickMainArticleHTML
							+'<button class="btn btn-social btn-facebook"><i class="fa fa-facebook-official"></i><br>공유!</button>'
							+'<form name="itJaInfo"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
							+'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
							+'"><input type="hidden" name="subArticleNo" value=0></form></div>'
						//잇는글 폼 비활성화
						$('.itjaWriteForm').css('display','none');	
					}
				}
				if(mainLikeItHTML==""&&modalFooterLikeHTML==""){
					mainLikeItHTML = 
						'<span class="itjaCount itja"><i class="fa fa-chain-broken"></i>'+data.mainArticle.mainArticleLike+'it</span>'
					modalFooterLikeHTML = 
						'<div class="social-line social-line-visible" data-buttons="4"><button class="btn btn-social btn-pinterest">05:22<br>빨리!</button><button class="btn btn-social btn-twitter itja">'+
						'<span class="itjaCount"><i class="fa fa-chain-broken"></i><br>'+data.mainArticle.mainArticleTotalLike+'it</span></button>'
						+pickMainArticleHTML
						+'<button class="btn btn-social btn-facebook"><i class="fa fa-facebook-official"></i><br>공유하자!</button><div>';
					//잇는글 폼 비활성화
					$('.itjaWriteForm').css('display','none');	
				}
				
				
				
				//모달 창 하단에 찜하기,타임체크,잇자 , 공유버튼이 있다.
				$('.detailViewModalUtility').html(modalFooterLikeHTML);
				
				//해당 글에 잇자를 클릭했을 경우 잇는글 폼이 열려있을 것이다.
				$('form[action="auth_writeSubArticle.neon"]').html(subArticleWriteFormHTML);
				$('#cardDetailView .modal-title').text(data.mainArticle.mainArticleTitle);
				$('.mainLikeIt').html(mainLikeItHTML);	
				$('.mainArticleWriterDetail').attr('data-target','#collapseExample'+data.mainArticle.mainArticleNo);
				$('.mainArticleWriterDetailCollapse').attr('id','collapseExample'+data.mainArticle.mainArticleNo);
				$('.writersNickNameAtDetail').text(data.mainArticle.memberVO.memberNickname);
				
				$('#detailSubTable').html("");
					var mainArticle="";
					//작성자가 쓴 주제글이 맨위로 넘어 온다
					//$('.mainCardDetailViewContentNo').text(0);
					$('.mainArticleContent').text(data.mainArticle.mainArticleContent);
					$('.writersNickNameAtDetail').text(data.mainArticle.memberVO.memberNickName);
					$('.timeForDetail').text(data.mainArticle.mainArticleUpdateDate)
					$('.report').html("<span id='mainArticleReportOn'><i class='fa fa-ban'></i></span>" +
						"<form id='subArticleInfo'><input type='hidden' name='mainArticleNo' value="
							+data.mainArticle.mainArticleNo+"><input type='hidden' name='memberEmail' value="+memberEmail+"></form>");
					var mainLikeItHTML = "";
					//이어진 글들은 작성자가 쓴 주제글 밑에 넘어간다
				for(var i=0; i<data.likingSubArticle.length;i++){
					var flag=true;
					if(data.itjaMemberList!=null){
						for(var j=0;j<data.itjaMemberList.length;j++){
							if(data.itjaMemberList[j].subArticleNo == data.likingSubArticle[i].subArticleNo){
								mainLikeItHTML 
								='<span class="itjaCount itja"><i class="fa fa-link"></i><br>'+data.likingSubArticle[i].subArticleLike+'it</span>'
								+'<form name="itJaInfo"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
								+'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
								+'"><input type="hidden" name="subArticleNo" value='+data.likingSubArticle[i].subArticleNo+'></form>'
								flag=false;
								break;
							}
						}
						if(flag){
							mainLikeItHTML 
							='<span class="itjaCount itja"><i class="fa fa-chain-broken"></i><br>'+data.likingSubArticle[i].subArticleLike+'it</span>'+
							'<form name="itJaInfo"><input type="hidden" name="memberEmail" value="'+data.itjaMemberList[0].memberEmail
							+'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
							+'"><input type="hidden" name="subArticleNo" value='+data.likingSubArticle[i].subArticleNo+'></form>'
						}
					}
					if(mainLikeItHTML==""){
						mainLikeItHTML = 
							'<span class="itjaCount itja"><i class="fa fa-chain-broken"></i><br>'+data.likingSubArticle[i].subArticleLike+'it</span></button>'
					}
					var isEnd = "";
					if(data.likingSubArticle[i].isEnd=="0"){
						isEnd="계속";
					}else{
						isEnd="끊자";
					}
					mainArticle=
					mainArticle+'<a class="postForDetail-description mainArticleWriterDetail" data-toggle="collapse" data-target="#'+data.likingSubArticle[i].subArticleNo
					+'" aria-expanded="false" aria-controls="collapseExample1">'
					+'<span class="mainArticleContent">['+isEnd+']'+data.likingSubArticle[i].subArticleContent+'</span><br></a>'
					+'<div class="writersNickNameAtDetail">'+data.likingSubArticle[i].memberVO.memberNickName+'<br></div>'
       				+'<div class="collapse postForDetail-heading mainArticleWriterDetailCollapse" id="'+data.likingSubArticle[i].subArticleNo+'">'
            		+'<div class="pull-left imageForDetail">'
                	+'<img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatarForDetail" alt="user profile image"></div>'
            		+'<div class="pull-left metaForDetail"><div class="titleForDetail h5 "><a href="#" class="writersNickNameAtDetail"><b>'
            		+data.likingSubArticle[i].memberVO.memberNickName+'</b></a></div>'
                    +'<h6 class="text-muted timeForDetail"></h6><a href="#" class="btn btn-default statForDetail-item mainLikeIt">'+mainLikeItHTML
	                +'</a><a href="#" class="btn btn-default statForDetail-item report"><span class="articleReport"><i class="fa fa-ban"></i></span>'
	                +'<form name="subArticleInfo">'
                    +'<input type="hidden" name="memberEmail" value="'+memberEmail
                    +'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
                    +'"><input type="hidden" name="subArticleNo" value='+data.likingSubArticle[i].subArticleNo+'></form></a>'
           			+'</div></div>'
				}
				$('.linkingSubArticleContentInModal').html(mainArticle);//이어진 글 할당
				
				//잇는글은 주제글 아래에 있는 잇는글 전용 테이블에 할당된다
				var mainLikeItHTML = "";
				
				if(data.subArticleVO.length==0){
					
					$('#detailSubTable').html("작성한 잇는글이 없습니다");
					
				}else{
					for(var i=0; i<data.subArticleVO.length;i++){
		                  var flag = true;
		                  if(data.itjaMemberList!=null){
		                     for(var j=0;j<data.itjaMemberList.length;j++){
		                        if(data.itjaMemberList[j].subArticleNo == data.subArticleVO[i].subArticleNo){
		                           mainLikeItHTML 
		                           ='<span class="itjaCount itja"><i class="fa fa-link"></i>'+data.subArticleVO[i].subArticleLike+'it</span>'
		                           flag=false;
		                           break;
		                        }
		                     }
		                     if(flag){
		                        mainLikeItHTML 
		                        ='<span class="itjaCount itja"><i class="fa fa-chain-broken"></i>'+data.subArticleVO[i].subArticleLike+'it</span>'
		                     }
		                  }
		                  if(mainLikeItHTML==""){
		                     mainLikeItHTML ='<span class="itjaCount itja"><i class="fa fa-chain-broken"></i>'+data.subArticleVO[i].subArticleLike+'ijja</span></button>'
		                  }
		                  if(data.subArticleVO[i].block==1){
		                	  subAtricleOrder=subAtricleOrder+"<font color='gray' size='3px'><center>해당 잇는글은 관리자에 의해 삭제된 글입니다.</center>"
		                  }else{
		                	  var isEnd = "";
		  					  if(data.subArticleVO[i].isEnd=="0"){
		  						isEnd="계속";
		  					  }else{
		  						isEnd="끊자";
		  					  }
		  					subAtricleOrder=subAtricleOrder+
		  					'<div class="postForDetail-heading"><div class="pull-left imageForDetail">'
		  					+'<img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatarForDetail" alt="user profile image"></div>'
                			+'<div class="pull-left metaForDetail"><div class="titleForDetail h5"><a href="#"><b>'+ data.subArticleVO[i].memberVO.memberNickName+'</b></a>'
                   			+'</div><h6 class="text-muted timeForDetail">'+data.subArticleVO[i].subArticleDate+'</h6>'
                   			+'<a href="#" class="btn btn-default statForDetail-item mainLikeIt">'+mainLikeItHTML+'</a>'
                   			+'</a><a href="#" class="btn btn-default statForDetail-item report"><span class="articleReport"><i class="fa fa-ban"></i></span>'
        	                +'<form name="subArticleInfo">'
                            +'<input type="hidden" name="memberEmail" value="'+data.subArticleVO[i].memberEmail
                            +'"><input type="hidden" name="mainArticleNo" value="'+data.mainArticle.mainArticleNo
                            +'"><input type="hidden" name="subArticleNo" value='+data.subArticleVO[i].subArticleNo+'></form></a></div>'
                            +'<div class="unLinkingSubArticleContent">['+isEnd+']'+data.subArticleVO[i].subArticleContent+'</div></div>'
		               }
					}
					$('.unLinkingSubArticleList').html(subAtricleOrder);
				}
					if(isComplete=="complete"){
						$('.itjaWriteForm').css('display','none');	
					}
			}
		});
	}
	//끝
	
	
	// 잇는글 신고 버튼 클릭시 실행되는 스크립트
	$('.unLinkingSubArticleList').on('click','.articleReport',function(){
		alert($($(this).next()).html());
		var dataForm=$($(this).next()).serialize();
		if(confirm("해당글을 신고 하시겠습니까?")){
		$.ajax({
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		
			},
			type : "POST",
			url : "auth_ArticleReport.neon",
			data : dataForm,
			success : function(data){
				if(data=="ok"){
				alert("신고를 완료하였습니다.");
				}else{
					alert("이미 신고를 하셨습니다")
				}

			},
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			}
		});
		}
	});
	
	// 이어진 글 신고 버튼 클릭시 실행되는 스크립트
	$('.linkingSubArticleContentInModal').on('click','.articleReport',function(){
		var dataForm=$($(this).next()).serialize();
		if(confirm("해당글을 신고 하시겠습니까?")){
		$.ajax({
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		
			},
			type : "POST",
			url : "auth_ArticleReport.neon",
			data : dataForm,
			success : function(data){
				if(data=="ok"){
					alert("신고를 완료하였습니다.");
					}else{
						alert("이미 신고를 하셨습니다")
					}

			},
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			}
		});
		}
	});
	
	// 주제글 신고 버튼 클릭시 실행되는 스크립트
	$('.metaForDetail').on('click','#mainArticleReportOn',function(){
		var dataForm=$($(this).next()).serialize();
		if(confirm("해당글을 신고 하시겠습니까?")){	
		$.ajax({
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		
			},
			type : "POST",
			url : "auth_ArticleReport.neon",
			data : dataForm,
			success : function(data){
				if(data=="ok"){
					alert("신고를 완료하였습니다.");
					}else{
						alert("이미 신고를 하셨습니다")
					}
	
			},
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			}
		});
		}
		
	});
	//잇는글 작성 제한을 위한 keyUp 이벤트 - 글자수를 제한해준다.
	$('form[action="auth_writeSubArticle.neon"]').on('keyup',$('textarea[name="subArticleContent"]'),function(){
		function korTextCheck($str){
            var str = $str;
            var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
            var result = str.match(check);
            if(result) return true; //한글일 경우
            return false; //한글이 아닐경우
        }	
		var userWriting = $('textarea[name="subArticleContent"]').val();
		var wrtingByte = 0;
		for(var i=0;i<userWriting.length;i++){
			//한글일 경우 2byte
			if(korTextCheck(userWriting.charAt(i))){
				wrtingByte += 2; 
			//영어일 경우 1byte
			}else{
				wrtingByte += 1; 
			}
			if(wrtingByte>400){
				 $('textarea[name="subArticleContent"]').val(userWriting.substring(0,400));
			}
		}
		$('.userLength').text(wrtingByte);
	});
	//잇는글 작성 시 공란체크
	//auth_writeSubArticle.neon
	$('form[action="auth_writeSubArticle.neon"]').on('click','.subArticleSubmit',function(){
		if($('textarea[name="subArticleContent"]').val()==''){
			alert('잇고자하는 내용을 입력해주세요');
			$('textarea[name="subArticleContent"]').focus();
			return false;
		}
		var formData = $('form[action="auth_writeSubArticle.neon"]').serialize();
		$.ajax({
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		
			},
			type : "POST",
			url : "auth_writeSubArticle.neon",
			data : formData,
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			},
			success:function(data){
				if(data.result){
					 detailItjaView(data.subArticleVO.mainArticleNo);
				}else{
					alert('이미 잇는글을 등록하셨습니다. 다음턴에 도전하세요')
					detailItjaView(data.subArticleVO.mainArticleNo);
				}
			}
		});
	});
	
	
	//끌
	
	// 모달 창에서 주제글  잇자 클릭 시 발동하기 (토탈 증가) - 
	$('.detailViewModalUtility').on('click','.itja',function(){
		var formData =  $(this).siblings().eq(3).serialize();
		var itjaCountSpan = $(this).children('.itjaCount');
		itjaClick(formData,itjaCountSpan,'total');
	});
	
	// 모달창에서 주제글 잇자 버튼
	$('.mainLikeIt').on('click','.itja',function(){
		var formData =  $($(this).next()).serialize();
		var itjaCountSpan = $(this);
		itjaClick(formData,itjaCountSpan,'one');
	});
	
	//모달 창에서 이어진 잇는글 클릭시 발동하는 잇자 버튼
	$('.linkingSubArticleContentInModal').on('click','.itja',function(){
		var formData =  $($(this).next()).serialize();
		var itjaCountSpan = $(this);
		itjaClick(formData,itjaCountSpan,'one');
	});
	
	//모달 창에서 아직 안이어진 잇는글들 클릭시 발동하는 잇자 버튼
	$('.unLinkingSubArticleList').on('click','.itja',function(){
		var formData =  $($(this).parent().next().children().next()).serialize();
		var itjaCountSpan = $(this);
		itjaClick(formData,itjaCountSpan,'one');
	});
	
	// 메인 페이지에서  잇자 클릭 시 발동하기
	$('.bestItja').on('click',function(){
		var formData = $($(this).next()).serialize();
		var itjaCountSpan = $(this).children('.itjaCount');
		itjaClick(formData,itjaCountSpan,'total');
		
	});
	
	// 잇자 버튼 
	$('#pinBoot').on('click','.itja',function(){
		var formData = $($(this).siblings('form[name="itJaInfo"]')).serialize();
		var itjaCountSpan = $(this).children('.itjaCount');
		itjaClick(formData,itjaCountSpan,'total');
		
	});
	
	//잇자 클릭 ajax 메서드 통합
	function itjaClick(formData,itjaCountSpan,AllOrOne){
		//var itjaWriteFormCheck=0;
		$.ajax({
			type : "POST",
			url : "auth_itjaClick.neon",
			data : formData,
			success : function(data){
				if(AllOrOne=="total"){
					if(data.itjaSuccess==1){
						//itjaWriteFormCheck=1;
						itjaCountSpan.html('<i class="fa fa-chain-broken"></i><br>'+data.itjaTotalCount+'it');
						//잇는글 폼 비 활성화
						if($('#isComplete').val()!='complete'){
							$('.itjaWriteForm').css('display','none');
						}
					}else{
						itjaCountSpan.html('<i class="fa fa-link"></i><br>'+data.itjaTotalCount+'it');
						//잇는글 폼 활성화
						if($('#isComplete').val()!='complete'){
							$('.itjaWriteForm').css('display','block');	
						}
					}
				}else if(AllOrOne="one"){
					if(data.itjaSuccess==1){
						//1이면 잇자를 하지 않은것 0이면 잇자를 한것
						itjaCountSpan.html('<i class="fa fa-chain-broken"></i><br>'+data.itjaCount+'it');
						//잇는글 폼 비활성화
						if($('#isComplete').val()!='complete'){
							$('.itjaWriteForm').css('display','none');	
						}
					}else{
						itjaCountSpan.html('<i class="fa fa-link"></i><br>'+data.itjaCount+'it');
						//잇는글 폼 활성화
						if($('#isComplete').val()!='complete'){
							$('.itjaWriteForm').css('display','block');	
						}
					}
				}
				if(data.itjaTotalCount==10){
					var msg="새 베스트 잇자 타임이 시작되었습니다. 바로 참여 하실래요?<br/><br/>"+
					"<center><button class='closeToast' "+
					"onclick='detailItjaView(mainArticleNO);'>Ok</button> "+
					"<button class='closeToast'>Cancel</button>";
					$().toasty({
						autoHide: 2000,
						message: msg,
						modal: false
					});
				}
			},
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		
			},
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			}
		});
	}
	
	$('.memberLogin').click(function(){
		$('#loginModal').modal({
			//취소버튼으로만 창을 끌 수 있도록 지정
			backdrop: 'static',
			keyboard: false
		});
		//다시 모달창을 열었을 대 첫 화면 띄움
	});
	/*
	 * 회원이 글쓰기 폼을 열었을 때 인기 태그를 불러오는 에이잭스를 시작으로
	 * 글자수 제한을 위한 keyup이벤트 및 공란체크 그리고 서밋을 담고 잇다.
	 * */
	
	$('.openModalInsertArticleForm').click(function(){
		$.ajax({
			type:"post",
			url:"auth_openMainArticleModal.neon",
			dataType:"json",
			success:function(data){
				tagList = '';
				for(var i=0;i<data.length;i++){
					tagList += 
						'<label><input type="checkbox" name="tagName" value="'+data[i].tagName+'">#'+data[i].tagName+'</label>'+'&nbsp&nbsp&nbsp'
					if(i!=0){
						if(i%6==0){
							tagList +='<br>'
						}
					}
				}
				$('#writeMainArticle').modal({
					//취소버튼으로만 창을 끌 수 있도록 지정
					backdrop: 'static',
					keyboard: false
				});
				
				$('#writeMainArticle').on('shown.bs.modal', function () {
					$('#tagCheck').html(tagList);
					//태그는 2개까지 선택이 가능하도록 강제함
					$('input[name="tagName"]').on('click',function(){
						if($('input[name="tagName"]:checked').length>2){
							alert('태그는 2개 까지만 선택이 가능합니다.');
							$(this).attr("checked", false);
						}
					});
					//태그 공란 체크
					$('button[name="newMainArticleSubmit"]').click(function(){
						if($('input[name="tagName"]:checked').length<1){
							alert('태그는 1개 이상 선택해주세요.');
							return false;
						}
						//제목 공란체크
						if($('input[name="mainArticleTltle"]').val()==''){
							alert('글 제목을 입력해주세요');
							$('input[name="mainArticleTltle"]').focus();
							return false;
						}
						//글 내용 공란 체크
						if($('textarea[name="mainArticleContent"]').val()==''){
							alert('글 내용을 입력해주세요');
							 $('textarea[name="mainArticleContent"]').focus();
							 return false;
						}
						$('form[action="auth_insertNewMainArticle.neon"]').submit();
					});
					//주제글 작성 제한을 위한 keyUp 이벤트 - 글자수를 제한해준다.
					$('textarea[name="mainArticleContent"]').keyup(function(){
						function korTextCheck($str){
				            var str = $str;
				            var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
				            var result = str.match(check);
				            if(result) return true; //한글일 경우
				            return false; //한글이 아닐경우
				        }	
						var userWriting = $(this).val();
						var wrtingByte = 0;
						for(var i=0;i<userWriting.length;i++){
							//한글일 경우 2byte
							if(korTextCheck(userWriting.charAt(i))){
								wrtingByte += 2; 
							//영어일 경우 1byte
							}else{
								wrtingByte += 1; 
							}
							if(wrtingByte>400){
								$('textarea[name="mainArticleContent"]').val(userWriting.substring(0,400));
							}
						}
						$('.userLength').text(wrtingByte);
					});
					
				});
			},
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		
			},
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			}
		});
		
	});
	// 글쓰기 폼 끝
	


	
	//매인 끝
	
	//index부분
	//회원 가입 모달창 띄우는 부분
	//index부분
	//회원 가입 모달창 띄우는 부분
	$('.memberJoinByEmailBtn').click(function(){
		$('#memberJoinByEmailModal').modal({
			//취소버튼으로만 창을 끌 수 있도록 지정
			backdrop: 'static',
			keyboard: false
		});
		//다시 모달창을 열었을 대 첫 화면 띄움
		$('.personInfoForJoin').show();
		$('.babyInfoForJoin').hide();
	});
	
	//모달 창 취소시 경고창 , 
	$('.InfoForJoinCancel').click(function(){
		if(confirm('정말 회원가입을 취소하시겠습니까 ?')){
			$( "#memberJoinByEmail" ).each( function () {
				$('#memberJoinByEmailModal').modal('hide');
				/**
				 * 꼼수의 느낌이 강하지만 소스를 아끼기위함이다.
				 * 실력이 부족해서 서버에 부하를 많이주고 있따.
				 * 열심히하자
				 */
				//폼 초기화를 위한 새로고침
				location.reload();
			});
		}else{
			return;
		};
	});
	/**
	 * 미완성  취소 시 입력 값 및 경고 모두 초기화 시키는 부분
	 */
	$('#memberJoinByEmailModal').on('hide.bs.modal', function () {
			$( "#memberJoinByEmail" ).each( function () {
				this.reset();
				location.reload();
			});
	});
	/**
	 * 미완성 모달 창이 열리는 순간 회원가입 유효성 검증 시작
	 */
	$('#memberJoinByEmailModal').on('show.bs.modal', function () {
			var userMailFlag = false;
			var userNameFlag = false;
			var userPassFlag = false;
			var userRePassFlag = false;
			/**
			 * - Ajax방식의 중복검사 추가해야함
			 */
			//공랑체크 시작
			//이메일 공란 및 정규식을 통한 형태 검사  
			$('#memberJoinInputEmail').keyup(function(){
					/** 이메일 중복체크 
					 * @author 한솔
					 */
					var regEmail=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/;
					var emailComp = $(this).val();
					if(emailComp==""){
						userMailFlag = false;
						$('.emailInput').attr('class','form-group has-feedback emailInput has-error');
						$('.emailInput > .control-label').html('이메일을 입력해주세요');
						$('.emailInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
					}else if(!regEmail.test(emailComp)){
						userMailFlag = false;
						$('.emailInput').attr('class','form-group has-feedback emailInput has-error');
						$('.emailInput > .control-label').html('올바른 이메일을 입력해주세요');
						$('.emailInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
					}else if(emailComp.length<1||emailComp.length>30){
						userMailFlag = false;
						$('.emailInput').attr('class','form-group has-feedback emailInput has-error');
						$('.emailInput > .control-label').html('이메일길이부적합');
						$('.emailInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');	
					}else{
						$.ajax({
							type:"post",
							url:"findMemberByEmail.neon",				
							data:"emailComp="+emailComp,	
							success:function(result){
						if(result==false){
							userMailFlag = false;
						$('.emailInput').attr('class','form-group has-feedback emailInput has-error');
						$('.emailInput > .control-label').html('사용할 수 없는 이메일 입니다');
						$('.emailInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
						}else{
							userMailFlag = true;
						$('.emailInput').attr('class','form-group has-feedback emailInput has-success');
						$('.emailInput > .control-label').html('사용가능한 이메일 입니다');
						$('.emailInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
						}//else2
						}//success
					});//ajax
				}//else
			});//keyup
			//이름 공란 및 길이 체크
			
			$('#memberJoinInputName').keyup(function(){
				/**  닉네임 중복체크 
				 * @author 한솔
				 */

				var nameComp = $(this).val();
				if(nameComp==""){
					userNameFlag = false;
					$('.nameInput').attr('class','form-group has-feedback nameInput has-error');
					$('.nameInput > .control-label').html('닉네임을 입력해주세요');
					$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
				}else if(nameComp.length<1 || nameComp.length>7){
					userNameFlag = false;
					$('.nameInput').attr('class','form-group has-feedback nameInput has-error');
					$('.nameInput > .control-label').html('닉네임은 1글자 이상 ~7글자 이하로 입력해주세요');
					$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
				}else{					
					$.ajax({
						type:"post",
						url:"findMemberByNickName.neon",				
						data:"nameComp="+nameComp,	
						success:function(result){
							//	alert(result);
							if(result==false){
								 userNameFlag = false;
								$('.nameInput').attr('class','form-group has-feedback nameInput has-error');
								$('.nameInput > .control-label').html(nameComp+"사용할수 없는 닉네임 입니다.");
								$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
								  
							}else{
								 userNameFlag = true;
								$('.nameInput').attr('class','form-group has-feedback nameInput has-success');
								$('.nameInput > .control-label').html(nameComp+"사용 가능한 닉네임 입니다");	
								$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
								   
							}//else2			
						}//success			
					});//ajax
				
				}//else1
			
			});//keyup
			//암호 공란 검사 및 암호 길이 검사
			$('#memberJoinInputpassword').keyup(function(){
				var passwordComp = $(this).val();
				if(passwordComp==""){
					userPassFlag = false;
					$('.passInput').attr('class','form-group has-feedback passInput has-error');
					$('.passInput > .control-label').html('암호를 입력해주세요');
					$('.passInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
				}else if(passwordComp.length<7|| passwordComp.length>18){
					userPassFlag = false;
					$('.passInput').attr('class','form-group has-feedback passInput has-error');
					$('.passInput > .control-label').html('암호는 7글자 이상 ~18글자 이하로 입력해주세요');
					$('.passInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
				}else{
					userPassFlag = true;
					$('.passInput').attr('class','form-group has-feedback passInput has-success');
					$('.passInput > .control-label').html('압호가 입력되었습니다.');
					$('.passInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
				};
			});
			//암호확인 공란 검사 및 암호확인 길이 검사 및 암호와 암호확인 값 비교
			$('#memberJoinInputRePassword').keyup(function(){
				var passComp = $('#memberJoinInputpassword').val();
				var rePasswordComp = $(this).val();
				if(rePasswordComp==""){
					userRePassFlag = false;
					$('.rePassInput').attr('class','form-group has-feedback rePassInput has-error');
					$('.rePassInput > .control-label').html('암호를 확인해주세요');
					$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
				}else if(rePasswordComp.length<7 || rePasswordComp.length>18){
					userRePassFlag = false;
					$('.rePassInput').attr('class','form-group has-feedback rePassInput has-error');
					$('.rePassInput > .control-label').html('암호는 7글자 이상 ~18글자 이하로 입력해주세요');
					$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
				}else if(passComp!=rePasswordComp){
					userRePassFlag = false;
					$('.rePassInput').attr('class','form-group has-feedback rePassInput has-error');
					$('.rePassInput > .control-label').html('암호확인이 제대로 이루어지지 않았습니다.');
					$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
				}else{
					userRePassFlag = true;
					$('.rePassInput').attr('class','form-group has-feedback rePassInput has-success');
					$('.rePassInput > .control-label').html('압호가 입력되었습니다.');
					$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
				};
			});
			
			//전송 전 유효성 체크 시발
			$('#submitInfo').click(function(){
				if(userMailFlag){
					if(userNameFlag){
						if(userPassFlag){
							if(userRePassFlag){
								$('#memberJoinByEmail').submit();
							}
						}
					}
				}	
			});
			
		});//모달 유효성 체크

	//로그아웃 confirm
	$("#memberLogout").click(function(){

		if(confirm("로그아웃하시겠습니까?")){
			location.href="memberLogout.neon";
		}else{
			return false;
		}
	});
 	if($("#logout").val()=="0"){
		alert("이메일과 비밀번호가 맞지 않습니다.");
		
	} 
 	/**
	 * 관리자 페이지의 토글 탭
	 */
	$('#myTab a').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		})
	/**
     * 관리자가 회원리스트에서 해당 회원을
     * Block 하는 스크립트
     */
    $('#memberReportList').on('click','.memberBlock',function () {
    	var memberEmail=$(this).parent().parent().children().eq(0).text();
    	if(confirm("해당 회원을 Block 하시겠습니까?")){
    		document.location.href = "memberBlock.neon?memberEmail="+memberEmail;
    	}
		return false;
	});
    /**
     * 관리자가 회원리스트에서 해당 회원을
     * Block 해제 하는 스크립트
     */
	$('#blockMemberReportList').on('click','.memberService',function () {
   	var memberEmail=$(this).parent().parent().children().eq(0).text();
   	if(confirm("해당 회원을 Block해제 하시겠습니까?")){
   		document.location.href = "memberBlockRelease.neon?memberEmail="+memberEmail;
   	}
		return false;
	});

	$('#mainReportList').on('click','.boardReport',function () {
		var subArticleNO=$(this).parent().parent().children().eq(4).text();
		var articleNO=$(this).parent().parent().children().eq(2).text();
		var reportNO=$(this).parent().parent().children().eq(1).text();
		if(confirm("신고처리 하시겠습니까?")){
			location.href="adminPageDeleteArticle.neon?reportNO="+reportNO+"&articleNO="+articleNO+"&subArticleNO="+subArticleNO+
			"&command=report";
		}
	});
	
	$('#mainReportList').on('click','.ReportCancle',function () {
		var subArticleNO=$(this).parent().parent().children().eq(4).text();
		var articleNO=$(this).parent().parent().children().eq(2).text();
		var reportNO=$(this).parent().parent().children().eq(1).text();
		if(confirm("반려처리 하시겠습니까?")){
			location.href="adminPageDeleteArticle.neon?reportNO="+reportNO+"&articleNO="+articleNO+"&subArticleNO="+subArticleNO+
			"&command=cancle";
		}
	});
   
	$('#subReportList').on('click','.boardReport',function () {
		var subArticleNO=$(this).parent().parent().children().eq(4).text();
		var articleNO=$(this).parent().parent().children().eq(2).text();
		var reportNO=$(this).parent().parent().children().eq(1).text();
		if(confirm("신고처리 하시겠습니까?")){
			location.href="adminPageDeleteArticle.neon?reportNO="+reportNO+"&articleNO="+articleNO+"&subArticleNO="+subArticleNO+
			"&command=report";
		}
	});

	$('#subReportList').on('click','.ReportCancle',function () {
		var subArticleNO=$(this).parent().parent().children().eq(4).text();
		var articleNO=$(this).parent().parent().children().eq(2).text();
		var reportNO=$(this).parent().parent().children().eq(1).text();
		if(confirm("반려처리 하시겠습니까?")){
			location.href="adminPageDeleteArticle.neon?reportNO="+reportNO+"&articleNO="+articleNO+"&subArticleNO="+subArticleNO+
			"&command=cancle";
		}
	});
	
	$('.articleReportPaging').click(function () {
		var articleReportList="";
		var pageNo=$($(this).next().children()).val();
		var pageType=$($(this).next().children().eq(1)).val();
		var reportIndex=(pageNo-1)*13;
		alert("type : "+pageType);
		$.ajax({
			type:"post",
			url:"mainreportListPaging.neon",
			data:"pageNo="+pageNo+"&pageType="+pageType,
			dataType:"json",
			success:function(data){ 
				if(pageType=="mainArticleList"){
					alert("주제글");
					for(var i=0; i<data.list.length;i++){
						articleReportList=articleReportList+"<tr><td>"+(reportIndex+i)+"</td><td>"+
						data.list[i].reportNo+"</td><td>"+data.list[i].mainArticleNo+"</td><td>"+
						data.list[i].mainArticleVO[0].mainArticleTitle+"</td><td>"+
						data.list[i].mainArticleVO[0].memberVO.memberNickName+"</td><td>"+
						data.list[i].reportDate+"</td><td>"+data.list[i].reportAmount+"</td><td>"+
						data.list[i].stagesOfProcess+"</td><td><input type='button' value='신고처리' class='boardReport'></td>"+
						"<td><input type='button' value='반려처리' class='ReportCancle'></td></tr>"
					}
					$('#mainReportList').html(articleReportList);
				}else{
					alert("잇는글");
					for(var i=0; i<data.list.length;i++){
						articleReportList=articleReportList+"<tr><td>"+(reportIndex+i)+"</td><td>"+
						data.list[i].reportNo+"</td><td>"+data.list[i].mainArticleNo+"</td><td>"+
						data.list[i].mainArticleVO[0].mainArticleTitle+"</td><td>"+
						data.list[i].mainArticleVO[0].subArticle[0].subArticleNo+"</td><td>"+
						data.list[i].mainArticleVO[0].subArticle[0].subArticleContent+"</td><td>"+
						data.list[i].mainArticleVO[0].subArticle[0].memberVO.memberNickName+"</td><td>"+
						data.list[i].reportDate+"</td><td>"+data.list[i].reportAmount+"</td><td>"+
						data.list[i].stagesOfProcess+"</td><td><input type='button' value='신고처리' class='boardReport'></td>"+
						"<td><input type='button' value='반려처리' class='ReportCancle'></td></tr>"
					}
					$('#subReportList').html(articleReportList);
				}
			}
		});
	});
	$('.memberReportPaging').click(function () {
		var memberReportList="";
		var pageNo=$($(this).next().children()).val();
		var pageType=$($(this).next().children().eq(1)).val();
		$.ajax({
			type:"post",
			url:"memberReportListPaging.neon",
			data:"pageNo="+pageNo+"&pageType="+pageType,
			dataType:"json",
			success:function(data){ 
				if(pageType=="memberList"){
					for(var i=0; i<data.list.length; i++){
						memberReportList=memberReportList+"<tr><td>"+
						data.list[i].memberEmail+"</td><td>"+data.list[i].memberNickName+"</td><td>"+
						data.list[i].memberJoinDate+"</td><td>"+data.list[i].memberPoint+"</td><td>"+
						data.list[i].memberReportAmount+"</td><td>"+
						"<input type='button' value='서비스정지' class='memberBlock'></td>";	
					}
					$('#memberReportList').html(memberReportList);
					
				}else{
					for(var i=0;i<data.list.length;i++){
						memberReportList=memberReportList+"<tr><td>"+
						data.list[i].memberEmail+"</td><td>"+data.list[i].memberNickName+"</td><td>"+
						data.list[i].memberJoinDate+"</td><td>"+data.list[i].memberPoint+"</td><td>"+
						data.list[i].memberReportAmount+"</td><td>"+
						"<input type='button' value='서비스시작' class='memberService'></td>";	
					}
					$('#blockMemberReportList').html(memberReportList);
					
				}
			}
		});
	});
	
	/**
	 * @author JeSeong Lee
	 * 찜 수정
	 * */
		
	// 베스트 찜 클릭 시 발동하기
	$('.staticPick').on('click',function(){
		var formData = $($(this).next()).serialize();
		var pickSpan = $(this).children('.pickSpan');
		pickBtnClick(formData, pickSpan);
	});
	
	// 메인 페이지 동적으로 생성된 새로운 잇자 카드 찜 클릭 시 발동하기
	$('.newItjaList').on('click','.pickBtn',function(){
		var formData = $($(this).next()).serialize();
		var pickSpan = $(this).children('.pickSpan');
		pickBtnClick(formData, pickSpan);
	});
	
	// 완결 페이지 동적으로 생성된 새로운 잇자 카드 찜 클릭 시 발동하기
	$('.completeItjaList').on('click','.pickBtn',function(){
		var formData = $($(this).next()).serialize();
		var pickSpan = $(this).children('.pickSpan');
		pickBtnClick(formData, pickSpan);
	});
	
	// 잇자 버튼 
	$('#pinBoot').on('click','.pickBtn',function(){
		var formData = $($(this).siblings('form[name="pickInfo"]')).serialize();
		var pickSpan = $(this).children('.pickSpan');
		pickBtnClick(formData, pickSpan);
		
	});
	
	// 디테일 뷰의 동적으로 생성된 찜 클릭 시 발동하기
	$('.detailViewModalUtility').on('click','.pickBtn',function(){
		var formData = $($(this).next().next()).serialize(); 
		var pickSpan = $(this).children('.pickSpan');
		//alert("실행됨");
		pickBtnClick(formData, pickSpan);
	});
	
	// alert("파워ON!!!@@@@@");
	function pickBtnClick(formData, pickSpan){
		// alert(formData);
		// alert(pickSpan.html);
		$.ajax({
			type:"post",
			url:"auth_updatePickedVO.neon",
			data:formData,
			success:function(data){
				// alert(data);
				// alert("data : " + pickBtn.html());
				// alert(data.pickResult);
				if(data.pickResult == "insert"){
					// alert("인서트했다");
					pickSpan.html("<i class='fa fa-heart'></i><br>찜!");
				}else{
					// alert("delete 햇다");
					pickSpan.html("<i class='fa fa-heart-o'></i><br>찜하자!");
				}
			},
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		
			},
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			}
		}); // ajax
	}; // pickBtn click
	
	
	/**
	 * 구독하기 
	 * @author JeSeong Lee
	 */
	// 정적인 구독하기 클릭 시 발동하기
	$('.staticSubscriptionBtn').on('click',function(){
		var formData = $($(this).next()).serialize();
		var subscriptionInfoSpan = $(this).children('.subscriptionInfoSpan');
		subscriptionBtnClick(formData, subscriptionInfoSpan);
	});
	
	// $('.staticSubscriptionBtn').click(function(){
	function subscriptionBtnClick(formData, subscriptionInfoSpan){
		// alert(subscriberCount.html());
		// alert(formData);
		// alert(subscriptionInfoSpan.html);
		var subscriptedInfoHTML = "";
		$.ajax({
			type:"post",
			url:"auth_updateSubscriptionInfo.neon",
			data:formData,
			success:function(data){
				if(data.subscriptionResult == "selfSubscription"){
					alert("자신을 구독할 수 없습니다");
				}else if(data.subscriptionResult == "insert"){
					subscriptionInfoSpan.html("<i class='fa fa-minus-square'></i>&nbsp;구독 취소");
				}else if(data.subscriptionResult == "delete"){
					subscriptionInfoSpan.html("<i class='fa fa-plus-square'></i>&nbsp;구독 하기");
				}
				$('.subscriptedCount').html(data.subscriberCount);
				for(var i=0 ; i<data.subscriberMemberList.length ; i++){
					subscriptedInfoHTML += data.subscriberMemberList[i].memberNickName+'<br>';
				}
				$('.subscriptedInfo').html(subscriptedInfoHTML);
				
				
				
			},
			beforeSend : function(xmlHttpRequest){
		           xmlHttpRequest.setRequestHeader("AJAX", "true");
		           
			},
			error:function(xhr, textStatus, error){
				if(xhr.status=="901"){
					if(confirm('로그인이 필요합니다. 가입하시겠어요?')){
						location.href="loginPage.neon"
					}
				}
			}
		}); // ajax
	}; // subscriptionBtn click
	
	
	/**
	 * @author JeSeong Lee
	 * 랭킹 팝오버
	 */
	
	  $('.popover2').popover({ 
		  	trigger:'focus',
		  	placement: 'top',
		  	content: $('#rankingPopover').html(),
		    html: true
	  });
	
	  /**
	   * @author JeSeong Lee
	   * 구독 팝오버
	   */
	  $('.popover3').popover({ 
		  	trigger:'focus',
		  	placement: 'bottom',
		  	content: $('#subscriptedPopover').html(),
		    html: true
	  });
	  $('.popover4').popover({ 
		  	trigger:'focus',
		  	placement: 'bottom',
		  	content: $('#subscriptingPopover').html(),
		    html: true
	  });
	  
	  
	/**
	 * @author JeSeong Lee
	 * 닉네임 팝오버 - 호버
	 */
	
	var popOverSettings = {
			trigger:'hover',
			placement: 'bottom',
		    container: 'body',
		    html: false,
		    selector: '[data-toggle="popover"]', //Sepcify the selector here
		    content: function () {
		        return $('#popover-content').html();
		    }
		}
		$('body').popover(popOverSettings);
	
	/*<!--업데이트 모달창 --!>*/
	$('.memberUpate').click(function(){
		$("#memberUpateModal").modal({
			//취소버튼으로만 창을 끌 수 있도록 지정
			backdrop: 'static',
			keyboard: false
		});
	});

	$("#memberUpateModal").on('show.bs.modal', function () {
		//암호 공란 검사 및 암호 길이 검사
		var userCheckFlag= false;
		var userPassFlag = false;
		var userRePassFlag = false;
		var userNameFlag = true;
		
		
		
		$("#membercheckpassword").keyup(function () {
			var checkPassComp = $(this).val();
			var mailComp=$('#memberJoinupdateEmail').val();
		
			if(checkPassComp==""){
				userCheckFlag = false;
				$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-error');
				$('.checkpassInput > .control-label').html('현재비밀번호를 입력해 주세요');
				$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else if(checkPassComp.length<7 || checkPassComp.length>18){
				userPassFlag = false;
				$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-error');
				$('.checkpassInput > .control-label').html('비밀번호 확인중 입니다');
				$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else{					
				$.ajax({
					type:"post",
					url:"findByPassword.neon",				
					data:"mailComp="+mailComp,	
					success:function(result){
						if(result.memberPassword!=checkPassComp){
							userCheckFlag = false;
							$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-error');
							$('.checkpassInput > .control-label').html("현재 비밀번호 오류");
							$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
							  
						}else{
							userCheckFlag = true;
							$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-success');
							$('.checkpassInput > .control-label').html("확인 되었습니다");	
							$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
							   
						}//else2			
					}//success			
				});//ajax
			
			}//else1
		});
		

		$("#memberupdateInputName").keyup(function(){
			var nameComp = $(this).val();
			var nameCheck= $("#memberupdateInputReName").val();
			if(nameComp==""){
				userNameFlag = false;
				$('.nameInput').attr('class','form-group has-feedback nameInput has-error');
				$('.nameInput > .control-label').html('닉네임을 입력해주세요');
				$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else if(nameComp.length<2 || nameComp.length>8){
				userNameFlag = false;
				$('.nameInput').attr('class','form-group has-feedback nameInput has-error');
				$('.nameInput > .control-label').html('닉네임은 1글자 이상 ~7글자 이하로 입력해주세요');
				$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else if(nameComp==nameCheck){
				userNameFlag = true;
			}else{										 
				$.ajax({
					type:"post",
					url:"findMemberByNickName.neon",				
					data:"nameComp="+nameComp,	
					success:function(result){
						if(result==false){
							 userNameFlag = false;
							$('.nameInput').attr('class','form-group has-feedback nameInput has-error');
							$('.nameInput > .control-label').html(nameComp+"사용할수 없는 닉네임 입니다.");
							$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
							  
						}else{
							 userNameFlag = true;
							$('.nameInput').attr('class','form-group has-feedback nameInput has-success');
							$('.nameInput > .control-label').html(nameComp+"사용 가능한 닉네임 입니다");	
							$('.nameInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
							   
						}//else2			
					}//success			
				});//ajax
			
			}//else1
		
		});//keyup
		$("#memberupdatepassword").keyup(function(){
			var passwordComp = $(this).val();
			if(passwordComp==""){
				userPassFlag = false;
				$('.passInput').attr('class','form-group has-feedback passInput has-error');
				$('.passInput > .control-label').html('암호를 입력해주세요');
				$('.passInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else if(passwordComp.length<7 || passwordComp.length>18){
				userPassFlag = false;
				$('.passInput').attr('class','form-group has-feedback passInput has-error');
				$('.passInput > .control-label').html('암호는 7글자 이상 ~18글자 이하로 입력해주세요');
				$('.passInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else{
				userPassFlag = true;
				$('.passInput').attr('class','form-group has-feedback passInput has-success');
				$('.passInput > .control-label').html('압호가 입력되었습니다.');
				$('.passInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
			}
		});
		//암호확인 공란 검사 및 암호확인 길이 검사 및 암호와 암호확인 값 비교
		$("#memberupdateRepassword").keyup(function(){
			var passComp = $('#memberupdatepassword').val();
			var rePasswordComp = $(this).val();
			if(rePasswordComp==""){
				userRePassFlag = false;
				$('.rePassInput').attr('class','form-group has-feedback rePassInput has-error');
				$('.rePassInput > .control-label').html('암호를 확인해주세요');
				$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else if(rePasswordComp.length<7 || rePasswordComp.length>18){
				userRePassFlag = false;
				$('.rePassInput').attr('class','form-group has-feedback rePassInput has-error');
				$('.rePassInput > .control-label').html('암호는 7글자 이상 ~18글자 이하로 입력해주세요');
				$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else if(passComp!=rePasswordComp){
				userRePassFlag = false;
				$('.rePassInput').attr('class','form-group has-feedback rePassInput has-error');
				$('.rePassInput > .control-label').html('암호확인이 제대로 이루어지지 않았습니다.');
				$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
			}else{
				userRePassFlag = true;
				$('.rePassInput').attr('class','form-group has-feedback rePassInput has-success');
				$('.rePassInput > .control-label').html('압호가 입력되었습니다.');
				$('.rePassInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
			}
		});
		$("#memberUpdateSubmit").click(function(){
			if(userPassFlag&&userRePassFlag&&userNameFlag){
			$("#memberUpdate").submit();
			alert("변경처리되었습니다");
			}else{
				alert('유효성 체크하시오!!');
			}
	});
		});




	//<!--회원탈퇴모달--!>
	$('.memberDelete').click(function(){
		$("#memberDeleteModal").modal({
			//취소버튼으로만 창을 끌 수 있도록 지정
			backdrop: 'static',
			keyboard: false
		});
	});

	var userCheckFlag=false;
	$("#memberDeleteModal").on('show.bs.modal', function () {
		 
	 
	$("#password").keyup(function () {
		var PassComp = $(this).val();
		var mailComp=$('#memberDeleteEmail').val();

		if(PassComp==""){
			userCheckFlag = false;
			$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-error');
			$('.checkpassInput > .control-label').html('현재비밀번호를 입력해 주세요');
			$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
		}else if(PassComp.length<7 || PassComp.length>18){
			userPassFlag = false;
			$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-error');
			$('.checkpassInput > .control-label').html('비밀번호 확인중 입니다');
			$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
		}else{					
			$.ajax({
				type:"post",
				url:"findByPassword.neon",				
				data:"mailComp="+mailComp,	
				success:function(result){
					if(result.memberPassword!=PassComp){
						userCheckFlag = false;
						$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-error');
						$('.checkpassInput > .control-label').html("현재 비밀번호 오류");
						$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-remove form-control-feedback');
						  
					}else{
						userCheckFlag = true;
						$('.checkpassInput').attr('class','form-group has-feedback checkpassInput has-success');
						$('.checkpassInput > .control-label').html("확인 되었습니다");	
						$('.checkpassInput > .glyphicon').attr('class','glyphicon glyphicon-ok form-control-feedback');
						   
					}//else2			
				}//success			
			});//ajax
		
		}//else1


	});
	});

	$("#memberDeleteSubmit").click(function(){		
		//alert(userCheckFlag);
			if(userCheckFlag==true){
				alert("회원탈퇴 되었습니다");
				$("#memberDelete").submit();

			}
	});
	
	//검색 창 드롭다운 메뉴
    $('.search-panel .dropdown-menu').find('a').click(function(e) {
		e.preventDefault();
		var param = $(this).attr("href").replace("#","");
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		$('.input-group #search_param').val(param);
	});
    
    // 마이페이지
    $(".btn-pref .btn").click(function () {
        $(".btn-pref .btn").removeClass("btn-primary").addClass("btn-default");
        // $(".tab").addClass("active"); // instead of this do the below 
        $(this).removeClass("btn-default").addClass("btn-primary");   
    });
    
    //로그인 및 가입 요청창
    function confirmLogin(){
    	if(confirm('로그인이 필요합니다. 가입하실래요?')){
	    	redirect:memberLogin;
	    	}else{return;
	    	}
    }
    // 비밀번호 찾기를 위한 요청 폼 검증
    var userRequestPasswordMailFlag = false;
    $('#recovery-email').keyup(function(){
		var regEmail=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/;
		var emailComp = $(this).val();
		if(emailComp==""){
			userRequestPasswordMailFlag = false;
			$('.alertSpace').html('이메일을 입력해주세요');
		}else if(!regEmail.test(emailComp)){
			userRequestPasswordMailFlag = false;
			$('.alertSpace').html('유효한 이메일을 입력해주세요');
		}else if(emailComp.length<1||emailComp.length>30){
			userRequestPasswordMailFlag = false;
			$('.alertSpace').html('입력한 이메일의 길이가 너무 깁니다.');
		}else{
			$.ajax({
				type:"post",
				url:"findMemberByEmail.neon",				
				data:"emailComp="+emailComp,	
				success:function(result){
	    			if(result==false){
	    				userRequestPasswordMailFlag = true;
	    				$('.alertSpace').html('저희 회원이십니다. 요청하기 버튼을 눌러주세요');
	    			}else{
	    				userRequestPasswordMailFlag = false;
	    				$('.alertSpace').html('저희 회원이 아닙니다.');
	    			}//else2
				}
			});//ajax
		}//success
	});
    // 비밀번호 찾기를 위한 요청 폼 검증
    $('.requestTemporaryPassword').click(function(){
		 if(userRequestPasswordMailFlag){
			 location.href="findPasswordMailRequest.neon?memberEmail="+ $('#recovery-email').val();
		 }else{
			 alert('이메일을 확인해주세요');
			 $('#recovery-email').val('');
			 $('#recovery-email').focus();
		 }
	 });
    // 비밀번호 찾기를 위한 요청 폼 검증 끝
    $("#navbar").ready( function () {
    	$("#serch_result").click( function () {
    		//keyword
    	var text=$("#serch").val();
    	//alert(text);
    	//select
    	var check=$('#search_concept').text();
    	//alert(check);
    	
    	location.href = "findBy.neon?selector="+check+"&keyword="+text;
    	
    	
    	});//click
    	});//serch click
    
    //카드를 위한 js
    
    $(document).ready(function() {
    	$('#pinBoot').pinterest_grid({
    	no_columns: 4,
    	padding_x: 10,
    	padding_y: 10,
    	margin_bottom: 50,
    	single_column_breakpoint: 700
    	});
    	});

    
    	;(function ($, window, document, undefined) {
    	    var pluginName = 'pinterest_grid',
    	        defaults = {
    	            padding_x: 10,
    	            padding_y: 10,
    	            no_columns: 3,
    	            margin_bottom: 50,
    	            single_column_breakpoint: 700
    	        },
    	        columns,
    	        $article,
    	        article_width;

    	    function Plugin(element, options) {
    	        this.element = element;
    	        this.options = $.extend({}, defaults, options) ;
    	        this._defaults = defaults;
    	        this._name = pluginName;
    	        this.init();
    	    }

    	    Plugin.prototype.init = function () {
    	        var self = this,
    	            resize_finish;

    	        $(window).resize(function() {
    	            clearTimeout(resize_finish);
    	            resize_finish = setTimeout( function () {
    	                self.make_layout_change(self);
    	            }, 11);
    	        });

    	        self.make_layout_change(self);

    	        setTimeout(function() {
    	            $(window).resize();
    	        }, 500);
    	    };

    	    Plugin.prototype.calculate = function (single_column_mode) {
    	        var self = this,
    	            tallest = 0,
    	            row = 0,
    	            $container = $(this.element),
    	            container_width = $container.width();
    	            $article = $(this.element).children();

    	        if(single_column_mode === true) {
    	            article_width = $container.width() - self.options.padding_x;
    	        } else {
    	            article_width = ($container.width() - self.options.padding_x * self.options.no_columns) / self.options.no_columns;
    	        }

    	        $article.each(function() {
    	            $(this).css('width', article_width);
    	        });

    	        columns = self.options.no_columns;

    	        $article.each(function(index) {
    	            var current_column,
    	                left_out = 0,
    	                top = 0,
    	                $this = $(this),
    	                prevAll = $this.prevAll(),
    	                tallest = 0;

    	            if(single_column_mode === false) {
    	                current_column = (index % columns);
    	            } else {
    	                current_column = 0;
    	            }

    	            for(var t = 0; t < columns; t++) {
    	                $this.removeClass('c');
    	            }

    	            if(index % columns === 0) {
    	                row++;
    	            }

    	            $this.addClass('c' + current_column);
    	            $this.addClass('r' + row);

    	            prevAll.each(function(index) {
    	                if($(this).hasClass('c' + current_column)) {
    	                    top += $(this).outerHeight() + self.options.padding_y;
    	                }
    	            });

    	            if(single_column_mode === true) {
    	                left_out = 0;
    	            } else {
    	                left_out = (index % columns) * (article_width + self.options.padding_x);
    	            }

    	            $this.css({
    	                'left': left_out,
    	                'top' : top
    	            });
    	        });

    	        this.tallest($container);
    	        $(window).resize();
    	    };

    	    Plugin.prototype.tallest = function (_container) {
    	        var column_heights = [],
    	            largest = 0;

    	        for(var z = 0; z < columns; z++) {
    	            var temp_height = 0;
    	            _container.find('.c'+z).each(function() {
    	                temp_height += $(this).outerHeight();
    	            });
    	            column_heights[z] = temp_height;
    	        }

    	        largest = Math.max.apply(Math, column_heights);
    	        _container.css('height', largest + (this.options.padding_y + this.options.margin_bottom));
    	    };

    	    Plugin.prototype.make_layout_change = function (_self) {
    	        if($(window).width() < _self.options.single_column_breakpoint) {
    	            _self.calculate(true);
    	        } else {
    	            _self.calculate(false);
    	        }
    	    };

    	    $.fn[pluginName] = function (options) {
    	        return this.each(function () {
    	            if (!$.data(this, 'plugin_' + pluginName)) {
    	                $.data(this, 'plugin_' + pluginName,
    	                new Plugin(this, options));
    	            }
    	        });
    	    }

    	})(jQuery, window, document);
    
    
});//document.ready
