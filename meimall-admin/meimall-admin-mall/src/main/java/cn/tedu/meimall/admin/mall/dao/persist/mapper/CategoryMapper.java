package cn.tedu.meimall.admin.mall.dao.persist.mapper;

import cn.tedu.meimall.admin.mall.pojo.entity.Category;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理類別資料的Mapper介面
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根據ID查詢類別資料詳情
     * @param id 類別ID
     * @return 匹配的類別資料詳情,如果沒有匹配的資料,則返回null
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 查詢類別資料列表
     * @return 類別資料列表
     */
    List<CategoryListItemVO> list();

    /**
     * 根據父級類別查詢其子級類別列表
     * @param parentId 父級類別的ID
     * @return 類別列表
     */
    List<CategoryListItemVO> listByParent(Long parentId);
}
