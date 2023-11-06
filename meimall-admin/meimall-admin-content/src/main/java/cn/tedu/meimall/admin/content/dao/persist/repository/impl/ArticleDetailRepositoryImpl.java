package cn.tedu.meimall.admin.content.dao.persist.repository.impl;

import cn.tedu.meimall.admin.content.dao.persist.mapper.ArticleDetailMapper;
import cn.tedu.meimall.admin.content.dao.persist.repository.IArticleDetailRepository;
import cn.tedu.meimall.admin.content.pojo.entity.ArticleDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 處理文章詳情資料的存儲庫實現類
 */
@Slf4j
@Repository
public class ArticleDetailRepositoryImpl implements IArticleDetailRepository {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    public ArticleDetailRepositoryImpl() {
        log.info("創建存儲庫對象：ArticleDetailRepositoryImpl");
    }

    @Override
    public int insert(ArticleDetail articleDetail) {
        log.debug("開始執行【插入文章詳情】的資料訪問，參數：{}", articleDetail);
        return articleDetailMapper.insert(articleDetail);
    }

    @Override
    public int deleteByArticle(Long articleId) {
        log.debug("開始執行【根據文章ID删除文章詳情資料】的資料訪問，參數：{}", articleId);
        QueryWrapper<ArticleDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        return articleDetailMapper.delete(queryWrapper);
    }

    @Override
    public int updateByArticle(ArticleDetail articleDetail) {
        log.debug("開始執行【更新文章詳情】的資料訪問，參數：{}", articleDetail);
        QueryWrapper<ArticleDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleDetail.getArticleId());
        return articleDetailMapper.update(articleDetail, queryWrapper);
    }
}
