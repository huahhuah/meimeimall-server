package cn.tedu.meimall.admin.content.dao.persist.repository.impl;

import cn.tedu.meimall.admin.content.dao.persist.mapper.CategoryMapper;
import cn.tedu.meimall.admin.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.meimall.admin.content.pojo.entity.Category;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryStandardVO;
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
 * 處理類別資料的存儲庫實現類
 */
@Slf4j
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryRepositoryImpl() {
        log.info("創建存儲庫對象：CategoryRepositoryImpl");
    }

    @Override
    public int insert(Category category) {
        log.debug("開始執行【插入類别】的資料訪問，參數：{}", category);
        return categoryMapper.insert(category);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID删除類别】的資料訪問，參數：{}", id);
        return categoryMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("開始執行【批量删除類别】的資料訪問，參數：{}", idList);
        return categoryMapper.deleteBatchIds(idList);
    }

    @Override
    public int update(Category category) {
        log.debug("開始執行【更新類别】的資料訪問，參數：{}", category);
        return categoryMapper.updateById(category);
    }

    @Override
    public int count() {
        log.debug("開始執行【統計類别的數量】的資料訪問，參數：無");
        return categoryMapper.selectCount(null);
    }

    @Override
    public int countByName(String name) {
        log.debug("開始執行【統計匹配名稱的類别的數量】的資料訪問，參數：{}", name);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return categoryMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByNameAndNotId(Long id, String name) {
        log.debug("開始執行【統計匹配名稱但不匹配ID的類别的數量】的資料訪問，ID：{}，類别：{}", id, name);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name).ne("id", id);
        return categoryMapper.selectCount(queryWrapper);
    }

    @Override
    public CategoryStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢類别信息】的資料訪問，參數：{}", id);
        return categoryMapper.getStandardById(id);
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("開始執行【查詢類别列表】的資料訪問，參數：無");
        return categoryMapper.list();
    }

    @Override
    public PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢類别列表】的資料訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryListItemVO> list = categoryMapper.list();
        PageInfo<CategoryListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
