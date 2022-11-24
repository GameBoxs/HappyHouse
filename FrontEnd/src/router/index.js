import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserView from '@/views/UserView.vue'
import FindApt from '@/views/FindApt.vue'
import BoardView from '@/views/BoardView.vue'
import NoticeList from '@/components/Function/board/Notice/NoticeList.vue'
import NoticeDetail from '@/components/Function/board/Notice/NoticeDetail.vue'
import NoticeEdit from '@/components/Function/board/Notice/NoticeEdit.vue'
import NoticeWrite from '@/components/Function/board/Notice/NoticeWrite.vue'
import QnaList from '@/components/Function/board/Qna/QnaList.vue'
import QnaDetail from '@/components/Function/board/Qna/QnaDetail.vue'
import QnaEdit from '@/components/Function/board/Qna/QnaEdit.vue'
import QnaWrite from '@/components/Function/board/Qna/QnaWrite.vue'
import store from '../store/index'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/user',
    component: UserView,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import('@/components/User/LoginView.vue'),
      },
      {
        path: 'signup',
        name: 'signup',
        component: () => import('@/components/User/SignUpView.vue'),
      },
      {
        path: 'findpassword',
        name: 'findpassword',
        component: () => import('@/components/User/FindPassword.vue'),
      },
    ],
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: () => import('@/views/MyPage.vue'),
    meta: {
      auth: true
    }
  },
  {
    path: '/apt',
    name: 'apt',
    component: FindApt,
  },
  {
    path: '/board',
    name: 'board',
    component: BoardView,
    children: [
      {
        path: 'noticelist',
        name: 'noticelist',
        component: NoticeList
      },
      {
        path: 'noticedetail',
        name: 'noticedetail',
        component: NoticeDetail,
      },
      {
        path: 'noticeedit',
        name: 'noticeedit',
        component: NoticeEdit,
        meta: {
          auth: true
        }
      },
      {
        path: 'noticewrite',
        name: 'noticewrite',
        component: NoticeWrite,
        meta: {
          auth: true
        }
      },
      {
        path: 'qnalist',
        name: 'qnalist',
        component: QnaList
      },
      {
        path: 'qnadetail',
        name: 'qnadetail',
        component: QnaDetail,
      },
      {
        path: 'qnaedit',
        name: 'qnaedit',
        component: QnaEdit,
        meta: {
          auth: true
        }
      },
      {
        path: 'qnawrite',
        name: 'qnawrite',
        component: QnaWrite,
        meta: {
          auth: true
        }
      },
    ],
  },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/NavBar.vue')
  // }
]
function get_cookie() {
  var value = document.cookie.match('(^|;) ?' + 'JWT-Token' + '=([^;]*)(;|$)');
  return value? value[2] : null;
}
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior(){
    return {  x: 0, y: 0 }
  },
})
router.beforeEach((to, from, next) => {
  if (to.meta.auth && get_cookie() == null) {
    alert('로그인 세션이 만료되었습니다. 다시 로그인해 주세요!');
    store.state.isLogin = false;
    store.state.myRole = "";
    store.state.myName = "";
    store.state.myEmail = "";
    // store.dispatch('setisLogin',false);
    // store.dispatch('setMyRole', "");
    // store.dispatch('setMyName', "");
    // store.dispatch('setMyEmail', "");
    next('/');
    return;
  } else if(get_cookie() == null){
    store.state.isLogin = false;
    store.state.myRole = "";
    store.state.myName = "";
    store.state.myEmail = "";
    next();
    return;
  }
  next();
})
export default router
