<template>
    <div class="boardMain">
        <div class="bg-image">
            <div class="mask">
                <div class="d-flex justify-content-center align-items-center h-100 pt-5" style="flex-direction: column;">
                    <div>
                        <!-- <h1 class="mb-5 text-warning" v-if="$route.params.text == '공지 사항'">{{$route.params.text}}</h1> -->
                        <h1 class="mb-5 text-warning">{{maskname}}</h1>
                        <!-- <h1 class="mb-5" v-if="$route.params.text == 'Q&A'">
                            <span class="text-danger">{{$route.params.text.substr(0,1)}} </span>
                            <span class="text-white">{{$route.params.text.substr(1,1)}} </span>
                            <span class="text-primary">{{$route.params.text.substr(2,1)}}</span>
                        </h1> -->
                        
                    </div>
                </div>
            </div>
        </div>
        <div class="boardBodyDiv container-fluid">
            <div class="boardBody rounded-3 mx-auto">
                <div class="px-2 py-2" style="height:100%">
                    <router-view :key="$route.fullPath" @Mask-Name="setMaskName"/>
                </div>
            </div>
        </div>
        <div class="space">
        </div>
    </div>
</template>

<script>
export default {
    name: 'BoardView',

    data() {
        return {
            maskname:'',
        };
    },

    mounted() {

    },

    methods: {
        setMaskName(text) {
            this.maskname = text;
        }
    },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
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
    /* box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff; */
    box-shadow: 1px 3px 26px 11px rgba(0,0,0,0.2);
    /* margin-left: -150px; */
    /* left: -260px; */
    width: 95%;
    /* height: 700px; */
    /* background: #F9F9F9; */
    
    background: #F9F9F9;
    position: relative;
}

/* @media (min-width: 2400px){
    .boardBody {
        box-shadow: 1px 3px 26px 11px rgba(0,0,0,0.2);
        width: 95%;
        height: 800px;
        background: #F9F9F9;
        position: relative;
    }
} */

.space {
    height: 100px;
}

body{
    /* background: #F2F2F2; */
    background: white;
}
.boardBodyDiv{
    /* position: sticky; */
    transform: translateY(-80px);
    z-index: 1;
}

.boardMain{
    min-height: 100vh;
}
</style>