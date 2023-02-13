package cn.nean.forum.index;

import cn.nean.forum.model.es.BlogIndexDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogIndexMapper extends ElasticsearchRepository<BlogIndexDo,Long> {

    /*
    *  根据 title 查询文章
    * */
    Page<BlogIndexDo> findByTitle(String title, Pageable pageable);
}
