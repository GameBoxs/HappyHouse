<template>
    <div>
        <b-card bg-variant="light" header="최근 거래가" class="text-center mb-5 mt-5">
            <b-card-text class="recentText" style="font-size:25px">{{recentPrice}}</b-card-text>
        </b-card>
    </div>
</template>

<script>
export default {
    name: 'BookMarkCount',

    data() {
        return {
            bookmarkCount:0,
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>