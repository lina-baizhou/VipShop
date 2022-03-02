package com.lina.market_project.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 白粥
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收藏的商品id
     */
    private Integer goodsId;


}
