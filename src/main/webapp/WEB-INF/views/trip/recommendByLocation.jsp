<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<body>
	<%@ include file="/WEB-INF/views/common/SignIn.jsp"%>
	<%@ include file="/WEB-INF/views/common/nav.jsp"%>


	<diV id="main">
		<!-- ======= Intro Single ======= -->
		<section class="intro-single">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-lg-8">
						<div class="title-single-box">
							<h1 class="title-single">지역별 여행지 추천</h1>
							<!-- <span class="color-text-a">서울</span> -->
						</div>
					</div>
					<div class="col-md-12 col-lg-4">
						<nav aria-label="breadcrumb"
							class="breadcrumb-box d-flex justify-content-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
								<li class="breadcrumb-item"><a
									href="recommendByLocation.html">지역별 여행지 추천</a></li>
								<!-- <li class="breadcrumb-item active" aria-current="page">
                  304 Blaster Up
                </li> -->
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</section>
		<!-- End Intro Single-->

		<div class="d-flex justify-content-center search-spot w-75">
			<form class="d-flex justify-content-center w-100"
				onsubmit="return false;" role="search">
				<select id="search-area" class="form-select me-2 w-100">
					<option value="0" selected>검색 할 지역 선택</option>
					<c:forEach items="${sido}" var="sido">
						<option value="${sido.sidoCode}">${sido.sidoName}</option>
					</c:forEach>
				</select> <select id="search-gugun" class="form-select me-2 w-100">
					<option value="0" selected>검색 할 지역 선택</option>
				</select> <select id="search-content-id" class="form-select me-2 w-100">
					<option value="0" selected>관광지 유형</option>
					<option value="12">관광지</option>
					<option value="14">문화시설</option>
					<option value="15">축제공연행사</option>
					<option value="25">여행코스</option>
					<option value="28">레포츠</option>
					<option value="32">숙박</option>
					<option value="38">쇼핑</option>
					<option value="39">음식점</option>
				</select>
				<button id="btn-search" class="btn btn-outline-success w-50"
					type="button" style="width: 200px">검색</button>
			</form>
		</div>
		<div class="d-flex align-items-top result-spot w-75 ">
			<!-- kakao map start -->
			<div id="map" class="mt-3 " style="width: 700px; height: 700px"></div>
			<!-- kakao map end -->
			<div class="row w-50">
				<table class="table table-hover" style="display: none">
					<thead>
						<tr style="color: #2eca6a; font-weight: bolder;">
							<th>대표이미지</th>
							<th>관광지명</th>
							<th>주소</th>
						</tr>
					</thead>
					<tbody id="trip-list">
					</tbody>
				</table>
			</div>
		</div>
		<!-- 관광지 검색 end -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
			crossorigin="anonymous"></script>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ce68fe8da91c9f36d81376b8131b49e&libraries=services,clusterer,drawing"></script>
		<script>
 
document.getElementById("search-area").addEventListener("change", () =>{
	
	let searchUrl = "http://localhost${root}/trip/attr/";
	let sidoCode = document.getElementById("search-area").value;
	searchUrl += sidoCode;
	
	fetch(searchUrl)
         .then((response) => response.json())
         .then((data) => {
       	  let html = "";
       	  data.gugunList.forEach( item => {
        	  html += "<option value=" + item.gugunCode + ">" + item.gugunName + "</option>";
       	  })
       	  
       	  document.getElementById("search-gugun").innerHTML = html;
         });
})

      // 검색 버튼을 누르면..
      // 지역, 유형, 검색어 얻기.
      // 위 데이터를 가지고 공공데이터에 요청.
      // 받은 데이터를 이용하여 화면 구성.
    document.getElementById("btn-search").addEventListener("click", () => {
      let searchUrl = `http:${root}/trip/attrs?`;

      let sidoCode = document.getElementById("search-area").value;
      let gugunCode = document.getElementById("search-gugun").value;
      let contentTypeId = document.getElementById("search-content-id").value;

      if (parseInt(sidoCode)) searchUrl += "&sidoCode=" + sidoCode;
      if (parseInt(gugunCode)) searchUrl += "&gugunCode=" + gugunCode;
      if (parseInt(contentTypeId)) searchUrl += "&contentTypeId=" + contentTypeId;

	      fetch(searchUrl)
	        .then((response) => response.json())
	        .then((data) => makeList(data));
	  
    });

    var positions; // 위치 배열
    var markers; // 마커 배열
	var normalImageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
	var clickImageSrc = "http://t1.daumcdn.net/localimg/localimages/07/2018/pc/img/marker_spot.png"; 
	var imageSize = new kakao.maps.Size(24, 35);
	
	var normalMarkerImage = new kakao.maps.MarkerImage(normalImageSrc, imageSize);
	var clickMarkerImage = new kakao.maps.MarkerImage(clickImageSrc, imageSize);
	// 마커 이미지를 생성합니다

	var selectedMarker = null;
	
	var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
      level: 5, // 지도의 확대 레벨
    };
	
	var map = new kakao.maps.Map(mapContainer, mapOption);

    function makeList(data) {
	  console.log(data);
      document.querySelector("table").setAttribute("style", "display: ;");

      let tripList = ``;
      var userInfo = `${userInfo}`;
      positions = [];
          data.attrList.forEach((item) => {
          	tripList += 
          		"<tr onclick=" + "\"moveCenter(" + item.latitude + "," + item.longitude + ")\">" +
          			"<td><img src=\"" + item.imgPath  + "\" width=\"100px\" \"></td>" +
          			"<td>" + item.title + "</td>" +
          			"<td>" + item.address1 + " " + item.address2 + "</td>";
          	if(userInfo != 'null'){
          		tripList+= "<td><input type='button' onclick = \"increaseLikeCount(" + item.contentId + ")\" id = \"hotplaceBtn\" name = \"" + item.contentId + "\" style=\"background-color: #00DE38;\" value='핫플 등록!'/></td>";
          	}
              	tripList += "</tr>";

            let markerInfo = {
          		contentId: item.contentId,
    	        title: item.title,
    	        latlng: new kakao.maps.LatLng(item.latitude, item.longitude),
            };
            positions.push(markerInfo);
    });
      
      document.getElementById("trip-list").innerHTML = tripList;
      displayMarker();
    }

      // 카카오지도
      
      function displayMarker() {
        for (var i = 0; i < positions.length; i++) {
        	let curContentId=positions[i].contentId;
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				  map: map, // 마커를 표시할 지도
				  position: positions[i].latlng, // 마커를 표시할 위치
				  title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				  image: normalMarkerImage, // 마커 이미지
				  clickable: true
			});
			var iwContent = "<div style=\"display: flex; justify-content: center; padding:5px;\" >", // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			   iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
			marker.addListener('click', function() {
				if(selectedMarker != null){
					selectedMarker.setImage(normalMarkerImage);
				}
				this.setImage(clickMarkerImage);
				selectedMarker = this;
				
				let searchUrl = `http:${root}/trip/detail?contentId=` + curContentId;
				console.log(searchUrl)
				
				var iwContent = "<div class=\"d-flex flex-column justify-content-center\" style=\"padding:5px; width:500px; height:150px; \">", // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				   iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
				
				fetch(searchUrl
						//,{
					  //headers: {
						//    Accept: "application/json",
						  //},
						  //method: "GET",
						//}
				)
		        .then((response) => response.json())
		        .then(data => {
		        	console.log(data);
		        	let html = "";
		        	html += "<h3 style=\"text-align: left;\">" + data.detailDto.title +"</h3>";
		        	html += "<div style=\"display: flex; align-content: center;  margin-bottom:5px; \">"
			        html += "<img src=\"" + data.detailDto.imgPath + "\" width=30%; height=100%; \">";
			        html += "<div class=\"d-flex flex-column justify-content-center \" style=\"margin-left: 10px;\">";
		        	html += "<div style=\"text-align: left; font-size: 15px\">" + "주소 : " + data.detailDto.address1 + " " + data.detailDto.address2 + "</div>";
		        	html += "<div style=\"text-align: left; font-size: 15px\">" + "우편 번호 : " + data.detailDto.zipCode + "</div>";
		        	html += "<div style=\"text-align: left; font-size: 15px\">" + "전화번호 : " + data.detailDto.telNumber +  "</div>";
		        	html+= "</div>";
		        	html+= "</div>";
		        	
		        	iwContent += html;
		        	iwContent += "</div>";
		        	
					var infowindow = new kakao.maps.InfoWindow({
					    content : iwContent,
					    removable : iwRemoveable
					});
					    
					infowindow.open(map, this);	        	
		        	
		        });		
		    });
			
        }
        
        // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
        map.setCenter(positions[0].latlng);
      }
      

      function moveCenter(lat, lng) {
           map.setCenter(new kakao.maps.LatLng(lat, lng));
      }
      
      function increaseLikeCount(id){
    	  console.log(id);
    	  url = "${root}/trip/hotplace";
    	  fetch(url,{
    		  method: "POST",
    		  headers:{
    			  "Content-Type": `application/json`
    		  },
    		  body: JSON.stringify({
    			  contentId: id,
    			  uid: `${userInfo.id}`,
    			  count: 0,
    		  }),
    	  })
    	  
      }
      



      
    </script>


		</main>
		<!-- End #main -->

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>

</html>