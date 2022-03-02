import Request from "../../../request/Request";

export default function selectGoodsByTypeIdAndPriceRange(minPrice,maxPrice,typeId,orderColumn,orderFlag,currentPage) {
  return Request({
    url:'/goods/selectByPriceRange',
    method:'get',
    params:{
      minPrice,
      maxPrice,
      typeId,
      orderColumn,
      orderFlag,
      currentPage
    }
  })
}