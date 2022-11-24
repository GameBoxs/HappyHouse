<template>
    <div>
        <div id="bookMarkListDiv">
            <table class="table table-hover">
            <tbody>
                <tr v-for="item,index in bookmarknamelist" :key="index">
                    <td @click="moveToApt(item)">
                        {{item.name}}
                    </td>
                </tr>
            </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: "BookMarkList",

    data() {
        return {
            bookmarklist:[],
            bookmarknamelist:[],
            isModalOpen:true,
        };
    },

    mounted() {
        this.readBookMarkList();
    },

    methods: {
        get_cookie() {
            var value = document.cookie.match('(^|;) ?' + 'JWT-Token' + '=([^;]*)(;|$)');
            return value? value[2] : null;
        },
        readBookMarkList() {
            if(this.get_cookie() != null){
                let url = '/favorite/users';
                http.get(url)
                .then((response) => {
                    console.log(response);
                    return response;
                })
                .then(({data}) => {
                    console.log('북마크 데이터 : '+ data);
                    this.bookmarklist = data;
                    this.addlist();
                })
                .catch((error) => {
                    if(error.response.status == 403) {
                        this.$store.dispatch('setisLogin',false);
                        this.$store.dispatch('setMyRole', "");
                        this.$store.dispatch('setMyName', "");
                        this.$store.dispatch('setMyEmail', "");
                    }
                    console.log(error);
                })
            } else{
                this.$store.dispatch('setisLogin',false);
                this.$store.dispatch('setMyRole', "");
                this.$store.dispatch('setMyName', "");
                this.$store.dispatch('setMyEmail', "");
            }
        },
        addlist(){
            if(this.bookmarklist){
                for(let i in this.bookmarklist) {
                    this.bookmarknamelist.push({name:this.bookmarklist[i].apartmentName, aptcode:this.bookmarklist[i].aptCode, lng:this.bookmarklist[i].lng, lat:this.bookmarklist[i].lat});
                }
                this.makebookmarklist();
            }
        },
        makebookmarklist() {
            console.log("BookMarkList.vue bookmarknamelist");
            console.log(this.bookmarknamelist);
            if(this.bookmarknamelist.length>0){
                this.$emit('make-aptmarker', this.bookmarknamelist);
            } else{
                this.$emit('make-aptmarker', null);
            }
        },
        moveToApt(item){
            this.$emit('Move-Apt', item);
        }
    },

    props:{
        keys:Number,
    },

    watch:{
        keys: function() {
            this.bookmarklist=[];
            this.bookmarknamelist=[];
            this.readBookMarkList();
        },
    },
};
</script>

<style scoped>
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
td {
  display: block;
  font-family: 'GmarketSansMedium', sans-serif;
  font-size: 15px;
  transform: skew(-0.1deg);
  margin-bottom: 3px;
  color: black;
  cursor: pointer;
}
</style>
