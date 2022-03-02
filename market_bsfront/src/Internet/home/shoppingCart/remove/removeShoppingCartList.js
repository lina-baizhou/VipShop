import request from "../../../request/Request";

export default function removeShoppingCartList(data) {
  return request({
    url: '/shopping-cart/removeShoppingCartList',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}