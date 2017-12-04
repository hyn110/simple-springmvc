package com.fmi110.controller;

import com.fmi110.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @InitBinder("user")
    public void initBinder(WebDataBinder binder){
        System.out.println("=========initBinder========");
        binder.setFieldDefaultPrefix("t.");
    }
//
    @InitBinder("t")
    public void initBinder2(WebDataBinder binder){
        System.out.println("=========initBinder2========");
        binder.setFieldDefaultPrefix("user.");
    }

    @RequestMapping("test")
    @ResponseBody
    public User test(@ModelAttribute("user") User user, @ModelAttribute("t") User u2){
        System.out.println(user);
        System.out.println(u2);
//        System.out.println(u3);
        return user;
    }
}
