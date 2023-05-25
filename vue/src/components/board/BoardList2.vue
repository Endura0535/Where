<template>
    <div>
      <!-- Start Intro Single-->
      <section class="intro-single">
        <div class="container">
          <div class="row">
            <div class="col-md-12 col-lg-8">
              <div class="title-single-box">
                <h1 class="title-single">수정이 게시판</h1>
              </div>
            </div>
            <div class="col-md-12 col-lg-4">
              <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item"> <router-link :to="{ name: 'home' }">Home</router-link>
                  </li>
                  <li class="breadcrumb-item"><a href="#">수정이 커뮤니티</a></li>
                  <li class="breadcrumb-item"><a href="#">수정이가 답변해 드려요</a></li>
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
          <div>
            <form class="d-flex" id="frm" action="${root}/article/list"
              style="width: 300px; float: left; margin-top: 1.5em;">
              <input type="hidden" name='pageNo' id='pageNo' /> <select name="sortKey" id="sortKey"
                class="form-select form-select-sm ms-5 me-1 w-50" aria-label="조회조건">
                <option value="articleNo" selected>글번호</option>
                <option value="hit">조회수</option>
                <option value="subject">제목순</option>
              </select>
              <div class="input-group input-group-sm">
                <button id="btn-sort" class="btn btn-dark" type="submit">정렬</button>
              </div>
            </form>
          </div>
          <!-- ======= 검색 Start ======= -->
          <div>
            <form class="d-flex" id="frm" action="${root}/article/list"
              style="width: 300px; float: left; margin-top: 1.5em;">
              <input type="hidden" name='pageNo' id='pageNo' /> 
              <select name="sortKey" id="sortKey"
                class="form-select form-select-sm ms-5 me-1 w-50" aria-label="조회조건">
                <option value="articleNo" selected>글번호</option>
                <option value="hit">조회수</option>
                <option value="subject">제목순</option>
              </select>
              <div class="input-group input-group-sm">
                <button id="btn-sort" class="btn btn-dark" type="submit" >정렬</button>
              </div>
            </form>
          </div>
          <!-- <div>
            <div class="row">
              <div class="col-2"></div>
              <div class="col-2">
                <b-form-select v-model="key" :options="keys">
                </b-form-select>
              </div>
              <div class="col-4">
                <b-form-input v-model="word" />
              </div>
              <div class="col-2">
                <b-button variant="primary" @click="searchBook">검색</b-button>
              </div>
              <div class="col-2"></div>
            </div>
          </div> -->
      <!-- ======= 검색 End ======= -->
          <div class="d-flex justify-content-end align-content-center p-2 m-auto">
            <b-button class="btn btn-b border border-2 rounded-4 text-light" style="padding: 0.7rem 1rem"
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
                <board-list-item v-for="article in articles" :key="article.articleno" v-bind="article" />
              </tbody>
            </table>
          </div>
        </div>
      </section>
    </div>
  </template>
        
  <script>
  import http from "@/api/http";
  import BoardListItem from "@/components/board/item/BoardListItem.vue";
  
  export default {
    name: "boardList",
    components: {
      BoardListItem,
    },
    data() {
      return {
        articles: [],
        key: 'all',
              word: '',
              keys: [
                  {value:'all',   text:'----선택하세요----'},
                  {value:'title',  text:'제목'},
                  {value:'id', text:'작성자'},
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
      });
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
    },
  };
  </script>
  
  <style scope>
  /* .tdClass {
    width: 50px;
    text-align: center;
  }
  
  .tdSubject {
    width: 300px;
    text-align: left;
  } */
  </style>
  