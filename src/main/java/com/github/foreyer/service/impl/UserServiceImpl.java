package com.github.foreyer.service.impl;

import org.springframework.stereotype.Service;

import com.github.foreyer.common.service.impl.CrudServiceImpl;
import com.github.foreyer.entity.User;
import com.github.foreyer.repository.UserRepository;
import com.github.foreyer.service.IUserService;

@Service
public class UserServiceImpl extends CrudServiceImpl<UserRepository, User, Long> implements IUserService {

}
