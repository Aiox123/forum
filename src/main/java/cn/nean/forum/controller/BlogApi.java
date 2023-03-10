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
                .shopId(12201L)
                .title("半天妖烤鱼新品尝鲜")
                .images("localhost:8001/images/blogs/11/14/1a23bbe8-c96d-49cd-bdb9-bd98ee44c819.jpg")
                .content("大家快来买呀")
                .liked(0)
                .comments(0)
                .build();
        return blogService.publishBlog(blogDto);
    }
}
