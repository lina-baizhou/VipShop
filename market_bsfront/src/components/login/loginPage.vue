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
        <a-row style="height: 600px;background-color: rgb(254,242,198)">
            <a-col :xs="0" :sm="0" :md="2" :lg="2" :xl="2" >

            </a-col>
            <a-col :xs="0" :sm="0" :md="11" :lg="10" :xl="10" >
                <div>
                    <img :src="photo" style="max-width: 500px;margin-top: 100px">
                </div>
            </a-col>
            <a-col :xs="24" :sm="22" :md="11" :lg="10" :xl=10 style="display: flex;justify-content: flex-end">
                <router-view></router-view>
            </a-col>
            <a-col :xs="0" :sm="2" :md="0" :lg="2" :xl=2 >

            </a-col>
        </a-row>
    </div>
</template>

<script>
  export default {
    name: "Login",

    setup() {
      let logo = require("../../assets/img/2021-07-16_214731.png")
      let description = require("../../assets/img/2021-07-16_214845.png")
      let photo = require("../../assets/img/2021-07-16_220309.png")


      return {logo, description, photo}
    }
  }

</script>

<style scoped>

</style>