package cn.tedu.meimall.front.content.dao.persist.mapper;

import cn.tedu.meimall.front.content.pojo.entity.Category;
import cn.tedu.meimall.front.content.pojo.vo.CategoryListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理類別資料的Mapper介面
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查詢類別資料列表
     * @return 類別資料列表
     */
    List<CategoryListItemVO> list();
}
