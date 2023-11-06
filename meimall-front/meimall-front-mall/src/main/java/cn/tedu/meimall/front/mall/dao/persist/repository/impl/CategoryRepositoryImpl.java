package cn.tedu.meimall.front.mall.dao.persist.repository.impl;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import cn.tedu.meimall.front.mall.dao.persist.mapper.CategoryMapper;
import cn.tedu.meimall.front.mall.dao.persist.repository.ICategoryRepository;
import cn.tedu.meimall.front.mall.pojo.vo.CategoryListItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理類別資料的存儲庫實現類
 */
@Slf4j
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryRepositoryImpl(){
        log.debug("創建存儲庫對象: CategoryRepositoryImpl");
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("開始執行【查詢類別列表】的資料訪問，參數：無");
        return categoryMapper.list();
    }

    @Override
    public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據父級類別查詢子級類別列表】的資料訪問，父級類別:{}，頁碼:{},每頁記錄數:{}", parentId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryListItemVO> list = categoryMapper.listByParent(parentId);
        PageInfo<CategoryListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
