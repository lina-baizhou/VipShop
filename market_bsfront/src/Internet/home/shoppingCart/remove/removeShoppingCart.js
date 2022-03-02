import request from "../../../request/Request";

export default function removeShoppingCart(data) {
  return request({
    url: '/shopping-cart/removeShoppingCart',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}