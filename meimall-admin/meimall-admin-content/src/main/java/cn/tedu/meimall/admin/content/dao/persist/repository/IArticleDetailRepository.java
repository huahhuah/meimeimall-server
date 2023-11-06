package cn.tedu.meimall.admin.content.dao.persist.repository;

import cn.tedu.meimall.admin.content.pojo.entity.ArticleDetail;

/**
 * 處理文章詳情資料的存儲庫接口
 */
public interface IArticleDetailRepository {

    /**
     * 插入文章詳情資料
     * @param articleDetail 文章詳情資料
     * @return 受影響的行數
     */
    int insert(ArticleDetail articleDetail);

    /**
     * 根據文章ID刪除文章詳情資料
     * @param articleId 文章ID
     * @return 受影響的行數
     */
    int deleteByArticle(Long articleId);

    /***
     * 根據ID修改文章詳情資料
     * @param articleDetail 封裝了文章ID和新文章詳情資料的對象
     * @return 受影響的行數
     */
    int updateByArticle(ArticleDetail articleDetail);
}
