package cn.woyioii.justtakeaway.dto;

import cn.woyioii.justtakeaway.entity.Setmeal;
import cn.woyioii.justtakeaway.entity.SetmealDish;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
