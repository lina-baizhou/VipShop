import request from "../../../request/Request";

export default function findByShopId(shopId) {
  return  request({
    url: '/shop/findByShopId',
    method:'get',
    params:{
      shopId
    }
  })
}
