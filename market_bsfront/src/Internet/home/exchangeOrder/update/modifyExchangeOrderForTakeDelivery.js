import request from "../../../request/Request";

export default function modifyExchangeOrderForTakeDelivery(data) {
  return request({
    url: '/exchange-order/modifyExchangeOrderForTakeDelivery',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}