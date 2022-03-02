package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品id
     */
    @TableField("goods_title")
    private String goodsTitle;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Integer shopId;

    /**
     * 商品三级分类id
     */
    @TableField("goods_type_id")
    private Integer goodsTypeId;

    /**
     * 商品描述
     */
    @TableField("goods_description")
    private String goodsDescription;

    /**
     * 商品主图
     */
    @TableField("goods_image1")
    private String goodsImage1;

    /**
     * 商品图片2
     */
    @TableField("goods_image2")
    private String goodsImage2;

    /**
     * 商品图片3
     */
    @TableField("goods_image3")
    private String goodsImage3;

    /**
     * 商品图片4
     */
    @TableField("goods_image4")
    private String goodsImage4;

    /**
     * 商品正面图
     */
    @TableField("goods_image5")
    private String goodsImage5;

    /**
     * 商品规格
     */
    @TableField("goods_specifications")
    private String goodsSpecifications;

    /**
     * 商品销量
     */
    @TableField("goods_sales_volumn")
    private Integer goodsSalesVolumn;

    /**
     * 商品品牌
     */
    @TableField("goods_brand")
    private String goodsBrand;

    /**
     * 商品最低价格
     */
    @TableField("goods_price")
    private Float goodsPrice;

    /**
     * 上架状态
     */
    @TableField("shelf_state")
    private Integer shelfState;
}
