package com.soft.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.forum.Utils.Res;
import com.soft.forum.Utils.resCodeEnum;
import com.soft.forum.entity.News;
import com.soft.forum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public Res getAllNews(){
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(News::getNewsState, 1);
        List<News> listNews = newsService.getBaseMapper().selectList(queryWrapper);
        return Res.ok().data(listNews);
    }

    @GetMapping("/{id}")
    public Res getNewsById(@PathVariable String id){
        int news_id = Integer.parseInt(id);
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(News::getNewsId, news_id).eq(News::getNewsState, 1);
        List<News> listNews = newsService.getBaseMapper().selectList(queryWrapper);
        if (listNews.isEmpty()){
            return Res.error(resCodeEnum.FAIL);
        }
        return Res.ok().data("第"+ String.valueOf(id) + "篇", listNews.get(0));
    }
    @PostMapping
    public Res addNews(@RequestBody News news){
        if (news == null) return Res.error();
        return newsService.save(news) ? Res.ok() : Res.error();
    }

    @PostMapping("/{id}")
    public Res updateNews(@RequestBody News news, @PathVariable String id){
        if (news == null || newsService.getById(id) == null) return Res.error(resCodeEnum.PARAM_ERROR);
        return newsService.updateById(news) ? Res.ok() : Res.error();
    }

    @DeleteMapping("/{id}")
    public Res delNews(@PathVariable String id){
        if (newsService.getById(id) == null) return Res.error(resCodeEnum.PARAM_ERROR);
        return newsService.removeById(id) ? Res.ok() : Res.error();
    }

}
