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
 * @since 2021-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("exchange_order")
public class ExchangeOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("order_number")
    private Integer orderNumber;

    @TableField("specifications_id")
    private Integer specificationsId;

    @TableField("goods_index")
    private Integer goodsIndex;


    @TableField("goods_count")
    private Integer goodsCount;

    @TableField("exchange_cause")
    private String exchangeCause;

    @TableField("exchange_order_number")
    private String exchangeOrderNumber;
}
