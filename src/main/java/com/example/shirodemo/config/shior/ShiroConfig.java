package com.example.shirodemo.config.shior;

import com.example.shirodemo.config.component.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 *
 * @author lixingwu
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 方法描述：Shiro filter.
     * 创建时间：2019-01-09 15:06:18
     *
     * @param securityManager security manager
     * @return 在容器中注入ShiroFilterFactoryBean
     * @author "lixingwu"
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info(">> 开始加载shiroFilter...");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // Shiro 官方过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/test/**", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，
        // 后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/unauth");

        log.info(">> shiroFilter加载成功！");
        return shiroFilterFactoryBean;
    }


    /**
     * 方法描述：注入securityManager.
     * 创建时间：2019-01-09 15:13:57
     *
     * @return the security manager
     * @author "lixingwu"
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm realm = new CustomRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * 方法描述：自定义SessionManager.
     * 创建时间：2019-01-09 15:30:58
     *
     * @return the session manager
     * @author "lixingwu"
     */
    @Bean
    public SessionManager sessionManager() {
        com.example.shirodemo.config.shior.SessionManager sessionManager
                = new com.example.shirodemo.config.shior.SessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 方法描述：密码处理规则.
     * 创建时间：2019-01-09 15:15:28
     *
     * @return the hashed credentials matcher
     * @author "lixingwu"
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //使用MD5散列算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列次数，这里等于1次MD5
        hashedCredentialsMatcher.setHashIterations(1024);
        //散列后密码为16进制，要与生成密码时一致，false 表示Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 方法描述：sessionDao层的实现 通过redis，.
     * 创建时间：2019-01-09 15:19:41
     *
     * @return 使用的是shiro-redis开源插件
     * @author "lixingwu"
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisUtils);
        return sessionDAO;
    }

    /**
     * 方法描述：开启shiro aop注解支持.
     * 使用代理方式，所以需要开启代码支持;
     * 创建时间：2019-01-09 15:16:09
     *
     * @param securityManager the security manager
     * @return the authorization attribute source advisor
     * @author "lixingwu"
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
