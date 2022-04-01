package com.zeng.fly.facade.api;

import com.zeng.fly.facade.data.GetHotData;

/**
 * @author zengxingdeng
 * @data 2022/3/31
 * @des
 */
public interface HotService {

    /**
     * 获取所有
     * @return
     */
    GetHotData listAll();
}
