<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
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
            <li class="nav-item"><a class="nav-link" href="#about"><span class="navspan">아파트 매매 찾기</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#team"><span class="navspan">오늘의 뉴스</span></a></li>
          </ul>
        </div>
        <div class="ms-5">
          <div class="btn-group">
            <a href="${root}/member?act=mvregist" class="btn btn-default btn-sm text-white"><i class="fa fs-5 fa-user pr-2 fa-beat-fade" style="--fa-animation-duration: 2s;"></i> <span class="navspan">SignUp</span></a>
          </div>
          <div class="btn-group">
            <router-link :to="{name:'login'}" class="btn btn-default btn-sm text-white">
              <i class="fa fs-5 fa-lock pr-2 fa-shake" style="--fa-animation-duration: 2s;"></i> <span class="navspan">Login</span>
            </router-link>
            <!-- <button type="button" class="btn btn-default btn-sm text-white" data-bs-toggle="modal" data-bs-target="#exampleModal">
              <i class="fa fs-5 fa-lock pr-2 fa-shake" style="--fa-animation-duration: 2s;"></i> <span class="navspan">Login</span>
            </button> -->
            <!-- <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-body">
                    <section>
                      <div class="container">
                        <div class="card bg-dark text-white" style="border-radius: 1rem;">
                          <div class="card-body p-5 text-center">
                            <div class="">
                              <form method="post" action="${root}/member">
                                <input type="hidden" name="act" value="login">
                                <h2 class="fw-bold text-white mb-2 text-uppercase">Login</h2>
                                <p class="text-white-50 mb-5">Please enter your login and password!</p>
                                <div class="form-outline form-white mb-4">
                                  <label class="form-label" for="id">ID</label>
                                  <input type="text" id="nid" name="id" class="form-control form-control-lg" />
                                </div>
                                <div class="form-outline form-white mb-4">
                                  <label class="form-label" for="password">Password</label>
                                  <input type="password" id="password" name="password" class="form-control form-control-lg" />
                                </div>
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
                              </form>
                            </div>
                            <div class="pt-5">
                                <p class="mb-0">Don't have an account? <a href="${root}/member?act=mvregist" class="text-white-50 fw-bold">SignUp</a></p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </section>
                  </div>
                </div>
              </div>
            </div> -->
          </div>
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
        </div>
      </div>
    </nav>
  </div>
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