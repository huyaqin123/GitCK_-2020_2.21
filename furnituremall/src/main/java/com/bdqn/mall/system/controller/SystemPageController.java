package com.bdqn.mall.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemPageController {

    /**
     * 跳到后台登录页面
     * @return
     */
    @RequestMapping({"/login","/login.html"})
    public String toLogin(){
        return "system/login";
    }

    /**
     * 跳到后台注册页面
     * @return
     */
    @RequestMapping({"/regist","/regist.html"})
    public String toRegist(){
        return "system/user/regist";
    }
}
