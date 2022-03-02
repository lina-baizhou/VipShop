import request from "../../../request/Request";

export default function selectShopCollection(data) {
  return request({
    url: '/shop-collection/selectShopCollection',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}