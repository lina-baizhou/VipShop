import Request from "../../../request/Request";

export default function modifyGoodsSpecifications(data) {
  return Request({
    url: '/goods-specifications/modifyGoodsSpecifications',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}