<template>
    <div style="width: 850rem;height: 450rem;display: flex;flex-direction: column;align-items: center;justify-content: space-between;font-size:14rem">
        <div style="width: 850rem;height: 150rem;background-color: white;display: flex;flex-direction: column;align-items: center;justify-content: space-evenly">
            <div style="width: 800rem;height: 100rem;display: flex;justify-content: space-between">
                <img :src="userImage" style="width: 100rem;height: 100rem;border-radius: 50%">
                <input class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="onchangeImgFun">
                <div style="width:650rem;height: 100rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                    <div style="width: 650rem;height: 100rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                        <div style="width: 330rem;height: 20rem;display: flex;justify-content: space-between;align-items: flex-start">
                            <span style="font-weight: bold;font-size: 18rem">唯品会会员</span>
                            <span style="font-size: 15rem">({{loginName}})</span>
                            <span style="color: grey;font-size: 15rem">你好！</span>
                            <router-link to="/mainHeader/UserAccountCenter/modifyUserInf">修改信息</router-link>
                        </div>
                        <div>年龄：{{userAge}}</div>
                        <div>性别：{{userSex}}</div>
                    </div>
                </div>
            </div>
        </div>
        <div style="width: 850rem;height: 290rem;background-color: white;display: flex;flex-direction: column;justify-content: space-between">
            <div style="width: 100%;height: 40rem;background-color: whitesmoke;border-bottom: 1rem lightgrey solid;display: flex;justify-content: space-evenly">
                <div style="width: 800rem;display: flex;justify-content: space-between;align-items: center">
                    <span style="width: 100rem;display: flex;justify-content: space-evenly;font-size: 15rem;font-weight: bold;border-left: 2rem rgb(241,1,128) solid">我的订单</span>
                </div>
            </div>
            <div style="width: 100%;height: 240rem;display: flex;justify-content: space-evenly">
                <div style="width: 140rem;height: 100%;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                    <img :src="logo1" style="max-width: 100rem">
                    <p>待付款</p>
                </div>
                <div style="width: 140rem;height: 100%;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                    <img :src="logo2" style="max-width: 100rem">
                    <p>待收货</p>
                </div>
                <div style="width: 140rem;height: 100%;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                    <img :src="logo3" style="max-width: 100rem">
                    <p>退换/售后</p>
                </div>
                <div style="width: 140rem;height: 100%;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                    <img :src="logo4" style="max-width: 100rem">
                    <p>全部订单</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

  import {ref} from "vue";
  import axios from 'axios'
  import { getCurrentInstance } from "@vue/runtime-core";

  export default {
    name: "UserAccountInf",
    setup(){
      const internalInstance = getCurrentInstance() // works
      let loginName=ref('')
      let user1=JSON.parse(sessionStorage.getItem('user'))
      let userAge=ref('')
      let userSex=ref('')
      let userImage=ref('')
      let logo1=require('../../assets/img/2021-07-20_163250.png')
      let logo2=require('../../assets/img/2021-07-20_163016.png')
      let logo3=require('../../assets/img/2021-07-20_163216.png')
      let logo4=require('../../assets/img/2021-07-20_163235.png')
      if(user1!==null&&user1!==undefined) {
        if (user1.username !== null && user1.username !== undefined) {
          loginName.value = user1.username
        } else {
          //中间4位加密显示出来
          let result = /^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
          loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
        }
        if (user1.age !== null && user1.age !== undefined) {
          userAge.value = user1.age+'岁'
        } else {
          userAge.value='未设置'
        }
        if (user1.sex !== null && user1.sex !== undefined) {
          userSex.value = user1.sex
        } else {
          userSex.value='未设置'
        }
        if (user1.image !== null && user1.image !== undefined) {
          userImage.value = require('../../assets/img/'+user1.image)
        } else {
          userImage.value=require('../../assets/img/3f6d96131fd462118c191c644ca0e3dd_38x38_90.png')
        }
      }

      function onchangeImgFun (e) {
        console.log(111)
        let file = e.target.files[0]

        event.preventDefault()

        let formData = new FormData();

        formData.append('image',file);//键名要和后台一致

        formData.append('id',Number(JSON.parse(sessionStorage.getItem('user')).userId));


        const instance=axios.create({
          withCredentials: true
        })
        instance.post('/servlet/user/uploadImage',formData).then(res=>{
          if(res.data.code==0){
            user1.image=res.data.data
            console.log(user1);
            internalInstance.proxy.resetSetItem('user',JSON.stringify(user1))

            console.log(JSON.parse(sessionStorage.getItem('user')));
          }
          // sessionStorage.setItem('user',JSON.stringify(res.data.user))
        })

        // base64方法 2
        var reader = new FileReader()
        reader.readAsDataURL(file) // 读出 base64
        reader.onloadend = function () {
          // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
          var dataURL = reader.result
          userImage.value = dataURL
          // 下面逻辑处理
        }
      }

      return {
        loginName,
        userAge,
        userSex,
        userImage,
        logo1,
        logo2,
        logo3,
        logo4,
        onchangeImgFun
      }
    }
  }
</script>

<style scoped>
    .upload-btn{
        position: absolute;
        opacity: 0;
        width: 100rem;
        height: 100rem;
        cursor: pointer;
        /* 通过定位把input放在img标签上面，通过不透明度隐藏 */
    }
</style>