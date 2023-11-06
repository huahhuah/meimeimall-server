package cn.tedu.meimall.front.mall.dao.persist.mapper;

import cn.tedu.meimall.front.mall.pojo.entity.Comment;
import cn.tedu.meimall.front.mall.pojo.vo.CommentListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論資料的Mapper介面
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根據商品查詢評論資料列表
     * @param goodsId 商品ID
     * @return 評論資料列表
     */
    List<CommentListItemVO> list(Long goodsId);
}
