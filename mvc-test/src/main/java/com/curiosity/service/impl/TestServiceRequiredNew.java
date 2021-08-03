package com.curiosity.service.impl;

import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Classname TestService
 * @Description
 * @Date 2021/8/2 14:58
 * @Created by lijinze
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceRequiredNew {
    @Resource(name = "fatherServiceImpl")
    private FatherServiceImpl fatherServiceImpl;
    @Resource(name = "sonServiceImpl")
    private SonServiceImpl sonServiceImpl;

    /**
     * 外部方法抛出异常并且带有事物
     * @param father
     * @param son1
     * @param son2
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequiresNewRequiresNew(Father father, Son son1, Son son2) {
        fatherServiceImpl.add(father);
        sonServiceImpl.add(son1);
        sonServiceImpl.addRequiredNew(son2);
        throw new RuntimeException();
    }


    /**
     * 外部方法有事物内部方法抛出异常
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiresNewRequiresNewException(Father father, Son son1, Son son2) {
        fatherServiceImpl.add(father);
        sonServiceImpl.addRequiredNew(son1);
        sonServiceImpl.addRequiredNewAndThrowException(son2);
    }


    /**
     * 外部方法有事物内部方法抛出异常且外部方法捉住异常
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void  transactionRequiredRequiresNewRequiresNewExceptionTry(Father father, Son son1, Son son2) {
        fatherServiceImpl.add(father);
        sonServiceImpl.addRequiredNew(son1);
        try {
            sonServiceImpl.addRequiredNewAndThrowException(son2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
