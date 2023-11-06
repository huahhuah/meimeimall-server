package cn.tedu.meimall.admin.content.dao.persist.repository.impl;

import cn.tedu.meimall.admin.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.meimall.admin.content.dao.persist.repository.IArticleRepository;
import cn.tedu.meimall.admin.content.pojo.entity.Article;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.admin.content.pojo.vo.search.ArticleSearchVO;
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
 * 處理文章資料的存儲庫實現類
 */
@Slf4j
@Repository
public class ArticleRepositoryImpl implements IArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleRepositoryImpl() {
        log.info("創建存儲庫對象：ArticleRepositoryImpl");
    }

    @Override
    public int insert(Article article) {
        log.debug("開始執行【插入文章】的資料訪問，參數：{}", article);
        return articleMapper.insert(article);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID删除文章】的資料訪問，參數：{}", id);
        return articleMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("開始執行【根據若干個ID批量删除文章資料】的資料訪問，參數：{}", idList);
        return articleMapper.deleteBatchIds(idList);
    }

    @Override
    public int update(Article article) {
        log.debug("開始執行【根據ID查詢文章資料詳情】的資料訪問，參數：{}", article);
        return articleMapper.updateById(article);
    }

    @Override
    public int countByCategory(Long categoryId) {
        log.debug("開始執行【根據類别統計文章數量】的資料訪問，參數：{}", categoryId);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return articleMapper.selectCount(queryWrapper);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢文章信息】的資料訪問，參數：{}", id);
        return articleMapper.getStandardById(id);
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的資料訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.list();
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<ArticleSearchVO> listSearchVO(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的資料訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSearchVO> list = articleMapper.listSearchVO();
        PageInfo<ArticleSearchVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的資料訪問，文章類别：{}，頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByCategory(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
