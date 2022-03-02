<template>
    <div style="width: 850rem;height: 500rem;display: flex;flex-direction: column;align-items: center;justify-content: space-between;font-size: 12rem">
        <div style="width: 100%;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
            <div style="border-left: 2rem rgb(241,1,128) solid;width: 170rem;height:20rem;align-items:center;display: flex;justify-content: space-evenly;align-items: center;font-size: 12rem;">
                <span ><router-link style="color: black;cursor: pointer" to="/mainHeader/UserAccountCenter/userInf">我的唯品会</router-link></span>
                <span>></span>
                <span>修改登录名</span>
            </div>
        </div>
        <div style="width: 850rem;height: 450rem;background-color: white;display: flex;flex-direction: column;justify-content: space-between;align-items: center;border: 1rem lightgrey solid">
            <div style="width: 850rem;height: 40rem;background-color: whitesmoke;border-bottom: 1rem lightgrey solid;border-left:1rem lightgrey solid;border-right: 1rem lightgrey solid;display: flex;justify-content: space-evenly;align-items: center">
                <span style="width: 800rem;height: 40rem;display: flex;justify-content: flex-start;align-items: center">
                      <span style="font-weight: bold;font-size: 15px">邮箱验证修改</span>
                </span>
            </div>
            <div style="width: 800rem;height: 410rem;display: flex;justify-content: space-evenly;align-items: center;">
                <div style="width: 590rem;height: 300rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                    <div style="width: 580rem;height:30rem;display: flex;justify-content: space-between;align-items: center">
                        <div style="width: 30rem;height: 30rem;background-color: lightgrey;border-radius: 50%;display: flex;justify-content: center;align-items: center;z-index: 2">
                        <div  id="circle1" style="width: 20rem;height: 20rem;background-color: whitesmoke;border-radius: 50rem;color:grey;font-size: 15rem;display: flex;font-weight: bold;align-items: center;justify-content: space-evenly">
                                <span v-if="step1IsCheck">1</span>
                                <check-outlined v-else style='font-size: 15rem;color: white'></check-outlined>
                            </div>
                        </div>
                        <div id="line1" style="width: 263rem;height: 10rem;background-color: lightgrey;margin-left: -2rem"></div>
                        <div  style="width: 30rem;height: 30rem;background-color: lightgrey;border-radius: 50%;margin-left: -2rem;display: flex;justify-content: center;align-items: center;position: relative;z-index: 2">
                            <div id="circle2" style="width: 20rem;height: 20rem;background-color: whitesmoke;border-radius: 50rem;color:grey;font-size: 15rem;display: flex;font-weight: bold;align-items: center;justify-content: space-evenly;">
                                <span id="circle2Content" v-if="step2IsCheck">2</span>
                                <check-outlined v-else style='font-size: 15rem;color: white'></check-outlined>
                            </div>
                        </div>
                        <div id="line2" style="width: 263rem;height: 10rem;background-color: lightgrey;margin-left: -3rem"></div>
                        <div  style="width: 30rem;height: 30rem;background-color: lightgrey;border-radius: 50%;margin-left: -4rem;display: flex;justify-content: center;align-items: center">
                            <div id="circle3" style="width: 20rem;height: 20rem;background-color: whitesmoke;border-radius: 50px;color:grey;font-size: 15px;display: flex;font-weight: bold;align-items: center;justify-content: space-evenly">
                                <span id="circle3Content">3</span>
                            </div>
                        </div>
                    </div>
                    <div style="height: 30rem;width: 580rem;display:flex;justify-content: space-between;font-size: 10rem;color: grey">
                        <span style="margin-left: -10rem">验证身份</span>
                        <span>设置登录名</span>
                        <span>完成</span>
                    </div>
                    <div v-if="step1Flag" style="height: 200rem;width: 300rem;display: flex;flex-direction: column;justify-content: space-evenly">
                        <div style="height: 30rem;width: 300rem;display: flex;justify-content: space-between;align-items: center">
                            <div style="height: 30rem;width: 100rem;display: flex;justify-content: flex-end;align-items: center">
                                <span >验证邮箱：</span>
                            </div>
                            <div style="height: 30rem;width: 180rem;display: flex;justify-content: flex-start;align-items: center">
                                <span>{{loginEmail}}</span>
                            </div>
                        </div>
                        <div style="height: 50rem;width: 350rem;display: flex;justify-content: space-between;align-items: center">
                            <div style="height: 30rem;width: 100rem;display: flex;justify-content: flex-end;align-items: center">
                                <span>短信验证码：</span>
                            </div>
                            <div style="height: 50rem;width: 230rem;display: flex;justify-content: space-between;align-items: center">
                                <input :onkeydown="clearInf1" v-model="verifyCodeInput" id="inputVerifyCode" type="text" style="border: 1rem darkgrey solid;width: 100px;border-radius: 5rem;background-color: whitesmoke;" disabled="disabled">
                                <button @click="sendVerifyCodeForCheck" style="background-color: rgb(252,237,242);color: rgb(241,1,128);border-radius: 2px;border: 1rem rgb(241,1,128) solid;cursor: pointer">获取验证码</button>
                            </div>
                            <check-outlined v-if="false"></check-outlined>
                        </div>
                        <span>{{step1Inf}}</span>
                        <div style="height: 50rem;width: 350rem;display: flex;justify-content: space-evenly;align-items: center">
                            <button @click="step2" style="background-color: rgb(241,1,128);color: white;border-radius: 2rem;border: 1rem rgb(241,1,128) solid;cursor: pointer;width: 150rem;height: 32rem">下一步</button>
                        </div>
                    </div>
                    <div v-if="step2Flag" style="height: 200px;width: 300px;display: flex;flex-direction: column;justify-content: space-evenly">
                        <div style="height: 50rem;width: 350rem;display: flex;justify-content: space-between;align-items: center">
                            <div style="height: 30rem;width: 100rem;display: flex;justify-content: flex-end;align-items: center">
                                <span>输入登录名：</span>
                            </div>
                            <div style="height: 50rem;width: 230rem;display: flex;justify-content: space-between;align-items: center">
                                <input :onblur="checkLoginName" v-model="loginName" type="text" style="border: 1rem darkgrey solid;width: 100rem;border-radius: 5rem;background-color: whitesmoke;" >
                            </div>
                        </div>
                        <span>{{step2Inf}}</span>
                        <div style="height: 50px;width: 350rem;display: flex;justify-content: space-evenly;align-items: center">
                            <button @click="step3" style="background-color: rgb(241,1,128);color: white;border-radius: 2rem;border: 1rem rgb(241,1,128) solid;cursor: pointer;width: 150rem;height: 32rem">设置登录名</button>
                        </div>
                    </div>
                    <div v-if="step3Flag" style="height: 200rem;width: 300rem;display: flex;flex-direction: column;justify-content: space-evenly">
                        <div style="height: 50rem;width: 350rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                            <span style="font-size: 15rem;"><CheckOutlined style="color:rgb(35,194,121) "></CheckOutlined>登录名设置成功</span>
                            <router-link to="/mainHeader/UserAccountCenter/modifyUserInf">返回</router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import {reactive, ref} from "vue";
  import sendEmail from "../../Internet/home/user/login/sendVerifyCode";
  import $ from "jquery";
  import {CheckOutlined} from '@ant-design/icons-vue';
  import selectUserName from "../../Internet/home/user/select/selectUserName";
  //import modifyPassword from "../../Internet/home/modifyPassword/modifyPassword";
  import modifyUser from "../../Internet/home/user/modifyUser/modifyUser";
  //import {getCurrentInstance} from "@vue/runtime-core";
  import { getCurrentInstance } from "@vue/runtime-core";


  export default {
    name: "ModifyUserName",
    components:{
      CheckOutlined,
    },
    setup(){
      const internalInstance = getCurrentInstance() // works
      let loginEmail=ref('')
      let user1=reactive(JSON.parse(sessionStorage.getItem('user')))

      console.log(user1);

      let verifyCodeInput=ref('')
      let loginName=ref('')

      let step1IsCheck=ref(true)
      let step2IsCheck=ref(true)
      let step3IsCheck=ref(true)

      let step1Flag=ref(true)
      let step2Flag=ref(false)
      let step3Flag=ref(false)

      let step1Inf=ref('')
      let step2Inf=ref('')

      if(user1!==null&&user1!==undefined) {
          //中间4位加密显示出来
          let result = /^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
          loginEmail.value = (String(user1.email)).replace(result, "$1****$3$4");
      }

      let verifyCode=ref('')

      function sendVerifyCodeForCheck(){
        sendEmail(user1.email).then(res=>{
          console.log(res);
          verifyCode.value=res.data.verifyCode
          document.getElementById('inputVerifyCode').removeAttribute('disabled')
        })
      }


      function step2() {
        if(verifyCodeInput.value==''){
          step1Inf.value='请输入验证码'
        }else if(verifyCodeInput.value==verifyCode.value){
          step1Flag.value=false
          step2Flag.value=true
          step1IsCheck.value=false
          $('#circle2Content').text('...')
          $("#circle1").css('backgroundColor',"rgb(35,194,121)");
          $("#line1").css('backgroundColor',"rgb(35,194,121)");
        }else {
          step1Inf.value='验证码错误'
        }
      }

      let loginNameFlag=false

      function checkLoginName() {
        console.log(loginName.value);
        selectUserName(loginName.value).then(res=>{
          if(res.code==1){
            step2Inf.value='该用户名已存在，请重新输入'
          }else {
            loginNameFlag=true
          }
        })
      }

      function step3() {
        if(loginName.value==''){
          step2Inf.value='请输入用户名'
        }else if(loginNameFlag==false){
          step2Inf.value='该用户名已存在，请重新输入'
        }else {
          user1.username=loginName.value
          modifyUser(user1).then((res)=>{

            internalInstance.proxy.resetSetItem('user',JSON.stringify(user1))

            user1=res.data
            sessionStorage.setItem('user',JSON.stringify(user1))
            step2Flag.value=false
            step3Flag.value=true
            step2IsCheck.value=false
            $('#circle3Content').text('...')
            $("#circle2").css('backgroundColor',"rgb(35,194,121)");
            $("#line2").css('backgroundColor',"rgb(35,194,121)");

          })
        }
      }

      function clearInf1(){
        step1Inf.value=''
      }

      return {
        loginEmail,
        sendVerifyCodeForCheck,
        step2,
        step3,
        loginName,
        step1IsCheck,
        step2IsCheck,
        step3IsCheck,
        verifyCodeInput,
        step1Flag,
        step2Flag,
        step3Flag,
        checkLoginName,
        step1Inf,
        step2Inf,
        clearInf1
      }
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