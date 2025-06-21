package cn.woyioii.justtakeaway.service.impl;

import cn.woyioii.justtakeaway.mapper.EmployeeMapper;
import cn.woyioii.justtakeaway.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.woyioii.justtakeaway.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
    
    /**
     * 根据用户名查找员工
     * @param username 用户名
     * @return 匹配的员工对象，如果未找到则返回null
     */
    @Override
    public Employee getByUsername(String username) {
        return employeeMapper.getByUsername(username);
    }
}