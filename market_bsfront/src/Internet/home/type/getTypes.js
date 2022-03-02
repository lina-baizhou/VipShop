import axios from "axios";
export default function getTypes() {
  return  axios({
    url: '/api/types.json',
    method: 'get',
    dataType: 'jsonp',
  })

}