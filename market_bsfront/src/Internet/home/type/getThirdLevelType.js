import Request from "../../request/Request";
export default function getThirdLevelType(fatherId) {
  return Request({
    url:'/third-level/getThirdLevelList',
    method:'get',
    params:{
      fatherId
    }
  })
}