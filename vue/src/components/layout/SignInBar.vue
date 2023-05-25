<template>
    <div>
            <!-- <c:when test="${empty userInfo}"> -->
                <div>
                    <div class="title-box-d">
                        <h3 class="title-d">Sign In</h3>
                    </div>

                    <div>
                        <b-navbar-nav class="ml-auto" v-if="userInfo">
                        <b-nav-item class="align-self-center">
                            <b-avatar variant="primary"></b-avatar>
                            {{ userInfo.name }}({{ userInfo.id }})님 환영합니다.
                        </b-nav-item>
                        <b-nav-item class="align-self-center">
                            <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
                        </b-nav-item>
                        <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">로그아웃</b-nav-item>
                        </b-navbar-nav>

                        
                        <b-form class="form-a" v-else>
                        <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
                        <b-form-group label="아이디:" label-for="id">
                            <div class="col-md-12 mb-2">
                                    <div class="form-group">
                                        <label class="pb-2" for="id">ID</label> 
                                        <b-form-input
                                            id="id"
                                            v-model="user.id"
                                            required
                                            placeholder="아이디 입력...."
                                            @keyup.enter="confirm"
                                        ></b-form-input>
                                    </div>
                            </div>
                        </b-form-group>
                        <b-form-group label="비밀번호:" label-for="pass">
                          <b-form-input
                            type="password"
                            id="pass"
                            v-model="user.pass"
                            required
                            placeholder="비밀번호 입력...."
                            @keyup.enter="confirm"
                          ></b-form-input>
                        </b-form-group>
                        <b-button type="button" variant="primary" class="m-1" @click="confirm">로그인</b-button>
                        <b-button type="button" variant="success" class="m-1" @click="movePage">회원가입</b-button>
                      </b-form>
                      
                        <!-- <form class="form-a" method="POST" action="${root}/user/login">
                            <div class="row border border-1 p-4 rounded-4">
                                <div class="col-md-12 mb-2">
                                    <div class="form-group">
                                        <label class="pb-2" for="id">ID</label> <input type="text"
                                            class="form-control form-control-lg form-control-a" id="id" name="id"
                                            value="" placeholder="ID" />
                                    </div>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <b-form-group label="비밀번호:" label-for="pass">
                                        <label class="pb-2" for="pw">Password</label>
                                        <b-form-input
                                            class="form-control form-control-lg form-control-a"
                                            type="password"
                                            id="pass"
                                            v-model="user.pass"
                                            required
                                            placeholder="password"
                                            @keyup.enter="confirm"
                                        ></b-form-input> -->
                                        <!-- <input type="password" class="form-control form-control-lg form-control-a" id="pw"
                                            name="pw" placeholder="Password" /> -->
                                    <!-- </b-form-group>
                                </div>
                                <div class="d-flex flex-column align-items-center justify-content-center">
                                    <button type="submit" id="btn-login"
                                        class="btn btn-b w-100 border border-2 rounded-4 text-light mt-3">
                                        <strong>로그인</strong>
                                    </button>
                                </div>
                            </div>
                            <div class="d-flex align-content-center justify-content-center">
                                <a href="${root}/user/findPwForm.jsp" class="ms-5 me-5 mt-3 md-3 text-black-50">ID/Password
                                    찾기</a> <a href="${root}/user/signup" class="ms-5 me-5 mt-3 md-3 text-black-50">회원가입</a>
                            </div>
                        </form> -->
                    </div>
                </div>
            <!-- </c:when>
            <c:otherwise>
                <div class="box-collapse w-25">
                    <form class="form-a" action="${root}/user/mypage">
                        <div class="title-box-d">
                            <h3 class="title-d">내 정보</h3>
                        </div>
                        <span class="close-box-collapse right-boxed bi bi-x"></span>
                        <div class="box-collapse-wrap form">
                            <div class="row border border-1 p-4 rounded-4">
                                <div class="col-md-12 mb-2">
                                    <div class="form-group">
                                        <strong>${userInfo.name}</strong> (${userInfo.id})님 안녕하세요.
                                    </div>
                                </div>
                                <div class="d-flex flex-column align-items-center justify-content-center">
                                    <button type="submit" id="btn-login"
                                        class="btn btn-b w-100 border border-2 rounded-4 text-light mt-3">
                                        <strong>My page</strong>
                                    </button>
                                </div>
                            </div>
                            <div class="d-flex align-content-center justify-content-center">
                                <a href="${root}/user/logout" class="ms-5 me-5 mt-3 md-3 text-black-50">로그아웃</a>
                            </div>
                        </div>
                    </form>
                </div>
            </c:otherwise>
        </c:choose> -->
    </div>
</template>
<!-- <script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script> -->
    <script>
    import { mapState, mapActions, mapGetters } from "vuex";
    
    const memberStore = "memberStore";
    
    export default {
      name: "SignInBar",
      data() {
        return {
          //isLoginError: false,
          user: {
            id: null,
            pass: null,
          },
        };
      },
      computed: {
              ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
              ...mapGetters(["checkUserInfo"]),
      },
      methods: {
        ...mapActions(memberStore, ["userConfirm", "getUserInfo", "userLogout"]),
        async confirm() {
          await this.userConfirm(this.user);
          let token = sessionStorage.getItem("access-token");
          console.log("1. confirm() token >> " + token);
          if (this.isLogin) {
            await this.getUserInfo(token);
            console.log("4. confirm() userInfo :: ", this.userInfo);
            this.$router.push({ name: "home" });
          }
        },
        movePage() {
                this.$router.push({ name: "register" });
            },
            onClickLogout() {
                // this.SET_IS_LOGIN(false);
                // this.SET_USER_INFO(null);
                // sessionStorage.removeItem("access-token");
                // if (this.$route.path != "/") this.$router.push({ name: "main" });
                console.log(this.userInfo.id);
                //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
                //+ satate에 isLogin, userInfo 정보 변경)
                // this.$store.dispatch("userLogout", this.userInfo.userid);
                this.userLogout(this.userInfo.id);
                sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
                sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
                if (this.$route.path != "/") this.$router.push({ name: "main" });
                alert("로그아웃 성공")
            },
      },
    };
    </script>

<style>
/* 

    /* Vendor CSS Files */
@import '@/assets/vendor/animate.css/animate.min.css';
@import '@/assets/vendor/bootstrap/css/bootstrap.min.css';
@import '@/assets/vendor/bootstrap-icons/bootstrap-icons.css';
@import '@/assets/vendor/swiper/swiper-bundle.min.css';

/* Font */
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

/* Google Fonts */
@import url("https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700");

/* Template Main CSS File */
@import '@/assets/css/style.css';

/* Service Key */
/* @import '@/assets/js/key.js'; */
</style>



