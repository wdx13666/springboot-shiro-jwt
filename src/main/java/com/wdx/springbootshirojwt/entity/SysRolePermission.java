package com.wdx.springbootshirojwt.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdx
 * @since 2020-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;


}
