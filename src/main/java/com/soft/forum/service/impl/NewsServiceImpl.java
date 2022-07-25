package com.soft.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.forum.entity.News;
import com.soft.forum.mapper.NewsMapper;
import com.soft.forum.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
