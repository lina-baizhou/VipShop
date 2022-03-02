import request from "../../../request/Request";

export default function setDefaultAddress(oldAddressId,newAddressId,userId) {
  return request({
    url: '/harvest-address/setDefaultAddress',
    method:'get',
    params:{
      oldAddressId,
      newAddressId,
      userId
    }
  })
}