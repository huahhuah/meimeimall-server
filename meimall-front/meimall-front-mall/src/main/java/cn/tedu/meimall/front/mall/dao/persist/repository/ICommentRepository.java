package cn.tedu.meimall.front.mall.dao.persist.repository;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.entity.Comment;
import cn.tedu.meimall.front.mall.pojo.vo.CommentListItemVO;


/**
 * 處理商品評論資料的存儲庫接口
 */
public interface ICommentRepository {

    /**
     * 插入評論資料
     * @param comment 評論資料
     * @return 受影響的行數
     */
    int insert(Comment comment);

    /**
     * 根據商品查詢評論資料列表
     * @param goodsId  商品ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論資料列表
     */
    PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize);
}
