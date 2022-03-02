<template>
    <div class="mainDiv" style="width: 100%;display: flex;height: auto;background-color: white;justify-content: center;flex-wrap: wrap;align-items: center;">
        <show-in-right></show-in-right>
        <div style="padding-top: 40rem;padding-bottom: 40rem;width: 1000rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;flex-wrap: wrap;align-items: flex-start">
            <div style="width: 100%;height: 42rem;background: white;display: flex;justify-content: flex-start;align-items: flex-start;border-bottom: 1rem solid whitesmoke">
                <router-link :to="{path:'/mainHeader/searchedShopByShopId',query: {shopId:shop.data.shopId}}"><img :src="shop.data.shopLogo" style="height: 42rem;width: auto"></router-link>
                <router-link :to="{path:'/mainHeader/searchedShopByShopId',query: {shopId:shop.data.shopId}}"><span style="margin-left: 20rem;color: black;width: auto;height: auto;font-size: 16rem;font-weight: 700;">{{shop.data.shopName}}</span></router-link>

                <div style="width: 150rem;margin-left: 20rem;height: 42rem;display: flex;justify-content:space-between;align-items: flex-start">
                    <div style="width: 33%;height: 100%;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                    <span style="font-size: 12rem;color: darkgrey">
                        描述
                    </span>
                        <span style="font-size: 12rem;font-weight: bold">
                        {{shop.data.shopDescriptionCount}}
                    </span>
                    </div>
                    <div style="width: 33%;height: 100%;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                    <span style="font-size: 12rem;color: darkgrey">
                       服务
                    </span>
                        <span style="font-size: 12rem;font-weight: bold">
                        {{shop.data.shopServiceCount}}
                    </span>
                    </div>
                    <div style="width: 33%;height: 100%;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                    <span style="font-size: 12rem;color: darkgrey">
                       物流
                    </span>
                        <span style="font-size: 12rem;font-weight: bold">
                        {{shop.data.shopLogisticsCount}}
                    </span>
                    </div>
                </div>
            </div>
            <div style="width: 100%;margin-top: 20rem;height: auto;display: flex;background: white;justify-content: space-between;align-items: flex-start;">
                <div style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;font-size: 12rem;color: black">
                    <router-link to="/mainHeader/home" style="cursor: pointer;color: black;font-size: 12rem">首页</router-link>
                    <span style="margin-left: 5rem">></span>
                    <router-link to="/mainHeader/home" style="cursor: pointer;color: black;font-size: 12rem;margin-left: 5rem">{{shop.shopName}}</router-link>
                    <span style="margin-left: 5rem">></span>
                    <span style="margin-left: 5rem">{{goods.data.goodsTitle}}</span>
                </div>
                <div @click="changeCollectionFlag" v-if="collectFlag"  style="cursor: pointer;width: auto;height: auto;display: flex;justify-content:flex-end;margin-top: 10rem;align-items: center">
                    <star-filled style="color:rgb(240,56,103);font-weight: 800"></star-filled>&nbsp;取消收藏
                </div>
                <div @click="changeCollectionFlag" v-else style="cursor:pointer;width: auto;height: auto;display: flex;justify-content:flex-end;margin-top: 10rem;align-items: center">
                    <star-outlined style="color:rgb(240,56,103);font-weight: 800"></star-outlined>&nbsp;添加收藏
                </div>
            </div>
            <div style="width: 100%;margin-top: 20rem;height: auto;display: flex;background: white;justify-content: space-between;align-items: flex-start;">
                <div style="width: 420rem;height: auto;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                    <img :src="showImage" style="width: 100%;height: 420rem">

                    <div style="width: 100%;height: 64rem;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                        <span style="width: 29rem;height: 100%;"></span>
                        <img class="imageChoose" @mouseenter="chooseImage(0, goods.data.goodsImage1)" :src="goods.data.goodsImage1" style="width: auto;height: 100%">
                        <img class="imageChoose" @mouseenter="chooseImage(1, goods.data.goodsImage2)" :src="goods.data.goodsImage2" style="width: auto;height: 100%">
                        <img class="imageChoose" @mouseenter="chooseImage(2, goods.data.goodsImage3)" :src="goods.data.goodsImage3" style="width: auto;height: 100%">
                        <img class="imageChoose" @mouseenter="chooseImage(3, goods.data.goodsImage4)" :src="goods.data.goodsImage4" style="width: auto;height: 100%">
                        <img class="imageChoose" @mouseenter="chooseImage(4, goods.data.goodsImage5)" :src="goods.data.goodsImage5" style="width: auto;height: 100%">
                        <span style="width: 29rem;height: 100%;"></span>
                    </div>
                </div>

                <div style="width: 550rem;height: auto;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                    <div style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;padding-left: 10rem">
                        <router-link to="/mainHeader/home" style="cursor: pointer;color: steelblue;font-size: 15rem;margin-left: 5rem">{{goods.data.goodsBrand}}</router-link>
                    </div>

                    <div style="width: 100%;height: auto;display: flex;justify-content: space-between;margin-top: 10rem;align-items: flex-start;flex-wrap: wrap;padding-left: 10rem">
                        <div style="width: auto;height: auto;display: flex;justify-content: flex-start;margin-top: 10rem;align-items: flex-start">
                              <span style="height: 18.667rem;width: 67rem;display: flex;justify-content: center;align-items: center;background: rgb(103,224,196);color: white;font-size: 12rem;border-radius: 2rem;">
                                  商家自营
                              </span>
                            <span style="font-size: 16rem;font-weight: 700;margin-left: 10rem;text-align: left">
                                {{goods.data.goodsTitle}}
                            </span>
                         </div>
                    </div>

                    <div class="priceDiv" style="width: 100%;height: 94rem;display: flex;justify-content: center;margin-top: 10rem;align-items: center;padding-left: 10rem;padding-right: 10rem">
                        <div style="width: 95%;height: 100%;display: flex;justify-content: space-between;align-items: center">
                            <div style="width: 200rem;height: 100%;display: flex;justify-content: space-between;align-items: center">
                                <span style="width:auto;height: auto;display: flex;align-items:center;padding: 0">
                                    <span style="font-size: 24rem;color: white;height: auto;align-items: flex-end;padding: 0;margin-top: 10rem">￥</span>
                                    <span style="font-size: 38rem;color: white;height: auto;align-items: flex-end;padding: 0">{{goods.data.goodsPrice}}</span>
                                </span>
                            </div>
                            <div style="width: 180rem;height: 60rem;display: flex;justify-content: flex-end;align-items: flex-end">
                                <span style="color: white;font-size: 12rem">
                                    累计热卖{{goods.data.goodsSalesVolumn}}件
                                </span>
                            </div>
                        </div>
                    </div>

                    <div style="width: 100%;height: auto;display: flex;flex-direction: column;justify-content: flex-start;margin-top: 10rem;align-items: flex-start;">
                        <div style="width: 100%;display: flex;justify-content: flex-start;align-items: flex-start;padding-left: 10rem;padding-right: 10rem;margin-top: 10rem">
                            <div class="leftLabel">
                                运费
                            </div>
                            <div class="rightInf">
                                运费12元，满88元免运费（实际可能受购买数量影响，具体以结算时为准）
                            </div>
                        </div>

                        <div class="selectDiv" style="width: 100%;height: auto;display: flex;flex-direction: column;padding:10rem;justify-content: flex-start;align-items: flex-start;margin-top: 10rem;">

                            <div v-for="(item,index) in goods.data.goodsSpecifications" :key="index" style="width: 100%;display: flex;justify-content: flex-start;align-items: flex-start;">
                                <div class="leftLabel" style="margin-top: 10rem">
                                    {{item.name}}
                                </div>
                                <div class="rightInf">
                                    <div v-for="(item1,index1) in item.data" @click="selectGoodsSpecifications(index1,item,index)" @mouseenter="enterGoodsSpecifications(index1,item,index)" :class="item.name" :key="index1" style="cursor: pointer;width: auto;height:auto;display: flex;align-items: center;padding-bottom: 5rem;padding-top: 5rem;padding-left: 20rem;padding-right: 20rem;border: 1rem darkgrey solid;margin-top: 10rem">
                                        {{item1}}
                                    </div>
                                </div>
                            </div>

                            <div style="width: 100%;display: flex;justify-content: flex-start;align-items: center;margin-top: 15rem" v-if="countFlag">
                                <div class="leftLabel">
                                    数量
                                </div>
                                <div class="rightInf">
                                    <div style="width: 75rem;height: 30rem;border: 1rem darkgrey solid;display: flex;justify-content: space-between;align-items: center">
                                        <div style="width: 21rem;height: 100%;border-right: 1rem darkgrey solid;background: whitesmoke;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer"
                                             @click="()=>{
                                           if(count>1){
                                             count--
                                           }
                                         }">
                                            <minus-outlined></minus-outlined>
                                        </div>
                                        <div style="width: 33rem;height: 100%;background: white;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer">
                                            {{count}}
                                        </div>
                                        <div style="width: 21rem;height: 100%;background: whitesmoke;border-left: 1rem darkgrey solid;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer" @click="addCount">
                                            <plus-outlined></plus-outlined>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div style="width: 100%;display: flex;justify-content: flex-start;align-items: center;margin-top: 10rem;" v-if="countFlag">
                                <div class="leftLabel">

                                </div>
                                <div class="rightInf" style="display: flex;justify-content: flex-start;color: rgb(240,56,110)">
                                    {{selectInf}}
                                </div>
                            </div>
                        </div>

                        <div style="width: 100%;display: flex;justify-content: flex-start;align-items: center;margin-top: 25rem;padding-left: 10rem;padding-right: 10rem" v-if="countFlag">
                            <div class="leftLabel">

                            </div>
                            <div class="rightInf" style="display: flex;justify-content: flex-start">
                                <div @click="balanceAccount(goods)" style="cursor: pointer;width: 139rem;height: 44rem;background: rgba(240,56,103,0.6);border-radius: 5rem;border-color:rgba(240,56,103,0.3);outline-color: white;color: white;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                                    <span style="font-size:18rem;font-weight: 700">￥{{goods.data.goodsPrice}}</span>
                                    <span style="font-size: 15rem;margin-top: -5rem;font-weight: lighter">特卖价 抢></span>
                                </div>

                                <div @click="addIntoShoppingCart(goods)"  style="cursor: pointer;width: 139rem;margin-left: 20rem;height: 44rem;background: rgb(240,56,103);border-radius: 5rem;border-color:rgb(240,56,103);outline-color: white;color: white;display: flex;justify-content: center;align-items: center">
                                    <img  :src="shopping" style="max-height: 30rem"><span style="margin-left: 5rem;font-size:14rem;font-weight: 700">加入购物袋</span>
                                </div>
                            </div>
                        </div>

                        <div style="width: 100%;display: flex;justify-content: flex-start;align-items: center;margin-top: 25rem;padding-left: 10rem;padding-right: 10rem;border-top: 1rem dotted grey" v-else>
                            <div class="leftLabel">

                            </div>
                            <div class="rightInf" style="display: flex;justify-content: flex-start">
                                本商品已抢光，下次早点来！
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="goods.data.shelfState==1" style="width: 1000rem;height: 100%;display: flex;justify-content: center;flex-direction: column;align-items: center;background-color: rgba(255,255,255,0.3);color: #a0a0a0;font-size: 35rem; font-style: oblique 8deg;z-index: 2; position: absolute">
                <div style="-o-transform: rotate(30deg);-webkit-transform: rotate(30deg);-moz-transform: rotate(30deg);">
                    很抱歉，您查看的宝贝可能已下架或者被转移。
                </div>
                <span></span>
            </div>
            <show-comments style="margin-top:40rem;width: 1000rem"></show-comments>
        </div>
    </div>
</template>

<script>
  import ShowInRight from "../../views/inf/showInRight";
  import {reactive, ref} from "@vue/reactivity";
  import {watch} from "vue"
  import selectByGoodsId from "../../Internet/home/goods/select/selectByGoodsId";
  import $ from 'jquery'
  import {PlusOutlined,MinusOutlined,StarOutlined,StarFilled} from '@ant-design/icons-vue'
  import selectGoodsCollectionFlag from "../../Internet/home/goodsCollection/select/selectGoodsCollectionFlag";
  import addGoodsCollection from "../../Internet/home/goodsCollection/add/addGoodsCollection";
  import deleteGoodsCollection from "../../Internet/home/goods/remove/deleteGoodsCollection";
  import getGoodsCollectionList from "../../Internet/home/goodsCollection/select/getGoodsCollectionList";
  import store from "../../store/index"
  import saveShoppingCart from "../../Internet/home/shoppingCart/save/saveShoppingCart";
  import getGoodsSpecifications from "../../Internet/home/goodsSpecifications/select/selectGoodsSpecifications";
  import getShoppingCartList from "../../assets/functions/getShoppingCartList";
  import {provide} from "@vue/runtime-core";
  import showComments from "../comment/showCommments"
  import saveBrowsingHistory from "../../Internet/home/browsingHistory/save/saveBrowsingHistory";
  import findByShopId from "../../Internet/home/shop/find/findByShopId";
  import router from "../../router/index"

  export default {
    name: "GoodsDetailPage",
    components:{
      ShowInRight,
      PlusOutlined,
      MinusOutlined,
      StarOutlined,
      StarFilled,
      showComments
    },
    setup(){
      let shop=reactive({
        data:{}
      })

      let search = search ||  window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg = new RegExp("(^|&)"+ 'goodsId' +"=([^&]*)(&|$)");
      let r = search.match(reg);

      let goods=reactive({
        data:{
          'goodsBrand':'',
          'goodsDescription':'',
          'goodsId':'',
          'goodsImage1':'',
          'goodsImage2':'',
          'goodsImage3':'',
          'goodsImage4':'',
          'goodsImage5':'',
          'goodsPrice':'',
          'goodsSalesVolumn':'',
          'goodsSpecifications':[],
          'goodsTitle':'',
          'goodsTypeId':'',
          'shopId':'',
          'shelfState':'',
        }
      })

      let shopping=require("../../assets/img/顶部购物袋.svg")

      let showImage=ref('')

      let count=ref(1)

      let goodsId=ref(unescape(r[2]))

      provide('goodsId',goodsId)


      let selectIndex=[]

      selectByGoodsId(unescape(r[2])).then(res=>{
        store.state.goods.data=res.data;
        findByShopId(res.data.shopId).then(res=>{
          shop.data=res.data
          shop.data.shopLogo=require('../../assets/img/'+shop.data.shopLogo)
        })
      })

      function setGoodsInf(){
        goods.data.goodsBrand=store.state.goods.data.goodsBrand
        goods.data.goodsDescription=store.state.goods.data.goodsDescription
        goods.data.goodsId=store.state.goods.data.goodsId

        goods.data.goodsImage1=require('../../assets/img/'+ store.state.goods.data.goodsImage1)
        goods.data.goodsImage2=require('../../assets/img/'+ store.state.goods.data.goodsImage2)
        goods.data.goodsImage3=require('../../assets/img/'+ store.state.goods.data.goodsImage3)
        goods.data.goodsImage4=require('../../assets/img/'+ store.state.goods.data.goodsImage4)
        goods.data.goodsImage5=require('../../assets/img/'+ store.state.goods.data.goodsImage5)

        goods.data.goodsPrice=store.state.goods.data.goodsPrice
        goods.data.goodsSalesVolumn=store.state.goods.data.goodsSalesVolumn
        goods.data.goodsSpecifications=store.state.goods.data.goodsSpecifications
        goods.data.goodsTitle=store.state.goods.data.goodsTitle
        goods.data.goodsTypeId=store.state.goods.data.goodsTypeId
        goods.data.shopId=store.state.goods.data.shopId
        goods.data.shelfState=store.state.goods.data.shelfState

        showImage.value= goods.data.goodsImage1

        let strs1= goods.data.goodsSpecifications.split(';')

        let strs=[]
        for(let i=0;i<strs1.length;i++){
          let strTemp=strs1[i].split(':')
          for(let j=0;j<strTemp.length;j++){
            strs.push(strTemp[j])
          }
        }

        let goodsSpecifications=[]
        for(let i=0;i<strs.length/2;i++){
          let goodsSpecification={name:'',data:[]}
          goodsSpecification.name=strs[2*i]
          goodsSpecification.data=strs[2*i+1].split(' ')
          goodsSpecification.data.splice(goodsSpecification.data.length-1,1)

          goodsSpecifications[i]=goodsSpecification
        }

       goods.data.goodsSpecifications=goodsSpecifications

        for(let i=0;i<goods.data.goodsSpecifications.length;i++){
          selectIndex[i]=' '
        }
      }

      watch(
          store.state.goods,
          () => {
            setGoodsInf()
            let search = search ||  window.location.search.substr(1) || window.location.hash.split("?")[1];
            let reg = new RegExp("(^|&)"+ 'goodsId' +"=([^&]*)(&|$)");
            let r = search.match(reg);
            goodsId.value=unescape(r[2])
            selectGoodsCollectionFlag(JSON.parse(sessionStorage.getItem('user')).userId,unescape(r[2])).then(res=>{
              collectFlag.value=res.data
            })

            if(sessionStorage.getItem('user')!==null&&sessionStorage.getItem('user')!==undefined){
              saveBrowsingHistory({'userId':JSON.parse(sessionStorage.getItem('user')).userId,'goodsId':unescape(r[2]),'shopId':store.state.goods.data.shopId}).then(res=>{
                console.log(res);
              })
            }

            goodsSpecifications.data={}

            selectInf.value=''

            countFlag.value=true

            count.value=1
          }
      )


      let collectFlag=ref(false)


      function chooseImage(index,image) {
        for(let i=0;i<5;i++){
          if(i==index){
            $('.imageChoose').eq(index).css('border','1rem rgb(242,2,128) solid')
          }else {
            $('.imageChoose').eq(i).css('border','none')
          }
        }

        showImage.value=image
      }

      let selectInf=ref('')



      function changeCollectionFlag() {

        if(collectFlag.value==false){//加入收藏
          addGoodsCollection({'userId':JSON.parse(sessionStorage.getItem('user')).userId,'goodsId':unescape(r[2])}).then(()=>{
            getGoodsCollectionList(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
              store.state.goodsCollectionList.list=res.data

              for(let i=0;i<store.state.goodsCollectionList.list.length;i++){
                store.state.goodsCollectionList.list[i].goodsImage1=require("../../assets/img/"+store.state.goodsCollectionList.list[i].goodsImage1)
              }

              collectFlag.value=!collectFlag.value
            })
          })
        }else {
          deleteGoodsCollection({'userId':JSON.parse(sessionStorage.getItem('user')).userId,'goodsId':unescape(r[2])}).then(()=>{
            getGoodsCollectionList(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
              store.state.goodsCollectionList.list=res.data

              for(let i=0;i<store.state.goodsCollectionList.list.length;i++){
                store.state.goodsCollectionList.list[i].goodsImage1=require("../../assets/img/"+store.state.goodsCollectionList.list[i].goodsImage1)
              }

              collectFlag.value=!collectFlag.value
            })
          })
        }

      }

      let goodsSpecifications=reactive({
        data:{}
      })

      function enterGoodsSpecifications(index,item,index1) {

          for(let i=0;i<item.data.length;i++){
            if(i!==selectIndex[index1]){
              if(i==index){
                $("."+item.name).eq(index).css('border','1rem rgb(242,2,128) solid')
              }else {
                $("."+item.name).eq(i).css('border','1rem darkgrey solid')
              }
            }
          }
      }

      let flag=true



      function checkSelectIndex() {
        let flag1 = true

        for (let i = 0; i < selectIndex.length; i++) {
          if (selectIndex[i] ===" ") {
            flag1 = false
          }
        }
        if (flag1 == true) {
          flag=true
          console.log(flag);
          $('.selectDiv').eq(0).css('background','none')
          $('.selectDiv').eq(0).css('border', 'none')
          selectInf.value = ''
        }else {
          flag=false
          $('.selectDiv').eq(0).css('background', 'rgba(240,56,103,0.1)')
          $('.selectDiv').eq(0).css('border', '1rem rgb(240,56,103) solid')
          selectInf.value = '请选择规格信息'
        }
      }

      let countFlag=ref(true)


      function addCount() {
        checkSelectIndex()

        if(flag==true){
          if(count.value<goodsSpecifications.data.goodsStoke){
            count.value++
          }
        }

      }

      function selectGoodsSpecifications(index,item,index1){
        selectIndex[index1]=index

        for(let i=0;i<item.data.length;i++){
            $("."+item.name).eq(i).css('border','1rem darkgrey solid')
        }
        $("."+item.name).eq(index).css('border','2rem rgb(242,2,128) solid')

        let flag1 = true
        for (let i = 0; i < selectIndex.length; i++) {
          if (selectIndex[i] ===" ") {
            flag1 = false
          }
        }
        if (flag1 == true) {
          flag = true
          $('.selectDiv').eq(0).css('background', 'none')
          $('.selectDiv').eq(0).css('border', 'none')
          selectInf.value = ''

          if (selectIndex.length == 2) {
            getGoodsSpecifications(goods.data.goodsId,goods.data.goodsSpecifications[0].data[selectIndex[0]], goods.data.goodsSpecifications[1].data[selectIndex[1]]).then(res => {
              if (res.code == 0) {
                goodsSpecifications.data = res.data
              }
            }).then(() => {
              if (goodsSpecifications.data.goodsStoke == 0) {
                countFlag.value = false
              }
            })
          }else {
            getGoodsSpecifications(goods.data.goodsId, goods.data.goodsSpecifications[0].data[selectIndex[0]],'null').then(res => {
              if (res.code == 0) {
                goodsSpecifications.data = res.data
              }
            }).then(() => {
              if (goodsSpecifications.data.goodsStoke == 0) {
                countFlag.value = false
              }else {
                countFlag.value = true
              }
            })
          }
        }
      }
      
      function addIntoShoppingCart(item) {
        checkSelectIndex()

          if(flag==true){
            saveShoppingCart({
              'goodsId': item.data.goodsId,
              'userId': JSON.parse(sessionStorage.getItem('user')).userId,
              'time': '',
              'specificationsId': goodsSpecifications.data.specificationsId,
              'count': count.value,
              'shopId': item.data.shopId
            }).then(()=>{
              let user1=JSON.parse(sessionStorage.getItem('user'))
              if(user1!=null){
                getShoppingCartList(user1.userId)
              }
            })
          }

      }

      function balanceAccount(item) {
        checkSelectIndex()
        if (flag==true) {

          let shoppingCartList = []

          let shoppingCart = {
            shop:shop.data,
            goodsList: [item.data],
            goodsSpecificationsList: [goodsSpecifications.data],
            goodsCountList: [count.value]
          }


          console.log(shoppingCart);

          shoppingCartList[0]=shoppingCart

          sessionStorage.setItem('goodsList', JSON.stringify(shoppingCartList))
          router.push('/confirmOrderPage')

        }
      }

      return{
        shop,
        goods,
        showImage,
        chooseImage,
        count,
        collectFlag,
        countFlag,
        addCount,
        changeCollectionFlag,
        enterGoodsSpecifications,
        selectGoodsSpecifications,
        shopping,
        addIntoShoppingCart,
        selectInf,
        balanceAccount,
        store
      }
    }
  }
</script>

<style scoped>
    .priceDiv{
        background-image: url("../../assets/img/162881901743.png");
        background-size: 100% 100%;
        background-repeat: no-repeat;
    }
    .leftLabel{
        width: 10%;
        height: auto;
        display: flex;
        justify-content: flex-start;
        align-items: flex-start;
        font-size: 12rem;
        color: grey;
    }
    .rightInf{
        width: 90%;
        height: auto;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        font-size: 12rem;
        align-items: flex-start;
    }
</style>