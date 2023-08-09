package com.target.target_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.target.target_common.pojo.Admin;
import com.target.target_common.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    void deleteAdminAllRole(Long aid);
    Admin findById(Long aid);
    void addRoleToAdmin(@Param("aid") Long aid, @Param("rid")Long rid);
    List<Permission> findAllPermission(String username);
}
