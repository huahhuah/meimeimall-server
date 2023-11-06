package cn.tedu.meimall.admin.content.dao.persist.repository;

import cn.tedu.meimall.admin.content.pojo.entity.Category;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/***
 * 處理類別資料的存儲庫接口
 */
public interface ICategoryRepository {

    /**
     * 插入類別資料
     * @param category 類別資料
     * @return  受影響的行數
     */
    int insert(Category category);

    /**
     * 根據ID刪除類別資料
     * @param id 類別ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據若干個ID批量刪除類別資料
     * @param idList 若干個類別ID的數組
     * @return 受影響的行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據ID修改類別資料
     * @param category 封裝了類別ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Category category);

    /**
     * 統計類別表中的資料的數量
     * @return 類別表中的資料的數量
     */
    int count();

    /**
     * 根據類别名稱統計當前表中類别資料的數量
     *
     * @param name 類别名稱
     * @return 當前表中匹配名稱的類别資料的數量
     */
    int countByName(String name);

    /**
     * 統計當前非此類別ID的匹配名稱的類別資料的數量
     * @param id   當前類別ID
     * @param name 類別名稱
     * @return 當前表中非此類別ID的匹配名稱的類別資料的數量
     */
    int countByNameAndNotId(@Param("id") Long id, @Param("name") String name);

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
     * 查詢類別資料列表
     * @param pageNum   頁碼
     * @param pageSize  每頁記錄數
     * @return 類別資料列表
     */
    PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize);
}
