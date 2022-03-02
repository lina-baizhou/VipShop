<template>
    <div id="main" style="display: flex;flex-wrap: wrap;justify-content: center">
        <top-header></top-header>
         <div class="mainDiv" style="width: 100%;display: flex;height: auto;background-color: white;justify-content: space-evenly;align-items: center;flex-wrap: wrap">
            <div style="width: 1000rem;height: 100rem;display: flex;justify-content: space-between;align-items: center;position: relative;z-index:1;">
                <img :src="logo" style="max-height: 100rem">

                <div style="width: 320rem;height: 36rem;border: 2rem rgb(244,51,153) solid;display: flex;justify-content: space-evenly;align-items: center">
                    <input style="width: 300rem;border: 0rem;outline: none;font-size: 15rem;" placeholder="请输入商品/店铺/品类">
                    <div style="font-size:20rem;width: 56rem;height:100%;background-color: rgb(244,51,153);color: white;display: flex;justify-content: space-evenly;align-items: center">
                        <SearchOutlined></SearchOutlined>
                    </div>
                </div>
            </div>

            <div style="width: 1000rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;padding-bottom: 60rem">
                <div style="width: 100%;height: 43rem;display: flex;justify-content: flex-start;align-items: flex-start;border-bottom: 2rem lightgrey solid;">
                    <span style="width: 86rem;height: 43rem;border-bottom: 2rem rgb(244,51,153) solid;color:rgb(244,51,153);font-size: 16rem;font-weight: 700;display: flex;justify-content: center;align-items: center">
                        特卖商品
                    </span>
                </div>

                <div style="width: 100%;height: 40rem;padding-left: 15rem;padding-right: 15rem;display: flex;justify-content: flex-start;align-items: center;margin-top: 10rem">
                    <div style="width: 58rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;font-size: 12rem">
                        <input type="checkbox" v-model="selectAll">&nbsp;&nbsp;全选
                    </div>
                    <div style="width: 300rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                        商品
                    </div>
                    <div style="width: 200rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                        单价
                    </div>
                    <div style="width: 120rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                        数量
                    </div>
                    <div style="width: 172rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                        金额
                    </div>
                    <div style="width: 150rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                        操作
                    </div>
                </div>

                <div v-for="(item,index) in store.state.shoppingCartList.list" :key="index" style="width: 100%;height: auto;padding-left: 15rem;padding-right: 15rem;display: flex;justify-content: flex-start;align-items: center;margin-top: 10rem;flex-wrap: wrap;padding-bottom: 20rem">
                    <div style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: center;font-size: 12rem">
                        <input type="checkbox" v-model="selectShop.list" :value="index">&nbsp;&nbsp;{{item.shop.shopName}}
                    </div>
                    <div style="margin-top: 10rem;padding: 10rem;width: 100%;heistght: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;background-color: rgb(252, 252, 252);border: 1rem lightgrey solid">
                        <div v-for="(item1,index1) in item.goodsList" :key="index1" style="width: 100%;height: 101rem;display: flex;justify-content: flex-start;align-items: center">
                            <div style="width: 38rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;font-size: 12rem">
                                <input type="checkbox" v-model="selectGoodsInShoppingCart.list" :value="{'goodsId':item1.goodsId,'specificationsId':item.goodsSpecificationsList[index1].specificationsId,'userId':user1.userId,'goodsPrice':item.goodsSpecificationsList[index1].goodsPrice,'goodsCount':item.goodsCountList[index1],'index':index,'index1':index1}">
                            </div>
                            <div style="width: 300rem;height: 100%;display: flex;justify-content: space-between;align-items: center;font-size: 12rem">
                                <img :src="item1.goodsImage1" style="width: 76rem;height: 76rem;">

                                <div style="width:197rem;height: 88.5rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                                    <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item1.goodsId}}" @click="selectGoods(item1.goodsId)" style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{item1.goodsTitle}}</router-link>
                                    <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem">{{item.goodsSpecificationsList[index1].goodsType}}</span>
                                </div>
                            </div>
                            <div style="width: 200rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                ￥{{item.goodsSpecificationsList[index1].goodsPrice}}
                            </div>
                            <div style="width: 113rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                <div style="width: 75rem;height: 30rem;border: 1rem darkgrey solid;display: flex;justify-content: space-between;align-items: center">
                                    <div style="width: 21rem;height: 100%;border-right: 1rem darkgrey solid;background: whitesmoke;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer"
                                         @click="()=>{
                                           if(item.goodsCountList[index1]>1){
                                             item.goodsCountList[index1]--
                                              updateShoppingCart({
                                               'goodsId': item1.goodsId,
                                               'userId': user1.userId,
                                               'time': '',
                                               'specificationsId': item.goodsSpecificationsList[index1].specificationsId,
                                               'count': item.goodsCountList[index1],
                                               'shopId': item.shop.shopId
                                               })
                                           }
                                         }">
                                        <minus-outlined></minus-outlined>
                                    </div>
                                    <div style="width: 33rem;height: 100%;background: white;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer">
                                        {{item.goodsCountList[index1]}}
                                    </div>
                                    <div style="width: 21rem;height: 100%;background: whitesmoke;border-left: 1rem darkgrey solid;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer" @click="
                                    ()=>{
                                       if(item.goodsCountList[index1]<item.goodsSpecificationsList[index1].goodsStoke){
                                         item.goodsCountList[index1]++
                                          updateShoppingCart({
                                               'goodsId': item1.goodsId,
                                               'userId': user1.userId,
                                               'time': '',
                                               'specificationsId': item.goodsSpecificationsList[index1].specificationsId,
                                               'count': item.goodsCountList[index1],
                                               'shopId': item.shop.shopId
                                               })
                                       }
                                    }
                                    ">
                                        <plus-outlined></plus-outlined>
                                    </div>
                                </div>
                            </div>
                            <div style="width: 162rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                ￥{{item.goodsSpecificationsList[index1].goodsPrice*item.goodsCountList[index1]}}
                            </div>
                            <div style="width: 140rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem;">
                                <span style="cursor: pointer"
                                      @click="deleteShoppingCartItem(item1.goodsId,item.goodsSpecificationsList[index1].specificationsId)"
                                >删除</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div style="width: 100%;height: auto;display: flex;justify-content: center;position: fixed;bottom: 0rem;">
                <div style="width:972rem;height: 50rem;background-color: rgb(229, 229, 229);display: flex;justify-content: space-between;align-items: center;font-size: 12rem;font-family: 宋体">
                    <div style="width: 155rem;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
                        <div style="width: 55rem;padding-left: 5rem;height: 100%;display: flex;justify-content: space-between;align-items: center">
                            <input type="checkbox" v-model="selectAll" >&nbsp;全选
                        </div>
                        <div style="width: 100rem;padding-left: 5rem;height: 100%;display: flex;justify-content: center;align-items: center">
                            <span @click="deleteShoppingCartList" style="cursor: pointer">删除</span>
                        </div>
                    </div>
                    <div style="width: auto;display: flex;justify-content: flex-start;align-items: center">
                        <div style="width: auto;height: 50rem;display: flex;align-items: center">
                            已选商品&nbsp;<span style="font-size: 16rem;font-weight: 700;color:rgb(244,51,153)">{{goodsTotalCount}}</span>&nbsp;件
                        </div>
                        <div style="width: auto;height: 50rem;display: flex;align-items: center;padding-left: 20rem;padding-right: 20rem">
                            总金额（未含运费）：
                            <span style="font-size: 12rem;font-weight: 700;color:rgb(244,51,153);font-family: Arial">￥</span>
                            <span style="font-size: 18rem;font-weight: 700;color:rgb(244,51,153)">{{goodsTotalPrice}}</span>
                        </div>
                        <button @click="balanceAccount" style="width: 252rem;height:50rem;border: none;background-color:rgb(244,51,153);font-size: 18rem;color: white;cursor: pointer">
                            立即结算
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>

</template>

<script>
  import {SearchOutlined} from '@ant-design/icons-vue';
  import topHeader from "../inf/topHeader";
  import {ref,provide,reactive,watch} from 'vue';
  import getShoppingCartList from "../../assets/functions/getShoppingCartList";
  import store from '../../store/index'
  import selectByGoodsId from "../../Internet/home/goods/select/selectByGoodsId";
  import updateShoppingCart from "../../Internet/home/shoppingCart/update/updateShoppingCart";
  import removeShoppingCart from "../../Internet/home/shoppingCart/remove/removeShoppingCart";
  import removeShoppingCartList from "../../Internet/home/shoppingCart/remove/removeShoppingCartList";
  import {cloneDeep} from 'lodash/fp';
  import router from "../../router";
  import selectByUserId from "../../Internet/home/user/select/selectByUserId";


  let loginName=ref('')
  let user1=reactive({
    'username':'',
    'userId':'',
    'age':'',
    'identity':'',
    'image':'',
    'sex':'',
    'password':'',
    'email':''
  })
  export default {
    name: "ShoppingCartTopHeader",
    components:{
      SearchOutlined,
      topHeader,
    },
   setup() {
      let user = JSON.parse(sessionStorage.getItem('user'))

      let loginFlag = ref(true)

      new Promise(()=>{
        if (user != null) {
          user1.username = user.username
          user1.userId = user.userId
          user1.age = user.age
          user1.identity = user.identity
          user1.image = user.image
          user1.sex = user.sex
          user1.password = user.password
          user1.email = user.email

          if (user1 !== null && user1 !== undefined) {
            loginFlag.value = false

            new Promise((resolve) => {
              resolve(
                  getShoppingCartList(user1.userId),
              )
            })

            if (user1.username !== null && user1.username !== undefined) {
              loginName.value = user1.username
              console.log(loginName.value);
            } else {
              //中间4位加密显示出来
              let result = /^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
              this.loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
            }
          } else {
            loginFlag.value = true
          }
        } else {
          let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
          let reg = new RegExp("(^|&)" + 'userId' + "=([^&]*)(&|$)");
          let r = search.match(reg);

          selectByUserId(unescape(r[2])).then(res => {
            sessionStorage.setItem('user',JSON.stringify(res.data))
            user1.username = res.data.username
            user1.userId = res.data.userId
            user1.age = res.data.age
            user1.identity = res.data.identity
            user1.image = res.data.image
            user1.sex = res.data.sex
            user1.password = res.data.password
            user1.email = res.data.email

            if (user1 !== null && user1 !== undefined) {
              loginFlag.value = false

              new Promise((resolve) => {
                resolve(
                    getShoppingCartList(user1.userId),
                )
              })

              if (user1.username !== null && user1.username !== undefined) {
                loginName.value = user1.username
                console.log(loginName.value);
              } else {
                //中间4位加密显示出来
                let result = /^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
                this.loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
              }
            } else {
              loginFlag.value = true
            }
          })
        }
      })

      let logo = require("../../assets/img/cartHeader.png")



      //给子组件传值
      provide("loginFlag", loginFlag)

      provide("loginName", loginName)


      let selectAll = ref(false)

      let selectShop = reactive({
        list: []
      })


      let selectGoodsInShoppingCart = reactive({
        list: []
      })




      let goodsTotalCount = ref(0)

      let goodsTotalPrice = ref(0)


      function selectGoods(goodsId) {
        selectByGoodsId(goodsId).then(res => {
          store.state.goods.data = res.data;
        })
      }

      watch(
          selectGoodsInShoppingCart,
          () => {
            goodsTotalCount.value = selectGoodsInShoppingCart.list.length
            goodsTotalPrice.value = 0
            for (let i = 0; i < selectGoodsInShoppingCart.list.length; i++) {
              goodsTotalPrice.value += selectGoodsInShoppingCart.list[i].goodsCount * selectGoodsInShoppingCart.list[i].goodsPrice
            }
            for (let i = 0; i < store.state.shoppingCartList.list.length; i++) {
              if (isHasShopIndex(selectShop.list, i)||selectShop.list.length==0) {
                if (checkShopGoodsLength(selectGoodsInShoppingCart.list, i) == store.state.shoppingCartList.list[i].goodsList.length) {
                  selectShop.list.push(i)
                } else {
                  for (let j = 0; j < selectShop.list.length; j++) {
                    if (selectShop.list[j] == i) {
                      selectShop.list.splice(j, 1)
                      j--
                    }
                  }
                }
              }
            }
            if (selectGoodsInShoppingCart.list.length != store.state.shoppingCartList.goodsCount && selectAll.value == true) {
              selectAll.value = false
            } else if (selectGoodsInShoppingCart.list.length == store.state.shoppingCartList.goodsCount && selectAll.value == false) {
              selectAll.value = true
            }
          }
      )


      function isHasObj(arr, val) {
        let flag = false; // true为有 false为没有
        for (let i = 0; i < arr.length; i++) {
          if (JSON.stringify(arr[i]).indexOf(JSON.stringify(val)) != -1) {
            flag = true;
          }
        }
        return flag;
      }

      function isHasShopIndex(arr, val) {
        let flag = false; // true为有 false为没有
        for (let i = 0; i < arr.length; i++) {
          if (arr[i] === val) {
            flag = true;
            break;
          }
        }
        return flag;
      }

      function checkShopGoodsLength(arr, val) {
        let count = 0;
        for (let i = 0; i < arr.length; i++) {
          if (arr[i].index === val) {
            count++
          }
        }
        return count
      }

      watch(
          () => cloneDeep(selectShop),
          (value, preValue) => {
            if (value.list.length <= preValue.list.length) {
              for (let i = 0; i < preValue.list.length; i++) {
                let flag = isHasObj(value.list, preValue.list[i])
                if (flag == false) {
                  for (let j = 0; j < selectGoodsInShoppingCart.list.length; j++) {
                    if (checkShopGoodsLength(selectGoodsInShoppingCart.list, preValue.list[i]) == store.state.shoppingCartList.list[preValue.list[i]].goodsList.length) {
                      selectGoodsInShoppingCart.list[j] = '';
                    }
                  }

                  for (let j = 0; j < selectGoodsInShoppingCart.list.length; j++) {
                    if (selectGoodsInShoppingCart.list[j] == '') {
                      j--
                      selectGoodsInShoppingCart.list.splice(j, 1)
                    }
                  }

                }
              }
            }
            if (value.list.length > preValue.list.length) {
              for (let i = 0; i < value.list.length; i++) {
                let flag = isHasObj(preValue.list, value.list[i])
                if (checkShopGoodsLength(selectGoodsInShoppingCart.list, value.list[i]) < store.state.shoppingCartList.list[value.list[i]].goodsList.length) {
                  for (let j = 0; j < selectGoodsInShoppingCart.list.length; j++) {
                    if (selectGoodsInShoppingCart.list[j].index == value.list[i]) {
                      selectGoodsInShoppingCart.list.splice(j, 1);
                      j--
                    }
                  }
                  if (flag == false) {
                    for (let j = 0; j < store.state.shoppingCartList.list[selectShop.list[i]].goodsList.length; j++) {
                      selectGoodsInShoppingCart.list.push({
                        'goodsId': store.state.shoppingCartList.list[selectShop.list[i]].goodsList[j].goodsId,
                        'specificationsId': store.state.shoppingCartList.list[selectShop.list[i]].goodsSpecificationsList[j].specificationsId,
                        'userId': user1.userId,
                        'goodsPrice': store.state.shoppingCartList.list[selectShop.list[i]].goodsSpecificationsList[j].goodsPrice,
                        'goodsCount': store.state.shoppingCartList.list[selectShop.list[i]].goodsCountList[j],
                        'index': selectShop.list[i],
                        'index1': j
                      })
                    }
                  }
                }
              }
            }
          },
          {deep: true}
      )

      watch(
          selectAll,
          () => {
            if (selectAll.value == true) {
              for (let i = 0; i < store.state.shoppingCartList.list.length; i++) {
                selectShop.list[i] = i
              }
            } else if (selectGoodsInShoppingCart.list.length == store.state.shoppingCartList.goodsCount && selectAll.value == false) {
              selectShop.list.splice(0, store.state.shoppingCartList.list.length)
              selectGoodsInShoppingCart.list.splice(0, store.state.shoppingCartList.goodsCount)
            }
          },
      )

      function deleteShoppingCartList() {
        let flag = confirm('确定删除这些商品吗？')
        if (flag == true) {
          let searchItems = {
            list: []
          }
          for (let i = 0; i < selectGoodsInShoppingCart.list.length; i++) {
            let searchItem = {
              userId: selectGoodsInShoppingCart.list[i].userId,
              goodsId: selectGoodsInShoppingCart.list[i].goodsId,
              specificationsId: selectGoodsInShoppingCart.list[i].specificationsId
            }
            searchItems.list.push(searchItem)
          }
          removeShoppingCartList(searchItems).then(() => {
            getShoppingCartList(user1.userId)
          })
        }
      }

      function deleteShoppingCartItem(goodsId, specificationsId) {
        let flag = confirm('确定删除这个商品吗？')
        if (flag == true) {
          removeShoppingCart({
            'goodsId': goodsId,
            'specificationsId': specificationsId,
            'userId': user1.userId
          }).then(() => {
            getShoppingCartList(user1.userId)
          })
        }
      }

      //封装成方法，通过某个属性去重
      function filterRepeat(arr, prop) {
        return arr.filter(function (element, index, self) {
          return self.findIndex(el => el[prop] == element[prop]) === index
        })
      }

      function balanceAccount() {
        if (selectGoodsInShoppingCart.list.length == 0) {
          alert('请选择商品')
        } else {
          let arr = filterRepeat(selectGoodsInShoppingCart.list, 'index')
          let shoppingCartList = []
          for (let i = 0; i < arr.length; i++) {
            let shoppingCart = {
              shop: store.state.shoppingCartList.list[arr[i].index].shop,
              goodsList: [],
              goodsSpecificationsList: [],
              goodsCountList: []
            }
            for (let j = 0; j < selectGoodsInShoppingCart.list.length; j++) {
              if (selectGoodsInShoppingCart.list[j].index == arr[i].index) {
                shoppingCart.goodsList.push(store.state.shoppingCartList.list[selectGoodsInShoppingCart.list[j].index].goodsList[selectGoodsInShoppingCart.list[j].index1])
                shoppingCart.goodsSpecificationsList.push(store.state.shoppingCartList.list[selectGoodsInShoppingCart.list[j].index].goodsSpecificationsList[selectGoodsInShoppingCart.list[j].index1])
                shoppingCart.goodsCountList.push(store.state.shoppingCartList.list[selectGoodsInShoppingCart.list[j].index].goodsCountList[selectGoodsInShoppingCart.list[j].index1])
              }
            }
            shoppingCartList.push(shoppingCart)
          }
          sessionStorage.setItem('goodsList', JSON.stringify(shoppingCartList))
          router.push('/confirmOrderPage')
        }
      }

      return {
        loginFlag,
        loginName,
        logo,
        store,
        selectGoods,
        selectAll,
        selectShop,
        user1,
        selectGoodsInShoppingCart,
        updateShoppingCart,
        getShoppingCartList,
        removeShoppingCart,
        deleteShoppingCartList,
        goodsTotalCount,
        goodsTotalPrice,
        deleteShoppingCartItem,
        balanceAccount
      };
    },
    created() {
      window.addEventListener('setItem', ()=> {
        this.newVal = sessionStorage.getItem('user');
        loginName.value=(JSON.parse(sessionStorage.getItem("user")).username)
        let user= JSON.parse(sessionStorage.getItem('user'))


        user1.username=user.username
        user1.userId=user.userId
        user1.age=user.age
        user1.identity=user.identity
        user1.image=user.image
        user1.sex=user.sex
        user1.password=user.password
        user1.email=user.email
      })
    }
  }
</script>

<style scoped>


    /* 清除浏览器默认边距，
    使边框和内边距的值包含在元素的width和height内 */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    html{
        font-size:1px;
    }
    #main{
        width: 100%;
        height: auto;
    }
</style>