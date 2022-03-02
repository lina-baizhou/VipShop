import request from "../../../request/Request";

export default function remindOfTakingDelivery(email1,shopName) {
  return request({
    url: '/shop-order/remindOfTakingDelivery',
    method:'get',
    params:{
      email1,
      shopName
    }
  })
}