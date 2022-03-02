import Request from "../../../request/Request";

export default function getGoodsSpecificationsInf(goodsId) {
  return Request({
    url: '/goods-specifications/getTypeInf',
    method:'get',
    params:{
      goodsId,
    }
  })
}