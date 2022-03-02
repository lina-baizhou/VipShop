import request from "../../../request/Request";

export default function sendEmailWhenRegister(QQEmail) {
  return  request({
    url: '/user/sendEmailInRegister',
    method:'get',
    params:{
      QQEmail
    }
  })
}
