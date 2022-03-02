<template>
    <div style="margin-top: 60px;width: 360px;height: 341px;background-color: white;border-radius: 0.3px;display: flex;flex-direction: column;justify-content: space-between">
        <div style="height: 50px;width: 100%;display: flex;justify-content: space-evenly;align-items: center;border-bottom:1px lightgrey solid">
            <div  style="width: 50%;height: 20px;border-right:1.5px lightgrey solid;font-size: 20px;font-size: 20px;color: darkgrey;display: flex;justify-content: center;align-items: center;color: rgb(244,51,153);cursor: pointer" id="loginByEmailFlag" ref="email" @click="chooseLoginType(1)">
                邮箱登录
            </div>
            <div style="width: 50%;height: 100%;font-size: 20px;color: darkgrey;display: flex;align-items: center;justify-content: center;cursor: pointer" id="loginByPasswordFlag" ref="username" @click="chooseLoginType(2)">
                账号登录
            </div>
        </div>
        <div style="width: 100%;height: 289px;display: flex;justify-content: center;align-items: center" id="loginByEmailDiv" v-if="loginchoose">
            <div>
                <div  style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <MailOutlined style="width: 50px"></MailOutlined><input v-model="user.email" type="text" placeholder="输入邮箱" style="border: 0px;cursor: text;width: 250px;" >
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{emailMsg}}
                </div>
                <div  style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <input v-model="verifyCode" type="text" placeholder="输入邮箱验证码" style="border: 0px;cursor: text;width: 250px"><input type="button" style="background-color: rgb(244,51,153);cursor: pointer;color: #ffffff;height: 40px" @click="checkEmail" value="获取验证码">
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{verifyCodeMsg}}
                </div>
                <div style="width: 300px;height: 19px;display: flex;justify-content: flex-end;align-items: center">

                </div>
                <button @click="checkVerifyCode" style="border: 0px;background-color: rgb(244,51,153);width: 300px;height: 50px;font-size: 20px;color:white;cursor: pointer">登录</button>
            </div>
        </div>

        <div style="width: 100%;height: 289px;display: flex;justify-content: center;align-items: center" id="loginByPasswordDiv" v-else>
            <div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{errorU}}
                </div>
                <div @click="checkUserName" style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <UserOutlined style="width: 50px"></UserOutlined><input type="text" v-model="user.username" placeholder="输入用户名/账号" style="border: 0px;cursor: text;width: 250px">
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{usernameMsg}}
                </div>
                <div  @click="checkPassword" style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <LockOutlined style="width: 50px"></LockOutlined><input type="password" v-model="user.password" placeholder="密码" style="border: 0px;cursor: text;width: 250px">
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{passwordMsg}}
                </div>
                <div style="width: 300px;height: 19px;display: flex;justify-content: flex-end;align-items: center">
                    <router-link to="/login/findPassword" style="color: darkgrey">忘记密码</router-link>
                </div>
                <button @click="loginByUsernamePassword" style="border: 0px;background-color: rgb(244,51,153);width: 300px;height: 50px;font-size: 20px;color:white;cursor: pointer">登录</button>
            </div>
        </div>
    </div>
</template>

<script>
  import { UserOutlined,LockOutlined,MailOutlined} from '@ant-design/icons-vue';
  import {ref, reactive} from 'vue'
  import loginByUsername from "../../Internet/home/user/login/loginByUsername";
  import sendEmail from "../../Internet/home/user/login/sendVerifyCode";
  import router from "../../router"
  // import {getCurrentInstance} from "@vue/runtime-core";


  export default {
    name: "Login",
    components: {
      UserOutlined,
      LockOutlined,
      MailOutlined
    },
    setup() {
      let logo = require("../../assets/img/2021-07-16_214731.png")
      let description = require("../../assets/img/2021-07-16_214845.png")
      let photo = require("../../assets/img/2021-07-16_220309.png")

      let loginchoose = ref(true)

      function chooseLoginType(flag) {
        let div1 = document.getElementById("loginByEmailFlag")
        let div2 = document.getElementById("loginByPasswordFlag");

        if (flag == 1 && loginchoose.value == false) {//选择邮箱验证方式
          loginchoose.value = true

          div1.style.color = "rgb(244,51,153)"
          div2.style.color = "darkgrey"

          usernameMsg.value=''
          passwordMsg.value=''
          errorU.value=''
          enterFlag1=false
          enterFlag2=false

        } else if (flag == 2 && loginchoose.value == true) {//选择用户名密登录方式
          loginchoose.value = false

          div1.style.color = "darkgrey"
          div2.style.color = "rgb(244,51,153)"
        }
      }

      let user = new reactive({
        username: '',
        password: '',
        email: '',
      })


      let usernameMsg=ref('')
      let passwordMsg=ref('')
      let emailMsg=ref('')
      let verifyCodeMsg=ref('')

      let verifyCode=ref('')

      let enterFlag1=false
      let enterFlag2=false

      let errorU=ref('')


      //检查用户名
      function checkUserName() {
        enterFlag1=true
        if(enterFlag2==true){
          if(user.password==''){
            passwordMsg.value='请输入密码'
          }else {
            passwordMsg.value=''
          }
        }
      }

      //检查密码
      function checkPassword() {
        enterFlag2=true
        if(enterFlag1==true){
          if(user.username==''){
            usernameMsg.value='请输入用户名'
          }else {
            usernameMsg.value=''
          }
        }
      }

      let verifyCode1=''
      //检查邮箱
      function checkEmail(){

        if(user.email==''){
          emailMsg.value='请输入邮箱'
        }else {
          let regex7 = /^\d{5,12}@[qQ][qQ]\.(com|cn)$/;
          if(!(user.email.match(regex7))){
            emailMsg.value='请输入正确的邮箱'
          }else {
            emailMsg.value=''
            sendEmail(user.email).then(res=>{
              if(res.code==0){
                verifyCode1=res.data.verifyCode
               sessionStorage.setItem("user",JSON.stringify(res.data.user))
              }else {
                emailMsg.value="该邮箱未注册"
              }
            })
          }
        }
      }

      //检查验证码
      function checkVerifyCode(){
        if(verifyCode.value==''){
          verifyCodeMsg.value="请输入验证码"
        }else {
          if(verifyCode.value!=verifyCode1){
            verifyCodeMsg.value="验证码错误"
          }else {
            router.push('/mainHeader/home')
          }
        }
      }

      //账号密码登录
      function loginByUsernamePassword() {
        let flag=true
        if(user.username==''){
          usernameMsg.value='请输入用户名'

          flag=false
        }else {
          usernameMsg.value=''
        }

        if(user.password==''){

          passwordMsg.value='请输入密码'
          flag=false
        }else {
          passwordMsg.value=''
        }

        if(flag==true){
          loginByUsername(user).then(res=>{
            console.log(res);
            if(res.code==1){
              errorU.value="用户名或密码错误，请重新输入"
            }else {
              sessionStorage.setItem('user',JSON.stringify(res.data))
              router.push('/mainHeader/home')
              errorU.value=''
            }
          })
        }
      }

      function check() {

        if(enterFlag1==true){
          if(user.username==''){
            console.log(222)
            usernameMsg.value='请输入用户名'
          }else {
            usernameMsg.value=''
          }
        }
        if(enterFlag2==true){
          if(user.password==''){
            passwordMsg.value='请输入密码'
          }else {
            passwordMsg.value=''
          }
        }
      }

      return {logo, description, photo, loginchoose, chooseLoginType, user, loginByUsernamePassword,usernameMsg,passwordMsg,verifyCodeMsg,emailMsg,errorU,checkUserName,checkPassword,check,verifyCode,checkVerifyCode,checkEmail}
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