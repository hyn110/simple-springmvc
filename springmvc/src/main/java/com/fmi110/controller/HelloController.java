package com.fmi110.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/test")
    @ResponseBody
    public String testException(){
        int i = 1/0;
        return "index";
    }
}
