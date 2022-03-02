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
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_details")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("order_number")
    private Integer orderNumber;

    @TableField("order_state")
    private String orderState;

    @TableField("order_remarks")
    private String orderRemarks;

    @TableField("harvest_id")
    private Integer harvestId;
}
