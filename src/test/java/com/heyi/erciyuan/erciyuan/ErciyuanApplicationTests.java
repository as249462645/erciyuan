package com.heyi.erciyuan.erciyuan;

import com.heyi.erciyuan.erciyuan.entity.User;
import com.heyi.erciyuan.erciyuan.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ErciyuanApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        User user= new User();
        user.setId(1);
        user.setToken(UUID.randomUUID().toString());
        user.setName("xiaohe");
        user.setAccountId(String.valueOf("123"));
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtCreate(user.getGmtCreate());
        userMapper.insert(user);
    }


}
