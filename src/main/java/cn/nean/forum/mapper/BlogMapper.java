package cn.nean.forum.mapper;

import cn.nean.forum.model.po.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    /*
    *  保存文章信息
    * */
    int saveBlog(@Param("blog") Blog blog);
}
