package com.example.shirodemo.config.shior;

import cn.hutool.core.collection.CollUtil;
import com.example.shirodemo.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义身份认证
 *
 * @author lixingwu
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     * 密码：md5(明密+用户名)*加密次数
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        log.debug(">>> 获取身份验证信息 CustomRealm:doGetAuthenticationInfo()，token：{}", token);
        UserInfo user = getUserInfo(token.getUsername());
        ByteSource credentialsSalt = ByteSource.Util.bytes(token.getUsername());
        if (null == user) {
            throw new UnknownAccountException();
        }
        // principal：认证的对象
        // hashedCredentials：加密后的密码
        // credentialsSalt：加密的盐值
        return new SimpleAuthenticationInfo(user, user.getUserPwd(), credentialsSalt, getName());
    }

    /**
     * 获取授权信息
     * 需要定义角色的权限信息，如果不区分权限，不用管
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        log.debug(">>> 获取授权信息 CustomRealm:doGetAuthorizationInfo()，用户名：{}", username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = "用户的角色";
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
//        return info;
        return null;
    }

    /**
     * 方法描述：md5密码生成器.
     * 创建时间：2018-10-19 15:14:15
     *
     * @param username 用户登录的账号
     * @param password 用户的明文密码
     * @return the string
     * @author "lixingwu"
     */
    public static String getMd5Pwd(String username, String password) {
        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleHash("MD5", password, salt, 1024).toString();
    }

    /**
     * 模拟根据用户名获取用户信息
     */
    public static UserInfo getUserInfo(String userName) {
        List<UserInfo> userInfos = CollUtil.newArrayList();
        UserInfo userInfo;
        for (int i = 0; i < 10; i++) {
            userInfo = new UserInfo();
            userInfo.setId((long) i);
            userInfo.setUserName("lixingwu" + i);
            userInfo.setUserPwd(getMd5Pwd("lixingwu" + i, "lixingwu"));
            userInfos.add(userInfo);
        }
        // 筛选出符合的用户
        userInfos = userInfos.stream().filter(u -> (userName.equals(u.getUserName()))).collect(Collectors.toList());
        return userInfos.isEmpty() ? null : userInfos.get(0);
    }
}
