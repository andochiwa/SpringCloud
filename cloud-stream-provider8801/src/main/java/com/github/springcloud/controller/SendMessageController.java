package com.github.springcloud.controller;

import com.github.springcloud.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HAN
 * @version 1.0
 * @create 03-24-1:22
 */
@RestController
public class SendMessageController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }

}
