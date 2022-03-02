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
 * @since 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("return_order")
public class ReturnOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("order_number")
    private Integer orderNumber;

    @TableField("goods_index")
    private Integer goodsIndex;

    @TableField("goods_count")
    private Integer goodsCount;

    @TableField("goods_price")
    private float goodsPrice;

    @TableField("return_cause")
    private String returnCause;
}
