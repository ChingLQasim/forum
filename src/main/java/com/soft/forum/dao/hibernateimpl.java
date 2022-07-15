package com.soft.forum.dao;

import org.springframework.stereotype.Repository;

@Repository
public class hibernateimpl implements daodemo{

    @Override
    public String select() {
        return "hello";
    }
}
