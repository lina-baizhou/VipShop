package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 白粥
 * @since 2021-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("shopping_cart")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 加入购物车时间
     */
    @TableId("time")
    private LocalDateTime time;

    /**
     * 数量
     */
    @TableField("count")
    private Integer count;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Integer shopId;

    /**
     * 商品规格id
     */
    @TableField("specifications_id")
    private Integer specificationsId;

}
