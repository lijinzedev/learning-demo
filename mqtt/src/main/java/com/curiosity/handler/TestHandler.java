package com.curiosity.handler;


import com.curiosity.annotations.ProcessType;
import com.curiosity.business.MsgHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 更新合作商信息
 */
@Slf4j
@Component
@ProcessType(value = "server/task/completed")
public class TestHandler implements MsgHandler {
    @Override
    public void process(String jsonMsg) throws IOException {
        log.info(jsonMsg);
    }
}
