package cn.woyioii.justtakeaway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.woyioii.justtakeaway.dto.DishDto;
import cn.woyioii.justtakeaway.entity.Dish;

public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时保存对应的口味数据
     * 
     * @param dishDto
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id查询菜品信息和对应的口味信息
     * 
     * @param id
     * @return
     */
    DishDto getByIdWithFlavor(Long id);

    /**
     * 更新菜品信息，同时更新对应的口味数据
     * 
     * @param dishDto
     */
    void updateWithFlavor(DishDto dishDto);
}
