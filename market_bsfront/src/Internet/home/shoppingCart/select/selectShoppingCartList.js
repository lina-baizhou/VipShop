import request from "../../../request/Request";

export default function selectShoppingCartList(userId) {
  return request({
    url: '/shopping-cart/selectShoppingCartList',
    method:'get',
    params:{
      userId
    }
  })
}