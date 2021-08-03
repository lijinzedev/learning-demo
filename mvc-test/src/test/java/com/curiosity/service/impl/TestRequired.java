package com.curiosity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.curiosity.Main;
import com.curiosity.dao.FatherMapper;
import com.curiosity.dao.SonMapper;
import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import com.curiosity.service.impl.TestServiceRequired;
import com.curiosity.service.impl.TruncateDatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest(classes = {Main.class})
class TestRequired {
    @Autowired
    TestServiceRequired testServiceRequired;
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
    public void testWithoutTransaction() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(2);
        son.setName("joker son ");
        testServiceRequired.testWithoutTransaction(father, son);
    }

    @Test
    public void testWithTransaction() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        testServiceRequired.testWithTransaction(father, son);
    }

    @Test
    public void testWithTransactionAndThrow() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        testServiceRequired.testWithTransactionAndThrow(father, son);
    }

    @Test
    public void testWithTransactionAndTry() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        testServiceRequired.testWithTransactionAndTry(father, son);
    }
}




