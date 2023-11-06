package cn.tedu.meimall.front.content.dao.persist.repository.impl;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import cn.tedu.meimall.front.content.dao.persist.mapper.CommentMapper;
import cn.tedu.meimall.front.content.dao.persist.repository.ICommentRepository;
import cn.tedu.meimall.front.content.pojo.entity.Comment;
import cn.tedu.meimall.front.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.front.content.pojo.vo.CommentListItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *處理評論資料的存儲庫實現類
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
        log.debug("開始持行【插入評論】的資料訪問,參數:{}", comment);
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除評論】的資料訪問,參數:{}", id);
        return commentMapper.deleteById(id);
    }

    @Override
    public int update(Comment comment) {
        log.debug("開始執行【更新評論】的資料訪問,參數:{}", comment);
        return commentMapper.updateById(comment);
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據文章查詢評論列表】的資料訪問,文章:{},頁碼:{}", articleId, pageNum);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.listByArticle(articleId);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        PageData<CommentListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }
}
