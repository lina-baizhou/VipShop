<template>
    <div style="width: 850rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;font-size: 12rem">
        <div style="width: 100%;height:auto;display:flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
            <div style="border-left: 2rem rgb(241,1,128) solid;width: 170rem;height:20rem;align-items:center;display: flex;justify-content: space-evenly;align-items: center;font-size: 12rem;">
                <span ><router-link style="color: black;cursor: pointer" to="/mainHeader/UserAccountCenter/userInf">我的唯品会</router-link></span>
                <span>></span>
                <span>订单管理</span>
            </div>
            <div style="width: 100%;height: 80rem;border: 1px solid #ececec;background: white;margin-top: 20rem;padding: 17rem 20rem 20rem;display: flex;justify-content: flex-start;align-items: flex-start">
                <img :src="informLogo" style="width: 21rem">
                <span style="margin-left: 20rem;text-align: left;line-height: 20rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                    安全提醒：唯品会不会以任何理由要求您提供银行卡信息或支付额外费用，请谨防钓鱼链接或诈骗电话。<br>
                    *为保障您的信息安全，订单信息中的联系电话已被加密隐藏。
                </span>
            </div>

            <div style="width: 100%;height: 36rem;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem;border-bottom: 1rem solid #d0d0d0;">
                <div @click="changeBorder(0)" @mouseleave="leave" @mouseenter="changeFontColor(0)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;border-bottom: 2rem rgb(241,1,128) solid">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        全部订单
                    </div>
                </div>

                <div  @click="changeBorder(1)" @mouseleave="leave"  @mouseenter="changeFontColor(1)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        待付款
                    </div>
                </div>

                <div  @click="changeBorder(2)" @mouseleave="leave"  @mouseenter="changeFontColor(2)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        待发货
                    </div>
                </div>

                <div  @click="changeBorder(3)" @mouseleave="leave" @mouseenter="changeFontColor(3)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        待收货
                    </div>
                </div>

                <div  @click="changeBorder(4)" @mouseleave="leave" @mouseenter="changeFontColor(4)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        待评价
                    </div>
                </div>

                <div  @click="changeBorder(5)" @mouseleave="leave" @mouseenter="changeFontColor(5)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        已完成
                    </div>
                </div>

                <div  @click="changeBorder(6)" @mouseleave="leave" @mouseenter="changeFontColor(6)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        已取消
                    </div>
                </div>

                <div  @click="changeBorder(7)" @mouseleave="leave" @mouseenter="changeFontColor(7)" class="orderTypeDiv" style="width: 100rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;">
                    <div class="orderType" style="width: 100rem;height:auto;border-left: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14rem;color: rgb(102,102,102);cursor: pointer">
                        退款中
                    </div>
                </div>
            </div>

            <div v-if="orders.list.length==0" style="width: 100%;height: 151rem;display: flex;justify-content: center;align-items: center">
                <img :src="warnLogo" style="height: 35rem">
                <span style="font-size: 12rem;margin-left: 20rem">无符合该条件的订单</span>
            </div>

            <div  v-if="orders.list.length!=0" style="width: 100%;height: 38rem;display: flex;justify-content: flex-start;align-items: center;background-color: #f9f9f9;border: 1px solid #eaeaea;font-size: 12rem;margin-top: 20rem">
                <div style="width: 416rem;height: 100%;display: flex;justify-content: center;align-items: center;">
                    商品
                </div>
                <div style="width: 137rem;height: 100%;display: flex;justify-content: center;align-items: center;">
                    订单总额
                </div>
                <div style="width: 107rem;height: 100%;display: flex;justify-content: center;align-items: center;">
                    订单状态
                </div>
                <div style="width: 174rem;height: 100%;display: flex;justify-content: center;align-items: center;">
                    操作
                </div>
            </div>

            <div v-for="(item,index) in orders.list" :key="index" style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start;margin-top: 15rem">
                <div style="width: 100%;height: 41rem;border-left: 2px solid #f10180;background-color: #f9f9f9;color: #333;display: flex;align-items: center;justify-content: space-between;padding-left: 20rem;padding-right: 20rem">
                    <span>{{item.shop.shopName}}</span>
                    <span>{{item.shopOrder.time}}</span>
                </div>
                <div style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;background-color: #fff;">

                    <div style="width: 416rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;border-right: 1px solid #eaeaea;border-left: 1px solid #eaeaea">
                        <div v-for="(item1,index1) in item.goodsList" :key="index1" style="width: 416rem;height: 104rem;padding: 20rem;border-bottom: 1px solid #eaeaea;display: flex;justify-content: space-between;align-items: center">
                            <img :src="item1.goodsImage1" style="height: 63rem;width: 50rem">
                            <div  v-if="item1.shelfState==1" style="width: 50rem;height: 63rem;background-color: rgba(255,255,255,0.3);position: absolute;z-index: 2;display: flex;justify-content: center;align-items: center;font-size: 15rem;font-weight: bold;color: #a0a0a0">
                                已下架
                            </div>
                            <div  style="width:197rem;height: 63rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item1.goodsId}}" @click="selectGoods(item1.goodsId)" style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{item1.goodsTitle}}</router-link>
                                <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem">{{item.goodsSpecificationsList[index1].goodsType}}</span>
                            </div>
                            <div  style="width: 80rem;height: 63rem;display: flex;flex-direction: column;align-items: center;justify-content: space-evenly">
                               <div v-if="item.shopOrder.returnFlag!==null&&item.shopOrder.changeFlag!==null" style="width: auto;height: auto;display: flex;flex-direction: column;align-items: center;justify-content: space-evenly">
                                   <router-link v-if="item.shopOrder.orderState!=='待付款'&&item.shopOrder.orderState!=='待发货'&&item.shopOrder.orderState!=='已取消'&&item.shopOrder.returnFlag[index1]==-1&&item.shopOrder.changeFlag[index1]==-1" :to="{path:'/mainHeader/UserAccountCenter/selectAfterSaleType',query:{orderNumber:item.shopOrder.orderNumber,goodsIndex:index1}}" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                       申请售后
                                   </router-link>
                                   <span v-if="item.shopOrder.orderState!=='待付款'&&item.shopOrder.orderState!=='待发货'&&item.shopOrder.orderState!=='已取消'&&item.shopOrder.returnFlag[index1]==3&&item.shopOrder.changeFlag[index1]==-1" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                       部分退款成功<br>
                                       <router-link  :to="{path:'/mainHeader/UserAccountCenter/selectAfterSaleType',query:{orderNumber:item.shopOrder.orderNumber,goodsIndex:index1}}" >
                                        申请售后
                                       </router-link>
                                   </span>
                                   <span v-else-if="item.shopOrder.orderState!=='待付款'&&item.shopOrder.orderState!=='待发货'&&item.shopOrder.orderState!=='已取消'&&item.shopOrder.returnFlag[index1]==2">
                                       退款成功
                                   </span>
                                   <span v-if="item.shopOrder.orderState!=='待付款'&&item.shopOrder.orderState!=='待发货'&&item.shopOrder.orderState!=='已取消'&&item.shopOrder.changeFlag[index1]==3" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                       换货成功<br>
                                       <router-link  :to="{path:'/mainHeader/UserAccountCenter/selectAfterSaleType',query:{orderNumber:item.shopOrder.orderNumber,goodsIndex:index1}}" >
                                        申请售后
                                       </router-link>
                                   </span>
                                   <span @click="cancelReturnGoods(item.shopOrder,index1)" v-if="item.shopOrder.orderState!=='待付款'&&item.shopOrder.orderState!=='待发货'&&item.shopOrder.orderState!=='已取消'&&item.shopOrder.returnFlag[index1]==0" style="color: #1d94d1;cursor: pointer">
                                       取消退款
                                   </span>
                                   <span v-if="item.shopOrder.orderState!=='待付款'&&item.shopOrder.orderState!=='待发货'&&item.shopOrder.orderState!=='已取消'&&item.shopOrder.changeFlag[index1]==2">
                                       换货成功
                                   </span>
                                   <span @click="cancelExchangeGoods(item.shopOrder,index1)" v-if="item.shopOrder.orderState!=='待付款'&&item.shopOrder.orderState!=='待发货'&&item.shopOrder.orderState!=='已取消'&&item.shopOrder.changeFlag[index1]==0"  style="color: #1d94d1;cursor: pointer">
                                       取消换货
                                   </span>
                               </div>
                                <span>×{{item.goodsCountList[index1]}}</span>
                            </div>
                        </div>
                    </div>

                    <div  style="width: 137rem;height: 100%;border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea;display: flex;justify-content: center;align-items: center">
                        <div style="width: auto;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;">
                            <strong style="font-size: 14rem">{{item.shopOrder.goodsSalesVolume}}</strong>
                            <span style="margin-top: 10rem;color: #999;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                在线
                            </span>
                        </div>
                    </div>

                    <div  style="width: 137rem;height: 100%;border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea;display: flex;justify-content: center;align-items: center">
                        <div style="width: auto;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;">
                            <span style="color: #999;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">{{item.shopOrder.orderState}}</span>
                            <router-link :to="{path:'/mainHeader/UserAccountCenter/orderDetail',query:{orderNumber:item.shopOrder.orderNumber}}" style="margin-top: 10rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                订单详情
                            </router-link>
                        </div>

                    </div>

                    <div  style="width: 98rem;height: 100%;border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea;display: flex;justify-content: center;align-items: center">
                        <div style="width: auto;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;">
                            <span @click="pay(item.shopOrder)" v-if="item.shopOrder.orderState=='待付款'" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem;cursor: pointer;width: 50rem;height: 22rem;background:  #f10180;border-radius: 5rem;color: white;font-weight: bold;display: flex;justify-content: center;align-items: center">
                                支付
                            </span>
                            <span @click="takeDelivery(item.shopOrder)" v-if="item.shopOrder.orderState=='待收货'" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem;cursor: pointer;color: rgb(24,144,255);cursor: pointer">
                                  确认收货
                            </span>
                            <router-link :to="{path:'/mainHeader/UserAccountCenter/modifyOrder',query:{orderNumber:item.shopOrder.orderNumber,harvestId:item.shopOrder.harvestId,userId:item.shopOrder.userId}}" v-if="item.shopOrder.orderState=='待付款'||item.shopOrder.orderState=='待发货'" style="margin-top: 5rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem;cursor: pointer">
                                修改订单
                            </router-link>
                            <router-link  :to="{path:'/mainHeader/releaseComment',query:{orderNumber:item.shopOrder.orderNumber}}" v-if="item.shopOrder.orderState=='待评价'||item.shopOrder.orderState=='已完成'" style="margin-top: 10rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                去评价
                            </router-link>
                            <router-link :to="{path:'/mainHeader/UserAccountCenter/selectAfterSaleType',query:{orderNumber:item.shopOrder.orderNumber}}" v-if="item.shopOrder.orderState=='待评价'||item.shopOrder.orderState=='已完成'" style="margin-top: 10rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                                放弃评价
                            </router-link>

                        </div>
                    </div>
                    <div  style="width: 98rem;height: 100%;border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea;display: flex;justify-content: center;align-items: center">
                        <div style="width: auto;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;">
                             <span @click="cancelOrder(item.shopOrder)" v-if="item.shopOrder.orderState=='待付款'||item.shopOrder.orderState=='待发货'" style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem;cursor: pointer">
                                取消订单
                            </span>
                            <span v-else @click="deleteOrder(item.shopOrder)"  style="font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem;cursor: pointer">
                                删除订单
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <div ref="dialogForAddress" class="dialogForAddress" style="position: fixed;z-index: 5;display: flex;top:50rem;margin-left: 113rem;height:471rem;width: 634rem;background-color:rgba(200,200,200,0.7);padding: 5rem;visibility: hidden">
                <div style="width: 100%;height: 100%;background: white;display: flex;flex-direction: column;justify-content: flex-start;align-items:center;padding: 5rem">
                    <div style="width: 100%;height: auto;display: flex;justify-content: flex-end;align-items: center;">
                        <close-outlined @click="closeDialog" style="cursor: pointer;color: grey;font-size: 20rem"></close-outlined>
                    </div>
                    <div style="width: 440rem;height: auto;display: flex;flex-wrap: wrap;align-items: flex-start;justify-content: flex-start">
                        <div style="width: 100%;height: auto;display: flex;justify-content:space-between;align-items: center">
                            <img :src="cancelLogo" style="height: 53rem;">
                            <span style="font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:14px;font-stretch:100%;color: #333;font-weight: 700">主人~这么辛苦抢来的商品，取消订单后就会被人抢走了啦~</span>
                        </div>

                        <div style="width: 100%;height: 250rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start;margin-top: 20rem">
                            <span style="font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size:12rem;font-stretch:100%;color: #333;font-weight: 700">能告诉我们取消订单的原因吗？</span>
                            <span style="display: flex;align-items: center;margin-top: 13rem"><input type="radio" value="订单不能按时间送达" v-model="reasonForCancel.data">&nbsp;订单不能按预计时间送达</span>
                            <span style="display: flex;align-items: center"><input type="radio" value="收货地址/商品/发票等信息错误" v-model="reasonForCancel.data">&nbsp;收货地址/商品/发票等信息错误</span>
                            <span style="display: flex;align-items: center"><input type="radio" value="重复下单/误下单" v-model="reasonForCancel.data">&nbsp;重复下单/误下单</span>
                            <span style="display: flex;align-items: center"><input type="radio" value="有更优惠的购买方式" v-model="reasonForCancel.data">&nbsp;有更优惠的购买方式</span>
                            <span style="display: flex;align-items: center"><input type="radio" value="支付遇到问题" v-model="reasonForCancel.data">&nbsp;支付遇到问题</span>
                            <span style="display: flex;align-items: center"><input type="radio" value="不想买了" v-model="reasonForCancel.data">&nbsp;不想买了</span>
                            <span style="display: flex;align-items: center"><input type="radio" value="其他原因" v-model="reasonForCancel.data">&nbsp;其他原因</span>
                        </div>

                        <div style="height: auto;width: 100%;display: flex;justify-content: center;align-items: center;margin-top: 20rem">
                            <button style="width: 100rem;height: 32rem;background-color: white;border:1rem lightgrey solid;cursor: pointer" @click="cancel">取消订单</button>
                            <button style="width: 100rem;height: 32rem;margin-left: 20rem;background-color:rgb(252, 237, 242);color:rgb(224, 0, 120);border: 1rem solid rgb(224, 0, 120);" @click="closeDialog">关闭窗口</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
    import $ from 'jquery'
    import selectAllOrdersByUserId from "../../../Internet/home/shoppingOrder/select/selectAllOrdersByUserId";
    import {reactive, ref} from "@vue/reactivity";
    import store from "../../../store"
    import selectByGoodsId from "../../../Internet/home/goods/select/selectByGoodsId";
    import selectAllOrdersByUserIdAndState from "../../../Internet/home/shoppingOrder/select/selectAllOrdersByUserIdAndState";
    import cancelShopOrder from "../../../Internet/home/shoppingOrder/update/cancelOrder";
    import deleteShopOrder from "../../../Internet/home/shoppingOrder/remove/deleteShopOrder";
    import payOrder from "../../../Internet/home/shoppingOrder/update/payOrder";
    import {CloseOutlined} from '@ant-design/icons-vue'
    import changeOrderToComment from "../../../Internet/home/shoppingOrder/update/modifyOrderToComment";
    import modifyOrderGoodsToReturn from "../../../Internet/home/shoppingOrder/update/modifyOrderGoodsToReturn";
    import removeReturnOrder from "../../../Internet/home/returnOrder/remove/removeReturnOrder";
    import removeExchangeOrder from "../../../Internet/home/exchangeOrder/update/cancelExchangeOrder";
    import takeExchangeOrderDelivery from "../../../Internet/home/shoppingOrder/update/takeExchangeOrderDelivery";
    export default {
    name: "UserOrder",
    components:{
      CloseOutlined
    },
    setup(){
      let  warnLogo=require("../../../assets/img/警告.svg")

      let informLogo=require("../../../assets/img/通知.svg")

      let cancelLogo=require("../../../assets/img/2021-09-02_221808.png")

      let reasonForCancel=reactive({
        data:''
      })
      let orders=reactive({
        list:[]
      })

      selectAllOrdersByUserId(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
        orders.list=res.data

        for(let i=0;i<orders.list.length;i++){
          if(orders.list[i].shopOrder.returnFlag!==null){
            orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
          }else {
            console.log(orders.list[i].shopOrder.returnFlag!=null&&orders.list[i].shopOrder.changeFlag!==null)
            // console.log(1111)
            // for(let j=0;j<orders.list[i].goodsList.length;j++){
            //   orders.list[i].goodsList[j].goodsImage1=require('../../../assets/img/'+orders.list[i].goodsList[j].goodsImage1)
            //
            //   console.log(2222)
            //   if(orders.list[i].goodsSpecificationsList[j].goodsType2==null){
            //     orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1
            //     console.log(4444)
            //   }else {
            //     orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1+orders.list[i].goodsSpecificationsList[j].goodsType2
            //     console.log(5555)
            //   }
            // }
            console.log(orders.list[i]);
          }
          if(orders.list[i].shopOrder.changeFlag!==null){
            orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
          }


          for(let j=0;j<orders.list[i].goodsList.length;j++){
            orders.list[i].goodsList[j].goodsImage1=require('../../../assets/img/'+orders.list[i].goodsList[j].goodsImage1)
            if(orders.list[i].goodsSpecificationsList[j].goodsType2==null){
              orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1
            }else {
              orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1+orders.list[i].goodsSpecificationsList[j].goodsType2
            }
          }

          if(orders.list[i].shopOrder.returnFlag==null){
            console.log(orders.list[i]);
          }
        }
      })

      function changeFontColor(index) {
        $(".orderType").eq(index).css('color','#f10180')
      }

      function leave() {
        for(let i=0;i<9;i++){
          $(".orderType").eq(i).css('color','rgb(102,102,102)')
        }
      }

      let index1=0
      function changeBorder(index) {
        index1=index
        for(let i=0;i<9;i++){
          $(".orderTypeDiv").eq(i).css('borderBottom','none');
        }
        $(".orderTypeDiv").eq(index).css('borderBottom','2rem rgb(241,1,128) solid');

        orders.list=[]

        if(index==1){
          selectAllOrdersByUserIdAndState(JSON.parse(sessionStorage.getItem('user')).userId,"待付款").then(res=>{
            orders.list=res.data

            for(let i=0;i<orders.list.length;i++){
              if(orders.list[i].shopOrder.returnFlag!==null){
                orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
              }
              if(orders.list[i].shopOrder.changeFlag!==null){
                orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
              }
              for(let j=0;j<orders.list[i].goodsList.length;j++){
                orders.list[i].goodsList[j].goodsImage1=require('../../../assets/img/'+orders.list[i].goodsList[j].goodsImage1)

                if(orders.list[i].goodsSpecificationsList[j].goodsType2==null){
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1
                }else {
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1+orders.list[i].goodsSpecificationsList[j].goodsType2
                }
              }
            }
          })
        }else if(index==2){
          selectAllOrdersByUserIdAndState(JSON.parse(sessionStorage.getItem('user')).userId,"待发货").then(res=>{
            orders.list=res.data

            for(let i=0;i<orders.list.length;i++){
              if(orders.list[i].shopOrder.returnFlag!==null){
                orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
              }
              if(orders.list[i].shopOrder.changeFlag!==null){
                orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
              }

              for(let j=0;j<orders.list[i].goodsList.length;j++){
                orders.list[i].goodsList[j].goodsImage1=require('../../../assets/img/'+orders.list[i].goodsList[j].goodsImage1)

                if(orders.list[i].goodsSpecificationsList[j].goodsType2==null){
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1
                }else {
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1+orders.list[i].goodsSpecificationsList[j].goodsType2
                }
              }
            }
          })
        }else if(index==3){
          selectAllOrdersByUserIdAndState(JSON.parse(sessionStorage.getItem('user')).userId,"待收货").then(res=>{
            orders.list=res.data

            for(let i=0;i<orders.list.length;i++){
              if(orders.list[i].shopOrder.returnFlag!==null){
                orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
              }
              if(orders.list[i].shopOrder.changeFlag!==null){
                orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
              }
              for(let j=0;j<orders.list[i].goodsList.length;j++){
                orders.list[i].goodsList[j].goodsImage1=require('../../../assets/img/'+orders.list[i].goodsList[j].goodsImage1)

                if(orders.list[i].goodsSpecificationsList[j].goodsType2==null){
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1
                }else {
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1+orders.list[i].goodsSpecificationsList[j].goodsType2
                }
              }
            }
          })
        }else if(index==4){
          selectAllOrdersByUserIdAndState(JSON.parse(sessionStorage.getItem('user')).userId,"待评价").then(res=>{
            orders.list=res.data

            for(let i=0;i<orders.list.length;i++){
              if(orders.list[i].shopOrder.returnFlag!==null){
                orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
              }
              if(orders.list[i].shopOrder.changeFlag!==null){
                orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
              }
              for(let j=0;j<orders.list[i].goodsList.length;j++){
                orders.list[i].goodsList[j].goodsImage1=require('../../../assets/img/'+orders.list[i].goodsList[j].goodsImage1)

                if(orders.list[i].goodsSpecificationsList[j].goodsType2==null){
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1
                }else {
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1+orders.list[i].goodsSpecificationsList[j].goodsType2
                }
              }
            }
          })
        }else if(index==5){
          selectAllOrdersByUserIdAndState(JSON.parse(sessionStorage.getItem('user')).userId,"已完成").then(res=>{
            orders.list=res.data

            for(let i=0;i<orders.list.length;i++){
              if(orders.list[i].shopOrder.returnFlag!==null){
                orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
              }
              if(orders.list[i].shopOrder.changeFlag!==null){
                orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
              }
              for(let j=0;j<orders.list[i].goodsList.length;j++){
                orders.list[i].goodsList[j].goodsImage1=require('../../../assets/img/'+orders.list[i].goodsList[j].goodsImage1)

                if(orders.list[i].goodsSpecificationsList[j].goodsType2==null){
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1
                }else {
                  orders.list[i].goodsSpecificationsList[j].goodsType=orders.list[i].goodsSpecificationsList[j].goodsType1+orders.list[i].goodsSpecificationsList[j].goodsType2
                }
              }
            }
          })
        }else if(index==6){
          selectAllOrdersByUserIdAndState(JSON.parse(sessionStorage.getItem('user')).userId,"已取消").then(res=>{
            orders.list=res.data

            for(let i=0;i<orders.list.length;i++) {
              if(orders.list[i].shopOrder.returnFlag!==null){
                orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
              }
              if(orders.list[i].shopOrder.changeFlag!==null){
                orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
              }
              for (let j = 0; j < orders.list[i].goodsList.length; j++) {
                orders.list[i].goodsList[j].goodsImage1 = require('../../../assets/img/' + orders.list[i].goodsList[j].goodsImage1)

                if (orders.list[i].goodsSpecificationsList[j].goodsType2 == null) {
                  orders.list[i].goodsSpecificationsList[j].goodsType = orders.list[i].goodsSpecificationsList[j].goodsType1
                } else {
                  orders.list[i].goodsSpecificationsList[j].goodsType = orders.list[i].goodsSpecificationsList[j].goodsType1 + orders.list[i].goodsSpecificationsList[j].goodsType2
                }
              }
            }
            })
        }else if(index==7) {
          selectAllOrdersByUserIdAndState(JSON.parse(sessionStorage.getItem('user')).userId,"退款中").then(res=>{
            orders.list=res.data

            for(let i=0;i<orders.list.length;i++) {
              if(orders.list[i].shopOrder.returnFlag!==null){
                orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
              }
              if(orders.list[i].shopOrder.changeFlag!==null){
                orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
              }
              for (let j = 0; j < orders.list[i].goodsList.length; j++) {
                orders.list[i].goodsList[j].goodsImage1 = require('../../../assets/img/' + orders.list[i].goodsList[j].goodsImage1)

                if (orders.list[i].goodsSpecificationsList[j].goodsType2 == null) {
                  orders.list[i].goodsSpecificationsList[j].goodsType = orders.list[i].goodsSpecificationsList[j].goodsType1
                } else {
                  orders.list[i].goodsSpecificationsList[j].goodsType = orders.list[i].goodsSpecificationsList[j].goodsType1 + orders.list[i].goodsSpecificationsList[j].goodsType2
                }
              }
            }
          })
        }else {
          selectAllOrdersByUserId(JSON.parse(sessionStorage.getItem('user')).userId).then(res=> {
              orders.list = res.data

              for (let i = 0; i < orders.list.length; i++) {
                if(orders.list[i].shopOrder.returnFlag!==null){
                  orders.list[i].shopOrder.returnFlag=JSON.parse(orders.list[i].shopOrder.returnFlag)
                }
                if(orders.list[i].shopOrder.changeFlag!==null){
                  orders.list[i].shopOrder.changeFlag=JSON.parse(orders.list[i].shopOrder.changeFlag)
                }
                for (let j = 0; j < orders.list[i].goodsList.length; j++) {
                  orders.list[i].goodsList[j].goodsImage1 = require('../../../assets/img/' + orders.list[i].goodsList[j].goodsImage1)

                  if (orders.list[i].goodsSpecificationsList[j].goodsType2 == null) {
                    orders.list[i].goodsSpecificationsList[j].goodsType = orders.list[i].goodsSpecificationsList[j].goodsType1
                  } else {
                    orders.list[i].goodsSpecificationsList[j].goodsType = orders.list[i].goodsSpecificationsList[j].goodsType1 + orders.list[i].goodsSpecificationsList[j].goodsType2
                  }
                }
              }
            })
          }
      }

      function selectGoods(goodsId) {
        selectByGoodsId(goodsId).then(res => {
          store.state.goods.data = res.data;
        })
      }

      let dialogForAddress=ref(null)
      let selectCancelOrder={}
      function cancelOrder(order) {
        if(order.orderState=='待付款'){
          let flag=confirm("确定要取消该订单吗？")
          if(flag==true){
            cancelShopOrder(order).then(()=>{
              changeBorder(index1)
            })
          }
        }else {
          dialogForAddress.value.style.visibility='visible'
          $('.dialogForAddress').eq(0).fadeIn(200)
          selectCancelOrder=order
        }
      }

      function deleteOrder(order) {
        let flag=confirm("确定删除该订单吗？")
        if(flag==true){
          if(order.returnFlag!==null){
            order.returnFlag=JSON.stringify(order.returnFlag)
          }
          if(order.changeFlag!==null){
            order.changeFlag=JSON.stringify(order.changeFlag)
          }
          deleteShopOrder(order).then(()=>{
            changeBorder(index1)
          })
        }
      }

      function takeDelivery(order) {
        let flag=confirm("确认收货吗？")
        if(flag==true){

          if(order.returnFlag!==null){
            order.returnFlag=JSON.stringify(order.returnFlag)
          }
          if(order.changeFlag!==null){
            order.changeFlag=JSON.stringify(order.changeFlag)
          }
          if(order.tradeNo==null){
            takeExchangeOrderDelivery(order).then(()=>{
              changeBorder(index1)
            })
          }else {
            changeOrderToComment(order).then(()=>{
              changeBorder(index1)
            })
          }

        }
      }

      function pay(order) {
        order.returnFlag=JSON.stringify(order.returnFlag)
        order.changeFlag=JSON.stringify(order.changeFlag)
        let strs=window.location.href.split('/#/')
        payOrder(order.orderNumber,strs[0]).then(res=>{
          // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
          const divForm = document.getElementsByTagName('div1')
          if (divForm.length) {
            document.body.removeChild(divForm[0])
          }
          const div = document.createElement('div1')
          div.innerHTML = res // data就是接口返回的form 表单字符串
          document.body.appendChild(div)
          document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
          document.forms[0].submit()

          let browserName = navigator.appName;
          if (browserName == "Netscape") {
            window.location.href = "about:blank";                    //关键是这句话
            window.close();
          } else if (browserName == "Microsoft Internet Explorer") {
            window.opener = null;
            window.close();
          }
        })
      }

      function closeDialog() {
        $('.dialogForAddress').eq(0).fadeOut(200)
        reasonForCancel.data=''
      }

      function cancelReturnGoods(order,index){
        let flag=confirm("确定要取消退款吗？")

        if(flag==true){
          let returnList=order.returnFlag
          returnList[index]=-1
          modifyOrderGoodsToReturn({"returnList":JSON.stringify(returnList),"orderNumber":order.orderNumber}).then(res=>{
            if(res.code==0) {
              removeReturnOrder({"orderNumber":order.orderNumber,"goodsIndex":index}).then(res=>{
                if(res.code==0){
                  changeBorder(index1)
                }
              })
            }
          })
        }
      }

      function cancelExchangeGoods(order,index){
        let flag=confirm("确定要取消换货吗？")
        if(flag==true){
          removeExchangeOrder({"orderNumber":order.orderNumber,"specificationsId":JSON.parse(order.specificationsId)[index],"goodsIndex":index}).then(res=>{
            if(res.code==0){
              changeBorder(index1)
            }
          })
        }
      }

      function cancel() {
        if(reasonForCancel.data==''){
          alert("请选择取消订单的原因！")
        }else {
          selectCancelOrder.returnFlag=JSON.stringify(selectCancelOrder.returnFlag)
          selectCancelOrder.changeFlag=JSON.stringify(selectCancelOrder.changeFlag)
          cancelShopOrder(selectCancelOrder).then(res=>{
            console.log(res);
            if(res.data=='退款成功'){
              closeDialog()
              changeBorder(index1)
            }
          })
        }
      }


      return{
        informLogo,
        changeFontColor,
        leave,
        changeBorder,
        orders,
        selectGoods,
        cancelOrder,
        deleteOrder,
        dialogForAddress,
        pay,
        cancelLogo,
        reasonForCancel,
        cancel,
        closeDialog,
        warnLogo,
        takeDelivery,
        cancelReturnGoods,
        cancelExchangeGoods
      }
    }
  }
</script>

<style scoped>

</style>