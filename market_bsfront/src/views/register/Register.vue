<template>
    <div style="margin-top: 60px;width: 360px;height: 400px;background-color: white;border-radius: 0.3px;display: flex;flex-direction: column;justify-content: space-between">
        <div style="width: 100%;height: 400px;display: flex;justify-content: center;align-items: center" >
            <div>
                <div  style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <MailOutlined style="width: 50px"></MailOutlined><input v-model="email" type="text" placeholder="输入邮箱" style="border: 0px;cursor: text;width: 250px;" >
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{emailMsg}}
                </div>
                <div  style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <div style="width: 77px"></div><input v-model="verifyCode" type="text" placeholder="输入邮箱验证码" style="border: 0px;cursor: text;width: 200px"><input type="button" style="background-color: rgb(244,51,153);cursor: pointer;color: #ffffff;height: 40px" @click="checkEmail" value="获取验证码">
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{verifyCodeMsg}}
                </div>
                <div  @click="checkVerifyCode" style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <LockOutlined style="width: 50px"></LockOutlined><input type="password" v-model="password" placeholder="请输入密码" style="border: 0px;cursor: text;width: 250px">
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{passwordMsg}}
                </div>
                <div  @click="checkPassword" style="width: 300px;height: 46px;border: 1px lightgrey solid;border-radius: 2px;display:flex;justify-content: space-evenly;align-items: center">
                    <LockOutlined style="width: 50px"></LockOutlined><input type="password" v-model="password1" placeholder="再次输入密码" style="border: 0px;cursor: text;width: 250px">
                </div>
                <div style="width: 300px;height: 19px;color: indianred;display: flex;justify-content: flex-start;font-size: 6px">
                    {{passwordMsg1}}
                </div>
                <div style="width: 300px;height: 19px;display: flex;justify-content: flex-end;align-items: center">

                </div>
                <button @click="checkBefore" style="border: 0px;background-color: rgb(244,51,153);width: 300px;height: 50px;font-size: 20px;color:white;cursor: pointer">注册</button>
            </div>
        </div>
    </div>
</template>

<script>
  import router from "../../router"
  import { LockOutlined,MailOutlined} from '@ant-design/icons-vue';
  import {ref} from 'vue'
  import register from "../../Internet/home/user/register/register";
  import sendEmailInRegister from "../../Internet/home/user/register/sendEmailInRegister";
  export default {
    name: "Register",
    components: {
      MailOutlined,
      LockOutlined
    },
    setup() {
      let email=ref('')
      let password=ref('')
      let password1=ref('')

      let passwordMsg=ref('')
      let passwordMsg1=ref('')

      let emailMsg=ref('')
      let verifyCodeMsg=ref('')

      let verifyCode=ref('')

      let verifyCode1=''


      //检查密码
      function checkPassword() {
        if(password.value!=''){
          let pattern= /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/;
          if(password.value.match(pattern)){
            passwordMsg.value=''
            console.log("正确");
          }else {
            passwordMsg.value="必须包含大写字母，小写字母，数字,8~16位"
            console.log("错误");
          }
        }else {
          passwordMsg.value="请输入密码"
        }
      }

      //检查两次密码是否一致&&注册
      function checkBefore() {

        checkPassword()

        if(email.value==''){
          emailMsg.value='请输入邮箱'
        }else {
          let regex7 = /^\d{5,12}@[qQ][qQ]\.(com|cn)$/;
          if(!(email.value.match(regex7))){
            emailMsg.value='请输入正确的邮箱'
          }
        }

        if(password1.value!=''&&password.value!=''){
          passwordMsg1.value=''
          if(password.value!=password1.value){
            passwordMsg1.value="两次密码不一致，请重新输入";
          }else {
            if(verifyCode.value==''){
              verifyCodeMsg.value="请输入验证码"
            }else {
              if(verifyCode.value!=verifyCode1){
                verifyCodeMsg.value="验证码错误"
              }else{
                let user={"email":email.value,"password":password.value}
                register(user).then(()=>{
                  router.push("/login/loginPage")
                })
                verifyCodeMsg.value=""
              }
            }
          }
        }else if(password1.value==''){
          passwordMsg1.value="请再次输入密码";
        }
      }

      //检查邮箱
      function checkEmail(){

        if(email.value==''){
          emailMsg.value='请输入邮箱'
        }else {
          let regex7 = /^\d{5,12}@[qQ][qQ]\.(com|cn)$/;
          if(!(email.value.match(regex7))){
            emailMsg.value='请输入正确的邮箱'
          }else {
            emailMsg.value=''
            sendEmailInRegister(email.value).then(res=>{
              if(res.code==0){
                verifyCode1=res.data.verifyCode
                console.log(res);
              }else {
                emailMsg.value="该邮箱已注册"
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
            verifyCodeMsg.value=""
          }
        }
      }

      return {passwordMsg,passwordMsg1,verifyCodeMsg,emailMsg,checkPassword,verifyCode,checkVerifyCode,checkEmail,password,password1,email,checkBefore}
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