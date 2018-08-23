package com.denny.service;

import com.denny.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16 0016.
 */
public interface UserService {

    public List findAll();
    public List<User> selectModel(String name, String phone);
}
