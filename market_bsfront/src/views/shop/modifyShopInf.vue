<template>
    <div style="width: 100%;height: auto;padding-top: 20rem;padding-bottom: 20rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center;font-size: 12rem">
        <div style="width: 90%;height: auto;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
            <div style="width: 340rem;height: 30rem;color: grey;border: 1rem lightgrey solid;display: flex;justify-content: space-between;align-items: center;background: white">
                <span>
                    请注意！您填写的信息将在店铺前台展示给买家，请认真填写！
                </span>
            </div>
            <div style="width: 400rem;height: auto;display: flex;flex-direction: column;justify-content: space-evenly;align-items: flex-start">
                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 30rem">
                    <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          店铺名称：
                       </span>
                    </div>
                    <div class="right_input">
                        <input :onblur="checkShopNameFunction" type="text" v-model="shop.shopName" style="width: 200rem;height: 20rem;border: 1rem grey solid;border-radius: 2rem">
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">

                    </div>
                    <div class="right_input">
                        <span>
                            {{checkShopName}}
                        </span>
                    </div>
                </div>


                <div style="width: 400rem;height: 110rem;display: flex;justify-content: space-between;align-items: center;margin-top: 20rem">
                    <div class="left_label">
                       <span>
                           <span style="color: indianred;font-size: 20rem">*</span>
                           店铺标志：
                       </span>
                    </div>
                    <div class="right_input">
                        <img :src="shopImage" style="width: 90rem;height: 90rem">
                        <input :onblur="checkShopLogoFunction" class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="onchangeImgFun">
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">

                    </div>
                    <div class="right_input">
                        <span>
                            {{checkShopLogo}}
                        </span>
                    </div>
                </div>

                <div style="width: 400rem;height: 40rem;display: flex;justify-content: space-between;align-items: center;margin-top: 20rem">
                    <div class="left_label">
                       <span>
                           <span style="color: indianred;font-size: 20rem">*</span>
                           邮费：
                       </span>
                    </div>
                    <div class="right_input">
                        <input :onblur="checkFreightFunction" type="text" v-model="shop.freight" :onkeyup="()=>{shop.freight=shop.freight.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')}" style="width: 200rem;height: 20rem;border: 1rem grey solid;border-radius: 2rem">
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">

                    </div>
                    <div class="right_input">
                        <span>
                            {{checkFreight}}
                        </span>
                    </div>
                </div>

                <div style="width: 400rem;height: 40rem;display: flex;justify-content: space-between;align-items: center;margin-top: 20rem">
                    <div class="left_label">
                       <span>
                           <span style="color: indianred;font-size: 20rem">*</span>
                           包邮下限：
                       </span>
                    </div>
                    <div class="right_input">
                        <input :onblur="checkFreeDeliveryFunction" type="text" v-model="shop.freeDelivery" :onkeyup="()=>{shop.freeDelivery=shop.freeDelivery.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')}" style="width: 200rem;height: 20rem;border: 1rem grey solid;border-radius: 2rem">
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">

                    </div>
                    <div class="right_input">
                        <span>
                            {{checkFreeDelivery}}
                        </span>
                    </div>
                </div>

                <div style="width: 400rem;height: 50rem;display: flex;justify-content: space-between;align-items: center;margin-top: 20rem;z-index: 2">
                    <div class="left_label">
                       <span>
                           <span style="color: #cd5c5c;font-size: 20rem">*</span>
                           经营地址：
                       </span>
                    </div>
                    <div class="right_input">
                        <div  style="width: auto;display: flex;height:auto;justify-content: space-evenly;align-items: flex-start">
                            <select name="province" style="width: 120rem;height: auto;margin-top: 5rem;margin-right: 20rem"  v-model="province" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                                <option :onblur="checkShopLocationFunction" value="" selected>请选择省/直辖市</option>
                                <option :onblur="checkShopLocationFunction" v-for="(item,index) in provinces.list" :key="item" :value="index">{{item}}</option>
                            </select>
                            <select  name="city" style="width: 80rem;height: auto;margin-top: 5rem;margin-right: 20rem"  v-model="city" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="citySelectorShow">
                                <option :onblur="checkShopLocationFunction" value="" selected>请选择市</option>
                                <option :onblur="checkShopLocationFunction" v-for="(item,index) in cities.list" :key="item" :value="index">{{item.name}}</option>
                            </select>
                            <select  name="country" style="width: 100rem;height: auto;margin-top: 5rem"  v-model="country" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="countrySelector">
                                <option :onblur="checkShopLocationFunction" value="" selected>请选择区/县</option>
                                <option :onblur="checkShopLocationFunction" v-for="(item,index) in countries.list" :key="index" :value="index">{{item.name}}</option>
                            </select>

                            <select  name="country" style="width: 100rem;height: auto;margin-top: 5rem;margin-left: 20rem"  v-model="street" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="streetSelector">
                                <option :onblur="checkShopLocationFunction" value="" selected>请选择街道</option>
                                <option :onblur="checkShopLocationFunction" v-for="item in streets.list" :key="item" :value="item.name">{{item.name}}</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">

                    </div>
                    <div class="right_input">
                        <span>
                            {{checkShopLocation}}
                        </span>
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          详细地址：
                       </span>
                    </div>
                    <div class="right_input">
                        <textarea :onblur="checkShopFullAddressFunction" v-model="fullAddress" style="width: 500rem;height: 20rem;border: 1rem grey solid;border-radius: 2rem"></textarea>
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">

                    </div>
                    <div class="right_input">
                        <span>
                            {{checkShopFullAddress}}
                        </span>
                    </div>
                </div>

                <div style="width: 400rem;height: auto;display: flex;justify-content: space-between;align-items: center;margin-top: 20rem;padding-top: 10rem;padding-bottom: 10rem;z-index: 1">
                    <div class="left_label">
                       <span>
                           <span style="color: indianred;font-size: 20rem">*</span>
                           店铺描述：
                       </span>
                    </div>
                    <div class="right_input">
                        <textarea :onblur="checkShopDescriptionFunction" placeholder="店铺简介会在店铺索引中展现，请认真填写！" v-model="shop.shopDescription" style="width: 300rem;height: 200rem;border: 1rem grey solid;border-radius: 2rem"></textarea>
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-between;align-items: center;margin-top: 10rem">
                    <div class="left_label">

                    </div>
                    <div class="right_input">
                        <span>
                            {{checkShopDescription}}
                        </span>
                    </div>
                </div>

                <div style="width: 400rem;height:auto;display: flex;justify-content: space-evenly;align-items: center;margin-top: 10rem">
                    <div class="left_label">
                    </div>
                    <div class="right_input">
                        <button @click="saveShopInf" style="background: darkgrey;">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import {ref,reactive,watch} from "vue";
  import axios from "axios";
  // import getProvinces from "../../Internet/home/local/getProvinces";
  // import getCities from "../../Internet/home/local/getCities";
  // import getCountries from "../../Internet/home/local/getCountries";
  import updateShopInf from "../../Internet/home/shop/update/updateShopInf";
  import getLocals from "../../assets/functions/getLocals";

  export default {
    name: "modifyShopInf",
    setup(){
      let shopImage=ref('')

      let shop=reactive(JSON.parse(sessionStorage.getItem('shop')))

      if(shop.shopLogo!==null){
        shopImage.value=require('../../assets/img/'+shop.shopLogo);
      }

      function onchangeImgFun (e) {
        let file = e.target.files[0]

        event.preventDefault()

        let formData = new FormData();

        formData.append('image',file);//键名要和后台一致

        formData.append('shopId',shop.shopId);


        const instance=axios.create({
          withCredentials: true
        })
        instance.post('/servlet/shop/updateShopLogo',formData).then(res=>{
          if(res.data.code==0){
            shop.shopLogo=res.data.data
            sessionStorage.setItem('shop',JSON.stringify(shop))
          }
          // sessionStorage.setItem('user',JSON.stringify(res.data.user))
        })

        // base64方法 2
        let reader = new FileReader()
        reader.readAsDataURL(file) // 读出 base64
        reader.onloadend = function () {
          // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
          let dataURL = reader.result
          shopImage.value = dataURL
          // 下面逻辑处理
        }
      }

      let provinces=reactive({
        list:[]
      });

      let cities=reactive({
        list:[]
      });

      let countries=reactive({
        list:[]
      });

      let streets=reactive({
        list:[]
      })

      let locals=[]

      let province=ref('')
      let city=ref('')
      let country=ref('')
      let street=ref('')
      let freight=ref('')
      let freeDelivery=ref('')

      let citySelectorShow=ref(false);
      let countrySelector=ref(false);
      let streetSelector=ref(false);

      let fullAddress=ref('')

      let checkShopName=ref('')
      let checkShopLogo=ref('')
      let checkShopLocation=ref('')
      let checkShopFullAddress=ref('')
      let checkShopDescription=ref('')
      let checkFreight=ref('')
      let checkFreeDelivery=ref('')

      //获取所有省
      watch(province, (province, prevProvince) => {
        console.log(province);
        if(province!==prevProvince&&province!==''){
          //获取该省所有市
          city.value=''
          country.value=''
          street.value=''

          citySelectorShow.value=false
          countrySelector.value=false
          streetSelector.value=false

          new Promise((resolve)=>{
            resolve(
                cities.list=locals[province].children,
                citySelectorShow.value=true,
                countrySelector.value=false,
                // console.log(cities.list)
            )
          })
        }else if(province==''){
          citySelectorShow.value=false
          countrySelector.value=false
        }
      })

      watch(city, (city, prevCity) =>{


        console.log(city);
        country.value=''
        street.value=''

        countrySelector.value=false
        streetSelector.value=false

        if(city!==prevCity&&city!==''){
          //获取该市所有区
          new Promise((resolve)=>{
            resolve(
                // console.log(city),
                countries.list=cities.list[city].children,
                // console.log(countries.list),
                countrySelector.value=true
            )
          })
        }else if(city==''){
          countrySelector.value=false
        }
      })

      watch(country, (country, prevCountry) => {
        // console.log(country);
        // console.log(prevCountry);


        street.value=''

        streetSelector.value=false

        if(country!==prevCountry&&country!==''){
          //获取所有街道
          new Promise((resolve)=>{
            resolve(
                streets.list=countries.list[country].children,
                // console.log(streets.list[0]),
                streetSelector.value=true
            )
          })
        }else {
          streetSelector.value=false
        }
      })

      let strs;
      if(shop.shopAddress!==null){
        strs=shop.shopAddress.split(' ')
        fullAddress.value=strs[strs.length-1]
      }

      new Promise((resolve)=>{
        resolve(
            getLocals().then(res=>{
              locals=res.data.data

              for(let i=0;i<locals.length;i++){
                provinces.list[i]=locals[i].name
              }
            })
        )
      }).then(()=>{
        if(shop.shopAddress!==null) {
          for (let i = 0; i < locals.length; i++) {

            if (strs[0] == locals[i].name) {
              province.value = i
              break;
            }
          }
        }

      }).then(()=>{
        if(shop.shopAddress!==null) {
          for (let j = 0; j < locals[province.value].children.length; j++) {
            if (strs[1] == locals[province.value].children[j].name) {
              city.value = j
              break
            }
          }
        }
      }).then(()=>{
        if(shop.shopAddress!==null) {
          for (let i = 0; i < locals[province.value].children[city.value].children.length; i++) {
            if (strs[2] == locals[province.value].children[city.value].children[i].name) {
              country.value = i
              break
            }
          }
        }
      }).then(()=>{
        if(shop.shopAddress!==null) {
          for (let i = 0; i < locals[province.value].children[city.value].children[country.value].children.length; i++) {
            if (strs[3] == locals[province.value].children[city.value].children[country.value].children[i].name) {
              street.value = locals[province.value].children[city.value].children[country.value].children[i].name
              break
            }
          }
        }
      })



      let flag=true

      function checkShopNameFunction(){
        if(shop.shopName==''){
          checkShopName.value='请输入店铺名'
          flag=false
        }else {
          checkShopName.value=''
        }
      }

      function checkFreeDeliveryFunction() {
        if(shop.freeDelivery==''){
          checkFreeDelivery.value='请输入包邮下限'
          flag=false
        }else {
          checkFreeDelivery.value=''
        }
      }
      
      // function checkFreight() {
      //
      // }

      function checkShopLogoFunction() {
        if(shop.shopLogo==''){
          checkShopLogo.value='请上传logo图片'
          flag=false
        }else {
          checkShopLogo.value=''
        }
      }
      
      function checkFreightFunction() {
        if(shop.freight==''){
          checkFreight.value='请输入邮费'
          flag=false
        }else {
          checkFreight.value=''
        }
      }


      function checkShopLocationFunction(){
        if(province.value==''){
          checkShopLocation.value='请选择省/区'
          flag=false
        }else if(city.value==''){
          checkShopLocation.value='请选择市'
          flag=false
        }else if(countrySelector.value==true&&country.value==''){
          checkShopLocation.value='请选择区/县'
          flag=false
        }else if(street.value==''){
          checkShopLocation.value='请选择街道'
        } else {
          checkShopLocation.value=''
        }
      }

      function checkShopFullAddressFunction() {
        if(fullAddress.value==''){
          checkShopFullAddress.value='请输入详细地址'
          flag=false
        }else {
          checkShopFullAddress.value=''
        }
      }

      function checkShopDescriptionFunction() {
        console.log(shop.shopDescription);
        if(shop.shopDescription==''||shop.shopDescription==null){
          checkShopDescription.value='请输入店铺简介'
          flag=false
        }else {
          checkShopDescription.value=''
        }
      }

      function saveShopInf() {
        flag=true
        new Promise((resolve)=>{
          resolve(
          checkShopNameFunction(),
          checkShopLogoFunction(),
          checkShopLocationFunction(),
          checkShopFullAddressFunction(),
          checkShopDescriptionFunction(),
          checkFreeDeliveryFunction(),
          checkFreightFunction()
          )
        }).then(()=>{
          if(flag==true){

            console.log(cities.list[city.value]);
            shop.shopAddress=provinces.list[province.value]+' ' + cities.list[city.value].name + ' ' + countries.list[country.value].name + ' ' + street.value + ' ' +fullAddress.value
            updateShopInf(shop).then(res=>{
              sessionStorage.setItem("shop",JSON.stringify(res.data))
              if(res.code==0){
                alert("保存成功")
              }
            })
          }
        })


      }

      return {shopImage,onchangeImgFun,shop,provinces,province,citySelectorShow,freight,freeDelivery,checkFreight,checkFreeDelivery,cities,countries,countrySelector,city,country,fullAddress,checkShopDescription,checkShopFullAddress,checkShopLocation,checkShopLogo,checkShopName,saveShopInf,checkShopLocationFunction,checkShopFullAddressFunction,checkShopLogoFunction,checkShopNameFunction,checkShopDescriptionFunction,checkFreightFunction,checkFreeDeliveryFunction,streetSelector,street,streets}
    }
  }
</script>

<style scoped>
    .left_label{
        width: 100rem;
        height: 100%;
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }
    .right_input{
        width: 300rem;
        height: 100%;


        display: flex;
          /*设置为弹性布局*/
        /*justify-content: center;//x轴线上居中*/
        /*align-items: center;//y轴上居中*/

        justify-content: space-between;
        align-items: flex-start;
    }

    .upload-btn{
        position: absolute;
        opacity: 0;
        width: 90rem;
        height: 90rem;
        cursor: pointer;
        /* 通过定位把input放在img标签上面，通过不透明度隐藏 */
    }
</style>