<template>
    <div>
        <div class="blank"/>
        <div class="aptBody">
            <div class="aptTitle">
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'WhereismyhomeVueFindApt',

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');
.blank {
    height: 400px;
    /* background-color: #212429; */
    background-image: url("@/assets/img/carousel/carousel-1.jpg");
}
.aptBody {
    height: 1000px;
    background: #F9F9F9;
}
.aptTitle h1{
    font-family: 'Do Hyeon', sans-serif;
    color:black;
    font-size: 70px;
    transform: skew(-0.1deg);
}
</style>