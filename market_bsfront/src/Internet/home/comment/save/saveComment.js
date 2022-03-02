import request from "../../../request/Request";

export default function saveComment(data) {
  return request({
    url: '/comment/saveComment',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}