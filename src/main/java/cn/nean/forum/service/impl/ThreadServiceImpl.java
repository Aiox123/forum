package cn.nean.forum.service.impl;

import cn.hutool.json.JSONUtil;
import cn.nean.forum.index.BlogIndexMapper;
import cn.nean.forum.model.es.BlogIndexDo;
import cn.nean.forum.model.po.Blog;
import cn.nean.forum.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ThreadServiceImpl implements ThreadService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    BlogIndexMapper blogIndexMapper;

    @Override
    @Async("redisTaskExecutor")
    public void moveBlogToRedis(Blog blog) {
        try {
            TimeUnit.SECONDS.sleep(3);
            String key = "hot_blog:" + blog.getId();
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(blog));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Redis 同步成功!");
    }

    @Override
    @Async("esTaskExecutor")
    public void moveBlogToEs(BlogIndexDo blogIndexDo) {
        try {
            TimeUnit.SECONDS.sleep(3);
            blogIndexMapper.save(blogIndexDo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Es 同步成功!");
    }
}
