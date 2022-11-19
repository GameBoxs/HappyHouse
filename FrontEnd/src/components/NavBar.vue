<template>
    <nav class="navbar navbar-expand-xxl navbar-dark fixed-top" id="mainNav">
      <div class="container-fluid">
        <router-link class="navbar-brand" to="/">
          <h1 class="fw-bold text-waring m-0 ms-5">Where <span class="text-white">Is My </span>
            <span class="text-success">Home</span>
          </h1>
          <!-- <img src="@/assets/img/navbar-logo.svg" alt="..." /> -->
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fas fa-bars ms-1"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
            <li class="nav-item"><router-link class="nav-link" to="/"><span class="navspan">홈</span></router-link></li>
            <li class="nav-item"><a class="nav-link" href="#services"><span class="navspan">공지사항</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#portfolio"><span class="navspan">Q&A</span></a></li>
            <li class="nav-item"><router-link class="nav-link" :to="{name:'apt'}"><span class="navspan">아파트 매매 찾기</span></router-link></li>
            <li class="nav-item"><a class="nav-link" href="#team"><span class="navspan">오늘의 뉴스</span></a></li>
            <li class="nav-item">
              <a href="${root}/member?act=mvregist" class="btn btn-default btn-sm text-white">
                <i class="fa fs-5 fa-user pr-2 fa-beat-fade" style="--fa-animation-duration: 2s;"></i> <span class="navspan">SignUp</span>
              </a>
            </li>
            <li class="nav-item">
              <router-link :to="{name:'login'}" class="btn btn-default btn-sm text-white">
                <i class="fa fs-5 fa-lock pr-2 fa-shake" style="--fa-animation-duration: 2s;"></i> <span class="navspan">Login</span>
              </router-link>
            </li>
          </ul>
        </div>
        <!-- <div class="ms-5">
          <div class="btn-group">
            <a href="${root}/member?act=mvregist" class="btn btn-default btn-sm text-white"><i class="fa fs-5 fa-user pr-2 fa-beat-fade" style="--fa-animation-duration: 2s;"></i> <span class="navspan">SignUp</span></a>
          </div>
          <div class="btn-group">
            <router-link :to="{name:'login'}" class="btn btn-default btn-sm text-white">
              <i class="fa fs-5 fa-lock pr-2 fa-shake" style="--fa-animation-duration: 2s;"></i> <span class="navspan">Login</span>
            </router-link>
          </div> -->
          <!-- <div class="top-bar text-white-50 row gx-0 align-items-center d-none d-lg-flex" style="display: none;">
            <div class="col-3 col-sm-6 col-lg-9"></div>
            <div class="col-9 col-sm-6 col-lg-3">
              <div id="header-top-second" class="clearfix">
                <div class="btn-group">
                    <a href="${root}/member?act=logout" class="btn btn-default btn-sm" id="logoutBtn"><i class="fa fa-user pr-2"></i> LogOut</a>
                </div>
                <div class="btn-group">
                  <button id="header-top-drop-2" type="button" class="btn dropdown-toggle btn-default btn-sm dropdown-toggle--no-caret" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fa fa-lock pr-2"></i> 마이 페이지
                  </button>
                  <ul class="dropdown-menu bg-dark" aria-labelledby="dropdownMenu2">
                    <li><button class="dropdown-item text-white" type="button" id="mypageMove" onclick="location.href='mypage.jsp'">내 정보 페이지</button></li>
                    <li><button class="dropdown-item text-white" type="button" onclick="location.href='bookmark.jsp'">관심지역 보기</button></li>
                    <c:if test="${userInfo.level eq 'admin' }">
                      <li><button class="dropdown-item text-white" type="button" onclick="location.href='sign_upForAdmin.jsp'">관리자 계정 추가</button></li>                                
                    </c:if>
                  </ul>
                </div>
              </div>
            </div>
          </div> -->
        <!-- </div> -->
      </div>
    </nav>
</template>

<script>
export default {
  name: 'NavBar',

  data() {
    return {
      
    };
  },

  mounted() {
    
  },

  methods: {
    
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