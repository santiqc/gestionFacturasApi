package com.api.gestion.demo.security.service;

import com.api.gestion.demo.entitys.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserEntity> findAllUsers();
}
