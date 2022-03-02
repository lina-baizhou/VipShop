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
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FirstLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 一级分类id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 一级分类名称
     */
    private String type;
}
