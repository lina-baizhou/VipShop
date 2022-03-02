package com.lina.market_project.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.factory.Factory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lina.market_project.pojo.*;
import com.lina.market_project.service.impl.*;
import lombok.SneakyThrows;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.bouncycastle.jcajce.provider.digest.SHA1;
import org.springframework.web.bind.annotation.*;

import javax.annotation.RegEx;
import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/shop-order")
public class ShopOrderController {
    @Resource
    AliPayServiceImpl aliPayService;

    @Resource
    ShopOrderServiceImpl shopOrderService;

    @Resource
    ShoppingCartServiceImpl shoppingCartService;

    @Resource
    GoodsSpecificationsServiceImpl goodsSpecificationsService;

    @Resource
    HarvestAddressServiceImpl harvestAddressService;

    @Resource
    GoodsServiceImpl goodsService;

    @Resource
    ShopServiceImpl shopService;

    @Resource
    UserServiceImpl userService;

    @Resource
    ShopSalesReportServiceImpl shopSalesReportService;

    @Resource
    ReturnOrderServiceImpl returnOrderService;

    @Resource
    ExchangeOrderServiceImpl exchangeOrderService;

    @SneakyThrows
    @PostMapping("/payForOrder")
    public String payForOrder(@RequestBody ShoppingCartGroupByShopList shoppingCartGroupByShopList){
        float allAccount=0;
        List<Integer> list=new ArrayList<>();
        LocalDateTime localDateTime=LocalDateTime.now();
        for(ShoppingCartGroupByShop shoppingCartGroupByShop:shoppingCartGroupByShopList.getShoppingCartGroupByShopList()){
                ShopOrder shopOrder=new ShopOrder();
                shopOrder.setGoodsCount(JSONObject.toJSONString(shoppingCartGroupByShop.getGoodsCountList()));
                shopOrder.setGoodsRealCount(JSONObject.toJSONString(shoppingCartGroupByShop.getGoodsCountList()));

                float totalPrice=0;
                for(int i=0;i<shoppingCartGroupByShop.getGoodsCountList().size();i++) {
                    allAccount+=shoppingCartGroupByShop.getGoodsSpecificationsList().get(i).getGoodsPrice()*shoppingCartGroupByShop.getGoodsCountList().get(i);
                    totalPrice+=shoppingCartGroupByShop.getGoodsSpecificationsList().get(i).getGoodsPrice()*shoppingCartGroupByShop.getGoodsCountList().get(i);


                    //删除购物车里该商品
                    QueryWrapper<ShoppingCart> shoppingCartQueryWrapper=new QueryWrapper<>();
                    shoppingCartQueryWrapper.eq("user_id",shoppingCartGroupByShopList.getHarvestAddress().getUserId()).eq("specifications_id",shoppingCartGroupByShop.getGoodsSpecificationsList().get(i).getSpecificationsId()).eq("goods_id",shoppingCartGroupByShop.getGoodsSpecificationsList().get(i).getGoodsId());

                    shoppingCartService.remove(shoppingCartQueryWrapper);
                }
                if(totalPrice<shoppingCartGroupByShop.getShop().getFreeDelivery()){
                    allAccount+=shoppingCartGroupByShop.getShop().getFreight();
                    totalPrice+=shoppingCartGroupByShop.getShop().getFreight();
                }

                List<Integer> goodsIdList=new ArrayList<>();
                for(Goods goods:shoppingCartGroupByShop.getGoodsList()){
                    goodsIdList.add(goods.getGoodsId());
                }
                shopOrder.setGoodsId(JSONObject.toJSONString(goodsIdList));

                List<Integer> specificationsList=new ArrayList<>();
                for(GoodsSpecifications goodsSpecifications:shoppingCartGroupByShop.getGoodsSpecificationsList()){
                    specificationsList.add(goodsSpecifications.getSpecificationsId());
                }
                shopOrder.setSpecificationsId(JSONObject.toJSONString(specificationsList));


                shopOrder.setGoodsSalesVolume(totalPrice);

                shopOrder.setHarvestId(shoppingCartGroupByShopList.getHarvestAddress().getHarvestId());

                shopOrder.setShopId(shoppingCartGroupByShop.getShop().getShopId());

                shopOrder.setTime(LocalDateTime.now());

                shopOrder.setUserId(shoppingCartGroupByShopList.getHarvestAddress().getUserId());

                shopOrder.setOrderState("待付款");

                //支付订单号
                shopOrder.setTradeNo(String.valueOf(localDateTime));

                shopOrderService.save(shopOrder);

                list.add(shopOrder.getOrderNumber());
        }
        return aliPayService.aliPay("向商家付款",String.valueOf(localDateTime),allAccount,shoppingCartGroupByShopList.getHarvestAddress().getUserId(),list,shoppingCartGroupByShopList.getUrl());
    }

    @SneakyThrows
    @GetMapping("/payOrder")
    public String payOrder(@RequestParam Integer orderNumber,@RequestParam String url){
        List<Integer> list=new ArrayList<>();
        QueryWrapper<ShopOrder> shopOrderQueryWrapper=new QueryWrapper<>();
        ShopOrder order=shopOrderService.getById(orderNumber);

        list.add(order.getOrderNumber());

        String tradeNo=String.valueOf(LocalDateTime.now());
        order.setTradeNo(tradeNo);

        UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
        shopOrderUpdateWrapper.eq("order_number",order.getOrderNumber()).set("trade_no",tradeNo);
        shopOrderService.update(shopOrderUpdateWrapper);

        Shop shop=shopService.getById(order.getShopId());

        float allAccount=order.getGoodsSalesVolume()>=shop.getFreeDelivery()?order.getGoodsSalesVolume():order.getGoodsSalesVolume()+shop.getFreight();
        return aliPayService.aliPay("向商家付款",tradeNo,allAccount,order.getUserId(),list,url);
    }

    @PostMapping("/changeOrderToPaid")
    public Result<?> changeOrderToPaid (@RequestBody List<Integer> list) {
        for(Integer orderNumber:list){
            UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
            shopOrderUpdateWrapper.eq("order_number",orderNumber).set("order_state","待发货");

            shopOrderService.update(shopOrderUpdateWrapper);
        }

        return Result.success();
    }

    @GetMapping("/queryAllOrderListByUserId")
    public Result<?> queryAllOrderListByUserId(@RequestParam Integer userId){
        QueryWrapper<ShopOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).orderByDesc("time").eq("deleted",0);

        List<ShopOrder> shopOrderList=shopOrderService.list(queryWrapper);

        List<ShoppingOrderGroupByShop> shoppingOrderGroupByShopList=new ArrayList<>();

        for(ShopOrder shopOrder:shopOrderList){
            ShoppingOrderGroupByShop shoppingOrderGroupByShop = new ShoppingOrderGroupByShop();

            shoppingOrderGroupByShop.setShopOrder(shopOrder);

            //---------------查询店铺信息------------------
            QueryWrapper<Shop> shopQueryWrapper=new QueryWrapper<>();
            shopQueryWrapper.eq("shop_id",shopOrder.getShopId());
            shoppingOrderGroupByShop.setShop(shopService.getOne(shopQueryWrapper));

            //---------------查询商品信息---------------
            JSONArray goodsIdArray=JSONObject.parseArray(shopOrder.getGoodsId());
            List<Goods> goodsList=new ArrayList<>();
            for(int i = 0; i< goodsIdArray.size(); i++){
                QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
                goodsQueryWrapper.eq("goods_id",goodsIdArray.get(i));
                goodsList.add(goodsService.getOne(goodsQueryWrapper));
            }
            shoppingOrderGroupByShop.setGoodsList(goodsList);

            //-----------查询商品规格信息----------------
            JSONArray goodsSpecificationsArray=JSONArray.parseArray(shopOrder.getSpecificationsId());
            List<GoodsSpecifications> goodsSpecificationsList=new ArrayList<>();
            for(int i=0;i<goodsSpecificationsArray.size();i++){
                QueryWrapper<GoodsSpecifications> goodsSpecificationsQueryWrapper=new QueryWrapper<>();
                goodsSpecificationsQueryWrapper.eq("specifications_id",goodsSpecificationsArray.get(i));
                goodsSpecificationsList.add(goodsSpecificationsService.getOne(goodsSpecificationsQueryWrapper));
            }
            shoppingOrderGroupByShop.setGoodsSpecificationsList(goodsSpecificationsList);

            //-----------查询商品数量信息----------------
            List<Integer> list=JSONArray.parseArray(shopOrder.getGoodsCount(),Integer.class);
            shoppingOrderGroupByShop.setGoodsCountList(list);

            shoppingOrderGroupByShopList.add(shoppingOrderGroupByShop);
        }

        return Result.success(shoppingOrderGroupByShopList);
    }

    @GetMapping("/queryOrderByOrderNumber")
    public Result<?> queryOrderByOrderNumber(@RequestParam Integer orderNumber){
        QueryWrapper<ShopOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_number",orderNumber).orderByDesc("time").eq("deleted",0);

        ShopOrder shopOrder=shopOrderService.getOne(queryWrapper);

       //---------------查询店铺信息------------------
        QueryWrapper<Shop> shopQueryWrapper=new QueryWrapper<>();
        shopQueryWrapper.eq("shop_id",shopOrder.getShopId());

        //---------------查询商品信息---------------
        JSONArray goodsIdArray=JSONObject.parseArray(shopOrder.getGoodsId());
        List<Goods> goodsList=new ArrayList<>();
        for(int i = 0; i< goodsIdArray.size(); i++){
            QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
            goodsQueryWrapper.eq("goods_id",goodsIdArray.get(i));
            goodsList.add(goodsService.getOne(goodsQueryWrapper));
        }


        //-----------查询商品规格信息----------------
        JSONArray goodsSpecificationsArray=JSONArray.parseArray(shopOrder.getSpecificationsId());
        List<GoodsSpecifications> goodsSpecificationsList=new ArrayList<>();
        for(int i=0;i<goodsSpecificationsArray.size();i++){
            QueryWrapper<GoodsSpecifications> goodsSpecificationsQueryWrapper=new QueryWrapper<>();
            goodsSpecificationsQueryWrapper.eq("specifications_id",goodsSpecificationsArray.get(i));
            goodsSpecificationsList.add(goodsSpecificationsService.getOne(goodsSpecificationsQueryWrapper));
        }


        //-----------查询商品数量信息----------------
        List<Integer> list=JSONArray.parseArray(shopOrder.getGoodsRealCount(),Integer.class);
        List<Integer> list1=JSONArray.parseArray(shopOrder.getGoodsCount(),Integer.class);

        //-----------退款、换货信息------------------
        List<Integer> returnList=JSONArray.parseArray(shopOrder.getReturnFlag(),Integer.class);
        List<Integer> changeList=JSONArray.parseArray(shopOrder.getChangeFlag(),Integer.class);

        //-------------店家信息------------------
        User user=userService.getById(shopService.getOne(shopQueryWrapper).getUserId());


        HashMap<String,Object> map=new HashMap<>();
        map.put("shop",shopService.getOne(shopQueryWrapper));
        map.put("order",shopOrder);
        map.put("harvest",harvestAddressService.getById(shopOrder.getHarvestId()));
        map.put("countList",list);
        map.put("beforeCountList",list1);
        map.put("goodsList",goodsList);
        map.put("specificationsList",goodsSpecificationsList);
        map.put("returnList",returnList);
        map.put("changeList",changeList);
        map.put("seller",user);

        return Result.success(map);
    }

    @GetMapping("/queryAllOrderListByUserIdAndState")
    public Result<?> queryAllOrderListByUserIdAndState(@RequestParam Integer userId,@RequestParam String orderState){
        System.out.println(orderState);
        QueryWrapper<ShopOrder> queryWrapper=new QueryWrapper<>();

        if(orderState.equals("退款中")){
            queryWrapper.eq("user_id",userId).and(wrapper->wrapper.like("return_flag",0).or().like("return_flag",2).or().like("return_flag",3)).orderByDesc("time").eq("deleted",0);
        }else {
            queryWrapper.eq("user_id",userId).eq("order_state",orderState).orderByDesc("time").eq("deleted",0);
        }

        List<ShopOrder> shopOrderList=shopOrderService.list(queryWrapper);

        List<ShoppingOrderGroupByShop> shoppingOrderGroupByShopList=new ArrayList<>();

        System.out.println(shoppingOrderGroupByShopList);

        for(ShopOrder shopOrder:shopOrderList){
            ShoppingOrderGroupByShop shoppingOrderGroupByShop = new ShoppingOrderGroupByShop();

            shoppingOrderGroupByShop.setShopOrder(shopOrder);

            //---------------查询店铺信息------------------
            QueryWrapper<Shop> shopQueryWrapper=new QueryWrapper<>();
            shopQueryWrapper.eq("shop_id",shopOrder.getShopId());
            shoppingOrderGroupByShop.setShop(shopService.getOne(shopQueryWrapper));

            //---------------查询商品信息---------------
            JSONArray goodsIdArray=JSONObject.parseArray(shopOrder.getGoodsId());
            List<Goods> goodsList=new ArrayList<>();
            for(int i = 0; i< goodsIdArray.size(); i++){
                QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
                goodsQueryWrapper.eq("goods_id",goodsIdArray.get(i));
                goodsList.add(goodsService.getOne(goodsQueryWrapper));
            }
            shoppingOrderGroupByShop.setGoodsList(goodsList);

            //-----------查询商品规格信息----------------
            JSONArray goodsSpecificationsArray=JSONArray.parseArray(shopOrder.getSpecificationsId());
            List<GoodsSpecifications> goodsSpecificationsList=new ArrayList<>();
            for(int i=0;i<goodsSpecificationsArray.size();i++){
                QueryWrapper<GoodsSpecifications> goodsSpecificationsQueryWrapper=new QueryWrapper<>();
                goodsSpecificationsQueryWrapper.eq("specifications_id",goodsSpecificationsArray.get(i));
                goodsSpecificationsList.add(goodsSpecificationsService.getOne(goodsSpecificationsQueryWrapper));
            }
            shoppingOrderGroupByShop.setGoodsSpecificationsList(goodsSpecificationsList);

            //-----------查询商品数量信息----------------
            List<Integer> list=JSONArray.parseArray(shopOrder.getGoodsCount(),Integer.class);
            shoppingOrderGroupByShop.setGoodsCountList(list);

            shoppingOrderGroupByShopList.add(shoppingOrderGroupByShop);
        }

        return Result.success(shoppingOrderGroupByShopList);
    }

    @PostMapping("/deleteShopOrder")
    public Result<?> deleteShopOrder(@RequestBody ShopOrder order){
        QueryWrapper<ShopOrder> shopOrderQueryWrapper=new QueryWrapper<>();
        if(order.getOrderState().equals("待付款")||order.getOrderState().equals("已取消")){
            //------物理删除------
            shopOrderQueryWrapper.eq("order_number",order.getOrderNumber());

            //恢复库存
            if(order.getOrderState().equals("待付款")) {
                JSONArray jsonArray = JSONArray.parseArray(order.getSpecificationsId());
                List<Integer> list = JSONArray.parseArray(order.getGoodsCount(), Integer.class);
                for (int i = 0; i < jsonArray.size(); i++) {
                    QueryWrapper<GoodsSpecifications> goodsSpecificationsQueryWrapper = new QueryWrapper<>();
                    goodsSpecificationsQueryWrapper.eq("specifications_id", jsonArray.get(i));
                    GoodsSpecifications goodsSpecifications = goodsSpecificationsService.getOne(goodsSpecificationsQueryWrapper);

                    UpdateWrapper<GoodsSpecifications> goodsSpecificationsUpdateWrapper = new UpdateWrapper<>();
                    goodsSpecificationsUpdateWrapper.eq("specifications_id", jsonArray.get(i)).set("goods_stoke", goodsSpecifications.getGoodsStoke() + list.get(i));
                    goodsSpecificationsService.update(goodsSpecificationsUpdateWrapper);
                }
            }

            return Result.success(shopOrderService.remove(shopOrderQueryWrapper));
        }else {
            //------逻辑删除-----
            UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
            shopOrderUpdateWrapper.eq("order_number",order.getOrderNumber()).set("deleted",1);

            return Result.success(shopOrderService.update(shopOrderUpdateWrapper));
        }
    }

    @PostMapping("/cancelShopOrder")
    public Result<?> cancelShopOrder(@RequestBody ShopOrder order) throws Exception {
        //恢复库存
        JSONArray jsonArray = JSONArray.parseArray(order.getSpecificationsId());
        List<Integer> list = JSONArray.parseArray(order.getGoodsCount(), Integer.class);

        double money=order.getGoodsSalesVolume();

        int allCount=0;
        for (int i = 0; i < jsonArray.size(); i++) {
            QueryWrapper<GoodsSpecifications> goodsSpecificationsQueryWrapper = new QueryWrapper<>();
            goodsSpecificationsQueryWrapper.eq("specifications_id", jsonArray.get(i));
            GoodsSpecifications goodsSpecifications = goodsSpecificationsService.getOne(goodsSpecificationsQueryWrapper);

            UpdateWrapper<GoodsSpecifications> goodsSpecificationsUpdateWrapper = new UpdateWrapper<>();
            goodsSpecificationsUpdateWrapper.eq("specifications_id", jsonArray.get(i)).set("goods_stoke", goodsSpecifications.getGoodsStoke() + list.get(i));
            goodsSpecificationsService.update(goodsSpecificationsUpdateWrapper);

            allCount+=list.get(i);
        }


        UpdateWrapper<ShopOrder> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("order_number",order.getOrderNumber()).set("order_state","已取消");

        shopOrderService.update(updateWrapper);
        if(order.getOrderState().equals("待发货")){

            //---------------修改店铺销量------------------
            Shop shop=shopService.getById(order.getShopId());

            shop.setSaleAccount(shop.getSaleAccount()-order.getGoodsSalesVolume());
            shop.setSaleCount(shop.getSaleCount()-allCount);

            shopService.updateById(shop);

            return Result.success(aliPayService.refund(order.getTradeNo(),money));
        }else {
            return Result.success();
        }

    }

    @PostMapping("/notify_url")
    public String notify_url(HttpServletRequest request) throws Exception {

        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");


            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过

                QueryWrapper<ShopOrder> shopOrderQueryWrapper=new QueryWrapper<>();
                shopOrderQueryWrapper.eq("trade_no",params.get("out_trade_no"));

                List<ShopOrder> shopOrderList=new ArrayList<>();
                shopOrderList=shopOrderService.list(shopOrderQueryWrapper);


                for(ShopOrder shopOrder:shopOrderList){
                    List<Integer> list = JSONArray.parseArray(shopOrder.getGoodsId(), Integer.class);
                    List<Integer> list1=JSONArray.parseArray(shopOrder.getGoodsCount(),Integer.class);
                    List<Integer> specificationsIdList=JSONArray.parseArray(shopOrder.getSpecificationsId(),Integer.class);
                    List<Integer> returnFlag=new ArrayList<>();
                    int allCount=0;
                    for(int count:list1){
                        allCount+=count;
                        returnFlag.add(-1);
                    }

                    int i=0;
                    for(Integer specificationsId:specificationsIdList){
                        //减少库存
                        QueryWrapper<GoodsSpecifications> goodsSpecificationsQueryWrapper=new QueryWrapper<>();
                        goodsSpecificationsQueryWrapper.eq("specifications_id",specificationsId);
                        GoodsSpecifications goodsSpecifications=goodsSpecificationsService.getOne(goodsSpecificationsQueryWrapper);

                        UpdateWrapper<GoodsSpecifications> goodsSpecificationsUpdateWrapper=new UpdateWrapper<>();
                        goodsSpecificationsUpdateWrapper.eq("specifications_id",goodsSpecifications.getSpecificationsId()).set("goods_stoke",goodsSpecifications.getGoodsStoke()-list1.get(i));
                        goodsSpecificationsService.update(goodsSpecificationsUpdateWrapper);
                    }


                    //--------------改变店铺销售量------------------
                    Shop shop=shopService.getById(shopOrder.getShopId());
                    shop.setSaleCount(shop.getSaleCount()+allCount);
                    shop.setSaleAccount(shop.getSaleAccount()+shopOrder.getGoodsSalesVolume());
                    shopService.updateById(shop);

                    //---------------保存销售记录------------------
                    QueryWrapper<ShopSalesReport> shopSalesReportQueryWrapper=new QueryWrapper<>();
                    shopSalesReportQueryWrapper.eq("shop_id",shopOrder.getShopId()).eq("time", LocalDate.now());
                    ShopSalesReport shopSalesReport=shopSalesReportService.getOne(shopSalesReportQueryWrapper);

                    if(shopSalesReport==null){
                        shopSalesReport=new ShopSalesReport();
                        shopSalesReport.setSalesAccount(shopOrder.getGoodsSalesVolume());
                        shopSalesReport.setSalesCount(allCount);
                        shopSalesReport.setTime(LocalDate.now());
                        shopSalesReport.setShopId(shopOrder.getShopId());

                        shopSalesReportService.save(shopSalesReport);
                    }else {
                        UpdateWrapper<ShopSalesReport> updateWrapper=new UpdateWrapper<>();
                        updateWrapper.eq("time",LocalDate.now());
                        updateWrapper.eq("shop_id",shopOrder.getShopId());
                        shopSalesReport.setSalesAccount( shopSalesReport.getSalesAccount()+shopOrder.getGoodsSalesVolume());
                        shopSalesReport.setSalesCount( shopSalesReport.getSalesCount()+allCount);

                        shopSalesReportService.update(shopSalesReport,updateWrapper);
                    }

                    int count=0;
                    for(Integer goodsId:list){
                       Goods goods=goodsService.getById(goodsId);
                       goods.setGoodsSalesVolumn(goods.getGoodsSalesVolumn()+list1.get(count));
                       goodsService.updateById(goods);
                       count++;
                    }


                    UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
                    shopOrderUpdateWrapper.eq("order_number",shopOrder.getOrderNumber()).set("order_state","待发货").set("return_flag",JSON.toJSONString(returnFlag)).set("change_flag",JSON.toJSONString(returnFlag));

                    shopOrderService.update(shopOrderUpdateWrapper);
                }

            }
        }

        return "success";
    }

    @GetMapping("/modifyOrder")
    public Result<?> modifyOrder(@RequestParam Integer orderNumber,@RequestParam Integer harvestId){
        UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();

        shopOrderUpdateWrapper.eq("order_number",orderNumber).set("harvest_id",harvestId);

        return Result.success(shopOrderService.update(shopOrderUpdateWrapper));
    }

    @PostMapping("/modifyOrderToReturn")
    public Result<?> modifyOrderToReturn(@RequestBody Map<String,Object> map){

        UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();

        shopOrderUpdateWrapper.eq("order_number",map.get("orderNumber")).set("return_flag",map.get("returnList"));

        return Result.success(shopOrderService.update(shopOrderUpdateWrapper));
    }

    @PostMapping("/modifyOrderToExchange")
    public Result<?> modifyOrderToExchange(@RequestBody Map<String,Object> map){

        UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();

        shopOrderUpdateWrapper.eq("order_number",map.get("orderNumber")).set("change_flag",map.get("changeList"));

        return Result.success(shopOrderService.update(shopOrderUpdateWrapper));
    }

    @PostMapping("/changeOrderToComment")
    public Result<?> changeOrderToComment(@RequestBody ShopOrder order){
        order.setOrderState("待评价");
        order.setTime(LocalDateTime.now());
        return Result.success(shopOrderService.updateById(order));
    }

    @GetMapping("/getShopOrders")
    public Result<?> getShopOrders(@RequestParam Integer shopId){

        QueryWrapper<ShopOrder> shopOrderQueryWrapper=new QueryWrapper<>();
        shopOrderQueryWrapper.eq("shop_id",shopId).eq("order_state","待付款");
        List<ShopOrder> nonPaymentList= shopOrderService.list(shopOrderQueryWrapper);

        QueryWrapper<ShopOrder> shopOrderQueryWrapper0=new QueryWrapper<>();
        shopOrderQueryWrapper0.eq("shop_id",shopId).eq("order_state","待发货");
        List<ShopOrder> undeliveredList= shopOrderService.list(shopOrderQueryWrapper0);

        QueryWrapper<ShopOrder> shopOrderQueryWrapper1=new QueryWrapper<>();
        shopOrderQueryWrapper1.eq("shop_id",shopId).eq("order_state","待收货");
        List<ShopOrder> paidList= shopOrderService.list(shopOrderQueryWrapper1);

        QueryWrapper<ShopOrder> shopOrderQueryWrapper2=new QueryWrapper<>();
        shopOrderQueryWrapper2.eq("shop_id",shopId).eq("order_state","待评价");
        List<ShopOrder> nonCommentList= shopOrderService.list(shopOrderQueryWrapper2);

        QueryWrapper<ShopOrder> shopOrderQueryWrapper4=new QueryWrapper<>();
        shopOrderQueryWrapper4.eq("shop_id",shopId).eq("order_state","已完成");
        List<ShopOrder> finishedList= shopOrderService.list(shopOrderQueryWrapper4);

        QueryWrapper<ShopOrder> shopOrderQueryWrapper5=new QueryWrapper<>();
        shopOrderQueryWrapper5.eq("shop_id",shopId).eq("order_state","退款中");
        List<ShopOrder> refundList= shopOrderService.list(shopOrderQueryWrapper5);

        QueryWrapper<ShopOrder> shopOrderQueryWrapper6=new QueryWrapper<>();
        shopOrderQueryWrapper6.eq("shop_id",shopId).eq("order_state","换货中");
        List<ShopOrder> exchangeList= shopOrderService.list(shopOrderQueryWrapper6);

        Map<String,Object> map=new HashMap<>();
        map.put("nonPaymentList",nonPaymentList);
        map.put("undeliveredList",undeliveredList);
        map.put("paidList",paidList);
        map.put("nonCommentList",nonCommentList);
        map.put("finishedList",finishedList);
        map.put("refundList",refundList);
        map.put("exchangeList",exchangeList);

        return Result.success(map);
    }

    @SneakyThrows
    @GetMapping("/searchShopOrders")
    public Result<?> searchShopOrders(@RequestParam Integer shopId,@RequestParam Integer currentPage,@RequestParam String username,@RequestParam Integer orderNumber,@RequestParam String orderState,@RequestParam String goodsTitle,@RequestParam String fromDate,@RequestParam String toDate){
        QueryWrapper<ShopOrder> shopOrderQueryWrapper=new QueryWrapper<>();

        if(!goodsTitle.equals("")){
            QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
            goodsQueryWrapper.like("goods_title",goodsTitle);
            List<Goods> goodsList=goodsService.list(goodsQueryWrapper);

            for(Goods goods:goodsList){
                shopOrderQueryWrapper.or(wrapper -> wrapper.like("goods_id",goods.getGoodsId()));
            }
        }

        Page<ShopOrder> objectPage=new Page<>(currentPage,5);
        shopOrderQueryWrapper.eq("shop_id",shopId).orderByDesc("time").eq("shop_deleted",0);
        if(!username.equals("")){
            QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
            userQueryWrapper.like("username",username);
            List<User> users=userService.list(userQueryWrapper);
            String userIds="";
            for(int i=0;i<users.size();i++){
                if(i==0){
                    userIds+=users.get(i).getUserId();
                }else {
                    userIds+=","+users.get(i).getUserId();
                }
            }

            shopOrderQueryWrapper.inSql("user_id",userIds);
        }

        if(orderNumber!=0){
            shopOrderQueryWrapper.eq("order_number",orderNumber);
        }

        if(!orderState.equals("全部")) {
            if(orderState.equals("退款中")){
                shopOrderQueryWrapper.and(wrapper->wrapper.like("return_flag",0).or().like("return_flag",2).or().like("return_flag",3));
            }else {
                shopOrderQueryWrapper.eq("order_state", orderState);
            }
        }

        java.text.SimpleDateFormat formatter =  new SimpleDateFormat(  "yyyy-MM-dd" );
        Date date1 =  formatter.parse(fromDate);

        Instant instant1 = date1.toInstant();
        ZoneId zoneId1 = ZoneId.systemDefault();

        LocalDateTime fromLocalDateTime = instant1.atZone(zoneId1).toLocalDateTime();


        Date date2 =  formatter.parse(toDate);
        Instant instant2 = date2.toInstant();
        ZoneId zoneId2 = ZoneId.systemDefault();

        LocalDateTime toLocalDateTime = instant2.atZone(zoneId2).toLocalDateTime();

        shopOrderQueryWrapper.between("time",fromLocalDateTime,toLocalDateTime);

        List<ShopOrder> shopOrderList=shopOrderService.page(objectPage,shopOrderQueryWrapper).getRecords();

        List<User> users=new ArrayList<>();
        List<List<Goods>> orderGoodsList=new ArrayList<>();
        List<List<GoodsSpecifications>> orderSpecifications=new ArrayList<>();
        List<List<Integer>> goodsCountList=new ArrayList<>();
        List<HarvestAddress> harvestAddresses=new ArrayList<>();
        for(ShopOrder shopOrder:shopOrderList){
            users.add(userService.getById(shopOrder.getUserId()));
            goodsCountList.add(JSONArray.parseArray(shopOrder.getGoodsCount(),Integer.class));
            harvestAddresses.add(harvestAddressService.getById(shopOrder.getHarvestId()));

            List<Integer> goodsIdList=JSONArray.parseArray(shopOrder.getGoodsId(),Integer.class);
            List<Goods> goodsList=new ArrayList<>();

            for(Integer goodsId:goodsIdList){
                goodsList.add(goodsService.getById(goodsId));
            }

            List<Integer> specificationsIdList=JSONArray.parseArray(shopOrder.getSpecificationsId(),Integer.class);
            List<GoodsSpecifications> goodsSpecificationsList=new ArrayList<>();

            for(Integer specificationsId:specificationsIdList){
                goodsSpecificationsList.add(goodsSpecificationsService.getById(specificationsId));
            }

            orderGoodsList.add(goodsList);
            orderSpecifications.add(goodsSpecificationsList);
        }


        Map<String,Object> map=new HashMap<>();
        map.put("page",shopOrderService.page(objectPage,shopOrderQueryWrapper));
        map.put("users",users);
        map.put("orderGoodsList",orderGoodsList);
        map.put("orderSpecifications",orderSpecifications);
        map.put("goodsCountList",goodsCountList);
        map.put("harvestAddresses",harvestAddresses);

        return Result.success(map);
    }

    @GetMapping("/remindOfPaying")
    public Result<?> remindOfPaying(@RequestParam String email1,@RequestParam String username,@RequestParam String shopName){

        //------------------发送验证码--------------
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.setAuthentication("2923904910@qq.com", "sphujplfnxupdeec");//此处填写邮箱地址和客户端授权码
            email.setFrom("2923904910@qq.com", shopName);//此处填邮箱地址和用户名,用户名可以任意填写
            email.setSSLCheckServerIdentity(true);
            email.addTo(email1);// 收件地址
            email.setSubject("验证码");//此处填写邮件名，邮件名可任意填写
            email.setMsg("亲爱的"+username+",看到您的订单还没支付，库存有限尽快完成付款哦~我们7天无理由退款，还帮您购买了运费保险哦，收到包您满意，如不满意也无后顾之忧 \n");//此处填写邮件内容

            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    @GetMapping("/remindOfCommenting")
    public Result<?> remindOfCommenting(@RequestParam String email1,@RequestParam String username,@RequestParam String shopName){

        //------------------发送验证码--------------
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.setAuthentication("2923904910@qq.com", "sphujplfnxupdeec");//此处填写邮箱地址和客户端授权码
            email.setFrom("2923904910@qq.com", shopName);//此处填邮箱地址和用户名,用户名可以任意填写
            email.setSSLCheckServerIdentity(true);
            email.addTo(email1);// 收件地址
            email.setSubject("验证码");//此处填写邮件名，邮件名可任意填写
            email.setMsg("亲爱的"+username+"，后台看到您已经签收了宝贝。如果宝贝有任何问题，请第一时间联系客服,我们会立刻给您处理。若宝贝都满意,还麻烦您动动小手点亮15颗小星星呢-");//此处填写邮件内容

            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    @GetMapping("/remindOfTakingDelivery")
    public Result<?> remindOfTakingDelivery(@RequestParam String email1,@RequestParam String shopName){

        //------------------发送验证码--------------
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.setAuthentication("2923904910@qq.com", "sphujplfnxupdeec");//此处填写邮箱地址和客户端授权码
            email.setFrom("2923904910@qq.com", shopName);//此处填邮箱地址和用户名,用户名可以任意填写
            email.setSSLCheckServerIdentity(true);
            email.addTo(email1);// 收件地址
            email.setSubject("验证码");//此处填写邮件名，邮件名可任意填写
            email.setMsg("【"+shopName+"】温馨提示：您的包裹正在派件中，请保持手机畅通，并且当面验货签收以免造成包裹遗失。\n");//此处填写邮件内容

            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    @GetMapping("/deleteOrderInManage")
    public Result<?> deleteShopOrder(@RequestParam Integer orderNumber){
        UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
        shopOrderUpdateWrapper.eq("order_number",orderNumber).set("shop_deleted",1);

        return Result.success(shopOrderService.update(shopOrderUpdateWrapper));
    }

    @SneakyThrows
    @PostMapping("/refund")
    public Result<?> refund(@RequestParam Integer orderNumber,@RequestParam Integer goodsCount,@RequestParam String tradeNo,@RequestParam float money,@RequestParam String returnFlag,@RequestParam Integer index){
        QueryWrapper<ReturnOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_number",orderNumber).eq("goods_index",index);
        ReturnOrder returnOrder=returnOrderService.getOne(queryWrapper);


        if(returnOrder==null){
            UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
            shopOrderUpdateWrapper.eq("order_number",orderNumber).set("return_flag", returnFlag);

            shopOrderService.update(shopOrderUpdateWrapper);
            return Result.success(aliPayService.refund(tradeNo,money));
        }else {
            returnOrderService.remove(queryWrapper);
            if(returnOrder.getGoodsCount().equals(goodsCount)){
                UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
                shopOrderUpdateWrapper.eq("order_number",orderNumber).set("return_flag", returnFlag);

                shopOrderService.update(shopOrderUpdateWrapper);
                return Result.success(aliPayService.refund(tradeNo,money));
            }else {
                ShopOrder order=shopOrderService.getById(orderNumber);

                //修改商品数量
                List<Integer> list=JSONArray.parseArray(order.getGoodsRealCount(),Integer.class);
                Integer count1=list.get(index)-returnOrder.getGoodsCount();
                list.set(index,count1);
                System.out.println("goodsCount:"+goodsCount);
                System.out.println("list:"+list);

                //修改商品退款信息
                List<Integer> list1=JSONArray.parseArray(returnFlag,Integer.class);
                list1.set(index,3);

                System.out.println("list1:"+list1);


                UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
                shopOrderUpdateWrapper.eq("order_number",orderNumber).set("return_flag",JSONArray.toJSONString(list1)).set("goods_real_count",JSONArray.toJSONString(list));

                shopOrderService.update(shopOrderUpdateWrapper);
                return Result.success(aliPayService.refund(tradeNo,money));
            }
        }
    }

    @PostMapping("/toWaitTakingDelivery")
    public Result<?> toWaitTakingDelivery(@RequestParam Integer orderNumber){
        UpdateWrapper<ShopOrder> shopOrderUpdateWrapper=new UpdateWrapper<>();
        shopOrderUpdateWrapper.eq("order_number",orderNumber).set("order_state","待收货");

        return Result.success( shopOrderService.update(shopOrderUpdateWrapper));
    }

    @PostMapping("/exchangeOrder")
    public Result<?> exchangeOrder(@RequestBody ExchangeOrder exchangeOrder){
        QueryWrapper<ExchangeOrder> exchangeOrderQueryWrapper=new QueryWrapper<>();
        exchangeOrderQueryWrapper.eq("order_number",exchangeOrder.getOrderNumber()).eq("specifications_id",exchangeOrder.getSpecificationsId());

        exchangeOrder=exchangeOrderService.getOne(exchangeOrderQueryWrapper);

        int orderNumber=exchangeOrder.getOrderNumber();
        ShopOrder order=shopOrderService.getById(orderNumber);


        //--------------------------------改变订单状态--------------------------------
        List<Integer> changeList=JSONArray.parseArray(order.getChangeFlag(),Integer.class);
        changeList.set(exchangeOrder.getGoodsIndex(),1);

        order.setChangeFlag(JSONArray.toJSONString(changeList));
        shopOrderService.updateById(order);

        List<Integer> goodsIdList=JSONArray.parseArray(order.getGoodsId(),Integer.class);

        List<Integer> goodsIdList1=new ArrayList<>();
        goodsIdList1.add(goodsIdList.get(exchangeOrder.getGoodsIndex()));

        List<Integer> countList=new ArrayList<>();
        countList.add(exchangeOrder.getGoodsCount());

        List<Integer> specificationsList=new ArrayList<>();
        specificationsList.add(exchangeOrder.getSpecificationsId());

        GoodsSpecifications goodsSpecifications=goodsSpecificationsService.getById(exchangeOrder.getSpecificationsId());

        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setDeleted(0);
        shopOrder.setUserId(order.getUserId());
        shopOrder.setShopId(order.getShopId());
        shopOrder.setTime(LocalDateTime.now());
        shopOrder.setHarvestId(order.getHarvestId());
        shopOrder.setGoodsId(JSONArray.toJSONString(goodsIdList1));
        shopOrder.setGoodsCount(JSONArray.toJSONString(countList));
        shopOrder.setGoodsRealCount(JSONArray.toJSONString(changeList));
        shopOrder.setSpecificationsId(JSONArray.toJSONString(specificationsList));
        shopOrder.setGoodsSalesVolume(goodsSpecifications.getGoodsPrice()*exchangeOrder.getGoodsCount());
        shopOrder.setShopDeleted(0);
        shopOrder.setOrderState("待收货");

        shopOrderService.save(shopOrder);
        return Result.success(shopOrder);
    }

    @PostMapping("/takeExchangeOrderDelivery")
    public Result<?> takeExchangeOrderDelivery(@RequestBody ShopOrder shopOrder){
        QueryWrapper<ExchangeOrder> exchangeOrderQueryWrapper=new QueryWrapper<>();
        exchangeOrderQueryWrapper.eq("exchange_order_number",shopOrder.getOrderNumber());
        ExchangeOrder exchangeOrder=exchangeOrderService.getOne(exchangeOrderQueryWrapper);

        //-----------------恢复商品真实数量---------------------
        ShopOrder shopOrder1=shopOrderService.getById(exchangeOrder.getOrderNumber());

        List<Integer> countList=JSONArray.parseArray(shopOrder1.getGoodsRealCount(),Integer.class);
        countList.set(exchangeOrder.getGoodsIndex(),countList.get(exchangeOrder.getGoodsIndex())+exchangeOrder.getGoodsCount());
        shopOrder1.setGoodsRealCount(JSONArray.toJSONString(countList));

        List<Integer> changeList=JSONArray.parseArray(shopOrder1.getChangeFlag(),Integer.class);
        changeList.set(exchangeOrder.getGoodsIndex(),3);
        shopOrder1.setChangeFlag(JSONArray.toJSONString(changeList));

        shopOrderService.updateById(shopOrder1);

        //删除换货信息
        exchangeOrderService.remove(exchangeOrderQueryWrapper);

        //确认收货，收货即订单完成
        shopOrder.setOrderState("已完成");
        shopOrderService.updateById(shopOrder);

        return Result.success();
    }
}

