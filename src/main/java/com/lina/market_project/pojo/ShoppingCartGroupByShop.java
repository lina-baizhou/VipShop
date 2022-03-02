package com.lina.market_project.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShoppingCartGroupByShop {
    /**
     * 店铺
     */
    private Shop shop;

    /**
     * 加入购物车的店铺商品
     */
    private List<Goods> goodsList;

    /**
     * 加入购物车里的商品规格
     */
    private List<GoodsSpecifications> goodsSpecificationsList;

    /**
     * 加入购物车的商品的数量
     */
    private List<Integer> goodsCountList;
}
