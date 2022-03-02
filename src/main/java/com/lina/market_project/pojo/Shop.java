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
 * @since 2021-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("shop")
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 店铺id
     */
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 店铺名称
     */
    @TableField("shop_name")
    private String shopName;

    /**
     * 店铺logo
     */
    @TableField("shop_logo")
    private String shopLogo;

    /**
     * 店铺评分
     */
    @TableField("shop_count")
    private float shopCount;

    /**
     * 店铺粉丝数
     */
    @TableField("fans_number")
    private Integer fansNumber;

    /**
     * 店铺描述
     */
    @TableField("shop_description")
    private String shopDescription;

    /**
     * 店铺地址
     */
    @TableField("shop_address")
    private String shopAddress;

    /**
     * 店铺描述评分
     */
    @TableField("shop_description_count")
    private Float shopDescriptionCount;

    /**
     * 店铺服务评分
     */
    @TableField("shop_service_count")
    private Float shopServiceCount;

    /**
     * 店铺物流评分
     */
    @TableField("shop_logistics_count")
    private Float shopLogisticsCount;

    /**
     * 邮费
     */
    @TableField("freight")
    private Float freight;

    /**
     * 包邮的价格
     */
    @TableField("free_delivery")
    private Float freeDelivery;

    /**
     * 商品浏览量
     */
    @TableField("browse_count")
    private Integer browseCount;

    /**
     * 商品销量
     */
    @TableField("sale_count")
    private Integer saleCount;

    /**
     * 商品销售额
     */
    @TableField("sale_account")
    private float saleAccount;

    /**
     * 商品粉丝数
     */
    @TableField("goods_fans_count")
    private Integer goodsFansCount;
}
