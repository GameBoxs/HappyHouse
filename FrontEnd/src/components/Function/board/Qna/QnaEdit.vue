<template>
    <div class="container mt-2" style="" id="qnaEditDiv">
        <div class="container mt-3">
            <div class="row justify-content-md-center">
                <div class="col-md-10">
                    <table class="table table-condensed">
                        <thead>
                            <tr align="center">
                                <th width="10%">No.{{QnaDetailItem.id}}</th>
                                <th width="60%"><textarea class="form-control" rows="1" @input="changeTitleText" maxlength="50" :value="titleText" required style="width:100%; resize: none;"></textarea></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>작성일
                                </th>
                                <td>
                                {{QnaDetailItem.createTime}}
                                </td>
                            </tr>
                            <tr>
                                <th>글쓴이
                                </th>
                                <td>
                                {{QnaDetailItem.username}} <span style="float:right;"><span style='font-weight: bolder;'>조회 : </span>{{QnaDetailItem.hit}}</span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p><textarea class="form-control" id="commentTextArea" rows="5" style="resize: none;" maxlength="500" :value="contentText" @input="changeContent" required></textarea></p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <button class="btn btn-outline-secondary mt-2" style="float:right;" @click="submitEdit">수정</button>
                        <button class="btn btn-outline-secondary mt-2" style="" @click="returnDetail">수정 취소</button>
                    </div>
                </div>
            </div>
            <hr/>
        </div>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'QnaEdit',

    data() {
        return {
            QnaDetailItem:[],
            contentText:'',
            titleText:'',
        };
    },

    computed: {
        rows() {
                return this.totalElements;
            }
    },

    mounted() {
        this.getDetailItem();
    },

    watch: {
    },

    methods: {
        changeTitleText(e) {
            this.titleText = e.target.value;
        },
        changeContent(e) {
            this.contentText = e.target.value;
        },
        returnDetail() {
            this.$router.replace({path:'qnadetail', query:{id:this.QnaDetailItem.id} });
        },
        submitEdit() {
            let url = '/boards/'+this.$route.query.id;
            http.patch(url,{"title" : this.titleText, "content" : this.contentText})
            .then(() =>{
                alert('수정 성공!');
                this.returnDetail();
            })
            .catch((error) => {
                console.log(error);
                this.$router.replace({name:'qnalist'});
                alert('댓글 작성 실패!!');
            })
        },
        getDetailItem() {
            let url = 'boards/'+this.$route.query.id;
            http.get(url)
            .then(({data}) =>{
                this.QnaDetailItem = data;
                let convert = new Date(this.QnaDetailItem.createTime);
                this.QnaDetailItem.createTime = convert.getFullYear() + '-' + convert.getMonth() + '-' + convert.getDate() + ' ' + convert.getHours() + ":" + convert.getMinutes() + ":" + convert.getSeconds();
                this.contentText = this.QnaDetailItem.content;
                this.titleText = this.QnaDetailItem.title;
            })
            .catch((error) => {
                console.log(error);
                this.$router.replace({name:'home'});
                alert('게시글 상세정보 로딩에 에러가 발생했습니다.');
            })
        },
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