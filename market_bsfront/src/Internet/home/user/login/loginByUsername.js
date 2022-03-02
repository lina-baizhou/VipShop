import request from "../../../request/Request";

export default function loginByUsername(data) {
  return  request({
    url: '/user/loginByUsername',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}
