import request from "../../../request/Request";

export default function removeShopCollection(data) {
  return request({
    url: '/shop-collection/removeShopCollection',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}