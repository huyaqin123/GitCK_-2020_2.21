package com.bdqn.mall.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Controller
public class PageController {

    /**
     * 注册页面
     * @return
     */
    @RequestMapping({"resgist","regist.html"})
    public String resgist(){
        return "mall/user/regist";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping({"/login","/login.html"})
    public String toLogin(){
        return "mall/user/login";
    }

    /**
     * 跳转到商品列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
        return "mall/detail/list";
    }
    /**
     * 跳转到商品详情页面
     * @return
     */
    @RequestMapping("/toDetail")
    public String toDetail(){
        return "mall/detail/detail";
    }

}
