import request from "../../../request/Request";

export default function addGoodsCollection(data) {
  return request({
    url: '/goods-collection/saveGoodsCollection',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}