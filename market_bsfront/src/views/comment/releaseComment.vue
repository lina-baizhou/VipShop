<template>
    <div class="mainDiv" style="width: 100%;height: auto;display: flex;flex-wrap: wrap;justify-content: center">

        <div style="width: 100%;height: 40rem;background-color:rgb(241,1,128);display: flex;justify-content: space-evenly">
            <div class="mainDiv" style="width: 100%;display: flex;height: auto;justify-content: space-evenly;align-items: center">
                <div style="width: 1000rem;height: 40rem;display: flex;justify-content: left;">
                    <router-link to="/mainHeader/UserAccountCenter/userInf">
                        <div style="height: 40rem;width: 140rem;background-color: rgba(38,38,38,0.3);display: flex;justify-content: center;align-items: center;font-size: 17rem;color: white">
                            <span style="width: 100rem" v-if="!addCommentFlag">评价商品</span>
                            <span style="width: 100rem" v-if="addCommentFlag">追加评论</span>
                        </div>
                    </router-link>
                    <router-link to="/mainHeader/home">
                        <div style="height: 40rem;width: 140rem;display: flex;justify-content: center;align-items: center;font-size: 17rem;color: white;">
                            <span style="width: 100rem">首页</span>
                        </div>
                    </router-link>
                </div>
            </div>
        </div>

        <div style="width: 1000rem;height: 283rem;border: 1px solid #ececec;margin-top: 20rem;display: flex;justify-content: space-between;align-items: center">
            <img v-if="commentGoods.index>0" @click="getGoodsComment(commentGoods.index-1)" :src="lastLogo" style="height: 30rem;">
            <div style="width: 270rem;height: 100%;display: flex;flex-direction:column;justify-content: center;align-items: center">

                <div style="width: 133rem;height: 133rem;display: flex;justify-content: center;align-items: center;border: 1px solid #ececec;">
                    <img :src="commentGoods.goods.goodsImage1" style="width: 125rem;height: 125rem">
                </div>
                <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:commentGoods.goods.goodsId}}" @click="selectGoods(commentGoods.goods.goodsId)" style="margin-top: 10rem;width: 60%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{commentGoods.goods.goodsTitle}}</router-link>
                <span style="width: 60%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem">{{commentGoods.specifications.goodsType}}</span>
            </div>

            <div style="width: 670rem;height: 100%;display: flex;justify-content: center;align-items: center">
                <div style="width:633rem;margin-left: -100rem;height: 263rem;background-color: rgb(238,248,253);border: 1rem lightblue solid;display: flex;justify-content: center;align-items: center;flex-wrap: wrap">
                    <div v-if="!addCommentFlag" style="font-size: 12rem;font-family:宋体;height: 70rem;width: 600rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                        <span style="width: 100%;display: flex;justify-content: flex-start;align-items: center">
                            <span style="width: 100rem;display: flex;justify-content: flex-start;color:gray">
                                宝贝描述评分：
                            </span>
                            <span style="width: 100rem;display:flex;justify-content: space-between">
                                <img @click="gradeDescriptionCount(index)" v-for="(item,index) in descriptionCountLogo.data" :key="index" :src="item" style="height: 18rem;cursor: pointer">
                            </span>
                        </span>

                        <span style="width: 100%;display: flex;justify-content: flex-start;align-items: center">
                            <span style="width: 100rem;display: flex;justify-content: flex-start;color:gray">
                                店铺服务评分：
                            </span>
                            <span style="width: 100rem;display:flex;justify-content:space-between">
                                  <img @click="gradeServiceCount(index)" v-for="(item,index) in serviceCountLogo.data" :key="index" :src="item" style="height: 18rem;cursor: pointer">
                            </span>
                        </span>

                        <span style="width: 100%;display: flex;justify-content: flex-start;align-items: center">
                            <span style="width: 100rem;display: flex;justify-content: flex-start;color:gray">
                                店铺物流评分：
                            </span>
                            <span style="width: 100rem;display:flex;justify-content: space-between">
                                 <img @click="gradeLogisticsCount(index)" v-for="(item,index) in logisticsCountLogo.data" :key="index" :src="item" style="height: 18rem;cursor: pointer">
                            </span>
                        </span>
                    </div>
                    <div style="height: 100rem;width: 600rem;background-color: white;border: 1px solid #ececec;display: flex;justify-content: center;align-items: center">
                        <textarea v-model="comment" style="font-family: 宋体;font-size: 12rem;width: 98%;height: 95%;resize:none;border: none;outline: none" placeholder="宝贝用的好吗？来分享一下您的使用感受吧！"></textarea>
                    </div>
                    <div style="height: 50rem;width: 600rem;display: flex;justify-content: space-between;align-items: center">
                        <div style="width: 100rem;height: auto;display: flex;justify-content: space-between;align-items: center">
                            <div style="cursor: pointer;width: 64rem;height: 22rem;display: flex;justify-content: space-between;align-items: center;background-color: rgb(147,180,198);border: 1rem solid rgb(124,160,184)">
                                <img :src="imageLogo" style="height: 22rem">
                                <span style="font-size: 12rem;font-family: 宋体;color: white">晒照片</span>
                            </div>
                            <input v-if="images.data.length<5" class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="onchangeImgFun">
                            <span style="font-weight: 700;color: #a0a0a0;font-size: 12rem">
                                限5张
                            </span>
                        </div>
                        <div style="width: 480rem;height: 100%;display: flex;justify-content: flex-start;align-items: center">

                            <div @mouseenter="enterDeleteDiv(index)" @mouseleave="leaveDeleteDiv" v-for="(item,index) in images.data" :key="index" style="margin-left: 10rem;width: 50rem;height: 50rem;border: 1px solid gray;display: flex;justify-content: center;align-items: center">
                                <div @click="deleteImage(index)" class="deleteDiv" v-if="enterFlag.list[index]" style="width: 50rem;height: 50rem;position: absolute;background-color: rgba(0,0,0,0.9);display: flex;justify-content: center;align-items: center;color: white;font-size: 14rem;cursor: pointer">
                                    删除
                                </div>
                                <img  :src="item" style="height: 48rem">
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <img v-if="commentGoods.index+1<orderGroup.goodsList.length" @click="getGoodsComment(commentGoods.index+1)" :src="nextLogo" style="height: 30rem;">
        </div>

        <div style="width: 500rem;height: auto;display: flex;justify-content: space-evenly;align-items: center;margin-top: 20rem">
            <button v-if="commentGoods.comment==null" @click="releaseComment" style="width: 136rem;height: 36rem;background-color:rgb(241,1,128);border-radius: 5rem;border-color:rgba(241,1,128,0.7);color: white;font-size: 20rem;cursor: pointer">
                发表评论
            </button>
            <button v-if="commentGoods.comment==null" @click="abandonOnComment" style="width: 136rem;height: 36rem;background-color:rgb(241,1,128);border-radius: 5rem;border-color:rgba(241,1,128,0.7);color: white;font-size: 20rem;cursor: pointer">
                放弃评论
            </button>
            <button @click="toAddComment" v-if="commentGoods.comment!=null&&!addCommentFlag" style="width: 136rem;height: 36rem;background-color:rgb(241,1,128);border-radius: 5rem;border-color:rgba(241,1,128,0.7);color: white;font-size: 20rem;cursor: pointer">
                去追加评论
            </button>
            <button @click="addComment" v-if="addCommentFlag" style="width: 136rem;height: 36rem;background-color:rgb(241,1,128);border-radius: 5rem;border-color:rgba(241,1,128,0.7);color: white;font-size: 20rem;cursor: pointer">
                保存评论
            </button>

            <button @click="()=>{addCommentFlag=false;getGoodsComment(commentGoods.index)}" v-if="addCommentFlag" style="width: 136rem;height: 36rem;background-color:rgb(241,1,128);border-radius: 5rem;border-color:rgba(241,1,128,0.7);color: white;font-size: 20rem;cursor: pointer">
                退出
            </button>

            <button @click="modifyComment"   v-if="commentGoods.comment!=null&&commentGoods.comment.content!=null&&((commentGoods.comment.serviceCount+commentGoods.comment.logisticsCount+commentGoods.comment.descriptionCount)/3<4)&&!addCommentFlag"  style="width: 136rem;height: 36rem;background-color:rgb(241,1,128);border-radius: 5rem;border-color:rgba(241,1,128,0.7);color: white;font-size: 20rem;cursor: pointer">
                修改评论
            </button>
            <button  v-if="commentGoods.comment!=null&&commentGoods.comment.content!=null&&((commentGoods.comment.serviceCount+commentGoods.comment.logisticsCount+commentGoods.comment.descriptionCount)/3<4)&&!addCommentFlag"  @click="deleteComment" style="width: 136rem;height: 36rem;background-color:rgb(241,1,128);border-radius: 5rem;border-color:rgba(241,1,128,0.7);color: white;font-size: 20rem;cursor: pointer">
                撤销评论
            </button>
        </div>

    </div>
</template>

<script>
  import {reactive, ref} from "@vue/reactivity";
  import selectShoppingOrderByOrderNumber from "../../Internet/home/shoppingCart/select/selectShoppingOrderByOrderNumber";
  import getComment from "../../Internet/home/comment/select/getComment";
  import selectByGoodsId from "../../Internet/home/goods/select/selectByGoodsId";
  import store from "../../store/index"
  import axios from "axios";
  import saveComment from "../../Internet/home/comment/save/saveComment";
  import removeComment from "../../Internet/home/comment/remove/removeComment";
  import abandonComment from "../../Internet/home/comment/remove/abandonComment";

  export default {
    name: "releaseComment",
    setup(){
      let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg = new RegExp("(^|&)" + 'orderNumber' + "=([^&]*)(&|$)");
      let r = search.match(reg);

      let orderGroup=reactive({
        goodsList:[],
        shop:{},
        specificationsList:[],
        seller:{},
        shopOrder:{}
      })

      let comment=ref('')

      async function getOrderGroup() {
        await selectShoppingOrderByOrderNumber(unescape(r[2])).then(res => {
          orderGroup.goodsList=res.data.goodsList
          orderGroup.specificationsList = res.data.specificationsList


          orderGroup.shopOrder=res.data.order
          orderGroup.shop = res.data.shop
          orderGroup.seller = res.data.seller
          for (let i = 0; i < orderGroup.goodsList.length; i++) {
            orderGroup.goodsList[i].goodsImage1 = require("../../assets/img/" + orderGroup.goodsList[i].goodsImage1)
          }

          for (let i = 0; i < orderGroup.specificationsList.length; i++) {
            if (orderGroup.specificationsList[i].goodsType2 == null) {
              orderGroup.specificationsList[i].goodsType = orderGroup.specificationsList[i].goodsType1
            } else {
              orderGroup.specificationsList[i].goodsType = orderGroup.specificationsList[i].goodsType1 + orderGroup.specificationsList[i].goodsType2
            }
          }
        })
      }

      let commentGoods=reactive({
        goods:{},
        index:'',
        specifications:{},
        comment:null
      })

      let imagesToModify=[]

      let addCommentFlag=ref(false)
      function getGoodsComment(index){
        imagesToModify=[]
        console.log(orderGroup.specificationsList[index].specificationsId);
        console.log(orderGroup.shopOrder.orderNumber);
        console.log(orderGroup.goodsList[index].goodsId);
        getComment({'orderNumber':orderGroup.shopOrder.orderNumber,'goodsId':orderGroup.goodsList[index].goodsId,'specificationsId':orderGroup.specificationsList[index].specificationsId}).then(res=>{
          console.log(res.data);
          addCommentFlag.value=false
          if(res.data!=null){
            if(res.data.content==null&&res.data.additionalComment==null){
              toAddComment()
              comment.value=res.data.content
              if(comment.value!==null){
                if(res.data.commentPhoto!==null){
                  for(let i=0;i<JSON.parse(res.data.commentPhoto).length;i++){
                    images.data[i]=require('../../assets/img/'+JSON.parse(res.data.commentPhoto)[i])
                    imagesToModify[i]=JSON.parse(res.data.commentPhoto)[i]
                    imageFile[i]=null
                  }
                }
              }
              gradeLogisticsCount(res.data.logisticsCount-1)
              gradeServiceCount(res.data.serviceCount-1)
              gradeDescriptionCount(res.data.descriptionCount-1)
              commentGoods.goods=orderGroup.goodsList[index]
              commentGoods.index=index
              commentGoods.specifications=orderGroup.specificationsList[index]
              enterFlag.list=[]
              commentGoods.comment=res.data
            }else if(res.data.content==null&&res.data.additionalComment!=null){
              orderGroup.goodsList.splice(index,1)
              orderGroup.specificationsList.splice(index,1)
            }else {
              comment.value=res.data.content
              if(comment.value!==null){

                if(res.data.commentPhoto!==null){
                  for(let i=0;i<JSON.parse(res.data.commentPhoto).length;i++){
                    images.data[i]=require('../../assets/img/'+JSON.parse(res.data.commentPhoto)[i])
                    imagesToModify[i]=JSON.parse(res.data.commentPhoto)[i]
                    imageFile[i]=null
                  }
                }
              }
              gradeLogisticsCount(res.data.logisticsCount-1)
              gradeServiceCount(res.data.serviceCount-1)
              gradeDescriptionCount(res.data.descriptionCount-1)
              commentGoods.goods=orderGroup.goodsList[index]
              commentGoods.index=index
              commentGoods.specifications=orderGroup.specificationsList[index]
              enterFlag.list=[]
              commentGoods.comment=res.data
            }
          }else {
            descriptionCount=0
            logisticsCount=0
            serviceCount=0
            images.data=[]
            imageFile=[]
            for(let i=0;i<5;i++){
              descriptionCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
              serviceCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
              logisticsCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
             }
            comment.value=''
            commentGoods.goods=orderGroup.goodsList[index]
            commentGoods.index=index
            commentGoods.specifications=orderGroup.specificationsList[index]
            enterFlag.list=[]
            commentGoods.comment=res.data
          }
        })
      }

      new Promise((resolve)=>{
        resolve(getOrderGroup())
      }).then(()=>{
        getGoodsComment(0)
      })

      function selectGoods(goodsId) {
        selectByGoodsId(goodsId).then(res => {
          store.state.goods.data = res.data;
        })
      }

      let descriptionCountLogo=reactive({
        data:[]
      })
      let serviceCountLogo=reactive({
        data:[]
      })
      let logisticsCountLogo=reactive({
        data:[]
      })
      for(let i=0;i<5;i++){
        descriptionCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
        serviceCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
        logisticsCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
      }

      let descriptionCount=0
      function gradeDescriptionCount(index) {
        descriptionCount=index+1
        for(let i=0;i<=index;i++){
          descriptionCountLogo.data[i]=require("../../assets/img/评分-选中.svg")
        }
        for(let i=index+1;i<5;i++){
          descriptionCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
        }
      }

      let serviceCount=0
      function gradeServiceCount(index) {
        serviceCount=index+1
        for(let i=0;i<=index;i++){
          serviceCountLogo.data[i]=require("../../assets/img/评分-选中.svg")
        }
        for(let i=index+1;i<5;i++){
          serviceCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
        }
      }

      let logisticsCount=0
      function gradeLogisticsCount(index) {
        logisticsCount=index+1
        for(let i=0;i<=index;i++){
          logisticsCountLogo.data[i]=require("../../assets/img/评分-选中.svg")
        }
        for(let i=index+1;i<5;i++){
          logisticsCountLogo.data[i]=require("../../assets/img/评分-选中 (1).svg")
        }
      }

      let imageLogo=require('../../assets/img/照片.svg')

      let images=reactive({
        data:[]
      })

      let enterFlag=reactive({
        list:[]
      })

      function enterDeleteDiv(index) {
        enterFlag.list[index]=true
      }

      let imageFile=[]

      function leaveDeleteDiv() {
        for(let i=0;i<enterFlag.list.length;i++){
          enterFlag.list[i]=false
        }
      }

      function deleteImage(index) {
        images.data.splice(index,1)
        enterFlag.list.splice(index,1)
        imageFile.splice(index,1)
        imagesToModify.splice(index,1)
        for(let i=0;i<enterFlag.list.length;i++){
          enterFlag.list[i]=false
        }
      }

      function onchangeImgFun(e) {
        let file = e.target.files[0]
        console.log(file);
        imagesToModify.push(file.name)

        event.preventDefault()

        imageFile.push(file)

        // let formData = new FormData();
        //
        // formData.append('image', file);//键名要和后台一致
        //
        // formData.append('id', Number(JSON.parse(sessionStorage.getItem('user')).userId));
        //
        //
        // const instance = axios.create({
        //   withCredentials: true
        // })
        // instance.post('/servlet/user/uploadImage', formData).then(res => {
        //   if (res.data.code == 0) {
        //     user1.image = res.data.data
        //     sessionStorage.setItem('user', JSON.stringify(user1))
        //   }
        // })

        // base64方法 2
        let reader = new FileReader()
        reader.readAsDataURL(file) // 读出 base64
        reader.onloadend = function () {
          // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
          let dataURL = reader.result
          images.data.push(dataURL)
          enterFlag.list.push(false)
          // 下面逻辑处理
        }
      }

      let lastLogo=require("../../assets/img/下一步.svg")
      let nextLogo=require("../../assets/img/下一步@1x.svg")

      function toAddComment() {
        addCommentFlag.value=true
        images.data=[]
        imageFile=[]
        comment.value=null
      }

      function releaseComment() {
        let flag=true
        if(descriptionCount==0||logisticsCount==0||serviceCount==0){
          alert("请打分")
          flag=false
        }
        if(comment.value==''){
          alert('请输入评论')
          flag=false
        }
        console.log(commentGoods.specifications.specificationsId);

        if(flag==true){
          let formData = new FormData();

          let commentForSave={
            'userId':Number(JSON.parse(sessionStorage.getItem('user')).userId),
            'goodsId':commentGoods.goods.goodsId,
            'shopId':orderGroup.shop.shopId,
            'orderNumber':orderGroup.shopOrder.orderNumber,
            'content':comment.value,
            'descriptionCount':descriptionCount,
            'serviceCount':serviceCount,
            'logisticsCount':logisticsCount,
            'specificationsId': commentGoods.specifications.specificationsId
          }

          for(let i=0;i<imageFile.length;i++){
            formData.append('files',imageFile[i]);//键名要和后台一致
          }
          console.log(commentGoods.specifications.specificationsId);
          formData.append("orderNumber",orderGroup.shopOrder.orderNumber);
          formData.append("goodsId",commentGoods.goods.goodsId);
          formData.append('specificationsId',commentGoods.specifications.specificationsId);
          saveComment(commentForSave).then(()=>{
            if(imageFile.length>0){
              const instance = axios.create({
                withCredentials: true
              })
              instance.post('/servlet/comment/saveImages', formData).then(()=>{
                getGoodsComment(commentGoods.index)
              })
            }else {
              getGoodsComment(commentGoods.index)
            }
          })

        }
      }

      function deleteComment() {
        removeComment(orderGroup.shopOrder.orderNumber,commentGoods.goods.goodsId,commentGoods.specifications.specificationsId).then(()=>{
          getGoodsComment(commentGoods.index)
        })
      }

      function addComment() {
        let flag=true
        if(comment.value==null){
          alert('请输入评论')
          flag=false
        }
        if(flag==true){
          let formData = new FormData();

          if(imageFile.length!=0){
            for(let i=0;i<imageFile.length;i++){
              formData.append('files',imageFile[i]);//键名要和后台一致
            }
          }
          formData.append("orderNumber",orderGroup.shopOrder.orderNumber);
          formData.append("goodsId",commentGoods.goods.goodsId);
          formData.append('specificationsId',commentGoods.specifications.specificationsId);
          formData.append("additionalComment",comment.value)
          const instance = axios.create({
            withCredentials: true
          })
          if(imageFile.length!=0){
            instance.post('/servlet/comment/addComment', formData).then(res=>{
              console.log(res);
              if(res.data.code==0){
                alert("追评成功")
                getGoodsComment(commentGoods.index)
              }
            })
          }else {
            instance.post('/servlet/comment/addComment1', formData).then(res=>{
              console.log(res);
              if(res.data.code==0){
                alert("追评成功")
                getGoodsComment(commentGoods.index)
              }
            })
          }

        }
      }

      function modifyComment() {
        let flag=true
        if(descriptionCount==0||logisticsCount==0||serviceCount==0){
          alert("请打分")
          flag=false
        }
        if(comment.value==''){
          alert('请输入评论')
          flag=false
        }

        if(flag==true){
          let formData = new FormData();

          let flag1=false

          for(let i=0;i<imageFile.length;i++){
           if(imageFile[i]!==null){
             flag1=true
           }
          }

          if(flag1==true){
            for(let i=0;i<imageFile.length;i++){
              formData.append('files',imageFile[i]);//键名要和后台一致
            }
          }
          formData.append("orderNumber",orderGroup.shopOrder.orderNumber);
          formData.append("goodsId",commentGoods.goods.goodsId);
          formData.append('specificationsId',commentGoods.specifications.specificationsId);
          formData.append("content",comment.value)
          formData.append("descriptionCount",descriptionCount)
          formData.append("serviceCount",serviceCount)
          formData.append("logisticsCount",logisticsCount)

          for(let i=0;i<imagesToModify.length;i++){
            formData.append("images",imagesToModify[i])
          }
          if(imagesToModify.length==0){
            formData.append("images",null)
          }




          const instance = axios.create({
            withCredentials: true,
          })
          if(flag1==true){
            instance.post('/servlet/comment/modifyComment', formData).then(()=>{
              getGoodsComment(commentGoods.index)
            })
          }else {
            instance.post('/servlet/comment/modifyComment1', formData).then(()=>{
              getGoodsComment(commentGoods.index)
            })
          }
        }
      }
      
      function abandonOnComment() {
        let commentForSave={
          'orderNumber':orderGroup.shopOrder.orderNumber,
          'goodsId':commentGoods.goods.goodsId,
          'shopId':orderGroup.shop.shopId,
          'userId':JSON.parse(sessionStorage.getItem('user')).userId,
          'specificationsId': commentGoods.specifications.specificationsId
        }

        abandonComment(commentForSave).then(()=>{
          getGoodsComment(commentGoods.index)
        })
      }

      return{
        orderGroup,
        toAddComment,
        releaseComment,
        commentGoods,
        descriptionCount,
        logisticsCount,
        serviceCount,
        deleteImage,
        selectGoods,
        serviceCountLogo,
        descriptionCountLogo,
        logisticsCountLogo,
        gradeDescriptionCount,
        gradeLogisticsCount,
        gradeServiceCount,
        comment,
        leaveDeleteDiv,
        imageLogo,
        images,
        onchangeImgFun,
        enterFlag,
        enterDeleteDiv,
        getGoodsComment,
        addCommentFlag,
        nextLogo,
        lastLogo,
        deleteComment,
        modifyComment,
        addComment,
        abandonOnComment
      }
    }
  }
</script>

<style scoped>
    .upload-btn{
        position: absolute;
        width: 64rem;
        height: 22rem;
        cursor: pointer;
        opacity: 0;
        /* 通过定位把input放在img标签上面，通过不透明度隐藏 */
    }
</style>