import request from "../../../request/Request";

export default function findByShopName(shopName) {
  return  request({
    url: '/shop/findByShopName',
    method:'get',
    params:{
      shopName
    }
  })
}
