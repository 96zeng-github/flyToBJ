package com.zeng.fly.biz.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zeng.fly.dal.hot.domain.HotDomainService;
import com.zeng.fly.dal.hot.entity.Hot;
import com.zeng.fly.facade.api.HotService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public void listAll() {
        List<Hot> hotList =
                hotDomainService.list(new LambdaQueryWrapper<Hot>());
        hotList.forEach(hot -> {
            System.out.println(hot.getId());
        });

    }
}
