package com.reach.auth.service.serviceImpl;

import com.reach.auth.domain.po.User;
import com.reach.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reach.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
