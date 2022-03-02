import Request from "../../../request/Request";

export default function selectByGoodsId(goodsId) {
  return Request({
    url:'/goods/selectByGoodsId',
    method:'get',
    params:{
      goodsId
    }
  })
}