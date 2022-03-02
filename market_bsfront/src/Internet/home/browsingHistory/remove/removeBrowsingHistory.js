import request from "../../../request/Request";

export default function removeBrowsingHistory(userId,goodsId) {
  return request({
    url: '/browsing-history/deleteBrowsingHistory',
    method:'get',
    params:{
      userId,
      goodsId
    }
  })
}