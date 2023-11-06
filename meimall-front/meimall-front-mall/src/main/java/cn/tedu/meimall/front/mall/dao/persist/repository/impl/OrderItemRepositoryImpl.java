package cn.tedu.meimall.front.mall.dao.persist.repository.impl;

import cn.tedu.meimall.front.mall.dao.persist.mapper.OrderItemMapper;
import cn.tedu.meimall.front.mall.dao.persist.repository.IOrderItemRepository;
import cn.tedu.meimall.front.mall.pojo.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 處理訂單資料的存儲庫實現類
 */
@Slf4j
@Repository
public class OrderItemRepositoryImpl implements IOrderItemRepository {

    @Autowired
    private OrderItemMapper orderItemMapper;

    public OrderItemRepositoryImpl(){
        log.info("創建存儲庫對象: OrderItemRepositoryImpl");
    }

    @Override
    public int insert(OrderItem orderItem) {
        log.debug("開始執行【插入訂單】的資料訪問,參數:{}", orderItem);
        return orderItemMapper.insert(orderItem);
    }
}
