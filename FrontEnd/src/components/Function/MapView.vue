<template>
    <div class="mapMainDiv container-fluid">
        <div class="row rowbody">
            <div class="col-2 d-none d-xl-block " style="height:100%; ">
                <div class="leftSid" style="height:700px; overflow:auto">
                    <div class="accordion pt-2" id="mapOptionOne">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="mapOptionItemOneOfOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOneOne" aria-expanded="true" aria-controls="collapseOne" style="background-color:white; color:black;">
                                    <span class="" style="width:100%">주변 상권</span>
                                </button>
                            </h2>
                            <div id="collapseOneOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" role="switch" id="bigmart" v-model="bigmart">
                                        <label class="form-check-label ms-2" for="bigmart">대형 마트</label>
                                    </div>
                                    <div class="text-success opacity-50">
                                        <hr class="mt-1 mb-1">
                                    </div>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" role="switch" id="mart" v-model="mart">
                                        <label class="form-check-label ms-2" for="mart">편의점</label>
                                    </div>
                                    <div class="text-success opacity-50">
                                        <hr class="mt-1 mb-1">
                                    </div>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" role="switch" id="cafe" v-model="cafe">
                                        <label class="form-check-label ms-2" for="cafe">카페</label>
                                    </div>
                                    <div class="text-success opacity-50">
                                        <hr class="mt-1 mb-1">
                                    </div>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" role="switch" id="bank" v-model="bank">
                                        <label class="form-check-label ms-2" for="bank">은행</label>
                                    </div>
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
                                    <label for="customRange" class="form-label customRangeLabel" v-if="priceFilter < 101000">금액 : 5000만원 ~ {{priceFilter | rangeFilter}}</label>
                                    <label for="customRange" class="form-label customRangeLabel" v-else>금액 : 무제한</label>
                                    <input type="range" class="form-range" min="5000" max="101000" id="customRange" v-model="priceFilter" step="1000">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="accordion pt-2" id="mapOptionThree">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="aptListH2">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#aptList" aria-expanded="true" aria-controls="collapseOne" style="background-color:white; color:black;">
                                    <span class="" style="width:100%">검색 결과 - 아파트 리스트</span>
                                </button>
                            </h2>
                            <div id="aptList" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body aptListBody" style="overflow:auto; height:242px">
                                    <AptList :priceFilter="priceFilter" :finalDongCode="finalInfo.dongCode" @make-aptmarker="setAptList" @Move-Apt="moveApt"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 북마크 리스트 만들어야 함 AptList 그대로 복사해서 응용 -->
                    <div class="accordion pt-2 mb-2" id="mapOptionFour">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="aptListH2">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#aptList" aria-expanded="true" aria-controls="collapseOne" style="background-color:white; color:black;">
                                    <span class="" style="width:100%">북마크 리스트</span>
                                </button>
                            </h2>
                            <div id="aptList" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body aptListBody" style="overflow:auto; height:242px">
                                    <BookMarkList :keys="bookrerenderkey" @make-aptmarker="setBookList" @Move-Apt="moveApt" v-if="this.$store.getters.isLogin == true"/>
                                </div>
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
import AptList from '@/components/Function/Apt/AptList.vue'
import BookMarkList from '@/components/Function/Apt/BookMarkList.vue' 
export default {
    name: 'MapView',
    components: {
        AptList,
        BookMarkList,
    },
    props:{
        finalInfo: {
        type:Object,
        default() {
                return {};
            },
        },
        keys:Number,
    },
    data() {
        return {
            map: null,
            placeOverlay:null,
            ps:null,
            contentNode:document.createElement('div'),
            markers:[[],[],[],[],[],[]],
            infows:[],
            locPosition:"",
            geocoder:null,
            bigmart:"",
            mart:"",
            cafe:"",
            bank:"",
            priceFilter:"101000",
            aptList:[],
            tempAptList:[],
            bookList:[],
            bookrerenderkey:1,
        };
    },
    mounted() {
        if(!window.kakao || !window.kakao.maps){
            // Kakao script 등록 //
            const mapScript = document.createElement("script");
            mapScript.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=946e097cb36660234400eaaf59be1833&libraries=services";
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
    filters: {
        rangeFilter: function(value) {
            if(value.length <= 4){
                return value + '만원';
            } else{
                if(value % 10000 == 0){
                    return value/10000 + '억';
                } else{
                    return parseInt(value/10000) + '억 ' + value%10000 + '만원';
                }
            }
        }
    },
    methods: {
        mapInit() {
            let message='';
            var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
                level: 5 //지도의 레벨(확대, 축소 정도)
            };
            this.placeOverlay = new kakao.maps.CustomOverlay({zIndex:1});
            this.map = new kakao.maps.Map(container, options);
            this.ps = new kakao.maps.services.Places(this.map);
            kakao.maps.event.addListener(this.map, 'idle', this.searchPlaces);
            this.contentNode.className = 'placeinfo_wrap';
            this.addEventHandle(this.contentNode, 'mousedown', kakao.maps.event.preventMap);
            this.addEventHandle(this.contentNode, 'touchstart', kakao.maps.event.preventMap);
            this.placeOverlay.setContent(this.contentNode);
            // 주소-좌표 변환 객체를 생성합니다
            this.geocoder = new kakao.maps.services.Geocoder();
            // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
            if (navigator.geolocation) {
                // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                navigator.geolocation.getCurrentPosition((position) => {
                    var lat = position.coords.latitude, // 위도
                        lon = position.coords.longitude; // 경도
                    let locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                    this.map.setCenter(locPosition);
                    // message = '<div style="padding:5px;">현재위치</div>'; // 인포윈도우에 표시될 내용입니다
                    // // 마커와 인포윈도우를 표시합니다
                    // this.displayMarker(locPosition, message);
                });
            } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
                this.locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
                    message = 'geolocation을 사용할수 없어요..'
                this.displayMarker(this.locPosition, message);
            }
        },
        displayMarker(locPosition, message) {
            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: this.map,
                position: locPosition
            });
			this.markers.push(marker);
            var iwContent = message, // 인포윈도우에 표시할 내용
                iwRemoveable = true;
            // 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: iwContent,
                removable: iwRemoveable
            });
			this.infows.push(infowindow);
            // 인포윈도우를 마커위에 표시합니다 
            infowindow.open(this.map, marker);
            // 지도 중심좌표를 접속위치로 변경합니다
            this.map.setCenter(locPosition);
        },
        removeInfoWindo(){
            for ( var i = 0; i < this.infows.length; i++ ) {
                this.infows[i].setMap(null);
            }   
            this.infows = [];
        },
        addEventHandle(target, type, callback) {
            if (target.addEventListener) {
                target.addEventListener(type, callback);
            } else {
                target.attachEvent('on' + type, callback);
            }
        },
        removeCategoryMarker(){
            for(let i=0; i<6; i++){
                for(var j = 0; j < this.markers[i].length; j++){
                    this.markers[i][j].setMap(null);
                }
            }
            this.markers = [[],[],[],[],[],[]];
        },
        turnOffCategoryMarker(index){
            for(var i = 0; i < this.markers[index].length; i++){
                this.markers[index][i].setMap(null);
            }
        },
        searchPlaces() {
            if (this.bigmart==false && this.mart==false && this.bank==false && this.cafe==false && this.aptList==null && this.bookList==null) {
                return;
            }

            if(this.placeOverlay==null) {
            console.log(this.placeOverlay);
    return;
}
            
            // 커스텀 오버레이를 숨깁니다 
            this.placeOverlay.setMap(null);
            // 지도에 표시되고 있는 마커를 제거합니다
            this.removeCategoryMarker();
            
            if(this.bigmart==true){
                this.ps.categorySearch('MT1', this.placesSearchCB, {useMapBounds:true}); 
            }
            if(this.mart==true){
                this.ps.categorySearch('CS2', this.placesSearchCB, {useMapBounds:true}); 
            }
            if(this.bank==true){
                this.ps.categorySearch('BK9', this.placesSearchCB, {useMapBounds:true}); 
            }
            if(this.cafe==true){
                this.ps.categorySearch('CE7', this.placesSearchCB, {useMapBounds:true}); 
            }
            if(this.aptList){
                this.makeAptMarker(this.aptList);
            }
            if(this.bookList){
                this.makeBookMarker(this.bookList);
            }
        },
        placesSearchCB(data, status) {
            if (status === kakao.maps.services.Status.OK) {
                // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
                this.displayPlaces(data);
            } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
            } else if (status === kakao.maps.services.Status.ERROR) {
                // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
                
            }
        },
        displayPlaces(places) {
            // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
            // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
            let categoryName = places[0].category_group_code;
            let order = 0;
            if(categoryName == 'MT1'){
                order=1;
            } else if (categoryName == 'CS2') {
                order=5;
            } else if (categoryName == 'BK9') {
                order=0;
            } else {
                order=4;
            }
            
            for ( var i=0; i<places.length; i++ ) {
                    // 마커를 생성하고 지도에 표시합니다
                    var marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);
                    // 마커와 검색결과 항목을 클릭 했을 때
                    // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
                    ((marker, place) => {
                        kakao.maps.event.addListener(marker, 'click', () => {
                            this.displayPlaceInfo(place);
                        });
                    })(marker, places[i]);
            }
        },
        addMarker(position, order) {
            let imageSrc = ''; // 마커 이미지 url, 스프라이트 이미지를 씁니다
            if(order==1){
                imageSrc = require("@/assets/img/marker/bigmart.png");
            } else if (order==5) {
                imageSrc = require('@/assets/img/marker/mart.png');
            } else if (order==0) {
                imageSrc = require('@/assets/img/marker/bank.png');
            } else {
                imageSrc = require('@/assets/img/marker/cafe.png');
            }

            let imageSize = new kakao.maps.Size(33, 44),  // 마커 이미지의 크기
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
                    marker = new kakao.maps.Marker({
                    position: position, // 마커의 위치
                    image: markerImage 
                });

            // 1 : 대형마트, 5 : 편의점, 0: 은행, 4: 카페
            if(order==1){
                this.markers[0].push(marker);  // 배열에 생성된 마커를 추가합니다
            } else if (order==5) {
                this.markers[1].push(marker);  // 배열에 생성된 마커를 추가합니다
            } else if (order==0) {
                this.markers[2].push(marker);  // 배열에 생성된 마커를 추가합니다
            } else {
                this.markers[3].push(marker);  // 배열에 생성된 마커를 추가합니다
            }
                marker.setMap(this.map); // 지도 위에 마커를 표출합니다
            return marker;
        },
        displayPlaceInfo (place) {
            let content = '<div class="placeinfo">' +
                            '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   
            if (place.road_address_name) {
                content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                            '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
            }  else {
                content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
            }                
        
            content += '    <span class="tel">' + place.phone + '</span>' + 
                        '</div>' + 
                        '<div class="after"></div>';
            this.contentNode.innerHTML = content;
            this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
            this.placeOverlay.setMap(this.map);  
        },
        setAptList(aptnamelist) {
            this.aptList = [];
            if(aptnamelist == null){
                return;
            }
            console.log('MapView.vue 366 aptnamelist : ' + JSON.stringify(aptnamelist) + 'bookList : ' + this.bookList);
            if(aptnamelist || aptnamelist.length>0){
                console.log(typeof(this.bookList));
                if(this.bookList.length>0){
                    this.tempAptList = aptnamelist;
                    let temp = aptnamelist.filter(item1 => this.bookList.some(item2 => item1.name != item2.name))
                    this.aptList = temp;
                    console.log('MapView.vue 371 aptList : ' + this.aptList);
                } else{
                    console.log('MapView.vue tempAptList : ');
                    console.log(this.tempAptList);
                    if(this.tempAptList.length>0){
                        this.aptList = this.tempAptList;
                        this.tempAptList = [];
                    } else {
                        this.aptList = aptnamelist;
                    }
                }
                this.searchPlaces();
            }
        },
        setBookList(booknamelist) {
            this.bookList = [];
            if(booknamelist == null){
                this.bookList = [];
                this.setAptList(this.aptList);
                return;
            }
            if(booknamelist) {
                this.bookList = booknamelist;
            }
            console.log('setBookList MapView 384 : '+this.aptList);
            this.setAptList(this.aptList);
        },
        makeAptMarker(aptnamelist) {
            if(aptnamelist){
                for(let i in aptnamelist){
                    let locPosition = new kakao.maps.LatLng(aptnamelist[i].lat, aptnamelist[i].lng);
                    
                    let imageSrc = require('@/assets/img/marker/house.png');
                    let imageSize = new kakao.maps.Size(33,44);
                    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
                    
                    let marker = new kakao.maps.Marker({
                        map: this.map,
                        position: locPosition,
                        image: markerImage
                    });
                    this.markers[4].push(marker);
                    ((marker, aptitem) => {
                        kakao.maps.event.addListener(marker, 'click', () => {
                            this.aptmodal(aptitem);
                        });
                    })(marker, aptnamelist[i]);
                }
            }
        },
        makeBookMarker(booknamelist) {
            if(booknamelist){
                for(let i in booknamelist){
                    let locPosition = new kakao.maps.LatLng(booknamelist[i].lat, booknamelist[i].lng);
                    
                    let imageSrc = require('@/assets/img/marker/bookmark.png');
                    let imageSize = new kakao.maps.Size(33,44);
                    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
                    
                    let marker = new kakao.maps.Marker({
                        map: this.map,
                        position: locPosition,
                        image: markerImage
                    });
                    this.markers[5].push(marker);
                    ((marker, aptitem) => {
                        kakao.maps.event.addListener(marker, 'click', () => {
                            this.aptmodal(aptitem);
                        });
                    })(marker, booknamelist[i]);
                }
            }
        },
        aptmodal(aptitem){
            // console.log("aptitem 보내기 전 json: " + JSON.stringify(aptitem));
            this.$emit('request-modal', aptitem);
        },
        moveApt(item) {
            let locPosition = new kakao.maps.LatLng(item.lat,item.lng);
            this.map.setCenter(locPosition);
            this.map.setLevel(1);
        }
    },
    watch:{
        keys: function() {
            this.bookrerenderkey = this.keys;
        },
        finalInfo:{ 
            deep:true,
            handler(newData) {
                // console.log("MapView.vue - finalInfo 바뀜")
                this.removeCategoryMarker();
                this.removeInfoWindo();
                this.searchPlaces();
                // this.makeAptMarker();
                // 주소로 좌표를 검색, 이름이 있을때만(초기화 해서 이름이 없으면 진행 안함)
                if(newData.name){
                    this.geocoder.addressSearch(newData.name, (result, status) => {
                        // 정상적으로 검색이 완료됐으면 
                        if (status === kakao.maps.services.Status.OK) {
                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                            this.map.setCenter(coords);
                            this.map.setLevel(5);
                        } 
                    });  
                }
            },
        },
        bigmart:function() {
            if(this.bigmart==true){
                this.searchPlaces();
            } else{
                this.turnOffCategoryMarker(0);
            }
        },
        mart:function() {
            if(this.mart==true){
                this.searchPlaces();
            } else{
                this.turnOffCategoryMarker(1);
            }
        },
        bank:function() {
            if(this.bank==true){
                this.searchPlaces();
            } else{
                this.turnOffCategoryMarker(2);
            }
        },
        cafe:function() {
            if(this.cafe==true){
                this.searchPlaces();
            } else{
                this.turnOffCategoryMarker(3);
            }
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
    @media (max-width:1199px){
        #map {
            width: 100vw;
            margin-left: 2px;
        }
    }
    .accordion-button, .form-check-label, .customRangeLabel{
        font-family: 'GmarketSansMedium', sans-serif;
        font-size: 15px;
        transform: skew(-0.1deg);
    }
    
    .accordion-button span {
        color:#362222;
    }

    .aptListBody {
        -ms-overflow-style: none;
        scrollbar-width: none;
    }
    .aptListBody::-webkit-scrollbar {
        display: none;
    }

    .accordion-button.collapsed::after {
        background: none;
        background-image: none;
    }

    .accordion-button:not(.collapsed)::after {
        background-image: none;
    }

    @media(max-width: 140px){
        .accordion{
            padding-top: 8px;
            padding-bottom: 8px;
            padding-left: 10px;
            padding-right: 10px;
        }
    }
    @media (min-width: 2400px){
        #aptList{
            height: 343px;
        }
    }

    .placeinfo_wrap {
        position:absolute;
        bottom:28px;
        left:-150px;
        width:300px;
        }
    .placeinfo {
        position:relative;
        width:100%;
        border-radius:6px;
        border: 1px solid #ccc;
        border-bottom:2px solid #ddd;
        padding-bottom: 10px;
        background: #fff;
        }
    .placeinfo:nth-of-type(n) {
        border:0; 
        box-shadow:0px 1px 2px #888;
        }
    .placeinfo_wrap .after {
        content:'';
        position:relative;
        margin-left:-12px;
        left:50%;
        width:22px;
        height:12px;
        background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
        }
    .placeinfo a, .placeinfo a:hover, .placeinfo a:active {
        color:#fff;
        text-decoration: none;
        }
    .placeinfo a, .placeinfo span {
        display: block;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
        }
    .placeinfo span {
        margin:5px 5px 0 5px;
        cursor: default;
        font-size:13px;
        }
    .placeinfo .title {
        font-weight: bold; 
        font-size:14px;
        border-radius: 6px 6px 0 0;
        margin: -1px -1px 0 -1px;
        padding:10px; 
        color: #fff;
        background: #d95050;
        background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;
        }
    .placeinfo .tel {
        color:#0f7833;
        }
    .placeinfo .jibun {
        color:#999;
        font-size:11px;
        margin-top:0;
        }

.leftSid::-webkit-scrollbar,
.leftSid::-webkit-scrollbar-thumb {
    width: 26px;
    border-radius: 13px;
    background-clip: padding-box;
    border: 10px solid transparent;
}

.leftSid::-webkit-scrollbar-thumb {        
    box-shadow: inset 0 0 0 10px;
}
.leftSid    {
    color: rgba(0, 0, 0, 0);
}
.leftSid:hover {
    color: rgba(0, 0, 0, 0.3);
}
</style>