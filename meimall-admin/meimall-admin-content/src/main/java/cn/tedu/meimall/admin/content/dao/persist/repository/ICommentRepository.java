package cn.tedu.meimall.admin.content.dao.persist.repository;

import cn.tedu.meimall.admin.content.pojo.entity.Comment;
import cn.tedu.meimall.admin.content.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CommentStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;

import java.util.Collection;

/**
 * 處理評論資料的存儲庫接口
 */
public interface ICommentRepository {

    /**
     * 插入評論資料
     * @param comment 評論資料
     * @return 受影響的行數
     */
    int insert(Comment comment);

    /**
     * 根據ID刪除評論資料
     * @param id 評論ID
     * @return  受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據若干ID批量刪除評論資料
     * @param idList 若干個評論ID的數組
     * @return 受影響行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據文章刪除評論資料
     * @param resourceType 資源類型
     * @param resourceId   資源ID
     * @return  受影響的行數
     */
    int deleteByResource(Integer resourceType, Long resourceId);

    /**
     * 根據ID查詢評論資料詳情
     * @param comment 封裝了評論ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Comment comment);

    /**
     * 根據資源統計評論表中的資料的數量
     * @param resourceType 資源類型
     * @param resourceId   資源ID
     * @return 文章匹配的評論數量的數量
     */
    int countByResource(Integer resourceType, Long resourceId);

    /**
     * 根據ID查詢評論資料詳情
     * @param id 評論ID
     * @return 匹配的評論資料詳情,如果沒有匹配的資料,則返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查詢評論資料列表
     * @param resourceType 資源類型
     * @param pageNum      頁碼
     * @param pageSize     每頁記錄數
     * @return 評論資料列表
     */
    PageData<CommentListItemVO> listByResourceType(Integer resourceType, Integer pageNum, Integer pageSize);

}
