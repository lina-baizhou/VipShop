import request from "../../../request/Request";
export default function getHarvestAddressList(userId) {
  return request({
    url: '/harvest-address/getHarvestAddressList',
    method:'get',
    params:{
      userId
    }
  })
}