package cn.tedu.meimall.admin.mall.service.impl;

import cn.tedu.meimall.admin.mall.dao.cache.IUserCacheRepository;
import cn.tedu.meimall.admin.mall.dao.persist.repository.ICheckLogRepository;
import cn.tedu.meimall.admin.mall.dao.persist.repository.ICommentRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.CheckLog;
import cn.tedu.meimall.admin.mall.pojo.entity.Comment;
import cn.tedu.meimall.admin.mall.pojo.param.CommentAddInfoParam;
import cn.tedu.meimall.admin.mall.pojo.param.CommentUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentInfoVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentStandardVO;
import cn.tedu.meimall.admin.mall.service.ICommentService;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 處理評論資料的業務實現類
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private ICheckLogRepository checkLogRepository;

    public CommentServiceImpl(){
        log.debug("創建業務類對象: CommentServiceImpl");
    }


    @Override
    public void add(CommentAddInfoParam commentAddInfoParam) {
        log.debug("開始處理【新增商品評論】的業務,參數:{}" , commentAddInfoParam);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddInfoParam, comment);
        int rows = commentRepository.insert(comment);
        if(rows != 1){
            String message = "新增失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("開始處理【根據ID刪除評論】的業務,參數:{}", id);
        CommentStandardVO queryResult = commentRepository.getStandardById(id);
        if(queryResult == null){
            String message = "刪除評論失敗,嘗試刪除的評論資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = commentRepository.deleteById(id);
        if(rows != 1){
            String message = "刪除評論失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        commentRepository.deleteById(id);
    }

    @Override
    public void update(CommentUpdateInfoParam commentUpdateInfoParam) {
        log.debug("開始處理【更新評論】的業務,參數:{}", commentUpdateInfoParam);
        CommentStandardVO currentComment = commentRepository.getStandardById(commentUpdateInfoParam.getId());
        if(currentComment == null){
            String message = "刪除失敗,評論不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentUpdateInfoParam, comment);
        int rows = commentRepository.update(comment);
        if(rows < 1){
            String message = "更新失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

    }

    @Override
    public void passCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks) {
        log.debug("開始處理【審核通過評論】的業務,參數:{}", goodsId);
        updateCheckById(currentPrincipal, goodsId, CHECK_STATE_PASS, remarks);
    }

    @Override
    public void rejectCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks) {
        log.debug("開始處理【拒絕審核評論】的業務,參數:{}", goodsId);
        updateCheckById(currentPrincipal, goodsId, CHECK_STATE_REJECT, remarks);
    }

    @Override
    public PageData<CommentListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢商品評論列表】的業務,頁碼:{}", pageNum);
        return commentRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢商品的評論列表】的業務,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        return commentRepository.list(pageNum, pageSize);
    }

    @Override
    public PageData<CommentInfoVO> getCommentListByAuthorId(Long authorId,Integer pageNum) {

        if (commentRepository.getStandardById(authorId)==null){
            String message="查詢失敗，作者不存在";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND,message);
        }
        return getCommentListByAuthorId(authorId,pageNum, defaultQueryPageSize);
    }


    @Override
    public PageData<CommentInfoVO> getCommentListByAuthorId(Long authorId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據作者ID查詢評論列表】的業務,作者ID:{},頁碼:{},每頁記錄數:{}", authorId, pageNum, pageSize);
        if(commentRepository.getStandardById(authorId) == null){
            String message="查詢失敗，用戶不存在";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND,message);
        }
        return getCommentListByAuthorId(authorId, pageNum, pageSize);
    }

    @Override
    public PageData<CommentInfoVO> getCommentListByGoodsId(Long goodsId,Integer pageNum) {
        if (commentRepository.getStandardById(goodsId)==null){
            String message="查詢失敗，作者不存在";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND,message);
        }
        return getCommentListByAuthorId(goodsId,pageNum, defaultQueryPageSize);
    }


    @Override
    public PageData<CommentInfoVO> getCommentListByGoodsId(Long goodsId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據商品ID查詢評論列表】的業務,商品ID:{},頁碼:{},每頁記錄數:{}", goodsId, pageNum, pageSize);
        if(commentRepository.getStandardById(goodsId) == null){
            String message="查詢失敗，商品不存在";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND,message);
        }
        return getCommentListByGoodsId(goodsId, pageNum, pageSize);
    }

    /**
     * 根據ID修改評論的審核狀態
     * @param currentPrincipal  當事人
     * @param id                評論ID
     * @param checkState        新狀態
     * @param remarks           審核備註
     */
    public  void updateCheckById(CurrentPrincipal currentPrincipal,Long id, Integer checkState, String remarks){
        CommentStandardVO currentComment = commentRepository.getStandardById(id);
        if(currentComment == null){
            String message = "將評論的審核狀態修改為【"+ CHECK_STATE_TEXT[checkState] + "】失敗,評論資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(currentComment.getCheckState().equals(checkState)){
            String message = "將評論的審核狀態改為【"+ CHECK_STATE_TEXT[checkState] + "】失敗,此評論已經處於" + CHECK_STATE_TEXT[checkState] + "狀態!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Comment updateComment = new Comment();
        updateComment.setId(id);
        updateComment.setCheckState(checkState);
        int rows = commentRepository.update(updateComment);
        if(rows != 1){
            String message = "將評論的審核狀態修改為【"+ CHECK_STATE_TEXT[checkState] + "】失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        String content = currentComment.getContent();
        String brief = content.length() < BRIEF_MAX_LENGTH ? content : content.substring(0, BRIEF_MAX_LENGTH);

        CheckLog checkLog = new CheckLog();
        checkLog.setResourceType(RESOURCE_TYPE_COMMENT);
        checkLog.setResourceId(id);
        checkLog.setResourceBrief(brief);
        checkLog.setCheckUserId(currentPrincipal.getId());
        checkLog.setCheckUsername(currentPrincipal.getUsername());
        checkLog.setCheckRemarks(remarks);
        checkLog.setOriginalState(currentComment.getCheckState());
        checkLog.setNewState(checkState);
        checkLog.setGmtCheck(LocalDateTime.now());
        rows = checkLogRepository.insert(checkLog);
        if(rows != 1){
            String message = "將評論的審核狀態修改為【"+ CHECK_STATE_TEXT[checkState] +"】失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

    }
}
