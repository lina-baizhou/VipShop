import request from "../../../request/Request";

export default function selectBrowsingHistoryList(userId) {
  return request({
    url: '/browsing-history/getBrowsingHistoryList',
    method:'get',
    params:{
      userId
    }
  })
}