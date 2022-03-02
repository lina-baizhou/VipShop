package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("browsing_history")
public class BrowsingHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId("goods_id")
    private Integer goodsId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Integer shopId;

    /**
     * 浏览时间
     */
    @TableField("time")
    private LocalDateTime time;


    /**
     * 浏览时间
     */
    @TableField("deleted")
    private int deleted;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
