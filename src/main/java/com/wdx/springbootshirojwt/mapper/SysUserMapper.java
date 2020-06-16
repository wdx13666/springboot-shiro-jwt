package com.wdx.springbootshirojwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdx.springbootshirojwt.entity.SysUser;
import com.wdx.springbootshirojwt.vo.UserAndPermissionVo;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2020-06-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserAndPermissionVo getPermissionByUserId(Long userId);

}
