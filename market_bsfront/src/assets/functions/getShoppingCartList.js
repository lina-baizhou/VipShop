import store from '../../store/index'
import selectShoppingCartList from "../../Internet/home/shoppingCart/select/selectShoppingCartList";

export default async function getShoppingCartList(userId) {
  await selectShoppingCartList(userId).then(res => {
    store.state.shoppingCartList.list = res.data
    store.state.shoppingCartList.goodsCount = 0
    for (let i = 0; i < res.data.length; i++) {
      store.state.shoppingCartList.goodsCount += res.data[i].goodsList.length
      for (let j = 0; j < res.data[i].goodsList.length; j++) {
        store.state.shoppingCartList.list[i].goodsList[j].goodsImage1 = require('../../assets/img/' + res.data[i].goodsList[j].goodsImage1)
        store.state.shoppingCartList.list[i].goodsList[j].selected=false
        store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType1 = (store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType1.split(':'))[1]
        if (store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType2 !== null) {
          store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType = store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType1 + ';' + (store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType2.split(':'))[1]
        }else {
          store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType=store.state.shoppingCartList.list[i].goodsSpecificationsList[j].goodsType1
        }
      }
    }
  })
}