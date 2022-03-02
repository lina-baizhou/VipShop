<template>
    <div style="width: 850rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;font-size: 12rem">
        <div style="width: 100%;height:auto;display:flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
            <div style="border-left: 2rem rgb(241,1,128) solid;width: auto;padding-left:20rem;height:20rem;align-items:center;display: flex;justify-content: space-evenly;font-weight: 700;font-size: 14rem;">
                <span>订单详情</span>
            </div>

            <div style="width: 100%;height:270rem;border: 1px solid #ececec;background-color: #fff;box-shadow: 0 1px 4px rgb(0 0 0 / 10%);margin-top: 10rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
                <div style="width: 100%;height:41rem;background-color: #f9f9f9;font: 14rem/24rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;padding-left: 20rem; line-height: 40rem;border-bottom: 1px solid #e0e0e0;display: flex;justify-content: flex-start;align-items: center">
                    订单概括
                </div>
                <div style="width: 100%;height: 259rem;padding:20rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
                    <div style="width: 100%;height: auto;border-bottom: 1rem dotted #dcdcdc;padding-bottom: 15px;display: flex;justify-content: space-between;align-items: center">
                        <span>状态：<span style="font-weight: 700;color: darkgrey">{{orderGroup.shopOrder.orderState}}</span></span>
                        <span>下单时间：{{orderGroup.shopOrder.time}}</span>
                    </div>

                    <div style="width: 100%;height: auto;line-height: 35rem;border-bottom: 1rem dotted #dcdcdc;padding-bottom: 15px;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
                        <span style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: center">
                            订单金额：<span style="font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #f10180;font-weight: bold">¥{{orderGroup.shopOrder.goodsSalesVolume}}</span>
                            <span v-if="orderGroup.shopOrder.goodsSalesVolume<orderGroup.shop.freeDelivery">（含运费¥{{orderGroup.shop.freight}}）</span>
                            <span v-else>（含运费¥{{0}}）</span>
                        </span>
                        <span style="width: 100%;height: auto;display: flex;justify-content: flex-start">
                            收货信息：{{orderGroup.harvest.harvestName}}，{{orderGroup.harvest.harvestEmail}}，{{orderGroup.harvest.harvestAddress}}
                        </span>
                    </div>
                    <div style="width: 100%;height: auto;line-height: 35rem;padding-bottom: 15px;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
                        <span style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: center">
                            发货商家：<span style="color: blueviolet">{{orderGroup.shop.shopName}}</span>
                        </span>
                        <span style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: center">
                            联系客服：<img :src="customerService" style="height: 20rem">&nbsp;
                            在线客服<span style="color: grey">（09:00-23:00）</span>
                        </span>
                    </div>
                </div>
            </div>

            <div style="width: 100%;height:auto;border: 1px solid #ececec;background-color: #fff;box-shadow: 0 1px 4px rgb(0 0 0 / 10%);margin-top: 10rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
                <div style="width: 100%;height:41rem;background-color: #f9f9f9;font: 14rem/24rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;padding-left: 20rem; line-height: 40rem;border-bottom: 1px solid #e0e0e0;display: flex;justify-content: flex-start;align-items: center">
                    商品清单
                </div>
                <div style="width: 100%;height: 38rem;display: flex;justify-content: flex-start;align-items: center;font-size: 12rem;margin-top: 20rem;padding-left: 20rem;padding-right: 20rem;flex-wrap: wrap">
                    <div style="width: 100%;height: 38rem;display: flex;justify-content: space-between;align-items: center;font-size: 12rem;border-bottom: 2rem lightgrey solid;">
                        <div style="width: 406rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;color: #666">
                            商品
                        </div>
                        <div style="width: 127rem;height: 100%;display: flex;justify-content: center;align-items: center;color: #666">
                            单价
                        </div>
                        <div style="width: 97rem;height: 100%;display: flex;justify-content: center;align-items: center;color: #666">
                            数量
                        </div>
                        <div style="width: 164rem;height: 100%;display: flex;justify-content: center;align-items: center;color: #666">
                            小计
                        </div>
                    </div>
                </div>
                <div style="width: 100%;height: auto;display: flex;flex-direction: column;justify-content: space-between;align-items: center;font-size: 12rem;padding-right: 20rem;padding-left: 20rem">
                    <div v-for="(item,index) in orderGroup.goodsList" :key="index" style="width: 100%;height: 118.5rem;display: flex;justify-content: space-between;align-items: center;font-size: 12rem;border-bottom: 1px dashed #dcdcdc;">
                        <div style="width: 406rem;height: 78rem;display: flex;align-items: center;justify-content: flex-start">
                            <img :src="item.goodsImage1" style="width: 50rem;height: 63rem">
                            <div style="width:197rem;margin-left: 20rem;height: 63rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}" @click="selectGoods(item.goodsId)" style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{item.goodsTitle}}</router-link>
                                <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem">{{orderGroup.specificationsList[index].goodsType}}</span>
                            </div>
                            <div v-if="orderGroup.shopOrder.returnList!=null" style="width: 100rem;margin-left: 100rem;height: 63rem;display: flex;flex-direction: column;justify-content: center;align-items: center">
                                <router-link v-if="orderGroup.shopOrder.orderState!=='待付款'&&orderGroup.shopOrder.orderState!=='待发货'&&orderGroup.shopOrder.orderState!=='已取消'&&orderGroup.returnList[index]==-1&&orderGroup.changeList[index]==-1" :to="{path:'/mainHeader/UserAccountCenter/selectAfterSaleType',query:{orderNumber:orderGroup.shopOrder.orderNumber,goodsIndex:index}}" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                    申请售后
                                </router-link>
                                <span v-if="orderGroup.shopOrder.orderState!=='待付款'&&orderGroup.shopOrder.orderState!=='待发货'&&orderGroup.shopOrder.orderState!=='已取消'&&orderGroup.returnList[index]==3&&orderGroup.changeList[index]==-1" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                     部分退款成功<br>
                                </span>
                                <span v-if="orderGroup.shopOrder.orderState!=='待付款'&&orderGroup.shopOrder.orderState!=='待发货'&&orderGroup.shopOrder.orderState!=='已取消'&&orderGroup.returnList[index]==2">
                                     退款成功
                                </span>
                                <span v-if="orderGroup.shopOrder.orderState!=='待付款'&&orderGroup.shopOrder.orderState!=='待发货'&&orderGroup.shopOrder.orderState!=='已取消'&&orderGroup.changeList[index]==2">
                                     换货成功
                                </span>
                                <span v-if="orderGroup.shopOrder.orderState!=='待付款'&&orderGroup.shopOrder.orderState!=='待发货'&&orderGroup.shopOrder.orderState!=='已取消'&&orderGroup.changeList[index]==0">
                                     换货中
                                </span>
                            </div>
                        </div>

                        <span style="width: 127rem;height:auto;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: grey;font-weight: bold">¥{{orderGroup.specificationsList[index].goodsPrice}}</span>

                        <span style="width: 97rem;height:auto;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: grey;">{{orderGroup.countList[index]}}</span>

                        <span style="width: 164rem;height:auto;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #f10180;font-weight: bold">¥{{orderGroup.specificationsList[index].goodsPrice*orderGroup.countList[index]}}</span>
                    </div>
                    <div style="width: 100%;height: 110rem;display: flex;flex-direction: column;justify-content: flex-start;align-items:center;margin-top: 20rem;border-radius: 2rem;padding-top: 20rem">
                        <div style="width: 95%;height: 80rem;display:flex;flex-direction: column;justify-content: space-between;align-items: flex-end">
                            <span style="width: 250rem;height: auto;display: flex;justify-content: space-between;color: rgb(102, 102, 102);font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                <span style="width: 150rem;height: auto;display: flex;justify-content: flex-end">
                                    商品金额
                                </span>
                                <span style="width: 100rem;height: auto;display: flex;justify-content: flex-end;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #f10180;font-weight: bold">
                                    <span >¥</span>{{orderGroup.shopOrder.goodsSalesVolume}}
                                </span>
                            </span>
                            <span style="width: 250rem;height: auto;display: flex;justify-content: space-between;color: rgb(102, 102, 102);font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                <span style="width: 150rem;height: auto;display: flex;justify-content: flex-end">
                                    运费
                                </span>
                                <span style="width: 100rem;height: auto;display: flex;justify-content: flex-end;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #f10180;font-weight: bold">
                                    <span v-if="orderGroup.shopOrder.goodsSalesVolume<orderGroup.shop.freeDelivery&&orderGroup.shopOrder.tradeNo!==null">¥{{orderGroup.shop.freight}}</span>
                                    <span v-else>免运费</span>
                                </span>
                            </span>
                            <span style="width: 250rem;height: auto;display: flex;justify-content: space-between;font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                <span style="width: 150rem;height: auto;display: flex;justify-content: flex-end;color:black;font-weight: 700">
                                    订单金额
                                </span>
                                <span style="width: 100rem;height: auto;display: flex;justify-content: flex-end;font: 14rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #f10180;font-weight: bold">
                                    <span v-if="orderGroup.shopOrder.goodsSalesVolume<orderGroup.shop.freeDelivery&&orderGroup.shopOrder.tradeNo!=null">¥{{orderGroup.shop.freight+orderGroup.shopOrder.goodsSalesVolume}}</span>
                                    <span v-else>{{orderGroup.shopOrder.goodsSalesVolume}}</span>
                                </span>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import {reactive} from "@vue/reactivity";
  import selectShoppingOrderByOrderNumber from "../../Internet/home/shoppingCart/select/selectShoppingOrderByOrderNumber";
  import store from "../../store/index"
  import selectByGoodsId from "../../Internet/home/goods/select/selectByGoodsId";
  export default {
    name: "OrderDetailPage",
    setup() {
      let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg = new RegExp("(^|&)" + 'orderNumber' + "=([^&]*)(&|$)");
      let r = search.match(reg);

      let orderGroup=reactive({
          countList:[],
          goodsList:[],
          harvest:{},
          shopOrder:{},
          shop:{},
          specificationsList:[],
          returnList:[],
          changeList:[]
      })

      selectShoppingOrderByOrderNumber(unescape(r[2])).then(res=>{
        orderGroup.countList=res.data.beforeCountList
        orderGroup.goodsList=res.data.goodsList
        orderGroup.shopOrder=res.data.order
        orderGroup.shop=res.data.shop
        orderGroup.specificationsList=res.data.specificationsList
        orderGroup.returnList=res.data.returnList
        orderGroup.changeList=res.data.changeList
        orderGroup.harvest=res.data.harvest
        for(let i=0;i<orderGroup.goodsList.length;i++){
          orderGroup.goodsList[i].goodsImage1=require("../../assets/img/"+orderGroup.goodsList[i].goodsImage1)
        }

        for(let i=0;i<orderGroup.specificationsList.length;i++){
          if(orderGroup.specificationsList[i].goodsType2==null){
            orderGroup.specificationsList[i].goodsType=orderGroup.specificationsList[i].goodsType1
          }else {
            orderGroup.specificationsList[i].goodsType=orderGroup.specificationsList[i].goodsType1+orderGroup.specificationsList[i].goodsType2
          }

        }
        console.log(orderGroup);
      })

      function selectGoods(goodsId) {
        selectByGoodsId(goodsId).then(res => {
          store.state.goods.data = res.data;
        })
      }


      let customerService=require("../../assets/img/客服.svg")

      return{
        orderGroup,
        customerService,
        selectGoods,
        store
      }
    }
  }
</script>

<style scoped>

</style>