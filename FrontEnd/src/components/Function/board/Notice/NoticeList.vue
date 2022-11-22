<template>
    <div class="container mt-2" style="" id="noticeListDiv">
        <button class="btn btn-primary mb-3 mt-5" @click="redirectNoticeList">글 목록</button>
        <b-table id="boardListTable" striped hover 
            :items="items"
            :per-page="perPage"
            :fields="fields"
            :current-page="1" small
        >
            <template #cell(title)="data">
                <!-- <template @click="test(data.item.id)">{{data.value}} / {{data.item.id}}</template> -->
                <router-link :to="{path:'noticedetail', query:{id:data.item.id} }"> {{data.value}} </router-link>
            </template>
        </b-table>
        <!-- <div class="text-end" v-if="this.$store.getters.getMyRole == 'ADMIN' "> -->
        <div class="row mt-5">
            <div class="col-2"></div>
            <div class="col-8">
                <div class="p-1 bg-light rounded rounded-pill shadow-sm mb-4">
                    <div class="input-group">
                        <input type="search" placeholder="제목 입력" aria-describedby="button-addon1" class="form-control border-0 bg-light" @input="searchTextChange" :value="searchText" @keyup.enter="searchStart">
                        <div class="input-group-append">
                            <button id="button-addon1" type="button" class="btn btn-link text-primary" @click="searchStart"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-2 text-end">
                <button class="btn btn-primary">글 작성</button>
            </div>
        </div>
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
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'NoticeList',

    data() {
        return {
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
            searchText:"",
            perPage: 10,
            currentPage: 1,
            totalElements:'',
            isSearch: false,
        }
    },

    mounted() {
        let url = 'boards?boardType=notice&page=0';
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
        // console.log(this.$store.getters.)
    },

    computed: {
        rows() {
                return this.totalElements;
            }
    },
    watch: {
        currentPage() {
            let url = '';
            if(this.isSearch == false){
                url = 'boards?boardType=notice&page='+(parseInt(this.currentPage)-1);
            } else{
                url = 'boards/search?title='+ this.searchText + '&page=' +(parseInt(this.currentPage)-1);
            }
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
                console.log(e);
                alert('게시글 불러오기에 실패 했습니다.');
            })
        }
    },
    methods: {
        searchStart(){
            let url = 'boards/search?title='+ this.searchText;
            http.get(url)
            .then((response) => {
                return response;
            })
            .then(({data}) => {
                this.isSearch = true;
                this.currentPage = 1;
                this.items = [];
                console.log(data);
                this.items = data.content;
                console.log(this.items);
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
        searchTextChange(e) {
            this.searchText = e.target.value;
        },
        redirectNoticeList() {
            // this.$router.push({name:'noticelist'});
            window.location.reload(true);
        },
        test(data) {
            console.log(data);
        }
    },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');

thead th {
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 20px;
    color:black;
    transform: skew(-0.1deg);
}

button, .input-group {
    font-family: 'Noto Sans KR', sans-serif;
    transform: skew(-0.1deg);
}

#boardListTable {
    font-family: 'Noto Sans KR', sans-serif;
    color:black;
    transform: skew(-0.1deg);
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
</style>