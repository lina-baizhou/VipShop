import request from "../../../request/Request";

export default function saveShopCollection(data) {
  return request({
    url: '/shop-collection/saveShopCollection',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}