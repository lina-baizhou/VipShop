<template>
    <div style="width: 100%;height: auto;display: flex;justify-content:center;align-items: center;flex-wrap: wrap" class="mainDiv">
        <div style="width: 100%;height: 55rem;border-bottom: 1rem solid #e9e9e9;display: flex;justify-content: center" class="mainDiv">
            <div style="width:1000rem;height: 100%;display: flex;justify-content: space-between;align-items: center">
                <div style="width: 333rem;height: 100%;display: flex;justify-content: center;align-items: center">
                    <router-link to="/mainHeader/mySale/collection" style="width: 100%;display: flex;justify-content: center;align-items: center;color: #808080;font-size: 16rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;">
                        商品收藏
                    </router-link>
                </div>
                <div style="width: 333rem;height: 100%;display: flex;justify-content: center;align-items: center;border-bottom:2rem #de327f solid">
                    <router-link to="/mainHeader/mySale/shopCollection" style="width: 100%;display: flex;justify-content: center;align-items: center;border-right: 1rem solid #e9e9e9;color: #808080;font-size: 16rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;">
                        店铺收藏
                    </router-link>
                </div>
                <div style="width: 333rem;height: 100%;display: flex;justify-content: center;align-items: center">
                    <router-link to="/mainHeader/mySale/browse" style="width: 100%;display: flex;justify-content: center;align-items: center;color: #808080;font-size: 16rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;">
                       我的足迹
                    </router-link>
                </div>
            </div>
        </div>
        <div style="width: 100%;height: auto;display: flex;justify-content: center" class="mainDiv">
            <div style="width: 1170rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap;margin: 20rem 0rem">
                <div v-for="(item,index) in shopCollection.list" :key="index" style="width: 100%;height: 319rem;display: flex;padding: 44rem 0rem 40rem;justify-content: space-between;flex-wrap: wrap;align-items: center;border-bottom: 1px solid #e9e9e9;">
                    <router-link :to="{path:'/mainHeader/searchedShopByShopId',query: {shopId:item.shop.shopId}}" style="width: 100%;height: auto;display: flex;justify-content: flex-end;color: #333333;font-weight: 400;font-size: 16rem;margin-bottom: 20rem">
                        查看全部商品>
                    </router-link>
                    <div style="width: 165rem;height: 204rem;display: flex;flex-direction: column;justify-content: center;align-items: flex-start">
                        <div style="width: 80rem;height: 80rem;display: flex;justify-content: center;align-items: center;border: 1px solid #d9d9d9;">
                            <img :src="item.shop.shopLogo" style="width: 66rem;height: 66rem">
                        </div>
                        <div style="color: black;font-weight: 400;font-size: 20rem;margin-top: 10rem">
                            {{item.shop.shopName}}
                        </div>
                         <button @click="cancelCollectShop(item.shop.shopId)"  style="width: 100rem;height: 32rem;margin-top: 20rem;background-color: white;border:1rem #a0a0a0 solid;border-radius: 5rem;cursor: pointer">取消收藏</button>
                    </div>

                    <div style="width: 950rem;height: 203rem;display: flex;justify-content: flex-start;align-items: center">
                        <div v-for="(item1,index1) in item.goodsList" :key="index1" style="width: 190rem;height: 203rem;padding-left: 20rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                            <div style="width: 171rem;height: 171rem;display: flex;justify-content: center;align-items: center;border: 1px solid #d9d9d9;">
                                <img :src="item1.goodsImage1" style="width: 169rem;height: 169rem">
                            </div>
                            <div style="width: 170rem;height: 21rem;display: flex;justify-content: center;align-items: center">
                                <span style="font-size: 12rem;width: 50rem;height: 20rem;border-radius: 10rem;background:linear-gradient(to right,rgb(242,2,128),rgb(230,84,249));color: white">
                                    特卖价
                                </span>
                                <span  style="font-size: 20rem;margin-left: 10rem;width: auto;height:auto;display: flex;justify-content: flex-end;align-items: flex-end">
                                    ￥{{item1.goodsPrice}}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import {reactive, ref} from "@vue/reactivity";
  import $ from 'jquery';
  import selectShopCollectionList from "../../Internet/home/shopCollection/select/selectShopCollectionList";
  import removeShopCollection from "../../Internet/home/shopCollection/remove/removeShopCollection";

  export default {
    name: "Collection",
    setup(){
      let shopCollection=reactive({
        list:[]
      })

      selectShopCollectionList(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
        for(let i=0;i<res.data.length;i++){
          shopCollection.list[i]=res.data[i]
          shopCollection.list[i].shop.shopLogo=require("../../assets/img/"+shopCollection.list[i].shop.shopLogo)
          if(shopCollection.list[i].goodsList.length>5){
            shopCollection.list[i].goodsList.splice(5,shopCollection.list[i].goodsList.length-4)
          }
          for(let j=0;j<shopCollection.list[i].goodsList.length;j++){
            shopCollection.list[i].goodsList[j].goodsImage1=require("../../assets/img/"+shopCollection.list[i].goodsList[j].goodsImage1)
          }
        }
      })

      let isAllFlag=ref(false)

      function enterDiv(index){
        if(!isAllFlag.value){
          $('.deleteLogo').eq(index).css('visibility','visible')
        }
      }

      function leaveDiv(index){
        if(!isAllFlag.value) {
          $('.deleteLogo').eq(index).css('visibility', 'hidden')
        }
      }


      function allManage(){
        isAllFlag.value=true
        $('.deleteLogo').css('visibility','visible')
      }

      function cancelManage(){
        isAllFlag.value=false
        $('.deleteLogo').css('visibility', 'hidden')
      }

      let deleteLogo=require('../../assets/img/删 除 .svg')

      function cancelCollectShop(shopId) {
          removeShopCollection({'userId':JSON.parse(sessionStorage.getItem('user')).userId,'shopId':shopId}).then(res=>{
            if(res.code==0){
              shopCollection.list=[]
              selectShopCollectionList(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
                for(let i=0;i<res.data.length;i++){
                  shopCollection.list[i]=res.data[i]
                  shopCollection.list[i].shop.shopLogo=require("../../assets/img/"+shopCollection.list[i].shop.shopLogo)
                  if(shopCollection.list[i].goodsList.length>5){
                    shopCollection.list[i].goodsList.splice(5,shopCollection.list[i].goodsList.length-4)
                  }
                  for(let j=0;j<shopCollection.list[i].goodsList.length;j++){
                    shopCollection.list[i].goodsList[j].goodsImage1=require("../../assets/img/"+shopCollection.list[i].goodsList[j].goodsImage1)
                  }
                }
              })
            }
          })
      }

      return{
        deleteLogo,
        enterDiv,
        leaveDiv,
        isAllFlag,
        allManage,
        cancelManage,
        shopCollection,
        cancelCollectShop
      }
    }
  }
</script>

<style scoped>

</style>