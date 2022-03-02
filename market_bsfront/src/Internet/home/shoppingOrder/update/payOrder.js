import request from "../../../request/Request";

export default function payOrder(orderNumber,url) {
  return request({
    url: '/shop-order/payOrder',
    method:'get',
    params:{
      orderNumber,
      url
    }
  })
}