package cn.woyioii.justtakeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.woyioii.justtakeaway.entity.OrderDetail;
import cn.woyioii.justtakeaway.mapper.OrderDetailMapper;
import cn.woyioii.justtakeaway.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
