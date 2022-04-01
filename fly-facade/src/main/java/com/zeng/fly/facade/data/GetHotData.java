package com.zeng.fly.facade.data;

import com.zeng.fly.facade.info.HotInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author zengxingdeng
 * @data 2022/4/1
 * @des
 */
@Setter
@Getter
public class GetHotData implements Serializable {

    private List<HotInfo> hotInfoList;
}
