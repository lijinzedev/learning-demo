package com.curiosity.service.impl;

import com.curiosity.business.MsgHandler;
import com.curiosity.business.MsgHandlerContext;
import com.curiosity.service.MqttService;
import com.curiosity.utils.JsonUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MqttServiceImpl implements MqttService {
    @Autowired
    private MsgHandlerContext msgHandlerContext;

    /**
     * mqtt消息处理
     * @param topic
     * @param message
     */
    @Override
    public void processMessage(String topic, MqttMessage message) {
        String msgContent = new String(message.getPayload());
        log.info("接收到消息:"+msgContent);
        try {
            String msgType = JsonUtil.getValueByNodeName("msgType",msgContent);
            if(Strings.isNullOrEmpty(msgType)) {
                return;
            }
            MsgHandler msgHandler = msgHandlerContext.getMsgHandler(msgType);
            if(msgHandler == null) {
                return;
            }
            msgHandler.process(msgContent);
        } catch (IOException e) {
            log.error("process msg error,msg is: "+msgContent,e);
        }
    }
}
