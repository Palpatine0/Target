package com.target.target_admin_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_admin_service.mapper.AdminMapper;
import com.target.target_common.pojo.Admin;
import com.target.target_common.pojo.Permission;
import com.target.target_common.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@DubboService
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void add(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public void update(Admin admin) {
        if (!StringUtils.hasText(admin.getPassword())){
            String password = adminMapper.selectById(admin.getAid()).getPassword();
            admin.setPassword(password);
        }
        adminMapper.updateById(admin);
    }

    @Override
    public void delete(Long id) {
        adminMapper.deleteAdminAllRole(id);
        adminMapper.deleteById(id);
    }

    @Override
    public Admin findById(Long id) {
        return adminMapper.findById(id);
    }

    @Override
    public Page<Admin> search(int page, int size) {
        return adminMapper.selectPage(new Page(page,size),null);
    }

    @Override
    public void updateRoleToAdmin(Long aid, Long[] rids) {
        adminMapper.deleteAdminAllRole(aid);
        for (Long rid : rids) {
            adminMapper.addRoleToAdmin(aid,rid);
        }
    }

    @Override
    public Admin findByAdminName(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        Admin admin = adminMapper.selectOne(wrapper);
        return admin;
    }

    @Override
    public List<Permission> findAllPermission(String username) {
        return adminMapper.findAllPermission(username);
    }
}
