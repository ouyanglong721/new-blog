package com.oylong.newblog.controller;


import com.oylong.newblog.entity.Result;
import com.oylong.newblog.entity.User;
import com.oylong.newblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@Api(tags = "用户相关的接口")
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation("查找所有用户,传入username则开启模糊查询,否则全部查询")
    @GetMapping
    public Result<List> findAllUser(@RequestParam(name = "username", required = false)String username,@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return userService.getUser(username, page, limit);
    }


    @ApiOperation("添加用户")
    @PostMapping
    public Result addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @ApiOperation("根据用户名删除用户")
    @DeleteMapping
    public Result deleteUser(@RequestBody String username) {
        return userService.deleteUser(username);
    }

    @ApiOperation("更新用户")
    @PutMapping
    public Result updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }
}
