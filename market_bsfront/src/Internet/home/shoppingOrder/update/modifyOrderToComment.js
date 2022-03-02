import request from "../../../request/Request";

export default function changeOrderToComment(data) {
  return request({
    url: '/shop-order/changeOrderToComment',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}