import request from "../../../request/Request";

export default function cancelShopOrder(data) {
  return request({
    url: '/shop-order/cancelShopOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}