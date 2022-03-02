import request from "../../../request/Request";

export default function removeReturnOrder(data) {
  return request({
    url: '/return-order/removeReturnOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}