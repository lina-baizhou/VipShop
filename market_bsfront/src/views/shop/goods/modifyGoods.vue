<template>
    <div style="width: 100%;height: auto;padding-top: 20rem;padding-bottom: 20rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center;font-size: 12rem;padding-bottom: 40rem">
        <div style="width: 90%;height: auto;display: flex;flex-direction: column;align-items: flex-start;padding-top: 20rem;padding-bottom: 20rem">
            <div style="width: 600rem;height: auto;display: flex;justify-content: flex-start;align-items: start">
                <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          商品标题：
                       </span>
                </div>
                <div  class="right_input">
                    <textarea :onblur="checkGoodsTitle" v-model="goods.goodsTitle"  placeholder="商品标题组成：商品描述加规格，最多输入30个汉字（60个字符）" style="width: 400rem;height: 20rem;border: 1rem grey solid;border-radius: 2rem;margin-top: 5rem;outline-color: cornflowerblue"></textarea>
                </div>
            </div>

            <div style="width: 400rem;height: 25rem;display: flex;justify-content: flex-start;align-items: start;margin-top: 10rem">
                <div class="left_label">

                </div>
                <div class="right_input" style="display: flex;justify-content: flex-start;">
                    <span style="width:auto;height:auto;color: indianred">{{goodsInfCheck.titleCheck}}</span>
                </div>
            </div>

            <div style="width: 600rem;height: auto;display: flex;justify-content: flex-start;align-items: start">
                <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          商品品牌：
                       </span>
                </div>
                <div  class="right_input">
                    <textarea :onblur="checkGoodsBrand" v-model="goods.goodsBrand"  placeholder="请输入商品品牌,最多输入30个汉字（60个字符）" style="width: 400rem;height: 20rem;border: 1rem grey solid;border-radius: 2rem;margin-top: 5rem;outline-color: cornflowerblue"></textarea>
                </div>
            </div>

            <div style="width: 400rem;height: 25rem;display: flex;justify-content: flex-start;align-items: start;margin-top: 10rem">
                <div class="left_label">

                </div>
                <div class="right_input" style="display: flex;justify-content: flex-start;">
                    <span style="width:auto;height:auto;color: indianred">{{goodsInfCheck.goodsBrandCheck}}</span>
                </div>
            </div>

            <div style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          上传商品主图：
                       </span>
                </div>
                <div class="right_input">
                    <div  style="width: auto;height:auto;display: flex;flex-direction: column">
                        <span style="color: grey;font-size: 12rem;margin-top: 10rem">上传高清正面商品主图，可快速智能识别及填充商品信息，帮您智能选择发布类目，上传清晰商品正面图，自动生成白底图</span>

                        <div style="width: 600rem;height: 100rem;margin-top: 10rem;display: flex;justify-content: space-between;align-items: center">
                            <div style="width: 100rem;height: 100rem;border:2rem dotted grey;background: white;display: flex;flex-direction: column;align-items: center;justify-content: flex-end">
                                <img v-if="imageFlag1" :src="image1" style="width: 100rem;height: 100rem;max-height: 100rem;z-index: 1">
                                <div
                                        @mouseenter="()=>{
                                          enterImage(0,'topInf');
                                          leaveImage(0,'bottomInf')
                                        }"
                                        @mouseleave="()=>{
                                          leaveImage(0,'topInf');
                                          enterImage(0,'bottomInf')
                                        }"
                                        v-if="imageFlag1"
                                        style="position: absolute;width: 100rem;height: 100rem;display: flex;flex-direction: column;justify-content: space-between;z-index: 2"
                                >
                                    <div class="topInf" style="width: 100rem;height: 20rem;background: rgba(0,0,0,0.4);z-index: 2;display: flex;justify-content: center;align-items: center">
                                        <span style="color: white">商品主图</span>
                                    </div>

                                    <div  class="bottomInf" style="width: 100rem;height: 30rem;background: rgba(0,0,0,0.4);z-index: 2;display: flex;justify-content: center;align-items: center;opacity: 0">
                                        <span><DeleteOutlined  style="color: white;font-size: 15rem;cursor: pointer" @click="deleteImage('image1')"></DeleteOutlined></span>
                                    </div>
                                </div>

                                <div v-else style="width: 100%;height: 70%;display: flex;flex-direction: column;justify-content: flex-start;align-items: center">
                                    <PlusOutlined style="font-size: 30rem;color:rgb(244,51,153)"></PlusOutlined>
                                    <span style="color: grey;margin-top: 10rem">添加上传图片</span>
                                </div>
                                <input :onblur="checkGoodsImage" class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="(e)=>{temp=1;onchangeImgFun(e)}">
                            </div>

                            <div style="width: 100rem;height: 100rem;border:2rem dotted grey;background: white;display: flex;flex-direction: column;align-items: center;justify-content: flex-end">
                                <img v-if="imageFlag2" :src="image2" style="width: 100rem;height: 100rem;max-height: 100rem;z-index: 1">
                                <div
                                        @mouseenter="()=>{
                                          leaveImage(0,'bottomInf1')
                                        }"
                                        @mouseleave="()=>{
                                          enterImage(0,'bottomInf1')
                                        }"
                                        v-if="imageFlag2"
                                        style="position: absolute;width: 100rem;height: 100rem;display: flex;flex-direction: column;justify-content: flex-end;z-index: 2"
                                >

                                    <div  class="bottomInf1" style="width: 100rem;height: 30rem;background: rgba(0,0,0,0.4);z-index: 2;display: flex;justify-content: center;align-items: center;opacity: 0">
                                        <span><DeleteOutlined  style="color: white;font-size: 15rem;cursor: pointer" @click="deleteImage('image2')"></DeleteOutlined></span>
                                    </div>
                                </div>

                                <div v-else style="width: 100%;height: 70%;display: flex;flex-direction: column;justify-content: flex-start;align-items: center">
                                    <PlusOutlined style="font-size: 30rem;color:rgb(244,51,153)"></PlusOutlined>
                                    <span style="color: grey;margin-top: 10rem">添加上传图片</span>
                                </div>
                                <input :onblur="checkGoodsImage"  class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="(e)=>{temp=2;onchangeImgFun(e)}">
                            </div>

                            <div style="width: 100rem;height: 100rem;border:2rem dotted grey;background: white;display: flex;flex-direction: column;align-items: center;justify-content: flex-end">
                                <img v-if="imageFlag3" :src="image3" style="width: 100rem;height: 100rem;max-height: 100rem;z-index: 1">
                                <div
                                        @mouseenter="()=>{
                                          leaveImage(0,'bottomInf2')
                                        }"
                                        @mouseleave="()=>{
                                          enterImage(0,'bottomInf2')
                                        }"
                                        v-if="imageFlag3"
                                        style="position: absolute;width: 100rem;height: 100rem;display: flex;flex-direction: column;justify-content: flex-end;z-index: 2"
                                >

                                    <div  class="bottomInf2" style="width: 100rem;height: 30rem;background: rgba(0,0,0,0.4);z-index: 2;display: flex;justify-content: center;align-items: center;opacity: 0">
                                        <span><DeleteOutlined  style="color: white;font-size: 15rem;cursor: pointer" @click="deleteImage('image3')"></DeleteOutlined></span>
                                    </div>
                                </div>

                                <div v-else style="width: 100%;height: 70%;display: flex;flex-direction: column;justify-content: flex-start;align-items: center">
                                    <PlusOutlined style="font-size: 30rem;color:rgb(244,51,153)"></PlusOutlined>
                                    <span style="color: grey;margin-top: 10rem">添加上传图片</span>
                                </div>
                                <input :onblur="checkGoodsImage"  class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="(e)=>{temp=3;onchangeImgFun(e)}">
                            </div>

                            <div style="width: 100rem;height: 100rem;border:2rem dotted grey;background: white;display: flex;flex-direction: column;align-items: center;justify-content: flex-end">
                                <img v-if="imageFlag4" :src="image4" style="width: 100rem;height: 100rem;max-height: 100rem;z-index: 1">
                                <div
                                        @mouseenter="()=>{
                                          leaveImage(0,'bottomInf3')
                                        }"
                                        @mouseleave="()=>{
                                          enterImage(0,'bottomInf3')
                                        }"
                                        v-if="imageFlag4"
                                        style="position: absolute;width: 100rem;height: 100rem;display: flex;flex-direction: column;justify-content: flex-end;z-index: 2"
                                >

                                    <div  class="bottomInf3" style="width: 100rem;height: 30rem;background: rgba(0,0,0,0.4);z-index: 2;display: flex;justify-content: center;align-items: center;opacity: 0">
                                        <span><DeleteOutlined  style="color: white;font-size: 15rem;cursor: pointer" @click="deleteImage('image4')"></DeleteOutlined></span>
                                    </div>
                                </div>

                                <div v-else style="width: 100%;height: 70%;display: flex;flex-direction: column;justify-content: flex-start;align-items: center">
                                    <PlusOutlined style="font-size: 30rem;color:rgb(244,51,153)"></PlusOutlined>
                                    <span style="color: grey;margin-top: 10rem">添加上传图片</span>
                                </div>
                                <input :onblur="checkGoodsImage"  class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="(e)=>{temp=4;onchangeImgFun(e)}">
                            </div>

                            <div style="width: 100rem;height: 100rem;border:2rem dotted grey;background: white;display: flex;flex-direction: column;align-items: center;justify-content: flex-end">
                                <img v-if="imageFlag5" :src="image5" style="width: 100rem;height: 100rem;max-height: 100rem;z-index: 1">
                                <div
                                        @mouseenter="()=>{
                                          enterImage(0,'topInf1');
                                          leaveImage(0,'bottomInf4')
                                        }"
                                        @mouseleave="()=>{
                                          leaveImage(0,'topInf1');
                                          enterImage(0,'bottomInf4')
                                        }"
                                        v-if="imageFlag5"
                                        style="position: absolute;width: 100rem;height: 100rem;display: flex;flex-direction: column;justify-content: space-between;z-index: 2"
                                >
                                    <div class="topInf1" style="width: 100rem;height: 20rem;background: rgba(0,0,0,0.4);z-index: 2;display: flex;justify-content: center;align-items: center">
                                        <span style="color: white">商品正面图</span>
                                    </div>

                                    <div  class="bottomInf4" style="width: 100rem;height: 30rem;background: rgba(0,0,0,0.4);z-index: 2;display: flex;justify-content: center;align-items: center;opacity: 0">
                                        <span><DeleteOutlined  style="color: white;font-size: 15rem;cursor: pointer" @click="deleteImage('image5')"></DeleteOutlined></span>
                                    </div>
                                </div>

                                <div v-else style="width: 100%;height: 70%;display: flex;flex-direction: column;justify-content: flex-start;align-items: center">
                                    <PlusOutlined style="font-size: 30rem;color:rgb(244,51,153)"></PlusOutlined>
                                    <span style="color: grey;margin-top: 10rem">添加上传图片</span>
                                </div>
                                <input :onblur="checkGoodsImage"  class="upload-btn" type="file" name="image" ref="inputer" accept="image/*" @change="(e)=>{temp=5;onchangeImgFun(e)}">
                            </div>

                        </div>
                    </div>

                </div>
            </div>

            <div style="width: 400rem;height: 25rem;display: flex;justify-content: flex-start;align-items: start;margin-top: 10rem">
                <div class="left_label">

                </div>
                <div class="right_input" style="display: flex;justify-content: flex-start">
                    <span style="width:auto;height:auto;color: indianred">{{goodsInfCheck.imageCheck}}</span>
                </div>
            </div>

            <div style="width: auto;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          商品描述：
                       </span>
                </div>
                <div class="right_input">
                    <textarea :onblur="checkGoodsDescription" v-model="goods.goodsDescription" placeholder="请输入商品描述"  style="width: 500rem;height: 50rem;border: 1rem grey solid;border-radius: 2rem;margin-top: 5rem;outline-color: cornflowerblue"></textarea>
                </div>
            </div>

            <div style="width: 400rem;height: 25rem;display: flex;justify-content: flex-start;align-items: start;margin-top: 10rem">
                <div class="left_label">

                </div>
                <div class="right_input" style="display: flex;justify-content: flex-start">
                    <span style="width:auto;height:auto;color: indianred">{{goodsInfCheck.descriptionCheck}}</span>
                </div>
            </div>

            <div @mouseout="checkGoodsSpecifications" style="width: 900rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          商品规格：
                       </span>
                </div>
                <div class="right_input">
                    <div style="width: 800rem;height: auto;padding:10rem;background: white;display: flex;flex-direction: column;align-items: flex-start">
                        <span>最少添加一个商品规格,最多添加两个商品规格</span>
                        <div style="width: auto;height: auto;background: rgba(247,248,250,1);margin-top: 5rem;padding-left: 5rem">
                            <div style="width: 780rem;height: 50rem;border-bottom: 1rem solid white;border-top: 1rem solid white;display: flex;justify-content: flex-start;align-items:flex-start">
                                <select name="goodsSpecifications" v-model="goodsSpecifications.list1[0]" style="width: 200rem;border-color: whitesmoke;color: grey;position: absolute;margin-top: 10rem;min-height: 30rem;border-radius: 5rem;outline-color: cornflowerblue;z-index: 2"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                                    <option style="padding-top: 10rem;padding-bottom: 10rem" value="规格1" selected>规格1</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="尺寸">尺寸</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="净含量">净含量</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="型号">型号</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="款式">款式</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="器型">器型</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="材质">材质</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="口味">口味</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="色号">色号</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="适用人群">适用人群</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="颜色">颜色</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="容量">容量</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="花型">花型</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="尺码">尺码</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="地点">地点</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="香型">香型</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="成份">成份</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="版本">版本</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="度数">度数</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="运营商">运营商</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="属性">属性</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="重量">重量</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem"  value="地区">地区</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="套餐">套餐</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="类别">类别</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="适用年龄">适用年龄</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="功效">功效</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem"  value="品类">品类</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="时间">时间</option>
                                </select>
                            </div>
                            <div class="SpecificationsInputDiv" v-if="goodsSpecifications.list1[0]!='规格1'"  style="width: 780rem;height: auto;display: flex;justify-content:flex-start;align-items:flex-start;flex-wrap: wrap">
                                <div  v-for="index of goodsSpecifications.list1.length-1" :key="index" style="width: 33%;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
                                    <input  v-model="goodsSpecifications.list1[index]" @change="testGoodsSpecificationsItems(index)" class="specificationsInput" placeholder="请输入商品规格" type="text" style="width: 150rem;height: 30rem;border-radius: 5rem;border: 1rem whitesmoke solid;outline-color: cornflowerblue">
                                </div>

                            </div>
                        </div>

                        <div v-if="secondTypeFlag" style="width: auto;height: auto;background: rgba(247,248,250,1);margin-top: 5rem;padding-left: 5rem">
                            <div style="width: 780rem;height: 50rem;border-bottom: 1rem solid white;border-top: 1rem solid white;display: flex;justify-content: space-between;align-items:flex-start">
                                <select name="goodsSpecifications" v-model="goodsSpecifications.list2[0]" style="width: 200rem;border-color: whitesmoke;color: grey;position: absolute;margin-top: 10rem;min-height: 30rem;border-radius: 5rem;outline-color: cornflowerblue"  onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0">
                                    <option style="padding-top: 10rem;padding-bottom: 10rem" value="规格2" selected>规格2</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="尺寸">尺寸</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="净含量">净含量</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="型号">型号</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="款式">款式</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="器型">器型</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="材质">材质</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="口味">口味</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="色号">色号</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="适用人群">适用人群</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="颜色">颜色</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="容量">容量</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="花型">花型</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="尺码">尺码</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="地点">地点</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="香型">香型</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="成份">成份</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="版本">版本</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="度数">度数</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="运营商">运营商</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="属性">属性</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="重量">重量</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem"  value="地区">地区</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="套餐">套餐</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="类别">类别</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="适用年龄">适用年龄</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="功效">功效</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem"  value="品类">品类</option>
                                    <option  style="padding-top: 10rem;padding-bottom: 10rem" value="时间">时间</option>
                                </select>
                                <span style="color: steelblue;cursor: pointer;width: 100%;display: flex;justify-content: flex-end;height: 100%;align-items: center"  >
                                    <span @click="()=>{secondTypeFlag=false;goodsSpecifications.list2.splice(1,goodsSpecifications.list2.length-2);goodsSpecifications.list2[0]='规格2';goodsSpecifications.list2[1]=''}">删除规格</span>
                                </span>
                            </div>
                            <div v-if="goodsSpecifications.list2[0]!='规格2'" class="SpecificationsInputDiv"  style="width: 780rem;height: auto;display: flex;justify-content:flex-start;align-items:flex-start;flex-wrap: wrap">
                                <div  v-for="index of goodsSpecifications.list2.length-1" :key="index" style="width: 33%;height: 50rem;display: flex;justify-content: flex-start;align-items: center">
                                    <input v-model="goodsSpecifications.list2[index]" @change="testGoodsSpecifications2Items(index)" class="specificationsInput" placeholder="请输入商品规格" type="text" style="width: 150rem;height: 30rem;border-radius: 5rem;border: 1rem whitesmoke solid;outline-color: cornflowerblue">
                                </div>

                            </div>
                        </div>
                        <button @click="()=>{secondTypeFlag=true}" v-if="!secondTypeFlag" style="cursor: pointer;margin-top: 10rem;width: 100rem;height: 30rem;border: 1rem grey solid;border-radius: 5rem;display: flex;background: white;justify-content: space-evenly;align-items: center;outline-color: cornflowerblue" >
                            <plus-outlined></plus-outlined>
                            <span>添加规格</span>
                        </button>
                    </div>
                </div>
            </div>

            <div style="width: 400rem;height: 25rem;display: flex;justify-content: flex-start;align-items: start;margin-top: 10rem">
                <div class="left_label">

                </div>
                <div class="right_input" style="display: flex;justify-content: flex-start">
                    <span style="width:auto;height:auto;color: indianred">{{goodsInfCheck.goodsSpecificationsCheck}}</span>
                </div>
            </div>

            <div style="width: 900rem;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          价格及库存：
                       </span>
                </div>
                <div @mouseleave="checkGoodStokeAndPrice" class="right_input" style="width: 800rem;padding:10rem;background: white;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start">
                    <span style="color: black;">请如实填写库存信息，以确保商品可以在承诺发货时间内发出，避免可能的物流违规</span>
                    <table style="width: auto;height: auto;align-items: flex-end;margin-top: 10rem" v-if="goodsSpecifications.list2.length-2>0">
                        <tr style="width: auto;padding: 0">
                            <th style="width:auto;">{{goodsSpecifications.list1[0]}}</th>
                            <th style="width:auto;">{{goodsSpecifications.list2[0]}}</th>
                            <th style="width:auto;">库存</th>
                            <th style="width:auto;">价格</th>
                        </tr>
                        <tr  class="info" style="width: auto;height: auto;padding: 0" v-for="index1 of goodsSpecifications.list1.length-2" :key="index1">
                            <td  style="width:auto;">{{goodsSpecifications.list1[index1]}}</td>
                            <td style="padding: 0px;width: auto;">
                                <table style="width: 100%;height: 100%;">
                                    <tr v-for="index of goodsSpecifications.list2.length-2" :key="index" style="border: 0rem;">
                                        <td style="border: 0rem;">
                                            {{goodsSpecifications.list2[index]}}
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="width:auto;padding: 0rem">
                                <table style="width: auto;height: auto;">
                                    <tr v-for="index of goodsSpecifications.list2.length-2" :key="index" style="border: 0rem;border-top: 1rem whitesmoke solid;">
                                        <td style="border: 0rem;"><input type="text" v-model="goodsSpecifications1.list[(index1 - 1) * (goodsSpecifications.list2.length - 2) + index - 1].goodsStoke"   @input="goodsSpecifications1.list[(index1 - 1) * (goodsSpecifications.list2.length - 2) + index - 1].goodsStoke= Number($event.target.value.replace(/\D+/, ''))" style="border: 1rem lightgrey solid;border-radius: 4rem;width: 100rem;"  placeholder="请输入"></td>
                                    </tr>
                                </table>
                            </td>
                            <td style="width:auto;padding: 0rem">
                                <table style="width: auto;height: auto;">
                                    <tr v-for="index of goodsSpecifications.list2.length-2" :key="index" style="border: 0rem;border-top: 1rem whitesmoke solid;">
                                        <td style="border: 0rem;"><input type="text" v-model="goodsSpecifications1.list[(index1 - 1) * (goodsSpecifications.list2.length - 2) + index - 1].goodsPrice" :onkeyup="()=>{goodsSpecifications1.list[(index1 - 1) * (goodsSpecifications.list2.length - 2) + index - 1].goodsPrice=goodsSpecifications1.list[(index1 - 1) * (goodsSpecifications.list2.length - 2) + index - 1].goodsPrice.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')}"  style="border: 1rem lightgrey solid;border-radius: 4rem;" placeholder="请输入"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                    <table class="table1" style="width: auto;height: auto;align-items: flex-end;margin-top: 10rem" v-else>
                        <tr style="width: auto;">
                            <th style="width:auto;">{{goodsSpecifications.list1[0]}}</th>
                            <th style="width:auto;">库存</th>
                            <th style="width:auto;">价格</th>
                        </tr>
                        <tr  class="info" style="width: auto;" v-for="index1 of goodsSpecifications.list1.length-2" :key="index1">
                            <td  style="width:auto;">{{goodsSpecifications.list1[index1]}}</td>
                            <td style="width:auto;">
                                <input type="text" v-model="goodsSpecifications1.list[index1 - 1].goodsStoke"   @input="goodsSpecifications1.list[index1 - 1].goodsStoke = Number($event.target.value.replace(/\D+/, ''))" style="border: 1rem darkgrey solid;border-radius: 4rem;width: 100rem" placeholder="请输入">
                            </td>
                            <td style="width:auto;">
                                <input type="text" v-model="goodsSpecifications1.list[index1 - 1].goodsPrice" :onkeyup="()=>{goodsSpecifications1.list[index1 - 1].goodsPrice=goodsSpecifications1.list[index1 - 1].goodsPrice.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')}"  style="border: 1rem darkgrey solid;border-radius: 4rem;" placeholder="请输入">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div style="width: 400rem;height: 25rem;display: flex;justify-content: flex-start;align-items: start;margin-top: 10rem">
                <div class="left_label">

                </div>
                <div class="right_input" style="display: flex;justify-content: flex-start">
                    <span style="width:auto;height:auto;color: indianred">{{goodsInfCheck.goodsStokeAndPriceCheck}}</span>
                </div>
            </div>

            <div  style="width:100%;height: auto;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                <div class="left_label">
                       <span >
                           <span style="color: indianred;font-size: 20rem">*</span>
                          选择分类：
                       </span>
                </div>
                <div @mouseleave="checkGoodsType" class="right_input" style="margin-top: 10rem;">
                    <div style="width: 220rem;height: 300rem;border: 1rem grey solid;margin-right: 0rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                        <div style="width: 180rem;height: 25rem;border: 1rem grey solid;border-radius: 5rem;display: flex;justify-content: space-evenly;align-items: center">
                            <input v-model="searchItem1" placeholder="一级分类，名称检索" type="text" style="width: 130rem;height: 20rem;border: 0px;outline: none;background: none">
                            <search-outlined style="color: grey;font-size: 15rem"></search-outlined>
                        </div>
                        <div class="scoll" style="width: 200rem;height: 250rem;overflow-y: scroll;display: flex;flex-direction: column;justify-content:flex-start;align-items: center;">
                            <div  v-for="(item,index) in firstLevelList.list"  @click="getSecondLevel(item,index)" :key="index" style="width: 180rem;height:auto;display: flex;justify-content: center;align-items: flex-start;padding-top: 20rem;cursor: pointer">
                                <span class="firstLevel" style="background-color: rgb(237,247,255)" v-if="item.id==secondType.data.fatherId">
                                    <span>{{item.type}}</span>
                                </span>
                                <span class="firstLevel" v-else>
                                    <span>{{item.type}}</span>
                                </span>
                            </div>
                        </div>
                    </div>

                    <div style="width: 220rem;height: 300rem;border: 1rem grey solid;margin-right: 0rem;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                        <div v-if="secondLevelFlag" style="width: 180rem;height: 25rem;border: 1rem grey solid;border-radius: 5rem;display: flex;justify-content: space-evenly;align-items: center">
                            <input v-model="searchItem2" placeholder="二级分类，名称检索" type="text" style="width: 130rem;height: 20rem;border: 0px;outline: none;background: none">
                            <search-outlined style="color: grey;font-size: 15rem"></search-outlined>
                        </div>
                        <div v-if="secondLevelFlag" class="scoll" style="width: 200rem;height: 250rem;overflow-y: scroll;display: flex;flex-direction: column;justify-content:flex-start;align-items: center;">
                            <div  v-for="(item,index) in secondLevelList.list"  @click="getThirdLevel(item,index)" :key="index" style="width: 180rem;height:auto;display: flex;justify-content: center;align-items: flex-start;padding-top: 20rem;cursor: pointer">
                                <span class="secondLevel" style="background-color: rgb(237,247,255)" v-if="item.id==secondType.data.id">
                                    <span>{{item.type}}</span>
                                </span>
                                <span class="secondLevel" v-else>
                                    <span>{{item.type}}</span>
                                </span>
                            </div>
                        </div>
                    </div>

                    <div style="width: 220rem;height: 300rem;border: 1rem grey solid;display: flex;flex-direction: column;justify-content: space-evenly;align-items: center">
                        <div v-if="thirdLevelFlag" style="width: 180rem;height: 25rem;border: 1rem grey solid;border-radius: 5rem;display: flex;justify-content: space-evenly;align-items: center">
                            <input v-model="searchItem3" placeholder="三级分类，名称检索" type="text" style="width: 130rem;height: 20rem;border: 0px;outline: none;background: none">
                            <search-outlined style="color: grey;font-size: 15rem"></search-outlined>
                        </div>
                        <div v-if="thirdLevelFlag" class="scoll" style="width: 200rem;height: 250rem;overflow-y: scroll;display: flex;flex-direction: column;justify-content:flex-start;align-items: center;">
                            <div  v-for="(item,index) in thirdLevelList.list" @click="setType(item,index)" :key="index" style="width: 180rem;height:auto;display: flex;justify-content: center;align-items: flex-start;padding-top: 20rem;cursor: pointer">
                                <span class="thirdLevel" style="background-color: rgb(237,247,255)" v-if="item.id==thirdType.data.id">
                                    <span>{{item.type}}</span>
                                </span>
                                <span class="thirdLevel" v-else>
                                    <span>{{item.type}}</span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div style="width: 400rem;height: 25rem;display: flex;justify-content: flex-start;align-items: start;margin-top: 10rem">
                <div class="left_label">

                </div>
                <div class="right_input" style="display: flex;justify-content: flex-start">
                    <span style="width:auto;height:auto;color: indianred">{{goodsInfCheck.typeCheck}}</span>
                </div>
            </div>

            <div style="height: 40rem;display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 20rem">
                <div class="left_label">

                </div>
                <div class="right_input">
                    <button @click="releaseAGoods">发布</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import {PlusOutlined,DeleteOutlined,SearchOutlined} from '@ant-design/icons-vue'
  import {ref,reactive,watch} from "vue";
  import getFirstLevelType from "../../../Internet/home/type/getFirstLevelType";
  import getSecondLevelType from "../../../Internet/home/type/getSecondLevelType";
  import getThirdLevelType from "../../../Internet/home/type/getThirdLevelType";
  import axios from "axios";
  import selectByGoodsId from "../../../Internet/home/goods/select/selectByGoodsId";
  import getThirdType from "../../../Internet/home/type/getThirdType";
  import selectSecondLevel from "../../../Internet/home/type/selectSecondLevel";
  import getGoodsSpecificationsInf from "../../../Internet/home/goodsSpecifications/select/getGoodsSpecificationsInf";
  import {imageToBase64} from "../../../assets/functions/imageToBase64";
  import {base64ToFile} from "../../../assets/functions/base64ToFile";
  import modifyGoodsSpecifications from "../../../Internet/home/goodsSpecifications/update/modifyGoodsSpecifications";
  export default {
    name: "modifyGoods",
    components:{
      PlusOutlined,
      DeleteOutlined,
      SearchOutlined
    },
    setup(){
      let goods=reactive({
        goodsTitle:'',
        shopId:(JSON.parse(sessionStorage.getItem('shop')).shopId),
        goodsTypeId:'',
        goodsBrand:'',
        goodsDescription:'',
        goodsSpecifications:''
      })
      let search = search || window.location.search.substr(1) || window.location.hash.split("?")[1];
      let reg1 = new RegExp("(^|&)" + 'goodsId' + "=([^&]*)(&|$)");
      let r1 = search.match(reg1);

      let image=['','','','',''];

      console.log(image[0]);

      let goodsSpecifications1=reactive({
        list:[]
      })

      let goodsInfCheck=reactive({
        titleCheck:'',
        imageCheck:'',
        typeCheck:'',
        goodsBrandCheck:'',
        descriptionCheck:'',
        goodsSpecificationsCheck:'',
        goodsStokeAndPriceCheck:'',
      })

      let secondTypeFlag=ref(false)

      let goodsSpecifications=reactive({
        list1:['规格1',''],
        list2:['规格2','']
      })

      function setNewGoodsSpecifications1(){
        if(goodsSpecifications.list2.length-2>0) {
          for (let i = 1; i < goodsSpecifications.list1.length - 1; i++) {
            for (let j = 1; j < goodsSpecifications.list2.length-1; j++) {
              let goodsSpecification
              if(goodsSpecifications1.list[(i - 1) * (goodsSpecifications.list2.length - 2) + j - 1]!==null&&goodsSpecifications1.list[(i - 1) * (goodsSpecifications.list2.length - 2) + j - 1]!==undefined) {
                goodsSpecification = reactive({
                  goodsType1: goodsSpecifications.list1[0]+':'+goodsSpecifications.list1[i],
                  goodsType2: goodsSpecifications.list2[0]+':'+goodsSpecifications.list2[j],
                  goodsPrice: goodsSpecifications1.list[(i - 1) * (goodsSpecifications.list2.length - 2) + j - 1].goodsPrice,
                  goodsStoke: goodsSpecifications1.list[(i - 1) * (goodsSpecifications.list2.length - 2) + j - 1].goodsStoke,
                  goodsId:goodsSpecifications1.list[(i - 1) * (goodsSpecifications.list2.length - 2) + j - 1].goodsId,
                  specificationsId:goodsSpecifications1.list[(i - 1) * (goodsSpecifications.list2.length - 2) + j - 1].specificationsId
                })
              }else {
                goodsSpecification = reactive({
                  goodsType1: goodsSpecifications.list1[0]+':'+goodsSpecifications.list1[i],
                  goodsType2: goodsSpecifications.list2[0]+':'+goodsSpecifications.list2[j],
                  goodsPrice: '',
                  goodsStoke: '',
                  goodsId:null,
                  specificationsId:null
                })
              }

              console.log(goodsSpecification);
              goodsSpecifications1.list[(i - 1) * (goodsSpecifications.list2.length - 2) + j - 1]= goodsSpecification
            }
          }
        }else {
          for (let i = 1; i < goodsSpecifications.list1.length - 1; i++) {
            let goodsSpecification
            if(goodsSpecifications1.list[i-1]!==null&&goodsSpecifications1.list[i-1]!==undefined){
              goodsSpecification = reactive({
                goodsType1: goodsSpecifications.list1[0]+':'+goodsSpecifications.list1[i],
                goodsPrice: goodsSpecifications1.list[i-1].goodsPrice,
                goodsStoke: goodsSpecifications1.list[i-1].goodsStoke,
                goodsId:goodsSpecifications1.list[i-1].goodsId,
                specificationsId:goodsSpecifications1.list[i-1].specificationsId
              })
            }else {
              goodsSpecification = reactive({
                goodsType1: goodsSpecifications.list1[0]+':'+goodsSpecifications.list1[i],
                goodsPrice:'',
                goodsStoke: '',
                goodsId:null,
                specificationsId:null
              })
            }
            goodsSpecifications1.list[i - 1] = goodsSpecification
          }
        }
        console.log(goodsSpecifications1);
      }


      let image1=ref('')
      let image2=ref('')
      let image3=ref('')
      let image4=ref('')
      let image5=ref('')


      let imageFlag1=ref(false)
      let imageFlag2=ref(false)
      let imageFlag3=ref(false)
      let imageFlag4=ref(false)
      let imageFlag5=ref(false)

      let temp=ref(0)

      let secondLevelFlag=ref(false)
      let thirdLevelFlag=ref(false)

      let searchItem1=ref('')
      let searchItem2=ref('')
      let searchItem3=ref('')

      //进入图片之后，隐藏上面的div
      function enterImage(temp,className){
        let divs=document.getElementsByClassName(className)
        divs[temp].style.opacity='0'
      }

      //离开图片之后，显示上面的div
      function leaveImage(temp,className){
        let divs=document.getElementsByClassName(className)
        divs[temp].style.opacity='1'
      }


      function deleteImage(item){
        if(item=='image1'){
          image1.value=''
          imageFlag1.value=false
          image[0]=''
        }else if(item=='image2'){
          image2.value=''
          imageFlag2.value=false
          image[1]=''
        }else if(item=='image3'){
          image3.value=''
          imageFlag3.value=false
          image[2]=''
        }else if(item=='image4'){
          image4.value=''
          imageFlag4.value=false
          image[3]=''
        }else if(item=='image5'){
          image5.value=''
          imageFlag5.value=false
          image[4]=''
        }
      }

      let flag=false

      function onchangeImgFun (e) {

        let file = e.target.files[0]


        if(file.name==image[0].name||file.name==image[1].name||file.name==image[2].name||file.name==image[3].name||file.name==image[4].name){
          flag=false
          goodsInfCheck.imageCheck='请选择不同的照片'
        }else {
          goodsInfCheck.imageCheck=''
          flag=true
        }

        event.preventDefault()

        // base64方法 2
        let reader = new FileReader()
        reader.readAsDataURL(file) // 读出 base64
        reader.onloadend = function () {
          // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
          let dataURL = reader.result
          if(temp.value==1){
            image1.value = dataURL
            imageFlag1.value=true
            image[0]=file
          }else if(temp.value==2){
            image2.value = dataURL
            imageFlag2.value=true
            image[1]=file
          }else if(temp.value==3){
            image3.value = dataURL
            imageFlag3.value=true
            image[2]=file
          }else if(temp.value==4){
            image4.value = dataURL
            imageFlag4.value=true
            image[3]=file
          }else if(temp.value==5){
            image5.value = dataURL
            imageFlag5.value=true
            image[4]=file
          }
        }
      }

      let firstLevelList=reactive({
        list:[]
      })
      let firstLevelList1=[]

      let secondLevelList=reactive({
        list:[]
      })
      let secondLevelList1=[]

      let thirdLevelList=reactive({
        list:[]
      })
      let thirdLevelList1=[]

      function search1(item1){
        firstLevelList.list=firstLevelList1.filter(res=>{
          return res.type.indexOf(item1)!==-1
        })
      }

      function search2(item1){
        secondLevelList.list=secondLevelList1.filter(res=>{
          return res.type.indexOf(item1)!==-1
        })
      }

      function search3(item1){
        thirdLevelList.list=thirdLevelList1.filter(res=>{
          return res.type.indexOf(item1)!==-1
        })
      }

      let thirdType=reactive({
        data:{}
      })
      let secondType=({
        data:{}
      })

      function handleImgToBase64(url,index,fileName) {

        let image1 = new Image();
        image1.crossOrigin = '';
        image1.src = url;
        image1.onload=function () {
          let base64 = imageToBase64(image1); //图片转base64

          let file = base64ToFile(base64, fileName); //base64转File

          // 根据自身需求调整【因个人项目逻辑不一样，这里使用回调函数】

          image[index]=file

          return file;
        }
      }


      getGoodsSpecificationsInf(unescape(r1[2])).then(res=>{
        goodsSpecifications1.list=res.data.goodsSpecificationsList
        goodsSpecifications.list1[0]=res.data.type1Item
        for(let i=0;i<res.data.type1List.length;i++){
          goodsSpecifications.list1[i+1]=res.data.type1List[i]
        }
        goodsSpecifications.list1[goodsSpecifications.list1.length]=''

        if(res.data.type2Item==''){
          secondTypeFlag.value=false
        }else {
          secondTypeFlag.value=true
          goodsSpecifications.list2[0]=res.data.type2Item
          for(let i=0;i<res.data.type2List.length;i++){
            goodsSpecifications.list2[i+1]=res.data.type2List[i]
          }
          goodsSpecifications.list2[goodsSpecifications.list2.length]=''
        }
      })

      selectByGoodsId(unescape(r1[2])).then(res=>{
        goods.goodsTitle=res.data.goodsTitle
        goods.goodsTypeId=res.data.goodsTypeId
        goods.goodsId=res.data.goodsId
        goods.goodsBrand=res.data.goodsBrand
        goods.goodsDescription=res.data.goodsDescription
        goods.goodsSpecifications=res.data.goodsSpecifications


        image1.value=require("../../../assets/img/"+res.data.goodsImage1)
        image2.value=require("../../../assets/img/"+res.data.goodsImage2)
        image3.value=require("../../../assets/img/"+res.data.goodsImage3)
        image4.value=require("../../../assets/img/"+res.data.goodsImage4)
        image5.value=require("../../../assets/img/"+res.data.goodsImage5)

        handleImgToBase64(image1.value,0,res.data.goodsImage1)
        handleImgToBase64(image2.value,1,res.data.goodsImage2)
        handleImgToBase64(image3.value,2,res.data.goodsImage3)
        handleImgToBase64(image4.value,3,res.data.goodsImage4)
        handleImgToBase64(image5.value,4,res.data.goodsImage5)

        imageFlag1.value=true
        imageFlag2.value=true
        imageFlag3.value=true
        imageFlag4.value=true
        imageFlag5.value=true

        getThirdType(goods.goodsTypeId).then(res=>{
          thirdType.data=res.data
        }).then(()=>{
          getThirdLevelType(thirdType.data.fatherId).then(res=>{
            thirdLevelList.list=res.data
            thirdLevelList1=res.data
          })
        }).then(()=>{
          selectSecondLevel(thirdType.data.fatherId).then(res=>{
            secondType.data=res.data
          }).then(()=>{
            getSecondLevelType(secondType.data.fatherId).then((res)=>{
              secondLevelList.list=res.data
              secondLevelList1=res.data
              secondLevelFlag.value=true
              thirdLevelFlag.value=true
            })
          })
        })
      })

      function testGoodsSpecificationsItems(index){
        if(goodsSpecifications.list1[index]!=''&&goodsSpecifications.list1[index+1]==undefined){
          goodsSpecifications.list1[index+1]=''
        }else if(goodsSpecifications.list1[index]==''){
          if(index<=goodsSpecifications.list1.length){
            goodsSpecifications.list1.splice(index,1)
          }
        }

        setNewGoodsSpecifications1()
      }

      function testGoodsSpecifications2Items(index){
        if(goodsSpecifications.list2[index]!=''&&goodsSpecifications.list2[index+1]==undefined){
          goodsSpecifications.list2[index+1]=''
        }else if(goodsSpecifications.list2[index]==''){
          if(index<=goodsSpecifications.list2.length){
            goodsSpecifications.list2.splice(index,1)
          }
        }

        setNewGoodsSpecifications1()
      }

      watch(
          searchItem1,
          () => {
            search1(searchItem1.value)
          }
      )


      watch(
          searchItem2,
          () => {
            search2(searchItem2.value)
          }
      )

      watch(
          searchItem3,
          () => {
            search3(searchItem3.value)
          }
      )

      getFirstLevelType().then(res=>{
        firstLevelList.list=res.data
        firstLevelList1=res.data
      })

      function getSecondLevel(item,index){
        let divs=document.getElementsByClassName("firstLevel")

        goods.goodsTypeId=''

        for(let i=0;i<firstLevelList.list.length;i++){
          divs[i].style.backgroundColor="rgba(237,247,255,0)"
        }
        divs[index].style.backgroundColor="rgb(237,247,255)"

        let divs1=document.getElementsByClassName("secondLevel")


        for(let i=0;i<secondLevelList.list.length;i++){
          divs1[i].style.backgroundColor="rgba(237,247,255,0)"
        }

        getSecondLevelType(item.id).then(res=>{
          secondLevelList.list=res.data
          secondLevelList1=res.data
          thirdLevelFlag.value=false
        })
        secondLevelFlag.value=true
      }

      function getThirdLevel(item,index){
        thirdLevelFlag.value=true

        goods.goodsTypeId=''

        let divs1=document.getElementsByClassName("thirdLevel")

        if(divs1.length>=1){
          for(let i=0;i<thirdLevelList.list.length;i++){
            divs1[i].style.backgroundColor="rgba(237,247,255,0)"
          }
        }


        let divs=document.getElementsByClassName("secondLevel")


        for(let i=0;i<secondLevelList.list.length;i++){
          divs[i].style.backgroundColor="rgba(237,247,255,0)"
        }
        divs[index].style.backgroundColor="rgb(237,247,255)"


        getThirdLevelType(item.id).then(res=>{
          thirdLevelList.list=res.data
          thirdLevelList1=res.data
        })
        thirdLevelFlag.value=true
      }

      function setType(item,index) {
        goods.goodsTypeId=item.id
        let divs=document.getElementsByClassName("thirdLevel")

        for(let i=0;i<thirdLevelList.list.length;i++){
          divs[i].style.backgroundColor="rgba(237,247,255,0)"
        }
        divs[index].style.backgroundColor="rgb(237,247,255)"
      }

      //获取字符串长度
      function getStrLength(str) {
        //1、定义计数器
        let len=0,code=0;
        //2、遍历该字符串
        for(let i=0;i<str.length;i++)
        {
          code=str.charCodeAt(i)
          if (code>=0&&code<=127) {
            len+=1;
          }else{
            len+=2;
          }
        }

        return len
      }

      function checkGoodsTitle() {
        if(goods.goodsTitle==''){
          goodsInfCheck.titleCheck='请输入商品标题'
          flag=false
        }else if(getStrLength(goods.goodsTitle)>60){
          goodsInfCheck.titleCheck='最多输入30个汉字（60个字符）'
          flag=false
        }else{
          goodsInfCheck.titleCheck=''
          flag=true
        }
      }

      function checkGoodsBrand() {
        if(goods.goodsBrand==''){
          goodsInfCheck.goodsBrandCheck='请输入商品品牌'
          flag=false
        }else if(getStrLength(goods.goodsBrand)>30){
          goodsInfCheck.goodsBrandCheck='最多输入15个汉字（60个字符）'
          flag=false
        }else{
          goodsInfCheck.goodsBrandCheck=''
          flag=true
        }
      }

      function checkGoodsImage() {
        if((image[0]==''||image[1]==''||image[2]==''||image[3]==''||image[4]=='')){
          if(goodsInfCheck.imageCheck!='请选择不同的照片'){
            goodsInfCheck.imageCheck='需要上传五张照片，请上传完整'
            flag=false
          }
        }else {
          if(goodsInfCheck.imageCheck!='请选择不同的照片') {
            goodsInfCheck.imageCheck = ''
            flag = true
          }
        }
      }

      function checkGoodsPrice() {

        if(goods.goodsPrice==''){
          goodsInfCheck.priceCheck='请输入商品价格'
          flag=false
        }else if(!((Number(goods.goodsPrice)>=0)&&(Number(goods.goodsPrice)===Number(goods.goodsPrice)))) {
          goodsInfCheck.priceCheck = '请输入正确的商品价格'
          flag=false
        }else {
          goodsInfCheck.priceCheck = ''
          flag=true
        }
      }

      function checkGoodsType() {
        if(goods.goodsTypeId==''){
          goodsInfCheck.typeCheck='请选择商品类型（需选择完整）'
          flag=false
        }else {
          goodsInfCheck.typeCheck=''
          flag=true
        }
      }

      function checkGoodsDescription(){
        if(goods.goodsDescription==''){
          goodsInfCheck.descriptionCheck='请输入商品描述'
          flag=false
        }else if(getStrLength(goods.goodsDescription)>=200){
          goodsInfCheck.descriptionCheck='商品描述过长，最多输入100个汉字（200个字符）'
          flag=false
        }else {
          goodsInfCheck.descriptionCheck=''
          flag=true
        }
      }

      function isRepeat(arr){
        let  hash = {};
        for(let i in arr) {
          if(hash[arr[i]]) {
            return true;
          }
          hash[arr[i]] = true;
        }
        return false;
      }

      function checkGoodsSpecifications() {

        if(goodsSpecifications.list1[1]==''){
          goodsInfCheck.goodsSpecificationsCheck='请选择商品规格'
          flag=false
        }
        else if(isRepeat(goodsSpecifications.list1)||isRepeat(goodsSpecifications.list2)||(goodsSpecifications.list2[0]==goodsSpecifications.list1[0])){
          goodsInfCheck.goodsSpecificationsCheck='请勿输入相同的商品规格'
          flag=false
        }
        else {
          goodsInfCheck.goodsSpecificationsCheck=''
          flag=true
        }
      }

      function checkGoodStokeAndPrice() {
        let flag1=true
        for(let i=0;i<goodsSpecifications1.list.length;i++) {
          if(goodsSpecifications1.list[i].goodsStock==''||goodsSpecifications1.list[i].goodsPrice==''){
            goodsInfCheck.goodsStokeAndPriceCheck='请输入完整信息'
            flag1=false
            flag=false
            break
          }
        }

        if(flag1==true){
          flag=true
          goodsInfCheck.goodsStokeAndPriceCheck=''
        }
      }

      function releaseAGoods() {
        flag=true
        checkGoodsType()
        checkGoodsDescription()
        checkGoodsPrice()
        checkGoodsTitle()
        checkGoodsSpecifications()

        if(flag==true){
          let formData = new FormData();

          formData.append('goodsId',goods.goodsId);//键名要和后台一致
          formData.append('goodsTitle',goods.goodsTitle);//键名要和后台一致
          formData.append('goodsDescription',goods.goodsDescription);//键名要和后台一致
          formData.append('goodsTypeId',goods.goodsTypeId);//键名要和后台一致
          formData.append("shopId",goods.shopId)
          formData.append('goodsBrand',goods.goodsBrand)

          formData.append("image1",image[0]);
          formData.append("image2",image[1]);
          formData.append("image3",image[2]);
          formData.append("image4",image[3]);
          formData.append("image5",image[4]);

          goods.goodsSpecifications=goodsSpecifications.list1[0]+':'
          for(let i=1;i<goodsSpecifications.list1.length;i++){
            if(goodsSpecifications.list1[i]!==''){
              goods.goodsSpecifications+=goodsSpecifications.list1[i]+' '
            }
          }

          if(secondTypeFlag.value==true){
            if(goodsSpecifications.list2[0]!=''){
              goods.goodsSpecifications+=';'+goodsSpecifications.list2[0]+':'
              for(let i=1;i<goodsSpecifications.list2.length;i++){
                if(goodsSpecifications.list2[i]!==''){
                  goods.goodsSpecifications+=goodsSpecifications.list2[i]+' '
                }
              }
            }
          }

          formData.append("goodsSpecifications",goods.goodsSpecifications);



          const instance=axios.create({
            withCredentials: true
          })
          instance.post('/servlet/goods/modifyGoods',formData).then(res=>{
            console.log(res);
            if(res.data.code==0){
              for(let i=0;i<goodsSpecifications1.list.length;i++){
                goodsSpecifications1.list[i].goodsId=res.data.data.goodsId
              }
              console.log(goodsSpecifications1.list);

              modifyGoodsSpecifications(goodsSpecifications1).then(res=>{
                console.log(res);
                if(res.code==0) {
                  alert('修改商品成功')
                }
              })


              // goods.goodsTitle='';
              // goods.goodsTypeId='';
              // goods.goodsStock='';
              // goods.goodsPrice='';
              // goods.goodsDescription='';
              // goods.goodsSpecifications=''
            }
            // sessionStorage.setItem('user',JSON.stringify(res.data.user))
          })
        }
      }


      return{
        image1,
        image2,
        image3,
        image4,
        image5,
        onchangeImgFun,
        checkGoodStokeAndPrice,
        imageFlag1,
        imageFlag2,
        imageFlag3,
        imageFlag4,
        imageFlag5,
        temp,
        enterImage,
        leaveImage,
        deleteImage,
        testGoodsSpecificationsItems,
        testGoodsSpecifications2Items,
        goodsSpecifications,
        firstLevelList,
        secondLevelList,
        thirdLevelList,
        secondLevelFlag,
        thirdLevelFlag,
        getSecondLevel,
        getThirdLevel,
        searchItem1,
        searchItem2,
        searchItem3,
        setType,
        goods,
        goodsInfCheck,
        checkGoodsDescription,
        checkGoodsImage,
        checkGoodsTitle,
        checkGoodsType,
        releaseAGoods,
        checkGoodsBrand,
        secondTypeFlag,
        checkGoodsSpecifications,
        goodsSpecifications1,
        thirdType,
        secondType
      }
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
        width: auto;
        height: auto;
        display: flex;
        justify-content: flex-start;
        align-items: flex-start;
    }

    .upload-btn{
        position: absolute;
        opacity: 0;
        width: 100rem;
        height: 100rem;
        cursor: pointer;
        /* 通过定位把input放在img标签上面，通过不透明度隐藏 */
    }

    /* 设置滚动条的样式 */
    .scoll::-webkit-scrollbar {
        width:12rem;
        background-color: #eff0f4;
    }

    /* 滚动槽 */
    .scoll::-webkit-scrollbar-track {
        border-radius:5rem;
        width: 20rem;
    }

    /* 滚动条滑块 */
    .scoll::-webkit-scrollbar-thumb {
        border-radius:10rem;
        width: 20rem;
        background:grey;
    }
    .firstLevel{
        width: 100%;
        display: flex;
        justify-content: flex-start;
    }
    .secondLevel{
        width: 100%;
        display: flex;
        justify-content: flex-start;
    }
    .thirdLevel{
        width: 100%;
        display: flex;
        justify-content: flex-start;
    }
    input{
        outline-color: cornflowerblue;
        padding-left: 5rem;
    }
    th{
        border: 1rem whitesmoke solid;
        padding-left: 10rem;
        padding-right: 20rem;
        padding-bottom: 15rem;
        padding-top: 15rem;
        width: auto;
        height: auto;
        background: rgba(247,248,250,1);
        font-size: 12rem;
        color: darkgrey;
    }
    td {
        border: 1rem whitesmoke solid;
        padding-left: 5rem;
        padding-right: 20rem;
        padding-bottom: 15rem;
        padding-top: 15rem;
        height: auto;
        width: auto;
    }
</style>