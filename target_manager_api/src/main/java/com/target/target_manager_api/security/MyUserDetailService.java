package com.target.target_manager_api.security;

import com.target.target_common.pojo.Admin;
import com.target.target_common.pojo.Permission;
import com.target.target_common.service.AdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @DubboReference
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,NullPointerException {
        //authenticate
        Admin admin = adminService.findByAdminName(username);
        if (admin==null){
            throw new UsernameNotFoundException("Cant find u");
        }
        //authorize
        List<Permission> permissions = adminService.findAllPermission(username);
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission==null){
                throw new NullPointerException();
            }
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getUrl()));
        }
        //encrypt as Object UserDetails
        UserDetails userDetails = User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .authorities(grantedAuthorities)
                .build();

        return userDetails;
    }
}
