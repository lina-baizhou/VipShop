package com.lina.market_project;


import com.lina.market_project.config.LocalUtil;
import com.lina.market_project.pojo.FirstLevel;
import com.lina.market_project.pojo.SecondLevel;

import com.lina.market_project.pojo.ThirdLevel;
import com.lina.market_project.service.IFirstLevelService;
import com.lina.market_project.service.ISecondLevelService;
import com.lina.market_project.service.IThirdLevelService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class MarketProjectApplicationTests {
    @Resource
    LocalUtil localUtil;
    @Test
    void contextLoads() {

        List<String> str = new ArrayList<>();
        List<String> list = localUtil.getCities("中国","湖南");
        for (int i = 0; i < list.size(); i++) {
            str.add(list.get(i) + " ");
        }
        System.out.println(str);
    }

    @Resource
    IFirstLevelService firstLevelService;
    @Test
    void insertFirstLevel(){
        FirstLevel firstLevel=new FirstLevel();
        firstLevel.setType("医药健康");
        firstLevelService.save(firstLevel);

        Collection<SecondLevel> collection=new ArrayList<>();



        collection.add(new SecondLevel(null,11,"隐形眼镜"));
        collection.add(new SecondLevel(null,11,"计生用品"));
        collection.add(new SecondLevel(null,11,"医疗器械"));
//        collection.add(new SecondLevel(null,10,"茶叶"));
//        collection.add(new SecondLevel(null,10,"冲调饮料"));
//        collection.add(new SecondLevel(null,10,"传统滋补"));
//        collection.add(new SecondLevel(null,10,"食材"));
//        collection.add(new SecondLevel(null,10,"宠物"));





        secondLevelService.saveBatch(collection);


        Collection<ThirdLevel> collection1=new ArrayList<ThirdLevel>();


        addTo("隐形眼镜/白片 护理液 美瞳/彩片 ",collection1,86);

        addTo("避孕套 润滑液 验孕备孕 成人情趣类 ",collection1,87);

        addTo("康复理疗 血压计 体温计 呼吸机/制氧机 助行器具 血糖用品 ",collection1,88);

//        addTo("花草茶 绿茶 茶具 普洱茶 乌龙茶 红茶 白/黑/黄茶 铁观音 龙井 ",collection1,81);
//
//        addTo("冲饮 饮料 蜂蜜 即食粥 牛奶豆奶 奶粉 麦片谷粉 咖啡 固体饮料 饮用水 ",collection1,82);
//
//        addTo("药食同源 花胶 阿胶 燕窝 海参 ",collection1,83);
//
//        addTo("米面/面粉 食用油 方便速食 调味品 南北干货 杂粮 ",collection1,84);
//
//        addTo("宠物零食 宠物日用 宠物保健 宠物美容 狗粮 猫粮 出行装备 宠物玩具 ",collection1,85);

//        addTo("女袜 男袜 防晒袖 丝袜/裤袜 手套 ",collection1,69);
//
//        addTo("真丝丝巾 披肩/大方巾 女士围巾 羊毛围巾 羊绒围巾 男士围巾 ",collection1,70);

//        addTo("牙膏 漱口水 牙刷/牙线 电动牙刷 护理套装",collection1,35);
//
//        addTo("卫生巾 私处护理 丰胸美胸 护垫/棉条 胸部护理套装 ",collection1,36);
//
//        addTo("颈部护理 身体护理工具 身体喷雾 手部护理 提脱毛器 香薰精油 脱毛止汗 足部护理 ",collection1,37);

        thirdLevelService.saveBatch(collection1);
    }

    @Resource
    ISecondLevelService secondLevelService;
    @Test
    void insertSecondLevel(){
        Collection<SecondLevel> collection=new ArrayList<>();

        collection.add(new SecondLevel(1,2,"人气美衣"));
        collection.add(new SecondLevel(2,2,"美裙衣橱"));
        collection.add(new SecondLevel(3,2,"百搭上衣"));
        collection.add(new SecondLevel(4,2,"外套"));
        collection.add(new SecondLevel(5,2,"裤子"));
        collection.add(new SecondLevel(6,2,"特色服饰"));
        collection.add(new SecondLevel(7,2,"男士内搭"));
        collection.add(new SecondLevel(8,2,"男士外套"));
        collection.add(new SecondLevel(9,2,"男士裤装"));


        secondLevelService.saveBatch(collection);
    }

    Collection<ThirdLevel> addTo(String string,Collection<ThirdLevel> collection1,int id){
        String[] strs=string.split(" ");

        for(String str:strs){
            collection1.add(new ThirdLevel(null,id,str));
        }
        return collection1;
    }

    @Resource
    IThirdLevelService thirdLevelService;
    @Test
    void insertThirdLevel(){
        Collection<ThirdLevel> collection=new ArrayList<>();


        collection.add(new ThirdLevel(null,1,"防晒衣"));
        collection.add(new ThirdLevel(null,1,"短裤"));
        collection.add(new ThirdLevel(null,1,"牛仔裤"));
        collection.add(new ThirdLevel(null,1,"妈妈装"));
        collection.add(new ThirdLevel(null,1,"大码女装"));
        collection.add(new ThirdLevel(null,1,"外套"));

        collection.add(new ThirdLevel(null,2,"连衣裙"));
        collection.add(new ThirdLevel(null,2,"半身裙"));
        collection.add(new ThirdLevel(null,2,"套装裙"));
        collection.add(new ThirdLevel(null,2,"修身美裙"));
        collection.add(new ThirdLevel(null,2,"白色连衣裙"));
        collection.add(new ThirdLevel(null,2,"旗袍"));


        collection.add(new ThirdLevel(null,3,"T恤"));
        collection.add(new ThirdLevel(null,3,"衬衫"));
        collection.add(new ThirdLevel(null,3,"针织衫"));
        collection.add(new ThirdLevel(null,3,"卫衣"));
        collection.add(new ThirdLevel(null,3,"羊绒/羊毛衫"));
        collection.add(new ThirdLevel(null,3,"毛衣"));

//        外套
//        棉衣 毛呢外套 羽绒服 西装外套 马甲 针织外套
        collection.add(new ThirdLevel(null,4,"棉衣"));
        collection.add(new ThirdLevel(null,4,"毛呢外套"));
        collection.add(new ThirdLevel(null,4,"羽绒服"));
        collection.add(new ThirdLevel(null,4,"西装外套"));
        collection.add(new ThirdLevel(null,4,"马甲"));
        collection.add(new ThirdLevel(null,4,"针织外套"));
//
//        裤子
//        短裤 休闲裤 阔腿裤 打底裤 连体/背带裤 哈伦裤
        collection.add(new ThirdLevel(null,5,"短裤"));
        collection.add(new ThirdLevel(null,5,"休闲裤"));
        collection.add(new ThirdLevel(null,5,"阔腿裤"));
        collection.add(new ThirdLevel(null,5,"打底裤"));
        collection.add(new ThirdLevel(null,5,"连体/背带裤"));
        collection.add(new ThirdLevel(null,5,"哈伦裤"));

//
//        特色服饰
//        中/老年女装 大码女装 商场同款 设计师 民族风 礼服/旗袍
        collection.add(new ThirdLevel(null,6,"中/老年女装"));
        collection.add(new ThirdLevel(null,6,"大码女装"));
        collection.add(new ThirdLevel(null,6,"商场同款"));
        collection.add(new ThirdLevel(null,6,"设计师"));
        collection.add(new ThirdLevel(null,6,"民族风"));
        collection.add(new ThirdLevel(null,6,"礼服/旗袍"));
//
//        男式内搭
//        短袖T恤 Polo衫 衬衫 长袖T恤 短袖衬衫 长袖衬衫 薄衬衫 V领T恤 条纹T恤 棉麻T恤 纯色T恤 印花T恤
//        纯色衬衫 格子衬衫 亚麻衬衫 棉麻衬衫 薄款卫衣 连帽卫衣 套头卫衣 卫衣
        collection.add(new ThirdLevel(null,7,"短袖T恤"));
        collection.add(new ThirdLevel(null,7,"Polo衫"));
        collection.add(new ThirdLevel(null,7,"衬衫"));
        collection.add(new ThirdLevel(null,7,"长袖T恤"));
        collection.add(new ThirdLevel(null,7,"短袖衬衫"));
        collection.add(new ThirdLevel(null,7,"长袖衬衫"));
        collection.add(new ThirdLevel(null,7,"薄衬衫"));
        collection.add(new ThirdLevel(null,7,"V领T恤"));
        collection.add(new ThirdLevel(null,7,"条纹T恤"));
        collection.add(new ThirdLevel(null,7,"棉麻T恤"));
        collection.add(new ThirdLevel(null,7,"纯色T恤"));
        collection.add(new ThirdLevel(null,7,"印花T恤"));

        collection.add(new ThirdLevel(null,7,"纯色衬衫"));
        collection.add(new ThirdLevel(null,7,"格子衬衫"));
        collection.add(new ThirdLevel(null,7,"亚麻衬衫"));
        collection.add(new ThirdLevel(null,7,"棉麻衬衫"));
        collection.add(new ThirdLevel(null,7,"薄款卫衣"));
        collection.add(new ThirdLevel(null,7,"连帽卫衣"));
        collection.add(new ThirdLevel(null,7,"套头卫衣"));
        collection.add(new ThirdLevel(null,7,"卫衣"));
//
//        男式外套
//        西装 风衣 夹克 马甲/背心 套装 皮肤衣 牛仔外套
//        老人马甲 棒球服 薄外套 外套 皮衣 厚外套 棉衣 大衣 短款外套 厚羽绒 中长款外套 夹克外套 针织外套
        collection.add(new ThirdLevel(null,8,"西装"));
        collection.add(new ThirdLevel(null,8,"风衣"));
        collection.add(new ThirdLevel(null,8,"夹克"));
        collection.add(new ThirdLevel(null,8,"马甲/背心"));
        collection.add(new ThirdLevel(null,8,"套装"));
        collection.add(new ThirdLevel(null,8,"皮肤衣"));
        collection.add(new ThirdLevel(null,8,"套头卫衣"));
        collection.add(new ThirdLevel(null,8,"牛仔外套"));

        collection.add(new ThirdLevel(null,8,"老人马甲"));
        collection.add(new ThirdLevel(null,8,"棒球服"));
        collection.add(new ThirdLevel(null,8,"薄外套"));
        collection.add(new ThirdLevel(null,8,"外套"));
        collection.add(new ThirdLevel(null,8,"皮衣"));
        collection.add(new ThirdLevel(null,8,"厚外套"));
        collection.add(new ThirdLevel(null,8,"棉衣"));
        collection.add(new ThirdLevel(null,8,"大衣"));

        collection.add(new ThirdLevel(null,8,"短款外套"));
        collection.add(new ThirdLevel(null,8,"厚羽绒"));
        collection.add(new ThirdLevel(null,8,"中长款外套"));
        collection.add(new ThirdLevel(null,8,"夹克外套"));
        collection.add(new ThirdLevel(null,8,"针织外套"));
//

//
//        男式裤装
//        休闲裤 牛仔裤 短裤 西裤 运动裤 工装裤 束脚裤 小脚裤 九分裤 长裤 哈伦裤
        collection.add(new ThirdLevel(null,9,"休闲裤"));
        collection.add(new ThirdLevel(null,9,"牛仔裤"));
        collection.add(new ThirdLevel(null,9,"短裤"));
        collection.add(new ThirdLevel(null,9,"西裤"));
        collection.add(new ThirdLevel(null,9,"运动裤"));
        collection.add(new ThirdLevel(null,9,"工装裤"));
        collection.add(new ThirdLevel(null,9,"束脚裤"));
        collection.add(new ThirdLevel(null,9,"小脚裤"));
        collection.add(new ThirdLevel(null,9,"九分裤"));
        collection.add(new ThirdLevel(null,9,"长裤"));
        collection.add(new ThirdLevel(null,9,"哈伦裤"));

        thirdLevelService.saveBatch(collection);
    }

}
