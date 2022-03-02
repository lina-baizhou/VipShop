import request from "../../../request/Request";

export default function saveSellerRestore(data) {
  return request({
    url: '/comment/saveSellerRestore',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}