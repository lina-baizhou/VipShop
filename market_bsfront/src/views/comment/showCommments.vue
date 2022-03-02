<template>
    <div style="width: 100%;height: auto;display: flex;flex-direction: column;justify-content:flex-start;align-items: center;margin-top: 20rem">
        <div style="width: 100%;height: 44rem;display:flex;justify-content: flex-start;align-items: center;padding-left: 15rem;background-color: #eae8eb;">
            <h3 style="margin: 0;padding: 0">全部评价</h3>
        </div>
        <div style="width: 1000rem;height: 154rem;background-color: #fafafa;border: solid 1px #e5e5e5;display: flex;justify-content: center;align-items: center">
            <div style="width:auto;height: auto;padding: 6rem 35rem 15rem 35rem;display: flex;justify-content: flex-start;align-items: flex-start;border-right: 1px solid #e5e5e5;">
                <div style="width: 169rem;height: 69rem;display: flex;flex-direction: column;justify-content: center;align-items: center">
                    <div style="color: #de327f;font-family: 'Microsoft YaHei';font-size: 50rem">
                        {{goodsCommentInf.data.rate}}%
                    </div>
                    <span style="margin-top: -10rem">的用户对商品很满意</span>
                </div>
            </div>
            <div style="width: 622rem;height: 80rem;display: flex;justify-content: center;align-items: center;">
                <div @mouseenter="enterItem(0)" @mouseleave="leaveItem" @click="chooseItem(0)" class="chooseCommentItem" style="cursor: pointer;width: auto;height: auto;border-radius: 5rem;padding: 7rem 12rem 5rem;background-color: #f10180;color: #fff;border: 1px solid #f10180;margin-right: 10rem;font-size: 12rem;font-family: \5FAE\8F6F\96C5\9ED1,sans-serif;">
                    <span>全部-按默认排序</span>
                </div>
                <div @mouseenter="enterItem(1)" @mouseleave="leaveItem" @click="chooseItem(1)" class="chooseCommentItem" style="cursor: pointer;width: auto;height: auto;border-radius: 5rem;padding: 7rem 12rem 5rem;color: #333;border: solid 1px #cbcbcb;margin-right: 10rem;font-size: 12rem;font-family: \5FAE\8F6F\96C5\9ED1,sans-serif;">
                    <span>最新-按时间排序</span>
                </div>
                <div @mouseenter="enterItem(2)" @mouseleave="leaveItem" @click="chooseItem(2)" class="chooseCommentItem" style="cursor: pointer;width: auto;height: auto;border-radius: 5rem;padding: 7rem 12rem 5rem;color: #333;border: solid 1px #cbcbcb;margin-right: 10rem;font-size: 12rem;font-family: \5FAE\8F6F\96C5\9ED1,sans-serif;">
                    <span>有图（{{goodsCommentInf.data.haveImageCount}}）</span>
                </div>
                <div @mouseenter="enterItem(3)" @mouseleave="leaveItem" @click="chooseItem(3)" class="chooseCommentItem" style="cursor: pointer;width: auto;height: auto;border-radius: 5rem;padding: 7rem 12rem 5rem;color: #333;border: solid 1px #cbcbcb;margin-right: 10rem;font-size: 12rem;font-family: \5FAE\8F6F\96C5\9ED1,sans-serif;">
                    <span>追加（{{goodsCommentInf.data.additionalCount}}）</span>
                </div>
            </div>
        </div>

        <div v-for="(item,index) in comments.commentList" :key="index" style="width: 1000rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;border-top: solid 1px #e5e5e5;margin-bottom: 20rem">
            <div style="width: 235rem;height: 100rem;padding: 30rem 0rem 30rem 15rem;display: flex;justify-content: flex-start;align-items: flex-start">
                <img :src="comments.userList[index].image" style="width: 40rem;height: 40rem;border-radius: 50%;background-color: lightgrey">
                <span style="width: 160rem;margin-left: 20rem;height: 40rem;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #333;">
                    {{comments.userList[index].username}}
                </span>
            </div>
            <div style="width: 630rem;height: auto;padding: 30rem 0rem 30rem 15rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;margin-left: 60rem">
                <div style="width: 100%;height: 40rem;display: flex;justify-content: flex-start;align-items: center">
                    <span style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #666;">
                        {{item.time}}
                    </span>
                    <span style="width: auto;margin-left: 20rem;height: auto;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #666;">
                        {{comments.goodsSpecificationsList[index].goodsType1}}
                    </span>
                    <span v-if="comments.goodsSpecificationsList[index].goodsType2!=null" style="width: auto;margin-left: 20rem;height: auto;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #666;">
                        {{comments.goodsSpecificationsList[index].goodsType2}}
                    </span>
                </div>
                <div style="width: 619rem;height: auto;text-align: left;line-height: 10rem;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #333;">
                    {{item.content}}
                </div>

                <div v-if="item.commentPhoto!=null" style="width: 619rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 15rem">
                    <div v-for="(item1,index1) in item.commentPhoto" :key="index1" style="width: 78rem;height: 78rem;padding: 3rem;border: 1px solid #ccc;margin-right: 10rem;display: flex;justify-content: center;align-items: center">
                        <img :src="item1" style="width: 72rem;height: 72rem;background-color: lightgrey">
                    </div>
                </div>

                <hr v-if="item.additionalComment!=null" style="margin-top: 30rem;width: 100%;height: 1px; background-image: -webkit-linear-gradient(left,#ccc 0,#ccc 50%,transparent 50%); background-image: linear-gradient(to right,#ccc 0,#ccc 50%,transparent 50%);background-size: 8px 8px;background-repeat: repeat-x;border: 0;">

                <div v-if="item.additionalComment!=null" style="width: 100%;height: 40rem;display: flex;justify-content: flex-start;align-items: center">
                    <span style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #ccc;">
                        用户追评&nbsp;
                        {{item.additionalTime}}
                    </span>
                </div>
                <div v-if="item.additionalComment!=null" style="width: 619rem;height: auto;text-align: left;line-height: 10rem;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #333;">
                    {{item.additionalComment}}
                 </div>

                <div v-if="item.additionalPhoto!=null" style="width: 619rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 15rem">
                    <div v-for="(item1,index1) in item.additionalPhoto" :key="index1" style="width: 78rem;height: 78rem;padding: 3rem;border: 1px solid #ccc;margin-right: 10rem;display: flex;justify-content: center;align-items: center">
                        <img :src="item1" style="width: 72rem;height: 72rem;background-color: lightgrey">
                    </div>
                </div>

                <hr v-if="item.sellerRestore!=null" style="margin-top: 30rem;width: 100%;height: 1px; background-image: -webkit-linear-gradient(left,#ccc 0,#ccc 50%,transparent 50%); background-image: linear-gradient(to right,#ccc 0,#ccc 50%,transparent 50%);background-size: 8px 8px;background-repeat: repeat-x;border: 0;">
                <div v-if="item.sellerRestore!=null" style="width: 619rem;height: auto;text-align: left;line-height: 10rem;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #333;">
                    商家回复：{{item.sellerRestore}}
                </div>
            </div>
        </div>

        <div v-if="comments.commentList.length==0" style="width: 1000rem;height: 151rem;display: flex;justify-content: center;align-items: center">
            <img :src="warnLogo" style="height: 35rem">
            <span style="font-size: 12rem;margin-left: 20rem">暂时没有评价</span>
        </div>

        <div v-if="comments.page.pages>0" style="width: 1000rem;height: 32rem;display: flex;align-items: center;justify-content: center">
            <button @click="toChoosePage(-1)" class="lastPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;margin-right: 10rem;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                上一页
            </button>
            <div style="margin-right: 10rem;width: 32rem;color: white;height: 32rem;border-radius: 3rem;background-color:#f10180;display: flex;justify-content: center;align-items: center">
                {{choosePage}}
            </div>
            <button v-if="comments.page.pages>1" @click="toChoosePage(1)" class="nextPageBtn" style="border:1rem solid grey;cursor: pointer;width: 62rem;height: 32rem;border-radius: 3rem;background-color: #ffffff">
                下一页
            </button>
            <button v-else @click="toChoosePage(1)" class="nextPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;margin-right: 10rem;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                下一页
            </button>
        </div>
    </div>
</template>

<script>
    import $ from 'jquery'
    import {inject} from "@vue/runtime-core";
    import getAllCommentsInf from "../../Internet/home/comment/select/getAllCommentsInf";
    import getCommentListForShow from "../../Internet/home/comment/select/getCommentListForShow";
    import {reactive, ref} from "@vue/reactivity";
    import {watch} from 'vue'
    export default {
      name: "showComments",
      setup(){
        let chooseIndex=0;
        function enterItem(index) {
          if(index!==chooseIndex){
            $('.chooseCommentItem').eq(index).css('color','#f10180')
            $('.chooseCommentItem').eq(index).css('border','solid 1px #f10180')
          }
        }

        let comments=reactive({
          commentList:{},
          goodsSpecificationsList:{},
          userList:{},
          page:{}
        })

        let goodsCommentInf=reactive({
          data:{

          }
        })


        let goodsId = inject("goodsId")


        getAllCommentsInf(goodsId.value).then(res=>{
          goodsCommentInf.data=res.data
        })

        function getCommentList(item,currentPage){
          getCommentListForShow(goodsId.value,item,currentPage).then(res=>{
            comments.commentList=res.data.commentList.records
            comments.page=res.data.commentList
            comments.goodsSpecificationsList=res.data.goodsSpecificationsList
            comments.userList=res.data.userList
            for(let i=0;i<comments.userList.length;i++){
              comments.userList[i].image=require('../../assets/img/'+comments.userList[i].image)

              if(comments.commentList[i].commentPhoto!==null&& comments.commentList[i].commentPhoto!==undefined){
                let photos=JSON.parse(comments.commentList[i].commentPhoto)
                comments.commentList[i].commentPhoto=[]
                for(let j=0;j<photos.length;j++){
                  comments.commentList[i].commentPhoto[j]=require('../../assets/img/'+photos[j])
                }
              }
              if(comments.commentList[i].additionalPhoto!==null&& comments.commentList[i].additionalPhoto!==undefined){
                let photos=JSON.parse(comments.commentList[i].additionalPhoto)
                comments.commentList[i].additionalPhoto=[]
                for(let j=0;j<photos.length;j++){
                  comments.commentList[i].additionalPhoto[j]=require('../../assets/img/'+photos[j])
                }
              }
            }

          })
        }

        getCommentList('null',0)

        watch(
            goodsId,
            ()=>{
              getCommentList('null',0)
              getAllCommentsInf(goodsId.value).then(res=>{
                goodsCommentInf.data=res.data
              })
            }
        )

        function leaveItem() {
          for(let i=0;i<4;i++){
            if(i!==chooseIndex){
              $('.chooseCommentItem').eq(i).css('color','#333')
              $('.chooseCommentItem').eq(i).css('border','solid 1px #cbcbcb')
            }
          }
        }

        function chooseItem(index) {
          if(index!==chooseIndex){
            $('.chooseCommentItem').eq(chooseIndex).css('color','#333')
            $('.chooseCommentItem').eq(chooseIndex).css('border','solid 1px #cbcbcb')
            $('.chooseCommentItem').eq(chooseIndex).css('background-color','white')

            $('.chooseCommentItem').eq(index).css('color','#fff')
            $('.chooseCommentItem').eq(index).css('border','1px solid #f10180')
            $('.chooseCommentItem').eq(index).css('background-color','#f10180')

            if(index==0){
              getCommentList('null',0)
            }else if(index==1){
              getCommentList('时间',0)
            }else if(index==2){
              getCommentList('有图',0)
            }else if(index==3){
              getCommentList('追加',0)
            }
            choosePage.value=1
            chooseIndex=index
          }
        }

        let warnLogo=require('../../assets/img/警告.svg');

        let choosePage=ref(1);
        watch(
            choosePage,
            ()=>{
              if(choosePage.value==1){
                $('.lastPageBtn').attr("disabled",true);
                $('.lastPageBtn').css('border','1rem solid lightgrey')
                $('.lastPageBtn').css('color','#a0a0a0')
                $('.lastPageBtn').css('cursor','not-allowed')
              }else {
                $('.lastPageBtn').attr("disabled",false);
                $('.lastPageBtn').css('border','border:1rem solid grey')
                $('.lastPageBtn').css('color','black')
                $('.lastPageBtn').css('cursor','pointer')
              }
              if(choosePage.value==comments.page.pages){
                $('.nextPageBtn').attr("disabled",true);
                $('.nextPageBtn').css('border','1rem solid lightgrey')
                $('.nextPageBtn').css('color','#a0a0a0')
                $('.nextPageBtn').css('cursor','not-allowed')
              }else {
                $('.nextPageBtn').attr("disabled",false);
                $('.nextPageBtn').css('border','border:1rem solid grey')
                $('.nextPageBtn').css('color','black')
                $('.nextPageBtn').css('cursor','pointer')
              }
            }
        )

        function toChoosePage(index) {
          if(index+choosePage.value<=0){
            return;
          }else if(index+choosePage.value>comments.page.pages){
            return;
          }else {
            choosePage.value=index+choosePage.value
            if(chooseIndex==0){
              getCommentList('null',choosePage.value)
            }else if(chooseIndex==1){
              getCommentList('时间',choosePage.value)
            }else if(chooseIndex==2){
              getCommentList('有图',choosePage.value)
            }else if(chooseIndex==3){
              getCommentList('追加',choosePage.value)
            }
          }
        }

        return{
          enterItem,
          leaveItem,
          chooseItem,
          comments,
          goodsCommentInf,
          warnLogo,
          choosePage,
          toChoosePage
        }
      }
    }
</script>

<style scoped>
    .lastPageBtn:focus{
        background-color: gray;
    }
</style>