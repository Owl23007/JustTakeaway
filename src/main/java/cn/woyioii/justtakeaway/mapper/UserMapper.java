package cn.woyioii.justtakeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.woyioii.justtakeaway.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
