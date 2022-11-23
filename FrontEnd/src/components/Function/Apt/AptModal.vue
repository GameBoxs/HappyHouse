<template>
    <div>
        <div class="roadview border" id="roadview"></div>
        <div class="aptName text-start mt-2">
            {{aptname}} 

                <span  @click="clickBookMark" v-show="isBookMarked==false"><i class="fa-regular fa-bookmark" style="float:right; cursor:pointer" ></i></span>


                <span @click="clickBookMark" v-show="isBookMarked==true"><i class="fa-solid fa-bookmark" style="float:right; cursor:pointer" ></i></span>

        </div>
        <b-tabs content-class="mt-3" fill style="" class="menuTab">
            <b-tab title="최근거래" active class="menuItem">
                <RecentTrade :aptcode="aptcode"/>
            </b-tab>
            <b-tab title="모든거래" class="menuItem"><TradeList :aptcode="aptcode"/></b-tab>
            <b-tab title="가격변동" class="menuItem"><PriceChart :aptcode="aptcode"/></b-tab>
            <b-tab title="북마크수" class="menuItem"><p>I'm a disabled tab!</p></b-tab>
        </b-tabs>
    </div>
</template>

<script>
import RecentTrade from '@/components/Function/Apt/Modal/RecentTrade.vue'
import PriceChart from '@/components/Function/Apt/Modal/PriceChart.vue'
import TradeList from '@/components/Function/Apt/Modal/TradeList.vue'
import http from '@/api/http'
export default {
    name: 'AptModal',

    components: {
        RecentTrade,
        PriceChart,
        TradeList,
    },

    data() {
        return {
            isBookMarked:false,
        };
    },

    props: {
        lat:String,
        lng:String,
        aptcode:Number,
        aptname:String,
    },

    mounted() {
        if(!window.kakao || !window.kakao.maps || window.kakao === undefined){
            const mapScript = document.createElement("script");
            mapScript.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=946e097cb36660234400eaaf59be1833&libraries=services";
            mapScript.addEventListener("load", () =>{
                window.kakao.maps.load(this.roadviewInit);
            });
            document.head.appendChild(mapScript);
        } else{
            this.roadviewInit();
        }
        let url = '/favorite/check/'+this.aptcode;
        http.get(url)
        .then(({data}) => {
            this.isBookMarked = data;
        })
        .catch((error) => {
            if(error.response.status==403){
                alert('로그인 세션 만료!');
                this.$router.replace({name:'home'});
            } else {
                alert('북마크 저장 실패!')
            }
        }) 
    },

    methods: {
        get_cookie() {
            var value = document.cookie.match('(^|;) ?' + 'JWT-Token' + '=([^;]*)(;|$)');
            return value? value[2] : null;
        },
        clickBookMark() {
            if(this.get_cookie() == null){
                alert('로그인 세션 만료!');
                this.$store.dispatch('setisLogin',false)
                this.$store.dispatch('setMyRole','')
                this.$store.dispatch('setMyName','')
                this.$store.dispatch('setMyEmail','')
                this.$router.replace({name:'home'});
                return;
            }
            // console.log('북마크 클릭 됨');
            let url = '/favorite/'+this.aptcode;
            if(this.isBookMarked){
                http.delete(url)
                .then(() => {
                    this.isBookMarked = false;
                    this.$emit('Change-Keys');
                })
                .catch((error) => {
                    if(error.massage=='삭제할 북마크가 없습니다.'){
                        alert('삭제할 북마크가 없습니다.');
                    } else if(error.response.status==403){
                        alert('로그인 세션 만료!');
                        this.$router.replace({name:'home'});
                    } else {
                        alert('북마크 저장 실패!')
                    }
                })
            } else{
                http.post(url)
                .then(() => {
                    this.isBookMarked = true;
                    this.$emit('Change-Keys');
                })
                .catch((error) => {
                    if(error.response.status==403){
                        alert('로그인 세션 만료!');
                        this.$router.replace({name:'home'});
                    } else {
                        alert('북마크 저장 실패!')
                    }
                })
            }
        },
        roadviewInit(){
                let roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
                let roadview = new window.kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
                let roadviewClient = new window.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

                let position = new window.kakao.maps.LatLng(this.lat, this.lng);

                // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
                roadviewClient.getNearestPanoId(position, 50, function(panoId) {
                roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
        },
    },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.roadview{
    width: 100%;
    height: 250px;
}
.aptName{
    font-family: 'Noto Sans KR', sans-serif;
        font-size: 30px;
        transform: skew(-0.1deg);
}

.menuItem::-webkit-scrollbar,
.menuItem::-webkit-scrollbar-thumb {
    width: 26px;
    border-radius: 13px;
    background-clip: padding-box;
    border: 10px solid transparent;
    color: rgba(0, 0, 0, 0.3);
}

.menuItem::-webkit-scrollbar-thumb {        
    box-shadow: inset 0 0 0 10px;
}
.menuTab {
    font-family: 'GmarketSansMedium';
}
.menuItem {
    overflow:auto;
    height:400px;
}
</style>