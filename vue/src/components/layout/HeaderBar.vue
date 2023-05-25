<template>
    <div>
        <nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
            <div class="container">
                <a class="navbar-brand text-brand">
                    <router-link :to="{ name: 'home' }" class="link">
                        <span class="color-a"> 웨</span>
                        <span class="color-b">얼?</span>
                    </router-link>
                </a>
                <ul class="navbar-nav me-5 ms-5 d-flex w-75 align-items-center justify-content-center ">
                    <li class="nav-item dropdown ms-5 me-5">
                        <a class="nav-link" href="/trip" aria-haspopup="true" aria-expanded="false">지역별 여행지 추천</a>
                    </li>
                    <li class="nav-item dropdown ms-5 me-5">
                        <!-- <router-link class="nav-link" aria-haspopup="true" aria-expanded="false" to="/enjoytrip/trip">지역별 여행지 추천</router-link> -->
                        <a class="nav-link" href="/plan/list" aria-haspopup="true" aria-expanded="false">나의 여행 계획</a>
                    </li>

                    <li class="nav-item dropdown me-5 ms-5"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                            role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
                        <div class="dropdown-menu">
                            <b-dropdown-item><router-link :to="{ name: 'board' }" class="link">
                                    게시판
                                </router-link></b-dropdown-item>
                                <b-dropdown-item><router-link :to="{ name: 'hotPlaceView' }" class="link">
                                    핫 플레이스
                                </router-link></b-dropdown-item>
                            <!-- <a class="dropdown-item " href="#"> -->

                            <!-- </a> -->
                            <!-- <a class="dropdown-item " href="#">
                                <router-link :to="{ name: 'hotplace' }" class="link">
                                Hot Place
                            </router-link>
                        </a> -->
                        </div>
                    </li>
                </ul>

                <button v-b-toggle.sidebar-right class="btn btn-b-n navbar-toggle-box navbar-toggle-box-collapse"
                    data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01">
                    <i class="bi bi-person-circle"></i>
                </button>
                <b-sidebar id="sidebar-right" right shadow>
                    <sign-in-bar></sign-in-bar>
                </b-sidebar>

                <!-- <img src="@/assets/img/favicon.png" />
                <img src="@/assets/img/apple-touch-icon.png" /> -->
            </div>
        </nav>
    </div>
</template>
<script>
import SignInBar from "@/components/layout/SignInBar.vue";

/* Favicons  */
//     import MainIcon from "@/assets/img/favicon.png"
//     import AppleTouchIcon from "@/assets/img/apple-touch-icon.png"
// import { component } from "vue/types/umd";

export default {
    name: "HeaderBar",
    components: {
        SignInBar,
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
  