import Request from "../../../request/Request";

export default function getBestSellerGoods() {
  return Request({
    url:'/goods/getBestSellerGoods',
    method:'get',
  })
}