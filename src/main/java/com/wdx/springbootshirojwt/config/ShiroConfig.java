package com.wdx.springbootshirojwt.config;

import com.wdx.springbootshirojwt.cache.RedisCacheManager;
import com.wdx.springbootshirojwt.filter.JwtFilter;
import com.wdx.springbootshirojwt.realm.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //拦截器
        // 自定义url规则
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会拦截的链接，顺序判断
        filterChainDefinitionMap.put("/login/**", "anon");
//        filterChainDefinitionMap.put("/**.js", "anon");
//        filterChainDefinitionMap.put("/swagger**/**", "anon");
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>(1);

        filterMap.put("jwt", new JwtFilter());
        factoryBean.setFilters(filterMap);
        // 过滤链接定义，从上向下顺序执行，一般将/**放在最为下边
        // 所有请求通过我们自己的JWTFilter
        filterChainDefinitionMap.put("/**", "jwt");
        // 访问 /unauthorized/** 不通过JWTFilter
//        filterChainDefinitionMap.put("/unauthorized/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    /**
     * 安全管理器配置
     *
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(MyRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        // 关闭shiro自带的session,详情见文档
        // http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);
        securityManager.setSubjectDAO(subjectDAO);

//        securityManager.setCacheManager((CacheManager) redisCacheManager());
        return securityManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager cacheManager = new RedisCacheManager();
        return  cacheManager;
    }

    /**
     * 下面的代码是添加注解支持
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
