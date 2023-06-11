import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";
import MainNewView from "@/views/MainNewView.vue";
import PlanView from "../views/PlanView.vue";
import UserLoginView from "@/views/user/UserLoginView.vue";
import UserMypageView from "@/views/user/UserMypageView.vue";
import JournalWrite from "@/components/plan/PlanWrite";
import UserJoinView from "@/views/user/UserJoinView.vue";
import UserInfoView from "@/views/user/UserInfoView.vue";
import SearchView from "@/views/SearchView.vue";
import LocationInfo from "@/components/info/locationInfo.vue";
import PlanInfo from "@/components/info/planInfo.vue";
import JournalInfo from "@/components/info/journalInfo.vue";
import InfoView from "@/views/InfoView.vue";
import JournalView from "@/views/JournalView.vue";
import SearchContent from "@/views/SearchContent.vue";
import MainSearch from "@/components/main/MainSearch.vue";
import MainContent from "@/components/main/MainContent.vue";
import MainReview from "@/components/main/MainReview.vue";
import PlanContent from "@/components/plan/PlanContent.vue";
import UserModify from "@/views/user/UserModify.vue";
Vue.use(VueRouter);

const onlyAuthUser = (to, from, next) => {
  let token = sessionStorage.getItem("token");

  if (!token) {
    alert("로그인이 필요한 페이지입니다.");
    next({ name: "MainNewView" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Main",
    component: MainView,
    redirect: { name: "MainNewView" },
    children: [
      {
        path: "main",
        name: "MainNewView",
        component: MainNewView,
        children: [
          {
            path: "search",
            name: "MainSearch",
            component: MainSearch,
          },
          {
            path: "content",
            name: "MainContent",
            component: MainContent,
          },
          {
            path: "review",
            name: "MainReview",
            component: MainReview,
          },
        ],
      },
      {
        path: "search",
        name: "Search",
        props: true,
        component: SearchView,
        children: [
          {
            path: ":word",
            name: "SearchRedirect",
            component: SearchView,
            props: true,
          },
          {
            path: "list/:keyword",
            name: "SearchContent",
            component: SearchContent,
            props: true,
          },
        ],
      },
      {
        path: "userMyPage",
        name: "UserMypageView",
        component: UserMypageView,
        beforeEnter: onlyAuthUser,
      },
      {
        path: "userModify",
        name: "UserModify",
        component: UserModify,
        beforeEnter: onlyAuthUser,
      },

      {
        path: "plan",
        name: "Plan",
        component: PlanView,
        props: true,
        beforeEnter: onlyAuthUser,
        children: [
          {
            path: "content",
            name: "PlanContent",
            component: PlanContent,
            beforeEnter: onlyAuthUser,
            props: true,
          },
        ],
      },

      {
        path: "journal",
        name: "JournalView",
        component: JournalView,
        // chil
      },
      {
        path: "journalwrite",
        name: "JournalWrite",
        component: JournalWrite,
        beforeEnter: onlyAuthUser,
      },
      {
        path: "info",
        name: "infoView",
        component: InfoView,
        children: [
          {
            path: "location/:locationId",
            name: "LocationInfo",
            component: LocationInfo,
            props: true,
          },
          {
            path: "plan/:planId",
            name: "PlanInfo",
            component: PlanInfo,
            props: true,
          },
          {
            path: "journal/:journalId",
            name: "JournalInfo",
            component: JournalInfo,
            props: true,
          },
        ],
      },
    ],
  },
  {
    path: "/user",
    name: "UserLogin",
    component: UserLoginView,
    // children: [
    //   {
    //     path: "userJoin",
    //     name: "UserJoin",
    //     component: UserJoinView,
    //   },
    // ],
  },
  {
    path: "/userJoin",
    name: "UserJoin",
    component: UserJoinView,
  },
  {
    path: "/userInfo",
    name: "UserInfo",
    component: UserInfoView,
    beforeEnter: onlyAuthUser,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
