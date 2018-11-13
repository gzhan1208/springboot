package com.example.spring_boot_crud.service.impl;

import com.example.spring_boot_crud.mapper.UserMapper;
import com.example.spring_boot_crud.model.User;
import com.example.spring_boot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void delete(int uid) {
        userMapper.delete(uid);
    }

    @Override
    public List<User> queryUser() {
        List<User> list = userMapper.queryUser();
        return list;
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public User getUserById(int uid) {
        User user = userMapper.getUserById(uid);
        return user;
    }
}
