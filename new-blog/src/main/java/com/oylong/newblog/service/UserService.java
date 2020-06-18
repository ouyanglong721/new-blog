package com.oylong.newblog.service;

import com.oylong.newblog.entity.Result;
import com.oylong.newblog.entity.User;

public interface UserService {
   Result getUser(String username,int page, int limit);
   Result login(String username, String password);
   Result logout(String token);

   Result addUser(User user);

   Result deleteUser(Long id);

   Result updateUser(User user);

   Result deleteUser(String username);

   Result sendFindPassWordEmail(String username);

   Result findPassword(String username, String password, String captcha);

   Result getUserInfoByToken(String token);

   Result getUserInfoById(Long id);

}
