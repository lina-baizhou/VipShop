import {createRouter, createWebHashHistory} from 'vue-router'
import Home from "../components/home/Home";
import Login from "../components/login/loginPage";
import loginPage from "../views/login/Login"
import findPassword from "../views/findPassword/findPassword";
import Register from "../views/register/Register";

import UserAccount from "../components/UserAccount/UserAccount";
import HeadInf from "../views/inf/headInf"
import UserInf from "../views/UserAccount/UserInf";
import ModifyUserInf from "../views/UserAccount/ModifyUserInf";
import ModifyUserName from "../views/UserAccount/ModifyUserName";
import ModifyEmail from "../views/UserAccount/ModifyEmail";
import UserAddress from "../views/UserAccount/UserAddress";
import UserOrder from '../views/UserAccount/order/UserOrder'
import OrderDetailPage from '../views/UserAccount/OrderDetailPage';
import ModifyOrder from '../views/UserAccount/order/ModifyOrder';
import selectAfterSaleType from '../views/UserAccount/order/selectAfterSaleType';
import returnOrder from '../views/UserAccount/order/returnOrder';
import exchangeOrder from '../views/UserAccount/order/exchangeOrder'

import OpenStore from "../views/shop/OpenShop";

import shop from "../components/store/shop";
import shopHomePage from "../views/shop/shopHomePage";
import modifyShopInf from "../views/shop/modifyShopInf";

import releaseGoods from "../views/shop/goods/releaseGoods";
import goodsTable from "../views/shop/goods/goodsTable"
import modifyGoods from "../views/shop/goods/modifyGoods"
import orderManage from "../views/shop/order/orderManage"
import CommentManage from "../views/shop/comment/CommentManage";


import SearchedGoodsByType from '../components/searchedGoods/SearchedGoodsByType'
import SearchedShopByShopId from '../components/searchedShop/SearchedShopByShopId'
import SearchedGoods from '../components/searchedGoods/SearchedGoods'
import SearchedShops from '../components/searchedShop/searchedShops'

import GoodsDetailPage from '../views/goods/GoodsDetailPage'

import ShoppingCart from '../components/shoppingCart/ShoppingCart'

import confirmOrderPage from '../components/confirmOrder/confirmOrderPage'

import PayPage from '../components/pay/PayPage'

import releaseComment from '../views/comment/releaseComment'

import mySale from '../components/mySale/mySale'
import Browse from '../views/mySale/Browse'
import Collection from '../views/mySale/Collection'
import ShopCollection from '../views/mySale/ShopCollection'

const routes = [
    {
      path: '',
      redirect:'/mainHeader/home',
    },
    {
      path:'/shoppingCartPage',
      name: 'shoppingCartPage',
      component: ShoppingCart,
    },
   {
    path: '/payPage',
     component: PayPage
   },
   {
     path:'/confirmOrderPage',
     component: confirmOrderPage,
   },
    {
    path: '/login',
    name: 'Login',
    component: Login,
    children:[
      {
        path: 'loginPage',
        component:loginPage,
      },
      {
        path: 'findPassword',
        component: findPassword
      },
      {
        path: 'register',
        component: Register
      },
    ]
  },
  {
    path: '/shop',
    name: 'shop',
    component:shop,
    children:[
      {
        path: 'shopHome',
        component: shopHomePage
      },
      {
        path:'modifyShopInf',
        component: modifyShopInf
      },
      {
        path: 'releaseGoods',
        component: releaseGoods
      },
      {
        path: 'goodsTable',
        component: goodsTable
      },
      {
        path: 'commentManage',
        component: CommentManage
      },
      {
        path: 'modifyGoods',
        component: modifyGoods
      },
      {
        path: 'orderManage',
        component: orderManage
      }
    ]
  },
  {
    path: '/openStore',
    name: 'openStore',
    component: OpenStore,
  },
  {
    path: '/mainHeader',
    name: 'mainHeader',
    component: HeadInf,
    children:[
      {
        path: 'home',
        component: Home
      },
      {
        path: 'mySale',
        component: mySale,
        children:[
          {
            path: 'browse',
            component: Browse,
          },
          {
            path: 'collection',
            component: Collection
          },
          {
            path:'shopCollection',
            component: ShopCollection
          }
        ]
      },
      {
        path: 'searchedGoodsByType',
        name:'searchedGoodsByType',
        component: SearchedGoodsByType
      },
      {
        path: 'searchedShopByShopId',
        name: 'searchedShopByShopId',
        component: SearchedShopByShopId
      },
      {
        path: 'searchedShops',
        name: 'searchedShops',
        component: SearchedShops
      },
      {
        path: 'searchedGoods',
        name: 'searchedGoods',
        component: SearchedGoods
      },
      {
        path: 'goodsDetailPage',
        component: GoodsDetailPage
      },
      {
        path: 'releaseComment',
        component: releaseComment
      },
      {
        path: 'UserAccountCenter',
        component: UserAccount,
        children:[
          {
            path: '',
            redirect:'/mainHeader/UserAccountCenter/userInf',
          },
          {
            path: 'userInf',
            component: UserInf
          },
          {
            path:'modifyUserInf',
            component: ModifyUserInf
          },
          {
            path: 'modifyUserName',
            component: ModifyUserName,
          },
          {
            path: 'modifyEmail',
            component: ModifyEmail
          },
          {
            path: 'setUserAddress',
            component: UserAddress
          },
          {
            path:'userOrder',
            component: UserOrder
          },
          {
            path:'orderDetail',
            component:OrderDetailPage
          },
          {
            path: 'modifyOrder',
            component: ModifyOrder
          },
          {
            path: 'selectAfterSaleType',
            component: selectAfterSaleType
          },
          {
            path: 'returnOrder',
            component: returnOrder
          },
          {
            path: 'exchangeOrder',
            component: exchangeOrder
          }
        ],
      }
    ],
  }
]


const router = createRouter({
  history:createWebHashHistory(),
  routes
})

//导航守卫
router.beforeEach((to,from,next)=>{
  if(to.path.search("/login")!=-1) return next();
  if(to.path.search("/mainHeader/home")!=-1) return next();
  if(to.path.search("/mainHeader/searchedGoodsByType")!=-1) return next();
  if(to.path.search("/mainHeader/searchedShopByShopId")!=-1) return next();
  if(to.path.search("/mainHeader/searchedShops")!=-1) return next();
  if(to.path.search("/mainHeader/searchedGoods")!=-1) return next();
  if(to.path.search("/mainHeader/goodsDetailPage")!=-1) return next();
  const user = window.sessionStorage.getItem('user')
  if(!user){
    alert("请先登录！")
    return next('/login/loginPage')
  }
  next()
})

export default router
