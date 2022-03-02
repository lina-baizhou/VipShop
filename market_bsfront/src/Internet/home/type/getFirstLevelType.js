import Request from "../../request/Request";
export default function getFirstLevelType() {
  return Request({
    url:'/first-level/getFirstLevelList',
    method:'get'
  })
}