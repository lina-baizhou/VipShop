import request from "../../../request/Request";

export default function removeExchangeOrder(data) {
  return request({
    url: '/exchange-order/removeExchangeOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}