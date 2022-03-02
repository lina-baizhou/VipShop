import request from "../../../request/Request";

export default function getCommentListForShow(goodsId,chooseItem,currentPage) {
  return  request({
    url: '/comment/getCommentListForShow',
    method:'get',
    params:{
      goodsId,
      chooseItem,
      currentPage
    }
  })
}
