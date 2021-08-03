package com.curiosity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.curiosity.Main;
import com.curiosity.dao.FatherMapper;
import com.curiosity.dao.SonMapper;
import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import com.curiosity.service.impl.TestServiceRequired;
import com.curiosity.service.impl.TestServiceRequiredNew;
import com.curiosity.service.impl.TruncateDatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest(classes = {Main.class})
class TestRequiredNew {
    @Autowired
    TestServiceRequiredNew testServiceRequiredNew;
    @Autowired
    TruncateDatabaseService truncateDatabaseService;
    @Autowired
    FatherMapper fatherMapper;
    @Autowired
    SonMapper sonMapper;

    @BeforeEach
    public void init() throws SQLException {
        truncateDatabaseService.truncate();
    }
    @AfterEach
    public void result() {
        final List<Father> fathers = fatherMapper.selectList(new QueryWrapper<>());
        final List<Son> sons = sonMapper.selectList(new QueryWrapper<>());
        System.out.println("-----------------"+"fathers is "+fathers+"sons is "+sons);
    }

    @Test
    public void testTransactionExceptionRequiredRequiresNewRequiresNew() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        final Son son2 = new Son();
        son2.setId(2);
        son2.setName("joker son and 2");
        testServiceRequiredNew.transactionExceptionRequiredRequiresNewRequiresNew(father, son,son2);
    }
    @Test
    public void testTransactionRequiredRequiresNewRequiresNewExceptionTry() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        final Son son2 = new Son();
        son2.setId(2);
        son2.setName("joker son and 2");
        testServiceRequiredNew.transactionRequiredRequiresNewRequiresNewExceptionTry(father, son,son2);
    }


    @Test
    public void testTransactionRequiredRequiresNewRequiresNewException() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        final Son son2 = new Son();
        son2.setId(2);
        son2.setName("joker son and 2");
        testServiceRequiredNew.transactionRequiredRequiresNewRequiresNewException(father, son,son2);
    }
}




