<template>
    <div class="boardMain">
        <div class="bg-image">
            <div class="mask">
                <div class="d-flex justify-content-center align-items-center h-100 pt-5" style="flex-direction: column;">
                    <div>
                        <h1 class="mb-5 text-white">마이 페이지</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="boardBodyDiv container-fluid">
            <div class="boardBody rounded-3 mx-auto">
                <div class="p-5 container" style="height:100%">
                    <div class="logo">
                        <img src="https://cdn.pixabay.com/photo/2018/09/06/18/26/person-3658927_960_720.png" alt="">
                    </div>
                    <b-tabs content-class="mt-3" align="center" class="mt-5 tabs" lazy>
                        <b-tab title="회원 정보" active>
                            <h3> 회원 정보 수정 </h3>
                            <b-card header-tag="header" class="mt-3">
                                <template #header>
                                    <h6 class="pt-2">사이트 이용 정보 입력</h6>
                                </template>
                                <div class="mb-2">
                                    이름 <span v-b-tooltip.hover title="가입시 설정한 이름"><i class="fa-regular fa-circle-question"></i></span>
                                </div>
                                <div>
                                    <input class="form-control" type="text" disabled :value="$store.getters.getMyName">
                                </div>
                                <div class="mb-2 mt-3">
                                    <span>이메일</span> <span v-b-tooltip.hover title="ex. ssafy@ssafy.com"><i class="fa-regular fa-circle-question"></i></span>
                                </div>
                                <div>
                                    <input class="form-control" type="text" disabled :value="$store.getters.getMyEmail">
                                </div>
                                <div class="row mt-3">
                                    <div class="col">
                                        <div class="mb-2">
                                            비밀번호 <span v-b-tooltip.hover title="변경할 비밀번호 입력"><i class="fa-regular fa-circle-question"></i></span>
                                        </div>
                                        <div>
                                            <input class="form-control" type="password" >
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="mb-2">
                                            비밀번호 확인 <span v-b-tooltip.hover title="변경할 비밀번호 확인"><i class="fa-regular fa-circle-question"></i></span>
                                        </div>
                                        <div>
                                            <input class="form-control" type="password" >
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-4">
                                    <button class="btn btn-outline-secondary" style="float:right;" @click="submitChange">변경</button>
                                </div>
                            </b-card>
                        </b-tab>
                        <b-tab title="작성한 글 목록" lazy>
                            <h3> 작성한 QnA 게시글 </h3>
                            <b-table id="boardListTable" striped hover 
                                :items="items"
                                :per-page="perPage"
                                :fields="fields"
                                :current-page="1" small
                            >
                                <template #cell(title)="data">
                                    <!-- <template @click="test(data.item.id)">{{data.value}} / {{data.item.id}}</template> -->
                                    <router-link :to="{path:'qnadetail', query:{id:data.item.id} }" class="routerlink"> {{data.value}} </router-link>
                                </template>
                            </b-table>
                            <div class="mt-3">
                                <b-pagination
                                v-model="currentPage"
                                :per-page="perPage"
                                :total-rows="rows"
                                size="sm"
                                align="center"
                                class="mt-4 justyfiy-content-center">
                                </b-pagination>
                            </div>
                        </b-tab>
                    </b-tabs>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'MyPage',

    data() {
        return {
            maskname:'',
            password:'',
            passwordcheck:'',
            fields: [
                {
                    key: 'id',
                    label : 'No.'
                },
                {
                    key: 'title',
                    label: '제목',
                    tdClass: 'noticeTitleItem',
                },
                {
                    key: 'username',
                    label: '작성자',
                    tdClass: 'noticeNameItem',
                },
                {
                    key: 'createTime',
                    label: '작성일',
                    tdClass: 'noticeTimeItem',
                },
                {
                    key: 'hit',
                    label: '조회수',
                    tdClass: 'noticeHitItem',
                },
            ],
            items:[
            ],
            perPage: 10,
            currentPage: 1,
            totalElements:'',
        };
    },

    mounted() {
        let url = 'boards/user?boardType=qna&page=0';
        http.get(url)
        .then((response) => {
            return response;
        })
        .then(({data}) => {
            this.items = data.content;
            this.totalElements = data.totalElements;
            for(let i in this.items) {
                let convert = new Date(this.items[i].createTime);
                this.items[i].createTime = convert.getFullYear() + '-' + convert.getMonth() + '-' + convert.getDate() + ' ' + convert.getHours() + ":" + convert.getMinutes();
            }
        })
        .catch((e) => {
            console.log(e);
            alert('게시글 불러오기에 실패 했습니다.');
        })
    },

    computed: {
        rows() {
            return this.totalElements;
        }
    },

    watch : {
        currentPage() {
            let url = 'boards/user?boardType=qna&page='+(parseInt(this.currentPage)-1);
            http.get(url)
            .then((response) => {
                return response;
            })
            .then(({data}) => {
                this.items = [];
                this.items = data.content;
                this.totalElements = data.totalElements;
                for(let i in this.items) {
                    let convert = new Date(this.items[i].createTime);
                    this.items[i].createTime = convert.getFullYear() + '-' + convert.getMonth() + '-' + convert.getDate() + ' ' + convert.getHours() + ":" + convert.getMinutes();
                }
            })
            .catch((e) => {
                if(e.response.status == 403){
                    alert('로그인 세션이 만료 되었습니다!');
                    this.$store.dispatch('isLogin',false);
                    this.$store.dispatch('myName','');
                    this.$store.dispatch('myRole','');
                    this.$store.dispatch('myEmail','');
                    this.$router.replace({name:'home'});
                    return;
                }
                console.log(e);
                alert('게시글 불러오기에 실패 했습니다.');
            })
        }
    },

    methods: {
        checkPattern() {
            let pwdcheck = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*]{4,20}$/;
            if(this.password < 4){
                alert('4글자 이상 입력!!');
                return false;
            } else if(pwdcheck.test(this.password) == false){
                alert("문자,숫자가 최소 1개 이상 필요!!");
                return false;
            } else if(pwdcheck.test(this.pwd) == true) {
                return true;
            }
        },
        submitChange() {
            if(this.password != this.passwordcheck){
                alert('비밀번호와 확인이 일치하지 않습니다!');
            } else{
                if(this.checkPattern){
                    let url = 'users/password';
                    http.patch(url,{"password":new String(this.password)})
                    .then(() => {
                        alert('비밀번호 변경에 성공했습니다!');
                        this.$router.go();
                    })
                    .catch((error) => {
                        if(error.response.status == 403){
                            alert('로그인 세션이 만료 되었습니다!');
                            this.$store.dispatch('isLogin',false);
                            this.$store.dispatch('myName','');
                            this.$store.dispatch('myRole','');
                            this.$store.dispatch('myEmail','');
                            this.$router.replace({name:'home'});
                            return;
                        }
                        console.log('비밀번호 변경 에러! -');
                        console.log(error);
                    })
                }
            }
        }
    },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.boardBodyDiv, h6 {
  font-family: 'GmarketSansMedium', sans-serif;
  transform: skew(-0.1deg);
}

.bg-image{
    height: 500px;
    width: 100%;
    background: url('@/assets/img/findAptTitle.jpg');     
}
.mask{
    background-color: rgba(0, 0, 0, 0.6);
    height: 500px;
}
.mask h1{
    font-family: 'Black Han Sans', sans-serif;
    color:white;
    font-size: 70px;
    transform: skew(-0.1deg);
}

.boardBody {
    box-shadow: 1px 3px 26px 11px rgba(0,0,0,0.2);
    width: 95%;
    height: 750px;
    
    background: white;
    position: relative;
}

.boardBodyDiv{
    transform: translateY(-80px);
    z-index: 1;
}

.boardMain{
    min-height: 100vh;
}

.logo {
    width: 80px;
    margin: auto;
}

.logo img {
    width: 140%;
    height: 110px;
    object-fit: cover;
    border-radius: 50%;
    box-shadow: 0px 0px 3px #5f5f5f,
        0px 0px 0px 5px #ecf0f3,
        8px 8px 15px #a7aaa7,
        -8px -8px 15px #fff;
}

#boardListTable >>> .noticeTitleItem {
    width:63%;
}
#boardListTable >>> .noticeTimeItem {
    width:13%;
}
#boardListTable >>> .noticeNameItem {
    width:10%;
}
#boardListTable >>> .noticeHitItem {
    width:10%;
}

#boardListTable >>> .noticeTitleItem:hover {
    cursor: pointer;
}

.routerlink {
    text-decoration: none;
    color: black;
}
</style>