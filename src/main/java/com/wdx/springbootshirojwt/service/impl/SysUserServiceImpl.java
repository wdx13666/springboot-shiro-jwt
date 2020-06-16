package com.wdx.springbootshirojwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdx.springbootshirojwt.entity.SysUser;
import com.wdx.springbootshirojwt.mapper.SysUserMapper;
import com.wdx.springbootshirojwt.service.ISysUserService;
import com.wdx.springbootshirojwt.vo.UserAndPermissionVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2020-06-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser getByUserName(String userName) {
        return getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername,userName));
    }

    @Override
    public UserAndPermissionVo getPermissionByUserId(Long userId) {
        return baseMapper.getPermissionByUserId(userId);
    }


}
