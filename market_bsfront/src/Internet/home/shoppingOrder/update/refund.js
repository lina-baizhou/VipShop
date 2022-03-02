import request from "../../../request/Request";

export default function refund(orderNumber,goodsCount,tradeNo,money,returnFlag,index) {
  return request({
    url: '/shop-order/refund',
    method:'post',
    params:{
      orderNumber,
      tradeNo,
      money,
      returnFlag,
      index,
      goodsCount
    }
  })
}