import Request from "../../request/Request";

export default function getCities(province) {
  return Request({
    url: '/shop/getCities',
    method:'get',
    params:{
      province
    }
  })
}