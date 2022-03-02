import request from "../../../request/Request";

export default function sendEmail(QQEmail) {
  return  request({
    url: '/user/sendEmail',
    method:'get',
    params:{
      QQEmail
    }
  })
}
