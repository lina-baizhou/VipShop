import request from "../../../request/Request";

export default function addAGoods(data) {
  return request({
    url: '/goods/selectByTypeId',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}