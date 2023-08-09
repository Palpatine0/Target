package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Permission;

import java.util.List;

public interface PermissionService {
    void add(Permission permission);
    void update(Permission permission);
    void delete(Long id);
    Permission findById(Long id);
    Page<Permission> search(int page, int size);
    List<Permission> findAll();
}
