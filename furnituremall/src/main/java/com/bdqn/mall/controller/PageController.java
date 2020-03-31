package com.bdqn.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     * 注册页面
     * @return
     */
    @RequestMapping({"resgist","regist.html"})
    public String resgist(){
        return "user/regist";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping({"/login","/login.html"})
    public String toLogin(){
        return "user/login";
    }
}
