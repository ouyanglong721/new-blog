package com.oylong.newblog.service;

import com.oylong.newblog.entity.Friend;
import com.oylong.newblog.entity.Result;

public interface FriendService {
    Result insertFriend(Friend friend);
    Result deleteFriend(Long id);
    Result updateFriend(Friend friend);
    Result selectFriends(int page, int limit);
    Result selectFriendById(Long id);
}
