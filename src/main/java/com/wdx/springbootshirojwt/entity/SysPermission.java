package com.wdx.springbootshirojwt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;

    private Long parentId;

    private String resName;

    private String resType;

    private String permission;

    private String url;

    private String icon;


}
