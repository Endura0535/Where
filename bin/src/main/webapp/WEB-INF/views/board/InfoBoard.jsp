<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.ssafy.enjoytrip.model.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript">
window.onload=function(){
	  if (document.querySelector("#search")) {
	    document.querySelector("#search").addEventListener('click', function() {
	      let form = document.querySelector("#frm");
	      document.querySelector("#pageNo").value='1'
	      form.submit();
	    });
	  }
	  if (document.querySelector("#word")) {
	    document.querySelector("#word").value='${param.word}';
	  }
	  <c:choose>
	    <c:when test='${!empty param.pageNo}'>
	      document.querySelector("#pageNo").value='${param.pageNo}';
	    </c:when>
	    <c:otherwise>
	      document.querySelector("#pageNo").value='1';
	    </c:otherwise>
	  </c:choose>
/* 	  <c:if test='${!empty param.key}'>
	    document.querySelector("#key").value='${param.key}';
	  </c:if> */
	  <c:if test='${!empty param.sortKey}'>
	    document.querySelector("#sortKey").value='${param.sortKey}';
	  </c:if>
	  let pages = document.querySelectorAll(".page-link");
	  pages.forEach(function (page) {
	    page.addEventListener("click", function () {
	      document.querySelector("#pageNo").value= this.getAttribute("data-pg");
	      let form = document.querySelector("#frm");
	      form.setAttribute("action", "${root}/article/list");
	      form.submit();
	    });
	  });	
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/SignIn.jsp"%>
	<%@ include file="/WEB-INF/views/common/nav.jsp"%>

	<main id="main">
		<!-- ======= Intro Single ======= -->
		<section class="intro-single">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-lg-8">
						<div class="title-single-box">
							<h1 class="title-single">여행 정보 게시판</h1>
						</div>
					</div>
					<div class="col-md-12 col-lg-4">
						<nav aria-label="breadcrumb"
							class="breadcrumb-box d-flex justify-content-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
								<li class="breadcrumb-item"><a href="#">커뮤니티</a></li>
								<li class="breadcrumb-item"><a href="#">여행 정보 게시판</a></li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</section>
		<!-- End Intro Single-->
		<!-- ======= Blog Single ======= -->
		<section class="news-single nav-arrow-b">
			<div class="container">
				<div>
					<form class="d-flex" id="frm" action="${root}/article/list"
						style="width: 300px; float: left; margin-top: 1.5em;">
						<input type="hidden" name='pageNo' id='pageNo' /> <select
							name="sortKey" id="sortKey"
							class="form-select form-select-sm ms-5 me-1 w-50"
							aria-label="조회조건">
							<option value="articleNo" selected>글번호</option>
							<option value="hit">조회수</option>
							<option value="subject">제목순</option>
						</select>
						<div class="input-group input-group-sm">
							<button id="btn-sort" class="btn btn-dark" type="submit">조회</button>
						</div>
					</form>
				</div>
				<form class="form-a" action="${root}/article/write">
					<div
						class="d-flex justify-content-end align-content-center p-2 m-auto">
						<button id="btn-write" type="submit"
							class="btn btn-b border border-2 rounded-4 text-light"
							style="padding: 0.7rem 1rem">작성하기</button>
					</div>
				</form>
				<div
					class="d-flex flex-column justify-content-end align-content-center p-2 m-auto">
					<table class="table table-hover table-group-divider border-1">
						<%--             <colgroup>
              <col width="5%">
              <col width="45%" href="/board/detail?userId=${userId}">
              <col width="20%">
              <col width="20%">
              <col width="5%">
              <col width="5%">
            </colgroup> --%>
						<thead>
							<tr class="text-center">
								<th class="text-black">No.</th>
								<th class="text-black">제목</th>
								<th class="text-black">작성자</th>
								<th class="text-black">날짜</th>
								<th class="text-black">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${articles}">
								<tr class="text-center">
									<th scope="row">${article.articleNo}</th>
									<td><a href="${root}/article/detail"
										class="article-title link-dark" data-no="${article.articleNo}"
										style="text-decoration: none"> ${article.subject} </a></td>
									<td>${article.id}</td>
									<td>${article.registerTime}</td>
									<td>${article.hit}</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col">${pageBean.pageLink}</div>
				</div>
			</div>
	</main>
	<!-- End #main -->

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>