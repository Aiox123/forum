package cn.nean.forum;

import cn.nean.forum.converter.BlogConverter;
import cn.nean.forum.model.po.Blog;
import cn.nean.forum.model.vo.BlogVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ForumApplicationTests {

	@Resource
	BlogConverter blogConverter;

	@Test
	void contextLoads() {
		Blog blog = Blog.builder().title("nean 真帅!").content("太帅啦").likes(100).build();
		BlogVo blogVo = blogConverter.toBlogVo(blog);
		System.out.println(blogVo);
	}

}
