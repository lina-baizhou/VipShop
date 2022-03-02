import request from "../../../request/Request";

export default function getAllCommentsInf(goodsId) {
  return  request({
    url: '/comment/getAllCommentsInf',
    method:'get',
    params:{
      goodsId,
    }
  })
}
