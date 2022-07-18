package com.soft.forum;

import com.soft.forum.dao.daodemo;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ForumApplication.class)
class ForumApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Test
    void contextLoads() {
        System.out.println(applicationContext);
        daodemo daodemo1 = applicationContext.getBean(daodemo.class);
        System.out.println(daodemo1.select());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
