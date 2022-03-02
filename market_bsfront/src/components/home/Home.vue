<template>
  <div style="height:auto;width: 100%">
    <show-in-right></show-in-right>
    <div style="width: 100%;height: auto;display: flex;justify-content: center;flex-wrap: wrap" class="mainDiv">
      <div style="width: 1440rem;height:400rem;display: flex;justify-content: center;align-items: flex-end" class="backgroundDiv">
        <img :src="image" style="height: 320rem;width: 780rem">
      </div>
      <div style="width:1000rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
        <div style="width: 100%;height: 50rem;font-size: 25rem;display: flex;align-items: center;justify-content: center;font-family: Arial, 'Hiragino Sans GB', 'Microsoft Yahei', 'Microsoft Sans Serif', 'WenQuanYi Micro Hei', sans-serif">
          好物推荐
        </div>

        <div style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
          <div v-for="(item,index) in  bestSaleGoodsList.list" :key="index" style="width: 25%;height: auto;display: flex;justify-content: center">
            <goods>
              <template #goodsImg>
                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}"> <img :src="item.goodsImage1" style="width: 230rem;height: 230rem"></router-link>
              </template>
              <template #goodsPrice >
                <span style="font-size: 20rem;margin-left: 10rem">￥{{item.goodsPrice}}</span>
              </template>
              <template #goodsTitle>
                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}" style="color: #666;font-family:arial;font-size: 12rem">{{item.goodsTitle}}</router-link>
              </template>
            </goods>
          </div>
        </div>

        <div style="width: 100%;height: 50rem;font-size: 25rem;display: flex;align-items: center;justify-content: center;font-family: Arial, 'Hiragino Sans GB', 'Microsoft Yahei', 'Microsoft Sans Serif', 'WenQuanYi Micro Hei', sans-serif">
          猜你喜欢
        </div>

        <div style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
          <div v-for="(item,index) in  interestingGoodsList.list" :key="index" style="width: 25%;height: auto;display: flex;justify-content: center">
            <goods>
              <template #goodsImg>
                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}"> <img :src="item.goodsImage1" style="width: 230rem;height: 230rem"></router-link>
              </template>
              <template #goodsPrice >
                <span style="font-size: 20rem;margin-left: 10rem">￥{{item.goodsPrice}}</span>
              </template>
              <template #goodsTitle>
                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}" style="color: #666;font-family:arial;font-size: 12rem">{{item.goodsTitle}}</router-link>
              </template>
            </goods>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  //import testVue from "../view/testVue.vue";
  import showInRight from "../../views/inf/showInRight";
  import {reactive} from "@vue/reactivity";
  import Goods from '../../views/goods/Goods'
  import getBestSellerGoods from "../../Internet/home/goods/select/getBestSellerGoods";
  import getInterestingGoods from "../../Internet/home/goods/select/getInterestingGoods";
  import store from "../../store/index"
  export default {
    name: 'HelloWorld',
    props: {
      msg: String
    },
    components:{
      showInRight,
      Goods
    },
    data() {
      return {
        count: 0,
        interestingGoodsList:reactive({
          list:[]
        })
      }
    },
    setup(){
      let image=require("../../assets/img/TB1r4h8JXXXXXXoXXXXvKyzTVXX-520-280.jpg")

      let bestSaleGoodsList=reactive({
        list:[]
      })

      getBestSellerGoods().then(res=>{
        bestSaleGoodsList.list=res.data
        for(let i=0;i<bestSaleGoodsList.list.length;i++){
          bestSaleGoodsList.list[i].goodsImage1=require("../../assets/img/"+bestSaleGoodsList.list[i].goodsImage1)
        }
      })

      return{
        image,
        bestSaleGoodsList,
      }
    },
    created() {

      if(store.state.user.data==null||store.state.user.data==undefined){
        getInterestingGoods(0).then(res=>{
          this.interestingGoodsList.list=res.data
          console.log(res.data);
          for(let i=0;i<this.interestingGoodsList.list.length;i++){
            this.interestingGoodsList.list[i].goodsImage1=require("../../assets/img/"+this.interestingGoodsList.list[i].goodsImage1)
          }
        })
      }else {
        getInterestingGoods(store.state.user.data.userId).then(res=>{
          this.interestingGoodsList.list=res.data
          console.log(res.data);
          for(let i=0;i<this.interestingGoodsList.list.length;i++){
            this.interestingGoodsList.list[i].goodsImage1=require("../../assets/img/"+this.interestingGoodsList.list[i].goodsImage1)
          }
        })
      }
      window.addEventListener('setItem', ()=> {
        let user=JSON.parse(sessionStorage.getItem('user'))
        if(user==null||user==undefined){
          getInterestingGoods(0).then(res=>{
            console.log(res.data);
            this.interestingGoodsList.list=res.data
            for(let i=0;i<this.interestingGoodsList.list.length;i++){
              this.interestingGoodsList.list[i].goodsImage1=require("../../assets/img/"+this.interestingGoodsList.list[i].goodsImage1)
            }
          })
        }else {
          getInterestingGoods(user.userId).then(res=>{
            console.log(res.data);
            this.interestingGoodsList.list=res.data
            for(let i=0;i<this.interestingGoodsList.list.length;i++){
              this.interestingGoodsList.list[i].goodsImage1=require("../../assets/img/"+this.interestingGoodsList.list[i].goodsImage1)
            }
          })
        }
      })
    },
  }
</script>

<style>
  .backgroundDiv{
    background-image: url("../../assets/img/1630462332990.jpg");
    background-size: 100% 100%;
    background-repeat: no-repeat;
  }
</style>
