<template>
    <div class="mainDiv" style="width: 100%;height: auto;display: flex;justify-content: center;align-items: flex-start;flex-wrap: wrap">
        <show-in-right></show-in-right>
        <div style="width: 100%;margin-top: 2rem;height: 173rem;display: flex;flex-direction: column;justify-content: center;align-items: flex-start;padding:0rem 130rem;background: url(//ugc.vipstatic.com/img/fav/bg-fav-banner.png?a6ffc2f8) center top no-repeat;">
            <p style="font: 42rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;margin-bottom: 14rem">
                我的特卖
            </p>
            <p style="font: 16rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;letter-spacing: 12rem;">
                DISCOUNT
            </p>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
  import showInRight from "../../views/inf/showInRight";
  export default {
    name: "mySale",
    components:{
      showInRight
    }
  }
</script>

<style scoped>

</style>