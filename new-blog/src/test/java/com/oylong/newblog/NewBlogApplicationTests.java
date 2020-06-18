package com.oylong.newblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class NewBlogApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new Date());
    }

}
