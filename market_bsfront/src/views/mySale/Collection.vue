<template>
    <div style="width: 100%;height: auto;display: flex;justify-content:center;align-items: center;flex-wrap: wrap" class="mainDiv">
        <div style="width: 100%;height: 55rem;border-bottom: 1rem solid #e9e9e9;display: flex;justify-content: center" class="mainDiv">
            <div style="width:1000rem;height: 100%;display: flex;justify-content: space-between;align-items: center">
                <div style="width: 333rem;height: 100%;display: flex;justify-content: center;align-items: center;border-bottom:2rem #de327f solid">
                    <router-link to="/mainHeader/mySale/collection" style="width: 100%;display: flex;justify-content: center;align-items: center;color: #808080;font-size: 16rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;">
                        商品收藏
                    </router-link>
                </div>
                <div style="width: 333rem;height: 100%;display: flex;justify-content: center;align-items: center">
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
        <div style="width: 1000rem;height: 40rem;display: flex;justify-content:flex-end;margin-top: 20rem" class="mainDiv">
            <button @click="cancelManage" v-if="isAllFlag" style="width: 100rem;height: 32rem;background-color: white;border:1rem #a0a0a0 solid;border-radius: 5rem;cursor: pointer">取消管理</button>
            <button @click="allManage" v-else style="width: 100rem;height: 32rem;background-color: white;border:1rem #a0a0a0 solid;border-radius: 5rem;cursor: pointer">批量管理</button>
        </div>
        <div style="width: 100%;height: auto;display: flex;justify-content: center" class="mainDiv">
            <div style="width: 1030rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap;margin-top: 20rem">
                <div v-for="(item,index) in goodsList.list" :key="index" @mouseenter="enterDiv(index)" @mouseleave="leaveDiv(index)" style="width: 233rem;height: 355rem;border:1rem lightgrey solid;margin: 0 20rem 20rem 0;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start">
                    <div v-if="isAllFlag" style="width: 233rem;position: absolute;height: 100%;display: flex;align-items: flex-start;justify-content: flex-end;background-color: rgba(255,255,255,0.3)">
                        <img @click="deleteBrowse(item,index)" :src="deleteLogo" style="width: 30rem;margin: 10rem;cursor: pointer;">
                    </div>

                    <div v-else style="width: 233rem;position: absolute;height: auto;display: flex;justify-content: flex-end">
                        <img @click="deleteBrowse(item,index)" class="deleteLogo" :src="deleteLogo" style="width: 30rem;margin: 10rem;cursor: pointer;visibility: hidden">
                    </div>

                    <div  style="width: 222rem;height: 222rem">
                        <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}"> <img :src="item.goodsImage1" style="width: 230rem;height: 230rem"></router-link>
                    </div>
                    <div style="width: 100%;height: 223rem;display: flex;flex-direction: column;align-items: center">
                        <div style="width: 90%;height: 40rem;display: flex;justify-content: flex-start;align-items: center">
                             <span style="font-size: 12rem;width: 50rem;height: 20rem;border-radius: 10rem;background:linear-gradient(to right,rgb(242,2,128),rgb(230,84,249));color: white">
                                 特卖价
                             </span>
                            <div style="margin-left: 20rem;width: 30rem;height:auto;display: flex;justify-content: flex-end;align-items: flex-end">
                                <span style="font-size: 20rem;margin-left: 10rem">￥{{item.goodsPrice}}</span>
                            </div>

                        </div>
                        <div style="font-size: 13rem;color: darkgrey;width: 90%;height: 30rem;display: flex;justify-content: flex-start;text-align: left;align-items: flex-end;flex-wrap: wrap">
                            <div>
                                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}" style="color: #666;font-family:arial;font-size: 12rem">{{item.goodsTitle}}</router-link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import getGoodsCollectionList from "../../Internet/home/goodsCollection/select/getGoodsCollectionList";
  import deleteGoodsCollection from "../../Internet/home/goods/remove/deleteGoodsCollection";
  import {reactive, ref} from "@vue/reactivity";
  import $ from 'jquery';
  import store from '../../store/index'

  export default {
    name: "Collection",
    setup(){
      let goodsList=reactive({
        list:[]
      })

      getGoodsCollectionList(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
        goodsList.list=res.data
        for(let i=0;i<goodsList.list.length;i++){
          goodsList.list[i].goodsImage1=require('../../assets/img/'+goodsList.list[i].goodsImage1)
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

      function deleteBrowse(item,index){
        deleteGoodsCollection({'userId':JSON.parse(sessionStorage.getItem('user')).userId,'goodsId':item.goodsId}).then(res=>{
          if(res.code==0){
            goodsList.list.splice(index,1)
            getGoodsCollectionList(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
              store.state.goodsCollectionList.list=res.data

              for(let i=0;i<store.state.goodsCollectionList.list.length;i++){
                store.state.goodsCollectionList.list[i].goodsImage1=require("../../assets/img/"+store.state.goodsCollectionList.list[i].goodsImage1)
              }

            })
          }
        })
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
      return{
        goodsList,
        deleteLogo,
        enterDiv,
        leaveDiv,
        deleteBrowse,
        isAllFlag,
        allManage,
        cancelManage
      }
    }
  }
</script>

<style scoped>

</style>