package com.spider.demo.service.impl;

import com.spider.demo.dataobject.User;
import com.spider.demo.repository.UserRepository;
import com.spider.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        User result = userRepository.findByUsername(user.getUsername());
        if (result != null){
            return null;
        }

        return userRepository.save(user);
    }

    @Override
    public User update(String username,String passwordOld,String passwordNew) {
        User user = userRepository.findByUsername(username);
        if (user == null||!user.getPassword().equals(passwordOld))
        {
            return null;
        }
        user.setPassword(passwordNew);
        return userRepository.save(user);
    }

    @Override
    public Boolean checkLogin(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null||!user.getPassword().equals(password))
        {
            return false;
        }
        return true;
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }
}
