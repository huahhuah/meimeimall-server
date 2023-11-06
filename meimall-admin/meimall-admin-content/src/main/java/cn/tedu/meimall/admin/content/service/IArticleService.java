package cn.tedu.meimall.admin.content.service;

import cn.tedu.meimall.admin.content.pojo.param.ArticleAddNewParam;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.common.consts.data.ContentConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理文章資料的業務接口
 */
@Transactional
public interface IArticleService extends ContentConsts {

    /**
     * 發布文章
     * @param currentPrincipal   當事人
     * @param remoteAddr        IP地址
     * @param articleAddNewParam 新的文章資料
     */
    void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleAddNewParam articleAddNewParam);

    /**
     * 根據ID刪除文章
     * @param id 嘗試刪除的文章資料的ID
     */
    void delete(Long id);


    /**
     * 審核通過文章
     * @param currentPrincipal 當事人
     * @param id               嘗試審核通過的文章的ID
     * @param remarks          審核備注
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);


    /**
     * 拒絕審核文章
     * @param currentPrincipal 當事人
     * @param id               嘗試拒絕審核的文章的ID
     * @param remarks          審核備注
     */
    void rejectCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);

    /**
     * 顯示文章
     * @param id 嘗試顯示的文章的ID
     */
    void setDisplay(Long id);

    /**
     * 隱藏(不顯示) 文章
     * @param id 嘗試隱藏的文章的ID
     */
    void setHidden(Long id);

    /**
     * 根據ID查詢文章
     *
     * @param id 文章ID
     * @return 匹配的文章資料詳情，如果没有匹配的資料，則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢文章列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum);

    /**
     * 查詢文章列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根據類别查詢文章列表，將使用默認的每頁記錄數
     *
     * @param categoryId 文章類别的ID
     * @param pageNum    頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum);

    /**
     * 根據類别查詢文章列表
     *
     * @param categoryId 文章類别的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);

    /**
     * 重建搜索資料（重新從資料庫中獲取資料並寫入到ES中）
     */
    void rebuildSearch();
}
