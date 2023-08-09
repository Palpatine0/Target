package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Role;

import java.util.List;

public interface RoleService {
    void add(Role role);
    void update(Role role);
    void delete(Long id);
    Role findById(Long id);
    List<Role> findAll();
    Page<Role> search(int page, int size);
    void addPermissionToRole(Long rid, Long[] pids);
}
