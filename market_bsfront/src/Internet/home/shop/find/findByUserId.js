import request from "../../../request/Request";

export default function findByUserId(userId) {
  return  request({
    url: '/shop/findByUserId',
    method:'get',
    params:{
      userId
    }
  })
}
