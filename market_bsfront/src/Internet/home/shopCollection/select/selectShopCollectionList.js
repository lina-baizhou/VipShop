import request from "../../../request/Request";

export default function selectShopCollectionList(userId) {
  return request({
    url: '/shop-collection/getShopCollectionList',
    method:'get',
    params:{
      userId
    }
  })
}