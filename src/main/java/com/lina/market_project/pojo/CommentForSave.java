package com.lina.market_project.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentForSave {
    private Integer userId;
    private Integer goodsId;
    private Integer shopId;
    private String content;
    private Integer descriptionCount;
    private Integer serviceCount;
    private Integer logisticsCount;
}
