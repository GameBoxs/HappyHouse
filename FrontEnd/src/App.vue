<template>
  <div id="app">
    <NavBar/>
    <router-view/>
    <!-- <div class="footDiv">
    <FooterView/>
    </div> -->
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
// import FooterView from '@/components/FooterView.vue'
export default {
  name: 'App',
  data() {
    return {
      
    };
  },

  components: {
    NavBar,
    // FooterView,
  },

  mounted() {
    
  },

  methods: {
    
  },
};

</script>

<style>
@import "@/assets/css/styles.css";
/* 스크롤바 디자인 없애기(기능은 동작) 시작 */
body {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
body::-webkit-scrollbar {
    display: none;
}
#app{
  overflow: auto;
  /* min-height: 100%; */
  /* height: 100%; */
  /* padding-bottom: 80px; */
  /* margin-bottom: 80px; */
}
/* 스크롤바 디자인 없애기 끝 */





/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
