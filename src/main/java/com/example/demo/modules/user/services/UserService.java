package com.example.demo.modules.user.services;

import com.example.demo.modules.user.entities.UserEntity;
import com.example.demo.modules.user.repositories.UserRepository;
import com.example.demo.shared.interfaces.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IBaseService<UserEntity> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        return this.userRepository.findAll();
    }

    public UserEntity getById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public UserEntity create(UserEntity user) {
        return this.userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {
        return this.userRepository.save(user);
    }

    public void delete(String id) {
        this.userRepository.deleteById(id);
    }
}
