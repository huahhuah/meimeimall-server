package cn.tedu.meimall.admin.content.dao.persist.mapper;

import cn.tedu.meimall.admin.content.pojo.entity.Category;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理類別資料的Mapper接口
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {



    /**
     * 根據ID查詢類別資料詳情
     * @param id 類別ID
     * @return 匹配的類別資料詳情,如果沒有匹配的資料,則反為null
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 查詢類別資料列表
     * @return 類別資料列表
     */
    List<CategoryListItemVO> list();


}
