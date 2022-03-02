import request from "../../../request/Request";

export default function toWaitTakingDelivery(orderNumber) {
  return request({
    url: '/shop-order/toWaitTakingDelivery',
    method:'post',
    params:{
      orderNumber
    }
  })
}