package cn.woyioii.justtakeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.woyioii.justtakeaway.entity.User;
import cn.woyioii.justtakeaway.mapper.UserMapper;
import cn.woyioii.justtakeaway.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
