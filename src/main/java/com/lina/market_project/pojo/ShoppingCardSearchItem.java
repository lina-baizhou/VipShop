package com.lina.market_project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCardSearchItem {
    private Integer userId;
    private Integer goodsId;
    private Integer specificationsId;
}
