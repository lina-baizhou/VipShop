import request from "../../../request/Request";

export default function selectUsername(UserName) {
  return  request({
    url: '/user/findUserName',
    method:'get',
    params:{
      UserName
    }
  })
}
