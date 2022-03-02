<template>
    <div style="width: 100%;height: auto;padding-top: 20rem;padding-bottom: 20rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center;font-size: 12rem">
        <div style="width: 95%;height: auto;background-color: white;border-radius: 5rem;display: flex;justify-content: space-evenly;align-items: flex-start;flex-wrap: wrap">
            <div style="width: 100%;height: 40rem;display: flex;border-bottom: 1rem #eff0f4 solid;padding-left: 10rem;display: flex;align-items: center;font-size: 18rem">
                订单管理
            </div>
            <div style="width: 100%;border-bottom: 1rem #eff0f4 solid;height: auto;display: flex;justify-content: flex-start;align-items: center;flex-wrap: wrap;padding: 20rem">
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">订单编号：</span>
                    <input  v-model="orderNumber"  @input="orderNumber= Number($event.target.value.replace(/\D+/, ''))" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center;">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">卖家用户名：</span>
                    <input v-model="username" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: flex-start;z-index: 2">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">上架状态：</span>
                    <select v-model="orderState" name="province" style="margin-left: 120rem;position: absolute;width: 170rem;min-height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                        <option selected value="全部">请选择订单状态</option>
                        <option value="待付款">待付款</option>
                        <option value="待发货">待发货</option>
                        <option value="待收货">待收货</option>
                        <option value="待评价">待评价</option>
                        <option value="退款中">退款中</option>
                        <option value="换货中">换货中</option>
                    </select>
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center;margin-top: 20rem">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">商品名称：</span>
                    <input  v-model="goodsTitle" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: auto;height:30rem;display: flex;justify-content: flex-start;align-items: center;margin-top: 20rem">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">下单时间：</span>
                    <div style="width:190rem;font-size: 14rem;font-family:宋体;margin: 0;padding:10rem;border-radius: 3rem;outline-color: #1d94d1;display: flex;justify-content: flex-start;align-items: flex-start">
                        <select v-model="lastYear" style="position: absolute;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in lastYearList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 50rem;">年</span>
                        <select v-model="lastMonth" style="position: absolute;margin-left: 65rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in lastMonthList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 30rem">月</span>
                        <select v-model="lastDay" style="position: absolute;margin-left: 110rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in lastDayList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 40rem">日</span>
                    </div>
                    ____
                    <div style="width:190rem;font-size: 14rem;font-family:宋体;margin: 0;padding:10rem;border-radius: 3rem;outline-color: #1d94d1;display: flex;justify-content: flex-start;align-items: flex-start">
                        <select v-model="nextYear" style="position: absolute;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in nextYearList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 50rem;">年</span>
                        <select v-model="nextMonth" style="position: absolute;margin-left: 65rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in nextMonthList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 30rem">月</span>
                        <select v-model="nextDay" style="position: absolute;margin-left: 110rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in nextDayList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 40rem">日</span>
                    </div>

                </div>
            </div>
            <div style="width: 100%;height: auto;padding: 10rem;justify-content: center;align-items: flex-start;flex-wrap: wrap">
                <div style="width: 100%;height: 50rem;display: flex;justify-content: space-between;align-items: center">
                    <button @click="searchOrders" style="cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem  rgb(32,157,189) solid;background-color:  rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                        <img :src="searchLogo" style="height: 15rem">&nbsp;查询订单
                    </button>
                    <div  v-if="orderGoodsList.list.length!=0" style="width: 400rem;height: 32rem;display: flex;align-items: center;justify-content: flex-end">
                        <span style="font-size: 14rem;color: #a0a0a0;margin-right: 20rem">共查询到
                            <span style="font-size: 18rem;color: rgb(32,157,189)">&nbsp;{{page.data.total}}&nbsp;</span>
                            个订单
                        </span>
                        <span style="font-size: 14rem;color: #a0a0a0;margin-right: 20rem">
                            <span style="font-size: 14rem;color: rgb(32,157,189)">{{page.data.current}}</span>/{{page.data.pages}}
                        </span>
                        <button v-if="page.data.current>1" @click="toChoosePage(-1)" class="nextPageBtn" style="margin-right: 10rem;border:1rem solid grey;cursor: pointer;width: 62rem;height: 32rem;border-radius: 3rem;background-color: #ffffff">
                            上一页
                        </button>
                        <button v-else  class="nextPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;margin-right: 10rem;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                            上一页
                        </button>
                        <div style="margin-right: 10rem;width: 32rem;color: white;height: 32rem;border-radius: 3rem;background-color: rgb(32,157,189);display: flex;justify-content: center;align-items: center">
                            {{page.data.current}}
                        </div>
                        <button v-if="page.data.current<page.data.pages" @click="toChoosePage(1)" class="nextPageBtn" style="border:1rem solid grey;cursor: pointer;width: 62rem;height: 32rem;border-radius: 3rem;background-color: #ffffff">
                            下一页
                        </button>
                        <button v-else class="nextPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                            下一页
                        </button>
                    </div>
                </div>


                <div style="width: 100%;height: auto;margin-top: 20rem">
                    <div v-if="orderGoodsList.list.length==0" style="width: 100%;height: 151rem;display: flex;justify-content: center;align-items: center">
                        <img :src="warnLogo" style="height: 35rem">
                        <span style="font-size: 12rem;margin-left: 20rem">无符合该条件的订单</span>
                    </div>
                    <table v-else style="width: 100%;height: auto;" border='1'   bordercolor="DFDFDF">
                        <tr>
                            <th>
                                订单编号
                            </th>
                            <th>
                                商品标题
                            </th>
                            <th>
                                商品数量
                            </th>
                            <th>
                                商品总价
                            </th>
                            <th>
                                下单时间
                            </th>
                            <th>
                                买家用户名
                            </th>
                            <th>
                                买家邮箱
                            </th>
                            <th>
                                买家地址
                            </th>
                            <th>
                               订单状态
                            </th>
                            <th>
                                订单操作
                            </th>
                        </tr>
                        <tr v-for="(item,index) in orderGoodsList.list" :key="index">
                            <td>
                                {{page.data.records[index].orderNumber}}
                            </td>
                            <td>
                                <div style="width: 120rem;height: auto;display: flex;justify-content: center;align-items: flex-start;flex-wrap: wrap">
                                    <span v-for="(item1,index1) in item" :key="index1" style=" width: 120rem;height: 20rem;word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: center;font-family:'Microsoft YaHei';">
                                        {{item1.goodsTitle}}
                                    </span>
                                </div>
                            </td>
                            <td>
                                <div style="width: auto;height: auto;display: flex;flex-direction: column;justify-content:flex-start;align-items: center;">
                                    <span v-for="(item1,index1) in goodsCountList.list[index]" :key="index1" style="width:auto;height: 20rem;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';">
                                        {{item1}}
                                    </span>
                                </div>
                            </td>
                            <td>
                                <div style="width: auto;height: auto;display: flex;flex-direction: column;justify-content:flex-start;align-items: center;">
                                    <span v-for="(item1,index1) in goodsCountList.list[index]" :key="index1" style="width:auto;height: 20rem;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';">
                                        {{item1*orderSpecifications.list[index][index1].goodsPrice}}
                                    </span>
                                </div>
                            </td>
                            <td>
                                {{page.data.records[index].time}}
                            </td>
                            <td>
                                {{users.list[index].username}}
                            </td>
                            <td>
                                {{changeEmailForm(users.list[index].email)}}
                            </td>
                            <td>
                                 <span  style=" width: 120rem;height: 20rem;word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: center;font-family:'Microsoft YaHei';">
                                        {{harvestAddresses.list[index].harvestAddress}}
                                 </span>
                            </td>
                            <td>
                                {{page.data.records[index].orderState}}
                            </td>
                            <td>
                                <div style="width: auto;height: 100%;display: flex;justify-content: space-evenly;align-items: center">
                                    <span @click="remindUserOfPaying(users.list[index])" v-if="page.data.records[index].orderState=='待付款'" style="color: #1d94d1;cursor: pointer">
                                        提醒
                                    </span>

                                    <span @click="toTakingDelivery(page.data.records[index].orderNumber)" v-if="page.data.records[index].orderState=='待发货'" style="color: #1d94d1;cursor: pointer">
                                        发货
                                    </span>

                                    <div v-if="page.data.records[index].returnFlag!==null" style="height: 100%;display: flex;flex-direction: column;align-items: center;justify-content: flex-start">
                                        <span v-for="(item1,index1) in page.data.records[index].returnFlag" :key="index1">
                                            <span v-if="item1!=0&&page.data.records[index].changeFlag[index1]!=0&&page.data.records[index].changeFlag[index1]!=1" style="height: 20rem;">
                                                未申请售后 &nbsp;|&nbsp;
                                            </span>
                                            <span v-if="page.data.records[index].changeFlag[index1]==1" style="height: 20rem;">
                                                换货待签收 &nbsp;|&nbsp;
                                            </span>
                                            <span @click="refundOrders(page.data.records[index].orderNumber,goodsCountList.list[index][index1],page.data.records[index].tradeNo, goodsCountList.list[index][index1]*orderSpecifications.list[index][index1].goodsPrice,page.data.records[index].returnFlag,index1)" v-if="item1==0" style="color: #1d94d1;cursor: pointer;height: 20rem;">
                                                退款待处理
                                                <span style="color: #333333">  &nbsp;|&nbsp;</span>
                                            </span>
                                            <span @click="agreeExchangeOrder(page.data.records[index].orderNumber, orderSpecifications.list[index][index1].specificationsId)" v-if="page.data.records[index].changeFlag[index1]==0" style="color: #1d94d1;cursor: pointer;height: 20rem;">
                                                换货待处理
                                                <span style="color: #333333">  &nbsp;|&nbsp;</span>
                                            </span>
                                        </span>
                                    </div>


                                    <span @click="deleteShopOrder(page.data.records[index].orderNumber)" v-if="page.data.records[index].orderState=='已取消'||page.data.records[index].orderState=='已完成'" style="color: #1d94d1;cursor: pointer">
                                        删除
                                    </span>

                                    <span @click="remindUserOfCommenting(users.list[index])" v-if="page.data.records[index].orderState=='待评价'" style="color: #1d94d1;cursor: pointer">
                                        提醒
                                    </span>

                                    <span @click="remindUserOfTakingDelivery(users.list[index])" v-if="page.data.records[index].orderState=='待收货'" style="color: #1d94d1;cursor: pointer">
                                        提醒
                                    </span>

                                    <span style="color: #333333">&nbsp;|&nbsp;</span>

                                    <span style="height: 100%;display:flex;align-items: center;color: #1d94d1;cursor: pointer">
                                       详情
                                    </span>
                                </div>

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
  import {reactive, ref} from "@vue/reactivity";
  import {watch} from 'vue'
  import searchShopOrders from "../../../Internet/home/shoppingOrder/select/searchShopOrders";
  import remindOfPaying from "../../../Internet/home/shoppingOrder/remind/remindOfPaying";
  import remindOfCommenting from "../../../Internet/home/shoppingOrder/remind/remindOfCommenting";
  import deleteOrderInManage from "../../../Internet/home/shoppingOrder/remove/deleteOrderInManage";
  import refund from "../../../Internet/home/shoppingOrder/update/refund";
  import toWaitTakingDelivery from "../../../Internet/home/shoppingOrder/update/toWaitTakingDelivery";
  import remindOfTakingDelivery from "../../../Internet/home/shoppingOrder/remind/remindOfTakingDelivery";
  import exchangeOrder from "../../../Internet/home/shoppingOrder/save/exchangeOrder";
  import modifyExchangeOrderForTakeDelivery
    from "../../../Internet/home/exchangeOrder/update/modifyExchangeOrderForTakeDelivery";

  export default {
    name: "orderManage",
    setup(){
      let warnLogo=require("../../../assets/img/警告.svg")

      let searchLogo=require("../../../assets/img/查询.svg")
      let lastYear=ref(2021)
      let lastMonth=ref(9)
      let lastDay=ref(5)

      let nextYear=ref(new Date().getFullYear())
      let nextMonth=ref(new Date().getMonth()+1)
      let nextDay=ref(new Date().getDate())

      let currentPage=1
      let orderNumber=ref('')
      let orderState=ref('全部')
      let goodsTitle=ref('')
      let username=ref('')

      let lastYearList=reactive({
        list:[]
      })
      let lastMonthList=reactive({
        list:[]
      })
      let lastDayList=reactive({
        list:[]
      })
      let nextYearList=reactive({
        list:[]
      })
      let nextMonthList=reactive({
        list:[]
      })
      let nextDayList=reactive({
        list:[]
      })

      let page=reactive({
        data:{}
      })
      let orderGoodsList=reactive({
        list:[]
      })
      let orderSpecifications=reactive({
        list:[]
      })
      let users=reactive({
        list:[]
      })
      let goodsCountList=reactive({
        list:[]
      })
      let harvestAddresses=reactive({
        list:[]
      })

      let year=new Date().getFullYear();

      for(let i=2021;i<=Number(year);i++){
        lastYearList.list.push(i);
        nextYearList.list.push(i);
      }

      for(let i=1;i<=new Date().getMonth()+1;i++){
        lastMonthList.list.push(i)
        nextMonthList.list.push(i)
      }

      for(let i=1;i<=new Date().getDate();i++){
        lastDayList.list.push(i)
        nextDayList.list.push(i)
      }

      searchOrders()

      watch(
          username,
          ()=>{
            currentPage=0
          }
      )

      watch(
          orderNumber,
          ()=>{
            currentPage=0
          }
      )

      watch(
          orderState,
          ()=>{
            currentPage=0
          }
      )

      watch(
          goodsTitle,
          ()=>{
            currentPage=0
          }
      )

      watch(
          lastYear,
          ()=>{
            lastMonthList.list.splice(0,lastMonthList.list.length)
            lastMonth.value=1
            if(lastYear.value<new Date().getFullYear()){
              for(let i=1;i<=12;i++){
                lastMonthList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date().getMonth()+1;i++){
                lastMonthList.list[i-1]=i
              }
            }
          }
      )

      watch(
          nextYear,
          ()=>{
            currentPage=1
            nextMonthList.list.splice(0,nextMonthList.list.length)
            nextMonth.value=1
            if(nextYear.value<new Date().getFullYear()){
              for(let i=1;i<=12;i++){
                nextMonthList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date().getMonth()+1;i++){
                nextMonthList.list[i-1]=i
              }
            }
          }
      )

      watch(
          lastMonth,
          ()=>{
            currentPage=1
            lastDayList.list.splice(0,lastDayList.list.length)
            lastDay.value=1
            if(lastMonth.value==(new Date().getMonth()+1)&&lastYear.value==year){
              for(let i=1;i<=new Date().getDate();i++){
                lastDayList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date(lastYear.value,lastMonth.value,0).getDate();i++){
                lastDayList.list[i-1]=i
              }
            }
          }
      )

      watch(
          nextMonth,
          ()=>{
            currentPage=1
            nextDayList.list.splice(0, nextDayList.list.length)
            nextDay.value=1
            if(nextMonth.value==(new Date().getMonth()+1)&&nextYear.value==year){
              for(let i=1;i<=new Date().getDate();i++){
                nextDayList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date(nextYear.value,nextMonth.value,0).getDate();i++){
                nextDayList.list[i-1]=i
              }
            }
          }
      )



      function searchOrders(){
        let fromDate0=new Date(lastYear.value,lastMonth.value-1,lastDay.value)
        let toDate0=new Date(nextYear.value,nextMonth.value-1,nextDay.value)

        if(fromDate0>toDate0){
          let temp;
          temp=lastYear.value
          lastYear.value=nextYear.value;
          nextYear.value=temp;

          let temp1
          temp1=lastMonth.value
          lastMonth.value=nextMonth.value;
          nextMonth.value=temp1;

          let temp2
          temp2=lastDay.value
          lastDay.value=nextDay.value;
          nextDay.value=temp2;
        }

        let fromDate=new Date(lastYear.value,lastMonth.value-1,lastDay.value)
        let toDate=new Date(nextYear.value,nextMonth.value-1,nextDay.value+2)

        let orderNumber1=0
        if(orderNumber.value==''){
          orderNumber1=0
        }else {
          orderNumber1=orderNumber.value
        }
        let lastOrderState=orderState.value
        searchShopOrders(JSON.parse(sessionStorage.getItem('shop')).shopId,currentPage,username.value,orderNumber1,orderState.value,goodsTitle.value,fromDate,toDate).then(res=>{
          page.data=res.data.page
          orderGoodsList.list=res.data.orderGoodsList
          orderSpecifications.list=res.data.orderSpecifications
          users.list=res.data.users
          goodsCountList.list=res.data.goodsCountList
          harvestAddresses.list=res.data.harvestAddresses

          for(let i=0;i<users.list.length;i++){
            users.list[i].image=require('../../../assets/img/'+users.list[i].image)
            if(page.data.records[i].returnFlag!==null){
              page.data.records[i].returnFlag=JSON.parse(page.data.records[i].returnFlag)
            }
            if( page.data.records[i].changeFlag!==null){
              page.data.records[i].changeFlag=JSON.parse(page.data.records[i].changeFlag)
            }
            if(lastOrderState=='退款中'){
              page.data.records[i].orderState='退款中'
            }
          }

          for(let i=0;i<orderGoodsList.list.length;i++){
            for(let j=0;j<orderGoodsList.list[i].length;j++){
              orderGoodsList.list[i][j].goodsImage1=require("../../../assets/img/"+orderGoodsList.list[i][j].goodsImage1)
            }
          }
        })
      }

      function toChoosePage(index) {
        if(index+currentPage<=0){
          return;
        }else if(index+currentPage>page.data.pages){
          return;
        }else {
          currentPage=currentPage+index
          searchOrders()
        }
      }

      function changeEmailForm(item){
        let result=/^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
        return (String(item)).replace(result, "$1****$3$4");
      }

      function remindUserOfPaying(user) {
        remindOfPaying(user.email,user.username,JSON.parse(sessionStorage.getItem("shop")).shopName)
      }

      function remindUserOfCommenting(user) {
        remindOfCommenting(user.email,user.username,JSON.parse(sessionStorage.getItem("shop")).shopName)
      }

      function remindUserOfTakingDelivery(user) {
        remindOfTakingDelivery(user.email,JSON.parse(sessionStorage.getItem("shop")).shopName)
      }

      function deleteShopOrder(orderNumber) {
        let flag=confirm("确定要删除该订单吗？")
        if(flag==true){
          deleteOrderInManage(orderNumber).then(res=>{
            if(res.data==true){
              alert("成功删除订单！")
              searchOrders()
            }
          })
        }
      }

      function refundOrders(orderNumber, goodsCount,tradeNo, money, returnFlag,index) {
        let flag=confirm("确定同意退款吗？")
        if(flag==true){
          returnFlag[index]=2
          refund(orderNumber,goodsCount,tradeNo,money,JSON.stringify(returnFlag),index).then(res=>{
            console.log(res);
            searchOrders()
          })
        }
      }
      
      function agreeExchangeOrder(orderNumber,specificationsId) {
        exchangeOrder({'orderNumber':orderNumber,'specificationsId':specificationsId}).then(res=>{
            modifyExchangeOrderForTakeDelivery({'orderNumber':orderNumber,'specificationsId':specificationsId,'exchangeOrderNumber':res.data.orderNumber}).then(()=>{
              searchOrders()
            })
        })
      }

      function toTakingDelivery(orderNumber) {
        toWaitTakingDelivery(orderNumber).then(res=>{
          if(res.code==0){
            alert("成功发货！")
            searchOrders()
          }
        })
      }

      return{
        searchLogo,
        lastDay,
        lastYear,
        lastMonth,
        nextMonth,
        nextYear,
        nextDay,
        nextYearList,
        nextMonthList,
        nextDayList,
        lastYearList,
        lastMonthList,
        lastDayList,
        username,
        orderNumber,
        orderState,
        goodsTitle,
        searchOrders,
        users,
        orderGoodsList,
        orderSpecifications,
        goodsCountList,
        page,
        harvestAddresses,
        changeEmailForm,
        toChoosePage,
        remindUserOfCommenting,
        remindUserOfPaying,
        deleteShopOrder,
        refundOrders,
        toTakingDelivery,
        remindUserOfTakingDelivery,
        warnLogo,
        agreeExchangeOrder
      }
    }
  }
</script>

<style scoped>
    th{
        background-color: rgba(32,157,189,0.5);
        height: 30rem;
    }
</style>