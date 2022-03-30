package com.zeng.fly.dal.hot.domain.impl;

import com.zeng.fly.dal.hot.domain.HotDomainService;
import com.zeng.fly.dal.hot.entity.Hot;
import com.zeng.fly.dal.hot.mapper.HotMapper;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: zengxingdeng
 * @date: 2022-03-30
 * @version: V1.0
 **/
@Slf4j
@Service
public class HotDomainServiceImpl extends ServiceImpl<HotMapper, Hot> implements HotDomainService {

}

