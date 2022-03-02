package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 白粥
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsSpecificationsList implements Serializable {
    private List<GoodsSpecifications> list;
}
