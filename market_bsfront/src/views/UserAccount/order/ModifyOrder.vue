<template>
     <div style="width: 850rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;font-size: 12rem">
           <div ref="inf" style="width: 100%;height:auto;display:flex;flex-direction: column;justify-content: flex-start;align-items: flex-start">
                 <div style="border-left: 2rem rgb(241,1,128) solid;width: auto;padding-left:20rem;height:20rem;align-items:center;display: flex;justify-content: space-evenly;font-weight: 700;font-size: 14rem;">
                       <span>修改订单</span>
                 </div>
           </div>
           <div style="width: 100%;height:auto;border: 1px solid #ececec;background-color: #fff;box-shadow: 0 1px 4px rgb(0 0 0 / 10%);margin-top: 10rem;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;padding-bottom: 20rem">
                 <div style="width: 100%;height:41rem;background-color: #f9f9f9;font: 14rem/24rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;padding-left: 20rem; line-height: 40rem;border-bottom: 1px solid #e0e0e0;display: flex;justify-content: flex-start;align-items: center;font-weight: bold">
                       收货方式
                 </div>
                 <div style="width: 100%;height: auto;padding: 20rem;display: flex;justify-content: flex-start;align-items: flex-start">
                       <div style="width: 100%;height: 53.3rem;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start">
                             <div style="font: 14rem/24rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;">
                                   地址信息：
                             </div>
                             <div style="width: 100%;font: 12rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;height:auto;display: flex;line-height: 40rem;justify-content: space-between;align-items:center;flex-wrap: wrap">
                                   <div style="width: 600rem;font: 12rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;height:auto;display: flex;line-height: 40rem;justify-content: flex-start;align-items:flex-start;flex-wrap: wrap">
                                         <span style="width: 30rem; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;text-align: left;">{{harvestAddress1.data.harvestName}}</span>
                                         <span style="margin-left: 20rem">{{harvestAddress1.data.harvestEmail}}</span>
                                         <span style="width: 400rem; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;text-align: left;margin-left: 20rem">{{harvestAddress1.data.harvestAddress}}</span>
                                   </div>
                                   <div @click="()=>{showFlag=!showFlag}" style="cursor: pointer;font: 12rem tahoma,arial,Hiragino Sans GB,WenQuanYi Micro Hei,\5FAE\8F6F\96C5\9ED1,\5B8B\4F53,sans-serif;height:auto;height: auto;width: auto;display: flex;justify-content: space-between;align-items: center">
                                         切换地址&nbsp;
                                         <up-outlined v-if="showFlag"></up-outlined>
                                         <down-outlined v-else></down-outlined>
                                   </div>
                             </div>
                       </div>
                 </div>
                 <div v-if="showFlag" style="width:100%;height: auto;display: flex;justify-content:flex-start;align-items: flex-start;flex-wrap: wrap;">

                       <div style="width: 33%;height: auto;display: flex;justify-content: center;align-items: center" v-for="(item,index) in harvestAddressList.list" :key="index">
                             <div v-if="item.harvestId==harvestAddress1.data.harvestId" @click="selectAddress(harvestAddress1.data,index)" class="imageBackground" style="cursor:pointer;opacity: 1;width: 250rem;height: 150rem;padding-top: 6rem;padding-bottom: 6rem;padding-left:15rem;padding-right: 15rem;margin-top: 20rem">
                                   <div style="width: 100%;height: 45rem;display: flex;justify-content: space-between;align-items: center;border-bottom: 1rem dashed #ececec;">
                                         <span style="font-size: 14rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">{{harvestAddress1.data.harvestName}}</span>
                                         <div style="width: 35%;height: auto;display: flex;justify-content:space-between;align-items: center">
                                               <span style="font-size: 12rem;color:steelblue;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor: pointer" @click="toUpdateAddress(harvestAddress1.data)">修改</span>
                                               <span style="font-size: 12rem;color:#a0a0a0;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'" v-if="harvestAddress1.data.isDefault==1">默认地址</span>
                                               <span style="font-size: 12rem;color:steelblue;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor: pointer" v-else @click="deleteAddress(harvestAddress1.data)">删除</span>
                                         </div>
                                   </div>
                                   <div style="width: 100%;height: 30.46rem;display: flex;justify-content: space-between;align-items: center;font-size: 12rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">
                                         {{changeEmailForm(harvestAddress1.data.harvestEmail)}}
                                   </div>
                                   <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items: flex-start;margin-top: 10rem">
                                         <img :src="locationLogo" style="max-width: 13rem">
                                         <div style="width: 248rem;height: auto;display: flex;justify-content: flex-start;flex-wrap: wrap;text-align: left;font-size: 12rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">
                                               {{harvestAddress1.data.harvestAddress}}
                                         </div>
                                   </div>
                             </div>
                             <div v-else @click="selectAddress(item,index)" class="imageBackground"  style="cursor: pointer; width: 250rem; width: 250rem;height: 150rem;padding-top: 6rem;padding-bottom: 6rem;padding-left:15rem;padding-right: 15rem;margin-top: 20rem">
                                   <div style="width: 100%;height: 45rem;display: flex;justify-content: space-between;align-items: center;border-bottom: 1rem dashed #ececec;">
                                         <span style="font-size: 14rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">{{item.harvestName}}</span>
                                         <div style="width: 35%;height: auto;display: flex;justify-content:space-between;align-items: center">
                                               <span style="font-size: 12rem;color:steelblue;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor:pointer;cursor: pointer" @click="toUpdateAddress(item)">修改</span>
                                               <span style="font-size: 12rem;color:#a0a0a0;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'" v-if="item.isDefault==1">默认地址</span>
                                               <span style="font-size: 12rem;color:steelblue;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor: pointer" v-else @click="deleteAddress(item)">删除</span>
                                         </div>
                                   </div>
                                   <div style="width: 100%;height: 30.46rem;display: flex;justify-content: space-between;align-items: center;font-size: 12rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">
                                         {{changeEmailForm(item.harvestEmail)}}
                                   </div>
                                   <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items: flex-start;margin-top: 10rem">
                                         <img :src="locationLogo" style="max-width: 13rem">
                                         <div style="width: 248rem;height: auto;display: flex;justify-content: flex-start;flex-wrap: wrap;text-align: left;font-size: 12rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">
                                               {{item.harvestAddress}}
                                         </div>
                                   </div>
                             </div>
                       </div>
                       <div  style="width: 33%;height: auto;display: flex;justify-content: center;align-items: center">
                             <div  style="width: 250rem;height:150rem;padding-top: 6rem;padding-bottom: 6rem;padding-left:15rem;padding-right: 15rem;margin-top: 20rem;border: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;background-color:rgb(249, 249, 249)">
                                   <div @click="toAddAddress" style="cursor: pointer;width: 200rem;height: 62rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                                         <span style="font-size: 28rem;color:rgb(176, 176, 176)"><plus-outlined></plus-outlined></span>
                                         <span style="font-size: 14rem;color:rgb(176, 176, 176)">添加地址</span>
                                   </div>
                             </div>
                       </div>
                 </div>
           </div>
           <div class="dialogForAddress" style="position: fixed;display: flex;top:10rem;height: 500rem;width: 800rem;background-color:rgba(200,200,200,0.7);padding: 5rem;visibility: hidden;z-index: 4">
                 <div v-if="addFlag" style="width: 100%;height: 100%;background: white;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
                       <div style="width: 100%;height: 35rem;display: flex;align-items: flex-start;justify-content: center;border-bottom: 1rem #eff0f4 solid;">
                             <div style="width: 95%;height: 35rem;display: flex;justify-content: space-between;align-items: center">
                                   <span style="font-size: 15rem;font-weight: bold">收货信息</span>
                                   <close-outlined style="font-size: 20rem;color:grey;cursor: pointer" @click="hideDialog"></close-outlined>
                             </div>
                       </div>

                       <div style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start">
                             <div style="width: auto;height: 30rem;margin-top:10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>收货人：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem">
                                         <input :onblur="checkHarvestName" v-model="harvestAddress.harvestName" type="text" style="width: 200rem;height: 25rem;border: 1rem lightgrey solid;outline: none;border-radius: 5rem">
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestNameCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 30rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>QQ邮箱：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;;margin-left: 20rem;">
                                         <input  :onblur="checkHarvestEmail" v-model="harvestAddress.harvestEmail" placeholder="邮箱号" type="text" style="width: 200rem;height: 25rem;border: 1rem lightgrey solid;outline: none;border-radius: 5rem">
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestEmailCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 30rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>所在地区：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items:flex-start;z-index: 2;margin-left: 20rem">
                                         <select name="province" style="width: 120rem;height:auto;margin-top: 5rem;margin-right: 20rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="province" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择省/直辖市</option>
                                               <option :onblur="checkLocationFunction" v-for="(item,index) in provinces.list" :key="item" :value="index">{{item}}</option>
                                         </select>
                                         <select  name="city" style="width: 80rem;height: auto;margin-top: 5rem;margin-right: 20rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="city" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="citySelectorShow">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择市</option>
                                               <option :onblur="checkLocationFunction" v-for="(item,index) in cities.list" :key="item" :value="index">{{item.name}}</option>
                                         </select>
                                         <select  name="country" style="width: 100rem;height: auto;margin-top: 5rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="country" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="countrySelector">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择区/县</option>
                                               <option :onblur="checkLocationFunction" v-for="(item,index) in countries.list" :key="index" :value="index">{{item.name}}</option>
                                         </select>

                                         <select  name="country" style="width: 100rem;height: auto;margin-top: 5rem;margin-left: 20rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="street" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="streetSelector">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择街道</option>
                                               <option :onblur="checkLocationFunction" v-for="item in streets.list" :key="item" :value="item.name">{{item.name}}</option>
                                         </select>
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.localsCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 30rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>详细地址：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem">
                                         <input :onblur="checkHarvestAddress" v-model="harvestAddress.harvestAddress" type="text" style="width: 300rem;height: 25rem;border: 1rem lightgrey solid;outline: none;border-radius: 5rem">
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestAddressCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 150rem;margin-top: 10rem;display: flex;justify-content: flex-start;align-items: flex-start;">
                                   <div style="width: 120rem;height:30rem;display: flex;justify-content: flex-end;align-items:center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>地址类型：</span>
                                   </div>
                                   <div style="width: 300rem;height: 100%;display: flex;flex-direction: column;justify-content:space-between;align-items: flex-start;margin-left: 20rem">
                                         <div :onblur="checkHarvestType" style="width: 300rem;height: 30rem;display: flex;justify-content:space-between;align-items: center">
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="家庭" type="radio" v-model="harvestAddress.harvestType"> <span>家庭</span></span>
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="公司" type="radio" v-model="harvestAddress.harvestType"> <span>公司</span></span>
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="学校" type="radio" v-model="harvestAddress.harvestType"> <span>学校</span></span>
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="其他" type="radio" v-model="harvestAddress.harvestType"> <span>其他</span></span>
                                         </div>

                                         <div style="width: 300rem;height: 80rem;display: flex;flex-direction: column;justify-content:space-between;align-items: flex-start">
                                    <span style="width: 100rem;height: auto;display: flex;justify-content: space-between">
                                        <input type="checkbox" value="1" v-model="harvestAddress.isDefault"> <span>设为默认地址</span>
                                    </span>

                                               <button @click="save" style="width: 80rem;height: 30rem;border: none;background:rgb(241,1,128);border-radius: 5rem;color: white;margin-top: 20rem">保存</button>
                                         </div>

                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start;margin-bottom: 20rem">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestTypeCheck}}</span>
                                   </div>
                             </div>
                       </div>
                 </div>

                 <div v-else style="width: 100%;height: 100%;background: white;display: flex;justify-content: flex-start;align-items: flex-start;flex-wrap: wrap">
                       <div style="width: 100%;height: 35rem;display: flex;align-items: flex-start;justify-content: center;border-bottom: 1rem #eff0f4 solid;">
                             <div style="width: 95%;height: 35rem;display: flex;justify-content: space-between;align-items: center">
                                   <span style="font-size: 15rem;font-weight: bold">收货信息</span>
                                   <close-outlined style="font-size: 20rem;color:grey;cursor: pointer"  @click="hideDialog"></close-outlined>
                             </div>
                       </div>

                       <div style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start">
                             <div style="width: auto;height: 30rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>收货人：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem">
                                         <input :onblur="checkHarvestName" v-model="harvestAddress.harvestName" type="text" style="width: 200rem;height: 25rem;border: 1rem lightgrey solid;outline: none;border-radius: 5rem">
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestNameCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 30rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>QQ邮箱：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;;margin-left: 20rem;">
                                         <input  :onblur="checkHarvestEmail" v-model="harvestAddress.harvestEmail" placeholder="邮箱号" type="text" style="width: 200rem;height: 25rem;border: 1rem lightgrey solid;outline: none;border-radius: 5rem">
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestEmailCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 30rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>所在地区：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items:flex-start;z-index: 2;margin-left: 20rem">
                                         <select name="province" style="width: 120rem;height:auto;margin-top: 5rem;margin-right: 20rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="province" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择省/直辖市</option>
                                               <option :onblur="checkLocationFunction" v-for="(item,index) in provinces.list" :key="item" :value="index">{{item}}</option>
                                         </select>
                                         <select  name="city" style="width: 80rem;height: auto;margin-top: 5rem;margin-right: 20rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="city" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="citySelectorShow">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择市</option>
                                               <option :onblur="checkLocationFunction" v-for="(item,index) in cities.list" :key="item" :value="index">{{item.name}}</option>
                                         </select>
                                         <select  name="country" style="width: 100rem;height: auto;margin-top: 5rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="country" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="countrySelector">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择区/县</option>
                                               <option :onblur="checkLocationFunction" v-for="(item,index) in countries.list" :key="index" :value="index">{{item.name}}</option>
                                         </select>

                                         <select  name="country" style="width: 100rem;height: auto;margin-top: 5rem;margin-left: 20rem;background: white;border: 1rem lightgrey solid;border-radius: 5rem"  v-model="street" onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" v-if="streetSelector">
                                               <option :onblur="checkLocationFunction" value="" selected>请选择街道</option>
                                               <option :onblur="checkLocationFunction" v-for="item in streets.list" :key="item" :value="item.name">{{item.name}}</option>
                                         </select>
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.localsCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 30rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>详细地址：</span>
                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem">
                                         <input :onblur="checkHarvestAddress" v-model="harvestAddress.harvestAddress" type="text" style="width: 300rem;height: 25rem;border: 1rem lightgrey solid;outline: none;border-radius: 5rem">
                                   </div>
                             </div>

                             <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestAddressCheck}}</span>
                                   </div>
                             </div>

                             <div style="width: auto;height: 150rem;margin-top: 10rem;display: flex;justify-content: flex-start;align-items: flex-start;">
                                   <div style="width: 120rem;height:30rem;display: flex;justify-content: flex-end;align-items:center">
                                         <span style="color:rgb(241,1,128);width: 10rem;text-align: left">*</span>
                                         <span>地址类型：</span>
                                   </div>
                                   <div style="width: 300rem;height: 100%;display: flex;flex-direction: column;justify-content:space-between;align-items: flex-start;margin-left: 20rem">
                                         <div :onblur="checkHarvestType" style="width: 300rem;height: 30rem;display: flex;justify-content:space-between;align-items: center">
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="家庭" type="radio" v-model="harvestAddress.harvestType"> <span>家庭</span></span>
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="公司" type="radio" v-model="harvestAddress.harvestType"> <span>公司</span></span>
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="学校" type="radio" v-model="harvestAddress.harvestType"> <span>学校</span></span>
                                               <span style="width: 50rem;height: auto;display: flex;justify-content: space-between"><input value="其他" type="radio" v-model="harvestAddress.harvestType"> <span>其他</span></span>
                                         </div>

                                         <div style="width: 300rem;height: 80rem;display: flex;flex-direction: column;justify-content:space-between;align-items: flex-start">
                                    <span style="width: 100rem;height: auto;display: flex;justify-content: space-between">
                                        <input type="checkbox" value="1" v-model="harvestAddress.isDefault"> <span>设为默认地址</span>
                                    </span>

                                               <button @click="modifyAddress" style="width: 80rem;height: 30rem;border: none;background:rgb(241,1,128);border-radius: 5rem;color: white;margin-top: 20rem">保存</button>
                                         </div>

                                   </div>
                             </div>

                             <div style="width: auto;height: 10rem;margin-top: 10rem;display: flex;justify-content: flex-start;margin-bottom: 20rem">
                                   <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                                   </div>
                                   <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                         <span>{{harvestAddressCheck.harvestTypeCheck}}</span>
                                   </div>
                             </div>
                       </div>
                 </div>
           </div>
     </div>
</template>

<script>
      import {reactive, ref} from "@vue/reactivity";
      import {watch} from 'vue'
      import getHarvestAddressById from "../../../Internet/home/address/select/getHarvestAddressById";
      import {UpOutlined,DownOutlined} from '@ant-design/icons-vue'
      import getHarvestAddressList from "../../../Internet/home/address/select/getHarvestAddressList";
      import getLocals from "../../../assets/functions/getLocals";
      import addAddress from "../../../Internet/home/address/add/addAddress";
      import updateAddress from "../../../Internet/home/address/update/updateAddress";
      import removeAddress from "../../../Internet/home/address/remove/removeAddress";
      import setDefaultAddress from "../../../Internet/home/address/update/setDefaultAddress";
      import $ from 'jquery'
      import modifyOrder from "../../../Internet/home/shoppingOrder/update/modifyOrder";
      import {onMounted} from "@vue/runtime-core";

  export default {
        name: "ModifyOrder",
        components:{
              UpOutlined,
              DownOutlined
        },
        setup(){
              let inf=ref(null)

              onMounted(()=>{
                    console.log(inf.value)
              })

              let harvestAddress1=reactive({
                    data:{}
              })

              let harvestAddress=reactive({
                    harvestId:'',
                    userId:JSON.parse(sessionStorage.getItem('user')).userId,
                    harvestAddress:'',
                    harvestType:'',
                    harvestEmail:'',
                    isDefault:0,
                    harvestName:'',
              })

              let harvestAddressList=reactive({
                list:[]
              })

              let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
              let reg1 = new RegExp("(^|&)" + 'harvestId' + "=([^&]*)(&|$)");
              let r1 = search.match(reg1);



              getHarvestAddressById(unescape(r1[2])).then((res)=>{
                    harvestAddress1.data=res.data
              })

              let reg2 = new RegExp("(^|&)" + 'userId' + "=([^&]*)(&|$)");
              let r2 = search.match(reg2);
              getHarvestAddressList(unescape(r2[2])).then(res=>{
                    harvestAddressList.list=res.data
              })

              let showFlag=ref(false)

              let harvestAddressCheck=reactive({
                    localsCheck:'',
                    harvestAddressCheck:'',
                    harvestTypeCheck:'',
                    harvestEmailCheck:'',
                    harvestNameCheck:''
              })

              let province=ref('')
              let city=ref('')
              let country=ref('')
              let street=ref('')

              let citySelectorShow=ref(false);
              let countrySelector=ref(false);
              let streetSelector=ref(false);


              let addFlag=ref(true)

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

              new Promise((resolve)=>{
                    resolve(
                            getLocals().then(res=>{
                                  locals=res.data.data

                                  for(let i=0;i<locals.length;i++){
                                        provinces.list[i]=locals[i].name
                                  }
                            })
                    )
              })

              function showInf(index){
                    $(".setDefaultAddressBtn").eq(index).css('visibility','visible')
                    $(".changeBtn").eq(index).css('visibility','visible')
                    $(".deleteBtn").eq(index).css('visibility','visible')

                    $(".setDefaultAddressBtn").eq(index).slideDown(200)
                    $(".changeBtn").eq(index).fadeIn(200)
                    $(".deleteBtn").eq(index).fadeIn(200)
              }
              let flag=false

              function checkLocationFunction(){
                    if(province.value==''){
                          harvestAddressCheck.localsCheck='请选择省/区'
                          flag=false
                    }else if(city.value==''){
                          harvestAddressCheck.localsCheck='请选择市'
                          flag=false
                    }else if(country.value==''){
                          harvestAddressCheck.localsCheck='请选择区/县'
                          flag=false
                    }else if(street.value==''){
                          harvestAddressCheck.localsCheck='请选择街道'
                          flag=false
                    } else {
                          harvestAddressCheck.localsCheck=''
                    }
              }

              function checkHarvestName(){
                    if(harvestAddress.harvestName==''){
                          harvestAddressCheck.harvestNameCheck='请输入收货人姓名'
                          flag=false
                    }else {
                          harvestAddressCheck.harvestNameCheck=''
                    }
              }

              function checkHarvestEmail(){
                    let regex7 = /^\d{5,12}@[qQ][qQ]\.(com|cn)$/;
                    if(harvestAddress.harvestEmail==''){
                          harvestAddressCheck.harvestEmailCheck= '请输邮箱号'
                          flag=false
                    }else if(!(harvestAddress.harvestEmail.match(regex7))) {
                          harvestAddressCheck.harvestEmailCheck= '请输入正确的邮箱'
                          flag=false
                    }else {
                          harvestAddressCheck.harvestEmailCheck=''
                    }
              }

              function checkHarvestAddress(){
                    if(harvestAddress.harvestAddress==''){
                          harvestAddressCheck.harvestAddressCheck='请输入详细地址'
                          flag=false
                    }else {
                          harvestAddressCheck.harvestAddressCheck=''
                    }
              }

              function checkHarvestType(){
                    if(harvestAddress.harvestType==''){
                          harvestAddressCheck.harvestTypeCheck='请选择地址类型'
                          flag=false
                    }else {
                          harvestAddressCheck.harvestTypeCheck=''
                    }
              }

              let defaultAddress=reactive({
                    harvestAddress: '',
                    harvestEmail: '',
                    harvestId: null,
                    harvestName: '',
                    harvestType: '',
                    isDefault: 0,
                    userId: '',
              })


              function setAddressList(){
                    if(JSON.parse(sessionStorage.getItem('user')).userId!=null) {
                          getHarvestAddressList(JSON.parse(sessionStorage.getItem('user')).userId).then(res => {
                                setAddress(res)
                          })
                    }
              }


              function setAddress(res){
                    let j=0;
                    harvestAddressList.list=[]
                    for(let i=0;i<res.data.length;i++) {
                                harvestAddressList.list[j++] = res.data[i]
                    }
              }

              setAddressList()

              function changeEmailForm(item){
                    let result=/^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
                    return (String(item)).replace(result, "$1****$3$4");
              }

              watch(province, (province, prevProvince) => {
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

              watch(city, (city, prevCity) => {


                    country.value=''
                    street.value=''

                    countrySelector.value=false
                    streetSelector.value=false

                    if(city!==prevCity&&city!==''){
                          //获取该市所有区
                          new Promise((resolve)=>{
                                resolve(
                                        countries.list=cities.list[city].children,
                                        countrySelector.value=true
                                )
                          })
                    }else if(city==''){
                          countrySelector.value=false
                    }
              })

              watch(country, (country, prevCountry) => {
                    street.value=''

                    streetSelector.value=false

                    if(country!==prevCountry&&country!==''){
                          //获取所有街道
                          new Promise((resolve)=>{
                                resolve(
                                        streets.list=countries.list[country].children,
                                        streetSelector.value=true
                                )
                          })
                    }else {
                          streetSelector.value=false
                    }
              })

              function hideInf(index){

                    $(".setDefaultAddressBtn").eq(index).slideUp(200)
                    $(".changeBtn").eq(index).fadeOut()
                    $(".deleteBtn").eq(index).fadeOut(200)
              }

              function save() {
                    if(harvestAddressList.list.length<9){
                          flag=true

                          checkHarvestType()
                          checkHarvestAddress()
                          checkLocationFunction()
                          checkHarvestEmail()
                          checkHarvestName()

                          if(harvestAddress.isDefault==true){
                                harvestAddress.isDefault=1
                          }else {
                                harvestAddress.isDefault=0
                          }

                          if(flag==true) {
                                harvestAddress.harvestAddress = provinces.list[province.value] + ' ' + cities.list[city.value].name + ' ' + countries.list[country.value].name + ' ' + street.value + ' ' + harvestAddress.harvestAddress

                                addAddress(harvestAddress).then(res => {
                                      if (res.code == 0) {
                                            alert("地址保存成功")
                                            harvestAddress.harvestAddress = ''
                                            harvestAddress.isDefault = 0
                                            harvestAddress.harvestType = ''
                                            harvestAddress.harvestEmail = ''
                                            harvestAddress.harvestName = ''

                                            province.value = ''
                                            city.value = ''
                                            country.value = ''
                                            street.value = ''

                                            citySelectorShow.value = false
                                            countrySelector.value = false
                                            streetSelector.value = false

                                            setAddress(res)
                                            window.scrollTo(0,0)
                                      }
                                })
                          }
                    }else {
                          alert('已添加十条地址，不能再添加')
                    }
              }


              function modifyAddress(){
                    checkHarvestType()
                    checkHarvestAddress()
                    checkLocationFunction()
                    checkHarvestEmail()
                    checkHarvestName()

                    if(harvestAddress.isDefault==true){
                          harvestAddress.isDefault=1
                    }else {
                          harvestAddress.isDefault=0
                    }

                    harvestAddress.harvestAddress = provinces.list[province.value] + ' ' + cities.list[city.value].name + ' ' + countries.list[country.value].name + ' ' + street.value + ' ' + harvestAddress.harvestAddress

                    updateAddress(harvestAddress).then(res => {
                          if (res.code == 0) {
                                alert("地址保存成功")
                                harvestAddress.harvestAddress = ''
                                harvestAddress.isDefault = 0
                                harvestAddress.harvestType = ''
                                harvestAddress.harvestEmail = ''
                                harvestAddress.harvestName = ''

                                province.value = ''
                                city.value = ''
                                country.value = ''
                                street.value = ''

                                citySelectorShow.value = false
                                countrySelector.value = false
                                streetSelector.value = false

                                setAddress(res)

                                window.scrollTo(0,0)

                                addFlag.value=true
                          }
                    })
              }

              let isOnFunction=false

              function deleteAddress(item){
                    isOnFunction=true
                    let flag=confirm('确定要删除该地址吗？')
                    if(flag==true){
                          removeAddress(item).then(res=>{
                                setAddress(res)
                          })
                    }
              }

              function setNewDefaultAddress(item) {
                    isOnFunction=true
                    setDefaultAddress(Number(defaultAddress.harvestId),item.harvestId,item.userId).then(res=>{
                          setAddress(res)
                    })
              }

              function toAddAddress(){
                    isOnFunction=true
                    harvestAddress.harvestAddress = ''
                    harvestAddress.isDefault = 0
                    harvestAddress.harvestType = ''
                    harvestAddress.harvestEmail = ''
                    harvestAddress.harvestName = ''

                    province.value = ''
                    city.value = ''
                    country.value = ''
                    street.value = ''

                    citySelectorShow.value = false
                    countrySelector.value = false
                    streetSelector.value = false

                    if(harvestAddressList.list.length>8){
                          alert('已经添加了十条地址，不能再添加')
                    }else {
                          $(".dialogForAddress").css('visibility','visible')
                          $(".dialogForAddress").eq(0).show(500)
                    }
              }

              function hideDialog() {
                    $(".dialogForAddress").eq(0).hide(500)
              }

              function setProvince(strs) {
                    for(let i=0;i<locals.length;i++) {
                          if (strs[0] == locals[i].name) {
                                province.value = i

                                break;
                          }
                    }
              }

              function toUpdateAddress(item){
                    isOnFunction=true

                    addFlag.value=false


                    let strs=item.harvestAddress.split(' ')

                    harvestAddress.harvestAddress=strs[4]
                    new Promise((resolve)=>{
                          resolve(
                                  setProvince(strs)
                          )
                    }).then(()=>{
                          for(let j=0;j<locals[province.value].children.length;j++){
                                if(strs[1]==locals[province.value].children[j].name){
                                      city.value=j
                                      break
                                }
                          }
                    }).then(()=>{
                          for(let i=0;i<locals[province.value].children[city.value].children.length;i++){
                                if(strs[2]==locals[province.value].children[city.value].children[i].name){
                                      country.value=i
                                      break
                                }
                          }
                    }).then(()=>{
                          for(let i=0;i<locals[province.value].children[city.value].children[country.value].children.length;i++){
                                if(strs[3]==locals[province.value].children[city.value].children[country.value].children[i].name){
                                      street.value=locals[province.value].children[city.value].children[country.value].children[i].name
                                      break
                                }
                          }
                    })

                    harvestAddress.userId=item.userId
                    harvestAddress.harvestName=item.harvestName
                    harvestAddress.harvestEmail=item.harvestEmail
                    harvestAddress.harvestType=item.harvestType
                    harvestAddress.isDefault=item.isDefault==0?false:true
                    harvestAddress.harvestId=item.harvestId

                    $(".dialogForAddress").css('visibility','visible')
                    $(".dialogForAddress").eq(0).show(500)
              }


              //选择地址
              function selectAddress(item,index) {
                    if(isOnFunction==false){
                          let reg3 = new RegExp("(^|&)" + 'orderNumber' + "=([^&]*)(&|$)");
                          let r3 = search.match(reg3);

                          modifyOrder(unescape(r3[2]),item.harvestId).then(res=>{
                                console.log(res);

                                for(let i=0;i<harvestAddressList.list.length+1;i++){
                                      $('.imageBackground').eq(i).css('opacity',0.5)
                                }
                                $('.imageBackground').eq(index).css('opacity',1)

                                harvestAddress1.data=item
                          })
                          console.log(item);
                    }
                    isOnFunction=false
              }

              let  locationLogo=require('../../../assets/img/定位.svg')
              return{
                    harvestAddress1,
                    locationLogo,
                    harvestAddressList,
                    harvestAddress,
                    showFlag,
                    showInf,
                    hideInf,
                    province,
                    city,
                    country,
                    street,
                    citySelectorShow,
                    streetSelector,
                    countrySelector,
                    cities,
                    provinces,
                    countries,
                    streets,
                    defaultAddress,
                    checkLocationFunction,
                    checkHarvestAddress,
                    checkHarvestEmail,
                    checkHarvestName,
                    checkHarvestType,
                    deleteAddress,
                    modifyAddress,
                    toUpdateAddress,
                    changeEmailForm,
                    harvestAddressCheck,
                    save,
                    setNewDefaultAddress,
                    setAddressList,
                    toAddAddress,
                    selectAddress,
                    addFlag,
                    hideDialog,
                    inf
              }
        }
  }
</script>

<style scoped>
      .imageBackground{
            color: #333;
            border:1rem #a0a0a0 solid;
            background-image:url(//ccp.vipstatic.com/img/cart/te/4/checkout/sprites-y-hash-0476d4fe.png?6f921678);
            background-position: 0 0;
            opacity: 0.5;
      }
</style>