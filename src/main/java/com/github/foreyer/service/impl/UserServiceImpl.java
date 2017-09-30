package com.github.foreyer.service.impl;

import org.springframework.stereotype.Service;

import com.github.foreyer.common.service.impl.LayuiServiceImpl;
import com.github.foreyer.entity.User;
import com.github.foreyer.repository.UserRepository;
import com.github.foreyer.service.IUserService;

@Service
public class UserServiceImpl extends LayuiServiceImpl<UserRepository, User, Long> implements IUserService {

}
