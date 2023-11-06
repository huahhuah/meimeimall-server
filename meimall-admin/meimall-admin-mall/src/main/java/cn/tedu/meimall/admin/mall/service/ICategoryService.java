package cn.tedu.meimall.admin.mall.service;

import cn.tedu.meimall.admin.mall.pojo.param.CategoryAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryTreeItemVO;
import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理類別業務的介面
 */
@Transactional
public interface ICategoryService extends MallConsts {

    /**
     * 添加類別
     * @param categoryAddNewParam 新的資料類別
     */
    void addNew(CategoryAddNewParam categoryAddNewParam);

    /**
     * 根據ID刪除類別
     * @param id 嘗試刪除的類別資料的ID
     */
    void delete(Long id);

    /**
     * 啟用類別
     * @param id 嘗試啟用的類別的ID
     */
    void setEnable(Long id);

    /**
     * 禁用類別
     * @param id 嘗試禁用的類別的ID
     */
    void setDisable(Long id);

    /**
     * 顯示類別
     * @param id 嘗試顯示的類別的ID
     */
    void setDisplay(Long id);

    /**
     * 隱藏類別
     * @param id 嘗試隱藏的類別的ID
     */
    void setHidden(Long id);

    /**
     * 修改類別資料
     * @param id              被修改的類別資料的ID
     * @param updateInfoParam 類別的新資料
     */
    void updateInfoById(Long id, CategoryUpdateInfoParam updateInfoParam);

    /**
     * 根據ID查詢類别
     *
     * @param id 類别id
     * @return 匹配的類别資料詳情，如果没有匹配的資料，則返回null
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 获取所有類别形成的"樹"
     *
     * @return 所有類别形成的"樹"
     */
    List<CategoryTreeItemVO> listTree();

    /**
     * 根據父級查詢其子級列表，將使用默認的每頁記錄數
     *
     * @param parentId 父級類别的ID
     * @param pageNum  頁碼
     * @return 類别列表
     */
    PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum);

    /**
     * 根據父級查詢其子級列表
     *
     * @param parentId 父級類别的ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 類别列表
     */
    PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize);

}
