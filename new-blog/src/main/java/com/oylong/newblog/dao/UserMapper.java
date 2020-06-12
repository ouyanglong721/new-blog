package com.oylong.newblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oylong.newblog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    String selectEmailByUsername(String username);
}
