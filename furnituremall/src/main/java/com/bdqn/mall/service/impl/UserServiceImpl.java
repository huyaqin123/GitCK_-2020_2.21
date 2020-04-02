package com.bdqn.mall.service.impl;

import com.bdqn.mall.entity.User;
import com.bdqn.mall.dao.UserMapper;
import com.bdqn.mall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.mall.utils.PasswordUtil;
import com.bdqn.mall.utils.SystemConstants;
import com.bdqn.mall.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean save(User user) {
        String salt= UUIDUtil.randomUUID();//生成32位字符
        user.setLevel(SystemConstants.USER_STATE_ENABLE);//用户状态
        user.setCreatime(new Date());//用户注册时间
        user.setSalt(salt);//加密盐值
        user.setPassword(PasswordUtil.md5(user.getPassword(),salt,SystemConstants.HASHITERATIONS));
        return super.save(user);
    }

}
