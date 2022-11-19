<template>
    <div class="userDiv">
        <router-view/>
    </div>
</template>

<script>
export default {
    name: 'UserView',

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
    .userDiv{
        /* position: relative; */
        min-height: 100vh;
        width: 100%;
        background: white;
    }
</style>