package com.curiosity.service.impl;

import com.curiosity.base.BaseServiceImpl;
import com.curiosity.dao.FatherMapper;
import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import com.curiosity.service.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname User1ServiceImpl
 * @Description
 * @Date 2021/8/2 14:44
 * @Created by lijinze
 */
@Service
@RequiredArgsConstructor
public class FatherServiceImpl extends BaseServiceImpl<FatherMapper, Father> implements Handler<Father> {
    private final SonServiceImpl service;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Father father) {
        baseMapper.insert(father);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addAndThrowException(Father father) {
        baseMapper.insert(father);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNew(Father user) {
        baseMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNewAndThrowException(Father user) {
        baseMapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(Father user) {
        baseMapper.insert(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)

    public void addNestedAndAddSon() {
        final Father entity2 = new Father();
        entity2.setId(2);
        entity2.setName("tom2");
        baseMapper.insert(entity2);
        service.addNotSupport();

    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNestedException(Father user) {
        baseMapper.insert(user);
        throw new RuntimeException();
    }

}
