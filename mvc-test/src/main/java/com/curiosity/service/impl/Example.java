package com.curiosity.service.impl;

import com.curiosity.dao.FatherMapper;
import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Classname Example
 * @Description
 * @Date 2021/8/3 17:02
 * @Created by lijinze
 */
@Service
@RequiredArgsConstructor
public class Example {
    @Resource(name = "fatherServiceImpl")
    private FatherServiceImpl fatherServiceImpl;
    @Resource
    private FatherMapper fatherMapper;

    /**
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void example() {
        final Father entity = new Father();
        entity.setId(1);
        entity.setName("tom");
        fatherMapper.insert(entity);
        fatherServiceImpl.addNestedAndAddSon();

    }
}
