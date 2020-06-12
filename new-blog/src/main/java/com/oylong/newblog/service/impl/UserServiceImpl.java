package com.oylong.newblog.service.impl;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.dao.UserMapper;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.entity.User;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.service.UserService;
import com.oylong.newblog.utils.RedisUtil;
import com.oylong.newblog.utils.ResultUtil;
import com.oylong.newblog.utils.TokenUtil;
import com.oylong.newblog.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    RedisUtil redisUtil;


    @Override
    public Result getUser(String username, int page, int limit) {
        IPage<User> userPage = new Page<>(page, limit);
        IPage<User> userIPage;

        if(!StringUtils.isEmpty(username)) {
            QueryWrapper<User> queryWrapper = new QueryWrapper();
            queryWrapper.like("username", username);
            userIPage = userMapper.selectPage(userPage, queryWrapper);
        } else {
            userIPage = userMapper.selectPage(userPage, null);
        }


        List<UserVo> list= new ArrayList<>();

        for(User user: userIPage.getRecords()){
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(user, vo);
            list.add(vo);
        }

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", userIPage.getTotal());
        hashMap.put("list", list);

        Result<Map> result = ResultUtil.buildSuccessResult();
        result.setData(hashMap);

        return result;
    }

    @Override
    public Result login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper<>().eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        if (user.getPassword().equals(password)) {
            String token = TokenUtil.generateToken();
            TokenUtil.saveToken(token, user.getUsername());
            Result<String> result = ResultUtil.buildSuccessResult();
            result.setData(token);
            return result;
        } else {
            throw new CustomException(ResultCode.USER_CREDENTIALS_ERROR);
        }
    }

    @Override
    public Result logout(String token) {
        if (TokenUtil.isTokenAvalible(token)) {
            TokenUtil.deleteToken(token);
            return ResultUtil.buildSuccessResult();
        }
        else {
            throw new CustomException(ResultCode.NO_PERMISSION);
        }
    }

    @Override
    public Result addUser(User user) {
        String username = user.getUsername();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User euser = userMapper.selectOne(queryWrapper);
        if(euser != null){
            throw new CustomException(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result deleteUser(Long id) {
        User user = userMapper.selectById(id);
        if(user == null){
            throw new CustomException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        userMapper.deleteById(id);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result updateUser(User user) {
        User tuser = userMapper.selectById(user.getId());
        if(tuser == null){
            throw new CustomException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        userMapper.updateById(user);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result deleteUser(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);

        User tuser = userMapper.selectOne(queryWrapper);
        if(tuser == null){
            throw new CustomException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        userMapper.delete(queryWrapper);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result sendFindPassWordEmail(String username) {
        String captcha = RandomUtil.randomString(6);
        redisUtil.set("findpassword:"+username+":captcha", captcha, 60*60);
        String email = userMapper.selectEmailByUsername(username);
        if(email == null) {
            return  ResultUtil.buildUnSuccessResult("此用户不存在");
        }
        MailUtil.send(email, "MyBlog密码找回", "您的验证码是: "
                        + captcha +
                        "\n" + "此验证码用于找回密码,请勿泄露!",
                false);
        return ResultUtil.buildSuccessResult("邮箱发送成功");
    }

    @Override
    public Result findPassword(String username, String password, String captcha) {
        Result result;

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        User user = userMapper.selectOne(queryWrapper);


        if(user == null) {
            throw  new CustomException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }

        String value = (String)redisUtil.get("findpassword:"+username+":captcha");
        if(!captcha.equals(value)) {
            throw new CustomException(ResultCode.PARAM_NOT_VALID);
        }

        user.setPassword(password);

        userMapper.updateById(user);

        redisUtil.del("findpassword:"+username+":captcha");

        result = ResultUtil.buildSuccessResult("密码修改成功");
        return  result;
    }

    @Override
    public Result getUserInfoByToken(String token) {
        String usernameByToken = TokenUtil.getUsernameByToken(token);

        if(StringUtils.isEmpty(token)){
            throw new CustomException(ResultCode.NO_PERMISSION);
        }

        QueryWrapper username = new QueryWrapper<>().eq("username", usernameByToken);
        User user = userMapper.selectOne(username);

        if(user == null) {
            throw new CustomException(ResultCode.NO_PERMISSION);
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        Result result = ResultUtil.buildSuccessResult();
        result.setData(userVo);
        return result;
    }



}
