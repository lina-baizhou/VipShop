import request from "../../../request/Request";

export default function remindOfCommenting(email1,username,shopName) {
  return request({
    url: '/shop-order/remindOfCommenting',
    method:'get',
    params:{
      email1,
      username,
      shopName
    }
  })
}