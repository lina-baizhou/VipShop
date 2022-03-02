import request from "../../../request/Request";

export default function getShopComments(shopId) {
  return  request({
    url: '/comment/getShopComments',
    method:'get',
    params:{
      shopId
    }
  })
}
