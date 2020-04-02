package com.bdqn.mall.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/index")
    public String home(){
        return "admin/index";
    }
}
