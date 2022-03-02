import * as echarts from 'echarts';

export default function getSalesLine(XTime,YAccount,YCount){
  let chartDom = document.getElementById('salesLine');
  let myChart = echarts.init(chartDom);
  let option;

  option = {
    tooltip: {
      trigger: 'axis'
    },
    toolbox: {
      show: true,
      feature: {
        dataZoom: {
          yAxisIndex: 'none'
        },
        magicType: {type: ['line', 'bar']},
        restore: {},
        saveAsImage: {}
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: XTime
    },
    yAxis: {
      type: 'value',
      axisLabel: {
        formatter: '{value}'
      }
    },
    series: [
      {
        name: '销售量',
        type: 'line',
        smooth: true,
        data: YCount,
        markLine: {
          data: [
            {type: 'average', name: '平均值'}
          ]
        }
      },
      {
        name: '销售值',
        type: 'line',
        smooth: true,
        data: YAccount,

        markLine: {
          data: [
            {type: 'average', name: '平均值'},
            [{
              symbol: 'none',
              x: '90%',
              yAxis: 'max'
            }, {
              symbol: 'circle',
              label: {
                position: 'start',
                formatter: '最大值'
              },
              type: 'max',
              name: '最高点'
            }]
          ]
        }
      }
    ]
  };

  option && myChart.setOption(option);
}

