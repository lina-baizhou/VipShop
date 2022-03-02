const webpack = require("webpack");
module.exports = {
  publicPath:'./',
  configureWebpack: {
    plugins: [
      new webpack.ProvidePlugin({
        $: 'jquery',         // 创建 '$'、'jQuery'、'window.jQuery' 三个变量指向依赖jquery
        jQuery: 'jquery',
        'window.jQuery': 'jquery'
      })
    ]
  },
  css: {
    loaderOptions: {
      less: {
        javascriptEnabled: true,
      }
    }
  },
  devServer: {
    // 配置多个代理
    proxy: {
      "/servlet": {
        target: "http://localhost:80/",// 要访问的接口域名
        changeOrigin:true,
        pathRewrite: {
          '^/servlet': ''
        }
      },
      '/api': {
        target: 'http://localhost:8080', //路径指向本地主机地址及端口号
        ws: true,
        changeOrigin: true,
        pathRewrite:{
          '^/api': '/data' //路径转发代理
        }
      }
    },
  }
}
