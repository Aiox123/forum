package cn.nean.forum.service;

import cn.nean.forum.model.es.BlogIndexDo;
import cn.nean.forum.model.po.Blog;

public interface ThreadService {
    void moveBlogToRedis(Blog blog);

    void moveBlogToEs(BlogIndexDo blogIndexDo);
}
