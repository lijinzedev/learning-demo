package com.curiosity.service.impl;

import com.curiosity.base.BaseServiceImpl;
import com.curiosity.dao.User1Mapper;
import com.curiosity.model.model.Father;
import com.curiosity.service.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Classname User1ServiceImpl
 * @Description
 * @Date 2021/8/2 14:44
 * @Created by lijinze
 */
@Service
@RequiredArgsConstructor
public class FatherServiceImpl extends BaseServiceImpl<User1Mapper, Father> implements Handler<Father> {


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Father father) {
        baseMapper.insert(father);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addAndThrowException(Father father) {

    }
}
