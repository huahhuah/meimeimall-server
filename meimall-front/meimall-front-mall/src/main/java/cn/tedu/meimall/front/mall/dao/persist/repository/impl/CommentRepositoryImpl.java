package cn.tedu.meimall.front.mall.dao.persist.repository.impl;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import cn.tedu.meimall.front.mall.dao.persist.mapper.CommentMapper;
import cn.tedu.meimall.front.mall.dao.persist.repository.ICommentRepository;
import cn.tedu.meimall.front.mall.pojo.entity.Comment;
import cn.tedu.meimall.front.mall.pojo.vo.CommentListItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理商品評論數據的存儲庫實現類
 */
@Slf4j
@Repository
public class CommentRepositoryImpl implements ICommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    public CommentRepositoryImpl(){
        log.info("創建存儲庫對象: CommentRepositoryImpl");
    }

    @Override
    public int insert(Comment comment) {
        log.debug("開始執行【插入評論】的數據訪問,參數:{}", comment);
        return commentMapper.insert(comment);
    }

    @Override
    public PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據商品查詢評論數據列表】的數據訪問,商品ID:{},頁碼:{},每頁記錄數:{}", goodsId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.list(goodsId);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
