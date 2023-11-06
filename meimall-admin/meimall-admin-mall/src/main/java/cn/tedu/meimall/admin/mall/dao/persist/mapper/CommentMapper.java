package cn.tedu.meimall.admin.mall.dao.persist.mapper;

import cn.tedu.meimall.admin.mall.pojo.entity.Comment;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentInfoVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論資料的Mapper介面
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根據ID查詢評論
     * @param id 評論ID
     * @return 匹配的評論,如果沒有匹配的資料,則返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查詢評論資料列表
     * @return 評論資料列表
     */
    List<CommentListItemVO> list();

    /**
     * 根據作者ID查詢評論
     * @param authorId 作者ID
     * @return 評論資料列表
     */
    List<CommentInfoVO> getCommentListByAuthorId(Long authorId);

    /**
     * 根據商品ID查詢評論
     * @param goodsId 商品ID
     * @return 評論資料列表
     */
    List<CommentInfoVO> getCommentListByGoodsId(Long goodsId);
}
