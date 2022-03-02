export let base64ToFile = (urlData, fileName) => {
  let arr = urlData.split(',');
  console.log(arr[0]);
  let mime = arr[0].match(/:(.*?);/)[1];
  let bytes = atob(arr[1]); // 解码base64
  let n = bytes.length
  let ia = new Uint8Array(n);
  while (n--) {
    ia[n] = bytes.charCodeAt(n);
  }
  return new File([ia], fileName, { type: mime });
}
