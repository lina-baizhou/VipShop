<template>
    <div class="mainDiv" style="width: 100%;display: flex;height: auto;background-color: white;justify-content: center;flex-wrap: wrap;align-items: center;">
       <show-in-right></show-in-right>
        <div style="width: 1000rem;height:auto;margin-top: 20rem;display: flex;flex-wrap: wrap;padding-bottom: 20rem">

            <div style="width: 100%;height: 48rem;background-color: whitesmoke;border:1rem lightgrey solid;display: flex;justify-content: flex-start;align-items: flex-start">
                <div class="chooseItem" @click="chooseSelectItem(0)" style="width: 70rem;height: 100%;background-color: white;border:1rem rgb(242,2,128) solid;color: rgb(242,2,128);display: flex;justify-content: center;align-items: center;font-size: 14rem">
                    综合
                </div>
                <div class="chooseItem" @click="chooseSelectItem(1)" style="width: 70rem;height: 100%;border-right:1rem lightgrey solid;display: flex;justify-content: center;align-items: center;font-size: 14rem">
                    销量
                </div>
                <div class="chooseItem" @click="chooseSelectItem(2)" style="width: 70rem;height: 100%;border-right:1rem lightgrey solid;display: flex;justify-content: center;align-items: center;font-size: 14rem">
                    价格
                    <div style="margin-left: 5rem;width: auto;height: 100%;display: flex;flex-direction: column;justify-content: center;font-size: 10rem">
                        <up-outlined v-if="upFlag"></up-outlined>
                        <DownOutlined v-if="downFlag"></DownOutlined>
                    </div>

                </div>
                <div style="width: 790rem;height: 100%;display: flex;justify-content: space-between;align-items: center;font-size: 14rem">
                    <div style="width: 300rem;height: 100%;display: flex;justify-content:center;align-items: center;background: white;border-right:1rem lightgrey solid;">
                       <div style="width: 70rem;height: 25rem;border: 1rem darkgrey solid;background-color: white;display: flex;padding-left: 5rem;padding-right: 5rem;display: flex;justify-content: space-between;align-items: center">
                           ¥
                           <input  v-model="minPrice" :oninput="checkMinValue" type="text" style="border: none;width: 50rem;height: 20rem;outline: none">
                       </div>
                        <span style="width: 30rem;height: auto;display: flex;justify-content: center">-</span>
                        <div style="width: 70rem;height: 25rem;border: 1rem darkgrey solid;background-color: white;display: flex;padding-left: 5rem;padding-right: 5rem;display: flex;justify-content: space-between;align-items: center">
                            ¥
                            <input v-model="maxPrice" :oninput="checkMaxValue" type="text" style="border: none;width: 50rem;height: 20rem;outline: none">
                        </div>
                        <button  @click="fix" style="width: auto;height: 25rem;border: none;border-radius: 2rem;background: rgb(242,2,128);margin-left: 15rem;color: white;cursor: pointer">确定</button>
                        <button @click="clear" style="width: auto;height: 25rem;border: none;border-radius: 2rem;background: rgb(242,2,128);margin-left: 15rem;color: white;cursor: pointer">清除</button>
                    </div>
                    <div style="width: 240rem;height: 100%;display: flex;justify-content:space-between;align-items: center;border-right:1rem lightgrey solid;">
                        <div style="width:auto;height: 100%;display: flex;align-items: center">
                            <span style="color:rgb(242,2,128)">{{store.state.page.data.total}}</span>
                            件商品
                        </div>
                        <div style="width: auto;height: 100%;display: flex;align-items: center;justify-content: space-between">
                            <span style="color:rgb(242,2,128);margin-left: 10rem">{{store.state.page.data.current}}</span>/{{store.state.page.data.pages}}
                            <button class="pageBtn" @click="changePage(0)"  style="cursor: pointer;height: 25rem;border-radius: 3rem;border: 1rem darkgrey solid;color: darkgrey;background: white;"><left-outlined></left-outlined></button>
                            <button class="pageBtn" @click="changePage(1)" style="cursor: pointer;height: 25rem;border-radius: 3rem;;margin-left: 10rem;border: 1rem darkgrey solid;color: darkgrey;background: white;margin-right: 10rem">下一页<right-outlined></right-outlined></button>
                        </div>
                    </div>
                </div>
            </div>

            <div style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
                <div v-for="(item,index) in  store.state.searchedGoods.list" :key="index" style="width: 25%;height: auto;display: flex;justify-content: center">
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
</template>

<script>

  import ShowInRight from "../../views/inf/showInRight";
  import Goods from '../../views/goods/Goods'
  import {DownOutlined,LeftOutlined,RightOutlined,UpOutlined} from "@ant-design/icons-vue"
  import store from "../../store/index"
  import selectGoodsByTypeId from "../../Internet/home/goods/select/selectByTypeId";
  import {ref} from "@vue/reactivity";
  import selectGoodsByTypeIdAndPriceRange from "../../Internet/home/goods/select/seletcByTypeIdAndPriceRange";
  import $ from 'jquery'
  export default {
    name: "SearchedGoodsByType",
    components: {
      ShowInRight,
      DownOutlined,
      LeftOutlined,
      RightOutlined,
      UpOutlined,
      Goods
    },
    setup() {

      let search = search ||  window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg = new RegExp("(^|&)"+ 'typeId' +"=([^&]*)(&|$)");
      let r = search.match(reg);


      selectGoods(unescape(r[2]))

      function selectGoods(typeId){
        selectGoodsByTypeId(unescape(typeId)).then(res => {

          store.state.page.data=res.data
          store.state.searchedGoods.list=res.data.records;

          for(let i=0;i<store.state.searchedGoods.list.length;i++){
            store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
          }
        })
      }

      let minPrice=ref('')
      let maxPrice=ref('')

      let currentPage=1

      let oldMinValue=''
      function checkMinValue(){
        if(minPrice.value!=parseInt(minPrice.value)&&minPrice.value!=''){
          minPrice.value=oldMinValue
        }
        if(minPrice.value!=''){
          minPrice.value=parseInt(minPrice.value)
        }
        oldMinValue=minPrice.value
      }

      let oldMaxValue=''
      function checkMaxValue(){
        if(maxPrice.value!=parseInt(maxPrice.value)&&maxPrice.value!=''){
          maxPrice.value=oldMaxValue
        }
        if(maxPrice.value!=''){
          maxPrice.value=parseInt(maxPrice.value)
        }
        oldMaxValue=maxPrice.value
      }

      //清除
      function  clear() {
        currentPage=1
        minPrice.value=''
        maxPrice.value=''
        oldMaxValue=''
        oldMinValue=''

        selectGoodsByTypeIdAndPriceRange(0,0,unescape(r[2]),lastColumn,lastFlag,currentPage).then(res=>{
          store.state.page.data=res.data
          store.state.searchedGoods.list=res.data.records;

          for(let i=0;i<store.state.searchedGoods.list.length;i++){
            store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
          }
        })
      }

      let lastColumn='goodsSalesVolumn'
      let lastFlag='1'

      function fix() {
        currentPage=1
        if(maxPrice.value==''){
          maxPrice.value=0
        }
        if(minPrice.value==''){
          minPrice.value=0
        }

        if(Number(minPrice.value)>Number(maxPrice.value)){
          let temp=minPrice.value
          minPrice.value=maxPrice.value
          maxPrice.value=temp

          oldMaxValue=maxPrice.value
          oldMinValue=minPrice.value
        }

        selectGoodsByTypeIdAndPriceRange(minPrice.value,maxPrice.value,unescape(r[2]),lastColumn,lastFlag,currentPage).then(res=>{
          store.state.page.data=res.data
          store.state.searchedGoods.list=res.data.records;

          for(let i=0;i<store.state.searchedGoods.list.length;i++){
            store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
          }
        })

        if(minPrice.value==0&&maxPrice.value==0){
          maxPrice.value=''
          minPrice.value=''
        }

      }

      let lastIndex=''

      let upFlag=ref(true)
      let downFlag=ref(true)
      function chooseSelectItem(index){
        currentPage=1
        if(maxPrice.value==''){
          maxPrice.value=0
        }
        if(minPrice.value==''){
          minPrice.value=0
        }

        if(Number(minPrice.value)>Number(maxPrice.value)){
          let temp=minPrice.value
          minPrice.value=maxPrice.value
          maxPrice.value=temp

          oldMaxValue=maxPrice.value
          oldMinValue=minPrice.value
        }

        if(index==2){
          if(lastIndex!=index){
            //升序
            downFlag.value=false

            lastFlag=1
            lastColumn='goodsPrice'
            selectGoodsByTypeIdAndPriceRange(minPrice.value,maxPrice.value,unescape(r[2]),'goodsPrice',1,currentPage).then(res=>{
              store.state.page.data=res.data
              store.state.searchedGoods.list=res.data.records;

              for(let i=0;i<store.state.searchedGoods.list.length;i++){
                store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
              }
            })
          }else {
            if(downFlag.value==true){//根据价格升序
              downFlag.value=false
              upFlag.value=true

              lastFlag=1
              lastColumn='goodsPrice'

              selectGoodsByTypeIdAndPriceRange(minPrice.value,maxPrice.value,unescape(r[2]),'goodsPrice',1,currentPage).then(res=>{
                store.state.page.data=res.data
                store.state.searchedGoods.list=res.data.records;

                for(let i=0;i<store.state.searchedGoods.list.length;i++){
                  store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
                }
              })
            }else {//根据价格降序
              downFlag.value=true
              upFlag.value=false

              lastFlag=0
              lastColumn='goodsPrice'

              selectGoodsByTypeIdAndPriceRange(minPrice.value,maxPrice.value,unescape(r[2]),'goodsPrice',0,currentPage).then(res=>{
                store.state.page.data=res.data
                store.state.searchedGoods.list=res.data.records;

                for(let i=0;i<store.state.searchedGoods.list.length;i++){
                  store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
                }
              })
            }
          }
        }else {
          downFlag.value=true
          upFlag.value=true
        }

        //根据销量排序
        if(index==1){
          lastFlag=0
          lastColumn='goodsSalesVolumn'
          selectGoodsByTypeIdAndPriceRange(minPrice.value,maxPrice.value,unescape(r[2]),'goodsSalesVolumn',0,currentPage).then(res=>{
            store.state.page.data=res.data
            store.state.searchedGoods.list=res.data.records;

            for(let i=0;i<store.state.searchedGoods.list.length;i++){
              store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
            }
          })
        }

        if(index==0){
          lastFlag=0
          lastColumn=''
          selectGoodsByTypeIdAndPriceRange(minPrice.value,maxPrice.value,unescape(r[2]),'',0,currentPage).then(res=>{
            store.state.page.data=res.data
            store.state.searchedGoods.list=res.data.records;

            for(let i=0;i<store.state.searchedGoods.list.length;i++){
              store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
            }
          })
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

          lastIndex=index
        }

        if(minPrice.value==0&&maxPrice.value==0){
          maxPrice.value=''
          minPrice.value=''
        }

      }

      function changePage(index) {
        if(maxPrice.value==''){
          maxPrice.value=0
        }
        if(minPrice.value==''){
          minPrice.value=0
        }

        if(Number(minPrice.value)>Number(maxPrice.value)){
          let temp=minPrice.value
          minPrice.value=maxPrice.value
          maxPrice.value=temp

          oldMaxValue=maxPrice.value
          oldMinValue=minPrice.value
        }

        if(index==0) {//上一页
          if (currentPage != 1) {
            currentPage--

            $(".pageBtn").eq(index).css('color', 'rgb(242,2,128)')
            $(".pageBtn").eq(index).css('border', '1rem solid rgb(242,2,128)')
            setTimeout(() => {
              $(".pageBtn").eq(index).css('color', 'darkgrey')
              $(".pageBtn").eq(index).css('border', '1rem solid darkgrey')
            }, 200)

            selectGoodsByTypeIdAndPriceRange(minPrice.value, maxPrice.value, unescape(r[2]), lastColumn, lastFlag, currentPage).then(res => {
              store.state.page.data = res.data
              store.state.searchedGoods.list = res.data.records;

              for (let i = 0; i < store.state.searchedGoods.list.length; i++) {
                store.state.searchedGoods.list[i].goodsImage1 = require("../../assets/img/" + store.state.searchedGoods.list[i].goodsImage1);
              }
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


              selectGoodsByTypeIdAndPriceRange(minPrice.value,maxPrice.value,unescape(r[2]),lastColumn,lastFlag,currentPage).then(res=>{
                store.state.page.data=res.data
                store.state.searchedGoods.list=res.data.records;

                for(let i=0;i<store.state.searchedGoods.list.length;i++){
                  store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
                }
              })
            }
          }
        if(minPrice.value==0&&maxPrice.value==0){
          maxPrice.value=''
          minPrice.value=''
        }
      }

      return {
        store,
        minPrice,
        maxPrice,
        checkMinValue,
        checkMaxValue,
        clear,
        fix,
        chooseSelectItem,
        upFlag,
        downFlag,
        changePage
      }
    }
  }
</script>

<style scoped>

</style>