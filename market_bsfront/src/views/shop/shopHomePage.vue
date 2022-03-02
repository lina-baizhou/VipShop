<template>
    <div style="width: 100%;height: auto;padding-top: 20rem;padding-bottom: 20rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center;font-size: 12rem">
        <div style="width: 95%;height: 100rem;background-color: white;border-radius: 5rem;display: flex;justify-content: space-evenly;align-items: center">
            <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly">
                <span style="font-size: 20rem;color: steelblue">{{shopOrders.nonPaymentList.length}}</span>
                <span style="font-size: 12rem">待付款</span>
            </div>

            <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly">
                <span style="font-size: 20rem;color: steelblue">{{shopOrders.undeliveredList.length}}</span>
                <span style="font-size: 12rem">待发货</span>
            </div>

            <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly">
                <span style="font-size: 20rem;color: steelblue">{{shopOrders.paidList.length}}</span>
                <span style="font-size: 12rem">待收货</span>
            </div>

            <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly">
                <span style="font-size: 20rem;color: steelblue">{{shopOrders.nonCommentList.length}}</span>
                <span style="font-size: 12rem">待评价</span>
            </div>

            <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly">
                <span style="font-size: 20rem;color: steelblue">{{shopOrders.refundList.length}}</span>
                <span style="font-size: 12rem">待退款</span>
            </div>

            <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly">
                <span style="font-size: 20rem;color: steelblue">{{shopOrders.exchangeList.length}}</span>
                <span style="font-size: 12rem">待换货</span>
            </div>
        </div>

        <div style="width: 95%;display: flex;justify-content: space-between;height: 260rem;margin-top: 20rem">
            <div style="width: 49%;height: 100%;background-color: white;border-radius: 5rem;display: flex;flex-direction: column;align-items: center;justify-content: space-between">
                <div style="width: 100%;height: 50rem;border-bottom:1rem #eff0f4 solid;display: flex;justify-content: center">
                    <div style="width: 95%;height: 100%;display: flex;justify-content: space-between;align-items: center">
                        <span style="width: 250rem;display: flex;justify-content: space-between;align-items: center">
                            <span style="font-size: 13rem">实时数据</span>
                            <span style="font-size: 12rem;color: darkgrey">更新时间: {{time}}</span>
                        </span>
                    </div>
                </div>

                <div style="width: 92%;height: 125rem;display: flex;justify-content: space-between;align-items: center">
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">商品浏览量</span>
                        <span style="font-size: 20rem;">{{shop.browseCount}}</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">商品粉丝数</span>
                        <span style="font-size: 20rem;">{{shop.fansNumber}}</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">店铺粉丝数</span>
                        <span style="font-size: 20rem;">{{shop.goodsFansCount}}</span>
                    </div>
                </div>
                <div style="width: 95%;height: 125rem;display: flex;justify-content: space-between;align-items: center">
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">支付金额</span>
                        <span style="font-size: 20rem;">{{shop.saleAccount}}元</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">支付订单数</span>
                        <span style="font-size: 20rem;">{{shop.saleCount}}单</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                    </div>
                </div>
            </div>
            <div style="width: 49%;height: 100%;background-color: white;border-radius: 5rem;display: flex;flex-direction: column;align-items: center;justify-content: space-between">
                <div style="width: 100%;height: 50rem;border-bottom:1rem #eff0f4 solid;display: flex;justify-content: center">
                    <div style="width: 95%;height: 100%;display: flex;justify-content: space-between;align-items: center">
                        <span style="width: 250rem;display: flex;justify-content: space-between;align-items: center">
                            <span style="font-size: 13rem">店铺评论</span>
                            <span style="font-size: 12rem;color: darkgrey">更新时间: {{time}}</span>
                        </span>
                    </div>
                </div>

                <div style="width: 95%;height: 125rem;display: flex;justify-content: space-between;align-items: center">
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">好评</span>
                        <span style="font-size: 20rem;">{{shopComments.niceComments.length}}</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">中评</span>
                        <span style="font-size: 20rem;">{{shopComments.centerComments.length}}</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">差评</span>
                        <span style="font-size: 20rem;">{{shopComments.badComments.length}}</span>
                    </div>
                </div>
                <div style="width: 92%;height: 125rem;display: flex;justify-content: space-between;align-items: center">
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">待回复</span>
                        <span style="font-size: 20rem;">{{shopComments.nonAnswer.length}}</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                        <span style="font-size: 14rem;color:grey;border-bottom: 1rem lightgrey dotted">差评待处理</span>
                        <span style="font-size: 20rem;">{{shopComments.nonHandle.length}}</span>
                    </div>
                    <div style="width: 100rem;height: 80rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                    </div>
                </div>
            </div>
        </div>

        <div style="width: 95%;display: flex;flex-direction:column;justify-content: space-between;height: 400rem;margin-top: 20rem;margin-bottom: 20rem;background-color: white;border-radius: 5rem">
            <div style="width: 100%;height: 50rem;display: flex;justify-content: space-evenly;align-items: center;border-bottom: 1rem #eff0f4 solid">
                <div style="width: 98%;height: 100%;display: flex;justify-content: space-between;align-items: center">
                    <span style="width: 300rem;display: flex;justify-content: space-between;align-items: center">
                            <span style="font-size: 13rem">店铺销售趋势图</span>
                            <span style="font-size: 12rem;color: darkgrey">更新时间: {{time}}</span>
                    </span>

                    <div style="width: 200rem;height:100%;display: flex;justify-content: space-between;align-items: center">
                        <button @click="selectLineTime(0)" class="btn btn-default ripple btn-lg">7天</button>

                        <button @click="selectLineTime(1)" class="btn btn-default ripple btn-lg">30天</button>
                    </div>
                </div>
            </div>
            <div style="width: 98%;height: 350rem" id="salesLine">

            </div>
        </div>
    </div>
</template>

<script>

  import getSalesLine from "../../assets/echarts/getSalesLine";
  import {reactive} from "@vue/reactivity";
  import selectShopOrders from "../../Internet/home/shoppingOrder/select/selectShopOrders";
  import getShopComments from "../../Internet/home/comment/select/getShopComments";
  import searchShopSalesReport from "../../Internet/home/shopSalesReport/select/searchShopSalesReport";

  Date.prototype.Format = function (fmt) {
    let o = {
      "M+": this.getMonth() + 1, //月份
      "d+": this.getDate(), //日
      "h+": this.getHours(), //小时
      "m+": this.getMinutes(), //分
      "s+": this.getSeconds(), //秒
      "q+": Math.floor((this.getMonth() + 3) / 3), //季度
      "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  };

  export default {
    name: "storeHomePage",
    data(){
      return{
        XTime:{},
        YCount:{},
        YAccount:{}
      }
    },
    setup(){
      let shop=reactive(JSON.parse(sessionStorage.getItem('shop')))
      let shopOrders=reactive({
        nonPaymentList:{},
        undeliveredList:{},
        finishedList:{},
        paidList:{},
        nonCommentList:{},
        refundList:{},
        exchangeList:{}
      })
      selectShopOrders(shop.shopId).then(res=>{
        shopOrders.nonPaymentList=res.data.nonPaymentList
        shopOrders.undeliveredList=res.data.undeliveredList
        shopOrders.paidList=res.data.paidList
        shopOrders.nonCommentList=res.data.nonCommentList
        shopOrders.refundList=res.data.refundList
        shopOrders.exchangeList=res.data.exchangeList
        shopOrders.finishedList=res.data.finishedList
      })

     function selectLineTime(index){
       let XTime=[]
       let YAccount=[]
       let YCount=[]
       searchShopSalesReport(index,shop.shopId).then(res=>{
         for(let i=0;i<res.data.length;i++){
           XTime[i]=res.data[i].time
           YAccount[i]=res.data[i].salesAccount
           YCount[i]=res.data[i].salesCount
         }
         getSalesLine(XTime,YAccount,YCount)
       })
     }


      let shopComments=reactive({
        nonAnswer:[],
        nonHandle:[],
        badComments:[],
        niceComments:[],
        centerComments:[]
      })

      getShopComments(shop.shopId).then(res=>{
        console.log(res.data);
        shopComments.badComments=res.data.badComments
        shopComments.centerComments=res.data.centerComments
        shopComments.niceComments=res.data.niceComments
        shopComments.nonAnswer=res.data.nonAnswer
        shopComments.nonHandle=res.data.nonHandle
      })


      let time=new Date().Format("yyyy-MM-dd hh:mm:ss")

      return{
        shop,
        shopOrders,
        shopComments,
        selectLineTime,
        time
      }
    },
    mounted() {
      let shop=JSON.parse(sessionStorage.getItem('shop'))
      let XTime=[]
      let YAccount=[]
      let YCount=[]
      searchShopSalesReport(0,shop.shopId).then(res=>{
        for(let i=0;i<res.data.length;i++){
          XTime[i]=res.data[i].time
          YAccount[i]=res.data[i].salesAccount
          YCount[i]=res.data[i].salesCount
        }
        getSalesLine(XTime,YAccount,YCount)
      })
    }
  }
</script>

<style scoped>
    .ripple {
        position: relative;
        width: 80rem;
        height: 30rem;
        background-color: white;
        color: black;
        border:1rem black solid;
        border-radius: 5rem;

    /*此处不需要设置overflow:hidden,因为after元素需要溢出显示*/
    }
    .ripple:focus{
        outline: none;
        color: steelblue;
        border: 1rem steelblue solid;
    }

    .ripple:after {
        content: "";
        display: block;
        position: absolute;
        /*扩大伪类元素4个方向各10px*/
        top: -10px;
        left: -10px;
        right: -10px;
        bottom: -10px;
        pointer-events: none;
        background-color: steelblue;
        color: steelblue;
        border: 1rem steelblue solid;
        background-repeat: no-repeat;
        background-position: 50%;
        opacity: 0;
        transition: all .3s;
    }

    .ripple:active:after {
        opacity: .3;
        /*设置初始状态*/
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        transition: 0s;
    }

</style>