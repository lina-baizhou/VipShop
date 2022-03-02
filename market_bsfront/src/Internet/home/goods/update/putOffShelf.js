import request from "../../../request/Request";

export default function putOffShelf(data) {
  return request({
    url: '/goods/putOffShelf',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}