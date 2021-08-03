package com.curiosity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.curiosity.Main;
import com.curiosity.dao.FatherMapper;
import com.curiosity.dao.SonMapper;
import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest(classes = {Main.class})
class TestExample {
    @Autowired
    TestServiceNested testServiceNested;
    @Autowired
    TruncateDatabaseService truncateDatabaseService;
    @Autowired
    FatherMapper fatherMapper;
    @Autowired
    SonMapper sonMapper;
    @Autowired
    Example example;
    @BeforeEach
    public void init() throws SQLException {
        truncateDatabaseService.truncate();
    }

    @AfterEach
    public void result() {
        final List<Father> fathers = fatherMapper.selectList(new QueryWrapper<>());
        final List<Son> sons = sonMapper.selectList(new QueryWrapper<>());
        System.out.println("-----------------" + "fathers is " + fathers + "sons is " + sons);
    }

    @Test
    public void testWithoutTransactionExceptionNestedNested() throws Exception {
        example.example();
    }



}




