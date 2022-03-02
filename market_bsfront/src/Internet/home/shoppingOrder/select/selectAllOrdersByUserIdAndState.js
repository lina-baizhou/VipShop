import request from "../../../request/Request";

export default function selectAllOrdersByUserIdAndState(userId,orderState) {
  return request({
    url: '/shop-order/queryAllOrderListByUserIdAndState',
    method:'get',
    params:{
      userId,
      orderState
    }
  })
}