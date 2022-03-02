import request from "../../../request/Request";

export default function selectOrderByOrderNumber(orderNumber) {
  return request({
    url: '/shop-order/queryOrderByOrderNumber',
    method:'get',
    params:{
      orderNumber
    }
  })
}