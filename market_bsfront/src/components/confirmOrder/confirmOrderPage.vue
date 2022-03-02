<template>
    <div  style="width: 100%;height: auto;display: flex;flex-wrap: wrap;justify-content: center">
        <top-header></top-header>
        <div class="mainDiv" style="width: 100%;display: flex;height: auto;background-color: white;justify-content: space-evenly;align-items: flex-start;flex-wrap: wrap;padding-bottom: 20rem">
            <div style="height: 84rem;width: 1000rem;display: flex;justify-content: center;align-items: center;margin-top: 20rem">
                <div style="width: auto;height: 100%;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                    <div style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: flex-start">
                        <img style="max-height: 40rem" :src="raceLogo1">
                        <img style="max-height: 40rem" :src="raceLogo2">
                        <img style="max-height: 40rem" :src="raceLogo3">
                    </div>
                    <div style="width: 580rem;height: auto;display:flex;justify-content:space-between;align-items: flex-start;padding-top: 10rem">
                        <router-link to="/shoppingCartPage" style="font-size: 14rem;color: darkgrey">
                            查看购物袋
                        </router-link>
                        <span style="font-size: 14rem;">
                            确认订单信息
                        </span>
                        <span style="font-size: 14rem;color: darkgrey">
                            成功提交订单
                        </span>
                    </div>
                </div>
            </div>
            <div style="height:auto;width: 1000rem;display: flex;justify-content: space-evenly;flex-wrap: wrap;align-items: flex-start;margin-top: 20rem">
                <div style="height: 40rem;width: 1000rem;display: flex;justify-content:space-between;align-items:center;padding-top: 10rem;padding-left: 15rem;padding-bottom: 10rem;padding-right: 15rem;border: 1rem solid #e2e2e2; border-left: 2rem solid #f3168a;background-color: #f9f9f9;border-top-right-radius: 3rem;border-bottom-right-radius: 3rem;opacity: 1;">
                    <h2 style="font-size: 16rem;font-weight: 400;padding: 0;margin: 0">收货信息</h2>
                    <div style="width: auto;height: auto;display: flex;justify-content: space-between;align-items: center">
                        <span style="font-size: 12rem;color: #1d94d1;cursor: pointer;" @click="toAddAddress">新增收货地址</span>
                        <span style="font-size: 12rem;width: 80rem;display: flex;justify-content: flex-end;align-items: center;cursor: pointer" @click="()=>{isAllFlag=!isAllFlag;setAddressList()}" v-if="!isAllFlag">全部地址&nbsp;<down-outlined style="font-size: 12rem"></down-outlined></span>
                        <span style="font-size: 12rem;width: 80rem;display: flex;justify-content: flex-end;align-items: center;cursor: pointer" @click="()=>{isAllFlag=!isAllFlag;setAddressList()}" v-else>收起&nbsp;<up-outlined style="font-size: 12rem"></up-outlined></span>
                    </div>
                </div>
                <div style="width: 1000rem;height: auto;display: flex;justify-content:flex-start;align-items: flex-start;flex-wrap: wrap;">
                    <div style="width: 33%;height: auto;display: flex;justify-content: center;align-items: center">
                        <div @click="selectAddress(defaultAddress,0)" class="imageBackground" style="cursor:pointer;opacity: 1;width: 296rem;height: 150rem;padding-top: 6rem;padding-bottom: 6rem;padding-left:15rem;padding-right: 15rem;margin-top: 20rem">
                            <div style="width: 100%;height: 45rem;display: flex;justify-content: space-between;align-items: center;border-bottom: 1rem dashed #ececec;">
                                <span style="font-size: 14rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">{{defaultAddress.harvestName}}</span>
                                <div style="width: 35%;height: auto;display: flex;justify-content:space-between;align-items: center">
                                    <span style="font-size: 12rem;color:steelblue;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor: pointer" @click="toUpdateAddress(defaultAddress)">修改</span>
                                    <span style="font-size: 12rem;color:#a0a0a0;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">默认地址</span>
                                </div>
                            </div>
                            <div style="width: 100%;height: 30.46rem;display: flex;justify-content: space-between;align-items: center;font-size: 12rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">
                                {{changeEmailForm(defaultAddress.harvestEmail)}}
                            </div>
                            <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items: flex-start;margin-top: 10rem">
                                <img :src="locationLogo" style="max-width: 13rem">
                                <div style="width: 248rem;height: auto;display: flex;justify-content: flex-start;flex-wrap: wrap;text-align: left;font-size: 12rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">
                                    {{defaultAddress.harvestAddress}}
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div style="width: 33%;height: auto;display: flex;justify-content: center;align-items: center" v-for="(item,index) in harvestAddressList.list" :key="index">
                        <div @click="selectAddress(item,index+1)" class="imageBackground"  style="cursor: pointer;width: 296rem;height: 150rem;padding-top: 6rem;padding-bottom: 6rem;padding-left:15rem;padding-right: 15rem;margin-top: 20rem">
                        <div style="width: 100%;height: 45rem;display: flex;justify-content: space-between;align-items: center;border-bottom: 1rem dashed #ececec;">
                            <span style="font-size: 14rem;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ'">{{item.harvestName}}</span>
                            <div style="width: 50%;height: auto;display: flex;justify-content:space-between;align-items: center">
                                <span style="font-size: 12rem;color:steelblue;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor:pointer;cursor: pointer" @click="toUpdateAddress(item)">修改</span>
                                <span style="font-size: 12rem;color:steelblue;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor: pointer" @click="deleteAddress(item)">删除</span>
                                <span style="font-size: 12rem;color:#a0a0a0;font-family: tahoma, arial, 'Hiragino Sans GB', 'WenQ';cursor: pointer" @click="setNewDefaultAddress(item)">设为默认地址</span>
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
                    <div v-if="isAllFlag" style="width: 33%;height: auto;display: flex;justify-content: center;align-items: center">
                        <div  style="width: 296rem;height: 150rem;padding-top: 6rem;padding-bottom: 6rem;padding-left:15rem;padding-right: 15rem;margin-top: 20rem;border: 1rem solid #d0d0d0;display: flex;justify-content: center;align-items: center;background-color:rgb(249, 249, 249)">
                            <div @click="toAddAddress" style="cursor: pointer;width: 200rem;height: 62rem;display: flex;flex-direction: column;justify-content: space-between;align-items: center">
                                <span style="font-size: 28rem;color:rgb(176, 176, 176)"><plus-outlined></plus-outlined></span>
                                <span style="font-size: 14rem;color:rgb(176, 176, 176)">添加地址</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="dialogForAddress" style="position: fixed;display: flex;top:10rem;height: 500rem;width: 800rem;background-color:rgba(200,200,200,0.7);padding: 5rem;visibility: hidden">
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
            <div style="height:auto;width: 1000rem;display: flex;justify-content: space-evenly;flex-wrap: wrap;align-items: flex-start;margin-top: 30rem">
                <div style="height: 40rem;width: 1000rem;display: flex;justify-content:space-between;align-items:center;padding-top: 10rem;padding-left: 15rem;padding-bottom: 10rem;padding-right: 15rem;border: 1rem solid #e2e2e2; border-left: 2rem solid #f3168a;background-color: #f9f9f9;border-top-right-radius: 3rem;border-bottom-right-radius: 3rem;opacity: 1;">
                    <h2 style="font-size: 16rem;font-weight: 400;padding: 0;margin: 0">商品清单</h2>
                    <router-link to="shoppingCartPage" style="font-size: 12rem;color: #1d94d1;cursor: pointer;" >返回购物车修改商品信息</router-link>
                </div>

                <div v-for="(item,index) in goodsList" :key="index" style="width: 100%;height: auto;display: flex;justify-content: center;align-items: flex-start;flex-wrap: wrap">
                    <div style="width: 930rem;height: 40rem;padding-left: 15rem;padding-right: 15rem;display: flex;justify-content: flex-start;align-items: center;margin-top: 10rem;border-bottom: 1rem lightgrey solid">
                        <div style="width: 438rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;font-size: 16rem;font-weight: 700">
                            {{item.shop.shopName}}&nbsp;店铺商品&nbsp;发货订单
                        </div>
                        <div style="width: 200rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                            单价
                        </div>
                        <div style="width: 120rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                            数量
                        </div>
                        <div style="width: 172rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                            金额
                        </div>
                    </div>

                    <div  style="width: 930rem;height: auto;padding-left: 15rem;padding-right: 15rem;display: flex;justify-content: flex-start;align-items: center;margin-top: 10rem;flex-wrap: wrap;padding-bottom: 10rem">
                        <div style="margin-top: 10rem;padding: 10rem;width: 100%;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;">
                            <div v-for="(item1,index1) in item.goodsList" :key="index1" style="width: 100%;height: 101rem;display: flex;justify-content: flex-start;align-items: center">
                                <div style="width: 438rem;height: 100%;display: flex;justify-content: space-between;align-items: center;font-size: 12rem">
                                    <div style="width: 350rem;height: 100%;display: flex;justify-content: space-between;align-items: center;font-size: 12rem">
                                        <img :src="item1.goodsImage1" style="width: 76rem;height: 76rem;">

                                        <div style="width:197rem;height: 88.5rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                                            <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{item1.goodsTitle}}</span>
                                            <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem">{{item.goodsSpecificationsList[index1].goodsType}}</span>
                                        </div>
                                    </div>
                                </div>
                                <div style="width: 200rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                    ￥{{item.goodsSpecificationsList[index1].goodsPrice}}
                                </div>
                                <div style="width: 113rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                    <div style="width: 75rem;height: 30rem;display: flex;justify-content: center;align-items: center">
                                        {{item.goodsCountList[index1]}}
                                    </div>
                                </div>
                                <div style="width: 162rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                    ￥{{item.goodsSpecificationsList[index1].goodsPrice*item.goodsCountList[index1]}}
                                </div>
                            </div>
                        </div>

                        <div style="width: 100%;height: 40rem;display: flex;justify-content: flex-end;align-items: center;border-top: 1rem lightgrey solid">
                            <span style="font-size: 12rem;font-weight: 700;margin-right: 40rem" v-if="shopTotalAccount[index]<item.shop.freeDelivery">运费： {{item.shop.freight}}</span>
                            <span style="font-size: 12rem;font-weight: 700;margin-right: 40rem" v-else>运费： 免运费</span>
                            <span style="font-size: 12rem;font-weight: 700;margin-right: 50rem" v-if="shopTotalAccount[index]>=item.shop.freeDelivery">本组商品金额： ¥{{shopTotalAccount[index]}}</span>
                            <span style="font-size: 12rem;font-weight: 700;margin-right: 50rem" v-else>本组商品金额： ¥{{shopTotalAccount[index]+item.shop.freight}}</span>
                        </div>
                    </div>


                </div>
            </div>
            <div style="height:auto;width: 1000rem;display: flex;justify-content: space-evenly;flex-wrap: wrap;align-items: flex-start;margin-top: 30rem">
                <div style="height: 40rem;width: 1000rem;display: flex;justify-content:space-between;align-items:center;padding-top: 10rem;padding-left: 15rem;padding-bottom: 10rem;padding-right: 15rem;border: 1rem solid #e2e2e2; border-left: 2rem solid #f3168a;background-color: #f9f9f9;border-top-right-radius: 3rem;border-bottom-right-radius: 3rem;opacity: 1;">
                    <h2 style="font-size: 16rem;font-weight: 400;padding: 0;margin: 0">支付信息</h2>
                </div>
                <div style="width: 100%;height: 235rem;display: flex;flex-direction: column;justify-content: flex-start;align-items:center;border: 1rem lightgrey solid;margin-top: 20rem;border-radius: 2rem;padding-top: 20rem">
                    <div style="width: 95%;height: 45rem;display:flex;flex-direction: column;justify-content: space-between;align-items: flex-end">
                        <span style="width: 250rem;height: auto;display: flex;justify-content: space-between;color: rgb(102, 102, 102);font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                            <span style="width: 150rem;height: auto;display: flex;justify-content: flex-end">
                                商品金额
                            </span>
                            <span style="width: 100rem;height: auto;display: flex;justify-content: flex-end">
                               <span style="font-family: Arial">¥</span>{{totalAccount}}
                            </span>
                        </span>
                        <span style="width: 250rem;height: auto;display: flex;justify-content: space-between;color: rgb(102, 102, 102);font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem">
                            <span style="width: 150rem;height: auto;display: flex;justify-content: flex-end">
                                运费
                            </span>
                            <span style="width: 100rem;height: auto;display: flex;justify-content: flex-end">
                                <span v-if="totalFreight>0"><span style="font-family: Arial">¥</span>{{totalFreight}}</span>
                                <span v-else>免运费</span>
                            </span>
                        </span>
                    </div>

                    <div style="width: 95%;height: auto;display:flex;flex-direction: column;justify-content: space-between;align-items: flex-end">
                        <span style="width: 250rem;margin-top: 15rem;height: auto;display: flex;justify-content: space-between;align-items: center">
                            <span style="width: 150rem;height: auto;display: flex;justify-content: flex-end;font-size: 20rem;font-weight: 700">
                               待支付
                            </span>
                            <span style="width: 100rem;height: auto;display: flex;justify-content: flex-end;color:rgb(241, 1, 128);font-family:Arial;font-size: 26rem;align-items: center">
                                <span style="color:rgb(241, 1, 128);font-family:Arial;font-size: 16rem">￥</span>
                                    {{totalAccount+totalFreight}}
                            </span>
                        </span>
                    </div>

                    <div style="width: 95%;height: auto;display:flex;flex-direction: column;justify-content: space-between;align-items: flex-end;font-family:tahoma, arial, 'Hiragino Sans GB', 'WenQuanYi Micro Hei', 微软雅黑, 宋体, sans-serif;font-size: 12rem;font-size: 12rem;margin-top: 20rem">
                        <span style="max-width: 50%;display: flex;justify-content: flex-start;flex-wrap: wrap;color: rgb(102, 102, 102)">
                            <span>送货至：</span>
                            <span style="width: auto;height: auto;">{{selectedAddress.harvestAddress}}，</span>
                            <span>{{selectedAddress.harvestName}}，</span>
                             <span>{{changeEmailForm(selectedAddress.harvestEmail)}}</span>
                        </span>
                    </div>

                    <div style="width: 100%;height: 50rem;display: flex;justify-content: flex-end;align-items: center;background-color: #f0f0f0;margin-top: 24rem">
                        <div style="width: 50%;height: 100%;display: flex;justify-content: space-between;align-items: center">
                             <span style="color: #666;font: 14rem 'Microsoft Yahei';">
                                 请在倒计时结束前提交订单
                            </span>
                            <button @click="saveOrder" style="width: 250rem;height: 100%;background-color:rgb(241, 1, 128);color: white;font-size: 20rem;border: 0rem;outline-color: white;cursor: pointer;display: flex;justify-content: center;align-items: center">
                                <span>提交订单</span>
                                &nbsp;
                                <span>{{Math.floor(time / 60)}}</span>:<span>{{Math.floor(time % 60)}}</span>
                            </button>
                        </div>
                    </div>
                </div>

                <img :src="infLogo" style="width: 100%;height: auto;margin-top: 20rem">
            </div>
        </div>
    </div>
</template>

<script>
  import topHeader from "../../views/inf/topHeader";
  import {reactive, ref} from "@vue/reactivity";
  import {provide} from "@vue/runtime-core";
  import {watch} from 'vue'
  import {DownOutlined,UpOutlined,PlusOutlined,CloseOutlined} from '@ant-design/icons-vue'
  import setDefaultAddress from "../../Internet/home/address/update/setDefaultAddress";
  import removeAddress from "../../Internet/home/address/remove/removeAddress";
  import updateAddress from "../../Internet/home/address/update/updateAddress";
  import addAddress from "../../Internet/home/address/add/addAddress";
  import getHarvestAddressList from "../../Internet/home/address/select/getHarvestAddressList";
  import $ from 'jquery'
  import getLocals from "../../assets/functions/getLocals";
  import payForOrder from "../../Internet/home/shoppingOrder/save/payForOrder";
  import router from "../../router";
  let loginName=ref('')
  let user1=reactive(JSON.parse(sessionStorage.getItem("user")))
  export default {
    name: "confirmOrderPage",
    components:{
      topHeader,
      DownOutlined,
      UpOutlined,
      PlusOutlined,
      CloseOutlined
    },
    setup(){

      let loginFlag=ref(true)

      //给子组件传值
      provide("loginFlag",loginFlag)

      provide("loginName",loginName)

      if(user1!==null&&user1!==undefined){
        loginFlag.value=false

        if(user1.username!==null&&user1.username!==undefined){
          loginName.value=user1.username
        }else {
          //中间4位加密显示出来
          let result=/^(\w?)(\w+)(\w)(@\w+\.[a-z]+(\.[a-z]+)?)$/
          this.loginName.value = (String(user1.email)).replace(result, "$1****$3$4");
        }
      }else {
        loginFlag.value=true
      }

      let logo=require('../../assets/img/checkoutHeader.png')

      let raceLogo1=require('../../assets/img/2021-08-23_203223.png')
      let raceLogo2=require('../../assets/img/2021-08-23_203626.png')
      let raceLogo3=require('../../assets/img/2021-08-23_203036.png')
      let locationLogo=require('../../assets/img/定位.svg')

      let harvestAddress=reactive({
        harvestId:'',
        userId:JSON.parse(sessionStorage.getItem('user')).userId,
        harvestAddress:'',
        harvestType:'',
        harvestEmail:'',
        isDefault:0,
        harvestName:'',
      })

      let harvestAddressCheck=reactive({
        localsCheck:'',
        harvestAddressCheck:'',
        harvestTypeCheck:'',
        harvestEmailCheck:'',
        harvestNameCheck:''
      })

      let harvestAddressList=reactive({
        list:[]
      })

      let addressSelected=require("../../assets/img/address-bg-selected.png")
      let userIcon=require("../../assets/img/i-addr-user.png")
      let homeIcon=require("../../assets/img/i-addr-home.png")



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

      let isAllFlag=ref(false)

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
          if (res.data[i].isDefault == 1) {
            defaultAddress.harvestAddress = res.data[i].harvestAddress
            defaultAddress.harvestEmail = res.data[i].harvestEmail
            defaultAddress.harvestId = res.data[i].harvestId
            defaultAddress.harvestName = res.data[i].harvestName
            defaultAddress.harvestType = res.data[i].harvestType
            defaultAddress.isDefault = res.data[i].isDefault
            defaultAddress.userId = res.data[i].userId
          } else {
            harvestAddressList.list[j++] = res.data[i]
          }
        }

        console.log(isAllFlag.value);
        if(isAllFlag.value==false){
          harvestAddressList.list.splice(2,harvestAddressList.list.length-2)
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

      //选择的地址 默认为默认地址
      let selectedAddress=defaultAddress


      //选择地址
      function selectAddress(item,index) {
        if(isOnFunction==false){
          console.log(item);
          for(let i=0;i<harvestAddressList.list.length+1;i++){
            $('.imageBackground').eq(i).css('opacity',0.5)
          }
          $('.imageBackground').eq(index).css('opacity',1)


          selectedAddress.harvestAddress = item.harvestAddress
          selectedAddress.isDefault = item.isDefault
          selectedAddress.harvestType =item.harvestType
          selectedAddress.harvestEmail = item.harvestEmail
          selectedAddress.harvestName = item.harvestName
          selectedAddress.userId=item.userId
          selectedAddress.harvestId=item.harvestId

          console.log(selectedAddress);
        }
        isOnFunction=false
      }

      let goodsList=JSON.parse(sessionStorage.getItem('goodsList'))

      let shopTotalAccount=[];

      for(let i=0;i<goodsList.length;i++){
        let account=0
        for(let j=0;j<goodsList[i].goodsList.length;j++){
          account+=goodsList[i].goodsSpecificationsList[j].goodsPrice*goodsList[i].goodsCountList[j]
        }
        shopTotalAccount.push(account)
      }

      let  totalAccount=ref(0)
      for(let i=0;i<goodsList.length;i++){
        for(let j=0;j<goodsList[i].goodsList.length;j++){
          totalAccount.value+=goodsList[i].goodsSpecificationsList[j].goodsPrice*goodsList[i].goodsCountList[j]
        }
      }

      let totalFreight=ref(0)
      for(let i=0;i<goodsList.length;i++){
        let totalAccount1=0
        for(let j=0;j<goodsList[i].goodsList.length;j++) {
          totalAccount1+=goodsList[i].goodsSpecificationsList[j].goodsPrice*goodsList[i].goodsCountList[j]
        }


        if(totalAccount1<goodsList[i].shop.freeDelivery){
          totalFreight.value+=goodsList[i].shop.freight
        }

      }



      let time=ref(20*60)

      let timer=setInterval(()=>{
        time.value--
      },3000)

      watch(time,
          ()=>{
            if(time.value==0){
              clearInterval(timer)
            }
          }
      )

      let infLogo=require('../../assets/img/2021-08-24_221548.png')

      let strs=window.location.href.split('/#/')

      //提交订单
      function saveOrder(){


        let data={
          "shoppingCartGroupByShopList":goodsList,
          "harvestAddress":selectedAddress,
          "url":strs[0]
        }


        for(let i=0;i<data.shoppingCartGroupByShopList.length;i++){
          for(let j=0;j<data.shoppingCartGroupByShopList[i].goodsList.length;j++){
            if(typeof(data.shoppingCartGroupByShopList[i].goodsList[j].goodsSpecifications)!='string'){
              data.shoppingCartGroupByShopList[i].goodsList[j].goodsSpecifications=JSON.stringify(data.shoppingCartGroupByShopList[i].goodsList[j].goodsSpecifications)
            }
          }
        }


        payForOrder(data).then(res=>{
          // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
          const divForm = document.getElementsByTagName('div1')
          if (divForm.length) {
            document.body.removeChild(divForm[0])
          }
          const div = document.createElement('div1')
          div.innerHTML = res // data就是接口返回的form 表单字符串
          document.body.appendChild(div)
          document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
          document.forms[0].submit()


          let browserName = navigator.appName;
          if (browserName == "Netscape") {
            window.location.href = "about:blank";                    //关键是这句话
            window.close();
          } else if (browserName == "Microsoft Internet Explorer") {
            window.opener = null;
            window.close();
          }


          router.push("/mainHeader/UserAccountCenter/userOrder")
        })
      }

      return{
        time,
        saveOrder,
        shopTotalAccount,
        addressSelected,
        selectedAddress,
        userIcon,
        homeIcon,
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
        harvestAddress,
        changeEmailForm,
        harvestAddressCheck,
        save,
        harvestAddressList,
        setNewDefaultAddress,
        setAddressList,
        toAddAddress,
        selectAddress,
        addFlag,
        logo,
        raceLogo1,
        raceLogo2,
        raceLogo3,
        locationLogo,
        isAllFlag,
        hideDialog,
        goodsList,
        totalAccount,
        infLogo,
        totalFreight
      }
    },
    created() {
      window.addEventListener('setItem', ()=> {
        this.newVal = sessionStorage.getItem('user');
        loginName.value=(JSON.parse(sessionStorage.getItem("user")).username)
        user1=reactive(JSON.parse(sessionStorage.getItem("user")))
      })
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