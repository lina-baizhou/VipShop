<template>
    <div id="main" style="display: flex;flex-wrap: wrap;justify-content: center">
        <top-header></top-header>
        <div class="mainDiv" style="width: 100%;display: flex;height: auto;background-color: white;justify-content: space-evenly;align-items: center;box-shadow: lightgrey">
              <div style="width: 1000rem;height: 100rem;display: flex;justify-content: space-between;align-items: center;position: relative;z-index:1;">
                <img :src="logo" style="max-height: 100rem">

                <div style="width: 320rem;height: 36rem;border: 2rem rgb(244,51,153) solid;display: flex;justify-content: space-evenly;align-items: center">
                    <select v-model="store.state.searchFlag" style="height: 100%;outline: none;border: none;background-color: rgb(245,245,245);border-right: 1rem solid rgb(229,229,229)">
                        <option style="padding-top: 10rem;padding-bottom: 10rem;height: 100%" value="goods" selected>宝贝</option>
                        <option  style="padding-top: 10rem;padding-bottom: 10rem;height: 100%" value="shop">店铺</option>
                    </select>
                    <input v-model="store.state.searchItem" style="width: 250rem;margin-left: 5rem;border: 0rem;outline: none;font-size: 15rem;" placeholder="请输入要搜索的词">
                    <div @click="search" style="cursor: pointer;font-size:20rem;width: 56rem;height:100%;background-color: rgb(244,51,153);color: white;display: flex;justify-content: space-evenly;align-items: center">
                        <SearchOutlined></SearchOutlined>
                    </div>
                </div>


                <div style="width: 96rem;height: 34rem;background-color: whitesmoke;border: 0.1rem darkgrey solid;display: flex;justify-content: space-evenly;align-items: center;position: relative;">
                    <shopping-outlined style="color: grey;font-size: 15rem"></shopping-outlined>
                    <span id="shopingFont" style="font-size: 12rem">购物袋</span>
                    <span style="border-radius: 50rem;background-color: rgb(244,51,153);color: white;width: 30rem;height: 20rem;display: flex;justify-content: space-evenly;align-items: center;font-size: 15rem">0</span>
                </div>
            </div>
        </div>
          <router-view></router-view>
    </div>
</template>

<script>
  import { ShoppingOutlined,SearchOutlined} from '@ant-design/icons-vue';
  import topHeader from "./topHeader";
  import {ref,provide} from 'vue';
  import searchGoodsTypes from "../../Internet/home/goods/select/searchGoodsTypes";
  import searchGoods from "../../Internet/home/goods/select/searchGoods";
  import searchShop from "../../Internet/home/shop/find/searchShop";
  import store from '../../store/index'
  import router from "../../router";

  // import autoLogin from "../../Internet/home/user/login/autoLogin";

  let loginName=ref('')
  export default {
    name: "headInf",
    components:{
      ShoppingOutlined,
      SearchOutlined,
      topHeader,
    },
    setup(){
      let logo=require("../../assets/img/2021-07-18_102326.png")

      let loginFlag=ref(true)

      //给子组件传值
      provide("loginFlag",loginFlag)

      provide("loginName",loginName)

      // //自动登录
      // new Promise((resolve)=>{
      //   resolve(
      //       autoLogin().then(res=>{
      //         console.log(res);
      //         sessionStorage.setItem('user',JSON.stringify(res.data));
      //       })
      //   )
      // }).then(()=>{
      //
      //

      let user1=JSON.parse(sessionStorage.getItem("user"))

      if(user1!==null&&user1!==undefined){
          loginFlag.value=false

          if(user1.username!==null){
            loginName.value=user1.username
          }else {
            //中间4位加密显示出来
            let result=/^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
            loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
          }
        }else {
          loginFlag.value=true
        }
      // })




      function  search(){
        if(store.state.searchFlag=='goods'){
          searchGoodsTypes(store.state.searchItem).then(res=>{
            store.state.searchedTypes.list=res.data
            searchGoods(0,0,store.state.searchItem,0,0,1,1).then(res=>{
              store.state.searchedGoodsBySearchItem.list=res.data.records
              store.state.page.data=res.data
              for(let i=0;i<store.state.searchedGoodsBySearchItem.list.length;i++){
                store.state.searchedGoodsBySearchItem.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoodsBySearchItem.list[i].goodsImage1)
              }
              sessionStorage.setItem('searchItem',store.state.searchItem)
              router.push({path:'/mainHeader/searchedGoods',params:{'searchedItem':store.state.searchItem}})
            })
          })

        }else {
          searchShop(store.state.searchItem,1,'').then(res=>{
            store.state.searchedShops.list=res.data.data
            for(let i=0;i<store.state.searchedShops.list.length;i++){
              if(store.state.searchedShops.list[i].goodsList.length>5){
                store.state.searchedShops.list[i].goodsList.splice(5,store.state.searchedShops.list[i].goodsList.length-4)
              }
              store.state.searchedShops.list[i].shop.shopLogo=require("../../assets/img/"+store.state.searchedShops.list[i].shop.shopLogo)
              for(let j=0;j<store.state.searchedShops.list[i].goodsList.length;j++){
                store.state.searchedShops.list[i].goodsList[j].goodsImage1=require("../../assets/img/"+store.state.searchedShops.list[i].goodsList[j].goodsImage1)
              }
            }
            store.state.page.data=res.data.page
            sessionStorage.setItem('searchItem',store.state.searchItem)
            router.push({path:'/mainHeader/searchedShops',params:{'searchedItem':store.state.searchItem}})
          })
        }
      }

      return{
        loginFlag,
        loginName,
        logo,
        search,
        store
      };
    },
    created() {
      window.addEventListener('setItem', ()=> {
        this.newVal = sessionStorage.getItem('user');
        loginName.value=(JSON.parse(sessionStorage.getItem("user")).username)
      })
    }
  }
</script>

<style scoped>


    /* 清除浏览器默认边距，
    使边框和内边距的值包含在元素的width和height内 */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    html{
        font-size:1px;
    }
    #main{
        width: 100%;
        height: auto;
    }
</style>