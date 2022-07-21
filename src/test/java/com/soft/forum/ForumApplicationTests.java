package com.soft.forum;

import com.soft.forum.entity.User;
import com.soft.forum.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = ForumApplication.class)
@MapperScan("com.soft.forum.mapper")
class ForumApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        //Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
