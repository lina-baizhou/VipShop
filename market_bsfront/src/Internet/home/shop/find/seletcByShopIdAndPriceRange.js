import Request from "../../../request/Request";

export default function selectGoodsByShopIdAndPriceRange(minPrice,maxPrice,typeId,shopId,orderColumn,orderFlag,currentPage) {
  return Request({
    url:'/shop/selectByPriceRange',
    method:'get',
    params:{
      minPrice,
      maxPrice,
      typeId,
      shopId,
      orderColumn,
      orderFlag,
      currentPage
    }
  })
}