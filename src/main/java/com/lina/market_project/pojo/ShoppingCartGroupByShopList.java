package com.lina.market_project.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShoppingCartGroupByShopList {
   private List<ShoppingCartGroupByShop> shoppingCartGroupByShopList;
   private HarvestAddress harvestAddress;
   private String url;
}
