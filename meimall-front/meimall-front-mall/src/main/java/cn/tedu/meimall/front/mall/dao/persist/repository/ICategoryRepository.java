package cn.tedu.meimall.front.mall.dao.persist.repository;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * 處理類別資料的存儲庫接口
 */
public interface ICategoryRepository {

    /**
     * 查詢類別資料列表
     * @return 類別資料列表
     */
    List<CategoryListItemVO> list();

    /**
     * 根據父級類別查詢其子級類別列表
     * @param parentId  父級類別的ID
     * @param pageNum   頁碼
     * @param pageSize  每頁記錄數
     * @return 類別列表
     */
    PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum ,Integer pageSize);
}
