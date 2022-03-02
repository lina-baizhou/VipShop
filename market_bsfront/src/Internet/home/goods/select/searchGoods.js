import Request from "../../../request/Request";

export default function searchGoods(minPrice,maxPrice,searchItem,typeId,orderColumn,orderFlag,currentPage) {
  return Request({
    url:'/goods/searchGoods',
    method:'get',
    params:{
      minPrice,
      maxPrice,
      typeId,
      searchItem,
      orderColumn,
      orderFlag,
      currentPage
    }
  })
}