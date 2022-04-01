package com.zeng.fly.biz.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zeng.fly.dal.hot.domain.HotDomainService;
import com.zeng.fly.dal.hot.entity.Hot;
import com.zeng.fly.facade.api.HotService;
import com.zeng.fly.facade.data.GetHotData;
import com.zeng.fly.facade.info.HotInfo;
import org.apache.commons.io.CopyUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengxingdeng
 * @data 2022/3/31
 * @des
 */
@DubboService(version = "1.0")
public class HotServiceImpl implements HotService {

    @Autowired
    private HotDomainService hotDomainService;


    @Override
    public GetHotData listAll() {
        List<Hot> hotList =
                hotDomainService.list(new LambdaQueryWrapper<Hot>());
        List<HotInfo> hotInfoList = hotList.stream().map(hot -> {
            HotInfo hotInfo = new HotInfo();
            BeanUtils.copyProperties(hot, hotInfo);
            return hotInfo;
        }).collect(Collectors.toList());
        GetHotData data = new GetHotData();
        data.setHotInfoList(hotInfoList);
        return data;
    }
}
