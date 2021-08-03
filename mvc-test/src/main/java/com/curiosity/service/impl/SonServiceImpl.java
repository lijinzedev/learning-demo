package com.curiosity.service.impl;

import com.curiosity.base.BaseServiceImpl;
import com.curiosity.dao.SonMapper;
import com.curiosity.model.model.Son;
import com.curiosity.service.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname User2ServiceImpl
 * @Description
 * @Date 2021/8/2 14:44
 * @Created by lijinze
 */
@Service
@RequiredArgsConstructor
public class SonServiceImpl extends BaseServiceImpl<SonMapper, Son> implements Handler<Son> {


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Son user) {
        baseMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addAndThrowException(Son user) {
        baseMapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNew(Son user) {
        baseMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNewAndThrowException(Son user) {
        baseMapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(Son user) {
        baseMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNestedException(Son user) {
        baseMapper.insert(user);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addNotSupport() {
        final Son user1 = new Son();
        user1.setId(1);
        user1.setName("tom son ");
        baseMapper.insert(user1);
        throw new RuntimeException();
    }
}
