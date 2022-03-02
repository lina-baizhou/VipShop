import axios from 'axios'

export default function request(config) {
  // 1.创建axios的实例
  const instance = axios.create({
    baseURL: '/servlet',
    headers: {
      'Accept': '*/*',
      'Content-Type': 'application/json; charset=utf-8'
    },
  })


  // 2.axios的拦截器
  // 2.1.请求拦截的作用
  instance.interceptors.request.use(config => {
    return config
  }, err => {
    console.log(err);
  })

  // 2.2.响应拦截
  instance.interceptors.response.use(res => {
    return res.data
  }, err => {
    console.log(err);
  })

  // 3.发送真正的网络请求
  return instance(config)
}
