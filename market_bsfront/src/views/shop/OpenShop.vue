<template>
    <div >
        <a-row style="height: 100px">
            <a-col :xs="0" :sm="0" :md="2" :lg="4" :xl="2" >

            </a-col>
            <a-col :xs="24" :sm="22" :md="20" :lg="16" :xl="20" style="background-color: white">
                <div style="width: 100%;height:100px;display: flex;justify-content: space-between">
                    <img :src="logo">
                    <img :src="description">
                </div>
            </a-col>
            <a-col :xs="0" :sm="2" :md="2" :lg="4" :xl="2" >

            </a-col>
        </a-row>
        <a-row style="height: 600px;background-color: rgb(250,168,172)">
            <a-col :xs="0" :sm="0" :md="2" :lg="2" :xl="2" >

            </a-col>
            <a-col :xs="0" :sm="0" :md="11" :lg="10" :xl="10" >
                <div>
                    <img :src="photo" style="max-width: 500px;margin-top: 100px">
                </div>
            </a-col>
            <a-col :xs="24" :sm="22" :md="11" :lg="10" :xl=10 style="display: flex;justify-content: flex-end">
                <div>
                    <div style="margin-top: 120px;width: 360px;height: 250px;background-color: white;border-radius: 0.3px;display: flex;flex-direction: column;justify-content: space-between">
                        <div style="width: 100%;height: 400px;display: flex;justify-content: center;align-items: center" >
                            <div>
                                <div  style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                                    <div style="width: 77px"></div><input :onblur="checkVerifyCode" v-model="verifyCode" type="text" placeholder="输入邮箱验证码" style="border: 0px;cursor: text;width: 200px"><input type="button" style="background-color: rgb(244,51,153);cursor: pointer;color: #ffffff;height: 40px" @click="checkEmail" value="获取验证码">
                                </div>
                                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                                    {{verifyCodeMsg}}
                                </div>
                                <div   style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                                    <ShopOutlined style="width: 50px"></ShopOutlined><input :onblur="checkStore" type="text" v-model="store" placeholder="请输入店铺名" style="border: 0px;cursor: text;width: 250px">
                                </div>
                                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                                    {{storeMsg}}
                                </div>
                                <button @click="checkBefore" style="border: 0px;background-color: rgb(244,51,153);width: 300px;height: 50px;font-size: 20px;color:white;cursor: pointer">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
            </a-col>
            <a-col :xs="0" :sm="2" :md="0" :lg="2" :xl=2 >

            </a-col>
        </a-row>
    </div>
</template>

<script>
  import {ShopOutlined} from '@ant-design/icons-vue';
  import {ref} from 'vue'
  import sendEmail from "../../Internet/home/user/login/sendVerifyCode";
  import findByShopName from "../../Internet/home/shop/find/findByShopName";
  import addShop from "../../Internet/home/shop/add/addShop";
  import router from '../../router/index.js'

  export default {
    name: "OpenStore",
    components: {
        ShopOutlined
      },
      setup() {
        let logo = require("../../assets/img/2021-07-16_214731.png")
        let description = require("../../assets/img/2021-07-16_214845.png")
        let photo = require("../../assets/img/2021-07-22_153436.png")


        let email=ref('')
        let store=ref('')

        if(JSON.parse(sessionStorage.getItem('user')).username!==null){
          store.value=JSON.parse(sessionStorage.getItem('user')).username+'的店'
        }

        let storeMsg=ref('')

        let emailMsg=ref('')
        let verifyCodeMsg=ref('')

        let verifyCode=ref('')

        let verifyCode1=''


        //检查两次密码是否一致&&注册
        function checkBefore() {
          if(verifyCode.value==''){
            verifyCodeMsg.value="请输入验证码"
          }else if(store.value==''){
            storeMsg.value='请输入店铺名'
          }else {
            console.log(111)
            findByShopName(store.value).then(res=>{
              if(res.code==1){//说明没有找到同名的
                let data={'shopName':store.value,'userId':JSON.parse(sessionStorage.getItem('user')).userId,'shopCount':6,'fansNumber':0}
                addShop(data).then(res=>{
                  console.log(res);
                  if(res.code==0){
                    alert('申请成功')
                    setTimeout(()=>{
                     router.push('/mainHeader/home')
                    },1000)
                  }
                })
              }else {
                storeMsg.value='已存在该店铺名，请重新输入'
              }
            })
          }
        }

        email.value=JSON.parse(sessionStorage.getItem('user')).email


        //检查邮箱
        function checkEmail(){
          sendEmail(email.value).then(res=>{
            if(res.code==0){
              console.log(res.data.verifyCode);
              verifyCode1=res.data.verifyCode
            }
          })
        }

        //检查验证码
        function checkVerifyCode(){
          if(verifyCode.value==''){
            verifyCodeMsg.value="请输入验证码"
          }else {
            if(verifyCode.value!=verifyCode1){
              verifyCodeMsg.value="验证码错误"
            }else {
              verifyCodeMsg.value=""
            }
          }
        }

        function checkStore() {
          if(store.value==''){
            verifyCodeMsg.value="请输入店铺名"
          }else {
            findByShopName(store.value).then(res=>{
              if(res.code==0){//说明找到同名的
                storeMsg.value='已存在该店铺名，请重新输入'
              }else {
                storeMsg.value=''
              }
            })
          }
        }

        return {verifyCodeMsg,emailMsg,verifyCode,checkVerifyCode,checkEmail,email,logo, description, photo,checkBefore,store,storeMsg,checkStore}
      }
  }
</script>

<style scoped>
    input{
        /*去除阴影*/
        box-shadow:none;
        /*聚焦input的蓝色边框*/
        outline: none;

        /*去除边框*/
        border: none;

        /*点击高亮的颜色*/
        -webkit-tap-highlight-color:rgba(0,0,0,0);

        border-radius: 0;
    }
</style>