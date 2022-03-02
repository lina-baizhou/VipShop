import request from "../../../request/Request";

export default function searchShop(searchItem,currentPage,orderItem) {
  return  request({
    url: '/shop/searchShop',
    method:'get',
    params:{
      searchItem,
      currentPage,
      orderItem
    }
  })
}
