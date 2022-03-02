package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lina.market_project.pojo.*;
import com.lina.market_project.service.impl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-08-02
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    GoodsServiceImpl goodsService;

    @Resource
    ThirdLevelServiceImpl thirdLevelService;

    @Resource
    SecondLevelServiceImpl secondLevelService;

    @Resource
    GoodsSpecificationsServiceImpl goodsSpecificationsService;

    @Resource
    BrowsingHistoryServiceImpl browsingHistoryService;

    @PostMapping("/addAGoods")
    public Result<?> addAGoods(@RequestParam String goodsTitle,@RequestParam String goodsDescription,@RequestParam String goodsTypeId,@RequestParam String shopId,@RequestParam MultipartFile image1,@RequestParam MultipartFile image2,@RequestParam MultipartFile image3,@RequestParam MultipartFile image4,@RequestParam MultipartFile image5,@RequestParam String goodsSpecifications,@RequestParam String goodsBrand) throws IOException {
        //获取上传文件的文件名
        MultipartFile[] images=new MultipartFile[5];
        images[0]=image1;
        images[1]=image2;
        images[2]=image3;
        images[3]=image4;
        images[4]=image5;
        for(MultipartFile image:images){
            String fileName = image.getOriginalFilename();

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
        }
        Goods goods=new Goods();

        goods.setGoodsDescription(goodsDescription);
        goods.setGoodsTitle(goodsTitle);
        goods.setGoodsTypeId(Integer.parseInt(goodsTypeId));
        goods.setGoodsBrand(goodsBrand);
        goods.setShopId(Integer.parseInt(shopId));

        goods.setGoodsImage1(images[0].getOriginalFilename());
        goods.setGoodsImage2(images[1].getOriginalFilename());
        goods.setGoodsImage3(images[2].getOriginalFilename());
        goods.setGoodsImage4(images[3].getOriginalFilename());
        goods.setGoodsImage5(images[4].getOriginalFilename());

        goods.setShelfState(-1);

        goods.setGoodsSalesVolumn(0);

        goods.setGoodsSpecifications(goodsSpecifications);

        goodsService.save(goods);

        return Result.success(goods);
    }

    @PostMapping("/modifyGoods")
    public Result<?> modifyGoods(@RequestParam Integer goodsId,@RequestParam String goodsTitle,@RequestParam String goodsDescription,@RequestParam String goodsTypeId,@RequestParam String shopId,@RequestParam MultipartFile image1,@RequestParam MultipartFile image2,@RequestParam MultipartFile image3,@RequestParam MultipartFile image4,@RequestParam MultipartFile image5,@RequestParam String goodsSpecifications,@RequestParam String goodsBrand) throws IOException {
        //获取上传文件的文件名
        MultipartFile[] images=new MultipartFile[5];
        images[0]=image1;
        images[1]=image2;
        images[2]=image3;
        images[3]=image4;
        images[4]=image5;
        for(MultipartFile image:images){
            String fileName = image.getOriginalFilename();

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
        }
        Goods goods=new Goods();

        goods.setGoodsId(goodsId);
        goods.setGoodsDescription(goodsDescription);
        goods.setGoodsTitle(goodsTitle);
        goods.setGoodsTypeId(Integer.parseInt(goodsTypeId));
        goods.setGoodsBrand(goodsBrand);
        goods.setShopId(Integer.parseInt(shopId));

        goods.setGoodsImage1(images[0].getOriginalFilename());
        goods.setGoodsImage2(images[1].getOriginalFilename());
        goods.setGoodsImage3(images[2].getOriginalFilename());
        goods.setGoodsImage4(images[3].getOriginalFilename());
        goods.setGoodsImage5(images[4].getOriginalFilename());

        goods.setShelfState(-1);

        goods.setGoodsSalesVolumn(0);

        goods.setGoodsSpecifications(goodsSpecifications);

        goodsService.updateById(goods);

        return Result.success(goods);
    }


    @GetMapping("/selectByTypeId")
    public Result<?> selectByTypeId(@RequestParam Integer typeId){
        //参数一：当前页
        //参数二：页面大小


        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_type_id",typeId).eq("shelf_state",0);

        Page<Goods> objectPage=new Page<>(1,40);


        return Result.success(goodsService.page(objectPage, queryWrapper));
    }

    @GetMapping("/selectByPriceRange")
    public Result<?> selectByPriceRange(@RequestParam Integer minPrice,@RequestParam Integer maxPrice,@RequestParam Integer typeId,@RequestParam String orderColumn,@RequestParam Integer orderFlag,@RequestParam Integer currentPage){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();

        Page<Goods> objectPage=new Page<>(currentPage,40);

        if (orderColumn.equals("goodsSalesVolumn") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("goods_type_id", typeId).between("goods_price", minPrice, maxPrice).orderByDesc("goods_sales_volumn");
                } else {
                    queryWrapper.eq("goods_type_id", typeId).orderByDesc("goods_sales_volumn");
                }
            } else if (orderColumn.equals("goodsPrice") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    if (orderFlag == 1) {
                        queryWrapper.eq("goods_type_id", typeId).between("goods_price", minPrice, maxPrice).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("goods_type_id", typeId).between("goods_price", minPrice, maxPrice).orderByDesc("goods_price");
                    }
                }else {
                    if (orderFlag == 1) {
                        queryWrapper.eq("goods_type_id", typeId).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("goods_type_id", typeId).orderByDesc("goods_price");
                    }
                }
            } else {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("goods_type_id", typeId).between("goods_price", minPrice, maxPrice);
                }else {
                    queryWrapper.eq("goods_type_id", typeId);
                }
            }

        queryWrapper.eq("shelf_state",0);

        return Result.success(goodsService.page(objectPage, queryWrapper));
    }

    @GetMapping("/selectByGoodsId")
    public Result<?> selectByGoodsId(@RequestParam Integer goodsId){
        return Result.success(goodsService.getById(goodsId));
    }

    @GetMapping("/searchGoodsType")
    public Result<?> searchGoodsType(@RequestParam String searchItem){

        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).groupBy("goods_type_id").eq("shelf_state",0);


        List<Goods> list=new ArrayList<>();

        list=goodsService.list(queryWrapper);


        List<ThirdLevel> thirdLevels=new ArrayList<>();
        for(Goods goods:list){
            thirdLevels.add(thirdLevelService.getById(goods.getGoodsTypeId()));
        }

        return Result.success(thirdLevels);
    }

    @GetMapping("/searchGoods")
    public Result<?> searchGoods(@RequestParam String searchItem,@RequestParam Integer minPrice,@RequestParam Integer maxPrice,@RequestParam Integer typeId,@RequestParam String orderColumn,@RequestParam Integer orderFlag,@RequestParam Integer currentPage){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();

        Page<Goods> objectPage=new Page<>(currentPage,40);

        if(typeId!=0){
            if (orderColumn.equals("goodsSalesVolumn") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem).or().like("goods_description",searchItem)).between("goods_price", minPrice, maxPrice).orderByDesc("goods_sales_volumn");
                } else {
                    queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).orderByDesc("goods_sales_volumn");
                }
            } else if (orderColumn.equals("goodsPrice") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    if (orderFlag == 1) {
                        queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).between("goods_price", minPrice, maxPrice).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).between("goods_price", minPrice, maxPrice).orderByDesc("goods_price");
                    }
                }else {
                    if (orderFlag == 1) {
                        queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).orderByAsc("goods_price");
                    } else {
                        queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).orderByDesc("goods_price");
                    }
                }
            } else {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).between("goods_price", minPrice, maxPrice);
                }else {
                    queryWrapper.eq("goods_type_id", typeId).and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem));
                }
            }
        }else {
            if (orderColumn.equals("goodsSalesVolumn") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem).or().like("goods_description",searchItem)).between("goods_price", minPrice, maxPrice).orderByDesc("goods_sales_volumn");
                } else {
                    queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).orderByDesc("goods_sales_volumn");
                }
            } else if (orderColumn.equals("goodsPrice") == true) {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    if (orderFlag == 1) {
                        queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).between("goods_price", minPrice, maxPrice).orderByAsc("goods_price");
                    } else {
                        queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).between("goods_price", minPrice, maxPrice).orderByDesc("goods_price");
                    }
                }else {
                    if (orderFlag == 1) {
                        queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).orderByAsc("goods_price");
                    } else {
                        queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).orderByDesc("goods_price");
                    }
                }
            } else {
                if (minPrice != null && maxPrice != null && maxPrice != 0) {
                    queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem)).between("goods_price", minPrice, maxPrice);
                }else {
                    queryWrapper.and(wrapper -> wrapper.like("goods_title",searchItem).or().like("goods_brand",searchItem));
                }
            }
        }

        queryWrapper.eq("shelf_state",0);

        return Result.success(goodsService.page(objectPage, queryWrapper));
    }

    @GetMapping("/searchShopGoods")
    public Result<?> searchShopGoods(@RequestParam Integer shopId,@RequestParam Integer goodsId,@RequestParam String goodsTitle,@RequestParam Integer shelfState,@RequestParam Integer id,@RequestParam Integer typeLevel,@RequestParam Integer currentPage){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        Page<Goods> objectPage=new Page<>(currentPage,5);



        queryWrapper.eq("shop_id",shopId);
        if(goodsId!=0){
            queryWrapper.like("goods_id",goodsId);
        }
        if(!goodsTitle.equals("")){
            queryWrapper.like("goods_title",goodsTitle);
        }
        if(shelfState!=-2){
            System.out.println(shelfState);
            queryWrapper.eq("shelf_state",shelfState);
        }

        if(typeLevel==1){//一级分类查询
            QueryWrapper<SecondLevel> secondLevelQueryWrapper=new QueryWrapper<>();
            secondLevelQueryWrapper.eq("father_id",id);

            List<SecondLevel> list=secondLevelService.list(secondLevelQueryWrapper);

            String str="";
            for(int i=0;i<list.size();i++){
                if(i<list.size()-1){
                    str+=list.get(i).getId()+",";
                }else {
                    str+=list.get(i).getId();
                }
            }

            System.out.println(str);

            QueryWrapper<ThirdLevel> thirdLevelQueryWrapper=new QueryWrapper<>();
            thirdLevelQueryWrapper.inSql("father_id",str);
            List<ThirdLevel> thirdLevels=thirdLevelService.list(thirdLevelQueryWrapper);

            String str1="";
            for(int i=0;i<thirdLevels.size();i++){
                if(i<thirdLevels.size()-1){
                    str1+=thirdLevels.get(i).getId()+",";
                }else {
                    str1+=thirdLevels.get(i).getId();
                }
            }

            queryWrapper.inSql("goods_type_id",str1);
        }else if(typeLevel==2){//根据二级分类查询
            QueryWrapper<ThirdLevel> thirdLevelQueryWrapper=new QueryWrapper<>();
            thirdLevelQueryWrapper.eq("father_id",id);
            List<ThirdLevel> thirdLevels=thirdLevelService.list(thirdLevelQueryWrapper);

            String str1="";
            for(int i=0;i<thirdLevels.size();i++){
                if(i<thirdLevels.size()-1){
                    str1+=thirdLevels.get(i).getId()+",";
                }else {
                    str1+=thirdLevels.get(i).getId();
                }
            }
            System.out.println(str1);

            queryWrapper.inSql("goods_type_id",str1);
        }else if(typeLevel==3) {
            queryWrapper.eq("goods_type_id", id);
        }

        List<Goods> list=goodsService.page(objectPage, queryWrapper).getRecords();


        List<List<GoodsSpecifications>> goodsSpecificationsList=new ArrayList<>();
        List<ThirdLevel> thirdLevels=new ArrayList<>();

        for(Goods goods:list){
            QueryWrapper<GoodsSpecifications> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("goods_id",goods.getGoodsId());
            List<GoodsSpecifications> goodsSpecificationsList1=goodsSpecificationsService.list(queryWrapper1);

            thirdLevels.add(thirdLevelService.getById(goods.getGoodsTypeId()));

            goodsSpecificationsList.add(goodsSpecificationsList1);
        }


        Map<String,Object> map=new HashMap<>();
        map.put("page",goodsService.page(objectPage, queryWrapper));
        map.put("goodsSpecificationsList",goodsSpecificationsList);
        map.put("types", thirdLevels);

        return Result.success(map);
    }

    //--------------上架商品-----------------
    @PostMapping("/putOnShelf")
    public Result<?> putOnShelf(@RequestBody List<Goods> goodsList){
        for(Goods goods:goodsList){
            UpdateWrapper<Goods> updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("goods_id",goods.getGoodsId()).set("shelf_state",0);
            goodsService.update(updateWrapper);
        }
        return Result.success();
    }

    //--------------下架商品-----------------
    @PostMapping("/putOffShelf")
    public Result<?> putOffShelf(@RequestBody List<Goods> goodsList){
        for(Goods goods:goodsList){
            UpdateWrapper<Goods> updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("goods_id",goods.getGoodsId()).set("shelf_state",1);
            goodsService.update(updateWrapper);
        }
        return Result.success();
    }

    @GetMapping("/getBestSellerGoods")
    public Result<?> getBestSellerGoods(){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.last("limit 8").orderByDesc("goods_sales_volumn");
        return Result.success(goodsService.list(queryWrapper));
    }

    @GetMapping("/getInterestingGoods")
    public Result<?> getInterestingGoods(@RequestParam Integer userId){
        QueryWrapper<BrowsingHistory> browsingHistoryQueryWrapper=new QueryWrapper<>();
        browsingHistoryQueryWrapper.orderByDesc("time");
        if(userId!=0){
            browsingHistoryQueryWrapper.eq("user_id",userId);
        }

        List<BrowsingHistory> browsingHistories=browsingHistoryService.list(browsingHistoryQueryWrapper);

        String str1="";
        for(int i=0;i<browsingHistories.size();i++){
            if(i<browsingHistories.size()-1){
                str1+=browsingHistories.get(i).getGoodsId()+",";
            }else {
                str1+=browsingHistories.get(i).getGoodsId();
            }
        }

        QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
        goodsQueryWrapper.inSql("goods_id",str1).groupBy("goods_type_id");

        List<Goods> list=goodsService.list(goodsQueryWrapper);

        String str2="";
        for(int i=0;i<list.size();i++){
            if(i<list.size()-1){
                str2+=list.get(i).getGoodsTypeId()+",";
            }else {
                str2+=list.get(i).getGoodsTypeId();
            }
        }

        QueryWrapper<Goods> goodsQueryWrapper1=new QueryWrapper<>();
        goodsQueryWrapper1.inSql("goods_type_id",str2).last("limit 20");

        return Result.success(goodsService.list(goodsQueryWrapper1));
    }
    
}


