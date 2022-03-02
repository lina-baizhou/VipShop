import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';
import $ from 'jquery'



// 全局获取缓存数据
function resetSetItem(key, newVal) {
  if (key === 'user') {

    // 创建一个StorageEvent事件
    let newStorageEvent = document.createEvent('StorageEvent');
    const storage = {
      setItem: function (k, val) {
        sessionStorage.setItem(k, val);

        // 初始化创建的事件
        newStorageEvent.initStorageEvent('setItem', false, false, k, null, val, null, null);

        // 派发对象
        window.dispatchEvent(newStorageEvent)
      }
    }
    return storage.setItem(key, newVal);
  }
}


const app=createApp(App)


app.config.globalProperties.resetSetItem = resetSetItem;

// // 全局使用图标
const icons= Icons;
for (const i in icons) {
  app.component(i, icons[i]);
}

let docEl = document.documentElement;
let width = window.screen.width;
let rem = width / (1272);
docEl.style.fontSize = rem + 'px';

let lastsUserAgent = navigator.userAgent.toLowerCase();

let mql = window.matchMedia('(orientation: portrait)');

let lastDirection=mql.matches

let lastHeight=docEl.getBoundingClientRect().height

function refreshRem() {

  let height = docEl.getBoundingClientRect().height;


  let sUserAgent = navigator.userAgent.toLowerCase();



  if(lastsUserAgent!=sUserAgent||((mql.matches!==lastDirection)&&!(sUserAgent.includes('windows')))&&lastHeight!=height){
    width =  window.screen.width ;
    rem = width / (1272);
    docEl.style.fontSize = rem + 'px';


    let div = document.getElementById('showRightMain');


    if(div!=null){
      div.style.height = height + 'px'
    }


    docEl.style.rem = window.rem = rem;

    lastsUserAgent=sUserAgent

    lastDirection=mql.matches
  }

  if(docEl.getBoundingClientRect().width<=(1000*rem)){
    let divs=document.getElementsByClassName('mainDiv')
    for(let i=0;i<divs.length;i++){

      divs[i].style.justifyContent='space-between'
    }
  }else {
    let divs=document.getElementsByClassName('mainDiv')
    for(let i=0;i<divs.length;i++){

      divs[i].style.justifyContent='space-evenly'
    }
  }


  lastHeight=height
}

window.addEventListener('resize', refreshRem);

app.use(store).use(router).use(Antd).use($).mount('#app')

