package cn.tedu.meimall.front.content.dao.persist.repository;

import cn.tedu.meimall.front.content.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * 處理類別資料的存儲庫介面
 */
public interface ICategoryRepository {

    /**
     * 查詢類別資料
     * @return 類別資料列表
     */
    List<CategoryListItemVO> list();
}
