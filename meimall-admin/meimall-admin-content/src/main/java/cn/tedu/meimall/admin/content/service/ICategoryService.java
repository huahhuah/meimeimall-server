package cn.tedu.meimall.admin.content.service;

import cn.tedu.meimall.admin.content.pojo.param.CategoryAddNewParam;
import cn.tedu.meimall.admin.content.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.common.consts.data.ContentConsts;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理類別業務的接口
 */
@Transactional
public interface ICategoryService extends ContentConsts {

    /**
     * 添加類別
     * @param categoryAddNewParam 新的類別資料
     */
    void addNew(CategoryAddNewParam categoryAddNewParam);

    /**
     * 根據ID刪除類別
     * @param id 嘗試刪除的類別資料的ID
     */
    void  delete(Long id);

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
    void seDisplay(Long id);

    /**
     * 隱藏類別
     * @param id 嘗試隱藏的類別的ID
     */
    void setHidden(Long id);

    /**
     * 修改類別資料
     * @param id                        被修改的類別資料的ID
     * @param categoryUpdateInfoParam   類別的新資料
     */
    void updateInfoById(Long id, CategoryUpdateInfoParam categoryUpdateInfoParam);

    /**
     * 根據ID查詢類別詳情
     * @param id 類別ID
     * @return 匹配的類別詳情,如果沒有匹配的資料,則返回null
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 查詢類別列表,將使用默認的每頁記錄數
     * @param pageNum  頁碼
     * @return         類別列表
     */
    PageData<CategoryListItemVO> list(Integer pageNum);

    /**
     * 查詢類別列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return         類別列表
     */
    PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize);
}
