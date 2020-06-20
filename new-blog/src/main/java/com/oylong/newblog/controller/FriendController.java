package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Friend;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.FriendService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "友链相关的接口")
@RequestMapping("/friends")
public class FriendController {
    @Resource
    FriendService friendService;

    @GetMapping
    public Result getFriends(@RequestParam("page")int page, @RequestParam("limit")int limit){
        return friendService.selectFriends(page, limit);
    }

    @GetMapping("/{id}")
    public Result getFriendById(@PathVariable("id") Long id){
        return friendService.selectFriendById(id);
    }

    @PutMapping
    public Result updateFriend(@Valid @RequestBody Friend friend){
        return friendService.updateFriend(friend);
    }

    @DeleteMapping("/{id}")
    public Result deleteFriend(@PathVariable("id") Long id){
        return friendService.deleteFriend(id);
    }

    @PostMapping
    public Result insertFriend(@RequestBody @Valid Friend friend){
        return friendService.insertFriend(friend);
    }

}
