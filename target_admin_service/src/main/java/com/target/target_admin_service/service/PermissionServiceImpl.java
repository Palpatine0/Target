package com.target.target_admin_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_admin_service.mapper.PermissionMapper;
import com.target.target_common.pojo.Permission;
import com.target.target_common.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public void add(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateById(permission);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteById(id);
        permissionMapper.deletePermissionAllRole(id);
    }

    @Override
    public Permission findById(Long id) {
        return permissionMapper.selectById(id);
    }

    @Override
    public Page<Permission> search(int page, int size) {
        return permissionMapper.selectPage(new Page(page,size),null);
    }
    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectList(null);
    }
}
