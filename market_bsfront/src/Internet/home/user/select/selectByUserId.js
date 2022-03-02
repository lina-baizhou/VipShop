import request from "../../../request/Request";

export default function selectByUserId(userId) {
  return  request({
    url: '/user/getUserByUserId',
    method:'get',
    params:{
      userId
    }
  })
}
