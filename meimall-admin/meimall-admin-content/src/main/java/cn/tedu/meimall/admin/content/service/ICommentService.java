package cn.tedu.meimall.admin.content.service;

import cn.tedu.meimall.admin.content.pojo.param.CommentAddNewParam;
import cn.tedu.meimall.admin.content.pojo.param.CommentUpdateInfoParam;
import cn.tedu.meimall.admin.content.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.common.consts.data.ContentConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論資料的業務接口
 */
@Transactional
public interface ICommentService extends ContentConsts {

    /**
     * 新增評論
     * @param commentAddNewParam 評論的參數
     */
    void add(CommentAddNewParam commentAddNewParam);

    /**
     * 根據ID刪除評論
     * @param id ID
     */
    void deleteById(Long id);

    /**
     * 更改評論
     * @param commentUpdateInfoParam  評論的參數
     */
    void update(CommentUpdateInfoParam commentUpdateInfoParam);


    /**
     *審核通過評論
     * @param currentPrincipal 當事人
     * @param id               嘗試審核通過的評論的ID
     * @param remarks          審核備注
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);

    /**
     *拒絕審核評論
     * @param currentPrincipal 當事人
     * @param id               嘗試審核通過的評論的ID
     * @param remarks          審核備注
     */
    void cancelCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);

    /**
     * 顯示評論
     * @param id 嘗試顯示的評論的ID
     */
    void setDisplay(Long id);

    /**
     * 隱藏(不顯示) 評論
     * @param id 嘗試隱藏的評論的ID
     */
    void setHidden(Long id);

    /**
     * 查詢文章的評論列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 文章的評論資料列表
     */
    PageData<CommentListItemVO> listByArticle(Integer pageNum);

    /**
     * 查詢文章的評論列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章的評論資料列表
     */
    PageData<CommentListItemVO> listByArticle(Integer pageNum, Integer pageSize);

    /**
     * 查詢評論的評論列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 評論的評論列表
     */
    PageData<CommentListItemVO> listByComment(Integer pageNum);

    /**
     * 查詢評論的評論列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論的評論列表
     */
    PageData<CommentListItemVO> listByComment(Integer pageNum, Integer pageSize);

}
