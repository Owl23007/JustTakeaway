package cn.woyioii.justtakeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.woyioii.justtakeaway.entity.AddressBook;
import cn.woyioii.justtakeaway.mapper.AddressBookMapper;
import cn.woyioii.justtakeaway.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
