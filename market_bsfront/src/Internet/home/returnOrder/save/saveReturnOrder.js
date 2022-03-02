import request from "../../../request/Request";

export default function saveReturnOrder(data) {
  return request({
    url: '/return-order/saveReturnOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}