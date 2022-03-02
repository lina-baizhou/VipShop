import Request from "../../../request/Request";
export default function updateShopInf(data) {
  return Request({
    url: '/shop/updateShop',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}