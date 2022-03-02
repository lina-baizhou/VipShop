import { createStore } from 'vuex'
import {reactive, ref} from "@vue/reactivity";


export default createStore({
  state: {
    searchedGoods:reactive({
      list:[]
    }),
    page:reactive({
      data:{}
    }),
    goodsCollectionList:reactive({
      list:[]
    }),
    shopCollectionList:reactive({
      list:[]
    }),
    goodsBrowseList:reactive({
      list:[]
    }),
    shoppingCartList:reactive({
      list:[],
      goodsCount:0
    }),
    goods:reactive({
      data:{}
    }),
    searchedTypes:reactive({
      list:[]
    }),
    searchedShops:reactive({
      list:[]
    }),
    searchedGoodsBySearchItem:reactive({
      list:[]
    }),
    searchItem:ref(''),
    searchFlag:ref('goods'),
    user:reactive({
          data:null
    })
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
