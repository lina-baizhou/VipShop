import Request from "../../../request/Request";

export default function getGoodsSpecificationsByGoodsId(goodsId) {
  return Request({
    url: '/goods-specifications/getGoodsSpecificationsByGoodsId',
    method:'get',
    params:{
      goodsId,
    }
  })
}