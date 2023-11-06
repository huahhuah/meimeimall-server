package cn.tedu.meimall.front.mall.dao.persist.mapper;

import cn.tedu.meimall.front.mall.pojo.entity.Category;
import cn.tedu.meimall.front.mall.pojo.vo.CategoryListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理類別資料的Mapper的介面
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查詢類別資料列表
     * @return  類別資料列表
     */
    List<CategoryListItemVO> list();

    /**
     * 根據父級類別查詢其子級類別列表
     * @param parentId 父級類別的ID
     * @return 類別列表
     */
    List<CategoryListItemVO> listByParent(Long parentId);
}
