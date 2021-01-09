package com.oylong.newblog;

import com.oylong.newblog.controller.UserController;
import com.oylong.newblog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewBlogApplicationTests {

    @Autowired
    UserController userController;
    @Test
    void contextLoads() {
        User user = new User();
        user.setId(1L);
        user.setPassword("admin1");
        userController.updateUser(user);
    }


}
