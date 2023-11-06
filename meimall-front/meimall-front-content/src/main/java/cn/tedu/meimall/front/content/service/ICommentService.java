package cn.tedu.meimall.front.content.service;

import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.content.pojo.param.ArticleCommentAddNewParam;
import cn.tedu.meimall.front.content.pojo.vo.CommentListItemVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論資料的業務接口
 */
@Transactional
public interface ICommentService {

    /**
     * 發表文章評論
     * @param currentPrincipal           當事人
     * @param remoteAddr                 IP地址
     * @param articleCommentAddNewParam  新增的資料評論
     */
    void addNewArticleComment(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleCommentAddNewParam articleCommentAddNewParam);

    /**
     * 根據文章評論列表,將使用默認的每頁記錄數
     * @param articleId 文章的ID
     * @param pageNum   頁碼
     * @return  評論列表
     */
    PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum);
}
