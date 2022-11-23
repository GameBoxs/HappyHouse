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
        readBookMarkList() {
            let url = '/favorite/users';
            http.get(url)
            .then((response) => {
                return response;
            })
            .then(({data}) => {
                console.log('북마크 데이터 : '+ data);
                this.bookmarklist = data;
                this.addlist();
            })
            .catch((error) => {
                console.log(error + " (Error)");
            })
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
            if(this.bookmarknamelist){
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
