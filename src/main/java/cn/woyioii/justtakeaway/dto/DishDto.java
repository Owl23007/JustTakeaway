package cn.woyioii.justtakeaway.dto;

import cn.woyioii.justtakeaway.entity.Dish;
import cn.woyioii.justtakeaway.entity.DishFlavor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DishDto extends Dish {

    // 菜品对应的口味数据
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
