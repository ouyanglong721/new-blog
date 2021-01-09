package com.oylong.newblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.dao.FriendMapper;
import com.oylong.newblog.entity.Friend;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.FriendService;
import com.oylong.newblog.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    FriendMapper friendMapper;

    @Override
    public Result insertFriend(Friend friend) {
        friendMapper.insert(friend);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result deleteFriend(Long id) {
        Friend friend = friendMapper.selectById(id);
        if(friend == null){
            return ResultUtil.buildUnSuccessResult("友链不存在");
        }
        friendMapper.deleteById(id);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result updateFriend(Friend friend) {
        Friend tf = friendMapper.selectById(friend.getId());
        if(friend == null){
            return ResultUtil.buildUnSuccessResult("友链不存在");
        }
        friendMapper.updateById(friend);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result selectFriends(int page, int limit) {
        IPage<Friend> iPage = new Page<>(page, limit);
        IPage<Friend> p = friendMapper.selectPage(iPage, null);

        Result result = ResultUtil.buildSuccessResult();

        result.addData("total", p.getTotal());
        result.addData("list", p.getRecords());

        return result;
    }

    @Override
    public Result selectFriendById(Long id) {
        Friend tf = friendMapper.selectById(id);
        if(tf == null){
            return ResultUtil.buildUnSuccessResult("友链不存在");
        }


        Result result = ResultUtil.buildSuccessResult();
        result.setData(tf);
        return result;
    }
}
