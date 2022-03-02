import request from "../../../request/Request";

export default function getShopSpecifications(shopId) {
  return  request({
    url: '/shop/getShopSpecifications',
    method:'get',
    params:{
      shopId
    }
  })
}
