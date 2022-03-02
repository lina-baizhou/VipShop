<template>
    <div style="width: 100%;height: auto;padding-top: 20rem;padding-bottom: 20rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center;font-size: 12rem">
        <div style="width: 95%;height: auto;background-color: white;border-radius: 5rem;display: flex;justify-content: space-evenly;align-items: flex-start;flex-wrap: wrap">
            <div style="width: 100%;height: 40rem;display: flex;border-bottom: 1rem #eff0f4 solid;padding-left: 10rem;display: flex;align-items: center;font-size: 18rem">
                评论管理
            </div>

            <div style="width: 100%;border-bottom: 1rem #eff0f4 solid;height: auto;display: flex;justify-content: flex-start;align-items: center;flex-wrap: wrap;padding: 20rem">
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">商品编号：</span>
                    <input v-model="goodsId"  @input="goodsId= Number($event.target.value.replace(/\D+/, ''))" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center;">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">卖家用户名：</span>
                    <input v-model="username" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: flex-start;z-index: 2">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">评论类型：</span>
                    <select v-model="searchItem"  name="province" style="margin-left: 120rem;position: absolute;width: 170rem;min-height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                        <option selected value="全部">请选择评论类型</option>
                        <option value="好评">好评</option>
                        <option value="中评">中评</option>
                        <option value="差评">差评</option>
                        <option value="追加">追加</option>
                        <option value="有图">有图</option>
                        <option value="待回复">待回复</option>
                        <option value="已回复">已回复</option>
                    </select>
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center;margin-top: 20rem">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">商品名称：</span>
                    <input v-model="goodsTitle" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: auto;height:30rem;display: flex;justify-content: flex-start;align-items: center;margin-top: 20rem">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">评论时间：</span>
                    <div style="width:190rem;font-size: 14rem;font-family:宋体;margin: 0;padding:10rem;border-radius: 3rem;outline-color: #1d94d1;display: flex;justify-content: flex-start;align-items: flex-start">
                        <select  style="position: absolute;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in lastYearList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 50rem;">年</span>
                        <select v-model="lastMonth" style="position: absolute;margin-left: 65rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in lastMonthList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 30rem">月</span>
                        <select v-model="lastDay" style="position: absolute;margin-left: 110rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in lastDayList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 40rem">日</span>
                    </div>
                    ____
                    <div style="width:190rem;font-size: 14rem;font-family:宋体;margin: 0;padding:10rem;border-radius: 3rem;outline-color: #1d94d1;display: flex;justify-content: flex-start;align-items: flex-start">
                        <select v-model="nextYear" style="position: absolute;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in nextYearList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 50rem;">年</span>
                        <select v-model="nextMonth" style="position: absolute;margin-left: 65rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in nextMonthList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 30rem">月</span>
                        <select v-model="nextDay" style="position: absolute;margin-left: 110rem;border:none;outline: none;margin-top: 5rem"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                            <option v-for="item in nextDayList.list" :key="item" :value="item">{{item}}</option>
                        </select>
                        <span style="margin-left: 40rem">日</span>
                    </div>

                </div>
            </div>

            <div style="width: 100%;height: auto;padding: 10rem;justify-content: center;align-items: flex-start;flex-wrap: wrap">
                <div style="width: 100%;height: 50rem;display: flex;justify-content: space-between;align-items: center">
                    <button @click="searchShopComments" style="cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem  rgb(32,157,189) solid;background-color:  rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                        <img :src="searchLogo" style="height: 15rem">&nbsp;查询评论
                    </button>
                    <div  v-if="comments.list.length!=0" style="width: 400rem;height: 32rem;display: flex;align-items: center;justify-content: flex-end">
                        <span style="font-size: 14rem;color: #a0a0a0;margin-right: 20rem">共查询到
                            <span style="font-size: 18rem;color: rgb(32,157,189)">&nbsp;{{page.data.total}}&nbsp;</span>
                            条评论
                        </span>
                        <span style="font-size: 14rem;color: #a0a0a0;margin-right: 20rem">
                            <span style="font-size: 14rem;color: rgb(32,157,189)">{{page.data.current}}</span>/{{page.data.pages}}
                        </span>
                        <button v-if="page.data.current>1" @click="toChoosePage(-1)" class="nextPageBtn" style="margin-right: 10rem;border:1rem solid grey;cursor: pointer;width: 62rem;height: 32rem;border-radius: 3rem;background-color: #ffffff">
                            上一页
                        </button>
                        <button v-else  class="nextPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;margin-right: 10rem;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                            上一页
                        </button>
                        <div style="margin-right: 10rem;width: 32rem;color: white;height: 32rem;border-radius: 3rem;background-color: rgb(32,157,189);display: flex;justify-content: center;align-items: center">
                            {{page.data.current}}
                        </div>
                        <button v-if="page.data.current<page.data.pages" @click="toChoosePage(1)" class="nextPageBtn" style="border:1rem solid grey;cursor: pointer;width: 62rem;height: 32rem;border-radius: 3rem;background-color: #ffffff">
                            下一页
                        </button>
                        <button v-else class="nextPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                            下一页
                        </button>
                    </div>
                </div>
            </div>

            <div style="width: 100%;height: auto;margin-top: 20rem;padding: 10rem">
                <div v-if="comments.list.length==0" style="width: 100%;height: 100rem;display: flex;justify-content: center;align-items: center">
                    <img :src="warnLogo" style="height: 35rem">
                    <span style="font-size: 12rem;margin-left: 20rem">无符合该条件的订单</span>
                </div>
                <div v-for="(item,index) in comments.list" :key="index" style="width: 1000rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;border-top: solid 1px #e5e5e5;margin-bottom: 20rem">
                    <div style="width: 135rem;height: 100rem;padding: 30rem 0rem 30rem 15rem;display: flex;justify-content: flex-start;align-items: flex-start">
                        <img :src="userList.list[index].image" style="width: 40rem;height: 40rem;border-radius: 50%;background-color: lightgrey">
                        <span style="width: 160rem;margin-left: 20rem;height: 40rem;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #333;">
                    {{userList.list[index].username}}
                </span>
                    </div>
                    <div style="width: 800rem;height: auto;padding: 30rem 0rem 30rem 15rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;margin-left: 60rem">
                        <div style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: center">
                            <span style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #666;">
                                {{item.time}}
                            </span>
                            <span style="margin-left: 20rem;width: 250rem;height: 20rem;word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: center;font-family:'Microsoft YaHei';">
                                {{goodsList.list[index].goodsTitle}}
                            </span>
                            <span v-if="goodsSpecificationsList.list[index].goodsType2==null" style="margin-left: 20rem;width: 220rem;height: 15rem;word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;text-align: left;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #666;">
                                {{goodsSpecificationsList.list[index].goodsType1}}
                            </span>
                            <span v-if="goodsSpecificationsList.list[index].goodsType2!=null" style="margin-left: 20rem;width: 220rem;height: 20rem;word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;text-align: left;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #666;">
                                {{goodsSpecificationsList.list[index].goodsType2}}
                            </span>
                            <button  @click="()=>{sellerRestoreFlag.list[index]=true}" v-if="sellerRestoreFlag.list[index]==false&&item.sellerRestore==null&&(item.content!=='系统默认好评'||item.additionalComment!==null)" style="margin-left: 20rem;cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem  rgb(32,157,189) solid;background-color:  rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                                回复评论
                            </button>
                            <button  @click="()=>{sellerRestoreFlag.list[index]=false}" v-if="sellerRestoreFlag.list[index]==true&&item.sellerRestore==null&&(item.content!=='系统默认好评'||item.additionalComment!==null)" style="margin-left: 20rem;cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem  rgb(32,157,189) solid;background-color:  rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                                取消回复
                            </button>
                            <button v-if="item.sellerRestore!=null&&(item.content!=='系统默认好评'||item.additionalComment!==null)&&modifySellerRestoreFlag.list[index]==false" @click="()=>{modifySellerRestoreFlag.list[index]=true}" style="margin-left: 20rem;cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem  rgb(32,157,189) solid;background-color:  rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                                修改回复
                            </button>
                            <button v-if="item.sellerRestore!=null&&(item.content!=='系统默认好评'||item.additionalComment!==null)&&modifySellerRestoreFlag.list[index]==true" @click="()=>{modifySellerRestoreFlag.list[index]=false}" style="margin-left: 20rem;cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem  rgb(32,157,189) solid;background-color:  rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                                放弃修改
                            </button>
                        </div>
                        <div style="width: 619rem;height: auto;display: flex;justify-content: flex-start">
                            <span  style="margin-top: 2rem;height:auto;text-align: left;color: #333333">
                              物流评分：{{item.logisticsCount}}
                            </span>
                            <span  style="margin-top: 2rem;margin-left: 30rem;height:auto;text-align: left;color: #333333">
                              服务评分：{{item.serviceCount}}
                            </span>
                            <span  style="margin-top: 2rem;margin-left: 30rem;height:auto;text-align: left;color: #333333">
                              描述评分：{{item.descriptionCount}}
                            </span>
                        </div>


                        <div style="width: 619rem;height: auto;margin-top: 15rem;text-align: left;line-height: 10rem;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #333;">
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
                                <span style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: center;font: 12rem/1.5 tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;color: #ccc;">
                                    用户追评&nbsp;
                                    {{item.additionalTime}}
                                </span>
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
                        <div v-if="(item.content!=='系统默认好评'||item.additionalComment!==null)" style="width: 619rem;height: auto;display: flex;justify-content: center;align-items: flex-start;flex-wrap: wrap">

                            <hr v-if="(item.sellerRestore==null&&sellerRestoreFlag.list[index]==true)||(item.sellerRestore!=null)"  style="margin-top: 10rem;width: 100%;height: 1px; background-image: -webkit-linear-gradient(left,#ccc 0,#ccc 50%,transparent 50%); background-image: linear-gradient(to right,#ccc 0,#ccc 50%,transparent 50%);background-size: 8px 8px;background-repeat: repeat-x;border: 0;">

                            <textarea v-model="commentList.list[index].sellerRestore" v-if="item.sellerRestore==null&&sellerRestoreFlag.list[index]==true" style="margin-top: 20rem;width: 100%;height: 100rem">

                            </textarea>

                            <span v-if="item.sellerRestore!=null&&modifySellerRestoreFlag.list[index]==false&&sellerRestoreFlag.list[index]==true" style="margin-top: 20rem;width: 100%;height:auto;text-align: left">
                                商家回复：{{item.sellerRestore}}
                            </span>

                            <textarea v-model="commentList.list[index].sellerRestore" v-if="item.sellerRestore!=null&&modifySellerRestoreFlag.list[index]==true" style="margin-top: 20rem;width: 100%;height: 100rem">

                            </textarea>

                            <button v-if="(item.sellerRestore==null&&sellerRestoreFlag.list[index]==true)||(item.sellerRestore!=null&&modifySellerRestoreFlag.list[index]==true)" @click="saveAndModifySellerRestore(commentList.list[index])"  style="margin-top: 15rem;cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem  rgb(32,157,189) solid;background-color:  rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                                保存回复
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import {reactive, ref} from "@vue/reactivity";
  import {watch} from 'vue'
  import searchShopGoodsComments from "../../../Internet/home/comment/select/searchShopGoodsComments";
  import saveSellerRestore from "../../../Internet/home/comment/save/saveSellerRestore";
  import {cloneDeep} from 'lodash/fp';

  export default {
    name: "CommentManage",
    setup(){
      let warnLogo=require("../../../assets/img/警告.svg")
      let searchLogo=require('../../../assets/img/查询.svg')

      let lastYear=ref(2021)
      let lastMonth=ref(9)
      let lastDay=ref(5)

      let nextYear=ref(new Date().getFullYear())
      let nextMonth=ref(new Date().getMonth()+1)
      let nextDay=ref(new Date().getDate())

      let goodsId=ref('')
      let username=ref('')
      let searchItem=ref('')
      let goodsTitle=ref('')

      let page=reactive({
        data:{}
      })

      let goodsList=reactive({
        list:[]
      })

      let comments=reactive({
        list:[]
      })

      let commentList=reactive({
        list:[]
      })

      let userList=reactive({
        list:[]
      })

      let goodsSpecificationsList=reactive({
        list:[]
      })

      let sellerRestoreFlag=reactive({
        list:[]
      })

      let modifySellerRestoreFlag=reactive({
        list:[]
      })


      let currentPage=1

      let lastYearList=reactive({
        list:[]
      })
      let lastMonthList=reactive({
        list:[]
      })
      let lastDayList=reactive({
        list:[]
      })
      let nextYearList=reactive({
        list:[]
      })
      let nextMonthList=reactive({
        list:[]
      })
      let nextDayList=reactive({
        list:[]
      })

      let year=new Date().getFullYear();

      for(let i=2021;i<=Number(year);i++){
        lastYearList.list.push(i);
        nextYearList.list.push(i);
      }

      for(let i=1;i<=new Date().getMonth()+1;i++){
        lastMonthList.list.push(i)
        nextMonthList.list.push(i)
      }

      for(let i=1;i<=new Date().getDate();i++){
        lastDayList.list.push(i)
        nextDayList.list.push(i)
      }

      watch(
          lastYear,
          ()=>{
            lastMonthList.list.splice(0,lastMonthList.list.length)
            lastMonth.value=1
            if(lastYear.value<new Date().getFullYear()){
              for(let i=1;i<=12;i++){
                lastMonthList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date().getMonth()+1;i++){
                lastMonthList.list[i-1]=i
              }
            }
          }
      )

      watch(
          nextYear,
          ()=>{
            currentPage=1
            nextMonthList.list.splice(0,nextMonthList.list.length)
            nextMonth.value=1
            if(nextYear.value<new Date().getFullYear()){
              for(let i=1;i<=12;i++){
                nextMonthList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date().getMonth()+1;i++){
                nextMonthList.list[i-1]=i
              }
            }
          }
      )

      watch(
          lastMonth,
          ()=>{
            currentPage=1
            lastDayList.list.splice(0,lastDayList.list.length)
            lastDay.value=1
            if(lastMonth.value==(new Date().getMonth()+1)&&lastYear.value==year){
              for(let i=1;i<=new Date().getDate();i++){
                lastDayList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date(lastYear.value,lastMonth.value,0).getDate();i++){
                lastDayList.list[i-1]=i
              }
            }
          }
      )

      watch(
          nextMonth,
          ()=>{
            currentPage=1
            nextDayList.list.splice(0, nextDayList.list.length)
            nextDay.value=1
            if(nextMonth.value==(new Date().getMonth()+1)&&nextYear.value==year){
              for(let i=1;i<=new Date().getDate();i++){
                nextDayList.list[i-1]=i
              }
            }else {
              for(let i=1;i<=new Date(nextYear.value,nextMonth.value,0).getDate();i++){
                nextDayList.list[i-1]=i
              }
            }
          }
      )




      function searchShopComments() {
        let goodsId1
        if(goodsId.value==''){
          goodsId1=0
        }else {
          goodsId1=goodsId.value
        }

        let fromDate0=new Date(lastYear.value,lastMonth.value-1,lastDay.value)
        let toDate0=new Date(nextYear.value,nextMonth.value-1,nextDay.value)

        if(fromDate0>toDate0){
          let temp;
          temp=lastYear.value
          lastYear.value=nextYear.value;
          nextYear.value=temp;

          let temp1
          temp1=lastMonth.value
          lastMonth.value=nextMonth.value;
          nextMonth.value=temp1;

          let temp2
          temp2=lastDay.value
          lastDay.value=nextDay.value;
          nextDay.value=temp2;
        }

        let fromDate=new Date(lastYear.value,lastMonth.value-1,lastDay.value)
        let toDate=new Date(nextYear.value,nextMonth.value-1,nextDay.value+2)

        searchShopGoodsComments(JSON.parse(sessionStorage.getItem('shop')).shopId,goodsId1,username.value,searchItem.value,goodsTitle.value,currentPage,fromDate,toDate).then(res=>{
          page.data=res.data.commentList
          sellerRestoreFlag.list=[]
          modifySellerRestoreFlag.list=[]
          comments.list=res.data.commentList.records
          commentList.list=cloneDeep(res.data.commentList.records);
          for(let i=0;i<comments.list.length;i++){
            modifySellerRestoreFlag.list[i]=false
            if(comments.list[i].sellerRestore==null){
              sellerRestoreFlag.list[i]=false
            }else {
              sellerRestoreFlag.list[i]=true
            }

            if(comments.list[i].commentPhoto!==null){
              comments.list[i].commentPhoto=JSON.parse(comments.list[i].commentPhoto)
              for(let j=0;j<comments.list[i].commentPhoto.length;j++){
                comments.list[i].commentPhoto[j]=require("../../../assets/img/"+comments.list[i].commentPhoto[j])
              }
            }

            if(comments.list[i].additionalPhoto!==null){
              comments.list[i].additionalPhoto=JSON.parse(comments.list[i].additionalPhoto)
              for(let j=0;j<comments.list[i].additionalPhoto.length;j++){
                comments.list[i].additionalPhoto[j]=require("../../../assets/img/"+comments.list[i].additionalPhoto[j])
              }
            }
          }


          userList.list=res.data.userList
          for(let i=0;i<userList.list.length;i++){
            userList.list[i].image=require("../../../assets/img/"+userList.list[i].image)
          }
          goodsSpecificationsList.list=res.data.goodsSpecificationsList

          goodsList.list=res.data.goodsList
          for(let i=0;i<goodsList.list.length;i++){
            goodsList.list[i].goodsImage1=require("../../../assets/img/"+goodsList.list[i].goodsImage1)
          }
        })
      }

      searchShopComments()

      function toChoosePage(index) {
        if(index+currentPage<=0){
          return;
        }else if(index+currentPage>page.data.pages){
          return;
        }else {
          currentPage=currentPage+index
          searchShopComments()
        }
      }

      function saveAndModifySellerRestore(comment) {

        saveSellerRestore(comment).then(res=>{
          if(res.data==true){
           searchShopComments()
          }
        })
      }

      return{
        lastDay,
        lastMonth,
        nextDay,
        nextMonth,
        nextYear,
        lastDayList,
        lastMonthList,
        lastYearList,
        nextDayList,
        nextMonthList,
        nextYearList,
        currentPage,
        searchLogo,
        goodsId,
        goodsTitle,
        username,
        searchItem,
        searchShopComments,
        page,
        comments,
        userList,
        goodsSpecificationsList,
        goodsList,
        toChoosePage,
        warnLogo,
        sellerRestoreFlag,
        saveAndModifySellerRestore,
        commentList,
        modifySellerRestoreFlag
      }
    }
  }
</script>

<style scoped>

</style>