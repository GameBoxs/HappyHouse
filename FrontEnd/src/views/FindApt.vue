<template>
    <div class="findaptmain">
        <div class="black-bg" v-if="isModalOpen" @mousedown="modalClose($event)" id='black-bg'>
            <div class="white-bg">
                <AptModal :lat="requestItem.lat" :lng="requestItem.lng" :aptcode="requestItem.aptCode" :aptname="requestItem.aptname"/>
            </div>
        </div>
        <div class="bg-image">
            <div class="mask">
                <div class="d-flex justify-content-center align-items-center h-100 pt-5" style="flex-direction: column;">
                    <div>
                        <h1 class="text-white mb-5">아파트 <span class="text-info">매매</span> 찾기</h1>
                    </div>
                    <div>
                        <ol class="breadcrumb justify-content-center mb-0">
                            <div class="row col justify-content-center mb-2">
                                <div class="form-group col">
                                    <select class="form-select bg-white text-black" v-model="sido" style="width:150px;">
                                        <option value="all">도/광역시</option>
                                        <option value="서울특별시">서울특별시</option>
                                        <option value="경기도">경기도</option>
                                        <option value="인천광역시">인천광역시</option>
                                        <option value="부산광역시">부산광역시</option>
                                        <option value="대전광역시">대전광역시</option>
                                        <option value="대구광역시">대구광역시</option>
                                        <option value="울산광역시">울산광역시</option>
                                        <option value="세종특별자치시">세종특별자치시</option>
                                        <option value="광주광역시">광주광역시</option>
                                        <option value="강원도">강원도</option>
                                        <option value="충청북도">충청북도</option>
                                        <option value="경상북도">경상북도</option>
                                        <option value="경상남도">경상남도</option>
                                        <option value="전라북도">전라북도</option>
                                        <option value="전라남도">전라남도</option>
                                        <option value="제주특별자치도">제주특별자치도</option>
                                    </select>
                                </div>
                                <div class="form-group col">
                                    <select class="form-select bg-white text-black" id="gugun" v-model="gugun" style="width:150px;">
                                        <option value="all">구/군</option>
                                    </select>
                                </div>
                                <div class="form-group col">
                                    <select class="form-select bg-white text-black" id="dong" v-model="dong" style="width:150px;">
                                        <option value="all">동</option>
                                    </select>
                                </div>

                                <button type="button" id="list-btn" class="btn btn-outline-warning ms-3 border-2" style="width: 50px" @click="resetAll">
                                    <i class="fa fa-undo"></i>
                                </button>
                            </div>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div class="aptBodyDiv container-fluid">
            <div class="aptBody rounded-3 mx-auto">
                <MapView :finalInfo="finalInfo" @request-modal="RequestModal"/>
            </div>
        </div>
        <div class="space">
        </div>
    </div>
</template>

<script>
import MapView from '@/components/Function/MapView.vue'
import AptModal from '@/components/Function/Apt/AptModal.vue'
import http from '@/api/http'
export default {
    name: 'FindApt',

    data() {
        return {
            sido:"all",
            gugun:"all",
            dong:"all",
            finalInfo:{
                dongCode:"",
                name:"",
            },
            isModalOpen:false,
            requestItem:null,
        };
    },

    mounted() {
        
    },

    watch:{
        sido: function() {
            // 작업 해야 할 것
            // 구/군 초기화
            document.querySelector('#gugun').replaceChildren();
            let initgu = document.createElement('option');
            initgu.setAttribute('value','all');
            initgu.innerText = '구/군';
            document.querySelector('#gugun').appendChild(initgu);
            this.gugun='all';
            
            // 동 초기화
            document.querySelector('#dong').replaceChildren();
            let initdo = document.createElement('option');
            initdo.setAttribute('value','all');
            initdo.innerText = '동';
            document.querySelector('#dong').appendChild(initdo);
            this.dong='all';
            
            if (this.sido && this.sido!='all') {
                let regcode = this.sido;

                let temp = `/dongcode/`;
                let url = temp+regcode;
                http.get(url)
                .then((response) => {
                    return response;
                })
                .then(({data}) => {
                    for(let i in data){
                        let opt = document.createElement('option');
                        opt.setAttribute('value', data[i]);
                        opt.innerText = data[i];
                        document.querySelector('#gugun').appendChild(opt);
                    }
                })
                .catch((error) => {
                    console.log(error + " (Error)");
                })
            }
        },
        gugun: function() {
            // 동 초기화
            document.querySelector('#dong').replaceChildren();
            let initdo = document.createElement('option');
            initdo.setAttribute('value','all');
            initdo.innerText = '동';
            document.querySelector('#dong').appendChild(initdo);
            this.dong='all';
            
            if (this.gugun && this.gugun!='all') {
                let regcode = this.gugun;
                let temp = `/dongcode/${this.sido}/`;
                let url = temp+regcode;
                http.get(url)
                .then((response) => {
                    return response;
                })
                .then(({data}) => {
                    for(let i in data){
                        let opt = document.createElement('option');
                        opt.setAttribute('value', data[i]);
                        opt.innerText = data[i];
                        document.querySelector('#dong').appendChild(opt);
                    }
                })
                .catch((error) => {
                    console.log(error + " (Error)");
                })
            }
        },
        dong: async function() {
            if (this.dong && this.dong!='all') {
                let regcode = this.dong;
                let temp = `/dongcode/${this.sido}/${this.gugun}/`;
                let url = temp+regcode;
                await http.get(url)
                .then((response) => {
                    return response;
                })
                .then(({data}) => {
                    this.finalInfo.dongCode = data[0].dongCode;
                    this.finalInfo.name = data[0].name;
                })
                .catch((error) => {
                    console.log(error + " (Error)");
                });
                // console.log(this.finalInfo.dongCode);
                // console.log(this.finalInfo.name);
            }
        }
    },

    methods: {
        resetAll() {
            this.sido = 'all';

            // 구/군 초기화
            document.querySelector('#gugun').replaceChildren();
            let initgu = document.createElement('option');
            initgu.setAttribute('value','all');
            initgu.innerText = '구/군';
            document.querySelector('#gugun').appendChild(initgu);
            this.gugun='all';
            
            // 동 초기화
            document.querySelector('#dong').replaceChildren();
            let initdo = document.createElement('option');
            initdo.setAttribute('value','all');
            initdo.innerText = '동';
            document.querySelector('#dong').appendChild(initdo);
            this.dong='all';

            this.finalInfo.dongCode="";
            this.finalInfo.name="";
        },
        modalClose(e) {
            console.log(e.target.id);
            if(e.target.id == 'black-bg'){
                this.isModalOpen = false;
            }
        },
        RequestModal(item) {
            let convertJson = JSON.stringify(item);
            this.requestItem = JSON.parse(convertJson);
            this.isModalOpen=true;
        }
    },
    components: {
        MapView,
        AptModal,
    }
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
    font-size: 50px;
    transform: skew(-0.1deg);
}
#list-btn{
    font-family: 'Jua', sans-serif;
    font-size: 17px;
}
ol{
    font-family: 'Noto Sans KR', sans-serif;
}
.aptBody {
    /* box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff; */
    box-shadow: 1px 3px 26px 11px rgba(0,0,0,0.2);
    /* margin-left: -150px; */
    /* left: -260px; */
    width: 95%;
    height: 700px;
    /* background: #F9F9F9; */
    
    background: #F9F9F9;
    position: relative;
}

@media (min-width: 2400px){
    .aptBody {
        box-shadow: 1px 3px 26px 11px rgba(0,0,0,0.2);
        width: 95%;
        height: 800px;
        background: #F9F9F9;
        position: relative;
    }
}
.space {
    height: 100px;
}

body{
    /* background: #F2F2F2; */
    background: white;
}
.aptBodyDiv{
    /* position: sticky; */
    transform: translateY(-80px);
    z-index: 1;
}

.findaptmain{
    min-height: 100vh;
}

.black-bg {
    top:0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    position: fixed;
    padding: 20px;
    z-index: 30;
}
.white-bg {
    height: 90%;
    width: 20%;
    margin: 5% auto;
    background: white;
    border-radius: 5px;
    padding: 20px;
}
</style>