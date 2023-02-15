package cn.nean.forum.controller;

import cn.nean.forum.model.dto.BlogDto;
import cn.nean.forum.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogApi {

    @Resource
    BlogService blogService;

    @GetMapping("/publish")
    public String publishBlog(){
        BlogDto blogDto = BlogDto.builder()
                .userId(1L)
                .shopId(10211L)
                .title("又又喜火锅真滴好吃")
                .images("localhost:8001/images/blogs/11/14/1a23bbe8-c96d-49cd-bdb9-bd98ee44c819.jpg")
                .content("大家快来吃")
                .liked(0)
                .comments(0)
                .build();
        return blogService.publishBlog(blogDto);
    }
}
