<template>
    <div style="width:100%;height: 36rem;display: flex;background-color: rgb(245,245,245);justify-content: space-evenly;align-items: center">
        <div style="width:1000rem;height: 36rem;display: flex;justify-content: flex-start;align-items: center;font-size: 12rem">
            <div style="width: 400rem;display: flex;justify-content: space-between;color: darkgrey;align-items: center">
                <div id="login" @mouseenter="Login" @mouseleave="hideLogin" style="display: flex;align-items: center;height: 36rem;font-size: 12rem;">
                    <caret-down-outlined v-if="showLogin"/>
                    <caret-up-outlined v-else/>
                    <router-link to="/login/loginPage" style="color: darkgrey;cursor: pointer;" v-if="loginFlag">
                        请登录
                    </router-link>
                    <router-link to="/mainHeader/UserAccountCenter" style="color: darkgrey;cursor: pointer" v-else>
                        {{loginName}}
                    </router-link>
                    <div v-if="!showLogin"  style="position: absolute;background-color: white;width: 280rem;height: 100rem;top:36rem;z-index: 2;display: flex;flex-direction:column;justify-content: space-between;align-items: center;border-left: 1rem solid rgb(245,245,245);border-right: 1rem solid rgb(245,245,245);box-shadow: 0 1rem 2rem lightgrey;border-bottom: 1rem solid rgb(245,245,245)">
                        <div style="width: 80%;display: flex;justify-content: space-between;align-items: center">
                            <img :src="user" style="max-width: 45rem;max-height: 45rem;width: 45rem;height: 45rem;border-radius: 50%">
                            <div style="display: flex;flex-direction: column;justify-content:space-evenly;width: 220rem;height: 50rem;align-items: flex-start;border-bottom:1rem lightgrey dotted">
                                <div style="width: 100%;height: 50rem;display: flex;justify-content: space-between;align-items: center">
                                    <router-link to="/login/loginPage" style="color: darkgrey;cursor: pointer" v-if="loginFlag">请登录</router-link>
                                    <div style="width: 100%;height: 50rem;display: flex;justify-content: space-between;align-items: center" v-else>
                                        <router-link to="/mainHeader/UserAccountCenter" style="color: darkgrey;cursor: pointer">
                                            {{loginName}}
                                        </router-link>
                                        <button @click="cancelLogin" style="background-color: white;color: darkgrey;border: 0px;cursor: pointer">
                                            退出
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style="width: 80%;display: flex;justify-content: space-between">
                            <p>我的订单</p>
                            <p>我的收藏</p>
                        </div>
                    </div>
                </div>
                <span>/</span>
                <span><router-link v-if="loginFlag" to="/login/register" style="color: darkgrey">注册</router-link></span>
                <span  v-if="loginFlag">/</span>
                <span>
                    <router-link  to="/mainHeader/UserAccountCenter/userOrder" style="color: darkgrey">我的订单</router-link>
                </span>
                <span>/</span>
                <span>
                    <router-link  to="/mainHeader/mySale/browse" style="color: darkgrey">我的特卖</router-link>
                         <caret-down-outlined/>
                </span>
                <span>/</span>
                <span v-if="!loginFlag">
                    <router-link to="/openStore" style="color: darkgrey;cursor: pointer" v-if="openFlag">
                        免费开店
                    </router-link>
                    <router-link to="/shop/shopHome" style="color: darkgrey;cursor: pointer" v-else>
                        我的小店
                    </router-link>
                </span>
                <span v-else>
                    <router-link to="/login/loginPage" style="color: darkgrey;cursor: pointer">
                        免费开店
                    </router-link>
                </span>
                <span>/</span>
                <router-link to="/mainHeader/home" style="color: darkgrey;cursor: pointer">
                    首页
                </router-link>
            </div>
        </div>
    </div>

</template>

<script>
  import { CaretDownOutlined,CaretUpOutlined} from '@ant-design/icons-vue';
  import findByUserId from "../../Internet/home/shop/find/findByUserId";
  import {inject,ref} from "vue";
  import signOut from "../../Internet/home/user/login/signOut";
  import store from "../../store/index"

  let user=ref('')
  export default {
    name: "topHeader",
    components:{
      CaretDownOutlined,
      CaretUpOutlined,
    },
    setup(){
      let showLogin=ref(true)

      let loginName = inject("loginName")


      let loginFlag = inject("loginFlag")



      let userlogo2=require("../../assets/img/my_cnt_pic.jpg")


      let userLogo=require("../../assets/img/3f6d96131fd462118c191c644ca0e3dd_38x38_90.png")

      user.value = require("../../assets/img/2021-07-16_171837.png");

      function Login(){
        showLogin.value=false;
        let div=document.getElementById("login");
        div.style.backgroundColor="white"
      }

      function hideLogin() {
        showLogin.value=true;
        let div=document.getElementById("login");
        div.style.backgroundColor="rgb(245,245,245)"
      }

      function cancelLogin(){
        loginFlag.value=true
        sessionStorage.setItem('user', null);
        store.state.user.data=null

        signOut()

        user.value = require("../../assets/img/2021-07-16_171837.png");
        sessionStorage.removeItem("user")
      }


      let user1=JSON.parse(sessionStorage.getItem("user"))

      if(user1!==null){
        if(user1.image!=null){
          user.value= require('../../assets/img/'+user1.image)
        }
      }



      function changeUserName(loginName1) {

        loginName.value=loginName1
      }

      //判断改用户是否开了店铺，每一个账号只有一家店
      let openFlag=ref(false)
      if(loginFlag.value==false){
        findByUserId(user1.userId).then(res=>{
          if(res.code==0){
            //找到，说明已经开了店
            openFlag.value=false
            sessionStorage.setItem('shop',JSON.stringify(res.data))
          }else {
            //没有找到，说明没有开店
            openFlag.value=true
          }
        })
      }

      return{
        showLogin,
        Login,
        hideLogin,
        user,
        loginFlag,
        cancelLogin,
        userLogo,
        userlogo2,
        changeUserName,
        loginName,
        openFlag};
    },
    created() {
      window.addEventListener('setItem', ()=> {
        store.state.user.data=JSON.parse(sessionStorage.getItem('user'))
        this.newVal = sessionStorage.getItem('user');
        user.value= require('../../assets/img/'+JSON.parse(this.newVal).image)
      })
    },
  }
</script>

<style scoped>

</style>