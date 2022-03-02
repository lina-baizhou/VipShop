import request from "../../../request/Request";

export default function selectGoodsCollectionFlag(userId,goodsId) {
  return request({
    url: '/goods-collection/getGoodsCollectionFlag',
    method:'get',
    params:{
      userId,
      goodsId
    }
  })
}