<template>
    <div>
        <canvas id="chart" style="margin-top:80px"></canvas>
    </div>
</template>

<script>
import http from '@/api/http'
import { Chart, BarElement, BarController, LinearScale, CategoryScale } from 'chart.js';
Chart.register(BarElement, BarController, LinearScale, CategoryScale);
export default {
    name: 'PriceChart',

    data() {
        return {
            myChart: null,
            yearLabel:[],
            priceData: [],
        };
    },

    props: {
        aptcode:Number,
    },

    mounted() {
        this.getAptItem();
    },

    methods: {
        getAptItem() {
            let url='housedeal/aptcode/'+this.aptcode;
            http.get(url)
            .then(({data}) => {
                console.log(data);
                let year = data.map((item) => item.dealYear).reverse();
                let yearNo = [...new Set(year)];
                this.yearLabel = yearNo;
                console.log(year =+ yearNo);

                let result = [];
                yearNo.forEach((year) => {
                    let max = Math.max( ...data.filter((item) => item.dealYear == year)
                        .map((item) =>
                                parseInt(item.dealAmount.replace(/,/g , ''))));
                                result.push(max);
                });
                this.priceData = result;
                console.log(result);
                this.fillData();
            })
        },
        fillData() {
            const ctx = document.getElementById('chart').getContext('2d');
            this.myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: this.yearLabel,
                    datasets: [
                        {
                            label: '# of Votes',
                            data: this.priceData,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        }
                    ]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }
    },
};
</script>

<style scoped>

</style>