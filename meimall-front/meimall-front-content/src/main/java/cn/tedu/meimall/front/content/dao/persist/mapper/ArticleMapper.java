package cn.tedu.meimall.front.content.dao.persist.mapper;

import cn.tedu.meimall.front.content.pojo.entity.Article;
import cn.tedu.meimall.front.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.front.content.pojo.vo.ArticleStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章資料的Mapper介面
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根據ID查詢文章
     * @param id 文章ID
     * @return 匹配的文章,如果沒有匹配的資料,則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的文章列表
     * @return 文章列表
     */
    List<ArticleListItemVO> listByRecommend();

    /**
     * 根據文章類別查詢其他文章
     * @param categoryId 文章類別ID
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategory(Long categoryId);
}
