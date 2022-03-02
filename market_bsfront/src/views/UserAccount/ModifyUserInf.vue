<template>
    <div style="width: 850rem;height: auto;display: flex;flex-direction: column;align-items: center;justify-content: space-between;font-size: 13rem">
        <div style="border-left: 2rem rgb(241,1,128) solid;width: 100%;height: 20rem;display: flex;justify-content: flex-start;font-size: 12rem">
            <div style="width: 170rem;display: flex;justify-content: space-evenly;align-items: center">
                <span ><router-link style="color: black;cursor: pointer" to="/mainHeader/UserAccountCenter/userInf">我的唯品会</router-link></span>
                <span>></span>
                <span>我的资料</span>
            </div>
        </div>
        <div style="width: 850rem;height: 510rem;background-color: white;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
            <div style="width: 750rem;height: 150rem;display: flex;justify-content: flex-start;border-bottom: 1rem lightgrey solid">
                <div style="width: 290rem;height: 150rem;display: flex;justify-content: space-between;align-items: center;">
                    <input class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="onchangeImgFun">
                    <img :src="userImage" style="width: 100rem;height: 100rem;border-radius: 50%">

                    <span style="font-weight: bold;font-size: 15rem">唯品会会员</span>
                    <span style="font-size: 12rem">({{loginName}})</span>
                </div>
            </div>
            <div style="width: 800rem;height: 300rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center;border-bottom:1rem lightgrey solid ">
                <div style="width: 100%;height: 300rem;display: flex;flex-direction:column;justify-content: space-evenly;align-items: flex-start">
                    <div style="width: 230rem;height: 20rem;display: flex;justify-content: flex-start;align-items: center">
                        <span style="font-weight: bold;color:  rgb(241,1,128);width: 20rem">*</span>
                        <span style="width: 60rem;display: flex;justify-content: flex-end">
                            <span>登录名：</span>
                        </span>
                        <span style="width: 150rem;display: flex;justify-content: space-between" v-if="userLoginNameFlag">
                            <span>{{loginName}}</span>
                            <router-link to="/mainHeader/UserAccountCenter/modifyUserName">修改登录名</router-link>
                        </span>
                        <a v-else><router-link to="/mainHeader/UserAccountCenter/modifyUserName">立即设置</router-link></a>
                    </div>
                    <div style="width: 200rem;height: 20rem;display: flex;justify-content: flex-start;align-items: center">
                        <span style="font-weight: bold;color:  rgb(241,1,128);width: 20rem">*</span>
                        <span style="width: 60rem;display: flex;justify-content: flex-end">
                            <span>性别：</span>
                        </span>
                        <span style="width: 100rem;display: flex;justify-content: space-between">
                            <span><input style="width: 12rem;height: 12rem" type="radio" name="sex" value="男" v-model="user1.sex" title="男" >男</span>
                            <span><input style="width: 12rem;height: 12rem" type="radio" name="sex" value="女" v-model="user1.sex" title="女" >女</span>
                        </span>
                    </div>
                    <div style="width: 180rem;display: flex;justify-content: space-between;align-items: flex-start;z-index:2;">
                        <span style="font-weight: bold;color:  rgb(241,1,128);width: 20rem">*</span>
                        <span style="width: 60rem;display: flex;justify-content: flex-end">
                            <span>年龄：</span>
                        </span>
                        <div style="width: 100rem;display: flex;justify-content: space-between;align-items: flex-start;">
                            <div style="width: 100%;display: flex;justify-content: space-between;align-items: flex-start;position: absolute">
                                <select name="age" style="width: 100rem;"  v-model="user1.age" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                                    <option v-for="index of 120" :key="index" :value="index">{{index}}</option>
                                </select>
                            </div>

                        </div>
                    </div>
                    <div style="width: 340rem;height: 20rem;display: flex;justify-content: flex-start;align-items: center">
                        <span style="font-weight: bold;color:  rgb(241,1,128);width: 20rem">*</span>
                        <span style="width: 60rem;display: flex;justify-content: flex-end">
                            <span>邮箱：</span>
                        </span>
                        <span style="width: 170rem;display: flex;justify-content: space-between">
                            <span>{{user1.email}}</span>
                            <router-link to="/mainHeader/UserAccountCenter/modifyEmail">修改</router-link>
                        </span>
                    </div>
                </div>
            </div>
            <div style="width: 800rem;height: 60rem;display: flex;justify-content: space-evenly;align-items: center">
                <button @click="modifyUserInf" style="border: 0rem;background-color: rgb(244,51,153);width: 100rem;height: 30rem;font-size: 20rem;color:white;cursor: pointer">提交</button>
            </div>
        </div>
    </div>
</template>

<script>
  import {ref,reactive} from "vue";
  import axios from "axios";
  import modifyUser from "../../Internet/home/user/modifyUser/modifyUser";
  import { getCurrentInstance } from "@vue/runtime-core";

  export default {
    name: "ModifyUserInf",
    setup: function () {
      const internalInstance = getCurrentInstance() // works


      let loginName = ref('')
      let user1 = reactive(JSON.parse(sessionStorage.getItem('user')))
      let userAge = ref('')
      let userSex = ref('')
      let userImage = ref('')
      let userLoginNameFlag = ref(false)

      if (user1 !== null && user1 !== undefined) {
        if (user1.username !== null && user1.username !== undefined) {
          loginName.value = user1.username
        } else {
          //中间4位加密显示出来
          let result = /^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
          loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
        }
        if (user1.age !== null && user1.age !== undefined) {
          userAge.value = user1.age + '岁'
        } else {
          userAge.value = '未设置'
        }
        if (user1.sex !== null && user1.sex !== undefined) {
          userSex.value = user1.sex
        } else {
          userSex.value = '未设置'
        }
        if (user1.image !== null && user1.image !== undefined) {
          userImage.value = require('../../assets/img/' + user1.image)
        } else {
          userImage.value = require('../../assets/img/3f6d96131fd462118c191c644ca0e3dd_38x38_90.png')
        }

        if (user1.username !== null && user1.username !== undefined) {
          userLoginNameFlag.value = true
        }
      }

      function modifyUserInf() {
        modifyUser(user1).then(res => {
          console.log(res);
          internalInstance.proxy.resetSetItem('user', JSON.stringify(res.data))
          alert("信息保存成功")
        })
      }

      function onchangeImgFun(e) {
        let file = e.target.files[0]

        event.preventDefault()

        let formData = new FormData();

        formData.append('image', file);//键名要和后台一致

        formData.append('id', Number(JSON.parse(sessionStorage.getItem('user')).userId));


        const instance = axios.create({
          withCredentials: true
        })
        instance.post('/servlet/user/uploadImage', formData).then(res => {
          if (res.data.code == 0) {
            user1.image = res.data.data
            sessionStorage.setItem('user', JSON.stringify(user1))
          }
        })

        // base64方法 2
        let reader = new FileReader()
        reader.readAsDataURL(file) // 读出 base64
        reader.onloadend = function () {
          // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
          let dataURL = reader.result
          userImage.value = dataURL
          // 下面逻辑处理
        }
      }


      return {
        loginName,
        userAge,
        userSex,
        user1,
        userImage,
        onchangeImgFun,
        userLoginNameFlag,
        modifyUserInf
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