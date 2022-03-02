import Request from "../../request/Request";
export default function getThirdType(id) {
  return Request({
    url:'/third-level/getThirdType',
    method:'get',
    params:{
      id
    }
  })
}