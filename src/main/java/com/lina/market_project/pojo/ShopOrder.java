package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2021-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("shop_order")
public class ShopOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableId(value = "order_number", type = IdType.AUTO)
    private Integer orderNumber;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 地址id
     */
    @TableField("harvest_id")
    private Integer harvestId;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private String goodsId;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Integer shopId;

    /**
     * 时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * 商品销售量
     */
    @TableField("goods_count")
    private String goodsCount;

    /**
     * 商品实际剩余数量
     */
    @TableField("goods_real_count")
    private String goodsRealCount;


    /**
     * 商品规格id
     */
    @TableField("specifications_id")
    private String specificationsId;

    /**
     * 订单状态
     */
    @TableField("order_state")
    private String orderState;

    /**
     * 订单备注
     */
    @TableField("order_remarks")
    private String orderRemarks;

    /**
     * 商品销售额
     */
    @TableField("goods_sales_volume")
    private Float goodsSalesVolume;

    /**
     * 逻辑删除
     */
    @TableField("deleted")
    private Integer deleted;

    /**
     * 支付订单号
     */
    @TableField("trade_no")
    private String tradeNo;

    /**
     * 退款进度
     */
    @TableField("return_flag")
    private String returnFlag;

    /**
     * 换货进度
     */
    @TableField("change_flag")
    private String changeFlag;

    /**
     * 店铺逻辑删除
     */
    @TableField("shop_deleted")
    private Integer shopDeleted;
}

