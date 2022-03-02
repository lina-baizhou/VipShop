import request from "../../../request/Request";

export default function searchShopGoodsComments(shopId,goodsId,username,chooseItem,goodsTitle,currentPage,fromDate,toDate) {
  return  request({
    url: '/comment/getShopGoodsComments',
    method:'get',
    params:{
      shopId,
      goodsId,
      username,
      chooseItem,
      goodsTitle,
      currentPage,
      fromDate,
      toDate
    }
  })
}
