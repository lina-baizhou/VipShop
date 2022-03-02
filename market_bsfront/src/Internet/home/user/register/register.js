import request from "../../../request/Request";

export default function register(data) {
  return  request({
    url: '/user/add',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}
