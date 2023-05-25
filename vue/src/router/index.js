import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import InstagramView from "../views/InstagramView.vue";
import HouseView from "../views/HouseView.vue";
// import TripView from "../views/TripView.vue";
import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "home" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};


const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/MemberView.vue"),
    children: [
      {
        path: "register",
        name: "register",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegister"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
    ],
  },
  {
    path: "/trip",
    name: "trip",
    component: () => import(/* webpackChunkName: "trip" */ "@/views/TripView.vue"),
    redirect: "/trip/list",
    children: [
      {
        path: "list",
        name: "tripList",
        component: () => import(/* webpackChunkName: "trip" */ "@/components/trip/TripList.vue"),
      },
     
      {
        path: "hotplace",
        name: "hotPlaceView",
        component: () => import(/* webpackChunkName: "trip" */ "@/components/trip/HotPlaceView.vue"),
      },
    ]
  },
    {
      path: "/plan/list",
      name: "tripPlanList",
      beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "trip" */ "@/components/trip/TripPlanList.vue"),
    },
    {
      path: "/plan/detail/:planName",
      name: "TripPlan",
      beforeEnter: onlyAuthUser,
      component: () => import(/* webpackChunkName: "trip" */ "@/components/trip/TripPlan.vue"),
    },

  {
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleNo",
        name: "boardDetail",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleNo",
        name: "boardModify",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleNo",
        name: "boardDelete",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/instagram",
    name: "instagram",
    component: InstagramView,
  },
  {
    path: "/house",
    name: "house",
    component: HouseView,
  },
];

const router = new VueRouter({
  mode: "history",
  //eslint-disable-next-line
  // eslint-disable-next-line
  base: process.env.BASE_URL,
  routes,
});

export default router;
