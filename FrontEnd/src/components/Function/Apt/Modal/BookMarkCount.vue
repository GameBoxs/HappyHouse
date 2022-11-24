<template>
    <div>
        <b-card bg-variant="light" header="북마크 수" class="text-center mb-5 mt-5">
            <b-card-text class="recentText" style="font-size:25px">{{bookmarkCount}}</b-card-text>
        </b-card>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'BookMarkCount',

    data() {
        return {
            bookmarkCount:0,
        };
    },

    props: {
        aptcode:Number,
    },

    mounted() {
        let url='/favorite/'+this.aptcode;
        http.get(url)
        .then(({data}) => {
            this.bookmarkCount = data.cnt;
        })
    },

    methods: {
        
    },

    
};
</script>

<style lang="scss" scoped>

</style>