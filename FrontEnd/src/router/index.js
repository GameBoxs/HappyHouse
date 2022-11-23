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
import QnaList from '@/components/Function/board/QnaList.vue'
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
    ],
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
      },
      {
        path: 'noticewrite',
        name: 'noticewrite',
        component: NoticeWrite,
      },
      {
        path: 'qnalist',
        name: 'qnalist',
        component: QnaList
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

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
