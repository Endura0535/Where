<template>
    <div>
    <!-- Start Intro-->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">나의 여행 계획 목록</h1>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"> <router-link :to="{ name: 'home' }">Home</router-link>
                </li>
                <li class="breadcrumb-item"><a href="#">커뮤니티</a></li>
                <li class="breadcrumb-item"><a href="#">지역별 여행지 추천</a></li>
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
            
          </div>
        </div>
        <!-- ======= 정렬 End ======= -->
        <!-- ======= 검색 Start ======= -->
        
        <!-- ======= 검색 End ======= -->
        <!-- ======= 결과 Start ======= -->
        <div class="d-flex justify-content-end align-content-center p-2 m-auto">
          <b-button class="btn btn-b border border-2 rounded-4 text-light" style="padding: 0.7rem 1rem"
            @click="$event => moveWrite()">새로 만들기</b-button>
        </div>
        <div class="d-flex flex-column justify-content-end align-content-center p-2 m-auto">
          <table class="table table-hover table-group-divider border-1">
            <thead>
              <tr class="text-center">
                <th class="text-black">이름</th>
                <th class="text-black">여행 시작일</th>
              </tr>
            </thead>
            <tbody>
              <!-- <board-list-item v-for="article in articles" :key="article.articleno" v-bind="article" /> -->
              <!-- <board-list-item v-for="(plan,index) in plans" :key="index" v-bind="plans" /> -->
              <plan-header-list-item v-for="plan in plans" :key="plan.planName" v-bind="plan"/>
          
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
  import store from "@/store";
import PlanHeaderListItem from "./items/planHeaderListItem.vue";
  
  export default {
    name: "TripPlanList",
    components: {
        PlanHeaderListItem,
    },
    data() {
      return {
        plans: [],
        pageNo: 1,
        perPage: 5,
        searchKey: 'all',
        searchWord: '',
            sortKey: 'all',
        user : store.getters["memberStore/checkUserInfo"].id,
      };
    },
    created() {
      http.get(`plan/header/${this.user}`).then(({ data }) => {
        this.plans = data;
        this.total = data.length;
      });
          console.log(this.plans);
      
    },
    computed: {
      lists() {
        const items = this.plans;
        // Return just page of items needed
        return items.slice(
          (this.pageNo - 1) * this.perPage,
          this.pageNo * this.perPage
        )
      },
      totalRows() {
        return this.plans.length;
      }
    },
    methods: {
      moveWrite() {
        const router = this.$router;
        this.checkAuthor()
                  .then(function (flag) {
            if (flag) {
                      router.push({ name: "TripPlan" });
                  }
              }) 
      },
      viewArticle(article) {
        this.$router.push({
          name: "TripPlan",
          params: { articleNo: article.articleNo },
        });
      },
      searchArticle() {
        http.get(`board/list?pageNo=${this.pageNo}&sortKey=${this.sortKey}&searchWord=${this.searchWord}&searchKey=${this.searchKey}`)
          .then(response => {   //200 ok 됐을때  응답 데이타를 받았을 때
            console.log(response.data)
            if (response.data) {
              this.plans = response.data
            } else {
              this.plans = []
              alert('조회한 데이타 정보가 없습니다.')
            }
            // console.log('plans.........',this.boards)
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
