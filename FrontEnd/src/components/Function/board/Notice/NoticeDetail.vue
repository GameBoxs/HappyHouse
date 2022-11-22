<template>
    <div class="container mt-2" style="" id="noticeDetailDiv">
        <!-- {{$route.query.id}} -->
        <div class="container mt-3">
            <!-- <hr/> -->
            <div class="row justify-content-md-center">
                <div class="col-md-10">
                    <table class="table table-condensed">
                        <thead>
                            <tr align="center">
                                <th width="10%">No.{{NoticeDetailItem.id}}</th>
                                <th width="60%">{{NoticeDetailItem.title}}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>작성일
                                </th>
                                <td>
                                {{NoticeDetailItem.createTime}}
                                </td>
                            </tr>
                            <tr>
                                <th>글쓴이
                                </th>
                                <td>
                                {{NoticeDetailItem.username}} <span style="float:right;"><span style='font-weight: bolder;'>조회 : </span>{{NoticeDetailItem.hit}}</span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p>{{NoticeDetailItem.content}}</p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p>
                                        <span class="commentTXT">댓글</span>
                                        <table class="table table-borderless commentTable">
                                            <tbody>
                                                <tr v-for="(item, index) in commentItem" :key="index" colspan="2" class="d-block">
                                                    <template v-if="item.delete == true">
                                                        <th>{{item.writeUserName}}</th>
                                                        <td class="" style="width:500px; font-size:15px;">{{item.content}}</td>
                                                        <td class="align-bottom" style="font-size:10px">({{item.createTime}})</td>
                                                        <td class="align-top"><button class="ms-5 btn btn-link" @click="removeComment(item.id)"><i class="fa-solid fa-x text-black"></i></button></td>
                                                    </template>
                                                    <template v-else>
                                                        <th>{{item.writeUserName}}</th>
                                                        <td class="" style="width:500px; font-size:15px;">{{item.content}}</td>
                                                        <td class="align-bottom" style="font-size:10px">({{item.createTime}})</td>
                                                    </template>
                                                </tr>
                                                
                                                <b-pagination
                                                v-model="currentPage"
                                                :per-page="perPage"
                                                :total-rows="rows"
                                                size="sm"
                                                align="center"
                                                class="mt-4 justyfiy-content-center">
                                                </b-pagination>
                                            </tbody>
                                        </table>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <textarea class="form-control" id="commentTextArea" rows="3" style="resize: none;" maxlength="200" :value="commentText" @input="changeComment"></textarea>
                        <button class="btn btn-outline-secondary mt-2" style="float:right;" @click="submitComment">등록</button>
                    </div>
                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <td>
                                    <span style='float:right'>
                                        <button type="button" id="list" class="btn btn-default">목록</button>
                                        <button type="button" id="modify" class="btn btn-default">수정</button>
                                        <button type="button" id="delete" class="btn btn-default">삭제</button>
                                        <button type="button" id="write" class="btn btn-default">글쓰기</button>
                                    </span>
                                </td>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <hr/>
        </div>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'NoticeDetail',

    data() {
        return {
            perPage: 10,
            currentPage: 1,
            totalElements:'0',
            commentItem:[],
            NoticeDetailItem:[],
            commentText:'',
        };
    },

    computed: {
        rows() {
                return this.totalElements;
            }
    },

    mounted() {
        this.getDetailItem();
        this.getCommentItem();
    },
    watch: {
        currentPage() {
            this.getCommentItem();
        }
    },

    methods: {
        removeComment(id) {
            if(window.confirm('정말로 댓글을 삭제 하시겠습니까?')) {
                let url = '/comments/'+id;
                http.delete(url)
                .then(() =>{
                    window.location.reload(true);
                    alert('삭제 성공!');
                })
                .catch((error) => {
                    console.log(error);
                    this.$router.replace({name:'noticelist'});
                    alert('댓글 삭제 실패!!');
                })
            }
        },
        submitComment() {
            let url = '/comments';
            http.post(url,{"boardId" : this.$route.query.id,"content" : this.commentText})
            .then(() =>{
                window.location.reload(true);
            })
            .catch((error) => {
                console.log(error);
                this.$router.replace({name:'noticelist'});
                alert('댓글 작성 실패!!');
            })
        },
        changeComment(e) {
            this.commentText = e.target.value;
        },
        getDetailItem() {
            let url = 'boards/'+this.$route.query.id;
            http.get(url)
            .then(({data}) =>{
                this.NoticeDetailItem = data;
                let convert = new Date(this.NoticeDetailItem.createTime);
                this.NoticeDetailItem.createTime = convert.getFullYear() + '-' + convert.getMonth() + '-' + convert.getDate() + ' ' + convert.getHours() + ":" + convert.getMinutes() + ":" + convert.getSeconds();
            })
            .catch((error) => {
                console.log(error);
                this.$router.replace({name:'home'});
                alert('게시글 상세정보 로딩에 에러가 발생했습니다.');
            })
        },
        getCommentItem() {
            let url = '/comments/boards/'+this.$route.query.id+'?&page='+(parseInt(this.currentPage)-1);
            http.get(url)
            .then(({data}) =>{
                this.commentItem = data.content;
                this.totalElements = data.totalElements;
                for(let i in this.commentItem) {
                    let convert = new Date(this.commentItem[i].createTime);
                    this.commentItem[i].createTime = convert.getFullYear() + '-' + convert.getMonth() + '-' + convert.getDate() + ' ' + convert.getHours() + ":" + convert.getMinutes() + ":" + convert.getSeconds();
                }
            })
            .catch((error) => {
                console.log(error);
                this.$router.replace({name:'home'});
                alert('댓글 로딩에 에러가 발생했습니다.');
            })
        }
    },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');

.commentTXT {
    font-weight: bolder;
}
.commentTable {
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 20px;
    color:black;
    transform: skew(-0.1deg);
}
</style>