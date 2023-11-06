package cn.tedu.meimall.admin.mall.service.impl;


import cn.tedu.meimall.admin.mall.dao.persist.repository.IOrderItemRepository;
import cn.tedu.meimall.admin.mall.dao.persist.repository.IOrderRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.Goods;
import cn.tedu.meimall.admin.mall.pojo.entity.GoodsDetail;
import cn.tedu.meimall.admin.mall.pojo.entity.Order;
import cn.tedu.meimall.admin.mall.pojo.entity.OrderItem;
import cn.tedu.meimall.admin.mall.pojo.param.OrderUpdateParam;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderStandardVO;
import cn.tedu.meimall.admin.mall.pojo.vo.ReceiverAddressStandardVO;
import cn.tedu.meimall.admin.mall.service.IOrderService;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理訂單資料的業務實現類
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IOrderItemRepository orderItemRepository;



    public OrderServiceImpl(){
        log.debug("創建業務類對象: OrderServiceImpl");
    }


    @Override
    public void delete( Long id) {
        log.debug("開始處理【刪除訂單】的業務, 參數:{}", id);
        OrderStandardVO queryResult = orderRepository.getStandardById(id);
        if(queryResult == null){
            String message = "刪除[訂單失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = orderRepository.deleteById(id);
        if(rows != 1 ){
            String message = "刪除訂單失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_DELETE, message);
        }
    }

    @Override
    public void updateInfoById(Long id, OrderUpdateParam orderUpdateParam) {
        log.debug("開始處理【修改商品詳情】的業務,ID:{},新資料:{}", id, orderUpdateParam);
        // 調用Mapper對象的getStandardById()持行查詢
        OrderStandardVO queryResult = orderRepository.getStandardById(id);
        //判斷查詢結果是否為null
        if(queryResult == null){
            //是: 拋出異常
            String message = "修改訂單詳情失敗,嘗試修改的訂單資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        //創建order對象
        Order order = new Order();
        //複製屬性,設置ID
        BeanUtils.copyProperties(orderUpdateParam, order);
        order.setId(id);
        //調用Mapper對象的update()方法執行修改
        int rows = orderRepository.update(order);
        if(rows != 1){
            String message = "修該訂單失敗,服務器忙,請稍稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        log.debug("修改商品成功!");
    }

    @Override
    public OrderStandardVO getStandardById(CurrentPrincipal currentPrincipal, Long orderId) {
        log.debug("開始處理【根據ID查詢訂單】的業務,當事人:{},參數:{}", currentPrincipal,orderId);
        OrderStandardVO order = orderRepository.getStandardByIdAndUser(orderId, currentPrincipal.getId());
        if(order == null){
            String message = "查詢訂單失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return order;
    }



    @Override
    public PageData<OrderListItemVO> listByUser(CurrentPrincipal currentPrincipal, Integer pageNum) {
        log.debug("開始處理【查詢訂單列表】的業務,用戶當事人:{},頁碼:{}",currentPrincipal, pageNum);
        return orderRepository.listByUser(currentPrincipal.getId(), pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<OrderListItemVO> listByUser(CurrentPrincipal currentPrincipal, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢訂單列表】的業務,當事人:{},頁碼:{},每頁記錄數:{}",currentPrincipal, pageNum, pageSize);
        return orderRepository.listByUser(currentPrincipal.getId(), pageNum , pageSize);
    }
}
