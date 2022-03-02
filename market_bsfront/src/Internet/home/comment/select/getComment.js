import request from "../../../request/Request";

export default function getComment(data) {
  return  request({
    url: '/comment/getComment',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}
