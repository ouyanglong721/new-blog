package com.oylong.newblog.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oylong.newblog.dao.UserMapper;
import com.oylong.newblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: new-blog
 * @Description:
 * @Author: OyLong
 * @Date: 2020/8/13 14:22
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));

        if(user == null) {
            throw  new UsernameNotFoundException("用户不存在");
        }

        return user;
    }
}
