import request from "../../../request/Request";

export default function changeOrderToPaid(data) {
  return request({
    url: '/shop-order/changeOrderToPaid',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}