<template>
    <div class="mainDiv" style="width: 100%;display: flex;height: 43rem;background-color: white;justify-content: center;flex-wrap: wrap;align-items: center;box-shadow: 0 1rem 2rem lightgrey">
        <div  style="width: 1000rem;height: 43rem;display: flex;justify-content: space-between;align-items: flex-start;z-index:2;position: relative;flex-wrap: wrap">
            <div  style="width: 1000rem;height: 43rem;display: flex;justify-content: space-between;align-items: flex-start;z-index:2;position: relative;flex-wrap: wrap">
                <div @click="showTypesDiv"  style="cursor: pointer;width:156rem;height:43rem;background-color: rgb(244,51,153);display: flex;justify-content: center;align-items: center;color: white;font-size: 16rem">
                    商品分类
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    推荐
                </div>
                <div style="width:84rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    大牌疯抢
                </div>
                <div style="width:84rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    唯品快抢
                </div>
                <div style="width:84rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    唯品奥莱
                </div>
                <div style="width:84rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    品牌清仓
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    美妆
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    女装
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    首饰
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    电器
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    鞋包
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    运动
                </div>
                <div style="width:52rem;height: 43rem;display: flex;justify-content: center;align-items: center;font-size:16rem;padding: 10rem">
                    更多
                </div>
            </div>

            <div class="showTypesDiv" style="visibility: hidden;position: absolute;top: 43rem;height:auto;width: auto;display: flex;align-items: flex-start;justify-content: space-between;">
                <div style="width: auto;height: auto;display: flex;flex-direction: column;padding-bottom: 78rem;background-color: rgb(244,51,153);">
                    <div class="firstTypes" @mouseenter="chooseTypes(index)" v-for="(item,index) in firstTypesList.list" :key="index" style="width: 156rem;height: 43rem;display: flex;justify-content: center;align-items: center;color: white;font-size: 12rem">
                        {{item.type}}
                    </div>
                </div>

                <div v-if="types.list.length!=0" style="height: auto;width: 874rem;padding-left: 20rem;padding-right: 20rem;background-color:white;box-shadow: 0 1rem 2rem lightgrey">
                    <div style="width: 844rem;height: 508rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;padding-left: 10rem">
                        <div v-for="(item,index) in types.list" :key="index" style="width: 100%;display: flex;align-items: flex-start;justify-content: flex-start;margin-top: 10rem;font-size: 12rem;color: black;">
                            <div style="width: 70rem;display: flex;height: auto;justify-content: flex-start;font-weight: bold">
                                <span> {{item.secondType.type}}</span>
                            </div>
                            <span>></span>
                            <div  style="width: 704rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
                                <div  v-for="(item1,index) in item.thirdTypes" :key="index" style="width: auto;padding-left: 15rem;padding-right: 15rem;height: 25rem;display: flex;justify-content: center;color: grey;font-size: 12rem">
                                    <router-link @click="selectGoods(item1.id)" :to="{path:'/mainHeader/searchedGoodsByType',query: {title: item1.type,typeId:item1.id}}" style="color: grey">{{item1.type}}</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
  import {reactive} from "@vue/reactivity";
  import $ from 'jquery'
  import getTypes from "../../Internet/home/type/getTypes";
  import getFirstLevelType from "../../Internet/home/type/getFirstLevelType";
  import selectGoodsByTypeId from "../../Internet/home/goods/select/selectByTypeId";
  import store from "../../store/index"

  export default {
    name: "showTypes",
    setup(){
      let firstTypesList=reactive({
        list:[]
      })

      getFirstLevelType().then(res=>{
        firstTypesList.list=res.data
      })



     function showTypesDiv(){
       console.log(document.getElementsByClassName('showTypesDiv')[0].style.visibility=='hidden');
       if(document.getElementsByClassName('showTypesDiv')[0].style.visibility=='hidden'){
         document.getElementsByClassName('showTypesDiv')[0].style.visibility='visible'
       }else {
         $(".showTypesDiv").slideToggle(500)
       }

     }

     function hideTypesDiv() {
       $(".showTypesDiv").eq(0).slideUp(300)
     }

     let types=reactive({
       list:[]
     })

      let allTypes= []
      getTypes().then(res=>{

        allTypes=res.data.data
      })


      let lastIndex=''

      async function chooseTypes(index) {

        types.list=[]



        if(lastIndex!==index){
          for (let i = 0; i < 10; i++) {

            if(allTypes[i].list[0].secondType.fatherId==firstTypesList.list[index].id){
              types.list=allTypes[i].list
            }

            if (i !== index) {
              $(".firstTypes").eq(i).css('color', 'white')
              $(".firstTypes").eq(i).css('background-color', 'rgb(244,51,153)')
            } else {
              $(".firstTypes").eq(i).css('background-color', 'white')
              $(".firstTypes").eq(i).css('color', 'rgb(244,51,153)')
            }
          }

          lastIndex=index
        }
      }




      function selectGoods(typeId){
        selectGoodsByTypeId(unescape(typeId)).then(res => {
          store.state.page.data=res.data
          store.state.searchedGoods.list=res.data.records;

          for(let i=0;i<store.state.searchedGoods.list.length;i++){
            store.state.searchedGoods.list[i].goodsImage1=require("../../assets/img/"+store.state.searchedGoods.list[i].goodsImage1);
          }
        })
      }


      return{
       firstTypesList,
        showTypesDiv,
        hideTypesDiv,
        chooseTypes,
        types,
        selectGoods
      }
    }
  }
</script>

<style scoped>
</style>