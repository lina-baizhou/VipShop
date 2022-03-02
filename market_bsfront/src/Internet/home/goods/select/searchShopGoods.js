import Request from "../../../request/Request";

export default function searchGoods(shopId,goodsId,goodsTitle,shelfState,id,typeLevel,currentPage) {
  return Request({
    url:'/goods/searchShopGoods',
    method:'get',
    params:{
      shopId,
      goodsId,
      goodsTitle,
      shelfState,
      id,
      typeLevel,
      currentPage
    }
  })
}