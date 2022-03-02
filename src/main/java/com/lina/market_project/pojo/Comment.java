package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.annotation.MultipartConfig;

/**
 * <p>
 * 
 * </p>
 *
 * @author 白粥
 * @since 2021-09-06
 */
@Data


@EqualsAndHashCode(callSuper = false)
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableField("order_number")
    private Integer orderNumber;

    /**
     * 商品规格id
     */
    @TableField("specifications_id")
    private Integer specificationsId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;

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
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论照片
     */
    @TableField("comment_photo")
    private String commentPhoto;

    /**
     * 追加评论
     */
    @TableField("additional_comment")
    private String additionalComment;

    /**
     * 追加评论的照片
     */
    @TableField("additional_photo")
    private String additionalPhoto;

    /**
     * 追加评论的时间
     */
    @TableField("additional_time")
    private String additionalTime;


    /**
     * 商家回复
     */
    @TableField("seller_restore")
    private String sellerRestore;

    /**
     * 描述评分
     */
    @TableField("description_count")
    private Integer descriptionCount;

    /**
     * 服务评分
     */
    @TableField("service_count")
    private Integer serviceCount;

    /**
     * 物流评分
     */
    @TableField("logistics_count")
    private Integer logisticsCount;
}
