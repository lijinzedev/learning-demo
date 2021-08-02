package com.curiosity;

import com.curiosity.model.model.Father;
import com.curiosity.service.impl.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Main.class})
class MainTests {
    @Autowired
    TestService testService;

    @Test
    public void shouldValidateDuplicated() throws Exception {
        final Father father = new Father();
        father.setId(1);
        father.setName("joker");
        testService.testUser1NotTransactional(father);

    }
}




