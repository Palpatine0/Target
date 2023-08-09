package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Admin;
import com.target.target_common.pojo.Permission;

import java.util.List;

public interface AdminService {
    void add(Admin admin);
    void update(Admin admin);
    void delete(Long id);
    Admin findById(Long id);
    Page<Admin> search(int page, int size);
    void updateRoleToAdmin(Long aid, Long[] rids);

    //for security
    Admin findByAdminName(String name);
    List<Permission> findAllPermission(String username);

}
