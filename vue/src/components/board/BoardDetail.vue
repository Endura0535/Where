<template>
  <div>
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8 w">
            <div class="title-single-box">
              <h1 class="title-single" v-text="article.subject"></h1>
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
                  <a href="#">QnA 게시판</a>
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
                <span class="color-text-a" v-text="article.id"></span>
              </li>
              <li class="list-inline-item mr-2">
                <strong>작성일 : </strong>
                <span class="color-text-a" v-text="article.registerTime"></span>
              </li>
              <li class="list-inline-item">
                <strong>조회 : </strong>
                <span class="color-text-a" v-text="article.hit"></span>
              </li>
              <li class="list-inline-item">
                <strong>댓글 : </strong>
                <span class="color-text-a">4</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->

    <!-- ======= Blog Single ======= -->
    <section class="news-single nav-arrow-b">
      <div class="container">
        <div class="row d-flex justify-content-center">
          <div class="col-sm-12 d-flex flex-column align-content-center justify-content-center">
            <div id="articleContent" class="post-content color-text-a">
              <pre class="mt-3">
                      <p v-text="article.content"></p>
                      </pre>
            </div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3" @click="moveHandler">
                목록
              </button>
              <!-- <c:if test="${userInfo.id eq article.userId}"> -->
              <button type="button" v-if = "this.article.id === userId" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1" @click="updateHandler">
                수정
              </button>
              <button type="button" v-if = "this.article.id === userId" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1" @click="removeHandler">
                삭제
              </button>
              <!-- </c:if> -->
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
    </section>
    <!-- End Blog Single-->
  </div>
</template>

<script>
import http from '@/api/http';
import store from '@/store'
export default {
  name: "boardDetail",
  data() {
    return {
      articleNo: '',
      article: {},
      userId : store.getters["memberStore/checkUserInfo"].id,
    };
  },
  created() {
    this.articleNo = this.$route.params.articleNo;
    console.log("view.html..................articleNo:", this.articleNo);
    http.get(`board/detail/${this.articleNo}`)
      .then(({ data }) => {
        console.log("data................................" + data)
        this.article = data;
      })
  },
  methods: {
    moveHandler() {
      this.$router.push({ name: "boardList" });
    },
    updateHandler() {
      this.$router.push({ name: "boardModify", params: { articleNo: this.articleNo } });
    },
    removeHandler() {
      http.delete(`/board/${this.articleNo}`)
        .then(({ data }) => {
          console.log(data)
          alert("삭제 완료");
          this.moveHandler();
        })
        .catch(err => {
          console.log(err)
          alert(err.response.data);
        })
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardView",
        params: { articleNo: article.articleNo },
      });
    },
  },
};
</script>

<style scoped>
#articleContent {
  width: 50%;
  margin: auto;
}
</style>