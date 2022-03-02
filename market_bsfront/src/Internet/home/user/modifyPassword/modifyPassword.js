import request from "../../../request/Request";

export default function sendEmail(id,password) {
  return  request({
    url: '/user/modifyPassword',
    method:'get',
    params:{
      id,
      password
    }
  })
}
