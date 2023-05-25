<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
          <div class="container">
          <div class="input-form-backgroud row">
              <div class="input-form col-md-12 mx-auto">
                      <div class="row">
                          <div class="col-md-6 mb-3">
                              <label for="id">아이디</label> <input type="text"
                                  class="form-control" id="id" name="id" placeholder="" value=""
                                  v-model="userInfo.id" required>
                          </div>
                          <div class="col-md-6 mb-3">
                              <label for="pw">비밀번호</label> <input type="password"
                              v-model="userInfo.pass" class="form-control" id="pass" name="pass" placeholder="비밀번호 입력" />
                          </div>
                      </div>
                      <div class="mb-3">
                          <label for="name">이름</label> <input type="text"
                          v-model="userInfo.name" class="form-control" id="name" name="name" placeholder="이름"
                              required>
                      </div>
                      <div class="mb-3">
                          <label for="email">이메일</label> <input type="email"
                          v-model="userInfo.email" class="form-control" id="email" name="email" placeholder="you@example.com"
                              required>
                          <div class="invalid-feedback">이메일을 입력해주세요.</div>
                      </div>
                      <div class="mb-3">
                          <label for="address">주소</label> <input type="text"
                          v-model="userInfo.addr1" class="form-control" id="addr1" name="addr1" placeholder="서울특별시 강남구"
                              required>
                          <div class="invalid-feedback">주소를 입력해주세요.</div>
                      </div>
                      <div class="mb-3">
                          <label for="address2">상세주소</label> <input type="text"
                          v-model="userInfo.addr2" class="form-control" id="addr2" name="addr2" placeholder="상세주소를 입력해주세요.">
                      </div>
                      <button class="btn btn-primary btn-lg btn-block" @click="createHandler">가입하기</button>
              </div>
          </div>
      </div>
        </b-col>
      </b-row>
    </b-container>
  </template>
  
  <script>
  import http from "@/api/http";

  export default {
    name: "UserRegister",
      data() {
          return {
                userInfo: {
                    id: "",
                    pass: "",
                    name: "",
                    email: "",
                    addr1: "",
                    addr2: "",
              }
          };
      },
      methods: {
        createHandler() {
              //데이타 검증 
              let err = false;
              let msg = '';

              if (this.userInfo.id == '') {
                  err = true;
                  msg = '아이디를 입력해 주세요'
                  // $refs : Vue 객체에서 DOM 객체에 접근 할 때 
                  this.$refs.id.focus();
              }

              if (this.userInfo.pass == '') {
                  err = true;
                  msg = '아이디를 입력해 주세요'
                  // $refs : Vue 객체에서 DOM 객체에 접근 할 때 
                  this.$refs.id.focus();
              }

              if (!err && this.userInfo.name == '') {
                  err = true;
                  msg = '이름을 입력해 주세요'
                  this.$refs.name.focus();
              }

              if (!err && this.userInfo.email == '') {
                  err = true;
                  msg = '이메일을 입력해 주세요'
                  this.$refs.email.focus();
              }

              if (!err && this.userInfo.addr1 == '') {
                  err = true;
                  msg = '주소1을 입력해 주세요'
                  this.$refs.addr1.focus();
              }

              if (!err && this.userInfo.addr2 == '') {
                  err = true;
                  msg = '주소2를 입력해 주세요'
                  this.$refs.addr2.focus();
              }

              console.log(this.userInfo);
              if (err) {
                  alert(msg)
              } else {
                  //등록 처리 
                  //localStorage에 이미 저장된 책 목록이 있는지 검사
                  http.post('/user/regist', this.userInfo)
                      .then(({ data }) => {
                          if (data == 'success') {
                              alert('회원가입 완료')
                              this.moveHandler();
                          }
                      })
                      .catch(err => {
                          alert(err.response.data)
                      })
              }

              this.moveList();
          },
          moveList() {
            alert('회원가입에 성공했습니다.')
            this.$router.push('/main').catch(()=>{});
          },
          
      },
      
  };
  </script>
  
  <style></style>
  