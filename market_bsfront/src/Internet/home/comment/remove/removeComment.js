import request from "../../../request/Request";

export default function removeComment(orderNumber,goodsId,specificationsId) {
  return request({
    url: '/comment/removeComment',
    method:'get',
    params:{
      orderNumber,
      goodsId,
      specificationsId
    }
  })
}