import Request from "../../../request/Request";

export default function searchGoodsType(searchItem) {
  return Request({
    url:'/goods/searchGoodsType',
    method:'get',
    params:{
      searchItem,
    }
  })
}