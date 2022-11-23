import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogin: false,
    myName: "",
    myEmail: "",
    myRole: "",
  },
  getters: {
    isLogin(state) {
      return state.isLogin;
    },
    getMyName(state) {
      return state.myName;
    },
    getMyEmail(state) {
      return state.myEmail;
    },
    getMyRole(state) {
      return state.myRole;
    }
  },
  mutations: {
    setisLogin(state, value) {
      state.isLogin = value;
    },
    setMyName(state, value) {
      state.myName = value;
    },
    setMyEmail(state, value) {
      state.myEmail = value;
    },
    setMyRole(state, value) {
      state.myRole = value;
    }
  },
  actions: {
    setisLogin:({commit}, value) => {
      commit('setisLogin', value);
    },
    setMyName: (({commit}, value) => {
      commit('setMyName', value);
    }),
    setMyEmail: (({commit}, value) => {
      commit('setMyEmail', value);
    }),
    setMyRole: (({commit}, value) => {
      commit('setMyRole', value);
    })
  },
  modules: {
  },
  plugins: [createPersistedState(
    
  )]
})
