package com.zeng.fly.dal.hot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author: zengxingdeng
 * @date: 2022-03-30
 * @version: V1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("hot")
public class Hot implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id;

    /**
     * 关联ID
     */
	@TableField("data_id")
	private Integer dataId;

    /**
     * 排序
     */
	@TableField("sort")
	private Integer sort;

    /**
     * 创建时间
     */
	@TableField("create_time")
	private Integer createTime;

    /**
     * 修改时间
     */
	@TableField("update_time")
	private Integer updateTime;

    /**
     * 分类类型（1：商品；2：盲盒）
     */
	@TableField("type")
	private Integer type;

    /**
     * 删除标志（0：未删，1：已删）
     */
	@TableField("del")
	private Boolean del;

    /********************* 静态字段 ************************/
    /**
     * id
     */
    public static final String ID = "id";
    /**
     * 关联ID
     */
    public static final String DATA_ID = "data_id";
    /**
     * 排序
     */
    public static final String SORT = "sort";
    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "create_time";
    /**
     * 修改时间
     */
    public static final String UPDATE_TIME = "update_time";
    /**
     * 分类类型（1：商品；2：盲盒）
     */
    public static final String TYPE = "type";
    /**
     * 删除标志（0：未删，1：已删）
     */
    public static final String DEL = "del";

}
