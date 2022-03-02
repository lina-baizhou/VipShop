import request from "../../../request/Request";

export default function deleteGoodsCollection(data) {
  return request({
    url: '/goods-collection/deleteGoodsCollection',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}