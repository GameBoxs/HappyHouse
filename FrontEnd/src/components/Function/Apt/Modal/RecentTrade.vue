<template>
    <div id="recentTradeDiv">
        <b-card bg-variant="light" header="최근 거래가" class="text-center">
            <b-card-text>{{recentPrice}} 만원</b-card-text>
        </b-card>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'RecentTrade',

    data() {
        return {
            recentPrice:0,
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
                console.log(data[0]);
            })
        },
    },
};
</script>

<style scoped>

</style>