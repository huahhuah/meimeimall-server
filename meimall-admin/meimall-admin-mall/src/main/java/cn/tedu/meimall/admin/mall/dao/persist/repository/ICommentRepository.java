package cn.tedu.meimall.admin.mall.dao.persist.repository;

import cn.tedu.meimall.admin.mall.pojo.entity.Comment;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentInfoVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;

/**
 * 處理商品評論資料的存儲庫介面
 */
public interface ICommentRepository {

    /**
     * 新增評論
     * @param comment 評論
     * @return 受影響的行數
     */
    int insert(Comment comment);

    /**
     * 根據商品ID刪除評論
     * @param id ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據ID修改評論資料
     * @param comment 封裝了評論ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Comment comment);

    /***
     * 根據ID查詢評論
     * @param id  評論ID
     * @return  匹配的評論,如果沒有匹配的資料,則返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查詢評論資料列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論資料列表
     */
    PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根據作者ID查詢評論
     * @param authorId 作者I
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論資料列表
     */
    PageData<CommentInfoVO> getCommentListByAuthorId(Long authorId, Integer pageNum, Integer pageSize);

    /**
     * 根據商品ID查詢評論
     * @param goodsId  商品ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論資料列表
     */
    PageData<CommentInfoVO> getCommentListByGoodsId(Long goodsId, Integer pageNum, Integer pageSize);

}
