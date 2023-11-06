package cn.tedu.meimall.front.content.dao.persist.repository;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.front.content.pojo.vo.ArticleStandardVO;

/**
 * 處理文章資料的存儲庫介面
 */
public interface IArticleRepository {

    /**
     * 設置文章的評論數
     * @param articleId     文章ID
     * @param commentCount  評論數的新值
     * @return 受影響的行數
     */
    int setCommentCount(Long articleId, Integer commentCount);

    /**
     * 根據ID查詢文章資料詳情
     * @param id 文章ID
     * @return 匹配的文章資料詳情,如果沒有匹配的資料,則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的文章列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章資料列表
     */
    PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize);

    /**
     * 跟據類別查詢文章列表
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return  文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);
}
