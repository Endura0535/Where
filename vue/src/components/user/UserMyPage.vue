<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <!-- <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.joindate }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" /> -->
          <h4 class="title-2 mb-2">
                    <strong>이름 : </strong> <span class="color-text-a">{{ userInfo.id }}</span>
                  </h4>
                  <h4 class="title-2 mt-4">
                    <strong>ID: </strong> <span class="color-text-a">{{ userInfo.name }}</span>
                  </h4>
                  <h4 class="title-2 mt-4">
                    <strong>Email: </strong> <span class="color-text-a">{{ userInfo.email }}</span>
                  </h4>
                  <h4 class="title-2 mt-4">
                    <strong>Address : </strong> <span class="color-text-a">{{userInfo.addr1}}
                      {{userInfo.addr2}} </span>
                  </h4>

          <b-button variant="primary" href="#" class="mr-1">정보수정</b-button>
          <b-button variant="danger" href="#">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
    import { mapState } from "vuex";

    const memberStore = "memberStore";

    export default {
        name: "UserMyPage",
        components: {},
        computed: {
            ...mapState(memberStore, ["userInfo"]),
        },
    };
</script>

<style></style>