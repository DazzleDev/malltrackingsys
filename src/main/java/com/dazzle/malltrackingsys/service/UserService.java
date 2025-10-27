package com.dazzle.malltrackingsys.service;

import com.dazzle.malltrackingsys.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserById(Long id);

    UserEntity createUser(UserEntity user);

    Optional<UserEntity> updateUser(Long id, UserEntity user);

    void deleteUser(Long id);
}
