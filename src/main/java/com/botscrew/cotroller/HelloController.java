package com.botscrew.cotroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String main(){
        return "Home page";
    }

    @RequestMapping("/hello")
    public String printHello(){
        return "hello";
    }
}
