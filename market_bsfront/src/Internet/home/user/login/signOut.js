import request from "../../../request/Request";

export default function signOut() {
  return  request({
    url: '/user/cancelLogin',
    method:'get',
  })
}
