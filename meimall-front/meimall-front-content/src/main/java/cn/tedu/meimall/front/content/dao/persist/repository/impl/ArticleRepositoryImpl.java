package cn.tedu.meimall.front.content.dao.persist.repository.impl;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import cn.tedu.meimall.front.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.meimall.front.content.dao.persist.repository.IArticleRepository;
import cn.tedu.meimall.front.content.pojo.entity.Article;
import cn.tedu.meimall.front.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.front.content.pojo.vo.ArticleStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章資料的存儲庫實現類
 */
@Slf4j
@Repository
public class ArticleRepositoryImpl implements IArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleRepositoryImpl() {
        log.info("創建存儲庫對象: ArticleRepositoryImpl");
    }

    @Override
    public int setCommentCount(Long articleId, Integer commentCount) {
        log.debug("開始執行【設置文章的評論數】的資料訪問,參數:{}", articleId);
        Article article = new Article();
        article.setId(articleId);
        article.setCommentCount(commentCount);
        return articleMapper.updateById(article);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢文章信息】的資料訪問,參數:{}", id);
        return articleMapper.getStandardById(id);
    }

    @Override
    public PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據推薦的文章列表】的資料訪問,頁碼:{}, 每頁記錄數:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByRecommend();
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據類別的文章列表】的資料訪問,頁碼:{}, 每頁記錄數:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByCategory(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
