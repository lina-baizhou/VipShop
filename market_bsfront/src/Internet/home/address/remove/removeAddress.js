import request from "../../../request/Request";

export default function removeAddress(data) {
  return request({
    url: '/harvest-address/deleteAddress',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}