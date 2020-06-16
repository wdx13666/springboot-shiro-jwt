package com.wdx.springbootshirojwt;

import com.wdx.springbootshirojwt.mapper.SysUserMapper;
import com.wdx.springbootshirojwt.service.impl.SysUserServiceImpl;
import com.wdx.springbootshirojwt.vo.UserAndPermissionVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroJwtApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserServiceImpl userService;

    @Test
    void contextLoads() {
        System.out.println(userService.getByUserName("wdx"));
        UserAndPermissionVo permissionByUserId = sysUserMapper.getPermissionByUserId(14l);
        System.out.println(permissionByUserId);
    }

}
