import request from "../../../request/Request";

export default function putOnShelf(data) {
  return request({
    url: '/goods/putOnShelf',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}