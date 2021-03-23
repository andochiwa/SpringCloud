package com.github.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @author HAN
 * @version 1.0
 * @create 03-24-1:08
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output; // 消息发送信道

    @Override
    public String send() {
        String uuid = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(uuid).build());
        return null;
    }
}
