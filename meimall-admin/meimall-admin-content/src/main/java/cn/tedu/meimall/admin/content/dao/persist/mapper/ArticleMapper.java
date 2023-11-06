package cn.tedu.meimall.admin.content.dao.persist.mapper;

import cn.tedu.meimall.admin.content.pojo.entity.Article;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.admin.content.pojo.vo.search.ArticleSearchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章資料的Mapper接口
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /***
     * 根據ID查詢文章
     * @param id 文章ID
     * @return 匹配的文章,如果沒有匹配的資料,則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢文章資料列表
     * @return 文章資料列表
     */
    List<ArticleListItemVO> list();

    /**
     * 查詢文章資料列表,用於讀取資料後存入到elasticsearch中
     * @return 文章資料列表
     */
    List<ArticleSearchVO> listSearchVO();

    /**
     * 跟據類別查詢文章列表
     * @param categoryId 文章類別ID
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategory(Long categoryId);
}
