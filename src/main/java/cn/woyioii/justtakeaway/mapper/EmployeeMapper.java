package cn.woyioii.justtakeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.woyioii.justtakeaway.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
    
    /**
     * 根据用户名查找员工
     * @param username 用户名
     * @return 匹配的员工对象，如果未找到则返回null
     */
    @Select("SELECT * FROM employee WHERE username = #{username}")
    Employee getByUsername(String username);
}
