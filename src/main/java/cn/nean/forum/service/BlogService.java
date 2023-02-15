package cn.nean.forum.service;

import cn.nean.forum.model.dto.BlogDto;

public interface BlogService {
    String publishBlog(BlogDto blogDto);
}
