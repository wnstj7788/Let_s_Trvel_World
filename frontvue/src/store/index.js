import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    form: true,
  },
  getters: {
    getForm(state) {
      return state.form;
    },
  },
  mutations: {
    FORM_CHANGE(state) {
      state.form = !state.form;
    },
  },
  actions: {
    formChange({ commit }) {
      commit("FORM_CHANGE");
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
