package com.curiosity;

import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import com.curiosity.service.impl.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Main.class})
class MainTests {
    @Autowired
    TestService testService;

    @Test
    public void testNotTransaction() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(2);
        son.setName("joker son ");
        testService.testNotTransaction(father,son);
    }

    @Test
    public void testHaveTransaction() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        testService.testHaveTransaction(father,son);
    }
    @Test
    public void testHaveTransactionAndThrow() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        testService.testHaveTransactionAndThrow(father,son);
    }
    @Test
    public void testHaveTransactionAndTry() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        final Son son = new Son();
        son.setId(1);
        son.setName("joker son ");
        testService.testHaveTransactionAndTry(father,son);
    }
}




