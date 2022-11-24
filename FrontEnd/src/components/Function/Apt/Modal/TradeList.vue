<template>
    <div>
        <div class="d-flex justify-content-end">
            <b-form-select v-model="selectYear" :options="yearLabel"></b-form-select>
            <b-form-select v-if="selectYear!=null" v-model="selectMonth" :options="monthLabel" class="ms-2"></b-form-select>
        </div>
        <div class="mt-2">
            <b-table stacked :items="tableItems" :fields="fields"></b-table>
        </div>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'FrontEndTradeList',

    data() {
        return {
            allItem:{},
            selectYear:null,
            selectMonth:null,
            yearLabel:[],
            monthLabel:[],
            yearitems:{},
            monthitems:{},
            tableItems:[],
            fields: [
                {
                    key: 'tradeday',
                    label : '거래일자',
                },
                {
                    key: 'price',
                    label: '거래가격',
                },
            ],
        };
    },
    
    props: {
        aptcode:Number,
    },

    mounted() {
        let url='housedeal/aptcode/'+this.aptcode;
        http.get(url)
        .then(({data}) => {
            this.allItem = data;
            let year = data.map((item) => item.dealYear);
            let yearNo = [...new Set(year)];

            this.yearLabel.push({value:null, text:'년도 선택', disabled:true});
            for(let item in yearNo){
                this.yearLabel.push({value:yearNo[item], text:yearNo[item]});
            }
        })
    },

    watch: {
        selectYear() {
            this.searchYear();
        },
        selectMonth() {
            this.searchMonth();
        }
    },

    methods: {
        searchYear() {
            if(this.selectYear != null){
                this.monthLabel=[];
                this.selectMonth=null;
                let yearItems = this.allItem.filter(item => {
                    return item.dealYear == this.selectYear;
                });
                this.yearitems = yearItems;
                let month = yearItems.map((items) => items.dealMonth);
                let monthNo = [...new Set(month)].reverse();

                this.monthLabel.push({value:null, text:'All'});
                for(let item in monthNo){
                    this.monthLabel.push({value:monthNo[item], text:monthNo[item]});
                }
                this.remakeItems(0);
            }
        },
        searchMonth() {
            if(this.selectMonth != null) {
                let monthItems = this.yearitems.filter(item => {
                    return item.dealMonth == this.selectMonth;
                });
                this.monthitems = monthItems;
                this.remakeItems(1);
            } else {
                this.searchYear();
            }
        },
        remakeItems(check) {
            this.tableItems = [];
            let whatItem = [];
            if(check == 0){
                whatItem = this.yearitems;
            } else {
                whatItem = this.monthitems;
            }
            for(let i in whatItem){
                let convertPrice = parseInt(whatItem[i].dealAmount.replace(/,/g , ''));
                if(convertPrice < 10000){
                    convertPrice = convertPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'만원';
                } else{
                    let first = parseInt(convertPrice/10000);
                    first = first.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'억 ';
                    let second = parseInt(convertPrice%10000);
                    if(second != 0){
                        second = second.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'만원';
                        convertPrice = first+second;
                    } else{
                        convertPrice = first;
                    }
                }
                this.tableItems.push({tradeday:whatItem[i].dealYear + '년 ' + whatItem[i].dealMonth + '월 ' + whatItem[i].dealDay + '일', price:convertPrice})
            }
        }
    },
};
</script>

<style scoped>

</style>