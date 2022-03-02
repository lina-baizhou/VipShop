import Request from "../../request/Request";

export default function getProvinces() {
  return Request({
    url: '/shop/getProvinces',
    method:'get',
  })
}