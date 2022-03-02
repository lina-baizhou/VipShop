import request from "../../../request/Request";

export default function updateShoppingCart(data) {
  return request({
    url: '/shopping-cart/updateShoppingCart',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}