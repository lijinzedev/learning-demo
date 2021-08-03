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
public class TestServiceRequired {
    @Resource(name = "fatherServiceImpl")
    private FatherServiceImpl fatherServiceImpl;
    @Resource(name = "sonServiceImpl")
    private SonServiceImpl sonServiceImpl;

    /**
     * 测试没有父事务且抛出异常
     *
     * @param father
     * @param son
     */
    public void testWithoutTransaction(Father father, Son son) {
        fatherServiceImpl.add(father);
        sonServiceImpl.addAndThrowException(son);
    }

    /**     * 测试有父事务且抛出异常
     *
     * @param father
     * @param son
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testWithTransaction(Father father, Son son) {
        fatherServiceImpl.add(father);
        sonServiceImpl.addAndThrowException(son);
    }

    /**
     * 测试有父事务且抛出异常
     *
     * @param father
     * @param son
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testWithTransactionAndThrow(Father father, Son son) {
        fatherServiceImpl.add(father);
        sonServiceImpl.add(son);
        throw new RuntimeException();
    }
    /**
     * 测试有父事务且抛出异常
     *
     * @param father
     * @param son
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testWithTransactionAndTry(Father father, Son son) {
        fatherServiceImpl.add(father);
        try {
            sonServiceImpl.addAndThrowException(son);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
