package cn.woyioii.justtakeaway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.woyioii.justtakeaway.entity.Employee;

public interface EmployeeService extends IService<Employee> {
    
    /**
     * 根据用户名查找员工
     * @param username 用户名
     * @return 匹配的员工对象，如果未找到则返回null
     */
    Employee getByUsername(String username);
}
