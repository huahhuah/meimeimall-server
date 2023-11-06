package cn.tedu.meimall.admin.content.service.impl;

import cn.tedu.meimall.admin.content.dao.persist.repository.*;
import cn.tedu.meimall.admin.content.dao.search.IArticleSearchRepository;
import cn.tedu.meimall.admin.content.pojo.entity.Article;
import cn.tedu.meimall.admin.content.pojo.entity.ArticleDetail;
import cn.tedu.meimall.admin.content.pojo.entity.CheckLog;
import cn.tedu.meimall.admin.content.pojo.param.ArticleAddNewParam;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.admin.content.pojo.vo.search.ArticleSearchVO;
import cn.tedu.meimall.admin.content.service.IArticleService;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 處理文章資料的業務實現類
 */
@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IArticleRepository articleRepository;
    @Autowired
    private IArticleSearchRepository articleSearchRepository;
    @Autowired
    private IArticleDetailRepository articleDetailRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ICheckLogRepository checkLogRepository;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IUpDownLogRepository upDownLogRepository;

    public ArticleServiceImpl() {
        log.debug("創建業務類對象：ArticleServiceImpl");
    }


    @Override
    public void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleAddNewParam articleAddNewParam) {
        log.debug("開始處理【發布文章】的業務，當事人：{}，IP地址：{}，參數：{}", currentPrincipal, remoteAddr, articleAddNewParam);

        Long categoryId = articleAddNewParam.getCategoryId();
        CategoryStandardVO category = categoryRepository.getStandardById(categoryId);
        if (category == null) {
            String message = "發布文章失敗，選擇的類别不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (category.getEnable() != 1) {
            String message = "發布文章失敗，選擇的類别已經被禁用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Article article = new Article();
        BeanUtils.copyProperties(articleAddNewParam, article);
        article.setAuthorId(currentPrincipal.getId());
        article.setAuthorName(currentPrincipal.getUsername());
        article.setCategoryName(category.getName());
        article.setIp(remoteAddr);
        article.setCheckState(0);
        article.setIsDisplay(0);
        article.setIsRecommend(0);
        int rows = articleRepository.insert(article);
        if (rows != 1) {
            String message = "發布文章失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setArticleId(article.getId());
        articleDetail.setDetail(articleAddNewParam.getDetail());
        rows = articleDetailRepository.insert(articleDetail);
        if (rows != 1) {
            String message = "發布文章失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("開始處理【根據ID删除文章】的業務，參數：{}", id);
        ArticleStandardVO queryResult = articleRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "删除文章失敗，嘗試删除的文章資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = articleRepository.deleteById(id);
        if (rows != 1) {
            String message = "删除文章失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        rows = articleDetailRepository.deleteByArticle(id);
        if (rows != 1) {
            String message = "删除文章失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        checkLogRepository.deleteByResource(RESOURCE_TYPE_ARTICLE, id);
        commentRepository.deleteByResource(RESOURCE_TYPE_ARTICLE, id);
        upDownLogRepository.deleteByResource(RESOURCE_TYPE_ARTICLE, id);
    }

    @Override
    public void passCheck(CurrentPrincipal currentPrincipal, Long id, String remarks) {
        log.debug("開始處理【審核通過文章】的業務，當事人：{}，文章ID：{}，審核備注：{}", currentPrincipal, id, remarks);
        updateCheckById(currentPrincipal, id, CHECK_STATE_PASS, remarks);
    }

    @Override
    public void rejectCheck(CurrentPrincipal currentPrincipal, Long id, String remarks) {
        log.debug("開始處理【拒絕審核文章】的業務，當事人：{}，文章ID：{}，審核備注：{}", currentPrincipal, id, remarks);
        updateCheckById(currentPrincipal, id, CHECK_STATE_REJECT, remarks);
    }

    @Override
    public void setDisplay(Long id) {
        log.debug("開始處理【顯示文章】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_ON);
    }

    @Override
    public void setHidden(Long id) {
        log.debug("開始處理【不顯示文章】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_OFF);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢文章】的業務，參數：{}", id);
        ArticleStandardVO queryResult = articleRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "查詢文章詳情失敗，文章資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢文章列表】的業務，頁碼：{}", pageNum);
        return articleRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢文章列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return articleRepository.list(pageNum, pageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum) {
        log.debug("開始處理【根據類别查詢文章列表】的業務，文章類别：{}, 頁碼：{}", categoryId, pageNum);
        return articleRepository.listByCategory(categoryId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據類别查詢文章列表】的業務，文章類别：{}, 頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        return articleRepository.listByCategory(categoryId, pageNum, pageSize);
    }

    @Override
    public void rebuildSearch() {
        log.debug("開始處理【更新搜索服務器中的文章列表】的業務，無參數");
        articleSearchRepository.deleteAll();
        Integer pageNum = 1;
        Integer pageSize = 10;
        Integer maxPage;
        PageData<ArticleSearchVO> pageData;
        do {
            pageData = articleRepository.listSearchVO(pageNum, pageSize);
            maxPage = pageData.getMaxPage();
            articleSearchRepository.saveAll(pageData.getList());
            pageNum++;
        } while (pageNum <= maxPage);
    }

    private void updateCheckById(CurrentPrincipal currentPrincipal, Long id, Integer checkState, String remarks) {
        ArticleStandardVO currentArticle = articleRepository.getStandardById(id);
        if (currentArticle == null) {
            String message = "將文章的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，文章資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentArticle.getCheckState().equals(checkState)) {
            String message = "將文章的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，此文章已經處於" + CHECK_STATE_TEXT[checkState] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Article updateArticle = new Article();
        updateArticle.setId(id);
        updateArticle.setCheckState(checkState);
        int rows = articleRepository.update(updateArticle);
        if (rows != 1) {
            String message = "將文章的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        CheckLog checkLog = new CheckLog();
        checkLog.setResourceType(RESOURCE_TYPE_ARTICLE);
        checkLog.setResourceId(id);
        checkLog.setResourceBrief(currentArticle.getTitle());
        checkLog.setCheckUserId(currentPrincipal.getId());
        checkLog.setCheckUsername(currentPrincipal.getUsername());
        checkLog.setCheckRemarks(remarks);
        checkLog.setOriginalState(currentArticle.getCheckState());
        checkLog.setNewState(checkState);
        checkLog.setGmtCheck(LocalDateTime.now());
        rows = checkLogRepository.insert(checkLog);
        if (rows != 1) {
            String message = "將文章的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updateDisplayById(Long id, Integer displayState) {
        ArticleStandardVO currentArticle = articleRepository.getStandardById(id);
        if (currentArticle == null) {
            String message = "將文章的顯示狀態修改為【" + DISPLAY_STATE_TEXT[displayState] + "】失敗，文章資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentArticle.getIsDisplay().equals(displayState)) {
            String message = "將文章的顯示狀態修改為【" + DISPLAY_STATE_TEXT[displayState] + "】失敗，此文章已經處於" + DISPLAY_STATE_TEXT[displayState] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Article updateArticle = new Article();
        updateArticle.setId(id);
        updateArticle.setIsDisplay(displayState);
        int rows = articleRepository.update(updateArticle);
        if (rows != 1) {
            String message = "將文章的顯示狀態修改為【" + DISPLAY_STATE_TEXT[displayState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

}
