import request from "../../../request/Request";

export default function abandonComment(data) {
  return request({
    url: '/comment/abandonComment',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}