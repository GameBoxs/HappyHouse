<template>
  <div>
    <p>{{priceFilter}}</p>
    <p>{{finalDongCode}}</p>
  </div>
</template>

<script>
import http from '@/api/http'
export default {
  name: "AptList",

  data() {
    return {
        aptlist:[],
    };
  },

  mounted() {},

  methods: {
    readAptList() {
        let url = 'houseinfo/dongcode/'+this.finalDongCode;
        console.log(url);
        http.get(url)
        .then((response) => {
            return response;
        })
        .then(({data}) => {
            this.aptlist = data;
            console.log(this.aptlist);
            // for(let i in data){
            //     let opt = document.createElement('option');
            //     opt.setAttribute('value', data[i]);
            //     opt.innerText = data[i];
            //     document.querySelector('#gugun').appendChild(opt);
            // }
        })
        .catch((error) => {
            console.log(error + " (Error)");
        })
    }
  },

  props:{
    priceFilter:String,
    finalDongCode:String,
  },

  watch:{
    finalDongCode() {
        if(this.finalDongCode)
        {
            this.readAptList();
        }
        else{
            this.aptlist=[];
            console.log("aptlist: "+this.aptlist);
        }
    },
  },
};
</script>

<style></style>
