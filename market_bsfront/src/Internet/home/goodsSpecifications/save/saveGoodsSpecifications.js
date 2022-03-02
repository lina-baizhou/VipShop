import Request from "../../../request/Request";

export default function saveGoodsSpecifications(data) {
  return Request({
    url: '/goods-specifications/saveGoodsSpecifications',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}