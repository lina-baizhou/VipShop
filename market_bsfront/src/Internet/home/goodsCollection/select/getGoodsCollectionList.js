import request from "../../../request/Request";

export default function getGoodsCollectionList(userId) {
  return request({
    url: '/goods-collection/getGoodsCollectionList',
    method:'get',
    params:{
      userId,
    }
  })
}