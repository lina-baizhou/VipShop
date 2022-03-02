<template>
    <div class="mainDiv" style="width: 100%;display: flex;height: auto;background-color: white;justify-content: center;flex-wrap: wrap;align-items: center;">
        <show-in-right></show-in-right>
        <div style="width: 1000rem;height: 48rem;background-color: whitesmoke;border:1rem lightgrey solid;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
            <div class="chooseItem" @click="chooseSelectItem(0)"  style="width: 70rem;height: 100%;background-color: white;border:1rem rgb(242,2,128) solid;color: rgb(242,2,128);display: flex;justify-content: center;align-items: center;font-size: 14rem">
                默认
            </div>
            <div class="chooseItem" @click="chooseSelectItem(1)"  style="width: 70rem;height: 100%;border-right:1rem lightgrey solid;display: flex;justify-content: center;align-items: center;font-size: 14rem">
                评分
            </div>
            <div class="chooseItem" @click="chooseSelectItem(2)"  style="width: 70rem;height: 100%;border-right:1rem lightgrey solid;display: flex;justify-content: center;align-items: center;font-size: 14rem">
                销量
            </div>
            <div style="width: 790rem;height: 100%;display: flex;justify-content:flex-end;align-items: center;border-right:1rem lightgrey solid;">
                    <div style="width:auto;height: 100%;display: flex;align-items: center">
                        <span style="color:rgb(242,2,128)">{{store.state.page.data.total}}</span>
                        个店铺
                    </div>
                    <div style="width: auto;height: 100%;display: flex;align-items: center;justify-content: space-between">
                        <span style="color:rgb(242,2,128);margin-left: 10rem">{{store.state.page.data.current}}</span>/{{store.state.page.data.pages}}
                        <button @click="changePage(0)" class="pageBtn"   style="cursor: pointer;height: 25rem;border-radius: 3rem;border: 1rem darkgrey solid;color: darkgrey;background: white;"><left-outlined></left-outlined></button>
                        <button @click="changePage(1)" class="pageBtn"  style="cursor: pointer;height: 25rem;border-radius: 3rem;;margin-left: 10rem;border: 1rem darkgrey solid;color: darkgrey;background: white;margin-right: 10rem">下一页<right-outlined></right-outlined></button>
                    </div>
            </div>
        </div>
        <div style="width: 1000rem;height: auto;display: flex;justify-content: center" class="mainDiv">
            <div style="width: 1000rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap;margin: 20rem 0rem">
                <div v-for="(item,index) in store.state.searchedShops.list" :key="index" style="width: 100%;height: 319rem;display: flex;padding: 44rem 0rem 40rem;justify-content: space-between;flex-wrap: wrap;align-items: center;border-bottom: 1px solid #e9e9e9;">
                    <router-link :to="{path:'/mainHeader/searchedShopByShopId',query: {shopId:item.shop.shopId}}" style="width: 100%;height: auto;display: flex;justify-content: flex-end;color: #333333;font-weight: 400;font-size: 16rem;margin-bottom: 20rem">
                        查看全部商品>
                    </router-link>
                    <div style="width: 150rem;height: 204rem;display: flex;flex-direction: column;justify-content: center;align-items: flex-start">
                        <div style="width: 80rem;height: 80rem;display: flex;justify-content: center;align-items: center;border: 1px solid #d9d9d9;">
                            <img :src="item.shop.shopLogo" style="width: 66rem;height: 66rem">
                        </div>
                        <div style="color: black;font-weight: 400;font-size: 20rem;margin-top: 10rem">
                            {{item.shop.shopName}}
                        </div>
                    </div>
                    <div style="width: 850rem;height: 180rem;display: flex;justify-content: space-between;align-items: center">
                        <div v-for="(item1,index1) in item.goodsList" :key="index1" style="width: 160rem;height: 173rem;padding-left: 20rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                            <div style="width: 152rem;height: 151rem;display: flex;justify-content: center;align-items: center;border: 1px solid #d9d9d9;">
                                <img :src="item1.goodsImage1" style="width: 148rem;height: 148rem">
                            </div>
                            <div style="width: 152rem;height: 21rem;display: flex;justify-content: center;align-items: center">
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
  import ShowInRight from "../../views/inf/showInRight";
  import store from "../../store/index"
  import searchShop from "../../Internet/home/shop/find/searchShop";
  import $ from 'jquery'
  export default {
    name: "searchedShop",
    components:{
      ShowInRight
    },
    setup() {
      if(store.state.searchItem==null||store.state.searchItem==''||store.state.searchItem==undefined) {
        store.state.searchItem=sessionStorage.getItem("searchItem")
        store.state.searchFlag='shop'
        searchShop(store.state.searchItem, 1, '').then(res => {
          store.state.searchedShops.list = res.data.data
          for (let i = 0; i < store.state.searchedShops.list.length; i++) {
            if (store.state.searchedShops.list[i].goodsList.length > 5) {
              store.state.searchedShops.list[i].goodsList.splice(5, store.state.searchedShops.list[i].goodsList.length - 4)
            }
            store.state.searchedShops.list[i].shop.shopLogo = require("../../assets/img/" + store.state.searchedShops.list[i].shop.shopLogo)
            for (let j = 0; j < store.state.searchedShops.list[i].goodsList.length; j++) {
              store.state.searchedShops.list[i].goodsList[j].goodsImage1 = require("../../assets/img/" + store.state.searchedShops.list[i].goodsList[j].goodsImage1)
            }
          }
          store.state.page.data = res.data.page
        })
      }

      let lastIndex=0
      let orderItem=''
      function chooseSelectItem(index) {
        if(lastIndex!==index){
          if(index==0){
            orderItem=''
            searchShop(store.state.searchItem, 1, '').then(res => {
              store.state.searchedShops.list = res.data.data
              for (let i = 0; i < store.state.searchedShops.list.length; i++) {
                if (store.state.searchedShops.list[i].goodsList.length > 5) {
                  store.state.searchedShops.list[i].goodsList.splice(5, store.state.searchedShops.list[i].goodsList.length - 4)
                }
                store.state.searchedShops.list[i].shop.shopLogo = require("../../assets/img/" + store.state.searchedShops.list[i].shop.shopLogo)
                for (let j = 0; j < store.state.searchedShops.list[i].goodsList.length; j++) {
                  store.state.searchedShops.list[i].goodsList[j].goodsImage1 = require("../../assets/img/" + store.state.searchedShops.list[i].goodsList[j].goodsImage1)
                }
              }
              store.state.page.data = res.data.page
            })
          }else if(index==1){
            orderItem='shopCount'
            searchShop(store.state.searchItem, 1, 'shopCount').then(res => {
              store.state.searchedShops.list = res.data.data
              for (let i = 0; i < store.state.searchedShops.list.length; i++) {
                if (store.state.searchedShops.list[i].goodsList.length > 5) {
                  store.state.searchedShops.list[i].goodsList.splice(5, store.state.searchedShops.list[i].goodsList.length - 4)
                }
                store.state.searchedShops.list[i].shop.shopLogo = require("../../assets/img/" + store.state.searchedShops.list[i].shop.shopLogo)
                for (let j = 0; j < store.state.searchedShops.list[i].goodsList.length; j++) {
                  store.state.searchedShops.list[i].goodsList[j].goodsImage1 = require("../../assets/img/" + store.state.searchedShops.list[i].goodsList[j].goodsImage1)
                }
              }
              store.state.page.data = res.data.page
            })
          }else if(index==2){
            orderItem='salesCount'
            searchShop(store.state.searchItem, 1, 'salesCount').then(res => {
              store.state.searchedShops.list = res.data.data
              for (let i = 0; i < store.state.searchedShops.list.length; i++) {
                if (store.state.searchedShops.list[i].goodsList.length > 5) {
                  store.state.searchedShops.list[i].goodsList.splice(5, store.state.searchedShops.list[i].goodsList.length - 4)
                }
                store.state.searchedShops.list[i].shop.shopLogo = require("../../assets/img/" + store.state.searchedShops.list[i].shop.shopLogo)
                for (let j = 0; j < store.state.searchedShops.list[i].goodsList.length; j++) {
                  store.state.searchedShops.list[i].goodsList[j].goodsImage1 = require("../../assets/img/" + store.state.searchedShops.list[i].goodsList[j].goodsImage1)
                }
              }
              store.state.page.data = res.data.page
            })
          }
        }

        for(let i=0;i<3;i++){
          if(i==index){
            $('.chooseItem').eq(index).css('border','1rem rgb(242,2,128) solid')
            $('.chooseItem').eq(index).css('color','rgb(242,2,128)')
            $('.chooseItem').eq(index).css('background-color', 'white')
          }else {
            $('.chooseItem').eq(i).css('border','none')
            $('.chooseItem').eq(i).css('border-right','1rem darkgrey solid')
            $('.chooseItem').eq(i).css('color','black')
            $('.chooseItem').eq(i).css('background-color', 'whitesmoke')
          }
        }

        lastIndex=index
      }


      let currentPage=1

      function changePage(index) {
        if(index==0) {//上一页
          if (currentPage != 1) {
            currentPage--
            $(".pageBtn").eq(index).css('color', 'rgb(242,2,128)')
            $(".pageBtn").eq(index).css('border', '1rem solid rgb(242,2,128)')
            setTimeout(() => {
              $(".pageBtn").eq(index).css('color', 'darkgrey')
              $(".pageBtn").eq(index).css('border', '1rem solid darkgrey')
            }, 200)

            searchShop(store.state.searchItem, currentPage-1, orderItem).then(res => {
              store.state.searchedShops.list = res.data.data
              for (let i = 0; i < store.state.searchedShops.list.length; i++) {
                if (store.state.searchedShops.list[i].goodsList.length > 5) {
                  store.state.searchedShops.list[i].goodsList.splice(5, store.state.searchedShops.list[i].goodsList.length - 4)
                }
                store.state.searchedShops.list[i].shop.shopLogo = require("../../assets/img/" + store.state.searchedShops.list[i].shop.shopLogo)
                for (let j = 0; j < store.state.searchedShops.list[i].goodsList.length; j++) {
                  store.state.searchedShops.list[i].goodsList[j].goodsImage1 = require("../../assets/img/" + store.state.searchedShops.list[i].goodsList[j].goodsImage1)
                }
              }
              store.state.page.data = res.data.page
            })
          }
        }else {//下一页
          if(currentPage<store.state.page.data.pages){
            currentPage++
            $(".pageBtn").eq(index).css('color','rgb(242,2,128)')
            $(".pageBtn").eq(index).css('border','1rem solid rgb(242,2,128)')
            setTimeout(()=>{
              $(".pageBtn").eq(index).css('color','darkgrey')
              $(".pageBtn").eq(index).css('border','1rem solid darkgrey')
            },200)

            searchShop(store.state.searchItem, currentPage-1, orderItem).then(res => {
              store.state.searchedShops.list = res.data.data
              for (let i = 0; i < store.state.searchedShops.list.length; i++) {
                if (store.state.searchedShops.list[i].goodsList.length > 5) {
                  store.state.searchedShops.list[i].goodsList.splice(5, store.state.searchedShops.list[i].goodsList.length - 4)
                }
                store.state.searchedShops.list[i].shop.shopLogo = require("../../assets/img/" + store.state.searchedShops.list[i].shop.shopLogo)
                for (let j = 0; j < store.state.searchedShops.list[i].goodsList.length; j++) {
                  store.state.searchedShops.list[i].goodsList[j].goodsImage1 = require("../../assets/img/" + store.state.searchedShops.list[i].goodsList[j].goodsImage1)
                }
              }
              store.state.page.data = res.data.page
            })
          }
        }
      }

      return{
        store,
        chooseSelectItem,
        changePage,
      }
    }
  }
</script>

<style scoped>

</style>