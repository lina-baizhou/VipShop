<template>
    <div style="width: 100%;height: auto;display: flex;flex-direction: column;justify-content: space-between">
        <top-header></top-header>

        <left-function></left-function>

        <div style="height: 559.8333129882812rem;width:1072rem;background-color: #eff0f4;position: fixed;top: 36rem;left: 200rem;overflow-y: scroll">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>

  import {provide, ref} from "vue";
  import topHeader from "../../views/inf/topHeader";
  import leftFunction from "../../views/shop/leftFunction";
  // import findByUserId from "../../Internet/home/shop/find/findByUserId";

  export default {
    name: "shop",
    components:{
      topHeader,
      leftFunction
    },
    setup(){

      let loginFlag=ref(false)
      let loginName=ref('')

      let user1=JSON.parse(sessionStorage.getItem('user'))

      if(user1!==null){
        if(user1.username==null){
          let result=/^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
          loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
        }else {
          loginName.value=user1.username
        }
      }

      //给子组件传值
      provide("loginFlag",loginFlag)

      provide("loginName",loginName)
    }
  }
</script>

<style scoped>

</style>