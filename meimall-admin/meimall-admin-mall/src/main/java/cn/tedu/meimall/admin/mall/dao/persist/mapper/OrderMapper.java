package cn.tedu.meimall.admin.mall.dao.persist.mapper;


import cn.tedu.meimall.admin.mall.pojo.entity.Order;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理訂單資料的Mapper介面
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 根據訂單ID查詢用戶的訂單
     * @param id 訂單ID
     * @return 訂單詳情
     */
    OrderStandardVO getStandardById(Long id);

    /**
     * 根據訂單ID查詢用戶的訂單
     * @param orderId 訂單ID
     * @param userId  用戶ID
     * @return 訂單詳情
     */
    OrderStandardVO getStandardByIdAndUser(@Param("orderId")Long orderId,@Param("userId") Long userId);

    /**
     * 根據用戶查詢訂單列表
     * @param userId 用戶ID
     * @return 訂單列表
     */
    List<OrderListItemVO> listByUser(Long userId);
}
