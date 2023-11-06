package cn.tedu.meimall.admin.content.dao.search;

import cn.tedu.meimall.admin.content.pojo.vo.search.ArticleSearchVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 處理文章搜索的存儲庫接口
 */
@Repository
public interface IArticleSearchRepository
        extends ElasticsearchRepository<ArticleSearchVO, Long> {

}

