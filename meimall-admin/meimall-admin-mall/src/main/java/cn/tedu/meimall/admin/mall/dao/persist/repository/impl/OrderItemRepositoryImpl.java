package cn.tedu.meimall.admin.mall.dao.persist.repository.impl;


import cn.tedu.meimall.admin.mall.dao.persist.mapper.OrderItemMapper;
import cn.tedu.meimall.admin.mall.dao.persist.repository.IOrderItemRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.GoodsDetail;
import cn.tedu.meimall.admin.mall.pojo.entity.OrderItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public int deleteById(Long orderId) {
        log.debug("開始執行【根據商品ID刪除商品詳情資料】的資料訪問,參數:{}", orderId);
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return orderItemMapper.delete(queryWrapper);
    }

    @Override
    public int updateByOrder(OrderItem orderItem) {
        log.debug("開始執行【更新訂單詳情】的資料訪問,參數:{}",orderItem);
        QueryWrapper<OrderItem> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("order_id", orderItem.getOrderId());
        return orderItemMapper.update(orderItem, queryWrapper);
    }
}
