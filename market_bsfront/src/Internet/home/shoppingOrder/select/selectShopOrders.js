import request from "../../../request/Request";

export default function selectAllOrdersByUserId(shopId) {
  return request({
    url: '/shop-order/getShopOrders',
    method:'get',
    params:{
      shopId
    }
  })
}