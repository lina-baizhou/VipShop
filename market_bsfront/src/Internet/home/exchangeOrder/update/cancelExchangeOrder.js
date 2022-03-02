import request from "../../../request/Request";

export default function cancelExchangeOrder(data) {
  return request({
    url: '/exchange-order/cancelExchangeOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}