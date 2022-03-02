import request from "../../../request/Request";

export default function deleteOrderInManage(orderNumber) {
  return request({
    url: '/shop-order/deleteOrderInManage',
    method:'get',
    params:{
      orderNumber
    }
  })
}