import request from "../../../request/Request";

export default function saveBrowsingHistory(data) {
  return request({
    url: '/browsing-history/saveBrowsingHistory',
    method:'post',
    data: JSON.stringify(data),
    datatype:'json',
  })
}