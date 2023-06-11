import Vue from "vue";
import { BootstrapVue, IconsPlugin, BootstrapVueIcons } from "bootstrap-vue";

Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
