import request from "../../../request/Request";

export default function searchShopOrders(shopId,currentPage,username,orderNumber,orderState,goodsTitle,fromDate,toDate) {
  return request({
    url: '/shop-order/searchShopOrders',
    method:'get',
    params:{
      shopId,
      currentPage,
      username,
      orderNumber,
      orderState,
      goodsTitle,
      fromDate,
      toDate
    }
  })
}