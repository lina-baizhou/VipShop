<template>
    <div style="width: 100%;height: auto;padding-top: 20rem;padding-bottom: 20rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center;font-size: 12rem">
        <div style="width: 95%;height: auto;background-color: white;border-radius: 5rem;display: flex;justify-content: space-evenly;align-items: flex-start;flex-wrap: wrap">
            <div style="width: 100%;height: 40rem;display: flex;border-bottom: 1rem #eff0f4 solid;padding-left: 10rem;display: flex;align-items: center;font-size: 18rem">
                商品管理
            </div>
            <div style="width: 100%;border-bottom: 1rem #eff0f4 solid;height: auto;display: flex;justify-content: flex-start;align-items: center;flex-wrap: wrap;padding: 20rem">
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">商品编号：</span>
                    <input v-model="goodsId"  @input="goodsId = Number($event.target.value.replace(/\D+/, ''))" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center;">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">商品标题：</span>
                    <input v-model="goodsTitle" type="text" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1">
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: center;">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">上架状态：</span>
                    <select v-model="shelfState" name="province" style="width: 170rem;height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                        <option selected value="-2">请选择上架状态</option>
                        <option value="-1">未上架</option>
                        <option value="0">已上架</option>
                        <option value="1">已下架</option>
                    </select>
                </div>
                <div style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">一级分类：</span>
                    <select  style="width: 170rem;margin-left: 120rem;position: absolute;min-height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1" v-model="firstType"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                        <option  value="" selected>请选择一级分类</option>
                        <option  v-for="(item,index) in firstTypesList.list" :key="index" :value="item.id">{{item.type}}</option>
                    </select>
                </div>

                <div v-if="secondTypesList.list.length>0" style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">二级分类：</span>
                    <select style="width: 170rem;margin-left: 120rem;position: absolute;min-height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1" v-model="secondType"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                        <option  value="" selected>请选择二级分类</option>
                        <option  v-for="(item,index) in secondTypesList.list" :key="index" :value="{'index':index,'id':item.secondType.id}">{{item.secondType.type}}</option>
                    </select>
                </div>

                <div v-if="thirdTypesList.list.length>0" style="width: 33%;height:auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                    <span style="width: 120rem;font-size: 14rem;font-family:宋体;text-align: left">三级分类：</span>
                    <select style="width: 170rem;margin-left: 120rem;position: absolute;min-height: 30rem;border:1rem #a0a0a0 solid;border-radius: 3rem;outline-color: #1d94d1" v-model="thirdType"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                        <option  value="" selected>请选择三级分类</option>
                        <option  v-for="(item,index) in thirdTypesList.list" :key="index" :value="item.id">{{item.type}}</option>
                    </select>
                </div>
            </div>
            <div style="width: 100%;height: auto;padding: 10rem;justify-content: center;align-items: flex-start;flex-wrap: wrap">
                <div style="width: 100%;height: 50rem;display: flex;justify-content: space-between;align-items: center">
                    <div style="width: auto;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
                        <button @click="searchGoods" style="cursor: pointer;width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem rgb(36,156,235) solid;background-color: rgb(36,156,235);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                            <img :src="searchLogo" style="height: 15rem">&nbsp;查询商品
                        </button>
                        <router-link to="/shop/releaseGoods">
                            <button style="cursor: pointer;width: 100rem;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem rgb(32,157,189) solid;background-color: rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                                <img :src="addLogo" style="height: 12rem">&nbsp;添加商品
                            </button>
                        </router-link>
                        <button  @click="putOnGoodsShelf" style="width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem rgb(36,167,71) solid;background-color: rgb(36,167,71);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                            <img :src="onLogo" style="height: 13rem">&nbsp;上架商品
                        </button>
                        <button @click="putOffGoodsShelf" style="width: 100rem;cursor: pointer;height: 30rem;margin-right: 20rem;border-radius: 3rem;border: 1rem rgb(227,50,68) solid;background-color: rgb(227,50,68);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                            <img :src="offLogo"  style="height: 13rem">&nbsp;下架商品
                        </button>
                    </div>
                    <div v-if="page.data.pages>0" style="width: 400rem;height: 32rem;display: flex;align-items: center;justify-content: flex-end">
                        <span style="font-size: 14rem;color: #a0a0a0;margin-right: 20rem">共查询到
                            <span style="font-size: 18rem;color: rgb(32,157,189)">&nbsp;{{page.data.total}}&nbsp;</span>
                            个商品
                        </span>
                        <span style="font-size: 14rem;color: #a0a0a0;margin-right: 20rem">
                            <span style="font-size: 14rem;color: rgb(32,157,189)">{{currentPage}}</span>/{{page.data.pages}}
                        </span>
                        <button v-if="currentPage>1" @click="toChoosePage(-1)" class="nextPageBtn" style="margin-right: 10rem;border:1rem solid grey;cursor: pointer;width: 62rem;height: 32rem;border-radius: 3rem;background-color: #ffffff">
                            上一页
                        </button>
                        <button v-else  class="nextPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;margin-right: 10rem;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                            上一页
                        </button>
                        <div style="margin-right: 10rem;width: 32rem;color: white;height: 32rem;border-radius: 3rem;background-color: rgb(32,157,189);display: flex;justify-content: center;align-items: center">
                            {{currentPage}}
                        </div>
                        <button v-if="currentPage<page.data.pages" @click="toChoosePage(1)" class="nextPageBtn" style="border:1rem solid grey;cursor: pointer;width: 62rem;height: 32rem;border-radius: 3rem;background-color: #ffffff">
                            下一页
                        </button>
                        <button v-else class="nextPageBtn" disabled style="border:1rem solid lightgrey;color: #a0a0a0;cursor: not-allowed ;width: 62rem;height: 32rem;border-radius: 3rem;background-color: white">
                            下一页
                        </button>
                    </div>
                </div>


                <div style="width: 100%;height: auto;margin-top: 20rem">
                    <table style="width: 100%;height: auto;" border='1'   bordercolor="DFDFDF">
                        <tr>
                            <th>
                                <input v-model="selectAll" type="checkbox">
                            </th>
                           <th>
                               商品编号
                           </th>
                            <th>
                                商品标题
                            </th>
                            <th>
                                商品分类
                            </th>
                            <th>
                                商品图片
                            </th>
                            <th>
                                商品规格
                            </th>
                            <th>
                                商品库存
                            </th>
                            <th>
                                商品价格
                            </th>
                            <th>
                                上架状态
                            </th>
                            <th>
                                商品操作
                            </th>
                        </tr>
                        <tr v-for="(item,index) in goodsList.list" :key="index">
                            <td>
                                <input :value="item" v-model="selectList.list" type="checkbox">
                            </td>
                            <td>
                                {{item.goodsId}}
                            </td>
                            <td>
                                <span style="width: 100%;word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;">
                                     {{item.goodsTitle}}
                                </span>
                            </td>
                            <td>
                                {{types.list[index].type}}
                            </td>
                            <td>
                                <img :src="item.goodsImage1" style="height: 30rem">
                            </td>
                            <td>
                                <div style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: center">
                                    <span v-for="(item1,index1) in goodsSpecificationsList.list[index]" :key="index1" style="width: 150rem;height: 20rem; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);text-align: center">
                                        {{item1.goodsType1}}&nbsp;{{item1.goodsType2}}
                                    </span>
                                </div>
                            </td>
                            <td>
                                <div style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: center">
                                     <span v-for="(item1,index1) in goodsSpecificationsList.list[index]" :key="index1" style="width: 100%;display: flex;justify-content: center;">
                                         {{item1.goodsStoke}}
                                     </span>
                                </div>
                            </td>
                            <td>
                                <div style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: center">
                                    <span v-for="(item1,index1) in goodsSpecificationsList.list[index]" :key="index1" >
                                        {{item1.goodsPrice}}
                                    </span>
                                </div>
                            </td>
                            <td>
                                <span v-if="item.shelfState==-1">
                                    未上架
                                </span>
                                <span v-if="item.shelfState==0">
                                    销售中
                                </span>
                                <span v-if="item.shelfState==1">
                                    已下架
                                </span>
                            </td>
                            <td style="padding: 10rem">
                                <router-link :to="{path:'/shop/modifyGoods',query:{goodsId:item.goodsId}}">
                                    <button style="width: 90rem;height: 30rem;border-radius: 3rem;border: 1rem rgb(32,157,189) solid;background-color: rgb(32,157,189);font-size: 13rem;font-weight: bold;color: white;display: flex;justify-content: center;align-items: center">
                                        <img :src="modifyLogo" style="height: 13rem">&nbsp;修改商品
                                    </button>
                                </router-link>
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import getFirstLevelType from "../../../Internet/home/type/getFirstLevelType";
  import {reactive, ref} from "@vue/reactivity";
  import getTypes from "../../../Internet/home/type/getTypes";
  import {watch} from "vue"
  import searchShopGoods from "../../../Internet/home/goods/select/searchShopGoods";
  import putOnShelf from "../../../Internet/home/goods/update/putOnShelf";
  import putOffShelf from "../../../Internet/home/goods/update/putOffShelf";

  export default {
    name: "goodsTable",
    setup(){
      let addLogo=require('../../../assets/img/添加.svg')
      let modifyLogo=require('../../../assets/img/修 改.svg')
      let onLogo=require("../../../assets/img/上架.svg")
      let offLogo=require("../../../assets/img/下架.svg")
      let lookLogo=require("../../../assets/img/查看.svg")
      let searchLogo=require("../../../assets/img/查询.svg")

      let firstType=ref('')
      let secondType=ref('')
      let thirdType=ref('')

      let shop=JSON.parse(sessionStorage.getItem('shop'))

      let goodsId=ref('')
      let goodsTitle=ref('')
      let shelfState=ref(-2)
      let currentPage=ref(1)

      let goodsList=reactive({
        list:[]
      })

      let page=reactive({
        data:{}
      })

      let goodsSpecificationsList=reactive({
        list:[]
      })

      let types=reactive({
        list:[]
      })

      function searchGoods(){
        let id=''
        let typeLevel=0
        if(thirdType.value!==''){
          id=thirdType.value
          typeLevel=3
        }else if(secondType.value!==''){
          id=secondType.value.id
          typeLevel=2
        }else if(firstType.value!==''){
          id=firstType.value
          typeLevel=1
        }else {
          id=0
          typeLevel=0
        }

        let goodsId1=''
        if(goodsId.value==''){
          goodsId1=0
        }else {
          goodsId1=goodsId.value
        }
        searchShopGoods(shop.shopId,goodsId1,goodsTitle.value,shelfState.value,id,typeLevel,currentPage.value).then(res=>{
          goodsList.list=res.data.page.records;
          goodsSpecificationsList.list=res.data.goodsSpecificationsList
          page.data=res.data.page
          currentPage.value=page.data.current
          types.list=res.data.types
          for(let i=0;i<goodsList.list.length;i++){
            goodsList.list[i].goodsImage1=require("../../../assets/img/"+ goodsList.list[i].goodsImage1)
          }
          selectAll.value=false
        })
      }

      searchGoods()

      let firstTypesList=reactive({
        list:[]
      })
      let secondTypesList=reactive({
        list:[]
      })
      let thirdTypesList=reactive({
        list:[]
      })

      let selectList=reactive({
        list:[]
      })

      let selectAll=ref(false)


      getFirstLevelType().then(res=>{
        firstTypesList.list=res.data
      })

      let allTypes= []
      getTypes().then(res=>{
        allTypes=res.data.data
      })

      watch(
          firstType,
          ()=>{
            if(firstType.value!==''){
              for (let i = 0; i < 10; i++) {
                if(allTypes[i].list[0].secondType.fatherId==firstType.value){
                  secondTypesList.list=allTypes[i].list
                  thirdTypesList.list=[]
                  secondType.value=''
                  thirdType.value=''
                  break;
                }
              }
            }else {
              secondType.value=''
              thirdType.value=''
              secondTypesList.list=[];
              thirdTypesList.list=[];
            }
          }
      )

      watch(
          secondType,
          ()=>{
            if(secondType.value!==''){
              thirdType.value=''
              thirdTypesList.list=secondTypesList.list[secondType.value.index].thirdTypes
              console.log(thirdTypesList);
            }else {
              thirdType.value=''
              thirdTypesList.list=[]
            }
          }
      )

      watch(
          selectAll,
          ()=>{
            if(selectAll.value==true){
              selectList.list.splice(0,selectList.list.length)
              for(let i=0;i<goodsList.list.length;i++){
                selectList.list[i]=goodsList.list[i]
              }
            }else {
              selectList.list.splice(0,selectList.list.length)
            }
          }
      )


      function putOnGoodsShelf() {
        if(selectList.list.length==0){
          alert('请选择商品')
        }else {
          let flag=true;
          for(let i=0;i<selectList.list.length;i++){
            if(selectList.list[i].shelfState==-1||selectList.list[i].shelfState==1){
              continue;
            }else {
              flag=false;
              selectList.list.splice(i,1);
              i--;
            }
          }
          console.log(selectList);
          if(flag==false){
            alert("不符合上架条件的商品已自动取消选中！")
          }
          putOnShelf(selectList.list).then(()=>{
            searchGoods()
          })
        }
      }

      function putOffGoodsShelf() {
        if(selectList.list.length==0){
          alert('请选择商品')
        }else {
          let flag=true;
          for(let i=0;i<selectList.list.length;i++){
            if(selectList.list[i].shelfState==0){
              continue;
            }else {
              flag=false;
              selectList.list.splice(i,1);
              i--;
            }
          }
          console.log(selectList);
          if(flag==false){
            alert("不符合上架条件的商品已自动取消选中！")
          }
          putOffShelf(selectList.list).then(()=>{
            searchGoods()
          })
        }
      }

      function toChoosePage(index) {
        if(index+currentPage.value<=0){
          return;
        }else if(index+currentPage.value>page.data.pages){
          return;
        }else {
          currentPage.value=currentPage.value+index
          searchGoods()
        }
      }


      return{
        addLogo,
        modifyLogo,
        onLogo,
        offLogo,
        lookLogo,
        firstTypesList,
        secondTypesList,
        thirdTypesList,
        firstType,
        secondType,
        thirdType,
        allTypes,
        searchLogo,
        searchGoods,
        goodsTitle,
        goodsId,
        shelfState,
        currentPage,
        goodsList,
        goodsSpecificationsList,
        types,
        selectAll,
        selectList,
        putOffGoodsShelf,
        putOnGoodsShelf,
        toChoosePage,
        page
      }
    }
  }
</script>

<style scoped>
    td{
        text-align: center;
        vertical-align: middle;
        margin: 0;
        padding: 0;
    }
</style>