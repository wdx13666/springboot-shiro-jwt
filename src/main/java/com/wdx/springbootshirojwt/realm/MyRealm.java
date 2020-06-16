package com.wdx.springbootshirojwt.realm;

import com.wdx.springbootshirojwt.JwtToken;
import com.wdx.springbootshirojwt.entity.SysUser;
import com.wdx.springbootshirojwt.service.impl.SysUserServiceImpl;
import com.wdx.springbootshirojwt.utils.JwtUtil;
import com.wdx.springbootshirojwt.vo.UserAndPermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserServiceImpl userService;


    /**
     * 重写 Realm 的 supports() 方法是通过 JWT 进行登录判断的关键
     * 因为前文中创建了 JWTToken 用于替换 Shiro 原生 token
     * 所以必须在此方法中显式的进行替换，否则在进行判断时会一直失败
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
        log.info("————身份认证方法————");
        String token = (String) auth.getPrincipal();
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isBlank(username)) {
            throw new IncorrectCredentialsException("用户名无效");
        }
        SysUser user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        if(!JwtUtil.verify(token,username,user.getPassword())){
            throw new AuthenticationException("账号或密码错误");
        }
        return new SimpleAuthenticationInfo(user, token, MyRealm.class.getName());
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("————权限认证————");
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
//        String username = JwtUtil.getUsername(principals.toString());
//        SysUser byUserName = userService.getByUserName(username);
        UserAndPermissionVo permissionByUserId = userService.getPermissionByUserId(user.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(permissionByUserId.getRoles().stream().map(s -> s.getRoleName()).collect(Collectors.toSet()));
        info.setStringPermissions(permissionByUserId.getPermissions().stream().map(s -> s.getUrl()).collect(Collectors.toSet()));
        //根据用户名查询权限 TODO 放到redis中
      /*  List roles = new ArrayList();
        roles.parallelStream().forEach(role ->{
            info.addStringPermission("");
        });*/
        return info;
    }}
