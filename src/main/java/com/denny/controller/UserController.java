package com.denny.controller;

import com.denny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16 0016.
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public List getAll(){
        return userService.findAll();
    }
    @GetMapping("/user")
    public List getUser(String name,String phone){
        return userService.selectModel(name, phone);
    }
}
