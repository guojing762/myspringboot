package com.guojing.boot.config;

import com.guojing.boot.model.ErrorInfo;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    //@ExceptionHandler用来设置拦截条件，这里表示拦截所有的Exception
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception e, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMsg", e.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("age");
    }
}