package com.zeng.fly.web;

import com.zeng.fly.facade.api.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengxingdeng
 * @data 2022/3/31
 * @des
 */
@RestController
@RequestMapping("/hot")
public class HotController {

    @Autowired
    private HotService hotService;

    @RequestMapping
    public void listAll() {
        hotService.listAll();
    }
}
