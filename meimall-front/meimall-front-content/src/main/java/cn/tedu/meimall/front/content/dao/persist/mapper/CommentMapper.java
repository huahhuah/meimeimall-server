package cn.tedu.meimall.front.content.dao.persist.mapper;

import cn.tedu.meimall.front.content.pojo.entity.Comment;
import cn.tedu.meimall.front.content.pojo.vo.CommentListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論資料的Mapper介面
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根據文章查詢評論列表
     * @param articleId 文章ID
     * @return 評論列表
     */
    List<CommentListItemVO> listByArticle(Long articleId);
}
