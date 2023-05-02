<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp"%>

<body>
	<%@ include file="common/SignIn.jsp"%>
	<%@ include file="common/nav.jsp"%>

	<!-- ======= Intro Section ======= -->
	<div class="intro intro-carousel swiper position-relative">

		<div class="swiper-wrapper">

			<div class="swiper-slide carousel-itemR-a intro-item bg-image"
				style="background-image: url(assets/img/Gwanghwamun.jpg)">
				<div class="overlay overlay-a"></div>
				<div class="intro-content display-table">
					<div class="table-cell">
						<div class="container">
							<div class="row">
								<div class="col-lg-8">
									<div class="intro-body">
										<p class="intro-title-top">
											Jong-ro, Seoul
											<!-- <br> 78345 -->
										</p>
										<h1 class="intro-title mb-4 ">
											<span class="color-b">03045 </span> <br> Gwanghwamun
										</h1>
										<p class="intro-subtitle intro-price">
											<a href="#"><span class="price-a">views more</span></a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="swiper-slide carousel-item-a intro-item bg-image"
				style="background-image: url(assets/img/jamsil-tower.jpg)">
				<div class="overlay overlay-a"></div>
				<div class="intro-content display-table">
					<div class="table-cell">
						<div class="container">
							<div class="row">
								<div class="col-lg-8">
									<div class="intro-body">
										<p class="intro-title-top">
											Song-pa, Seoul <br> 05556
										</p>
										<h1 class="intro-title mb-4">
											<span class="color-b">300 </span> <br> Olympic-ro
										</h1>
										<p class="intro-subtitle intro-price">
											<a href="#"><span class="price-a">views more</span></a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="swiper-slide carousel-item-a intro-item bg-image"
				style="background-image: url(assets/img/Suwon-Hwaseong.jpg)">
				<div class="overlay overlay-a"></div>
				<div class="intro-content display-table">
					<div class="table-cell">
						<div class="container">
							<div class="row">
								<div class="col-lg-8">
									<div class="intro-body">
										<p class="intro-title-top">
											Suwon, Gyeonggi <br> 16255
										</p>
										<h1 class="intro-title mb-4">
											<span class="color-b">21 </span> <br> Changnyong-daero
										</h1>
										<p class="intro-subtitle intro-price">
											<a href="#"><span class="price-a">views more</span></a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="swiper-pagination"></div>
	</div>
	<!-- End Intro Section -->

	<main id="main">
		<!-- ======= Latest Properties Section ======= -->
		<section class="section-property section-t8">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="title-wrap d-flex justify-content-between">
							<div class="title-box">
								<h2 class="title-a">여행지 추천</h2>
							</div>
							<div class="title-link">
								<a href="property-grid.jsp">더 보기 <span
									class="bi bi-chevron-right"></span>
								</a>
							</div>
						</div>
					</div>
				</div>

				<div id="property-carousel" class="swiper">
					<div class="swiper-wrapper">

						<div class="carousel-item-b swiper-slide">
							<div class="card-box-a card-shadow">
								<div class="img-box-a">
									<img src="assets/img/property-6.jpg" alt=""
										class="img-a img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-overlay-a-content">
										<div class="card-header-a">
											<h2 class="card-title-a">
												<a href="property-single.jsp">206 Mount <br /> Olive
													Road Two
												</a>
											</h2>
										</div>
										<div class="card-body-a">
											<div class="price-box d-flex">
												<span class="price-a">rent | $ 12.000</span>
											</div>
											<a href="#" class="link-a">Click here to views <span
												class="bi bi-chevron-right"></span>
											</a>
										</div>
										<div class="card-footer-a">
											<ul class="card-info d-flex justify-content-around">
												<li>
													<h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
												</span>
												</li>
												<li>
													<h4 class="card-info-title">Beds</h4> <span>2</span>
												</li>
												<li>
													<h4 class="card-info-title">Baths</h4> <span>4</span>
												</li>
												<li>
													<h4 class="card-info-title">Garages</h4> <span>1</span>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->

						<div class="carousel-item-b swiper-slide">
							<div class="card-box-a card-shadow">
								<div class="img-box-a">
									<img src="assets/img/property-3.jpg" alt=""
										class="img-a img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-overlay-a-content">
										<div class="card-header-a">
											<h2 class="card-title-a">
												<a href="property-single.jsp">157 West <br /> Central
													Park
												</a>
											</h2>
										</div>
										<div class="card-body-a">
											<div class="price-box d-flex">
												<span class="price-a">rent | $ 12.000</span>
											</div>
											<a href="property-single.jsp" class="link-a">Click here
												to views <span class="bi bi-chevron-right"></span>
											</a>
										</div>
										<div class="card-footer-a">
											<ul class="card-info d-flex justify-content-around">
												<li>
													<h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
												</span>
												</li>
												<li>
													<h4 class="card-info-title">Beds</h4> <span>2</span>
												</li>
												<li>
													<h4 class="card-info-title">Baths</h4> <span>4</span>
												</li>
												<li>
													<h4 class="card-info-title">Garages</h4> <span>1</span>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->

						<div class="carousel-item-b swiper-slide">
							<div class="card-box-a card-shadow">
								<div class="img-box-a">
									<img src="assets/img/property-7.jpg" alt=""
										class="img-a img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-overlay-a-content">
										<div class="card-header-a">
											<h2 class="card-title-a">
												<a href="property-single.jsp">245 Azabu <br /> Nishi
													Park let
												</a>
											</h2>
										</div>
										<div class="card-body-a">
											<div class="price-box d-flex">
												<span class="price-a">rent | $ 12.000</span>
											</div>
											<a href="property-single.jsp" class="link-a">Click here
												to views <span class="bi bi-chevron-right"></span>
											</a>
										</div>
										<div class="card-footer-a">
											<ul class="card-info d-flex justify-content-around">
												<li>
													<h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
												</span>
												</li>
												<li>
													<h4 class="card-info-title">Beds</h4> <span>2</span>
												</li>
												<li>
													<h4 class="card-info-title">Baths</h4> <span>4</span>
												</li>
												<li>
													<h4 class="card-info-title">Garages</h4> <span>1</span>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->

						<div class="carousel-item-b swiper-slide">
							<div class="card-box-a card-shadow">
								<div class="img-box-a">
									<img src="assets/img/property-10.jpg" alt=""
										class="img-a img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-overlay-a-content">
										<div class="card-header-a">
											<h2 class="card-title-a">
												<a href="property-single.jsp">204 Montal <br /> South
													Bela Two
												</a>
											</h2>
										</div>
										<div class="card-body-a">
											<div class="price-box d-flex">
												<span class="price-a">rent | $ 12.000</span>
											</div>
											<a href="property-single.jsp" class="link-a">Click here
												to views <span class="bi bi-chevron-right"></span>
											</a>
										</div>
										<div class="card-footer-a">
											<ul class="card-info d-flex justify-content-around">
												<li>
													<h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
												</span>
												</li>
												<li>
													<h4 class="card-info-title">Beds</h4> <span>2</span>
												</li>
												<li>
													<h4 class="card-info-title">Baths</h4> <span>4</span>
												</li>
												<li>
													<h4 class="card-info-title">Garages</h4> <span>1</span>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->
					</div>
				</div>
				<div class="propery-carousel-pagination carousel-pagination"></div>

			</div>
		</section>
		<!-- End Latest Properties Section -->
		<!-- ======= Latest News Section ======= -->
		<section class="section-news section-t8">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="title-wrap d-flex justify-content-between">
							<div class="title-box">
								<h2 class="title-a">최근 게시글</h2>
							</div>
							<div class="title-link">
								<a href="blog-grid.jsp">더보기 <span
									class="bi bi-chevron-right"></span>
								</a>
							</div>
						</div>
					</div>
				</div>

				<div id="news-carousel" class="swiper">
					<div class="swiper-wrapper">

						<div class="carousel-item-c swiper-slide">
							<div class="card-box-b card-shadow news-box">
								<div class="img-box-b">
									<img src="assets/img/post-2.jpg" alt="" class="img-b img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-header-b">
										<div class="card-category-b">
											<a href="#" class="category-b">House</a>
										</div>
										<div class="card-title-b">
											<h2 class="title-2">
												<a href="blog-single.jsp">House is comming <br> new
												</a>
											</h2>
										</div>
										<div class="card-date">
											<span class="date-b">18 Sep. 2017</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->

						<div class="carousel-item-c swiper-slide">
							<div class="card-box-b card-shadow news-box">
								<div class="img-box-b">
									<img src="assets/img/post-5.jpg" alt="" class="img-b img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-header-b">
										<div class="card-category-b">
											<a href="#" class="category-b">Travel</a>
										</div>
										<div class="card-title-b">
											<h2 class="title-2">
												<a href="blog-single.jsp">Travel is comming <br>
													new
												</a>
											</h2>
										</div>
										<div class="card-date">
											<span class="date-b">18 Sep. 2017</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->

						<div class="carousel-item-c swiper-slide">
							<div class="card-box-b card-shadow news-box">
								<div class="img-box-b">
									<img src="assets/img/post-7.jpg" alt="" class="img-b img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-header-b">
										<div class="card-category-b">
											<a href="#" class="category-b">Park</a>
										</div>
										<div class="card-title-b">
											<h2 class="title-2">
												<a href="blog-single.jsp">Park is comming <br> new
												</a>
											</h2>
										</div>
										<div class="card-date">
											<span class="date-b">18 Sep. 2017</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->

						<div class="carousel-item-c swiper-slide">
							<div class="card-box-b card-shadow news-box">
								<div class="img-box-b">
									<img src="assets/img/post-3.jpg" alt="" class="img-b img-fluid">
								</div>
								<div class="card-overlay">
									<div class="card-header-b">
										<div class="card-category-b">
											<a href="#" class="category-b">Travel</a>
										</div>
										<div class="card-title-b">
											<h2 class="title-2">
												<a href="#">Travel is comming <br> new
												</a>
											</h2>
										</div>
										<div class="card-date">
											<span class="date-b">18 Sep. 2017</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End carousel item -->
					</div>
				</div>

				<div class="news-carousel-pagination carousel-pagination"></div>
			</div>
		</section>
		<!-- End Latest News Section -->
	</main>
	<!-- End #main -->
	<%@ include file="common/footer.jsp"%>
</body>
</html>