package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lina.market_project.config.LocalUtil;
import com.lina.market_project.mapper.GoodsMapper;
import com.lina.market_project.pojo.*;
import com.lina.market_project.service.impl.GoodsServiceImpl;
import com.lina.market_project.service.impl.GoodsSpecificationsServiceImpl;
import com.lina.market_project.service.impl.ShopServiceImpl;
import com.lina.market_project.service.impl.ThirdLevelServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-07-22
 */
@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Resource
    ShopServiceImpl shopService;

    @Resource
    LocalUtil localUtil;

    @Resource
    GoodsServiceImpl goodsService;

    @Resource
    ThirdLevelServiceImpl thirdLevelService;

    @GetMapping("/findByShopName")
    public Result<?> findByShopName(@RequestParam String shopName){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("shop_name",shopName);

        Shop one=shopService.getOne(wrapper);

        if(one!=null){
            return Result.success(one);
        }else {
            return Result.error();
        }
    }

    @GetMapping("/findByShopId")
    public Result<?> findByShopId(@RequestParam Integer shopId){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("shop_id",shopId);
        Shop one=shopService.getOne(wrapper);

        if(one!=null){
            return Result.success(one);
        }else {
            return Result.error();
        }
    }


    @GetMapping("/findByUserId")
    public Result<?> findByUserId(@RequestParam String userId){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_id",userId);
        Shop one=shopService.getOne(wrapper);

        if(one!=null){
            return Result.success(one);
        }else {
            return Result.error();
        }
    }

    @PostMapping("/addShop")
    public Result<?> addNewShop(@RequestBody Shop shop){
        shop.setShopLogisticsCount((float) 4.0);
        shop.setShopDescriptionCount((float)4);
        shop.setShopServiceCount((float)4);
        shop.setShopCount((float)4);
        shop.setBrowseCount(0);
        shop.setSaleCount(0);
        shop.setSaleAccount(0);
        shop.setFansNumber(0);
        shop.setGoodsFansCount(0);
        boolean flag=shopService.save(shop);
        if(flag==true){
            return Result.success(shop);
        }else {
            return Result.error();
        }
    }

    @PostMapping("/updateShopLogo")
    public Result<?> updateLogo(@RequestParam Integer shopId,@RequestParam MultipartFile image) throws IOException {
        //获取上传文件的文件名
        String fileName = image.getOriginalFilename();

        System.out.println(fileName);

        String serverpath="C:\\Users\\lina\\IdeaProjects\\market_project\\market_bsfront\\src\\assets\\img";

        InputStream in=image.getInputStream();
        FileOutputStream fos=new FileOutputStream(serverpath+'\\'+fileName);

        byte[] bytes=new byte[1024];
        int length=0;
        while ((length=in.read(bytes))!=-1){
            fos.write(bytes,0,length);
        }
        fos.close();
        in.close();

        UpdateWrapper<Shop> wrapper=new UpdateWrapper<Shop>();
        wrapper.eq("shop_id",shopId).set("shop_logo",fileName);
        shopService.update(wrapper);

        return Result.success(fileName);
    }

    /**
     * 获取所有省
     */
    @GetMapping("/getProvinces")
    public Result<?> getProvinces(){
        List<String> list=new LinkedList<>();
        list=localUtil.getProvinces("中国");
        return Result.success(list);
    }

    /**
     * 获取该省所有市
     */
    @GetMapping("/getCities")
    public Result<?> getCities(@RequestParam String province){
        List<String> list=null;
        list=localUtil.getCities("中国",province);
        return Result.success(list);
    }

    /**
     * 获取该市所有地区
     */
    @GetMapping("/getCountries")
    public Result<?> getCountries(@RequestParam String province,@RequestParam String city){
        List<String> list=null;
        list=localUtil.getcounty("中国",province,city);
        return Result.success(list);
    }

    /**
     * 修改店铺信息
     */
    @PostMapping("/updateShop")
    public Result<?> update(@RequestBody Shop shop){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("shop_id",shop.getShopId());
        shopService.update(shop,wrapper);

        return Result.success(shop);
    }

    @GetMapping("/getShopSpecifications")
    public Result<?> getShopSpecifications(@RequestParam Integer shopId){
       List<Goods> goodsList1=new ArrayList<>();
       QueryWrapper<Goods> goodsQueryWrapper1=new QueryWrapper<>();
       goodsQueryWrapper1.eq("shop_id",shopId).groupBy("goods_type_id");
       goodsList1=goodsService.list(goodsQueryWrapper1);

       List<ThirdLevel> thirdLevels=new ArrayList<>();
       for(Goods goods:goodsList1){
           thirdLevels.add(thirdLevelService.getById(goods.getGoodsTypeId()));
       }

       Map<String,Object> map=new HashMap<>();
       map.put("typeList",thirdLevels);

       return Result.success(map);
    }

    @GetMapping("/searchShop")
    public Result<?> searchShop(@RequestParam String searchItem,@RequestParam Integer currentPage,@RequestParam String orderItem){
        QueryWrapper<Shop> queryWrapper=new QueryWrapper<>();
        Page<Shop> objectPage=new Page<>(currentPage,10);

        if(orderItem.equals("")){
            queryWrapper.and(wrapper -> wrapper.like("shop_name",searchItem).or().like("shop_description",searchItem));
        }else if(orderItem.equals("salesCount")){
            queryWrapper.and(wrapper -> wrapper.like("shop_name",searchItem).or().like("shop_description",searchItem)).orderByDesc("sale_count");
        }else if(orderItem.equals("shopCount")){
            queryWrapper.and(wrapper -> wrapper.like("shop_name",searchItem).or().like("shop_description",searchItem)).orderByDesc("shop_count");
        }

        Page<Shop> page=shopService.page(objectPage,queryWrapper);
        List<Shop> list=page.getRecords();

        List<Map<String,Object>> list1=new ArrayList<>();
        for(Shop shop:list){
            Map<String,Object> map=new HashMap<>();
            map.put("shop",shop);

            QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
            goodsQueryWrapper.eq("shop_id",shop.getShopId());
            List<Goods> goodsList=goodsService.list(goodsQueryWrapper);

            map.put("goodsList",goodsList);

            list1.add(map);
        }

        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("data",list1);

        return Result.success(map);
    }


    @GetMapping("/selectByPriceRange")
    public Result<?> selectByPriceRange(@RequestParam Integer minPrice,@RequestParam Integer maxPrice,@RequestParam Integer typeId,@RequestParam Integer shopId,@RequestParam String orderColumn,@RequestParam Integer orderFlag,@RequestParam Integer currentPage){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();

        Page<Goods> objectPage=new Page<>(currentPage,40);

        if(typeId!=0){
            if (orderColumn.equals("goodsSalesVolumn") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId).between("goods_price", minPrice, maxPrice).orderByAsc("goods_sales_volumn");
                } else {
                    queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId).orderByDesc("goods_sales_volumn");
                }
            } else if (orderColumn.equals("goodsPrice") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    if (orderFlag == 1) {
                        queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId).between("goods_price", minPrice, maxPrice).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId).between("goods_price", minPrice, maxPrice).orderByDesc("goods_price");
                    }
                }else {
                    if (orderFlag == 1) {
                        queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId).orderByDesc("goods_price");
                    }
                }
            } else {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId).between("goods_price", minPrice, maxPrice);
                }else {
                    queryWrapper.eq("goods_type_id", typeId).eq("shop_id",shopId);
                }
            }
        }else {
            if (orderColumn.equals("goodsSalesVolumn") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("shop_id",shopId).between("goods_price", minPrice, maxPrice).orderByDesc("goods_sales_volumn");
                } else {
                    queryWrapper.eq("shop_id",shopId).orderByDesc("goods_sales_volumn");
                }
            } else if (orderColumn.equals("goodsPrice") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    if (orderFlag == 1) {
                        queryWrapper.eq("shop_id",shopId).between("goods_price", minPrice, maxPrice).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("shop_id",shopId).between("goods_price", minPrice, maxPrice).orderByDesc("goods_price");
                    }
                }else {
                    if (orderFlag == 1) {
                        queryWrapper.eq("shop_id",shopId).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("shop_id",shopId).orderByDesc("goods_price");
                    }
                }
            } else {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("shop_id",shopId).between("goods_price", minPrice, maxPrice);
                }else {
                    queryWrapper.eq("shop_id",shopId);
                }
            }
        }


        return Result.success(goodsService.page(objectPage, queryWrapper));
    }

}

