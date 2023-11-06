package cn.tedu.meimall.admin.content.dao.persist.repository;

import cn.tedu.meimall.admin.content.pojo.entity.Article;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.admin.content.pojo.vo.search.ArticleSearchVO;
import cn.tedu.meimall.common.pojo.vo.PageData;

import java.util.Collection;

/**
 * 處理文章資料的存儲庫接口
 */
public interface IArticleRepository {

    /**
     * 插入文章資料
     * @param article 文章資料
     * @return 受影響的行數
     */
    int insert(Article article);

    /**
     * 根據ID刪除文章資料
     * @param id 文章ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據若干個ID批量刪除文章資料
     * @param idList 若干個文章ID的數組
     * @return 受影響的行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據ID修改文章資料
     * @param article 封裝了文章ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Article article);

    /**
     * 根據ID查詢文章資料詳情
     * @param categoryId 文章ID
     * @return 歸屬此類別下的文章數量
     */
    int countByCategory(Long categoryId);

    /**
     * 根據ID查詢文章資料詳情
     * @param id 文章ID
     * @return 匹配的文章資料詳情,如果沒有匹配的資料,則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢文章資料列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章資料列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 查詢文章資料列表，用於讀取資料後存入到elasticsearch中
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章資料列表
     */
    PageData<ArticleSearchVO> listSearchVO(Integer pageNum, Integer pageSize);

    /**
     * 根據類别查詢文章列表
     *
     * @param categoryId 文章類别的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);

}
