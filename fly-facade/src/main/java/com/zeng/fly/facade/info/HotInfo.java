package com.zeng.fly.facade.info;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zengxingdeng
 * @data 2022/4/1
 * @des
 */
@Setter
@Getter
public class HotInfo implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 关联ID
     */
    private Integer dataId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改时间
     */
    private Integer updateTime;

    /**
     * 分类类型（1：商品；2：盲盒）
     */
    private Integer type;

    /**
     * 删除标志（0：未删，1：已删）
     */
    private Boolean del;
}
