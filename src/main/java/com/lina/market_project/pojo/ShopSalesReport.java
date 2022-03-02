package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.Version;
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
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("shop_sales_report")
public class ShopSalesReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("shop_id")
    private Integer shopId;

    @TableField("time")
    private LocalDate time;

    @TableField("sales_count")
    private Integer salesCount;

    @TableField("sales_account")
    private Float salesAccount;
}
