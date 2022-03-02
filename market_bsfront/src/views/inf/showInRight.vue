<template>
    <show-types></show-types>
        <div id="showRightMain" style="position: fixed;width: 33rem;height: 556rem;right: 0rem;top: 0rem;background-color: rgb(38,38,38);display: flex;flex-direction: column;justify-content: space-evenly;z-index:2;font-size: 16rem">
                <div style="position: absolute;width: 274rem;height: 100%;right: -274rem;border-top-left-radius: 5px;background-color: white;z-index: 2;border-left: 1rem darkgrey solid;display: flex;justify-content: flex-start;align-items: flex-start;padding: 10rem;" id="showInfDiv">
                    <div v-if="type1=='myShopping'" style="width: 100%;height: 100%;display: flex;flex-direction:column;justify-content: flex-start;align-items: flex-start">
                        <span style="width: 100%;font-size: 14rem;display: flex;justify-content: flex-start;height: 40rem;align-items: center;border-bottom: 1rem lightgrey solid">
                            购物车
                        </span>
                        <div style="width: 100%;max-height: 82%;overflow-y: auto;display: flex;justify-content: space-between;flex-wrap: wrap;align-items: flex-start">
                            <div v-for="(item,index) in store.state.shoppingCartList.list" :key="index" style="display: flex;flex-direction: column;width: 100%;height: auto">
                                <div v-for="(item1,index1) in item.goodsList" :key="index1" style="padding-top: 15rem;padding-bottom: 4rem;width: 100%;display: flex;justify-content: space-between;align-items: center;height: 75rem">
                                    <img :src="item1.goodsImage1" style="width: 60rem;height: 70rem">
                                    <div style="width: 65rem;height: 68rem;display: flex;flex-direction: column;justify-content: space-between">
                                        <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item1.goodsId}}" @click="()=>{selectGoods(item1.goodsId);changeShowInf('myShopping')}" style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{item1.goodsTitle}}</router-link>
                                        <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem">{{item.goodsSpecificationsList[index1].goodsType1}}</span>
                                    </div>
                                    <div style="width: 50rem;height: 36rem;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                        {{item.goodsCountList[index1]}}
                                    </div>
                                    <div style="width: 50rem;height: 36rem;display: flex;justify-content: center;align-items: center;color: rgb(244,51,153);font-size: 12rem">
                                        ￥{{item.goodsSpecificationsList[index1].goodsPrice*item.goodsCountList[index1]}}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <router-link to="/shoppingCartPage" style="width: 100%">
                            <button style="width: 100%;height: 30rem;margin-top: 10rem;border: 0rem;background: rgb(242,1,128);color: white;border-radius: 5rem">
                                去购物车
                            </button>
                        </router-link>
                    </div>

                    <div v-if="type1=='myCollection'" style="width: 100%;height: auto;display: flex;flex-direction:column;justify-content: flex-start;align-items: flex-start">
                        <span style="width: 100%;font-size: 14rem;display: flex;justify-content: flex-start;height: 40rem;align-items: center;border-bottom: 1rem lightgrey solid">
                            我收藏的商品
                        </span>
                        <div style="width: 100%;height: 520rem;overflow-y:auto;overflow-x: hidden;display: flex;justify-content: space-between;flex-wrap: wrap;align-items: flex-start">
                            <div v-for="(item,index) in store.state.goodsCollectionList.list" :key="index" style="width: 113rem;height: 176rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;margin-top: 10rem;border: 1rem lightgrey solid">
                                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}"  @click="()=>{selectGoods(item.goodsId);changeShowInf('myCollection')}" style="width: 113rem;height: 176rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;">
                                 <img :src="item.goodsImage1" style="width: 100%;height: 142rem">
                                 <div style="width: 100%;height: 34rem;display: flex;justify-content: flex-start;align-items: center">
                                    <span style="width: auto;height: auto;margin-left: 10rem;color: rgb(244,51,153);font-size: 15rem;font-weight: 700;">
                                        ￥{{item.goodsPrice}}
                                    </span>
                                 </div>
                                </router-link>
                            </div>
                            <div style="width: 100%;height: 60rem;display: flex;justify-content: center;align-items: center">
                                <button @click="toCollectionPage" style="cursor: pointer;width: 238rem;height: 34rem;border: 1px solid #d8d6d6;border-radius: 3px;background-color: #f9f7f8;font-family: '\5B8B\4F53';color: #000;">
                                    查看全部收藏商品
                                </button>
                            </div>
                        </div>
                    </div>

                    <div v-if="type1=='myHistory'">
                         <span style="width: 100%;font-size: 14rem;display: flex;justify-content: flex-start;height: 40rem;align-items: center;border-bottom: 1rem lightgrey solid">
                            我的足迹
                        </span>
                        <div style="width: 100%;height: 520rem;overflow-y:auto;overflow-x: hidden;display: flex;justify-content: space-between;flex-wrap: wrap;align-items: flex-start">
                            <div v-for="(item,index) in store.state.goodsBrowseList.list" :key="index" style="width: 113rem;height: 176rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;margin-top: 10rem;border: 1rem lightgrey solid">
                                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item.goodsId}}"  @click="()=>{selectGoods(item.goodsId);changeShowInf('myHistory')}" style="width: 113rem;height: 176rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;">
                                    <img :src="item.goodsImage1" style="width: 100%;height: 142rem">
                                    <div style="width: 100%;height: 34rem;display: flex;justify-content: flex-start;align-items: center">
                                    <span style="width: auto;height: auto;margin-left: 10rem;color: rgb(244,51,153);font-size: 15rem;font-weight: 700;">
                                        ￥{{item.goodsPrice}}
                                    </span>
                                    </div>
                                </router-link>
                            </div>
                            <div style="width: 100%;height: 60rem;display: flex;justify-content: center;align-items: center">
                                <button @click="toBrowsePage" style="cursor: pointer;width: 238rem;height: 34rem;border: 1px solid #d8d6d6;border-radius: 3px;background-color: #f9f7f8;font-family: '\5B8B\4F53';color: #000;">
                                    查看全部浏览记录
                                </button>
                            </div>
                        </div>
                    </div>

                    <div v-if="type1=='myBrandCollection'" style="width: 100%;height: auto;display: flex;flex-direction:column;justify-content: flex-start;align-items: flex-start">
                        <span style="width: 100%;font-size: 14rem;display: flex;justify-content: flex-start;height: 40rem;align-items: center;border-bottom: 1rem lightgrey solid">
                            店铺收藏
                        </span>
                        <div style="width: 100%;height: 520rem;overflow-y:auto;overflow-x: hidden;display: flex;justify-content: center;flex-wrap: wrap;align-items: flex-start">
                            <router-link :to="{path:'/mainHeader/searchedShopByShopId',query: {shopId:item.shop.shopId}}" v-for="(item,index) in store.state.shopCollectionList.list" :key="index" style="margin-top: 20rem;width: 248rem;height: 120rem;border: 1px solid #cac8c8;display: flex;justify-content: center;align-items: center">
                                <div style="width: 214rem;height: 70rem;display: flex;justify-content: space-between;align-items: center">
                                    <div style="height: 70rem;width: 70rem;display: flex;justify-content: center;align-items: center;border: 1px solid #cac8c8;">
                                        <img :src="item.shop.shopLogo" style="width: 67rem;height: 67rem">
                                    </div>
                                    <span>
                                        <span style="color: #333333">{{item.shop.shopName}}</span>
                                    </span>
                                </div>
                            </router-link>
                            <button @click="toShopCollectionPage" style="margin-top: 20rem;cursor: pointer;width: 238rem;height: 34rem;border: 1px solid #d8d6d6;border-radius: 3px;background-color: #f9f7f8;font-family: '\5B8B\4F53';color: #000;">
                                查看全部收藏店铺
                            </button>
                        </div>
                    </div>
                </div>


                <div style="width:33rem;height: 400rem;display: flex;flex-direction: column;align-items: center;justify-content: space-between;z-index: 2">
                    <div id="userInfDiv" style="width: 33rem;height: 80rem;display: flex;justify-content: center;align-items: flex-start;position: relative;z-index: 2;" @click="changeShowUserInf">
                        <div style="width:100%;height: 100%;display: flex;align-items: center;justify-content: space-evenly;z-index: 4">
                            <img :src="userLogo" style="max-height:25rem">
                        </div>
                        <div id="showUserInf">
                            <div style="width: 100%;height: 140rem;display: flex;flex-direction:column;justify-content: space-around;align-items: center">
                                <img :src="userlogo2" style="border-radius: 50%;max-width: 70rem">
                                <router-link to="/login/loginPage" style="color: darkgrey;cursor: pointer" v-if="loginFlag">
                                    请登录
                                </router-link>
                                <span style="color: rgb(244,51,153)" v-else>{{loginName}}<span style="color: black">,你好!</span></span>
                            </div>
                            <div style="width: 100%;height: 120rem;display: flex;justify-content: space-evenly;background-color: whitesmoke;border: 0.1rem lightgrey solid;align-items: center">
                                <div style="width: 100%;height: 80rem;display: flex;justify-content: space-evenly;background-color: whitesmoke;align-items: center">
                                    <div style="width: 50%;height: 80rem;display: flex;flex-direction:column;justify-content: space-evenly;background-color: whitesmoke;align-items: center;border-right: 0.1rem lightgrey solid">
                                        <profile-outlined style="font-size: 30rem;color: grey"></profile-outlined>
                                        <span>我的订单</span>
                                    </div>
                                    <div style="width: 50%;height: 80rem;display: flex;flex-direction:column;justify-content: space-evenly;background-color: whitesmoke;align-items: center;">
                                        <pay-circle-outlined style="font-size: 30rem;color: grey"></pay-circle-outlined>
                                        <span>我的唯品币</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div @click="changeShowInf('myShopping')" style="color: white;width: 33rem;height: 180rem;background-color:rgb(244,51,153);z-index: 2;display: flex;flex-direction: column;justify-content: space-around;align-items: center;position: relative;" id="myShopping">
                        <div style="color: white;width: 33rem;height: 120rem;background-color:rgb(244,51,153);z-index: 2;display: flex;flex-direction: column;justify-content: space-around;align-items: center;">
                            <shopping-outlined  style="background-color:rgb(244,51,153);font-size: 25rem">
                            </shopping-outlined>
                            <span style="font-size: 14rem">购</span>
                            <span style="font-size: 14rem">物</span>
                            <span style="font-size: 14rem">袋</span>
                            <span style="border-radius: 50%;background-color: white;color: rgb(244,51,153);width: 20rem;height: 20rem">{{store.state.shoppingCartList.goodsCount}}</span>
                        </div>
                    </div>
                    <div @click="changeShowInf('myBrandCollection')" @mouseenter="showMyBrandColletion" @mouseleave="hiedMyBrandColletion" style="width: 33rem;height: 33rem;color: white;font-size: 25rem;display: flex;justify-content: space-around;align-items: center;position: relative;" id="myBrandCollection">
                        <div style="width: 33rem;height: 33rem;color: white;font-size: 25rem;display: flex;justify-content: space-around;align-items: center;position: relative;z-index: 5">
                            <heart-outlined  style="height: 33rem;width: 33rem;position: relative;z-index: 4" ></heart-outlined>
                        </div>
                        <div  style="position: absolute;width: 100rem;height: 33rem;right: -100rem;background-color: white;color: grey;font-size: 15rem;display: flex;justify-content: center;align-items: center;box-shadow: 0px 2rem 4rem grey;  z-index: 2;" id="myBrandCollectionInfDiv">
                            <span>店铺收藏</span>
                        </div>
                    </div>
                    <div @click="changeShowInf('myCollection')"  @mouseenter="showMyColletion" @mouseleave="hiedMyColletion" style="width: 33rem;height: 33rem;color: white;font-size: 25rem;justify-content: space-around;align-items: center;" id="myCollection">
                        <div style="position: absolute;width: 100rem;height: 33rem;background-color: white;color: grey;font-size: 15rem;display: flex;justify-content: center;align-items: center;box-shadow: 0px 2rem 4rem grey;right: -100rem;  z-index: 2;" id="myCollectionInfDiv"  >
                            <span>商品收藏</span>
                        </div>
                        <div style="width: 33rem;height: 33rem;color: white;font-size: 25rem;justify-content: space-around;align-items: center;z-index: 5">
                            <star-outlined></star-outlined>
                        </div>
                    </div>

                    <div @click="changeShowInf('myHistory')" @mouseenter="showMyHistory" @mouseleave="hiedMyHistory" style="width: 33rem;height: 33rem;color: white;font-size: 25rem;justify-content: space-around;align-items: center;z-index: 2;position: relative" id="myHistory">
                        <div  style="position: absolute;width: 100rem;height: 33rem;right: -100rem;background-color: white;color: grey;font-size: 15rem;display: flex;justify-content: center;align-items: center;box-shadow: 0px 2rem 4rem grey;z-index: 2;" id="myHistoryInfDiv">
                            <span>我的足迹</span>
                        </div>
                        <div style="width: 33rem;height: 33rem;color: white;font-size: 25rem;justify-content: space-around;align-items: center;z-index: 4;position: relative">
                          <history-outlined style="z-index: 4;position: relative"></history-outlined>
                        </div>
                    </div>
                </div>
        </div>
</template>

<script>
  import { ShoppingOutlined,HeartOutlined,StarOutlined,HistoryOutlined,ProfileOutlined,PayCircleOutlined } from '@ant-design/icons-vue';
  import {ref, inject} from 'vue';
  import showTypes from '../../views/inf/showTypes'
  import $ from 'jquery'
  import getGoodsCollectionList from "../../Internet/home/goodsCollection/select/getGoodsCollectionList";
  import store from "../../store/index"
  import getShoppingCartList from "../../assets/functions/getShoppingCartList";
  import selectByGoodsId from "../../Internet/home/goods/select/selectByGoodsId";
  import router from "../../router";
  import selectShopCollectionList from "../../Internet/home/shopCollection/select/selectShopCollectionList";
  export default {
    name: "showInRight",
    components:{
      ShoppingOutlined,
      HeartOutlined,
      StarOutlined,
      HistoryOutlined,
      ProfileOutlined,
      PayCircleOutlined,
      showTypes
    },

    setup() {

      let userlogo2 = require("../../assets/img/my_cnt_pic.jpg")

      let user1 = JSON.parse(sessionStorage.getItem("user"))

      if(user1!=null){
        if (user1.image !== null) {
          userlogo2 = require('../../assets/img/' + user1.image)
        }
        getShoppingCartList(user1.userId)

        getGoodsCollectionList(user1.userId).then(res=>{
          store.state.goodsCollectionList.list=res.data

          for(let i=0;i<store.state.goodsCollectionList.list.length;i++){
            store.state.goodsCollectionList.list[i].goodsImage1=require("../../assets/img/"+store.state.goodsCollectionList.list[i].goodsImage1)
          }
        })

        selectShopCollectionList(user1.userId).then(res=>{
          store.state.shopCollectionList.list=res.data
          for(let i=0;i<store.state.shopCollectionList.list.length;i++){
            store.state.shopCollectionList.list[i].shop.shopLogo=require("../../assets/img/"+store.state.shopCollectionList.list[i].shop.shopLogo)
          }
        })
      }


      let type1=ref('')


      let userLogo = require("../../assets/img/3f6d96131fd462118c191c644ca0e3dd_38x38_90.png")

      let isShowUserInf = ref(false)

      function changeShowUserInf() {
        isShowUserInf.value = !isShowUserInf.value
        let div = document.getElementById("userInfDiv")
        if(lastType!="myUserInf"){
          $("#showInfDiv").animate({right:"-274rem"},200);
          isShowUserInf.value = true
        }
        if (isShowUserInf.value == true) {
          lastType="myUserInf"
          div.style.backgroundColor = "rgb(244,51,153)"
          $("#showUserInf").animate({right:"33rem",visibility:"visible"},"fast");
        } else {
          lastType=''
          div.style.backgroundColor = "rgb(38,38,38)"
          $("#showUserInf").animate({right:"-274rem",visibility:"visible"},"fast");
        }
      }

      let showMyBrandCollectionInf = ref(false)
      let showMyCollectionInf = ref(false)
      let showMyHistoryInf = ref(false)
      let showMyShoppingInf=ref(false)

      function showMyColletion() {
        let div1 = document.getElementById("myCollection")
        div1.style.backgroundColor = "rgb(244,51,153)"
        $("#myCollectionInfDiv").animate({right:"33rem"},200);
      }

      function hiedMyColletion() {
        let div1 = document.getElementById("myCollection")
        div1.style.backgroundColor = "rgb(38,38,38)"
        // hide("myCollectionInfDiv",100,33)
        $("#myCollectionInfDiv").animate({right:"-100rem"},200);
      }

      function showMyBrandColletion() {
        let div = document.getElementById("myBrandCollection")
        div.style.backgroundColor = "rgb(244,51,153)"
        // show("myBrandCollectionInfDiv",100,33)
        $("#myBrandCollectionInfDiv").animate({right:"33rem"},200);
      }

      function hiedMyBrandColletion() {
        let div = document.getElementById("myBrandCollection")
        div.style.backgroundColor = "rgb(38,38,38)"
        $("#myBrandCollectionInfDiv").animate({right:"-100rem"},200);
      }

      function showMyHistory() {
        let div = document.getElementById("myHistory")
        div.style.backgroundColor = "rgb(244,51,153)"
        $("#myHistoryInfDiv").animate({right:"33rem"},200);
      }

      function hiedMyHistory() {
        let div = document.getElementById("myHistory")
        div.style.backgroundColor = "rgb(38,38,38)"
        $("#myHistoryInfDiv").animate({right:"-100rem"},200);
      }

      let lastType=''
      function changeShowInf(type){
        type1.value=type

        if(lastType==''){
          $("#showInfDiv").animate({right:"33rem"},200);
          lastType=type
        }else {
          if(lastType==type){
            $("#showInfDiv").animate({right:"-274rem"},200);
            lastType=''
          }else {
            if(lastType=="myUserInf"){
              $("#showUserInf").animate({right:"-274rem",visibility:"visible"},"fast");
              $("#showInfDiv").animate({right:"33rem"},200);
            }
            lastType=type
          }
        }
      }

      function selectGoods(goodsId){
        selectByGoodsId(goodsId).then(res=>{
          store.state.goods.data=res.data;
        })
      }


      let loginName = inject("loginName")


      let loginFlag = inject("loginFlag")

      function toCollectionPage() {
        changeShowInf(lastType)
        router.push('/mainHeader/mySale/collection')
      }

      function toBrowsePage() {
        changeShowInf(lastType)
        router.push('/mainHeader/mySale/browse')
      }

      function toShopCollectionPage() {
        changeShowInf(lastType)
        router.push('/mainHeader/mySale/shopCollection')
      }

      return {
        loginName,
        toCollectionPage,
        userLogo,
        userlogo2,
        isShowUserInf,
        changeShowUserInf,
        changeShowInf,
        loginFlag,
        showMyHistoryInf,
        showMyBrandCollectionInf,
        showMyCollectionInf,
        showMyShoppingInf,
        showMyBrandColletion,
        showMyColletion,
        showMyHistory,
        hiedMyBrandColletion,
        hiedMyColletion,
        hiedMyHistory,
        selectGoods,
        type1,
        store,
        toShopCollectionPage,
        toBrowsePage
      }
    },
    mounted() {
      // let docEl = document.documentElement;
      let height = window.innerHeight||Math.max(document.documentElement.clientHeight, document.body.clientHeight);

      let div=document.getElementById('showRightMain');

      if(div!==null){
        div.style.height=height+'px'
      }
    }
  }
</script>

<style scoped>
    #showUserInf{
        position: absolute;
        width: 274rem;
        height: 160rem;
        right: -274rem;
        z-index: 3;
        font-size: 12rem;
        background-color: white;
        border-top-left-radius: 5rem;
        border-bottom-left-radius: 5rem;
        box-shadow: 0px 2rem 4rem grey;
        border: 0.1rem lightgrey solid;
    }
</style>