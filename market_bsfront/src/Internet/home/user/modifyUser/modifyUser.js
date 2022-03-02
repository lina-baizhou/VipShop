import request from "../../../request/Request";

export default function modifyUser(data) {
  return  request({
    url: '/user/update',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}
