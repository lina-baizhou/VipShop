import request from "../../../request/Request";

export default function modifyOrderGoodsToReturn(data) {
  return request({
    url: '/shop-order/modifyOrderToReturn',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
    enctype:"multipart/form-data"
  })
}