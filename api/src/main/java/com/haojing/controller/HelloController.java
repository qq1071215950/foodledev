package com.haojing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
