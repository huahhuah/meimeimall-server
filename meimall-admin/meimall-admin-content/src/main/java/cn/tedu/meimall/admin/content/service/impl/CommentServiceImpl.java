package cn.tedu.meimall.admin.content.service.impl;

import cn.tedu.meimall.admin.content.dao.persist.repository.ICheckLogRepository;
import cn.tedu.meimall.admin.content.dao.persist.repository.ICommentRepository;
import cn.tedu.meimall.admin.content.pojo.entity.CheckLog;
import cn.tedu.meimall.admin.content.pojo.entity.Comment;
import cn.tedu.meimall.admin.content.pojo.param.CommentAddNewParam;
import cn.tedu.meimall.admin.content.pojo.param.CommentUpdateInfoParam;
import cn.tedu.meimall.admin.content.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CommentStandardVO;
import cn.tedu.meimall.admin.content.service.ICommentService;
import cn.tedu.meimall.common.consts.data.ContentConsts;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        log.info("創建業務類對象: CommentServiceImpl");
    }

    @Override
    public void add(CommentAddNewParam commentAddNewParam) {
        log.debug("開始處理【新增商品評論】的業務,參數:{}" ,commentAddNewParam);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewParam, comment);
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
    public void passCheck(CurrentPrincipal currentPrincipal, Long id, String remarks) {
        log.debug("開始處理【審核通過評論】的業務,當事人:{},評論ID:{},審核備注:{}", currentPrincipal, id, remarks);
        updateCheckById(currentPrincipal, id, CHECK_STATE_PASS, remarks);

    }

    @Override
    public void cancelCheck(CurrentPrincipal currentPrincipal, Long id, String remarks) {
        log.debug("開始處理【拒絕審核評論】的業務，當事人：{}，評論ID：{}，審核備注：{}", currentPrincipal, id, remarks);
        updateCheckById(currentPrincipal, id, CHECK_STATE_PASS, remarks);

    }

    @Override
    public void setDisplay(Long id) {
        log.debug("開始處理【顯示審核】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_ON);
    }

    @Override
    public void setHidden(Long id) {
        log.debug("開始處理【不顯示審核】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_OFF);
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Integer pageNum) {
        log.debug("開始處理【查詢文章的評論列表】的業務,頁碼:{}", pageNum);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_ARTICLE, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢文章的評論列表】的業務,頁碼:{}, 每頁記錄數:{}", pageNum, pageSize);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_ARTICLE, pageNum, pageSize);
    }

    @Override
    public PageData<CommentListItemVO> listByComment(Integer pageNum) {
        log.debug("開始處理【查詢評論的評論列表】的業務,頁碼:{}", pageNum);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_COMMENT, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CommentListItemVO> listByComment(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢評論的評論列表】的業務,頁碼:{}, 每頁記錄數:{}", pageNum, pageSize);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_COMMENT, pageNum, pageSize);
    }

    private void updateCheckById(CurrentPrincipal currentPrincipal,Long id, Integer checkState, String remarks){
        CommentStandardVO currentComment = commentRepository.getStandardById(id);
        if (currentComment == null) {
            String message = "將評論的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，評論資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentComment.getCheckState().equals(checkState)) {
            String message = "將評論的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，此評論已經處於" + CHECK_STATE_TEXT[checkState] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Comment updateComment = new Comment();
        updateComment.setId(id);
        updateComment.setCheckState(checkState);
        int rows = commentRepository.update(updateComment);
        if (rows != 1) {
            String message = "將評論的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
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
        if (rows != 1) {
            String message = "將文章的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updateDisplayById(Long id, Integer isDisplay){
        CommentStandardVO currentComment = commentRepository.getStandardById(id);
        if(currentComment == null) {
            String message = "將評論的顯示狀態修改為【" + DISPLAY_STATE_TEXT[isDisplay] + "】失敗，評論資料不存在！";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentComment.getIsDisplay().equals(isDisplay)) {
            String message = "將評論的顯示狀態修改為【" + DISPLAY_STATE_TEXT[isDisplay] + "】失敗，此評論已經處於" + DISPLAY_STATE_TEXT[isDisplay] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Comment updateComment = new Comment();
        updateComment.setId(id);
        updateComment.setIsDisplay(isDisplay);
        int rows = commentRepository.update(updateComment);
        if (rows != 1) {
            String message = "將評論的顯示狀態修改為【" + DISPLAY_STATE_TEXT[isDisplay] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

}
