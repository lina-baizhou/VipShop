package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
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
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_specifications")
public class GoodsSpecifications implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 规格id
     */
    @TableId("specifications_id")
    private Integer specificationsId;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;

    /**
     * 商品规格1
     */
    @TableField("goods_type1")
    private String goodsType1;

    /**
     * 商品规格2
     */
    @TableField("goods_type2")
    private String goodsType2;

    /**
     * 商品库存
     */
    @TableField("goods_stoke")
    private Integer goodsStoke;

    /**
     * 商品价格
     */
    @TableField("goods_price")
    private Float goodsPrice;
}
