package com.guojing.boot.controller;

import com.guojing.boot.config.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyExceptionController {
    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
    @RequestMapping("/rs")
    public String rs() throws Exception {
        throw new Exception("发生错误1");
    }
}