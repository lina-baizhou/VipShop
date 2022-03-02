import axios from "axios";
export default function getLocals() {
    return  axios({
      url: '/api/cities.json',
      method: 'get',
      dataType: 'jsonp',
    })

}