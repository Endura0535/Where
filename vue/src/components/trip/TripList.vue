<template>
	<div id="main">
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
						<nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
								<li class="breadcrumb-item"><a href="recommendByLocation.html">지역별 여행지 추천</a></li>
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

		<div class="d-flex align-items-top result-spot">
			<!-- kakao map start -->
			<!-- <div id="map" class="mt-3 " style="width: 700px; height: 700px"></div>> -->
			<kakaomap ref="kakaomap" @close="closeOverlay"></kakaomap>
			<!-- kakao map end -->
			<div style="width:5%"></div>

			<div v-if="attrList.length">
				<table class="table">
					<tbody>
						<tr v-for="trip in lists" :key="trip.contentId" @click="moveCenter(trip)">
							<td class="td">
								<img v-if="!trip.imgPath" :src="require(`@/assets/img/noImg.png`)"
									style="width:100px;height:70px" />
								<img v-else :src=trip.imgPath style="width:100px;height:70px" />
							</td>
							<td class="td">{{ trip.title }}</td>
							<td>
								<img type="button" :src="require(`@/assets/img/blue_heart.png`)"
									@click="increaseLikeCount(trip)" id="likeButton" width="20px" height="20px">
							</td>
						</tr>
					</tbody>
				</table>
				<b-pagination pills align='center' v-model="pageNo" :total-rows="totalRows"
					:per-page="perPage"></b-pagination>
			</div>

			<!-- <b-button @click="closeOverlay()">close</b-button> -->
		</div>

	</div>
</template>

<script>
import http from '@/api/http';
import kakaomap from '@/components/trip/KakaoMap';
import store from "@/store";


export default {
	name: "TripList",
	components: {
		kakaomap,
	},
	data() {
		return {
			pageNo: 1,
			perPage: 5,
			sidoCode: '',
			gugunCode: '',
			contentTypeId: '',
			gugunList: [],
			attrList: [],
			sidoList: [],
			markerPositions: [
			],
		}
	},
	created() {
		this.getSidoList()
	},
	computed: {
		lists() {
			const items = this.attrList;
			// Return just page of items needed
			return items.slice(
				(this.pageNo - 1) * this.perPage,
				this.pageNo * this.perPage
			)
		},
		totalRows() {
			return this.attrList.length;
		}
	},

	methods: {
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
		increaseLikeCount(trip) {
			const curTrip = trip
			console.log(curTrip)
			const curContentId = curTrip.contentId;
			this.checkAuthor()
				.then(function (flag) {
					if (flag) {
						const checkUserInfo = store.getters["memberStore/checkUserInfo"];
						console.log(checkUserInfo);

						const msg = '좋아요를 반영했어요'
						http.post(`trip/hotplace`, {

							contentId: curContentId,
							uid: checkUserInfo.id,
							likes: 0,
						}
						)
						alert(msg)
					}
				})
		},
		async moveCenter(trip) {
			const curTrip = trip;
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
			console.log(checkUserInfo, checkToken)
			if (checkUserInfo != null && token) {
				console.log("유저 검증 완료");
				await store.dispatch("memberStore/getUserInfo", token);
			}
			if (!checkToken || checkUserInfo === null) {
				alert("로그인이 필요합니다.");
				// next({ name: "login" });
			} else {
				console.log("좋아요 권한 승인 완료");
				return true;
			}
			return false;
		},
		closeOverlay() {
			console.log("Overlay closed.....................")
			this.$refs.kakaomap.closeOverlay();
		},
	}
};
</script>

<style scoped>
.table {
	table-layout: fixed;
	width: 100%;
}

.table td {
	width: 120px;
}

#likeButton {
	width: 20px;
	height: 20px;
	border: none;
}

#searchForm {
	margin-bottom: 50px;
}
</style>