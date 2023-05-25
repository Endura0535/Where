<template>
  <div class="row justify-content-center" style="margin-top: 50px">
    <div class="col-lg-8 col-md-10 col-sm-12">
      <b-form-group id="subject-group" label="제목:" label-for="subject">
        <b-form-input v-if="this.type === 'modify'" id="subject" ref="subject"
          type="text" v-model="article.subject"></b-form-input>
        <b-form-input v-if="this.type === 'register'" id="subject" ref="subject" type="text"
          placeholder="제목 입력..." v-model="article.subject"></b-form-input>
      </b-form-group>
      <br>
      <b-form-group id="content-group" label="내용:" label-for="content">
        <b-form-textarea id="content" ref="content" placeholder="내용 입력..." rows="10"
          max-rows="15" v-model="article.content"></b-form-textarea>
      </b-form-group>
      <div class="col-auto text-center" id="button-group">
        <button class="btn btn-outline-primary mb-3" v-if="this.type === 'register'" @click="registArticle"
          id="btn-register">작성</button>
        <button class="btn btn-outline-primary mb-3" v-if="this.type === 'modify'" @click="modifyArticle"
          id="btn-modify">수정</button>
        <button class="btn btn-outline-danger mb-3" @click="onReset">초기화</button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import store from "@/store"

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleNo: "",
        id: "",
        subject: "",
        content: "",
      },
      isUserid: false,
      // boardregister,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      // http.get(`/board/${this.$route.params.articleNo}`).then(({ data }) => {
      //   this.article.articleNo = data.article.articleNo;
      //   // this.article.id = data.article.id;
      //   this.article.subject = data.article.subject;
      //   this.article.content = data.article.content;
      //   this.article = data;
      // });
      // this.isUserid = true;
      this.articleNo = this.$route.params.articleNo;
      console.log("Modify.html.................articleNo", this.articleNo)
      http.get(`board/detail/${this.articleNo}`)
        .then(({ data }) => {
          console.log(data)
          this.article = data;
        })
    }
  },
  methods: {

    onReset(event) {
      event.preventDefault();
      this.article.subject = "";
      this.article.content = "";
    },

    registArticle() {
      //데이타 검증 
      let err = false;
      let msg = '';

      this.article.id = store.getters["memberStore/checkUserInfo"].id;
      if (!err && this.article.subject == '') {
        err = true;
        msg = '제목을 입력해 주세요'
        this.$refs.subject.focus();
      }

      if (!err && this.article.content == '') {
        err = true;
        msg = '내용를 입력해 주세요'
        this.$refs.content.focus();
      }

      if (err) {
        alert(msg);
      } else {
        http.post('board/write', this.article)
          .then(({ data }) => {
            if (data == 'success') {
              alert('등록 완료')
              this.moveList();
            }
          })
          .catch(err => {
            alert(err.response.data)
          })
      }

    },

    modifyArticle() {
      // 수정하기
      console.log("수정..........................................")
      http
        .put(`/board/${this.article.articleNo}`, {
          articleNo: this.article.articleNo,
          id: this.article.id,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "boardList" });
        });

    },

    moveList() {
      this.$router.push({ name: "boardList" });
    },
  }
}
</script>

<style scoped>
#button-group {
  margin-top: 20px;
}

#btn-register {
  margin-right: 30px;
}

#btn-modify {
  margin-right: 30px;
}
</style>
