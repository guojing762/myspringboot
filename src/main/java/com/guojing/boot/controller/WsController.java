package com.guojing.boot.controller;

import com.guojing.boot.interf.ResponseMessage;
import com.guojing.boot.model.RequestMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @Controller注解不必多言，say方法上添加的@MessageMapping注解和我们之前使用的@RequestMapping类似。
 * @SendTo注解表示当服务器有消息需要推送的时候，会对订阅了@SendTo中路径的浏览器发送消息。
 */
@Controller
public class WsController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }
}