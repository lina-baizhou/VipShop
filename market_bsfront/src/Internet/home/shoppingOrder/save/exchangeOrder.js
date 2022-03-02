import request from "../../../request/Request";

export default function exchangeOrder(data) {
  return request({
    url: '/shop-order/exchangeOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}