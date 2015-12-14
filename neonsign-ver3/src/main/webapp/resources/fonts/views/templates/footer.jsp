<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer>
        <p>&copy; Company 2014</p>
      </footer>
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
