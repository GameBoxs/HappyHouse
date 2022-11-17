<template>
    <div class="findaptmain">
        <div class="bg-image">
            <div class="mask">
                <div class="d-flex justify-content-center align-items-center h-100 pt-5" style="flex-direction: column;">
                    <div>
                        <h1 class="text-white mb-5"><span class="text-warning">Find</span> <span class="text-success">Apartment</span></h1>
                    </div>
                    <div>
                        <ol class="breadcrumb justify-content-center mb-0">
                            <div class="row col-md justify-content-center mb-2">
                                <div class="form-group col-md">
                                    <select class="form-select bg-white text-black" id="sido" style="width:150px">
                                        <option value="all">도/광역시</option>
                                        <option value="11">서울시</option>
                                        <option value="41">경기도</option>
                                        <option value="28">인천광역시</option>
                                        <option value="26">부산광역시</option>
                                        <option value="30">대전광역시</option>
                                        <option value="27">대구광역시</option>
                                        <option value="31">울산광역시</option>
                                        <option value="36">세종시</option>
                                        <option value="29">광주광역시</option>
                                        <option value="42">강원도</option>
                                        <option value="43">충청북도</option>
                                        <option value="47">경상북도</option>
                                        <option value="48">경상남도</option>
                                        <option value="45">전라북도</option>
                                        <option value="46">전라남도</option>
                                        <option value="50">제주도</option>
                                    </select>
                                </div>
                                <div class="form-group col-md">
                                    <select class="form-select bg-white text-black" id="gugun" style="width:150px">
                                        <option value="all">구/군</option>
                                    </select>
                                </div>
                                <div class="form-group col-md">
                                    <select class="form-select bg-white text-black" id="dong" style="width:150px">
                                        <option value="all">동</option>
                                        <!-- <c:forEach var="dong" items="${donglist}">
                                            <option value="${dong.dongcode}"> ${dong.dongname}</option>
                                        </c:forEach> -->
                                    </select>
                                </div>
                                <button type="button" id="list-btn" class="btn btn-outline-warning ms-3 border-2 opacity-75" style="width: 210px">
                                    아파트매매정보가져오기
                                </button>
                                &nbsp;&nbsp;&nbsp;
                                <!-- <c:if test="${sessionScope.userInfo.id ne null }">
                                <button type="button" id="btnAddBookmark" class="btn btn-outline-primary" style="width: 210px">
                                    관심 지역 등록
                                </button>
                                </c:if> -->
                            </div>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div class="aptBodyDiv container-fluid">
            <div class="aptBody rounded-3 mx-auto">
                <MapView/>
            </div>
        </div>
    </div>
</template>

<script>
import MapView from '@/components/Function/MapView.vue'
export default {
    name: 'FindApt',

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
    components: {
        MapView,
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
body{
    /* background: #F2F2F2; */
    background: white;
}
.aptBodyDiv{
    position: relative;
    transform: translateY(-80px);
    z-index: 1;
}
</style>