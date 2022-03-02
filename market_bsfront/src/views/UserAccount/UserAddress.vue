<template>
        <div style="width: 850rem;height: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: center;font-size: 12rem">
            <div style="width: 100%;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
                <div style="border-left: 2rem rgb(241,1,128) solid;width: 170rem;height:20rem;align-items:center;display: flex;justify-content: space-evenly;align-items: center;font-size: 12rem;">
                    <span ><router-link style="color: black;cursor: pointer" to="/mainHeader/UserAccountCenter/userInf">我的唯品会</router-link></span>
                    <span>></span>
                    <span>收货地址</span>
                </div>
            </div>

            <div style="width: 100%;height: auto;display: flex;flex-direction: column;justify-content: space-between;align-items: center;margin-top: 5rem;padding-bottom: 20rem">

                <div style="width: 100%;height:auto;background:white;display: flex;flex-direction: column;justify-content: flex-start">

                    <div style="width: 100%;height: 56rem;border-bottom: 1rem #eff0f4 solid;display: flex;justify-content: space-evenly;align-items: center">
                        <div style="width: 95%;height: 100%;display: flex;justify-content: space-between;align-items: center">
                            <div style="width: auto;height: auto">
                                <span style="font-size: 15rem;font-weight: bold;">已经保存的收货地址</span>
                                <span v-if="defaultAddress.harvestName!==''" style="color: grey;margin-left: 10rem;font-size: 13rem"> 您已经创建 <span style="color:rgb(241,1,128)">{{harvestAddressList.list.length+1}}</span> 个收货地址，最多可创建 <span style="color:rgb(241,1,128)">10</span> 个</span>
                                <span v-else style="color: grey;margin-left: 10rem;font-size: 13rem"> 您已经创建 <span style="color:rgb(241,1,128)">{{harvestAddressList.list.length}}</span> 个收货地址，最多可创建 <span style="color:rgb(241,1,128)">10</span> 个</span>
                            </div>
                            <button @click="toAddAddress"  style="width: 110rem;height: 30rem;border: 0rem;background: rgb(241,1,128);color: white;outline: none;border-radius: 3rem">
                                新增收货地址
                            </button>
                        </div>
                    </div>

                    <div style="width: 100%;height: auto;display: flex;justify-content: space-evenly;align-items: center;padding-top: 10rem;padding-bottom: 10rem">
                        <div style="width: 95%;height: auto;display: flex;justify-content: space-between;align-items:flex-start;flex-wrap: wrap">
                            <div v-if="defaultAddress.harvestName!==''" class="p">
                                <div style="width: 90%;height: 100%;padding-bottom: 10rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-end">
                                    <div style="width: 70rem;height: 20rem;display: flex;justify-content: space-evenly;align-items: center;background: rgb(241,1,128);color: white;margin-top: 0rem;position: absolute">
                                        <span>默认地址</span>
                                    </div>
                                    <div style="width: 100%;height: auto;margin-top: 10rem;display: flex;justify-content: space-between;align-items: center;padding-top: 5rem;padding-bottom: 5rem;border-bottom: 1rem darkgrey dotted">
                                        <div class="leftIcons">
                                            <img :src="userIcon">
                                        </div>
                                        <div class="rightInf">
                                            <span style="font-size: 12rem">{{defaultAddress.harvestName}}</span>
                                        </div>
                                    </div>
                                    <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items:flex-start;padding-top: 5rem;padding-bottom: 5rem;border-bottom: 1rem darkgrey dotted">
                                        <div class="leftIcons">
                                            <img :src="homeIcon">
                                        </div>
                                        <div class="rightInf">
                                            <span style="font-size: 12rem;display: flex;flex-wrap: wrap;text-align: left">{{defaultAddress.harvestAddress}}</span>
                                        </div>
                                    </div>
                                    <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items:flex-start;padding-top: 5rem;padding-bottom: 5rem;">
                                        <div class="leftIcons">
                                            <MailFilled style="color:#d3d3d3"></MailFilled>
                                        </div>
                                        <div class="rightInf">
                                            <span style="font-size: 12rem;display: flex;flex-wrap: wrap;text-align: left">{{changeEmailForm(defaultAddress.harvestEmail)}}</span>
                                            <button @click="toUpdateAddress(defaultAddress)" style="cursor: pointer;position: absolute;margin-top: 10rem;margin-left: 160rem;height: 20rem;width: 40rem;font-size: 11rem;background: whitesmoke;border:1rem darkgrey solid">修改</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div v-for="(item,index) in harvestAddressList.list" :key="item" style="width: auto;height: auto">

                                <div   class="p1">
                                    <div @mouseenter="showInf(index)" @mouseleave="hideInf(index)" style="width: 90%;height: 90%;padding-bottom: 10rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-end">
                                        <div class="setDefaultAddressBtn" @click="setNewDefaultAddress(item)" style="width: 80rem;height: 20rem;display: flex;justify-content: space-evenly;align-items: center;background: rgba(241,1,128,0.5);color: white;margin-top: 0rem;position: absolute;visibility: hidden;cursor: pointer">
                                            <span>设为默认地址</span>
                                        </div>
                                        <div style="width: 100%;height: auto;margin-top: 10rem;display: flex;justify-content: space-between;align-items: center;padding-top: 5rem;padding-bottom: 5rem;border-bottom: 1rem darkgrey dotted">
                                            <div class="leftIcons">
                                                <img :src="userIcon">
                                            </div>
                                            <div class="rightInf">
                                                <span style="font-size: 12rem">{{item.harvestName}}</span>
                                            </div>
                                        </div>
                                        <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items:flex-start;padding-top: 5rem;padding-bottom: 5rem;border-bottom: 1rem darkgrey dotted">
                                            <div class="leftIcons">
                                                <img :src="homeIcon">
                                            </div>
                                            <div class="rightInf">
                                                <span style="font-size: 12rem;display: flex;flex-wrap: wrap;text-align: left">{{item.harvestAddress}}</span>
                                            </div>
                                        </div>
                                        <div style="width: 100%;height: auto;display: flex;justify-content: space-between;align-items:flex-start;padding-top: 5rem;padding-bottom: 5rem;">
                                            <div class="leftIcons">
                                                <MailFilled style="color:#d3d3d3"></MailFilled>
                                            </div>
                                            <div class="rightInf">
                                                <span style="font-size: 12rem;display: flex;flex-wrap: wrap;text-align: left">{{changeEmailForm(item.harvestEmail)}}</span>
                                                <button class="changeBtn" @click="toUpdateAddress(item)" style="position: absolute;margin-top: 20rem;margin-left: 110rem;height: 20rem;width: 40rem;font-size: 11rem;background: whitesmoke;border:1rem darkgrey solid;visibility: hidden;cursor: pointer">修改</button>
                                                <button  class="deleteBtn" @click="deleteAddress(item)" style="position: absolute;margin-left: 165rem;margin-top: 20rem;height: 20rem;width: 40rem;font-size: 11rem;background: whitesmoke;border:1rem darkgrey solid;visibility: hidden;cursor: pointer">删除</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>

                </div>

                <div class="addAddressDiv" v-if="addFlag" style="width: 100%;height: auto;background:white;margin-top: 15rem;border-top:1rem rgb(241,1,128) solid;display: flex;flex-direction: column;justify-content: flex-start;padding-bottom: 20rem;align-items: center;font-size: 13rem;">

                    <div style="width: 100%;height: 50rem;display: flex;align-items: flex-start;justify-content: center;border-bottom: 1rem #eff0f4 solid;">
                        <div style="width: 95%;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
                            <span style="font-size: 15rem;font-weight: bold">新增收货地址</span>
                        </div>
                    </div>

                    <div style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start">
                       <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 150rem;margin-top: 20rem;display: flex;justify-content: flex-start;align-items: flex-start;">
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

                <div class="updateAddressDiv" v-else style="width: 100%;height: auto;background:white;margin-top: 15rem;border-top:1rem rgb(241,1,128) solid;display: flex;flex-direction: column;justify-content: flex-start;padding-bottom: 20rem;align-items: center;font-size: 13rem;">

                    <div style="width: 100%;height: 50rem;display: flex;align-items: flex-start;justify-content: center;border-bottom: 1rem #eff0f4 solid;">
                        <div style="width: 95%;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
                            <span style="font-size: 15rem;font-weight: bold">修改收货地址</span>
                        </div>
                    </div>

                    <div style="width: 100%;height: auto;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start">
                        <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 30rem;margin-top: 20rem;display: flex;justify-content: flex-start">
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

                        <div style="width: auto;height: 150rem;margin-top: 20rem;display: flex;justify-content: flex-start;align-items: flex-start;">
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

                        <div style="width: auto;height: 20rem;margin-top: 10rem;display: flex;justify-content: flex-start;margin-bottom: 20rem">
                            <div style="width: 120rem;height:100%;display: flex;justify-content: flex-end;align-items: center">

                            </div>
                            <div style="width: auto;height: 100%;display: flex;justify-content: flex-start;align-items: center;margin-left: 20rem;font-size: 12rem;color: indianred">
                                <span>{{harvestAddressCheck.harvestTypeCheck}}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-for="(item,index) in store.state.shoppingCartList.list" :key="index" style="width: 100%;height: auto;padding-left: 15rem;padding-right: 15rem;display: flex;justify-content: flex-start;align-items: center;margin-top: 10rem;flex-wrap: wrap;padding-bottom: 20rem">
                    <div style="width: 100%;height: auto;display: flex;justify-content: flex-start;align-items: center;font-size: 12rem">
                        <input type="checkbox" v-model="selectShop.list" :value="index">&nbsp;&nbsp;{{item.shop.shopName}}
                    </div>
                    <div style="margin-top: 10rem;padding: 10rem;width: 100%;heistght: auto;display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;background-color: rgb(252, 252, 252);border: 1rem lightgrey solid">
                        <div v-for="(item1,index1) in item.goodsList" :key="index1" style="width: 100%;height: 101rem;display: flex;justify-content: flex-start;align-items: center">
                            <div style="width: 38rem;height: 100%;display: flex;justify-content: flex-start;align-items: center;font-size: 12rem">
                                <input type="checkbox" v-model="selectGoodsInShoppingCart.list" :value="{'goodsId':item1.goodsId,'specificationsId':item.goodsSpecificationsList[index1].specificationsId,'userId':user1.userId,'goodsPrice':item.goodsSpecificationsList[index1].goodsPrice,'goodsCount':item.goodsCountList[index1],'index':index,'index1':index1}">
                            </div>
                            <div style="width: 300rem;height: 100%;display: flex;justify-content: space-between;align-items: center;font-size: 12rem">
                                <img :src="item1.goodsImage1" style="width: 76rem;height: 76rem;">

                                <div style="width:197rem;height: 88.5rem;display: flex;flex-direction: column;justify-content: space-between;align-items: flex-start">
                                    <router-link :to="{path:'/mainHeader/goodsDetailPage',query: {goodsId:item1.goodsId}}" @click="selectGoods(item1.goodsId)" style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:宋体;color: black">{{item1.goodsTitle}}</router-link>
                                    <span style="width: 100%; word-break:break-all;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;font-size: 10rem;text-align: left;font-family:'Microsoft YaHei';color: rgb(159,158,158);margin-top: 10rem">{{item.goodsSpecificationsList[index1].goodsType}}</span>
                                </div>
                            </div>
                            <div style="width: 200rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                ￥{{item.goodsSpecificationsList[index1].goodsPrice}}
                            </div>
                            <div style="width: 113rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                <div style="width: 75rem;height: 30rem;border: 1rem darkgrey solid;display: flex;justify-content: space-between;align-items: center">
                                    <div style="width: 21rem;height: 100%;border-right: 1rem darkgrey solid;background: whitesmoke;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer"
                                         @click="()=>{
                                           if(item.goodsCountList[index1]>1){
                                             item.goodsCountList[index1]--
                                              updateShoppingCart({
                                               'goodsId': item1.goodsId,
                                               'userId': user1.userId,
                                               'time': '',
                                               'specificationsId': item.goodsSpecificationsList[index1].specificationsId,
                                               'count': item.goodsCountList[index1],
                                               'shopId': item.shop.shopId
                                               })
                                           }
                                         }">
                                        <minus-outlined></minus-outlined>
                                    </div>
                                    <div style="width: 33rem;height: 100%;background: white;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer">
                                        {{item.goodsCountList[index1]}}
                                    </div>
                                    <div style="width: 21rem;height: 100%;background: whitesmoke;border-left: 1rem darkgrey solid;display: flex;justify-content: center;align-items: center;color: grey;cursor: pointer" @click="
                                    ()=>{
                                       if(item.goodsCountList[index1]<item.goodsSpecificationsList[index1].goodsStoke){
                                         item.goodsCountList[index1]++
                                          updateShoppingCart({
                                               'goodsId': item1.goodsId,
                                               'userId': user1.userId,
                                               'time': '',
                                               'specificationsId': item.goodsSpecificationsList[index1].specificationsId,
                                               'count': item.goodsCountList[index1],
                                               'shopId': item.shop.shopId
                                               })
                                       }
                                    }
                                    ">
                                        <plus-outlined></plus-outlined>
                                    </div>
                                </div>
                            </div>
                            <div style="width: 162rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem">
                                ￥{{item.goodsSpecificationsList[index1].goodsPrice*item.goodsCountList[index1]}}
                            </div>
                            <div style="width: 140rem;height: 100%;display: flex;justify-content: center;align-items: center;font-size: 12rem;">
                                <span style="cursor: pointer"
                                      @click="deleteShoppingCartItem(item1.goodsId,item.goodsSpecificationsList[index1].specificationsId)"
                                >删除</span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
</template>

<script>
  import {MailFilled} from '@ant-design/icons-vue'
  import $ from 'jquery'
  import getLocals from "../../assets/functions/getLocals";
  import {reactive, ref} from "@vue/reactivity";
  import {watch} from 'vue'
  import addAddress from "../../Internet/home/address/add/addAddress";
  import getHarvestAddressList from "../../Internet/home/address/select/getHarvestAddressList";
  import setDefaultAddress from "../../Internet/home/address/update/setDefaultAddress";
  import removeAddress from "../../Internet/home/address/remove/removeAddress";
  import updateAddress from "../../Internet/home/address/update/updateAddress";
  import store from '../../store/index'
  export default {
    name: "UserAddress",
    components:{
      MailFilled
    },
    setup(){
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

     getHarvestAddressList(JSON.parse(sessionStorage.getItem('user')).userId).then(res=>{
       setAddress(res)
     })

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
     }

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
            console.log(res);
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

      function deleteAddress(item){
        removeAddress(item).then(res=>{
          setAddress(res)
        })
      }

      function setNewDefaultAddress(item) {
        setDefaultAddress(Number(defaultAddress.harvestId),item.harvestId,item.userId).then(res=>{
          setAddress(res)
        })
      }

      function toAddAddress(){
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
          new Promise(resolve => {
            resolve(
                addFlag.value=true
            )
          }).then(()=>{
            let top = (document.getElementsByClassName('addAddressDiv'))[0].getBoundingClientRect().top;
            window.scrollTo(0,Math.abs(top))
          })
        }
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
        new Promise(resolve => {
          resolve(
              addFlag.value=false
          )
        }).then(()=>{
          let top = (document.getElementsByClassName('updateAddressDiv'))[0].getBoundingClientRect().top;
          window.scrollTo(0,Math.abs(top))
        })

        console.log(item);


       let strs=item.harvestAddress.split(' ')

        harvestAddress.harvestAddress=strs[4]
        new Promise((resolve)=>{
          resolve(
            setProvince(strs)
          )
        }).then(()=>{
          console.log(locals[province.value].children);
          for(let j=0;j<locals[province.value].children.length;j++){
            if(strs[1]==locals[province.value].children[j].name){
              city.value=j
              console.log(city.value);
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
          console.log(locals[province.value].children[city.value].children[country.value].children);
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
      }

      return{
        addressSelected,
        userIcon,
        homeIcon,
        showInf,
        hideInf,
        province,
        city,
        store,
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
        toAddAddress,
        addFlag
      }
    }
  }
</script>

<style scoped>
    .p{
        background-image: url('../../assets/img/address-bg-selected.png');
        width: 255rem;
        height: 150rem;
        border: 1rem #eff0f4 solid;
        margin-top: 20rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    .p1{
        background-image: url('../../assets/img/address-bg.png');
        width: 255rem;
        height: 150rem;
        border: 1rem #eff0f4 solid;
        margin-top: 20rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
    }

    .leftIcons{
        width: 10%;
        height: auto;
        display: flex;
        align-items: flex-start;
        justify-content: flex-start;
    }
    .rightInf{
        width: 90%;
        height: auto;
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }
</style>