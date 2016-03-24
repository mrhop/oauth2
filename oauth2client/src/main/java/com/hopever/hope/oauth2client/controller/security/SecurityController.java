package com.hopever.hope.oauth2client.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Donghui Huo on 2016/3/24.
 */
@Controller
public class SecurityController {
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String index(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index1";
    }
}
