package com.target.target_category_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_category_service.mapper.CategoryMapper;
import com.target.target_common.pojo.Category;

import com.target.target_common.service.CategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;

@DubboService
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
        refreshRedisCategory();
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateById(category);
        refreshRedisCategory();
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Category category = categoryMapper.selectById(id);
        category.setStatus(status);
        categoryMapper.updateById(category);
        refreshRedisCategory();
    }

    @Override
    public void delete(Long[] ids) {
        categoryMapper.deleteBatchIds(Arrays.asList(ids));

    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public Page<Category> search(int page, int size) {
        return categoryMapper.selectPage(new Page(page,size),null);
    }

    @Override
    public List<Category> findAll() {
        //1.browse ads from redis
        //1.1get the object to operate the list in redis
        ListOperations listOperations = redisTemplate.opsForList();
        //1.2get ads
        List<Category> categoryList = listOperations.range("categories",0,-1);
        if (categoryList != null && categoryList.size() > 0){
            //2.return res if has
            System.out.println("ads browsing from redis");
            return categoryList;
        }else{
            //not found in redis, go to MySQL
            System.out.println("ads browsing from MySQL");
            QueryWrapper<Category> queryWrapper = new QueryWrapper();
            queryWrapper.eq("status",1);
            List<Category> categories = categoryMapper.selectList(queryWrapper);
            //sync to redis
            listOperations.leftPushAll("categories",categories);
            return categories;
        }

    }

    public void refreshRedisCategory(){
        QueryWrapper<Category> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        List<Category> categories = categoryMapper.selectList(queryWrapper);

        redisTemplate.delete("categories");

        ListOperations<String,Category> listOperations = redisTemplate.opsForList();
        listOperations.leftPushAll("categories",categories);
    }

}
