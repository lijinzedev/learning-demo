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
public class TestServiceNested {
    @Resource(name = "fatherServiceImpl")
    private FatherServiceImpl fatherServiceImpl;
    @Resource(name = "sonServiceImpl")
    private SonServiceImpl sonServiceImpl;


    /**
     * 外围方法没有事物内部方法嵌套事物外部方法抛出异常
     *
     * @param father
     * @param son
     */

    public void withoutTransactionExceptionNestedNested(Father father, Son son) {
        fatherServiceImpl.addNested(father);
        sonServiceImpl.addNested(son);
        throw new RuntimeException();
    }

    /**
     * 外围方法没有事物内部方法嵌套事物内部方法抛出异常
     * @param father
     * @param son
     */
    public void withoutTransactionNestedNestedException(Father father, Son son) {
        fatherServiceImpl.addNested(father);
        sonServiceImpl.addNestedException(son);
    }

    /**
     * 外部方法有事物 外部方法抛出异常
     * @param father
     * @param son
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionNestedNested(Father father, Son son) {
        fatherServiceImpl.addNested(father);
        sonServiceImpl.addNested(son);
        throw new RuntimeException();
    }
    /**
     * 外部方法有事物 内部方法抛出异常
     * @param father
     * @param son
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionNestedNestedException(Father father, Son son) {
        fatherServiceImpl.addNested(father);
        sonServiceImpl.addNestedException(son);
    }

    /**
     * 外部方法有事物 内部方法抛出异常
     * @param father
     * @param son
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionNestedNestedExceptionTry(Father father, Son son) {
        fatherServiceImpl.addNested(father);
        try {
            sonServiceImpl.addNestedException(son);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
