package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 白粥
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HarvestAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址id
     */
    @TableId(value = "harvest_id", type = IdType.AUTO)
    private Integer harvestId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 地址
     */
    private String harvestAddress;

    /**
     * 地址类型
     */
    private String harvestType;

    /**
     * 邮箱
     */
    private String harvestEmail;

    /**
     * 是否为默认
     */
    private Integer isDefault;

    /**
     * 收货人姓名
     */
    private String harvestName;
}
