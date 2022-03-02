import request from "../../../request/Request";

export default function addShop(data) {
  return  request({
    url: '/shop/addShop',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}
