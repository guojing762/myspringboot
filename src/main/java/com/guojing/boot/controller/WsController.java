package com.guojing.boot.controller;

import com.guojing.boot.interf.ResponseMessage;
import com.guojing.boot.model.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @Controller注解不必多言，say方法上添加的@MessageMapping注解和我们之前使用的@RequestMapping类似。
 * @SendTo注解表示当服务器有消息需要推送的时候，会对订阅了@SendTo中路径的浏览器发送消息。
 */
@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }



    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if (principal.getName().equals("admin")) {
            messagingTemplate.convertAndSendToUser("140112", "/queue/notifications", principal.getName() + "给您发来了消息：" + msg);
        }else{
            messagingTemplate.convertAndSendToUser("admin", "/queue/notifications", principal.getName() + "给您发来了消息：" + msg);
        }
    }
}