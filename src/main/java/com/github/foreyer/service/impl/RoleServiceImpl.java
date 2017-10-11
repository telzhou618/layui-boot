package com.github.foreyer.service.impl;

import org.springframework.stereotype.Service;

import com.github.foreyer.common.service.impl.LayuiServiceImpl;
import com.github.foreyer.entity.Role;
import com.github.foreyer.repository.RoleRepository;
import com.github.foreyer.service.IRoleService;

@Service
public class RoleServiceImpl extends LayuiServiceImpl<RoleRepository, Role, Long> implements IRoleService {


}
