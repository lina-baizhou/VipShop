<template>
    <div class="mainDiv" style="width: 1272rem;height: auto;display: flex;justify-content: center;align-items: flex-start;flex-wrap: wrap">
        <top-header></top-header>
        <div style="width: 1000rem;height: auto;display: flex;justify-content: space-between;align-items: center;flex-wrap: wrap">
            <img :src="logo">
            <div style="width: 320rem;height: 36rem;border: 2rem rgb(244,51,153) solid;display: flex;justify-content: space-evenly;align-items: center">
                <input style="width: 300rem;border: 0rem;outline: none;font-size: 15rem;" placeholder="请输入商品/店铺/品类">
                <div style="font-size:20rem;width: 56rem;height:100%;background-color: rgb(244,51,153);color: white;display: flex;justify-content: space-evenly;align-items: center">
                    <SearchOutlined></SearchOutlined>
                </div>
            </div>
        </div>

        <div style="height:auto;width: 1000rem;display: flex;justify-content: center;align-items: center;margin-top: -15rem">
            <div style="width: 100%;height:auto;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                <div style="width:100%;height: auto;display:flex;justify-content:space-between;align-items: flex-start;padding-top: 10rem">
                    <span class="spanLogo1" style="font-size: 14rem;font-weight: 700;color: white;height: 40rem;width: 25%;display: flex;justify-content: center;align-items: center">
                        1.确认订单信息
                    </span>
                    <span class="spanLogo2" style="font-size: 14rem;font-weight: 700;height: 40rem;width: 25%;color: white;display: flex;justify-content: center;align-items: center">
                        2.付款到支付宝
                    </span>
                    <span class="spanLogo3" style="font-size: 14rem;font-weight: 700;height: 40rem;width: 25%;color: #333333;display: flex;justify-content: center;align-items: center">
                        3.确认收货
                    </span>
                    <span class="spanLogo4" style="font-size: 14rem;font-weight: 700;color: #333333;height: 40rem;width: 25%;display: flex;justify-content: center;align-items: center">
                        4.双方评价
                    </span>
                </div>
            </div>
        </div>

        <div style="margin-top: 20rem;padding: 10rem;width: 1000rem;height: 300rem;display: flex;flex-direction: column;justify-content: center;align-items: center;background-color: rgb(252, 252, 252);border: 1rem lightgrey solid">
            <div style="width:auto;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center ">
                <div style="width: auto;height: auto;align-items: center;display: flex;justify-content:flex-start;align-items: center">
                    <img :src="successLogo" style="width: 50rem">
                    <span style="font-size: 30rem;font-family: 宋体">您已成功付款{{totalAmount}}元</span>
                </div>
                <span style="font-family: 宋体;font-size: 20rem;color: darkgrey;margin-top: 10rem">感谢您选择唯品会，我们会尽快将货物送到你手中!</span>
                <span style="width: 100%;margin-top: 30rem;display: flex;justify-content: space-between;align-items: center">
                     <router-link to="/mainHeader/UserAccountCenter/userOrder">
                         <button style="width: 150rem;height: 40rem;border-radius: 15rem;cursor: pointer;border: 1rem solid darkgrey;color: grey">
                             查看订单
                         </button>
                     </router-link>
                    <router-link to="/mainHeader/home">
                        <button style="width: 150rem;height: 40rem;border-radius: 15rem;cursor: pointer;border: 1rem solid darkgrey;color:grey">
                            返回首页
                        </button>
                    </router-link>
                </span>
            </div>
        </div>
    </div>
</template>

<script>
  import topHeader from "../../views/inf/topHeader";
  import {reactive, ref} from "@vue/reactivity";
  import {provide} from "@vue/runtime-core";
  import selectByUserId from "../../Internet/home/user/select/selectByUserId";
  let loginName=ref('')
  let user1=reactive({
    'username':'',
    'userId':'',
    'age':'',
    'identity':'',
    'image':'',
    'sex':'',
    'password':'',
    'email':''
  })
  export default {
    name: "PayPage",
    components:{
      topHeader
    },
    setup(){
      let user = JSON.parse(sessionStorage.getItem('user'))

      let loginFlag = ref(true)

      let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg = new RegExp("(^|&)" + 'total_amount' + "=([^&]*)(&|$)");
      let r = search.match(reg);

      let totalAmount=ref(unescape(r[2]))


      new Promise(()=>{
        if (user != null) {
          user1.username = user.username
          user1.userId = user.userId
          user1.age = user.age
          user1.identity = user.identity
          user1.image = user.image
          user1.sex = user.sex
          user1.password = user.password
          user1.email = user.email

          if (user1 !== null && user1 !== undefined) {
            loginFlag.value = false

            if (user1.username !== null && user1.username !== undefined) {
              loginName.value = user1.username
              console.log(loginName.value);
            } else {
              //中间4位加密显示出来
              let result = /^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
              this.loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
            }
          } else {
            loginFlag.value = true
          }
        } else {
          let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
          let reg = new RegExp("(^|&)" + 'userId' + "=([^&]*)(&|$)");
          let r = search.match(reg);

          selectByUserId(unescape(r[2])).then(res => {
            sessionStorage.setItem('user',JSON.stringify(res.data))
            user1.username = res.data.username
            user1.userId = res.data.userId
            user1.age = res.data.age
            user1.identity = res.data.identity
            user1.image = res.data.image
            user1.sex = res.data.sex
            user1.password = res.data.password
            user1.email = res.data.email

            if (user1 !== null && user1 !== undefined) {
              loginFlag.value = false


              if (user1.username !== null && user1.username !== undefined) {
                loginName.value = user1.username
              } else {
                //中间4位加密显示出来
                let result = /^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
                this.loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
              }
            } else {
              loginFlag.value = true
            }
          })
        }
      })

      //给子组件传值
      provide("loginFlag", loginFlag)

      provide("loginName", loginName)

      let logo=require('../../assets/img/2021-07-26_181336.png')
      let successLogo=require('../../assets/img/成功.svg')

      let reg3= new RegExp("(^|&)" + 'userId' + "=([^&]*)(&|$)");
      let r3 = search.match(reg3);
      let userId=unescape(r3[2])

      return{
        logo,
        successLogo,
        totalAmount,
        userId
      }
    },
  }
</script>

<style scoped>
    .spanLogo1{
        background-image: url("../../assets/img/步骤1 (3).svg");
        background-repeat: no-repeat;
        background-size:100% 100%;
    }
    .spanLogo2{
        background-image: url("../../assets/img/步骤2 (2).svg");
        background-repeat: no-repeat;
        background-size:100% 100%;
    }
    .spanLogo3{
        background-image: url("../../assets/img/步骤2.svg");
        background-repeat: no-repeat;
        background-size:100% 100%;
    }
    .spanLogo4{
        background-image: url("../../assets/img/步骤3.svg");
        background-repeat: no-repeat;
        background-size:100% 100%;
    }
</style>