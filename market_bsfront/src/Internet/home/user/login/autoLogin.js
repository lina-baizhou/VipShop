import request from "../../../request/Request";

export default function autoLogin() {
  return  request({
    url: '/user/autoLogin',
    method:'get',
  })
}
