<template>
    <div>
    <section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Hot Place</h1>
						<span class="color-text-a">숨겨진 핫 플레이스에 방문해보세요!</span>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">커뮤니티
							</li>
							<li class="breadcrumb-item active" aria-current="page">Hot
								Place</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single--> <!-- ======= Agents Grid ======= -->
	<section class="agents-grid grid">
        
		<div class="container">
        
			<div class="row">
                <!-- <hot-place-item v-for="place in hotPlace" :key="place.contentId" v-bind="place" /> -->
                <hot-place-item v-for="place in lists" :key="place.contentId" v-bind="place" />
             
            <b-pagination pills align='center' v-model="pageNo" :total-rows="totalRows" :per-page="perPage"></b-pagination>

				<div class="row">
                    
					<div class="col-sm-12">
                        
						<!-- <nav class="pagination-a">
							<ul class="pagination justify-content-end">
								<li class="page-item disabled"><a class="page-link"
									href="#" tabindex="-1"> <span class="bi bi-chevron-left"></span>
								</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item active"><a class="page-link" href="#">2</a>
								</li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item next"><a class="page-link" href="#">
										<span class="bi bi-chevron-right"></span>
								</a></li>
							</ul>
						</nav> -->
					</div>
				</div>
			</div>
     
                    
		</div>
	</section>
</div>
</template>

<script>
import http from "@/api/http";
import HotPlaceItem from "@/components/trip/items/HotPlaceItem.vue";

export default {
    name: "HotPlaceView",
    components: {
        HotPlaceItem,
    },
    data() {
        return {
            hotPlace: [],
            pageNo: 1,
            perPage: 9,
            searchKey: 'all',
            searchWord: '',
            sortKey: 'all',
            searchKeys: [
                { value: 'all', text: '--선택--' },
                { value: 'subject', text: '제목' },
                { value: 'id', text: '작성자' },
            ],
            sortKeys: [
                { value: 'all', text: '--선택--' },
                { value: 'articleNo', text: '글 번호' },
                { value: 'subject', text: '제목순' },
                { value: 'hit', text: '조회수' },
            ],
            fields: [
                { key: "articleNo", label: "글번호", tdClass: "tdClass" },
                { key: "subject", label: "제목", tdClass: "tdSubject" },
                { key: "id", label: "작성자", tdClass: "tdClass" },
                { key: "registerTime", label: "작성일", tdClass: "tdClass" },
                { key: "hit", label: "조회수", tdClass: "tdClass" },
            ],
        };
    },
    created() {
        http.get(`/trip/hotplace`).then(({ data }) => {
            console.log(data)
            this.hotPlace = data.hotplaces;
        });
    },
    computed: {
        lists() {
            const items = this.hotPlace;
            // Return just page of items needed
            console.log(items)
            return items.slice(
                (this.pageNo - 1) * this.perPage,
                this.pageNo * this.perPage
            )
        },
        totalRows() {
            console.log("totalRows......................." + this.hotPlace.length)
            return this.hotPlace.length;
        }
    },
    methods: {
        moveWrite() {
            this.$router.push({ name: "boardRegister" });
        },
        viewArticle(article) {
            this.$router.push({
                name: "boardDetail",
                params: { articleNo: article.articleNo },
            });
        },
        searchArticle() {
            http.get(`board/list?pageNo=${this.pageNo}&sortKey=${this.sortKey}&searchWord=${this.searchWord}&searchKey=${this.searchKey}`)
                .then(response => {   //200 ok 됐을때  응답 데이타를 받았을 때
                    console.log(response.data)
                    if (response.data) {
                        this.articles = response.data
                    } else {
                        this.articles = []
                        alert('조회한 데이타 정보가 없습니다.')
                    }
                    // console.log('articles.........',this.boards)
                })
                .catch(err => {
                    console.log(err)
                    alert(err.response.data);
                })
        }
    },
};
</script>

<style scope>

      /* @import "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css";
      @import "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css";
        @import "https://use.fontawesome.com/releases/v6.1.0/js/all.js";
    @import "https://fonts.googleapis.com/css?family=Montserrat:400,700";
    @import "https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic";
    @import "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"; */


</style>
	