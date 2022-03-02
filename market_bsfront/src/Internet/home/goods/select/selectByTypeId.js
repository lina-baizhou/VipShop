import Request from "../../../request/Request";

export default function selectGoodsByTypeId(typeId) {
  return Request({
    url:'/goods/selectByTypeId',
    method:'get',
    params:{
      typeId
    }
  })
}