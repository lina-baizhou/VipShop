import request from "../../../request/Request";

export default function searchShopSalesReport(selectItem,shopId) {
  return request({
    url: '/shop-sales-report/searchShopSalesReport',
    method:'get',
    params:{
      selectItem,
      shopId
    }
  })
}