<template>
    <div style="width: 850rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;font-size: 12rem">
        <div  style="width: 100%;height:auto;display:flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
            <div style="border-left: 2rem rgb(241,1,128) solid;width: auto;padding-left:20rem;height:20rem;align-items:center;display: flex;justify-content: space-evenly;font-weight: 700;font-size: 14rem;">
                <span>选择服务类型</span>
            </div>
        </div>
        <div style="width: 100%;height: 176rem;background-color: white;margin-top: 10rem;border: 1rem solid #e0e0e0;padding: 20rem">
            <div style="width: 100%;height: 104rem;margin-top: 25rem;display: flex;justify-content: space-between">

                <router-link :to="{path:'/mainHeader/UserAccountCenter/exchangeOrder',query:{orderNumber:orderNumber,goodsIndex:goodsIndex}}" style="cursor: pointer;width: 390rem;height: 100%;border: 1rem solid #e0e0e0;border-radius: 5rem;display: flex;align-items: center;justify-content: center">    <div style="width: auto;height: auto;display: flex;align-items: center;justify-content: flex-start">
                        <div style="width: 50rem;height: 50rem;border-radius: 5rem;background-color: rgb(0,194,126);display: flex;justify-content: center;align-items: center">
                            <img :src="returnLogo" style="width: 40rem">
                        </div>
                        <div style="height: 55rem;width: 250rem;margin-left: 20rem;display: flex;flex-direction: column;align-items: flex-start;justify-content: space-between">
                            <span style="font-size: 20rem;">
                                换货
                            </span>
                            <span style="font-size: 13rem;color: grey">
                                填写换货信息并换货
                            </span>
                        </div>
                    </div>
                </router-link>

                <router-link :to="{path:'/mainHeader/UserAccountCenter/returnOrder',query:{orderNumber:orderNumber,goodsIndex:goodsIndex}}" style="cursor: pointer;width: 390rem;height: 100%;border: 1rem solid #e0e0e0;border-radius: 5rem;display: flex;align-items: center;justify-content: center">
                    <div style="width: auto;height: auto;display: flex;align-items: center;justify-content: flex-start">
                        <div style="width: 50rem;height: 50rem;border-radius: 5rem;display: flex;justify-content: center;align-items: center">
                            <img :src="changeLogo" style="width: 50rem;">
                        </div>
                        <div style="height: 55rem;width: 250rem;margin-left: 20rem;display: flex;flex-direction: column;align-items: flex-start;justify-content: space-between">
                            <span style="font-size: 20rem;">
                                退货
                            </span>
                            <span style="font-size: 13rem;color: grey">
                                退回收到的商品并退款
                            </span>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
  import {ref} from "@vue/reactivity";

  export default {
    name: "selectAfterSaleType",
    setup(){
      let changeLogo=require('../../../assets/img/换货.svg')
      let returnLogo=require('../../../assets/img/退货.png')

      let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg = new RegExp("(^|&)" + 'orderNumber' + "=([^&]*)(&|$)");
      let r = search.match(reg);

      let orderNumber=ref(unescape(r[2]))

      let reg2 = new RegExp("(^|&)" + 'goodsIndex' + "=([^&]*)(&|$)");
      let r2 = search.match(reg2);
      let goodsIndex=ref(unescape(r2[2]))

      return{
        changeLogo,
        returnLogo,
        orderNumber,
        goodsIndex
      }
    }
  }
</script>

<style scoped>

</style>