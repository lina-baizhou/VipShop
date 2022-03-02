import Request from "../../request/Request";
export default function getSecondLevelType(fatherId) {
  return Request({
    url:'/second-level/getSecondLevelList',
    method:'get',
    params:{
      fatherId
    }
  })
}