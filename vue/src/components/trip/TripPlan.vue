<template>
	<div id="main">
		<!-- ======= Intro Single ======= -->

		<section class="intro-single">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-lg-8">
						<div class="title-single-box">
							<h1 class="title-single">나의 여행 계획</h1>
							<!-- <span class="color-text-a">서울</span> -->
						</div>
					</div>
					<div class="col-md-12 col-lg-4">
						<nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
								<li class="breadcrumb-item"><a href="recommendByLocation.html">나의 여행 계획</a></li>
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

		<div class="d-flex justify-content-center search-spot" id="searchForm">
			<form class="d-flex justify-content-center w-100" onsubmit="return false;" role="search">
				<select id="search-area" class="form-select me-2 w-100" v-on:change="getGugunList($event)"
					:value="sidoCode">
					<option :value="null">시도 선택</option>
					<option v-for="sido in sidoList" :key="sido.sidoCode" :value="sido.sidoCode">{{ sido.sidoName }}
					</option>
				</select>
				<select id="search-gugun" class="form-select me-2 w-100" v-on:change="setGugun($event)" :value="gugunCode">
					<option :value="null">구군 선택</option>
					<option v-for="gugun in gugunList" :key="gugun.gugunCode" :value="gugun.gugunCode">{{ gugun.gugunName }}
					</option>
				</select>
				<select id="search-content-id" class="form-select me-2 w-100" v-on:change="setcontentTypeId($event)"
					:value="contentTypeId">
					<option :value="null">관광지 유형</option>
					<option value="12">관광지</option>
					<option value="14">문화시설</option>
					<option value="15">축제공연행사</option>
					<option value="25">여행코스</option>
					<option value="28">레포츠</option>
					<option value="32">숙박</option>
					<option value="38">쇼핑</option>
					<option value="39">음식점</option>
				</select>
				<button id="btn-search" class="btn btn-outline-success w-50" type="button" style="width: 200px"
					@click="getAttrList()">검색</button>
			</form>
		</div>



		<div class="d-flex align-items-top result-spot w-75 ">
			<!-- kakao map start -->
			<!-- <div id="map" class="mt-3 " style="width: 700px; height: 700px"></div>> -->
			<kakaomap ref="kakaomap"></kakaomap>
			<!-- kakao map end -->
			<div style="width:5%"></div>

			<draggable v-if="attrList.length" 
					class="dragArea list-group"
					:group="{ name: 'people', pull: 'clone', put: false }"	
					:list = "attrList"
					@change="log">
						<tr v-for= "trip in attrList" :key="trip.contentId" :value="trip.contentId" v-bind="attrList"  class="list-group-item" > 
												<td :value="trip" @click= "moveCenter(trip)"><img :src= trip.imgPath width= 100px></td>
												<td :value="trip" @click= "moveCenter(trip)">{{ trip.title }}</td>
												<!-- <td :value="trip" @click= "moveCenter(trip)">{{trip.address1}} {{trip.address2}} </td> -->
											<td  :value="trip"><input type='button' @click= "add(trip)" id = "addButton" :name = trip.contentId  value='+' ></td>
								
						</tr>
							
			</draggable>
			
			<div>
			<h3>
               <input type = "text" v-model="planTitle" value="내 일정">
			</h3>

			<div>
			
			<button @click="addPlan()">여행일 추가</button>
			<button @click="print()">출력</button>
			여행 시작일
			<input type="date" name="startday" v-model="startDay">
			</div>
			<div>
				<button v-for="(trip, index) in myPlan" :key="index" @click="showList(index)">
					<!-- <v-if= "this.selectedMyPlan.length> 1">  -->
					day {{ index + 1 }}
				</button>
			</div>

			<div>
				<draggable v-if="this.selectedMyPlan !== null && this.selectedMyPlan.length"
				class="dragArea list-group"
				:list="selectedMyPlan"
				group= 'people'
				@change="addPoint()"	
				v-bind="selectedMyPlan"
			>
				<div  class="list-group-item" v-for="(trip,index) in selectedMyPlan" :key="trip.contentId" v-bind="selectedMyPlan">
					<td><img :src = trip.imgPath width= 100px></td>
					<td>{{ trip.title }}<input type='button' @click= "pop(index)" id = "addButton" :name = trip.contentId  value='-' ></td>
				</div>
				<button @click="submitPlan()">플랜 생성</button>
			</draggable>
			</div>
			</div>
			
			
			</div>
			</div>
</template>


<script>
import http from '@/api/http';
import kakaomap from '@/components/trip/KakaoMap';
import store from "@/store";
import draggable from "vuedraggable"
export default {
	name: "TripPlan",
	components: {
		kakaomap,
		draggable,
	},
	data() {
		return {
			startDay: '',
			planTitle : '',
			sidoCode: '',
			gugunCode: '',
			contentTypeId: '',
			gugunList: [],
			attrList: [],
			sidoList: [],
			myPlan: [],
			selectedMyPlan: [],
			planName: '',
			user : store.getters["memberStore/checkUserInfo"].id,
		}
	},
	created() {
		this.getSidoList();
		if (this.$route.params) {
			
			this.planName = this.$route.params.planName;
			console.log(this.planName);
			console.log(this)
			http.get(`plan/list?planName=${this.planName}&uid=${this.user}`)
				.then(response => {   //200 ok 됐을때  응답 데이타를 받았을 때
					var maxDay = 0;
					var data = response.data;
					console.log(data);
					for (let i = 0; i < data.length; i++){
						if (data[i].dayNo > maxDay)
							maxDay = data[i].dayNo;
					}
					console.log(maxDay);
					for (let i = 0; i < maxDay; i++){
						this.myPlan.push([]);
					}
					

					console.log(this.myPlan.length);
					var result = data.sort((a, b) => {
						return a.orderNo - b.orderNo;
					})

					console.log("result")
					console.log(result);
					for (let i = 0; i < result.length; i++){
						var contentId = result[i].attractionId;
						
						var detail;
						http.get(`trip/detail?contentId=${contentId}`)
							.then(response => {
								console.log(response)
								detail = response.data.detailDto;
								
								console.log("배열")
								var list = this.myPlan[result[i].dayNo-1]
								console.log(list);
								list.push(detail)
							})
					}
					
				})
				.catch(err => {
					console.log(err)
					
				})
		}
		
	},
	methods: {
		log: function (evt) {
			window.console.log(evt);
			console.log(this.myPlan);
		},
		add: function (trip) {
			console.log("added......")
			console.log(trip)
			this.selectedMyPlan.push(trip);
			this.addPoint();
		},
		pop: function (index) {
			console.log("deleted......")
			this.selectedMyPlan.splice(index, 1);
			this.addPoint();
		},
		addPlan: function () {
			this.days++;
			this.myPlan.push([]);
		},
		showList: function (index) {
			this.selectedMyPlan = this.myPlan[index];
			this.addPoint();
			this.moveCenter(this.selectedMyPlan[0]);
		},
		submitPlan() {
			const checkUserInfo = store.getters["memberStore/checkUserInfo"];
			console.log(checkUserInfo.id);
			//todo
			//입력 검증 
			const submitPlan = [];
			for (var day = 0; day < this.myPlan.length; day++){
				for (var order = 0; order < this.myPlan[day].length; order++){
					var plan = {
						uid: checkUserInfo.id,
						planName: this.planTitle,
						dayNo: 0,
						orderNo: 0,
						attractionId: 0,
						startDay: this.startDay,
					};
					plan.dayNo = day + 1;
					plan.orderNo = order + 1;
					plan.attractionId = this.myPlan[day][order].contentId;
					submitPlan.push(plan);
				}
			}

			console.log(submitPlan);

			let planData = JSON.stringify(submitPlan);
			console.log(planData);
			http.post(`/plan`, {	
				submitPlan,
			});
			alert("플랜 생성 완료!")
			this.$router.push({"name" : "TripPlanList"})
		},
		print() {
			console.log(JSON.stringify(this.myPlan))
			console.log(this.myPlan.length)
			console.log(this.startDay);
		},
		getAttrList() {
			console.log(this.sidoCode);
			console.log(this.gugunCode);
			console.log(this.contentTypeId);
			http.get(`trip/attrs?sidoCode=${this.sidoCode}&gugunCode=${this.gugunCode}&contentTypeId=${this.contentTypeId}`)
				.then(response => {   //200 ok 됐을때  응답 데이타를 받았을 때
					console.log(response)
					if (response.data.attrList) {
						this.attrList = response.data.attrList;
						this.markerPositions = [];
						// console.log(this.attrList.length);
						for (let i = 0; i < this.attrList.length; i++) {
							this.markerPositions.push([this.attrList[i].latitude, this.attrList[i].longitude])
						}
						console.log(this.markerPositions)
						this.$refs.kakaomap.displayMarker(this.attrList);
						this.attrList = response.data.attrList;
						this.markerPositions = [];
						// console.log(this.attrList.length);
						for (let i = 0; i < this.attrList.length; i++) {
							this.markerPositions.push([this.attrList[i].latitude, this.attrList[i].longitude])
						}
						console.log(this.markerPositions)
						this.$refs.kakaomap.displayMarker(this.attrList);
					} else {
						this.attrList = []
						alert('조회한 데이타 정보가 없습니다.')
					}
					console.log('attrList.........', this.attrList)
				})
				.catch(err => {
					console.log(err)
					alert(err.response.data);
				})
		},
		getGugunList(e) {
			this.sidoCode = e.target.value;

			console.log(this.sidoCode);
			http.get(`trip/attr/${this.sidoCode}`)
				.then(response => {   //200 ok 됐을때  응답 데이타를 받았을 때
					console.log(response)
					if (response.data.gugunList) {
						this.gugunList = response.data.gugunList
					} else {
						this.gugunList = []
						alert('조회한 데이타 정보가 없습니다.')
					}
					console.log('gugunList.........', this.gugunList)
				})
				.catch(err => {
					console.log(err)
					alert(err.response.data);
				})
		},
		setGugun(e) {
			this.gugunCode = e.target.value;
		},
		setcontentTypeId(e) {
			this.contentTypeId = e.target.value;
		},
		getSidoList() {
			http.get(`trip`)
				.then(response => {   //200 ok 됐을때  응답 데이타를 받았을 때
					console.log(response)
					if (response.data.sidoList) {
						this.sidoList = response.data.sidoList
					} else {
						this.sidoList = []
						alert('조회한 데이타 정보가 없습니다.')
					}
					console.log('sidoList.........', this.sidoList)
				})
				.catch(err => {
					console.log(err)
					alert(err.response.data);
				})
		},
		async moveCenter(e) {
			const curTrip = e
			console.log(curTrip);
			// console.log(selectedcontentId)
			// const curTrip = this.attrList.filter(contentId => selectedcontentId == contentId);
			await this.$nextTick();
			console.log(this.$refs);
			this.$refs.kakaomap.moveCenter(curTrip);
		},
		async checkAuthor() {
			const checkUserInfo = store.getters["memberStore/checkUserInfo"];
			const checkToken = store.getters["memberStore/checkToken"];
			let token = sessionStorage.getItem("access-token");
			console.log("로그인 처리 전", checkUserInfo, token);

			if (checkUserInfo != null && token) {
				console.log("유저 검증 완료");
				await store.dispatch("memberStore/getUserInfo", token);
			}
			if (!checkToken || checkUserInfo === null) {
				alert("로그인이 필요합니다.");
				// next({ name: "login" });
			} else {
				console.log("좋아요 권한 승인 완료");
			}
		},
		addPoint() {
			console.log("Point Add.....................", this.selectedMyPlan)

			this.$refs.kakaomap.addPoint(this.selectedMyPlan);
		},
	}}
</script>