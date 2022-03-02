import request from "../../../request/Request";

export default function addAddress(data) {
  return request({
    url: '/harvest-address/save',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}