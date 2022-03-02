export let imageToBase64 = (img) => {
// Vue.prototype.$imageToBase64 = img => { //定义全局
  let  canvas = document.createElement("canvas");
  canvas.width = img.width;
  canvas.height = img.height;
  // console.log('canvas:'+canvas);

  let ctx = canvas.getContext("2d");
  ctx.drawImage(img, 0, 0, img.width, img.height);
  // console.log('ctx:'+ctx);

  let ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
  // console.log('ext:'+ext);

  let dataURL = canvas.toDataURL("image/jpeg" + ext);
  // console.log('dataURL:'+dataURL);

  return dataURL;
};
