<template>
    <div class="mapMainDiv container-fluid">
        <div class="row rowbody">
            <div class="col-2">
                <div class="accordion pt-2" id="mapOptionOne">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="mapOptionItemOneOfOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOneOne" aria-expanded="true" aria-controls="collapseOne" style="background-color:white; color:black;">
                                <span class="" style="width:100%">주변 상권</span>
                            </button>
                        </h2>
                        <div id="collapseOneOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                아이템 1
                            </div>
                        </div>
                    </div>
                </div>
                <div class="accordion pt-2" id="mapOptionTwo">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="mapOptionItemTwoOfOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwoOne" aria-expanded="true" aria-controls="collapseOne" style="background-color:white; color:black;">
                                <span class="" style="width:100%">매매 가격</span>
                            </button>
                        </h2>
                        <div id="collapseTwoOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                아이템 1
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-10 p-2">
                <div id="map" class="rounded-3">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MapView',
    data() {
        return {
            map: null,
        };
    },
    mounted() {
        if(!window.kakao || !window.kakao.maps){
            // Kakao script 등록 //
            const mapScript = document.createElement("script");
            mapScript.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=46fd494f1be679a30b9f720d1fba5c6c&libraries=services";

           /* global kakao */
            mapScript.addEventListener("load", () =>{
                kakao.maps.load(this.mapInit);
            });

            document.head.appendChild(mapScript);
            // script 등록 끝 //
        } else {
            this.mapInit();
        }
    },
    methods: {
        mapInit() {
            var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
                level: 10 //지도의 레벨(확대, 축소 정도)
            };
            this.map = new kakao.maps.Map(container, options);
        },
    },
};
</script>

<style>
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
    .mapMainDiv{
        width: 100%;
        height: 100%;
        overflow: auto;
        -ms-overflow-style: none;
        scrollbar-width: none;
    }
    .mapMainDiv::-webkit-scrollbar {
    display: none;
    }
    .rowbody {
        height: 100%;
    }
    #map {
        width: 100%;
        height: 100%;
    }
    
    .accordion-button{
        font-family: 'GmarketSansMedium', sans-serif;
        font-size: 15px;
        transform: skew(-0.1deg);
    }
    
    .accordion-button span {
        color:#362222;
    }


    .accordion-button.collapsed::after {
        background: none;
        background-image: none;
    }

    .accordion-button:not(.collapsed)::after {
        background-image: none;
    }
</style>