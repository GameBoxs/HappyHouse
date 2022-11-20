<template>
  <div>
    <div id="aptListDiv">
      <table class="table table-hover">
        <tbody>
          <tr v-for="item,index in aptnamelist" :key="index">
            <td @click="moveToApt(item)">
              {{item.name}}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import http from '@/api/http'
export default {
  name: "AptList",

  data() {
    return {
        aptlist:[],
        aptnamelist:[],
        isModalOpen:true,
    };
  },

  mounted() {},

  methods: {
    readAptList() {
        let url = 'housedeal/'+this.finalDongCode;
        console.log(url);
        http.get(url)
        .then((response) => {
            return response;
        })
        .then(({data}) => {
            this.aptlist = data;
            this.addlist();
        })
        .catch((error) => {
            console.log(error + " (Error)");
        })
    },
    addlist(){
      for(let i in this.aptlist) {
        if(this.priceFilter == 101000){
          if(this.aptnamelist.some(arr => arr.name == this.aptlist[i].houseInfo.apartmentName) == false){
            this.aptnamelist.push({name:this.aptlist[i].houseInfo.apartmentName, aptcode:this.aptlist[i].houseInfo.aptCode, lng:this.aptlist[i].houseInfo.lng, lat:this.aptlist[i].houseInfo.lat});
          }
        }
        else{
          let price = new String(this.aptlist[i].dealAmount).replace(/[\\,]/g,'');
          if(this.aptnamelist.some(arr => arr.name == this.aptlist[i].houseInfo.apartmentName) == false && parseInt(price) < parseInt(this.priceFilter)){
            this.aptnamelist.push({name:this.aptlist[i].houseInfo.apartmentName, aptcode:this.aptlist[i].houseInfo.aptCode, lng:this.aptlist[i].houseInfo.lng, lat:this.aptlist[i].houseInfo.lat});
          }
        }
      }
      this.makeAptList();
    },
    makeAptList() {
      if(this.aptnamelist){
        this.$emit('make-aptmarker', this.aptnamelist);
      }
    },
    moveToApt(item){
      this.$emit('Move-Apt', item);
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
          this.aptnamelist=[];
          this.readAptList();
        }
        else{
            this.aptlist=[];
            this.aptnamelist=[];
        }
    },
    priceFilter() {
      this.aptnamelist=[];
      this.addlist();
    }
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
td {
  display: block;
  font-family: 'GmarketSansMedium', sans-serif;
  font-size: 15px;
  transform: skew(-0.1deg);
  margin-bottom: 3px;
  color: black;
  cursor: pointer;
}
</style>
