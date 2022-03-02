import request from "../../../request/Request";

export default function updateAddress(data) {
  return request({
    url: '/harvest-address/updateAddress',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}