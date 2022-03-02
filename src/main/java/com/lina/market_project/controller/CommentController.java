package com.lina.market_project.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.lina.market_project.pojo.*;
import com.lina.market_project.service.impl.*;
import lombok.SneakyThrows;
import org.apache.logging.log4j.message.ReusableMessage;
import org.bouncycastle.LICENSE;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.RegEx;
import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-09-06
 */
@RestController
@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentServiceImpl commentService;

    @Resource
    ShopServiceImpl shopService;

    @Resource
    UserServiceImpl userService;

    @Resource
    ShopOrderServiceImpl shopOrderService;

    @Resource
    GoodsServiceImpl goodsService;

    @Resource
    GoodsSpecificationsServiceImpl goodsSpecificationsService;

    @PostMapping("/getComment")
    public Result<?> getComment(@RequestBody Map<String, Object> map) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number", map.get("orderNumber")).eq("goods_id", map.get("goodsId")).eq("specifications_id",map.get("specificationsId"));

        return Result.success(commentService.getOne(queryWrapper));
    }

    @PostMapping("/saveComment")
    public Result<?> saveComment(@RequestBody Comment comment) {
        comment.setTime(LocalDateTime.now());

        Shop shop = shopService.getById(comment.getShopId());
        shop.setShopServiceCount((shop.getShopServiceCount() + (float) comment.getServiceCount()) / 2);
        shop.setShopDescriptionCount((shop.getShopDescriptionCount() + (float) comment.getDescriptionCount()) / 2);
        shop.setShopLogisticsCount((shop.getShopLogisticsCount() + (float) comment.getLogisticsCount()) / 2);
        shop.setShopCount((shop.getShopDescriptionCount()+shop.getShopLogisticsCount()+shop.getShopServiceCount())/3);

        commentService.save(comment);

        shopService.updateById(shop);

        ShopOrder order=shopOrderService.getById(comment.getOrderNumber());

        List<Integer> goodsIdList=JSONArray.parseArray(order.getGoodsId(),Integer.class);
        List<Integer> specificationsIdList=JSONArray.parseArray(order.getSpecificationsId(),Integer.class);

        Boolean flag=true;
        for(int i=0;i<goodsIdList.size();i++){
            QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("order_number",comment.getOrderNumber()).eq("goods_id",goodsIdList.get(i)).eq("specifications_id",specificationsIdList.get(i));
            Comment comment1=commentService.getOne(queryWrapper);

            if(comment1==null){
                flag=false;
                break;
            }
        }

        if(flag==true){
            order.setTime(LocalDateTime.now());
            order.setOrderState("已完成");

            shopOrderService.updateById(order);
        }

        return Result.success();
    }

    @PostMapping("/abandonComment")
    public Result<?> abandonComment(@RequestBody Comment comment){
        comment.setTime(LocalDateTime.now());
        comment.setContent("系统默认好评");
        comment.setDescriptionCount(5);
        comment.setLogisticsCount(5);
        comment.setServiceCount(5);

        Shop shop = shopService.getById(comment.getShopId());
        shop.setShopServiceCount((shop.getShopServiceCount() + (float) comment.getServiceCount()) / 2);
        shop.setShopDescriptionCount((shop.getShopDescriptionCount() + (float) comment.getDescriptionCount()) / 2);
        shop.setShopLogisticsCount((shop.getShopLogisticsCount() + (float) comment.getLogisticsCount()) / 2);
        shop.setShopCount((shop.getShopDescriptionCount()+shop.getShopLogisticsCount()+shop.getShopServiceCount())/3);

        shopService.updateById(shop);

        commentService.save(comment);

        ShopOrder order=shopOrderService.getById(comment.getOrderNumber());

        List<Integer> goodsIdList=JSONArray.parseArray(order.getGoodsId(),Integer.class);
        List<Integer> specificationsIdList=JSONArray.parseArray(order.getSpecificationsId(),Integer.class);

        Boolean flag=true;
        for(int i=0;i<goodsIdList.size();i++){
            QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("order_number",comment.getOrderNumber()).eq("goods_id",goodsIdList.get(i)).eq("specifications_id",specificationsIdList.get(i));
            Comment comment1=commentService.getOne(queryWrapper);

            if(comment1==null){
                flag=false;
                break;
            }
        }

        if(flag==true){
            order.setTime(LocalDateTime.now());
            order.setOrderState("已完成");

            shopOrderService.updateById(order);
        }


        return Result.success();
    }


    @RequestMapping(value = "/saveImages", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> saveImages(@RequestParam("files") MultipartFile[] files, @RequestParam Integer orderNumber, @RequestParam Integer goodsId,@RequestParam Integer specificationsId) throws IOException {
        ArrayList<String> list1 = new ArrayList<>();
        for (MultipartFile file : files) {
            //获取上传文件的文件名
            String fileName = file.getOriginalFilename();

            String serverpath = "C:\\Users\\lina\\IdeaProjects\\market_project\\market_bsfront\\src\\assets\\img";

            InputStream in = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(serverpath + '\\' + fileName);

            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = in.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }

            list1.add(fileName);
            fos.close();
            in.close();
        }
        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("comment_photo", JSONArray.toJSONString(list1)).eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId);
        commentService.update(updateWrapper);
        return Result.success();
    }

    @GetMapping("/removeComment")
    public Result<?> removeComment(@RequestParam Integer orderNumber, @RequestParam Integer goodsId,@RequestParam Integer specificationsId) throws IOException {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number",orderNumber).eq("goods_id", goodsId);
        Comment comment = commentService.getOne(queryWrapper);

        Shop shop = shopService.getById(comment.getShopId());

        shop.setShopServiceCount(shop.getShopServiceCount() * 2 - (float) comment.getServiceCount());
        shop.setShopDescriptionCount(shop.getShopDescriptionCount() * 2 - (float) comment.getDescriptionCount());
        shop.setShopLogisticsCount(shop.getShopLogisticsCount() * 2 - (float) comment.getLogisticsCount());
        shop.setShopCount((shop.getShopDescriptionCount()+shop.getShopLogisticsCount()+shop.getShopServiceCount())/3);

        //恢复店铺评分
        shopService.updateById(shop);

        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_number",orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId).set("content", null).set("comment_photo", null).set("description_count", null).set("service_count", null).set("logistics_count", null);
        commentService.update(updateWrapper);
        return Result.success();
    }


    //----------有照片时追加评论-----------------
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> addComment(@RequestParam Integer orderNumber, @RequestParam Integer goodsId, @RequestParam Integer specificationsId,@RequestParam String additionalComment, @RequestParam("files") MultipartFile[] files) throws IOException {
        ArrayList<String> list1 = new ArrayList<>();
        for (MultipartFile file : files) {
            //获取上传文件的文件名
            String fileName = file.getOriginalFilename();

            String serverpath = "C:\\Users\\lina\\IdeaProjects\\market_project\\market_bsfront\\src\\assets\\img";

            InputStream in = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(serverpath + '\\' + fileName);

            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = in.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }

            list1.add(fileName);
            fos.close();
            in.close();
        }


        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId).set("additional_comment", additionalComment).set("additional_photo", JSONArray.toJSONString(list1)).set("additional_time",LocalDateTime.now());
        commentService.update(updateWrapper);
        return Result.success();
    }

    //----------无照片时追加评论-----------------
    @RequestMapping(value = "/addComment1", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> addComment1(@RequestParam Integer orderNumber, @RequestParam Integer goodsId, @RequestParam Integer specificationsId, @RequestParam String additionalComment) throws IOException {
        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId).set("additional_comment", additionalComment).set("additional_time",LocalDateTime.now());
        commentService.update(updateWrapper);
        return Result.success();
    }

    @RequestMapping(value = "/modifyComment", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> modifyComment(@RequestParam("files") MultipartFile[] files,@RequestParam Integer orderNumber, @RequestParam Integer goodsId,@RequestParam Integer specificationsId, @RequestParam String content, @RequestParam Integer descriptionCount, @RequestParam Integer serviceCount, @RequestParam Integer logisticsCount, @RequestParam("images") String[] images) throws IOException {
        for (MultipartFile file : files) {
            if(file!=null){
                //获取上传文件的文件名
                String fileName = file.getOriginalFilename();

                String serverpath = "C:\\Users\\lina\\IdeaProjects\\market_project\\market_bsfront\\src\\assets\\img";

                InputStream in = file.getInputStream();
                FileOutputStream fos = new FileOutputStream(serverpath + '\\' + fileName);

                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = in.read(bytes)) != -1) {
                    fos.write(bytes, 0, length);
                }
                fos.close();
                in.close();
            }
        }
        //-------------修改店铺评分-------------------
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId);
        Comment comment = commentService.getOne(queryWrapper);

        Shop shop = shopService.getById(comment.getShopId());
        shop.setShopServiceCount((shop.getShopServiceCount() * 2 - (float) comment.getServiceCount()+(float)serviceCount)/2);
        shop.setShopDescriptionCount((shop.getShopDescriptionCount() * 2 - (float) comment.getDescriptionCount()+(float)descriptionCount)/2);
        shop.setShopLogisticsCount((shop.getShopLogisticsCount() * 2 - (float) comment.getLogisticsCount()+(float)serviceCount)/2);
        shop.setShopCount((shop.getShopDescriptionCount()+shop.getShopLogisticsCount()+shop.getShopServiceCount())/3);

        shopService.updateById(shop);

        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        if(!images.equals(null)){
            updateWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId).set("content", content).set("comment_photo", JSONArray.toJSONString(images)).set("service_count",serviceCount).set("description_count",descriptionCount).set("logistics_count",logisticsCount);
        }else {
            updateWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId).set("content", content).set("service_count",serviceCount).set("description_count",descriptionCount).set("logistics_count",logisticsCount);
        }
        commentService.update(updateWrapper);

        return Result.success();
    }

    @RequestMapping(value = "/modifyComment1", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> modifyComment1(@RequestParam Integer orderNumber, @RequestParam Integer goodsId,@RequestParam Integer specificationsId,@RequestParam String content, @RequestParam Integer descriptionCount, @RequestParam Integer serviceCount, @RequestParam Integer logisticsCount, @RequestParam("images") String[] images) throws IOException {
        //-------------修改店铺评分-------------------
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId);
        Comment comment = commentService.getOne(queryWrapper);

        Shop shop = shopService.getById(comment.getShopId());
        shop.setShopServiceCount((shop.getShopServiceCount() * 2 - (float) comment.getServiceCount()+(float)serviceCount)/2);
        shop.setShopDescriptionCount((shop.getShopDescriptionCount() * 2 - (float) comment.getDescriptionCount()+(float)descriptionCount)/2);
        shop.setShopLogisticsCount((shop.getShopLogisticsCount() * 2 - (float) comment.getLogisticsCount()+(float)serviceCount)/2);
        shop.setShopCount((shop.getShopDescriptionCount()+shop.getShopLogisticsCount()+shop.getShopServiceCount())/3);

        shopService.updateById(shop);
        System.out.println(images);
        System.out.println(images == null);
        System.out.println(images.getClass());
        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();

        if(!images.equals(null)){
            updateWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id",specificationsId).set("content", content).set("comment_photo", JSONArray.toJSONString(images)).set("service_count",serviceCount).set("description_count",descriptionCount).set("logistics_count",logisticsCount);
        }else {
            updateWrapper.eq("order_number", orderNumber).eq("goods_id", goodsId).eq("specifications_id", specificationsId).set("content", content).set("service_count", serviceCount).set("description_count", descriptionCount).set("logistics_count", logisticsCount);
        }

        commentService.update(updateWrapper);

        return Result.success();
    }

    @GetMapping("/getCommentListForShow")
    public Result<?> getCommentListForShow(@RequestParam Integer goodsId,@RequestParam String chooseItem,@RequestParam Integer currentPage){
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        Page<Comment> objectPage=new Page<>(currentPage,1);
        if(chooseItem.equals("null")){
            queryWrapper.eq("goods_id",goodsId);
        }else if(chooseItem.equals("时间")){
            queryWrapper.eq("goods_id",goodsId).orderByDesc("time");
        }else if(chooseItem.equals("有图")){
            queryWrapper.eq("goods_id",goodsId).and(wrapper -> wrapper.isNotNull("comment_photo").or().isNotNull("additional_photo")).orderByDesc("time");
        }else if(chooseItem.equals("追加")){
            queryWrapper.eq("goods_id",goodsId).isNotNull("additional_comment").orderByDesc("time");
        }

        List<Comment> list=commentService.page(objectPage,queryWrapper).getRecords();

        List<User> users=new ArrayList<>();
        List<GoodsSpecifications> goodsSpecificationsList=new ArrayList<>();

        for(Comment comment:list){
            users.add(userService.getById(comment.getUserId()));
            goodsSpecificationsList.add(goodsSpecificationsService.getById(comment.getSpecificationsId()));
        }

        Map<String,Object> map=new HashMap<>();
        map.put("commentList",commentService.page(objectPage,queryWrapper));
        map.put("userList",users);
        map.put("goodsSpecificationsList",goodsSpecificationsList);

        return Result.success(map);
    }

    @GetMapping("/getAllCommentsInf")
    public Result<?> getCommentListForShow(@RequestParam Integer goodsId){
        QueryWrapper<Comment> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("goods_id",goodsId).isNotNull("description_count").isNotNull("service_count").isNotNull("logistics_count");

        List<Comment> list=commentService.list(queryWrapper1);
        float count=0;
        for(Comment comment:list){
            if(((float)comment.getLogisticsCount()+(float)comment.getDescriptionCount()+(float)comment.getServiceCount())/3>=4){
                count++;
            }else {
                continue;
            }
        }

        //-----------------满意率---------------------
        float rate=(count/list.size()*100);
        rate=(float)(Math.round(rate*1000)/1000);//这里的100就是2位小数点，如果要求精确4位就*10000然后/10000


        QueryWrapper<Comment> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("goods_id",goodsId).and(wrapper -> wrapper.isNotNull("comment_photo").or().isNotNull("additional_photo"));

        QueryWrapper<Comment> queryWrapper3=new QueryWrapper<>();
        queryWrapper3.eq("goods_id", goodsId).isNotNull("additional_comment");

        Map<String,Object> map=new HashMap<>();
        map.put("rate",rate);
        map.put("haveImageCount",commentService.list(queryWrapper2).size());
        map.put("additionalCount",commentService.list(queryWrapper3).size());

        return Result.success(map);
    }

    @GetMapping("/getShopComments")
    public Result<?> getShopComments(@RequestParam Integer shopId){
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);

        List<Comment> comments=commentService.list(queryWrapper);

        List<Comment> niceComments=new ArrayList<>();
        List<Comment> centerComments=new ArrayList<>();
        List<Comment> badComments=new ArrayList<>();
        List<Comment> nonAnswer=new ArrayList<>();
        List<Comment> nonHandle=new ArrayList<>();

        for(Comment comment:comments){
            if(comment.getSellerRestore()==null&&(comment.getContent()!=null||comment.getAdditionalComment()!=null)){
                nonAnswer.add(comment);
            }
            if(comment.getContent()!=null){
                if((comment.getServiceCount()+comment.getDescriptionCount()+comment.getLogisticsCount())/3>=4){
                    niceComments.add(comment);
                    continue;
                }else if((comment.getServiceCount()+comment.getDescriptionCount()+comment.getLogisticsCount())/3>=3){
                    centerComments.add(comment);
                    continue;
                }else{
                    badComments.add(comment);
                    if(comment.getSellerRestore()==null){
                        nonHandle.add(comment);
                    }
                    continue;
                }
            }
        }

        Map<String,Object> map=new HashMap<>();
        map.put("nonAnswer",nonAnswer);
        map.put("nonHandle",nonHandle);
        map.put("badComments",badComments);
        map.put("niceComments",niceComments);
        map.put("centerComments",centerComments);

        return Result.success(map);
    }

    @SneakyThrows
    @GetMapping("/getShopGoodsComments")
    public Result<?> getShopGoodsComments(@RequestParam Integer shopId,@RequestParam Integer goodsId, @RequestParam String username, @RequestParam String chooseItem, @RequestParam String goodsTitle, @RequestParam Integer currentPage, @RequestParam String fromDate, @RequestParam String toDate){
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("shop_id",shopId);

        Page<Comment> objectPage=new Page<>(currentPage,5);

        if(goodsId!=0){
            queryWrapper.eq("goods_id",goodsId);
        }

        if(!username.equals("")){
            QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
            userQueryWrapper.like("username",username);
            List<User> users=userService.list(userQueryWrapper);
            String userIds="";
            for(int i=0;i<users.size();i++){
                if(i==0){
                    userIds+=users.get(i).getIdentity();
                }else {
                    userIds+=","+users.get(i).getUserId();
                }
            }

           queryWrapper.inSql("user_id",userIds);
        }

        if(!goodsTitle.equals("")){
            QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
            goodsQueryWrapper.like("goods_title",goodsTitle);
            List<Goods> goodsList=goodsService.list(goodsQueryWrapper);
            String goodsIds="";
            for(int i=0;i<goodsList.size();i++){
                if(i==0){
                    goodsIds+=goodsList.get(i).getGoodsId();
                }else {
                    goodsIds+=","+goodsList.get(i).getGoodsId();
                }
            }

            queryWrapper.inSql("goods_id",goodsIds);
        }


        if(chooseItem.equals("好评")){
            queryWrapper.having("sum(description_count+service_count+logistics_count) >= 12").groupBy("time");
        }else if(chooseItem.equals("中评")){
            queryWrapper.having("sum(description_count+service_count+logistics_count) >= 9").having("sum(description_count+service_count+logistics_count) < 12").groupBy("time");
        }else if(chooseItem.equals("差评")){
            queryWrapper.having("sum(description_count+service_count+logistics_count) < 9").groupBy("time");
        }else if(chooseItem.equals("有图")){
            queryWrapper.and(commentQueryWrapper -> commentQueryWrapper.isNotNull("comment_photo").or().isNotNull("additional_photo"));
        } else if(chooseItem.equals("追加")){
            queryWrapper.isNotNull("additional_comment");
        } else if(chooseItem.equals("待回复")){
            queryWrapper.isNull("seller_restore").and(commentQueryWrapper -> commentQueryWrapper.notLike("content","系统默认好评").or().isNotNull("additional_comment"));
        }else if(chooseItem.equals("已回复")){
            queryWrapper.isNotNull("seller_restore").and(commentQueryWrapper -> commentQueryWrapper.notLike("content","系统默认好评").or().isNotNull("additional_comment"));
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


        queryWrapper.between("time",fromLocalDateTime,toLocalDateTime).orderByDesc("time");

        List<Comment> list=commentService.page(objectPage,queryWrapper).getRecords();

        List<User> users=new ArrayList<>();
        List<GoodsSpecifications> goodsSpecificationsList=new ArrayList<>();
        List<Goods> goodsList=new ArrayList<>();
        for(Comment comment:list){
            users.add(userService.getById(comment.getUserId()));
            goodsSpecificationsList.add(goodsSpecificationsService.getById(comment.getSpecificationsId()));
            goodsList.add(goodsService.getById(comment.getGoodsId()));
        }

        Map<String,Object> map=new HashMap<>();
        map.put("commentList",commentService.page(objectPage,queryWrapper));
        map.put("userList",users);
        map.put("goodsSpecificationsList",goodsSpecificationsList);
        map.put("goodsList",goodsList);

        return Result.success(map);
    }

    @PostMapping("/saveSellerRestore")
    public Result<?> saveSellerRestore(@RequestBody Comment comment){
        UpdateWrapper<Comment> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("order_number",comment.getOrderNumber());
        updateWrapper.eq("goods_id",comment.getGoodsId());
        updateWrapper.eq("specifications_id",comment.getSpecificationsId());

        return Result.success(commentService.update(comment,updateWrapper));
    }
}


