import request from "../../../request/Request";

export default function modifyOrder(orderNumber,harvestId) {
  return request({
    url: '/shop-order/modifyOrder',
    method:'get',
    params:{
      orderNumber,
      harvestId
    }
  })
}