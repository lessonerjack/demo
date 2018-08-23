package com.denny.service;

import com.denny.mapper.UserMapper;
import com.denny.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16 0016.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> selectModel(String name, String phone) {
        return userMapper.selectModel(name, phone);
    }
}
