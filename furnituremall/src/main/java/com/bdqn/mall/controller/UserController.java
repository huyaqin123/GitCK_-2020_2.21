package com.bdqn.mall.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdqn.mall.entity.User;
import com.bdqn.mall.service.UserService;
import com.bdqn.mall.utils.JSONResult;
import com.bdqn.mall.utils.SystemConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-03-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 验证手机号是否存在
     * @param phone 邮箱
     * @return
     */
    @RequestMapping("/checkPhone")
    public String checkEmail(String phone){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone",phone);//手机号
        //数量大于0表示该邮箱被注册
        if(userService.count(queryWrapper)>0){
            map.put(SystemConstants.EXIST,true);
            map.put(SystemConstants.MESSAGE,"手机号已被注册");
        }else{
            map.put(SystemConstants.EXIST,false);
            map.put(SystemConstants.MESSAGE,"手机号可以使用");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 检查验证码是否手机验证码的一致
     * @param code
     * @param session
     * @return
     */
    @RequestMapping("/checkCode")
    public boolean checkCode(String code,HttpSession session){
        String userCode = (String) session.getAttribute("code");
        if (code.equals(userCode)){
            return true;
        }
        return false;
    }

    /**
     * 检查手机号是不是发送短信的手机号
     * @param phone
     * @param session
     * @return
     */
    @RequestMapping("/sendPhone")
    public boolean sendPhone(String phone,HttpSession session){
        String userPhone = (String) session.getAttribute("phone");
        if (phone.equals(userPhone)){
            return true;
        }
        return false;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/regist")
    public JSONResult regist(User user){
        if (userService.save(user)){
            //注册成功
            return SystemConstants.REGIST_SUCCESS;
        }
        //注册失败
        return SystemConstants.REGIST_ERROT;
    }

    /**
     * 用户登录
     * @param phone     邮箱
     * @param password  密码
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public JSONResult login(String phone, String password, HttpServletRequest request){
        //获取当前登录主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建令牌对象
        UsernamePasswordToken token = new UsernamePasswordToken(phone,password);
        try {
            //执行登录
            subject.login(token);
            //获取当前登录用户
            User loginUser = (User) subject.getPrincipal();
            //保存session
            request.getSession().setAttribute(SystemConstants.LOGINUSER,loginUser);
            return SystemConstants.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return SystemConstants.LOGIN_ERROR;
    }
}

