package com.dazzle.malltrackingsys.service.impl;

import com.dazzle.malltrackingsys.entity.UserEntity;
import com.dazzle.malltrackingsys.repository.UserRepository;
import com.dazzle.malltrackingsys.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return List.of();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return null;
    }

    @Override
    public Optional<UserEntity> updateUser(Long id, UserEntity user) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long id) {

    }
}
