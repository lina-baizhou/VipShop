import Request from "../../request/Request";

export default function getCountries(province, city) {
  return Request({
    url: '/shop/getCountries',
    method:'get',
    params:{
      province,
      city
    }
  })
}