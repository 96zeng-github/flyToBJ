package com.zeng.fly.test;

import com.zeng.fly.facade.api.HotService;
import com.zeng.fly.facade.data.GetHotData;
import com.zeng.fly.facade.info.HotInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zengxingdeng
 * @data 2022/4/1
 * @des
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestHotService.class)
@EnableAutoConfiguration
@EnableDiscoveryClient//开启服务注册发现功能
public class TestHotService {

    @DubboReference(version = "1.0")
    HotService hotService;

    @Test
    public void testHot() {
        GetHotData hotData = hotService.listAll();
        List<HotInfo> infoList = hotData.getHotInfoList();
        infoList.forEach(hotInfo -> {
            System.out.println(hotInfo.getId());
        });
    }
}
