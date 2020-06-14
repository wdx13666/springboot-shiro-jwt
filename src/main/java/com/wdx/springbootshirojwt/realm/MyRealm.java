package com.wdx.springbootshirojwt.realm;

import com.wdx.springbootshirojwt.JwtToken;
import com.wdx.springbootshirojwt.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {

//    @Autowired
//    private SysUserService sysUserService;

    /**
     * 必须重写此方法，不然shiro会报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
            throws AuthenticationException {
        String token = (String) auth.getPrincipal();
        String username = JwtUtil.getUsername(token);
       /* if (StringUtil.isEmpty(username)) {
            throw new IncorrectCredentialsException("用户名无效");
        }

        SysUser user = sysUserService.getByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }*/

        if(!JwtUtil.verify(token,username,"123456")){
            throw new AuthenticationException("密码错误");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JwtUtil.getUsername(principals.toString());
//        SysUser user = sysUserService.getByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户名查询权限 TODO 放到redis中
        List roles = new ArrayList();
        roles.parallelStream().forEach(role ->{
            info.addStringPermission("");
        });
        return info;
    }}
