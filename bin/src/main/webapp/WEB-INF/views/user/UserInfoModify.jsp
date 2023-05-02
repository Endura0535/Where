<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<body>
	<%@ include file="/common/SignIn.jsp"%>
	<%@ include file="/common/nav.jsp"%>

	<!-- ======= Property Search Section ======= -->
	<div class="click-closed"></div>
	<!--/ Form Search Star /-->
	<div class="box-collapse w-25">
		<div class="title-box-d">
			<h3 class="title-d">Sign In</h3>
		</div>
		<span class="close-box-collapse right-boxed bi bi-x"></span>
		<div class="box-collapse-wrap form">
			<form class="form-a">
				<div class="row border border-1 p-4 rounded-4">
					<div class="col-md-12 mb-2">
						<div class="form-group">
							<label class="pb-2" for="Type">ID</label> <input type="text"
								class="form-control form-control-lg form-control-a"
								placeholder="ID">
						</div>
					</div>

					<div class="col-md-12 mb-2">
						<div class="form-group">
							<label class="pb-2" for="Type">Password</label> <input
								type="password"
								class="form-control form-control-lg form-control-a"
								placeholder="Password">
						</div>
					</div>

					<div
						class="d-flex flex-column align-items-center justify-content-center">
						<button type="submit"
							class="btn btn-b w-100 border border-2 rounded-4 text-light mt-3">
							<strong>로그인</strong>
						</button>
					</div>
				</div>

				<div class="d-flex align-content-center justify-content-center">
					<a href="SignUp.html" class="ms-5 me-5 mt-3 md-3 text-black-50">회원가입</a>
				</div>
			</form>
		</div>
	</div>
	<!-- End Property Search Section -->


	<!-- ======= Header/Navbar ======= -->
	<nav
		class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
		<div class="container">
			<a class="navbar-brand text-brand" href="index.html"><span
				class="color-a">Enjoy</span><span class="color-b">Trip</span></a>

			<ul
				class="navbar-nav me-5 ms-5 d-flex w-75 align-items-center justify-content-center ">
				<li class="nav-item dropdown ms-5 me-5"><a class="nav-link"
					href="recommendByLocation.html" aria-haspopup="true"
					aria-expanded="false">지역별 여행지 추천</a></li>

				<li class="nav-item dropdown me-5 ms-5"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">커뮤니티</a>
					<div class="dropdown-menu">
						<a class="dropdown-item " href="InfoBoard.html">정보</a> <a
							class="dropdown-item " href="HotPlaceBoard.html">Hot Place</a>
						<!-- <a class="dropdown-item " href="agents-grid.html">Agents Grid</a>
              <a class="dropdown-item " href="agent-single.html">Agent Single</a> -->
					</div></li>
			</ul>

			<button type="button"
				class="btn btn-b-n navbar-toggle-box navbar-toggle-box-collapse"
				data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01">
				<i class="bi bi-person-circle"></i>
			</button>

		</div>
	</nav>
	<!-- End Header/Navbar -->

	<main id="main"> <!-- ======= Intro Single ======= -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">회원 정보 수정</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page"><a
								href="MyPage.html">My Page</a></li>
							<li class="breadcrumb-item active" aria-current="page"><a
								href="UserInfoUpdate.html">Update Profile</a></li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single--> <!-- ======= Property Grid ======= -->
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<form action="${root}/user?action=modify" method="post">
					<div class="mb-3">
						<label for="pw">새 비밀번호</label> <input type="text"
							class="form-control" id="pw" name="pw" placeholder="새 비밀번호"
							required>
						<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="pwCheck">비밀번호 확인</label> <input type="text"
							class="form-control" id="pwCheck" name="pwCheck"
							placeholder="비밀번호 확인" required>
						<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="email">이메일</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="${userInfo.email}" required>
						<div class="invalid-feedback">이메일을 입력해주세요.</div>
					</div>

					<div class="mb-3">
						<label for="addr1">주소</label> <input type="text"
							class="form-control" id="addr1" name="addr1"
							placeholder="${userInfo.addr1}" required>
						<div class="invalid-feedback">주소를 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="addr2">상세주소</label> <input type="text"
							class="form-control" id="addr2" name="addr2"
							placeholder="${userInfo.addr2}">
					</div>
					<button class="btn btn-primary btn-lg btn-block" type="submit">수정하기</button>
				</form>
			</div>
		</div>
	</div>


	</main>
	<!-- End #main -->

	<%@ include file="/common/footer.jsp"%>

</body>

</html>