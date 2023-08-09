package com.target.target_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.target.target_common.pojo.Permission;

public interface PermissionMapper extends BaseMapper<Permission> {
    void deletePermissionAllRole(Long pid);
}
