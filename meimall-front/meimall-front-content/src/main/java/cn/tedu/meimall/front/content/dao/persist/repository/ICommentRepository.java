package cn.tedu.meimall.front.content.dao.persist.repository;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.content.pojo.entity.Comment;
import cn.tedu.meimall.front.content.pojo.vo.CommentListItemVO;

/**
 * 處理評論資料的存儲庫介面
 */
public interface ICommentRepository {

    /**
     * 插入評論數量
     * @param comment 評論數量
     * @return 受影響的行數
     */
    int insert(Comment comment);

    /**
     * 根據ID刪除評論資料
     * @param id 評論ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據ID修改評論資料
     * @param comment 封裝了評論ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Comment comment);

    /**
     * 根據文章查詢評論列表
     * @param articleId 文章ID
     * @param pageNum   頁碼
     * @param pageSize  每頁記錄數
     * @return 評論列表
     */
    PageData<CommentListItemVO> listByArticle(Long articleId ,Integer pageNum, Integer pageSize);
}
