<template>
          <div id="userInf">
                    <div style="width: 100%;height: 40rem;background-color:rgb(241,1,128);display: flex;justify-content: space-evenly">
                        <div class="mainDiv" style="width: 100%;display: flex;height: auto;justify-content: space-evenly;align-items: center">
                        <div style="width: 1000rem;height: 40rem;display: flex;justify-content: left;">
                                <router-link to="/mainHeader/UserAccountCenter/userInf">
                                    <div style="height: 40rem;width: 140rem;background-color: rgba(38,38,38,0.3);display: flex;justify-content: center;align-items: center;font-size: 17rem;color: white">
                                        <span style="width: 100rem">我的唯品会</span>
                                    </div>
                                </router-link>
                                <router-link to="/mainHeader/home">
                                    <div style="height: 40rem;width: 140rem;display: flex;justify-content: center;align-items: center;font-size: 17rem;color: white;">
                                        <span style="width: 100rem">首页</span>
                                    </div>
                                 </router-link>
                            </div>
                        </div>
                    </div>
                    <div class="imageBackgroundDiv" style="width: 100%;height:auto;display: flex;justify-content: space-evenly;margin-top: 20rem; margin-bottom: 20rem">
                        <div class="mainDiv" style="width: 100%;display: flex;height: auto;justify-content: space-evenly;align-items: center">
                            <div style="width: 1000rem;display:flex;justify-content: space-between;">
                                <div style="width: 130rem;height: 400rem;background-color: white;border:0.1rem whitesmoke solid;display: flex;justify-content: space-evenly">
                                <div style="width: 98rem;height: 400rem;display: flex;flex-direction: column;align-items: flex-start;justify-content: space-evenly;margin-right: 20rem ">
                                    <span style="font-size: 15rem;color: black;font-weight: bold">我的交易</span>
                                    <router-link to="/mainHeader/UserAccountCenter/userOrder"><span style="font-size: 12rem;color: grey;">订单管理</span></router-link>
                                    <span style="font-size: 12rem;color: grey;">退款/售后</span>
                                    <router-link to="/mainHeader/UserAccountCenter/setUserAddress"><span style="font-size: 12rem;color: grey;">收货地址</span></router-link>
                                    <span style="width: 100%;border-top: 1rem grey dotted"></span>
                                    <span style="font-size: 15rem;color: black;font-weight: bold">我的资产</span>
                                    <span style="font-size: 12rem;color: grey;">唯品币</span>
                                    <span style="font-size: 12rem;color: grey;">我的店铺</span>
                                    <span style="width: 100%;border-top: 1rem grey dotted"></span>
                                    <span style="font-size: 15rem;color: black;font-weight: bold">我的账户</span>
                                    <router-link to="/mainHeader/UserAccountCenter/modifyUserInf"><span style="font-size: 12rem;color: grey;">我的资料</span></router-link>
                                </div>
                            </div>
                                <router-view></router-view>
                            </div>
                        </div>
                    </div>
          </div>
</template>

<script>
  //import axios from "axios";

  import getLocals from "../../assets/functions/getLocals";

  export default {
    name: "UserAccount",
    emits: ["changeUserName"],
    setup(props,context){
      let backgroundImg=require("../../assets/img/qq_pic_merged_1626749647742.jpg")

      function changeUserNameOnSon(loginName1) {
        context.emit('changeUserName', loginName1)
      }


      getLocals().then(res=>{
        console.log(res.data);
      })


      let img=require("../../assets/img/layout-bg.jpg")
      return{backgroundImg,changeUserNameOnSon,img}

    }
  }
</script>

<style scoped>
    #userInf{
        background-color: rgb(239,240,244);
        background-repeat: no-repeat;
        background-size: 100% 100%;
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .imageBackgroundDiv{
        background-image: url('../../assets/img/layout-bg.jpg');
        background-color: #f5f6f8;
        background-size:100% 400rem;
        background-repeat: no-repeat;
    }
</style>