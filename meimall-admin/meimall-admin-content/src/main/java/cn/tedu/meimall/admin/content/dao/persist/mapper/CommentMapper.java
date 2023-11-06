package cn.tedu.meimall.admin.content.dao.persist.mapper;

import cn.tedu.meimall.admin.content.pojo.entity.Comment;
import cn.tedu.meimall.admin.content.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CommentStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論資料的Mapper接口
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根據ID查詢評論資料詳情
     * @param id 評論ID
     * @return 匹配的評論資料詳情,如果沒有匹配的資料,則反為null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查詢評論資料列表
     * @param resourceType 資源類型
     * @return 評論資料列表
     */
    List<CommentListItemVO> listByResourceType(Integer resourceType);
}
