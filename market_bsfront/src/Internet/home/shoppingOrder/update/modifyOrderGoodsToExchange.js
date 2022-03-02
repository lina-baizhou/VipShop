import request from "../../../request/Request";

export default function modifyOrderToExchange(data) {
  return request({
    url: '/shop-order/modifyOrderToExchange',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}