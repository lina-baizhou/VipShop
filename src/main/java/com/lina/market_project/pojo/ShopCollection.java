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
 * @since 2021-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("shop_collection")
public class ShopCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("shop_id")
    private Integer shopId;

    @TableField("user_id")
    private Integer userId;


}
