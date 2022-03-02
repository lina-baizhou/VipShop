import Request from "../../request/Request";
export default function selectSecondLevel(id) {
  return Request({
    url:'/second-level/getSecondLevel',
    method:'get',
    params:{
      id
    }
  })
}