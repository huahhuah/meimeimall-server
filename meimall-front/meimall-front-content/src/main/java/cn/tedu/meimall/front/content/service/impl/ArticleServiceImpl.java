package cn.tedu.meimall.front.content.service.impl;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.content.dao.persist.repository.IArticleRepository;
import cn.tedu.meimall.front.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.front.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.front.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public ArticleServiceImpl(){
        log.debug("創建業務類對象: ArticleServiceImpl");
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢文章】的業務,參數:{}", id);
        ArticleStandardVO queryResult = articleRepository.getStandardById(id);
        if(queryResult == null){
            String message = "查詢文章失敗,文章資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<ArticleListItemVO> listByRecommend(Integer pageNum) {
        log.debug("開始處理【查詢推薦的文章列表】的業務,頁碼:{}", pageNum);
        return articleRepository.listByRecommend(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢推薦的文章列表】的業務,頁碼:{},每頁記錄數:{}",pageNum, pageSize);
        return articleRepository.listByRecommend(pageNum, pageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum) {
        log.debug("開始處理【根據類別查詢文章列表】的業務,文章列別:{},頁碼:{}",categoryId, pageNum );
        return articleRepository.listByCategory(categoryId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據類別查詢文章列表】的業務，文章類別:{}, 頁碼:{}, 每頁記錄數:{}", categoryId, pageNum, pageSize);
        return articleRepository.listByCategory(categoryId, pageNum, pageSize);
    }


}

