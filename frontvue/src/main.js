import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/api/vueBootstrap.js";
import "@/api/http.js";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
