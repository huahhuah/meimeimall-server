package cn.tedu.meimall.front.content.service;

import cn.tedu.meimall.common.consts.data.ContentConsts;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.front.content.pojo.vo.ArticleStandardVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理文章資料的業務接口
 */
@Transactional
public interface IArticleService extends ContentConsts {

    /**
     * 根據ID查詢文章
     * @param id 文章ID
     * @return 匹配的文章資料詳情,如果沒有匹配的資料,則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的文章列表,將使用默認的每頁記錄數
     * @param pageNum  頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByRecommend(Integer pageNum);

    /**
     * 查詢推薦的文章列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize);

    /**
     * 跟據類別查詢文章列表,將使用默認的每頁記錄數
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId,Integer pageNum);

    /**
     * 跟據類別查詢文章列表
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);
}
