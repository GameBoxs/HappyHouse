<template>
    <div>
        <div class="roadview border" id="roadview"></div>
        <div>
            {{aptname}}
        </div>
    </div>
</template>

<script>
export default {
    name: 'AptModal',

    data() {
        return {
            
        };
    },

    props: {
        lat:String,
        lng:String,
        aptcode:String,
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

    },

    methods: {
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

<style>
.roadview{
    width: 100%;
    height: 250px;
}
</style>