package cn.tedu.meimall.front.mall.dao.persist.repository;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.entity.Order;
import cn.tedu.meimall.front.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.OrderStandardVO;

/**
 * 處理訂單資料的存儲庫接口
 */
public interface IOrderRepository {

    /**
     * 插入訂單資料
     * @param order 訂單資料
     * @return 受影響的行數
     */
    int insert(Order order);

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
