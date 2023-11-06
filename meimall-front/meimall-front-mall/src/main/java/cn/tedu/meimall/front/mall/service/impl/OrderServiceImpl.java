package cn.tedu.meimall.front.mall.service.impl;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.dao.cache.ICartCacheRepository;
import cn.tedu.meimall.front.mall.dao.persist.repository.IGoodsRepository;
import cn.tedu.meimall.front.mall.dao.persist.repository.IOrderItemRepository;
import cn.tedu.meimall.front.mall.dao.persist.repository.IOrderRepository;
import cn.tedu.meimall.front.mall.dao.persist.repository.IReceiverAddressRepository;
import cn.tedu.meimall.front.mall.pojo.entity.Order;
import cn.tedu.meimall.front.mall.pojo.entity.OrderItem;
import cn.tedu.meimall.front.mall.pojo.param.OrderAddNewParam;
import cn.tedu.meimall.front.mall.pojo.po.CartPO;
import cn.tedu.meimall.front.mall.pojo.vo.*;
import cn.tedu.meimall.front.mall.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 處理訂單數據的業務實現類
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
    @Autowired
    private IReceiverAddressRepository receiverAddressRepository;
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ICartCacheRepository cartCacheRepository;

    public OrderServiceImpl(){
        log.debug("創建業務類對象: OrderServiceImpl");
    }

    @Override
    public Long create(CurrentPrincipal currentPrincipal, OrderAddNewParam orderAddNewParam) {
        log.debug("開始處理【創建訂單】的業務，當事人：{}，參數：{}", currentPrincipal, orderAddNewParam);
        Long receiverAddressId = orderAddNewParam.getReceiverAddressId();
        ReceiverAddressStandardVO receiverAddress = receiverAddressRepository.getStandardById(receiverAddressId);
        if (receiverAddress == null) {
            String message = "創建訂單失敗，收貨地址數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        String fullAddress =  receiverAddress.getCity() + receiverAddress.getArea() + receiverAddress.getDetail();

        Long[] goodsIdList = orderAddNewParam.getGoodsIdList();

        BigDecimal totalPrice = BigDecimal.ZERO;
        Integer goodsTotalNum = 0;

        List<GoodsStandardVO> goodsList = new ArrayList<>();
        List<Integer> goodsNumList = new ArrayList<>();
        for (int i = 0; i < goodsIdList.length; i++) {
            GoodsStandardVO goods = goodsRepository.getStandardById(goodsIdList[i]);
            if (goods == null) {
                String message = "創建訂單失敗，部分商品數據不存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            }
            if (goods.getIsPutOn() != 1) {
                String message = "創建訂單失敗，商品【" + goods.getTitle() + "】已下架！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
            goodsList.add(goods);

            CartPO cartPO = cartCacheRepository.getByUserAndGoods(currentPrincipal.getId(), goodsIdList[i]);
            Integer goodsNum = cartPO.getGoodsNum();
            goodsNumList.add(goodsNum);

            goodsTotalNum += goodsNum;

            BigDecimal multiply = goods.getSalePrice().multiply(new BigDecimal(goodsNum));
            totalPrice = totalPrice.add(multiply);
        }

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setBuyerId(currentPrincipal.getId());
        order.setBuyerUsername(currentPrincipal.getUsername());
        order.setReceiverName(receiverAddress.getReceiverName());
        order.setReceiverPhone(receiverAddress.getReceiverPhone());
        order.setReceiverAddress(fullAddress);
        order.setGoodsNum(goodsTotalNum);
        order.setTotalPrice(totalPrice);
        order.setOrderState(ORDER_STATE_UNPAID);
        int rows = orderRepository.insert(order);
        if (rows != 1) {
            String message = "創建訂單失敗，服務器忙，請稍後再試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        for (int i = 0; i < goodsList.size(); i++) {
            GoodsStandardVO goods = goodsList.get(i);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId(goods.getId());
            orderItem.setGoodsCoverUrl(goods.getCoverUrl());
            orderItem.setGoodsTitle(goods.getTitle());
            orderItem.setGoodsNum(goodsNumList.get(i));
            orderItem.setSaleUnitPrice(goods.getSalePrice());
            rows = orderItemRepository.insert(orderItem);
            if (rows != 1) {
                String message = "創建訂單失敗，服務器忙，請稍後再試！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_INSERT, message);
            }
        }

        for (int i = 0; i < goodsIdList.length; i++) {
            cartCacheRepository.deleteByUserAndGoods(currentPrincipal.getId(), goodsIdList[i]);
        }

        return order.getId();
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
