<template>
    <div style="width: 233rem;height: 355rem;border:1rem lightgrey solid;margin-top: 20rem;display: flex;flex-direction: column;align-items: center;justify-content: flex-start">
        <slot name="deleteBtn" style="width: 20rem;height: 20rem;background-color: #a0a0a0">

        </slot>
        <slot name="goodsImg" style="width: 222rem;height: 222rem">

        </slot>
        <div style="width: 100%;height: 223rem;display: flex;flex-direction: column;align-items: center">
            <div style="width: 90%;height: 40rem;display: flex;justify-content: flex-start;align-items: center">
                <span style="font-size: 12rem;width: 50rem;height: 20rem;border-radius: 10rem;background:linear-gradient(to right,rgb(242,2,128),rgb(230,84,249));color: white">
                    特卖价
                </span>
                <slot name="goodsPrice" style="margin-left: 20rem;width: 30rem;height:auto;display: flex;justify-content: flex-end;align-items: flex-end">

                </slot>

            </div>
            <div style="font-size: 13rem;color: darkgrey;width: 90%;height: 30rem;display: flex;justify-content: flex-start;text-align: left;align-items: flex-end;flex-wrap: wrap">
                <slot name="goodsTitle">

                </slot>
            </div>
        </div>
    </div>
</template>

<script>
  export default {
    name: "Goods",
    setup(){
    }
  }
</script>

<style scoped>

</style>