package cn.tedu.meimall.admin.mall.dao.persist.repository;


import cn.tedu.meimall.admin.mall.pojo.entity.Order;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.apache.ibatis.annotations.Param;

/**
 * 處理訂單資料的存儲庫介面
 */
public interface IOrderRepository {


    /**
     * 根據ID刪除訂單資料
     * @param id 訂單ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據ID修改商品資料
     * @param order 封裝了訂單ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Order order);

    /**
     * 統計當前表中非此類別ID的匹配名稱的類別資料的數量
     * @param id   當前類別ID
     * @param buyerUsername 類別名稱
     * @return 當前表中非此類別ID的匹配名稱的類別資料的數量
     */
    int countByNameAndNotId(@Param("id")Long id, @Param("buyer_username") String buyerUsername);

    OrderStandardVO getStandardById(Long id);

    /**
     * 根據訂單ID查詢用戶的訂單
     * @param orderId 訂單ID
     * @param userId  用戶ID
     * @return 訂單列表
     */
    OrderStandardVO getStandardByIdAndUser(Long orderId, Long userId);

    /**
     * 根據用戶查詢訂單
     * @param userId   用戶ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 訂單列表
     */
    PageData<OrderListItemVO> listByUser(Long userId, Integer pageNum, Integer pageSize);
}
