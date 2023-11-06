package cn.tedu.meimall.admin.mall.dao.persist.repository.impl;

import cn.tedu.meimall.admin.mall.dao.persist.mapper.OrderMapper;
import cn.tedu.meimall.admin.mall.dao.persist.repository.IOrderRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.Goods;
import cn.tedu.meimall.admin.mall.pojo.entity.Order;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 處理訂單資料的存儲庫實現類
 */
@Slf4j
@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    public OrderRepositoryImpl(){
        log.info("創建存儲庫對象: OrderRepositoryImpl");
    }


    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除訂單】的資料訪問,參數:{}", id);
        return orderMapper.deleteById(id);
    }

    @Override
    public int update(Order order) {
        log.debug("開始執行【更新訂單】的資料訪問,參數:{}",order);
        return orderMapper.updateById(order);
    }

    @Override
    public int countByNameAndNotId(Long id, String buyerUsername) {
        log.debug("開始執行【統計匹配名稱但不匹配ID的訂單的數量】的資料訪問,ID:{},參數:{}",id, buyerUsername);
        QueryWrapper<Order> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("buyer_username", buyerUsername).ne("id", id);
        return orderMapper.selectCount(queryWrapper);
    }

    @Override
    public OrderStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據訂單ID查詢用戶的訂單】的資料訪問,參數:{}", id);
        return orderMapper.getStandardById(id);
    }


    @Override
    public OrderStandardVO getStandardByIdAndUser(Long orderId, Long userId) {
        log.debug("開始執行【根據訂單ID查詢用戶的訂單】的資料訪問,訂單ID:{},用戶ID:{}", orderId, userId);
        return orderMapper.getStandardByIdAndUser(orderId, userId);
    }

    @Override
    public PageData<OrderListItemVO> listByUser(Long userId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據用戶查詢訂單列表】的資料訪問，用戶：{}, 頁碼:{}, 每頁記錄數:{}", userId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<OrderListItemVO> list = orderMapper.listByUser(userId);
        PageInfo<OrderListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
