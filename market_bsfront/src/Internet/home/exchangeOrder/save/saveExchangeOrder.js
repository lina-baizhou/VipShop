import request from "../../../request/Request";

export default function saveExchangeOrder(data) {
  return request({
    url: '/exchange-order/saveExchangeOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}