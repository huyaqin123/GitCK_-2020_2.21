package com.bdqn.mall.mall.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdqn.mall.mall.entity.User;
import com.bdqn.mall.mall.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * UserRealm的域名
     *
     * @return
     */
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取当前登录主体
        String phone = authenticationToken.getPrincipal().toString();
        //创建条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone", phone);
        //根据邮箱查询用户信息
        User user = userService.getOne(queryWrapper);
        //对象不为空
        if (user != null) {
            //创建盐值(以用户名作为盐值)
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
            //创建身份验证对象
            //参数1：当前登录对象  参数2：密码  参数3：盐值 参数4：域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), salt, "");
            return info;//验证通过
        }
        return null;
    }
}
