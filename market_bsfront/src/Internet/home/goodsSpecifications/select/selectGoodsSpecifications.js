import Request from "../../../request/Request";

export default function getGoodsSpecifications(goodsId,goodsType1,goodsType2) {
  return Request({
    url: '/goods-specifications/selectGoodsSpecifications',
    method:'get',
    params:{
      goodsId,
      goodsType1,
      goodsType2
    }
  })
}