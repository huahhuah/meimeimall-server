package cn.tedu.meimall.front.mall.dao.persist.repository;

import cn.tedu.meimall.front.mall.pojo.entity.OrderItem;

/**
 * 處理訂單項資料的存儲庫接口
 */
public interface IOrderItemRepository {

    /**
     * 插入訂單項資料
     * @param orderItem　訂單項資料
     * @return 受影響的行數
     */
    int insert(OrderItem orderItem);
}
