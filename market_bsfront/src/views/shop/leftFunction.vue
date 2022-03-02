<template>
        <div style="width: 200rem;height: 551.3333129882812rem;background-color: white;position: fixed;left: 0px;top: 36rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;font-size: 14rem;">
            <router-link to="/shop/shopHome"  style="color: #333333;width: 185rem;height: 40rem;border-bottom: 1.5rem lightgrey solid;display: flex;justify-content: space-between;align-items: center">
                后台首页
            </router-link>
            <div style="width: 185rem;height: auto;border-bottom: 1.5rem lightgrey solid;display: flex;flex-direction: column;flex-wrap: wrap;justify-content: space-evenly;align-items: flex-start;margin-top: 10rem;padding-bottom: 10rem">
                <div @click="revolveTo(0)" style="width: 100%;height: 30rem;display: flex;justify-content: space-between;align-items: center">
                    <div class="left_shop_inf">
                        <img :src="logo1" style="width: 20rem;height: 20rem">
                        <span>商品管理</span>
                    </div>
                    <span class="right_shop_inf"><up-outlined></up-outlined></span>
                </div>
                <div class="functionItems" style="width: 160rem;height: auto;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start;font-size: 12rem">
                    <div style="width: 100%;height: 30rem;display: flex;justify-content: space-between;color: grey">
                        <router-link to="/shop/goodsTable" style="color: grey">商品列表</router-link>
                        <span><router-link to="/shop/releaseGoods" style="color: grey">发布新商品</router-link></span>
                    </div>
                </div>
            </div>

            <div style="width: 185rem;height: auto;border-bottom: 1.5rem lightgrey solid;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start;margin-top: 10rem;padding-bottom: 10rem">
                <div  @click="revolveTo(1)" style="width: 100%;height: 30rem;display: flex;justify-content: space-between;align-items: center">
                    <div class="left_shop_inf">
                        <img  :src="logo2" style="width: 20rem;height: 20rem">
                        <span>订单管理</span>
                    </div>
                    <span class="right_shop_inf"><up-outlined></up-outlined></span>
                </div>

                <div class="functionItems" style="width: 160rem;height: 40rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start;font-size: 12rem">
                    <div style="width: 100%;height: auto;display: flex;justify-content: space-between;color: grey">
                        <router-link to="/shop/orderManage" style="color: grey">订单列表</router-link>
                    </div>
                </div>
            </div>

            <div style="width: 185rem;height: auto;border-bottom: 1.5rem lightgrey solid;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start;margin-top: 10rem;padding-bottom: 10rem">
                <div @click="revolveTo(2)" style="width: 100%;height: 30rem;display: flex;justify-content: space-between;align-items: center">
                    <div class="left_shop_inf">
                        <img :src="logo3" style="width: 20rem;height: 20rem">
                        <span>店铺管理</span>
                    </div>
                    <span class="right_shop_inf"><up-outlined></up-outlined></span>
                </div>

                <div class="functionItems" style="width: 160rem;height: 30rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start;font-size: 12rem">
                    <div style="width: 100%;height: auto;display: flex;justify-content: space-between;color: grey">
                        <span><router-link style="color: grey" to="/shop/modifyShopInf">店铺信息</router-link></span>
                        <span>退店</span>
                    </div>
                </div>
            </div>

            <div style="width: 185rem;height: auto;border-bottom: 1.5rem lightgrey solid;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start;margin-top: 10rem;padding-bottom: 10rem">
                <div @click="revolveTo(3)" style="width: 100%;height: 30rem;display: flex;justify-content: space-between;align-items: center">
                    <div class="left_shop_inf">
                        <img :src="logo4" style="width: 20rem;height: 20rem">
                        <span>客服中心</span>
                    </div>
                    <span class="right_shop_inf"><up-outlined></up-outlined></span>
                </div>

                <div class="functionItems" style="width: 160rem;height: 40rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start;font-size: 12rem">
                    <div style="width: 100%;height: auto;display: flex;justify-content: space-between;color: grey">
                        <router-link to="/shop/commentManage" style="color: grey">消息设置</router-link>
                        <router-link to="/shop/commentManage" style="color: grey">待回复</router-link>
                    </div>
                </div>
            </div>
        </div>
</template>

<script>
  import {UpOutlined} from '@ant-design/icons-vue';
  //import $ from 'jquery'
  export default {
    name: "leftFunction",
    components:{
      UpOutlined
    },
    setup(){
      let logo1=require('../../assets/img/24c8b43e-37b1-4967-bdec-d6aabfaf3935.png')

      let logo2=require('../../assets/img/e28bf79a-91ca-4101-94d7-c598ffd0f523.png')

      let logo3=require('../../assets/img/a0b6afda-b18c-4bf9-b4c3-f48a3396ff36.png')

      let logo4=require('../../assets/img/fb5d7709-f9f3-4e9f-acfb-69d918868549.png')

      let logo5=require('../../assets/img/95b94fb8-77ec-4ac3-adef-8d3f29fdd1d0.png')

      function revolveTo(index) {
        let divs=document.getElementsByClassName('right_shop_inf')

        let div=divs[index]
        console.log(div.style.transform);

        let functionDivs=document.getElementsByClassName('functionItems')

        let functionDiv=functionDivs[index]

        if(div.style.transform=='rotate(180deg)'){
          div.style.transform='rotate(0deg)'
          functionDiv.style.display='flex'
        }else {
          div.style.transform='rotate(180deg)'
          functionDiv.style.display='none'
        }

      }

      return {logo1,logo2,logo3,logo4,logo5,revolveTo}
    }
  }
</script>

<style scoped>
    .left_shop_inf{
        height: 100%;
        width: 85rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 14rem;
    }
    .right_shop_inf{
        font-size: 14rem;
        color: darkgrey;
        transition: all 0.5s;
    }
</style>