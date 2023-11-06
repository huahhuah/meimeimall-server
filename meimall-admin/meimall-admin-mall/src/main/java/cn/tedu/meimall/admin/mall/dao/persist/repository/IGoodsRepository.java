package cn.tedu.meimall.admin.mall.dao.persist.repository;

import cn.tedu.meimall.admin.mall.pojo.entity.Goods;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsSearchVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * 處理商品資料的存儲庫介面
 */
public interface IGoodsRepository {

    /**
     * 插入商品資料
     * @param goods 商品資料
     * @return 受影響的行數
     */
    int insert(Goods goods);

    /**
     * 根據ID刪除商品資料
     * @param id 商品ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據若干個ID批量刪除商品資料
     * @param idList 若干個商品ID的數組
     * @return 受影響的行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據ID修改商品資料
     * @param goods 封裝了商品ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Goods goods);

    /**
     * 統計商品表中的資料的數量
     * @return 商品表中的資料的數量
     */
    int count();

    /**
     * 跟據類別統計商品數量
     * @param categoryId 類別ID
     * @return 歸屬此類別下的商品數量
     */
    int countByCategory(Long categoryId);

    /**
     * 統計當前表中非此類別ID的匹配名稱的類別資料的數量
     * @param id   當前類別ID
     * @param name 類別名稱
     * @return 當前表中非此類別ID的匹配名稱的類別資料的數量
     */
    int countByNameAndNotId(@Param("id")Long id, @Param("name") String name);

    /**
     * 查詢商品資料列表
     * @param id 商品ID
     * @return 匹配的商品資料詳情,如果沒有匹配的資料,則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢商品資料列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品資料列表
     */
    PageData<GoodsListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 查詢用於搜索的商品資料列表,此搜索結果將用於寫入ES中
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品資料列表
     */
    PageData<GoodsSearchVO> listSearch(Integer pageNum, Integer pageSize);

    /**
     * 根據類别查詢商品列表
     *
     * @param categoryId 商品類别的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);

}
