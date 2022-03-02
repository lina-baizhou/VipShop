import request from "../../../request/Request";

export default function takeExchangeOrderDelivery(data) {
  return request({
    url: '/shop-order/takeExchangeOrderDelivery',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}