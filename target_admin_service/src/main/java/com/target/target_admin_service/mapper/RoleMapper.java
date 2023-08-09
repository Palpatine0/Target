package com.target.target_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.target.target_common.pojo.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<Role> {
    Role findById(Long id);
    void deleteRoleAllPermission(Long rid);
    void addPermissionToRole(@Param("rid") Long rid,@Param("pid") Long pid);
}
