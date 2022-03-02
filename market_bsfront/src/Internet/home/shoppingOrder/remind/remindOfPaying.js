import request from "../../../request/Request";

export default function remindOfPaying(email1,username,shopName) {
  return request({
    url: '/shop-order/remindOfPaying',
    method:'get',
    params:{
      email1,
      username,
      shopName
    }
  })
}