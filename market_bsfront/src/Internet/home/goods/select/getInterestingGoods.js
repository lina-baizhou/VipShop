import Request from "../../../request/Request";

export default function getInterestingGoods(userId) {
  return Request({
    url:'/goods/getInterestingGoods',
    method:'get',
    params:{
      userId
    }
  })
}