<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <br><br><br>
<!-- main -->

  <div class="tab-content" class="mainList"> 
  <!-- 관리자 홈 --> 
  <div role="tabpanel" class="tab-pane active mainList" id="adminPageHome" >
  
  <div class="container">
	<div class="row">
		<div class="col-sm-3">
    	    <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-users"></i>
                </div>
                <div class="text">
                    <var>${fn:length(requestScope.adminList.memberList.list)}</var>
                    <label class="text-muted">서비스 중인 회원</label>
                </div>
                <div class="options">
                </div>
            </div>
		</div>
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-user-times"></i>
                </div>
                <div class="text">
                    <var>${fn:length(requestScope.adminList.blokcMemberList.list)}</var>
                    <label class="text-muted">서비스 중지인 회원</label>
                </div>
                <div class="options">
                </div>
            </div>
		</div>
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                   <i class="fa fa-spinner"></i>
                </div>
                <div class="text">
                    <var>${fn:length(requestScope.adminList.mainReportList.list)}</var>
                    <label class="text-muted">주제글 신고 수</label>
                </div>
                <div class="options">
                </div>
            </div>
    	</div>
        <div class="col-sm-3">
            <div class="hero-widget well well-sm">
                <div class="icon">
                     <i class="fa fa-circle-o-notch"></i>
                </div>
                <div class="text">
                    <var>${fn:length(requestScope.adminList.subReportList.list)}</var>
                    <label class="text-muted">잇는글 신고 수</label>
                </div>
                <div class="options">
                </div>
            </div>
		</div>
	</div>
</div>

  </div>

<!-- 회원 관리--> 

 <div role="tabpanel" class="tab-pane mainList" id="memberBlock" align="center" >
 	<h3>일반회원 리스트</h3>
		<table border=2 class="table table-hover" id="memberAllList"
			align="center">
			<thead align="center">
				<tr class="success">
					<td>메일</td>
					<td>닉네임</td>
					<td>가입일</td>
					<td>포인트</td>
					<td>신고누적</td>
					<td>서비스</td>
				</tr>
			</thead>
			<tbody align="center" id="memberReportList">
				<c:forEach items="${requestScope.adminList.memberList.list}" var="list">
					<tr>
						<td>${list.memberEmail}</td>
						<td>${list.memberNickName}</td>
						<td>${list.memberJoinDate}</td>
						<td>${list.memberPoint }</td>
						<td>${list.memberReportAmount}</td>
						<td><input type="button" value="서비스정지" class="memberBlock"></td>
					</tr>
				</c:forEach>
			</tbody>
				<tfoot>
				<tr>
					<td colspan="10"><c:if
							test="${requestScope.adminList.memberList.pagingBean.previousPageGroup}">
							<button class="memberReportPaging">◀</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.memberList.pagingBean.previousPage}">
							<input type="hidden" class="pagingType" value="memberList">
							</span>
							<%-- <input type="button"  value="${requestScope.adminList.mainReportList.pagingBean.previousPage}"> --%>
						</c:if> <c:forEach
							begin="${requestScope.adminList.memberList.pagingBean.startPageOfPageGroup }"
							end="${requestScope.adminList.memberList.pagingBean.endPageOfPageGroup }"
							var="i">
							<button class="memberReportPaging">${i}</button>
							<span> 
							<input type="hidden" class="pageNo" value="${i}"> 
							<input type="hidden" class="pagingType" value="memberList">
							</span>&nbsp;</c:forEach> <c:if
							test="${requestScope.adminList.memberList.pagingBean.nextPageGroup}">
							<button class="memberReportPaging">▶</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.memberList.pagingBean.nextPage}">
							<input type="hidden" class="pagingType" value="memberList">
							</span>
						</c:if></td>
				</tr>
			</tfoot>
		</table>
	</div>

<div role="tabpanel" class="tab-pane mainList" id="memberBlocked" align="center" >
 	<h3>불량회원 리스트</h3>
<table border=2 class="table table-hover" id="memberAllList"
			align="center">
			<thead align="center">
				<tr class="success">
					<td>메일</td>
					<td>닉네임</td>
					<td>가입일</td>
					<td>포인트</td>
					<td>신고누적</td>
					<td>서비스</td>
				</tr>
			</thead>
			<tbody align="center" id="blockMemberReportList">
				<c:forEach items="${requestScope.adminList.blokcMemberList.list}" var="list">
					<tr>
						<td>${list.memberEmail}</td>
						<td>${list.memberNickName}</td>
						<td>${list.memberJoinDate}</td>
						<td>${list.memberPoint }</td>
						<td>${list.memberReportAmount}</td>
						<td><input type="button" value="서비스시작" class="memberService"></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10"><c:if test="${requestScope.adminList.blokcMemberList.pagingBean.previousPageGroup}">
							<button class="memberReportPaging">◀</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.blokcMemberList.pagingBean.previousPage}">
							<input type="hidden" class="pagingType" value="blockMemberList">
							</span>
						</c:if> <c:forEach begin="${requestScope.adminList.blokcMemberList.pagingBean.startPageOfPageGroup }"
							end="${requestScope.adminList.blokcMemberList.pagingBean.endPageOfPageGroup }" var="i">
							<button class="memberReportPaging">${i}</button>
							<span> 
							<input type="hidden" class="pageNo" value="${i}"> 
							<input type="hidden" class="pagingType" value="blockMemberList">
							</span>&nbsp;
							</c:forEach>
							<c:if test="${requestScope.adminList.blokcMemberList.pagingBean.nextPageGroup}">
							<button class="memberReportPaging">▶</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.blokcMemberList.pagingBean.nextPage}">
							<input type="hidden" class="pagingType" value="blockMemberList">
							</span>
						</c:if></td>
				</tr> 
			</tfoot>
		</table>   
</div>

<!--주제글 신고 리스트 --> 
    <div role="tabpanel" class="tab-pane mainList"  id="boardReport" >
     	<h3>주제글 신고리스트</h3>
		<table border="1" class="table table-hover">
			<thead align="center">
				<tr class="success">
					<td>신고순서</td>
					<td>신고 번호</td>					
					<td>주제글 번호</td>
					<td>주제글 제목</td>
					<td>주제글 작성자</td>
					<td>신고일</td>
					<td>신고횟수</td>
					<td>처리현황</td>
					<td>신고처리</td>
					<td>반려처리</td>
				</tr>
			</thead>
			<tbody align="center" id="mainReportList">
				<c:forEach items="${requestScope.adminList.mainReportList.list}"
					var="mainReportList" varStatus="i">
					<tr>
						<td>${i.index+1 }</td>
						<td>${mainReportList.reportNo}</td>
						<td>${mainReportList.mainArticleNo}</td>
						<c:forEach items="${mainReportList.mainArticleVO}"
							var="mainArticleVO">
							<td>${mainArticleVO.mainArticleTitle }</td>
							<td>${mainArticleVO.memberVO.memberNickName }</td>
						</c:forEach>
						<td>${mainReportList.reportDate}</td>
						<td>${mainReportList.reportAmount}</td>
						<td>${mainReportList.stagesOfProcess}</td>
						<td><input type="button" value="신고처리" class="boardReport"></td>
						<td><input type="button" value="반려처리" class="ReportCancle"></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10"><c:if
							test="${requestScope.adminList.mainReportList.pagingBean.previousPageGroup}">
							<button class="articleReportPaging">◀</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.mainReportList.pagingBean.previousPage}">
							<input type="hidden" class="pagingType" value="mainArticleList">
							</span>
							<%-- <input type="button"  value="${requestScope.adminList.mainReportList.pagingBean.previousPage}"> --%>
						</c:if> <c:forEach
							begin="${requestScope.adminList.mainReportList.pagingBean.startPageOfPageGroup }"
							end="${requestScope.adminList.mainReportList.pagingBean.endPageOfPageGroup }"
							var="i">
							<button class="articleReportPaging">${i}</button>
							<span> 
							<input type="hidden" class="pageNo" value="${i}">
							<input type="hidden" class="pagingType" value="mainArticleList">
							</span>&nbsp;</c:forEach> <c:if
							test="${requestScope.adminList.mainReportList.pagingBean.nextPageGroup}">
							<button class="articleReportPaging">▶</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.mainReportList.pagingBean.nextPage}">
							<input type="hidden" class="pagingType" value="mainArticleList">
							</span>
						</c:if></td>
				</tr>
			</tfoot>
		</table>
	</div>


<!-- 잇는글 신고 리스트 --> 
<div role="tabpanel" class="tab-pane mainList" id="repleReport" >
     	<h3>잇자글 신고리스트</h3>
		<table border="1" class="table table-hover">
			<thead align="center">
				<tr class="success">
					<td>신고순서</td>
					<td>신고 번호</td>
					<td>주제글 번호</td>
					<td>주제글 제목</td>
					<td>잇자글 번호</td>
					<td>잇자글 내용</td>
					<td>잇자 작성자</td>
					<td>신고일</td>
					<td>신고횟수</td>
					<td>처리현황</td>
					<td>신고처리</td>
					<td>반려처리</td>
				</tr>
			</thead>
			<tbody align="center" id="subReportList">
				<c:forEach items="${requestScope.adminList.subReportList.list }"
					var="subReportList" varStatus="i">
					<tr>
						<td>${i.index+1 }</td>
						<td>${subReportList.reportNo}</td>
						<td>${subReportList.mainArticleNo}</td>
						<c:forEach items="${subReportList.mainArticleVO}"
							var="mainArticleVO">
							<td>${mainArticleVO.mainArticleTitle }</td>
							<c:forEach items="${mainArticleVO.subArticleList}"
								var="subArticleList">
								<td>${subArticleList.subArticleNo }</td>
								<td>${subArticleList.subArticleContent }</td>
								<td>${subArticleList.memberVO.memberNickName }</td>
							</c:forEach>
						</c:forEach>
						<td>${subReportList.reportDate}</td>
						<td>${subReportList.reportAmount}</td>
						<td>${subReportList.stagesOfProcess}</td>
						<td><input type="button" value="신고처리" class="boardReport"></td>
						<td><input type="button" value="반려처리" class="ReportCancle"></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="12"><c:if
							test="${requestScope.adminList.subReportList.pagingBean.previousPageGroup}">
							<button class="articleReportPaging">◀</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.subReportList.pagingBean.previousPage}">
							<input type="hidden" class="pagingType" value="subArticleList">
							</span>
						</c:if> <c:forEach
							begin="${requestScope.adminList.subReportList.pagingBean.startPageOfPageGroup }"
							end="${requestScope.adminList.subReportList.pagingBean.endPageOfPageGroup }"
							var="i">
							<button class="articleReportPaging">${i}</button>
							<span> 
							<input type="hidden" class="pageNo" value="${i}">
							<input type="hidden" class="pagingType" value="subArticleList">
							</span>&nbsp;</c:forEach> <c:if
							test="${requestScope.adminList.subReportList.pagingBean.nextPageGroup}">
							<button class="articleReportPaging">▶</button>
							<span>
							<input type="hidden" class="pageNo" value="${requestScope.adminList.subReportList.pagingBean.nextPage}">
							<input type="hidden" class="pagingType" value="subArticleList">
							</span>
						</c:if></td>
				</tr>
			</tfoot>
		</table>
	</div>


<!-- 문의글 리스트 -->
<div role="tabpanel" class="tab-pane mainList" id="Questions">
	<h1>문의글 보기</h1>
<table border="1" class="table table-hover">
	<tr class="success">
		<td>NO</td><td>TITLE</td><td>DATE</td><td>EMAIL</td>
	</tr>
	<tbody align="center" id="serviceCenterList" class="serviceCenterList">
	<c:forEach items="${requestScope.adminList.serviceCenterList.serviceCenterVO}" var="list"  varStatus="i">
		<tr  id="pagetitle">
			<td>${i.index+1 }</td>
			<td><a href="#" class="ServiceCenterView" >${list.serviceCenterTitle}</a>
						<input type="hidden" value="${list.serviceCenterNo}" class="ServiceCenterNo"></td>
			<td>${list.serviceCenterDate}</td>
			<td>${list.serviceCenterEmail}</td>
		</tr>
	</c:forEach>
	</tbody>
	<tfoot>
	<tr><td colspan="4">
	<c:if test="${requestScope.adminList.serviceCenterList.pagingBean.previousPageGroup}" >
	<a href="${initParam.root}ServiceCenterList.neon?pageNo=${requestScope.adminList.serviceCenterList.pagingBean.previousPage}">◀</a>
	</c:if>
	<c:forEach begin="${requestScope.adminList.serviceCenterList.pagingBean.startPageOfPageGroup}"  end="${requestScope.adminList.serviceCenterList.pagingBean.endPageOfPageGroup}" var="i">
	<button class="serviceCenterPaging" >${i}</button>

	<span> 
	<input type="hidden" class="pageNo" value="${i}">
	<input type="hidden" class="pagingType" value="serviceCenterList">
	</span>
	</c:forEach>
	<c:if test="${requestScope.adminList.serviceCenterList.pagingBean.nextPageGroup}">
	<a href="${initParam.root}ServiceCenterList.neon?pageNo=${requestScope.adminList.serviceCenterList.pagingBean.nextPage}">▶</a>
	</c:if>
	</td></tr>
	</tfoot>
	</table>
 </div>
  </div>
<!-- 끝 -->
