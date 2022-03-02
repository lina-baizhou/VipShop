package com.lina.market_project.pojo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShoppingOrderGroupByShop {
    private ShopOrder shopOrder;
    private List<Goods> goodsList;
    private Shop shop;
    private List<GoodsSpecifications> goodsSpecificationsList;
    private List<Integer> goodsCountList;
    private List<Integer> goodsBeforeCountList;
}
