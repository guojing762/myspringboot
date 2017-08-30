package com.guojing.boot.controller;

import com.guojing.boot.entity.Person;
import com.guojing.boot.entity.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/main/{id}")
    public ModelAndView view(@PathVariable("id") Long id, HttpServletRequest req) {
        TbUser user = new TbUser();
        user.setId(id);
        user.setLoginName("zhang");

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("main/test");
        return mv;
    }

    @RequestMapping("/hello")
    public String helloHtml(HashMap<String,Object> map){
        map.put("hello","hello");
        return"/hello";
    }
    @RequestMapping("/index")
    public String index(Model model) {
        Person single = new Person("aa", 11);
        List<Person> people = new ArrayList<>();
        Person p1 = new Person("zhangsan", 11);
        Person p2 = new Person("lisi", 22);
        Person p3 = new Person("wangwu", 33);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);
        return"/index";
    }

    @RequestMapping("/user")
    public String user(@ModelAttribute("msg") String msg, Person userBean) {
        System.out.println("username is :" + userBean.getName() + ";and id is :" + userBean.getAge());
        throw new IllegalArgumentException("抱歉，参数异常/ 来自@ModelAttribute:" + msg);
    }
}
