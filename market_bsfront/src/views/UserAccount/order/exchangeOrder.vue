<template>
    <div style="width: 850rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #333;">
        <div  style="width: 100%;height:auto;display:flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
            <div style="border-left: 2rem rgb(241,1,128) solid;width: auto;padding-left:20rem;height:20rem;align-items:center;display: flex;justify-content: space-evenly;font-size: 14rem;">
                <span>我的唯品会 > 换货</span>
            </div>
        </div>

        <div style="width: 100%;height:auto;border: 1px solid #ececec;background-color: #fff;box-shadow: 0 1px 4px rgb(0 0 0 / 10%);margin-top: 10rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
            <div style="width: 100%;height:41rem;background-color: #f9f9f9;font: 14rem/24rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;padding-left: 20rem; line-height: 40rem;border-bottom: 1px solid #e0e0e0;display: flex;justify-content: flex-start;align-items: center">
                申请换货
            </div>
            <div style="width: 100%;margin-top: 10rem;padding:0rem 20rem;padding-bottom: 20rem;height: auto;display: flex;flex-direction:column;align-items: flex-start;justify-content: flex-start;">
              <div style="width: 95%;height: auto;display: flex;justify-content: space-between;align-items: center">
                    <div style="width: 500rem;display: flex;justify-content: space-between;align-items: center">
                        <img :src="orderGroup.goods.goodsImage1" style="height: 63rem">
                        <div style="width:420rem;height: 63rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                            <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:orderGroup.goods.goodsId}}" @click="selectGoodsList(orderGroup.goods.goodsId)" style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{orderGroup.goods.goodsTitle}}</router-link>
                            <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem"> {{orderGroup.specifications.goodsType1}}&nbsp;{{orderGroup.specifications.goodsType2}}</span>
                        </div>
                    </div>
                    <div style="width: auto;display: flex;justify-content: flex-start;align-items: center;margin-top: 15rem">
                        <div class="rightInf">
                            <div style="width: 75rem;height: 30rem;border: 1rem darkgrey solid;display: flex;justify-content: space-between;align-items: center">
                                <div style="width: 21rem;height: 100%;border-right: 1rem darkgrey solid;background: whitesmoke;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer"
                                     @click="()=>{
                                           if(orderGroup.count>1){
                                             orderGroup.count--
                                           }
                                         }">
                                    <minus-outlined></minus-outlined>
                                </div>
                                <div style="width: 33rem;height: 100%;background: white;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer">
                                    {{orderGroup.count}}
                                </div>
                                <div  @click="()=>{
                                           if(orderGroup.count<count.value){
                                             orderGroup.count++
                                           }
                                       }"
                                        style="width: 21rem;height: 100%;background: whitesmoke;border-left: 1rem darkgrey solid;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer" >
                                    <plus-outlined></plus-outlined>
                                </div>
                            </div>
                        </div>
                    </div>
                    <span style="color: grey">
                                ￥{{orderGroup.count*orderGroup.specifications.goodsPrice}}
                            </span>
                </div>
                <div style="width: 95%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top:10rem;flex-wrap: wrap">
                    <div style="padding:5rem 0rem;margin-right: 20rem;width:auto;margin-top: 10rem">
                        请选择想要换的商品规格：
                    </div>
                    <div style="width: 70%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
                        <div class="selectSpecificationsItem" v-for="(item,index) in specifications.list" :key="index"  @click="selectGoodsSpecifications(index,item)" @mouseenter="enterGoodsSpecifications(index)"  style="padding: 5rem;display: flex;justify-content: center;align-items: center;margin-right: 20rem;margin-top: 10rem;border: 1rem darkgrey solid;">
                            {{item.goodsType1}}&nbsp;{{item.goodsType2}}
                        </div>
                        <div v-if="selectSpecification.data.goodsPrice!==undefined&&selectSpecification.data.goodsPrice!==orderGroup.specifications.goodsPrice" style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                            价格不符，无法换货！
                        </div>
                        <div v-if="selectSpecification.data.goodsStoke!==undefined&&selectSpecification.data.goodsStoke<orderGroup.specifications.goodsStoke" style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                            库存不足，无法换货！
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div style="width: 100%;padding: 20rem 0rem;height: auto;border: 1px solid #ececec;background-color: #fff;box-shadow: 0 1px 4px rgb(0 0 0 / 10%);margin-top: 10rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
            <div style="width: 100%;height: auto;padding:0rem 20rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
                <div style="width: auto;height: auto;display: flex;justify-content: space-between;align-items: flex-start">
                    <span>请选择换货原因：</span>
                    <select v-model="exchangeCause" style="outline-color: rgb(242,2,128)">
                        <option value="请选择" selected>------------------------请选择------------------------</option>
                        <option value="使用后过敏">使用后过敏</option>
                        <option value="版本/批次/颜色/容量等与商品描述不符">版本/批次/颜色/容量等与商品描述不符</option>
                        <option value="香味与商品描述不符">香味与商品描述不符</option>
                        <option value="收到商品少许">收到商品少件/破损或污渍</option>
                        <option value="假冒品牌">假冒品牌</option>
                        <option value="卖家发错货">卖家发错货</option>
                        <option value="其他">其他</option>
                    </select>
                </div>
                <div v-if="exchangeCause=='其他'" style="width: auto;height: auto;margin-top: 20rem;display: flex;justify-content: space-between;align-items: flex-start">
                    <span>请输入详细原因：</span>
                    <textarea v-model="detailExchangeCause" placeholder="请输入详细的换货原因，最多输入五十个汉字（60个字符）" style="width: 400rem;height: 50rem;outline-color: rgb(242,2,128)"></textarea>
                </div>
            </div>
            <div style="width: 100%;padding: 0rem 20rem;margin-top: 20rem;display: flex;align-items: flex-start;justify-content: space-between;flex-wrap: wrap ">
                <span style="width: 10%;text-align: left">寄货方式：</span>
                <div style="width: 90%;padding-right: 50rem;height: 132rem;display: flex;justify-content: space-between;align-items: center">
                    <div @click="selectReturnItem('item1')" ref="item1" style="width: 320rem;height: 100%;border: 1px solid #f10180;;border-radius: 5rem;padding: 15rem;cursor: pointer;display: flex;justify-content: flex-start;flex-wrap: wrap;align-items: flex-start">
                        <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items: center">
                            <span style="font-size: 16rem">上门取件（推荐）</span>
                            <span style="width: 64rem;height: 20rem;border-radius: 10rem;border: 1rem;border: 1rem solid #f10180;color:#f10180">运费全免</span>
                        </div>
                        <span style="text-align: left;color: #a0a0a0">快递免费上门取件，免填地址，自动回单，无需支付退货运费</span>
                        <span style="text-align: left;color: #a0a0a0">本次可享受极速退款服务，快递员上门取件后将发起退款</span>
                    </div>
                    <div @click="selectReturnItem('item2')" ref="item2" style="width: 320rem;height: 100%;border: 1px solid #b3b3b3;border-radius: 5rem;padding: 15rem;cursor: pointer;display: flex;justify-content: flex-start;flex-wrap: wrap;align-items: flex-start">
                        <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items: center">
                            <span style="font-size: 16rem">自寄退货</span>
                        </div>
                        <span style="text-align: left;color: #a0a0a0">请自行联系快递寄回退货商品，唯品会收到退货并验货通过后退款</span>
                        <span style="text-align: left;color: #a0a0a0">退货产生的退货运费，在退款时将统一补贴10元唯品币至您账户</span>
                    </div>
                </div>
                <div v-if="selectItem" style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start">
                    <span style="width: 10%;text-align: left;margin-top: 25rem">取件地址：</span>
                    <div style="width: 90%;margin-top: 25rem;padding-right: 50rem;height: 50rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                        <span>{{orderGroup.harvest.harvestAddress}}</span>
                        <span>{{orderGroup.harvest.harvestName}}&nbsp;&nbsp;&nbsp;{{orderGroup.harvest.harvestEmail}}</span>
                    </div>
                </div>
                <div v-else style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start">
                    <span style="width: 10%;text-align: left;margin-top: 25rem">寄件地址：</span>
                    <div style="width: 90%;margin-top:25rem;padding-right: 50rem;height: 50rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                        <span>{{orderGroup.shop.shopAddress}}</span>
                        <span>{{orderGroup.seller.username}}&nbsp;&nbsp;&nbsp;{{orderGroup.seller.email}}</span>
                    </div>
                </div>
            </div>
        </div>

        <div style="width: 100%;height: auto;margin-top: 30rem;display: flex;justify-content: flex-end">
            <div style="width: 144rem;height: 44rem;background-color: #f10180;border: 1px solid #f10180;border-radius: 5rem;display: flex;justify-content: center;align-items: center;">
                <span @click="exchangeGoods" style="font-size: 18rem;color: white;cursor: pointer">
                    提交换货
                </span>
            </div>
        </div>
    </div>
</template>

<script>
  import {reactive, ref} from "@vue/reactivity";
  import {PlusOutlined,MinusOutlined} from '@ant-design/icons-vue'
  import selectShoppingOrderByOrderNumber from "../../../Internet/home/shoppingCart/select/selectShoppingOrderByOrderNumber";
  import selectByGoodsId from "../../../Internet/home/goods/select/selectByGoodsId";
  import store from '../../../store/index';
  import {watch} from 'vue'
  import $ from 'jquery'
  import router from "../../../router";
  import getGoodsSpecificationsByGoodsId
    from "../../../Internet/home/goodsSpecifications/select/getGoodsSpecificationsByGoodsId";
  import modifyOrderGoodsToExchange from "../../../Internet/home/shoppingOrder/update/modifyOrderGoodsToExchange";
  import saveExchangeOrder from "../../../Internet/home/exchangeOrder/save/saveExchangeOrder";
  export default {
    name: "exchangeOrder",
    components:{
      PlusOutlined,
      MinusOutlined
    },
    setup(){
      let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg = new RegExp("(^|&)" + 'orderNumber' + "=([^&]*)(&|$)");
      let r = search.match(reg);

      let reg2 = new RegExp("(^|&)" + 'goodsIndex' + "=([^&]*)(&|$)");
      let r2 = search.match(reg2);
      let goodsIndex=ref(unescape(r2[2]))

      let repeatFlag=ref(false)
      let exchangeFlag=ref(false)

      let selectSpecification=reactive({
        data:{}
      })

      let exchangeCause=ref('请选择')
      let detailExchangeCause=ref('')

      let count=ref('')

      function enterGoodsSpecifications(index) {
        for(let i=0;i<specifications.list.length;i++){
          if(specifications.list[i]!==selectSpecification.data){
            $('.selectSpecificationsItem').eq(i).css('border','1rem darkgrey solid')
          }
        }
        if(specifications.list[index]!==selectSpecification.data) {
          $('.selectSpecificationsItem').eq(index).css('border', '1rem rgb(242,2,128) solid')
        }
      }

      function selectGoodsSpecifications(index,item){
        for(let i=0;i<specifications.list.length;i++){
          $('.selectSpecificationsItem').eq(i).css('border','1rem darkgrey solid')
        }
        $('.selectSpecificationsItem').eq(index).css('border','2rem rgb(242,2,128) solid')
        selectSpecification.data=item


        if(item.goodsStoke>=orderGroup.count&&(item.goodsPrice==orderGroup.specifications.goodsPrice)){
          exchangeFlag.value=true
        }else {
          exchangeFlag.value=false
        }
      }

      let item1=ref(null)
      let item2=ref(null)

      let selectItem=ref(true)

      let totalPrice=ref(0);

      let orderGroup=reactive({
        count:[],
        goods:[],
        harvest:{},
        shopOrder:{},
        shop:{},
        specifications:[],
        return:[],
        change:[],
        seller:{}
      })

      function selectGoodsList(goodsId) {
        selectByGoodsId(goodsId).then(res => {
          store.state.goods.data = res.data;
        })
      }

      let specifications=reactive({
        list:[]
      })


      function getOrderGroup(){
        selectShoppingOrderByOrderNumber(unescape(r[2])).then(res=>{
          orderGroup.count=res.data.countList[goodsIndex.value]
          count.value=orderGroup.count
          orderGroup.goods=res.data.goodsList[goodsIndex.value]
          orderGroup.shopOrder=res.data.order
          orderGroup.shop=res.data.shop
          orderGroup.change=res.data.changeList
          orderGroup.return=res.data.returnList
          orderGroup.specifications=res.data.specificationsList[goodsIndex.value]
          orderGroup.seller=res.data.seller
          orderGroup.harvest=res.data.harvest
          orderGroup.goods.goodsImage1=require("../../../assets/img/"+orderGroup.goods.goodsImage1)

          if(orderGroup.specifications.goodsType2==null){
            orderGroup.specifications.goodsType=orderGroup.specifications.goodsType1
          }else {
            orderGroup.specifications.goodsType=orderGroup.specifications.goodsType1+orderGroup.specifications.goodsType2
          }

          totalPrice.value=orderGroup.count*orderGroup.specifications.goodsPrice
        }).then(()=>{
          getGoodsSpecificationsByGoodsId(orderGroup.goods.goodsId).then(res=>{
            specifications.list=res.data
          })
        })
      }

      getOrderGroup()


      function selectReturnItem(item) {
        if (item=='item1'){
          selectItem.value=true
          item1.value.style.border='1px solid #f10180'
          item2.value.style.border='1px solid #b3b3b3'
        }else {
          selectItem.value=false
          item2.value.style.border='1px solid #f10180'
          item1.value.style.border='1px solid #b3b3b3'
        }
      }

      function exchangeGoods(){
        if(exchangeCause.value!='请选择')
        {
          if(exchangeCause.value=='其他'&&detailExchangeCause.value==''){
            alert('请输入详细换货原因！')
          }else {
            let cause;
            if(exchangeCause=='其他'){
              cause=detailExchangeCause.value
            }else {
              cause=exchangeCause.value
            }
            if(exchangeFlag.value==true){
              let flag=confirm("确定要换货吗？")

              if(flag==true){
                orderGroup.return[goodsIndex.value]=0
                saveExchangeOrder({'orderNumber':orderGroup.shopOrder.orderNumber,'specificationsId':orderGroup.specifications.specificationsId,'goodsIndex':goodsIndex.value,'goodsCount':orderGroup.count,'exchangeCause':cause}).then(res=>{
                  console.log(res);
                  if(res.code==0){
                    modifyOrderGoodsToExchange({"changeList":JSON.stringify(orderGroup.return),"orderNumber":orderGroup.shopOrder.orderNumber}).then(res=>{
                      console.log(res);
                      if(res.code==0){
                        alert("申请换货成功，请等待商家处理！")
                        router.push("/mainHeader/UserAccountCenter/userOrder")
                      }
                    })
                  }
                })
              }
            }else {
              alert("不符合换货要求，不能换货！")
            }
          }
        }else {
          alert("请选择换货原因！")
        }
      }

      //获取字符串长度
      function getStrLength(str) {
        //1、定义计数器
        let len=0,code=0;
        //2、遍历该字符串
        for(let i=0;i<str.length;i++)
        {
          code=str.charCodeAt(i)
          if (code>=0&&code<=127) {
            len+=1;
          }else{
            len+=2;
          }
        }

        return len
      }

      watch(
          detailExchangeCause,
          (newDetailExchangeCause,preDetailExchangeCause)=>{
            if(getStrLength(newDetailExchangeCause)>60){
              detailExchangeCause.value=preDetailExchangeCause
            }
          }
      )

      return{
        orderGroup,
        selectGoodsList,
        selectReturnItem,
        selectItem,
        item1,
        item2,
        goodsIndex,
        totalPrice,
        exchangeGoods,
        specifications,
        enterGoodsSpecifications,
        selectGoodsSpecifications,
        selectSpecification,
        repeatFlag,
        count,
        exchangeCause,
        detailExchangeCause
      }
    }
  }
</script>

<style scoped>

</style>