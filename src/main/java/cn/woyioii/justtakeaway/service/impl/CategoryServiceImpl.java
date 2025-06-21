package cn.woyioii.justtakeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.woyioii.justtakeaway.entity.Category;
import cn.woyioii.justtakeaway.mapper.CategoryMapper;
import cn.woyioii.justtakeaway.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
