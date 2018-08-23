package com.denny.mapper;

import com.denny.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16 0016.
 */
public interface UserMapper{

    public List<User> findAll();
    public List<User> selectModel(@Param("name") String name,@Param("phone") String phone);
}
