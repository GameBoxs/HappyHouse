import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogin: false,
  },
  getters: {
    isLogin(state) {
      return state.isLogin;
    },
  },
  mutations: {
    setisLogin(state, value){
      state.isLogin = value;
    }
  },
  actions: {
    setisLogin:({commit}, value) => {
      commit('setisLogin', value);
    }
  },
  modules: {
  },
  plugins: [createPersistedState(
    
  )]
})
