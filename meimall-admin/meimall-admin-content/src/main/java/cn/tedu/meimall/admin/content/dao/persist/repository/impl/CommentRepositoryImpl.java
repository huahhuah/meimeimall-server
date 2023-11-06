package cn.tedu.meimall.admin.content.dao.persist.repository.impl;

import cn.tedu.meimall.admin.content.dao.persist.mapper.CommentMapper;
import cn.tedu.meimall.admin.content.dao.persist.repository.ICommentRepository;
import cn.tedu.meimall.admin.content.pojo.entity.Comment;
import cn.tedu.meimall.admin.content.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CommentStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 處理評論資料的存儲庫實現類
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
        log.debug("開始執行【插入評論】的資料訪問,參數:{}", comment);
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除評論】的資料訪問,參數:{}", id);
        return commentMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("開始執行【批量刪除評論】的資料訪問,參數:{}", idList);
        return commentMapper.deleteBatchIds(idList);
    }

    @Override
    public int deleteByResource(Integer resourceType, Long resourceId) {
        log.debug("開始執行【根據資源刪除評論資料】的資料訪問,資源類型:{},資源ID:{}", resourceType, resourceId);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", resourceType);
        queryWrapper.eq("resource_id", resourceId)
                    .eq("resource_id", resourceId);
        return commentMapper.delete(queryWrapper);
    }

    @Override
    public int update(Comment comment) {
        log.debug("開始執行【更新評論】的資料訪問,參數:{}", comment);
        return commentMapper.updateById(comment);
    }

    @Override
    public int countByResource(Integer resourceType, Long resourceId) {
        log.debug("開始執行【根據資源統計評論表中的資料的數量】的資料訪問,資源類型:{},資源ID:{}", resourceType, resourceId);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", resourceType)
                .eq("resource_id", resourceId);
        return commentMapper.selectCount(queryWrapper);
    }

    @Override
    public CommentStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢評論信息】的資料訪問,參數:{}", id);
        return commentMapper.getStandardById(id);
    }

    @Override
    public PageData<CommentListItemVO> listByResourceType(Integer resourceType, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢評論列表】的資料訪問,頁碼:{},每頁記錄數:{}",  pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.listByResourceType(resourceType);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
