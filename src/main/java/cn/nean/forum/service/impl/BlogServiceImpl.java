package cn.nean.forum.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.nean.forum.converter.BlogConverter;
import cn.nean.forum.index.BlogIndexMapper;
import cn.nean.forum.mapper.BlogMapper;
import cn.nean.forum.model.dto.BlogDto;
import cn.nean.forum.model.es.BlogIndexDo;
import cn.nean.forum.model.po.Blog;
import cn.nean.forum.service.BlogService;
import cn.nean.forum.service.ThreadService;
import cn.nean.forum.util.RedisIdWorker;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BlogServiceImpl implements BlogService {


    @Resource
    BlogConverter blogConverter;

    @Resource
    BlogMapper blogMapper;

    @Resource
    RedisIdWorker redisIdWorker;

    @Resource
    ThreadService threadService;

    /*
    *  发布朋友圈作品
    *  7.5s
    *  3.5s ~ 3.8s
    *  3.3s
    *  2.8s
    * */
    @Override
    @Transactional
    public String publishBlog(BlogDto blogDto) {
        // 生成 文章ID 2s 贼难受
        long blogId = redisIdWorker.nextId("blog");
        blogDto.setId(blogId);
        // 先将 dto -> po
        Blog blog = blogConverter.dtoToBlog(blogDto);
        // 判断是否为 大V 发布的作品, 模拟 userId = 1 的用户为 大V
        if(blog.getUserId() == 1L){
            //使用线程池异步将数据添加到 redis 缓存
            threadService.moveBlogToRedis(blog);
        }
        // 存入 mysql
        int i = blogMapper.insert(blog);
        if(i > 0){
            // blog -> indexDo
            BlogIndexDo blogIndexDo = blogConverter.toBlogIndexDo(blog);
            //使用线程池异步将数据添加到 es
            threadService.moveBlogToEs(blogIndexDo);
            return "发布成功!";
        }
        return "发布失败!";
    }
}
