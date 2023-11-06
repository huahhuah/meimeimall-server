package cn.tedu.meimall.admin.mall.dao.persist.repository;


import cn.tedu.meimall.admin.mall.pojo.entity.Goods;
import cn.tedu.meimall.admin.mall.pojo.entity.GoodsDetail;
import cn.tedu.meimall.admin.mall.pojo.entity.OrderItem;

/**
 * 處理訂單項資料的存儲庫介面
 */
public interface IOrderItemRepository {

    /**
     * 插入訂單項資料
     * @param orderItem　訂單項資料
     * @return 受影響的行數
     */
    int insert(OrderItem orderItem);

    /**
     * 根據ID刪除訂單資料
     * @param orderId 訂單ID
     * @return 受影響的行數
     */
    int deleteById(Long orderId);

    /**
     * 根據ID修改訂單詳情資料
     * @param orderItem 封裝了訂單ID和新訂單詳情資料的對象
     * @return 受影響的行數
     */
    int updateByOrder(OrderItem orderItem);

}
