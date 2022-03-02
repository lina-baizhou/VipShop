import request from "../../../request/Request";
export default function getHarvestAddressById(harvestNumber) {
  return request({
    url: '/harvest-address/getHarvestAddressById',
    method:'get',
    params:{
      harvestNumber
    }
  })
}