package cn.tedu.meimall.admin.mall.dao.persist.repository.impl;

import cn.tedu.meimall.admin.mall.dao.persist.mapper.CommentMapper;
import cn.tedu.meimall.admin.mall.dao.persist.repository.ICommentRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.Comment;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentInfoVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理商品評論資料的存儲庫實現類
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
        log.debug("開始執行【新增評論】的資料訪問，參數：{}",comment);
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據商品ID刪除評論】的資料訪問,參數:{}", id);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return commentMapper.delete(queryWrapper);
    }

    @Override
    public int update(Comment comment) {
        log.debug("開始執行【更新評論】的資料訪問，參數:{}", comment);
        return commentMapper.updateById(comment);
    }

    @Override
    public CommentStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢評論】的資料訪問,參數:{}", id);
        return commentMapper.getStandardById(id);
    }

    @Override
    public PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢評論列表】的資料訪問,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.list();
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<CommentInfoVO> getCommentListByAuthorId(Long authorId, Integer pageNum, Integer pageSize) {
       log.debug("開始執行【根據作者ID查詢評論】的資料訪問,作者ID:{},頁碼:{},每頁記錄數:{}", authorId, pageNum, pageSize);
       //封裝pageData
        PageHelper.startPage(pageNum, pageSize);
        List<CommentInfoVO> list = commentMapper.getCommentListByAuthorId(authorId);
        PageInfo<CommentInfoVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<CommentInfoVO> getCommentListByGoodsId(Long goodsId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據商品ID查詢評論】的資料訪問,商品ID:{},頁碼:{},每頁記錄數:{}", goodsId, pageNum, pageSize);
        //封裝pageData
        PageHelper.startPage(pageNum, pageSize);
        List<CommentInfoVO> list = commentMapper.getCommentListByGoodsId(goodsId);
        PageInfo<CommentInfoVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
