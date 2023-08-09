package com.target.target_manager_api.controller;
import com.target.target_common.pojo.Admin;
import com.target.target_common.result.BaseResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.service.AdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @DubboReference
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public BaseResult add(@RequestBody Admin admin){
        String password = admin.getPassword();
        password= passwordEncoder.encode(password);
        admin.setPassword(password);
        adminService.add(admin);
        return BaseResult.ok();
    }
    @PutMapping("/update")
    public BaseResult update(@RequestBody Admin admin){
        String password = admin.getPassword();
        //only update when when its not a blend PWD
        if(StringUtils.hasText(password)){
            password= passwordEncoder.encode(password);
            admin.setPassword(password);
        }
        adminService.update(admin);
        return BaseResult.ok();
    }
    @DeleteMapping("/delete")
    public BaseResult delete(Long aid){
        adminService.delete(aid);
        return BaseResult.ok();
    }
    @GetMapping("/findById")
    public BaseResult<Admin> findById(Long aid){
        Admin admin = adminService.findById(aid);
        return BaseResult.ok(admin);
    }

    @PreAuthorize("hasAnyAuthority('/admin/search')")
    @GetMapping("/search")
    public BaseResult<Page<Admin>> search(int page, int size){
        Page<Admin> adminPage = adminService.search(page, size);
        return BaseResult.ok(adminPage);
    }

    @PutMapping("/updateRoleToAdmin")
    public BaseResult updateRoleToAdmin(Long aid,Long[] rids){
        adminService.updateRoleToAdmin(aid,rids);
        return BaseResult.ok();
    }

    @PutMapping("/getUserName")
    public BaseResult<String> getUserName(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return BaseResult.ok(username);
    }

}
