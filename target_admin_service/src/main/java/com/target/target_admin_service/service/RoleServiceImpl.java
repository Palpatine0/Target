package com.target.target_admin_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_admin_service.mapper.RoleMapper;
import com.target.target_common.pojo.Role;
import com.target.target_common.service.RoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateById(role);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteById(id);
        roleMapper.deleteRoleAllPermission(id);
    }

    @Override
    public Role findById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.selectList(null);
    }

    @Override
    public Page<Role> search(int page, int size) {
        return roleMapper.selectPage(new Page(page,size),null);
    }

    @Override
    public void addPermissionToRole(Long rid, Long[] pids) {
        roleMapper.deleteRoleAllPermission(rid);
        for (Long pid : pids) {
            roleMapper.addPermissionToRole(rid,pid);
        }
    }
}
