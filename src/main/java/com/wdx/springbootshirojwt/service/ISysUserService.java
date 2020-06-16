package com.wdx.springbootshirojwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdx.springbootshirojwt.entity.SysUser;
import com.wdx.springbootshirojwt.vo.UserAndPermissionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2020-06-15
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户名称查询用户
     * @param userName
     * @return
     */
    SysUser getByUserName(String userName);

    /**
     * 根据用户id获取权限
     * @param userId
     * @return
     */
    UserAndPermissionVo getPermissionByUserId(Long userId);
}
