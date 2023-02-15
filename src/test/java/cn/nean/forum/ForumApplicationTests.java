package cn.nean.forum;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.nean.forum.index.BlogIndexMapper;
import cn.nean.forum.mapper.BlogMapper;
import cn.nean.forum.model.es.BlogIndexDo;
import cn.nean.forum.model.po.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class ForumApplicationTests {

    @Resource
    BlogIndexMapper blogIndexMapper;

    @Resource
    BlogMapper blogMapper;

    /*
    *  创建索引
    * */
    @Test
    void testEsSave(){
        BlogIndexDo blogIndexDo = BlogIndexDo.builder()
                .id(2L)
                .userId(1L)
                .shopId(2L)
                .title("喜茶大降价啦")
                .content("喜茶会员五折购")
                .images("12231")
                .comments(20)
                .liked(20)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        BlogIndexDo isSave = blogIndexMapper.save(blogIndexDo);
        System.out.println(isSave);
    }

    @Test
    void testEsQuery(){
        Page<BlogIndexDo> blogIndexDos = blogIndexMapper.findByTitle("喜茶", PageRequest.of(0, 2));
        for (BlogIndexDo blogIndexDo : blogIndexDos) {
            System.out.println(blogIndexDo);
        }
    }

    @Test
    void TestIdUtil(){
        Snowflake snowflake = IdUtil.getSnowflake();
        Blog blog = Blog.builder()
                .id(snowflake.nextId())
                .userId(1L)
                .shopId(2L)
                .title("喜茶大降价啦")
                .content("喜茶会员五折购")
                .images("12231")
                .comments(20)
                .liked(20)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        System.out.println(blogMapper.insert(blog));
    }



}
