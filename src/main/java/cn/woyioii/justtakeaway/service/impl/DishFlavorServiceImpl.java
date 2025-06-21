package cn.woyioii.justtakeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.woyioii.justtakeaway.entity.DishFlavor;
import cn.woyioii.justtakeaway.mapper.DishFlavorMapper;
import cn.woyioii.justtakeaway.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
