<template>
    <div id="recentTradeDiv">
        <b-card bg-variant="light" header="최근 거래가" class="text-center mb-5 mt-5">
            <b-card-text class="recentText" style="font-size:25px">{{recentPrice}}</b-card-text>
        </b-card>
        <b-table stacked :items="items" :fields="fields"></b-table>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'RecentTrade',

    data() {
        return {
            recentPrice:0,
            items:[],
            fields: [
                {
                    key: 'floor',
                    label : '층',
                    thClass : "recentTh text-start"
                },
                {
                    key: 'area',
                    label: '크기(m²)',
                    thClass : "recentTh"
                },
                {
                    key: 'dong',
                    label: '법정동',
                    thClass : "recentTh"
                },
                {
                    key: 'jibun',
                    label: '지번',
                    thClass : "recentTh"
                },
                {
                    key: 'buildYear',
                    label: '건축연도',
                    thClass : "recentTh"
                },
                {
                    key: 'road',
                    label: '도로명주소',
                    thClass : "recentTh"
                },
            ],
        };
    },

    mounted() {
        this.getAptItem();
    },

    props: {
        aptcode:Number,
    },

    methods: {
        getAptItem() {
            let url='housedeal/aptcode/'+this.aptcode;
            http.get(url)
            .then(({data}) => {
                let item = {floor:data[0].floor, area:data[0].area, dong:data[0].houseInfo.dong, jibun:data[0].houseInfo.jibun, buildYear:data[0].houseInfo.buildYear, road:data[0].houseInfo.dongCode.sidoName+' '+data[0].houseInfo.dongCode.gugunName+' '+data[0].houseInfo.dongCode.dongName+' '+data[0].houseInfo.roadName};
                this.items.push(item);
                let max = new String(data[0].dealAmount).replace(/[\\,]/g,'');
                setTimeout(() => this.counter(max), 500);
            })
        },
        counter(max) {
            let now = max;
            const handle = setInterval(() => {
                this.recentPrice = Math.ceil(max - now);
            
                // 목표수치에 도달하면 정지
                if (now < 1) {
                    clearInterval(handle);
                    if(this.recentPrice < 10000){
                        this.recentPrice = this.recentPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'만원';
                    } else{
                        let first = parseInt(this.recentPrice/10000);
                        first = first.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'억 ';
                        let second = parseInt(this.recentPrice%10000);
                        if(second != 0){
                            second = second.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'만원';
                            this.recentPrice = first+second;
                        } else{
                            this.recentPrice = first;
                        }
                    }
                }
                
                // 증가되는 값이 계속하여 작아짐
                const step = now / 10;
                
                // 값을 적용시키면서 다음 차례에 영향을 끼침
                now -= step;
            }, 50);
        }
    },
};
</script>

<style scoped>
tr {
    text-align: start;
}
</style>