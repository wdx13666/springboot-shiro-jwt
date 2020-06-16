package com.wdx.springbootshirojwt.vo;

import com.wdx.springbootshirojwt.entity.SysPermission;
import com.wdx.springbootshirojwt.entity.SysRole;
import lombok.Data;

import java.util.Set;

/**
 * @author wdx
 */
@Data
public class UserAndPermissionVo {
    private Long userId;
    private String username;
    Set<SysRole> roles;
    Set<SysPermission> permissions;
}
