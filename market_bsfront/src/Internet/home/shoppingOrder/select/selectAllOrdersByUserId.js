import request from "../../../request/Request";

export default function selectAllOrdersByUserId(userId) {
  return request({
    url: '/shop-order/queryAllOrderListByUserId',
    method:'get',
    params:{
      userId
    }
  })
}