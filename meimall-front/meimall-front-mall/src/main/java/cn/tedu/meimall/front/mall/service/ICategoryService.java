package cn.tedu.meimall.front.mall.service;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.CategoryTreeItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理類別業務的介面
 */
@Transactional
public interface ICategoryService {

    /**
     * 獲取所有類別形成的"樹"
     * @return 所有類別形成的"樹"
     */
    List<CategoryTreeItemVO> listTree();

    /**
     * 根據父級類別查詢其子級類別列表,將使用默認的每頁記錄數
     * @param parentId  父級類別的ID
     * @param pageNum   頁碼
     * @return  類別列表
     */
    PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum);

    /**
     * 根據父級類別查詢其子級類別列表
     * @param parentId  父級類別的ID
     * @param pageNum   頁碼
     * @param pageSize  每頁記錄數
     * @return 類別列表
     */
    PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize);
}
