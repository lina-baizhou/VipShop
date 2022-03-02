import request from "../../../request/Request";

export default function saveShoppingCart(data) {
  return request({
    url: '/shop-order/payForOrder',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}