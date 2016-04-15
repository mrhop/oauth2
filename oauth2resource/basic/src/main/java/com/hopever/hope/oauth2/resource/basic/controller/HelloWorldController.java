package com.hopever.hope.oauth2.resource.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Donghui Huo on 2016/3/23.
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        return "Hello User!";
    }
}
