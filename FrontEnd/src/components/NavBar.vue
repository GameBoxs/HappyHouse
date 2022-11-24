<template>
    <nav class="navbar navbar-expand-xxl navbar-dark fixed-top" id="mainNav">
      <div class="container-fluid">
        <router-link class="navbar-brand" to="/">
          <h1 class="fw-bold text-waring m-0 ms-5">Where <span class="text-white">Is My </span>
            <span class="text-success">Home</span>
          </h1>
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fas fa-bars ms-1"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
            <li class="nav-item"><router-link class="nav-link" to="/"><span class="navspan">홈</span></router-link></li>
            <li class="nav-item"><router-link class="nav-link" :to="{name:'noticelist', params:{text:'공지 사항'} }"><span class="navspan">공지사항</span></router-link></li>
            <li class="nav-item"><router-link class="nav-link" :to="{name:'qnalist', params:{text:'Q&A'} }"><span class="navspan">Q&A</span></router-link></li>
            <li class="nav-item"><router-link class="nav-link" :to="{name:'apt'}"><span class="navspan">아파트 매매 찾기</span></router-link></li>
            
            <template >
            <li class="nav-item" v-show="this.$store.getters.isLogin==false">
              <router-link :to="{name:'signup'}" class="btn btn-default btn-sm text-white">
                <i class="fa fs-5 fa-user pr-2 fa-beat-fade" style="--fa-animation-duration: 2s;"></i> <span class="navspan">SignUp</span>
              </router-link>
            </li>
            <li class="nav-item" v-show="this.$store.getters.isLogin==false">
              <router-link :to="{name:'login'}" class="btn btn-default btn-sm text-white">
                <i class="fa fs-5 fa-lock pr-2 fa-shake" style="--fa-animation-duration: 2s;"></i> <span class="navspan">Login</span>
              </router-link>
            </li>
            </template>

            <template>
              <li class="nav-item" v-show="this.$store.getters.isLogin==true">
                <router-link :to="{name:'mypage'}" class="btn btn-default btn-sm text-white">
                  <i class="fa-regular fs-5 fa-pen-to-square"></i> <span class="navspan"> MyPage</span>
                </router-link>
              </li>
              <li class="nav-item" v-show="this.$store.getters.isLogin==true">
                <div @click="logout" class="btn btn-default btn-sm text-white">
                  <i class="fa-solid fs-5 fa-right-from-bracket"></i> <span class="navspan">Logout</span>
                </div>
              </li>
            </template>
          </ul>
        </div>
      </div>
    </nav>
</template>

<script>
import http from '@/api/http'
export default {
  name: 'NavBar',

  data() {
    return {
      
    };
  },

  mounted() {
    
  },

  methods: {
    logout(){
      let url = 'users/logout';
      http.get(url)
      .then(() => {
        this.$store.dispatch('setisLogin',false);
        this.$store.dispatch('setMyRole', "");
        this.$store.dispatch('setMyName', "");
        this.$store.dispatch('setMyEmail', "");
      })
      this.$router.push({name:'home'});
    },
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
.navspan {
  font-family: 'Noto Sans KR', sans-serif;
  color:white;
  font-size: 20px;
  transform: skew(-0.1deg);
}
</style>