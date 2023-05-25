<template>
  <div>
    <!-- Start Intro-->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">QnA 게시판</h1>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"> <router-link :to="{ name: 'home' }">Home</router-link>
                </li>
                <li class="breadcrumb-item"><a href="#">커뮤니티</a></li>
                <li class="breadcrumb-item"><a href="#">QnA게시판</a></li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro-->
    <!-- ======= 정렬 Start ======= -->
    <section class="news-single nav-arrow-b">
      <div class="container">
        <div>
          <div class="d-flex" id="frm" style="width: 300px; float: left; margin-top: 1.5em;">
            <input type="hidden" name='pageNo' id='pageNo' />
            <b-form-select name="sortKey" id="sortKey" class="form-select form-select-sm ms-5 me-1 w-50"
              aria-label="정렬 조건" v-model="sortKey" :options="sortKeys">
            </b-form-select>
            <div class="input-group input-group-sm">
              <button id="btn-sort" class="btn btn-dark" @click="searchArticle()">정렬</button>
            </div>
          </div>
        </div>
        <!-- ======= 정렬 End ======= -->
        <!-- ======= 검색 Start ======= -->
        <div>
          <div class="d-flex" id="frm" style="width: 600px; float: left; margin-top: 1.5em;">
            <input type="hidden" name='pageNo' id='pageNo' />
            <b-form-select name="searchKey" id="searchKey" class="form-select form-select-sm ms-5 me-1 w-50"
              aria-label="검색 조건" v-model="searchKey" :options="searchKeys">
            </b-form-select>
            <b-input type="text" class="form-input form-input-sm ms-4 me-1" style="margin:auto" v-model="searchWord" />
            <div class="input-group input-group-sm">
              <button id="btn-sort" class="btn btn-dark" @click="searchArticle()">검색</button>
            </div>
          </div>
        </div>
        <!-- ======= 검색 End ======= -->
        <!-- ======= 결과 Start ======= -->
        <div class="d-flex justify-content-end align-content-center p-2 m-auto" style="padding-bottom: 3rem;">
          <b-button class="btn btn-b border border-2 rounded-4 text-light" style="padding: 0.7rem 2rem"
            @click="$event => moveWrite()">작성하기</b-button>
        </div>
        <div class="d-flex flex-column justify-content-end align-content-center p-2 m-auto">
          <table class="table table-hover table-group-divider border-1">
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
              <!-- <board-list-item v-for="article in articles" :key="article.articleno" v-bind="article" /> -->
              <board-list-item v-for="list in lists" :key="list.articleno" v-bind="list" />
            </tbody>
          </table>
          <!-- ======= Pagenation Start ======= -->
          <b-pagination pills align='center' v-model="pageNo" :total-rows="totalRows" :per-page="perPage"></b-pagination>
          <!-- ======= Pagenation End ======= -->
        </div>
      </div>
      <!-- ======= 결과 End ======= -->


    </section>
  </div>
</template>
      
<script>
import http from "@/api/http";
import BoardListItem from "@/components/board/item/BoardListItem.vue";
import store from "@/store";

export default {
  name: "boardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      pageNo: 1,
      perPage: 5,
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
    http.get(`/board/list`).then(({ data }) => {
      console.log(data)
      this.articles = data;
      this.total = data.length;
    });
  },
  computed: {
    lists() {
      const items = this.articles;
      // Return just page of items needed
      return items.slice(
        (this.pageNo - 1) * this.perPage,
        this.pageNo * this.perPage
      )
    },
    totalRows() {
      return this.articles.length;
    }
  },
  methods: {
    moveWrite() {
      const router = this.$router;
      this.checkAuthor()
				.then(function (flag) {
          if (flag) {
					router.push({ name: "boardRegister" });
				}
			}) 
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
				return true;
			}
			return false;
		},
  },
};
</script>

<style scope></style>
