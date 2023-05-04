<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>EstateAgency Bootstrap Template - Index</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: EstateAgency
  * Updated: Mar 10 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/real-estate-agency-bootstrap-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

	<%@ include file="/common/SignIn.jsp"%>
	<%@ include file="/common/nav.jsp"%>


  <main id="main">

    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container w-75">
        <div class="row w-75">
          <div class="col-md-12 col-lg-8 w">
            <div class="title-single-box">
              <h1 class="title-single">${article.subject}</h1>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">커뮤니티</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">여행 정보 게시판</a>
                </li>
              </ol>
            </nav>
          </div>
        </div>
        <div class="d-flex justify-content-center">
          <div class="post-information w-75">
            <ul class="list-inline text-center color-a d-flex justify-content-end">
              <li class="list-inline-item mr-2">
                <strong>작성자 : </strong>
                <span class="color-text-a">${article.userId}</span>
              </li>
              <li class="list-inline-item mr-2">
                <strong>작성일 : </strong>
                <span class="color-text-a">${article.registerTime}</span>
              </li>
              <li class="list-inline-item">
                <strong>조회 : </strong>
                <span class="color-text-a">${article.hit}</span>
              </li>
              <li class="list-inline-item">
                <strong>댓글 : </strong>
                <span class="color-text-a">4</span>
              </li>
            </ul>
          </div>
        </div>
        
      </div>
    </section><!-- End Intro Single-->

    <!-- ======= Blog Single ======= -->
    <section class="news-single nav-arrow-b">
      <div class="container">
        <div class="row d-flex justify-content-center">
          <div class="col-sm-12 d-flex flex-column align-content-center justify-content-center w-75">
            <div class="post-content color-text-a">
              <pre class="mt-3">
                ${article.content}

              </pre>
            </div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
               		글목록
              </button>
              <c:if test="${userInfo.id eq article.userId}">
	              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
	               	 글수정
	              </button>
	              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
	               	 글삭제
	              </button>
	          </c:if>
            </div>
            <div class="post-footer">
              <div class="post-share d-flex justify-content-end">
                <span>Share: </span>
                <ul class="list-inline socials">
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-facebook" aria-hidden="true"></i>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-twitter" aria-hidden="true"></i>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-instagram" aria-hidden="true"></i>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-linkedin" aria-hidden="true"></i>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="col-md-10 w-75">
            <div class="title-box-d">
              <h3 class="title-d">Comments</h3>
            </div>
            <div class="box-comments">
              <table class="table table-hover">
                <colgroup>
                  <col width="90%">
                </colgroup>
                <tbody>
                  <tr>
                    <div class="d-flex">
                      <img src="assets/img/defaultProfile.jpg" class="img-fluid rounded-circle me-3 ms-3" width="10%">
                      <div class="d-flex flex-column justify-content-center align-items-start">
                        <h4 class="ms-1 mt-0 mb-0">유병익</h4>
                        <h5 class="ms-1 mt-0 mb-0">댓글 내용 댓글 내용 댓글 내용 댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용댓글 내용</h5>
                        <p class="ms-1 mt-0 mb-0">2023-03-17</p>
                      </div>
                    </div>
                  </tr>
                </tbody>
              </table>
              
            </div>
            <div class="form-comments">
              <div class="title-box-d">
                <h3 class="title-d"> Leave a Reply</h3>
              </div>
              <form class="form-a">
                <div class="row">
                  <div class="col-md-12 mb-3">
                    <div class="form-group">
                      <label for="textMessage">댓글을 입력하세요.</label>
                      <textarea id="textMessage" class="form-control" placeholder="Comment *" name="message" cols="45"
                        rows="8" required></textarea>
                    </div>
                  </div>
                  <div class="col-md-12">
                    <button type="submit" class="btn btn-a">댓글 달기</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section><!-- End Blog Single-->

  </main><!-- End #main -->
  
      <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/article?action=list";
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        alert("글 수정 ");
        location.href = "${root}/article?action=mvmodify&articleno=" + ${article.articleNo};
      });
      document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글이 삭제되었습니다.");
        location.href = "${root}/article?action=delete&articleno=" + ${article.articleNo};
      });
    </script>

    <!-- ======= Footer ======= -->
    <section class="section-footer">
      <div class="container">
        <div style="margin: 0% 1% 2% 45%;">
          <h3 class="w-title-a text-brand" style="color :#2eca6a; text-decoration-line: underline;">Site Map</h3>
        </div>
      </div>
      <div class="container">
        <div class="row" style="text-align: center;">
          <div class="col-sm-12 col-md-4 section-md-t3">
            <div class="widget-a">
              <div class="w-header-a">
                <p style="font-size: x-large;"> <b>여행지 찾기</b> </p>
              </div>
              <div class="w-body-a">
                <div class="w-body-a">
                  <ul class="list-unstyled">
                    <li class="item-list-a">
                      <i class="bi bi-chevron-right"></i> <a href="recommendByLocation.html">지역별 관광지 조회</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class="col-sm-12 col-md-4 section-md-t3">
            <div class="widget-a">
              <div class="w-header-a">
                <p style="font-size: x-large;"> <b>커뮤니티</b> </p>
              </div>
              <div class="w-body-a">
                <ul class="list-unstyled">
                  <li class="item-list-a">
                    <i class="bi bi-chevron-right"></i> <a href="#">핫플 등록</a>
                  </li>
                  <li class="item-list-a">
                    <i class="bi bi-chevron-right"></i> <a href="#">게시판</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="col-sm-12 col-md-4 section-md-t3">
            <div class="widget-a">
              <div class="w-header-a">
                <p style="font-size: x-large;"> <b>마이페이지</b> </p>
              </div>
              <div class="w-body-a">
                <ul class="list-unstyled">
                  <li class="item-list-a">
                    <i class="bi bi-chevron-right"></i> <a href="#">나만의 여행 계획</a>
                  </li>
                  <li class="item-list-a">
                    <i class="bi bi-chevron-right"></i> <a href="#">회원 정보 수정/탈퇴</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="socials-a">
              <ul class="list-inline">
                <li class="list-inline-item">
                  <a href="#">
                    <i class="bi bi-facebook" aria-hidden="true"></i>
                  </a>
                </li>
                <li class="list-inline-item">
                  <a href="#">
                    <i class="bi bi-twitter" aria-hidden="true"></i>
                  </a>
                </li>
                <li class="list-inline-item">
                  <a href="#">
                    <i class="bi bi-instagram" aria-hidden="true"></i>
                  </a>
                </li>
                <li class="list-inline-item">
                  <a href="#">
                    <i class="bi bi-linkedin" aria-hidden="true"></i>
                  </a>
                </li>
              </ul>
            </div>
            <div class="copyright-footer">
              <p class="copyright color-text-a">
                &copy; Copyright
                <span class="color-a">SSAFY</span> All Rights Reserved.
              </p>
            </div>
          </div>
        </div>
      </div>
    </footer><!-- End  Footer -->

  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
      class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>