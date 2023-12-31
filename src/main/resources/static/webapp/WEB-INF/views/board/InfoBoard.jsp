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
	  <c:if test='${!empty param.key}'>
	    document.querySelector("#key").value='${param.key}';
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

				<div
					class="d-flex justify-content-end align-content-center p-2 m-auto">
					<button id="btn-write" type="button" class="btn btn-secondary">작성하기</button>
				</div>

				<div
					class="d-flex flex-column justify-content-end align-content-center p-2 m-auto">
					<div>
						<form class="d-flex" id="form-sort" action="${root}/article/list"
							style="width: 300px; float: left">
							<input type="hidden" name='pageNo' id='pageNo' /> <select
								name="key" id="key"
								class="form-select form-select-sm ms-5 me-1 w-50"
								aria-label="조회조건">
								<option value="article_no" selected>글번호</option>
								<option value="view_count">조회수</option>
								<option value="subject">제목순</option>
							</select>
							<div class="input-group input-group-sm">
								<button id="btn-sort" class="btn btn-dark" type="button">조회</button>
							</div>
						</form>
					</div>
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
									<td class="text-start"><a href="#"
										class="article-title link-dark" data-no="${article.articleNo}"
										style="text-decoration: none"> ${article.subject} </a></td>
									<td>${article.userId}</td>
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
			<!-- 			<form id="form-param" method="get" action="">
				<input type="hidden" id="p-action" name="action" value=""> <input
					type="hidden" id="p-pgno" name="pgno" value=""> <input
					type="hidden" id="p-key" name="key" value=""> <input
					type="hidden" id="p-word" name="word" value="">
			</form>
			<script>
				let titles = document.querySelectorAll(".article-title");
				titles
						.forEach(function(title) {
							title
									.addEventListener(
											"click",
											function() {
												console
														.log(this
																.getAttribute("data-no"));
												location.href = "${root}/article?action=view&articleno="
														+ this
																.getAttribute("data-no");
											});
						});

				document.querySelector("#btn-write").addEventListener("click",
						function() {
							location.href = "${root}/article?action=mvwrite";
						});

				document.querySelector("#btn-sort").addEventListener("click",
						function() {
							console.log("sort called");
							let form = document.querySelector("#form-sort");
							form.setAttribute("action", "${root}/article");
							form.submit();
						});

				document.querySelector("#btn-write").addEventListener("click",
						function() {
							location.href = "${root}/article?action=mvwrite";
						});

				let pages = document.querySelectorAll(".page-link");
				pages
						.forEach(function(page) {
							page
									.addEventListener(
											"click",
											function() {
												console
														.log(this.parentNode
																.getAttribute("data-pg"));
												document
														.querySelector("#p-action").value = "list";
												document
														.querySelector("#p-pgno").value = this.parentNode
														.getAttribute("data-pg");
												document
														.querySelector("#p-key").value = "${param.key}";
												document
														.querySelector("#p-word").value = "${param.word}";
												document.querySelector(
														"#form-param").submit();
											});
						});
			</script> -->
	</main>
	<!-- End #main -->

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>