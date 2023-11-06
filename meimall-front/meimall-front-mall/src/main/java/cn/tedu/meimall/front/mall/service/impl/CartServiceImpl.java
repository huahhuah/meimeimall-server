package cn.tedu.meimall.front.mall.service.impl;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.front.mall.converter.GoodsToCartConverter;
import cn.tedu.meimall.front.mall.dao.cache.ICartCacheRepository;
import cn.tedu.meimall.front.mall.dao.persist.repository.IGoodsRepository;
import cn.tedu.meimall.front.mall.pojo.po.CartPO;
import cn.tedu.meimall.front.mall.pojo.vo.CartListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.front.mall.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 處理購物車的業務實現類
 */
@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartCacheRepository cartCacheRepository;
    @Autowired
    private IGoodsRepository goodsRepository;

    public CartServiceImpl(){
        log.debug("創建業務類對象: CartServiceImpl");
    }

    @Override
    public void add(CurrentPrincipal currentPrincipal, Long goodsId, Integer goodsNum) {
        log.debug("開始處理【添加商品到購物車】的業務，當事人：{}，商品：{}，數量：{}", currentPrincipal, goodsId, goodsNum);
        GoodsStandardVO goods = goodsRepository.getStandardById(goodsId);
        if (goods == null) {
            String message = "添加購物車失敗，商品資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        if (goods.getIsPutOn() != 1) {
            String message = "添加購物車失敗，商品已下架！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        CartPO cartPO = new CartPO(goodsId, goodsNum);
        CartPO cartCache = cartCacheRepository.getByUserAndGoods(currentPrincipal.getId(), goodsId);
        if (cartCache != null) {
            cartPO.setGoodsNum(cartCache.getGoodsNum() + goodsNum);
        }
        cartCacheRepository.put(currentPrincipal.getId(), cartPO);
    }

    @Override
    public void delete(CurrentPrincipal currentPrincipal, Long goodsId) {
        log.debug("開始處理【删除購物車中的商品】的業務，當事人：{}，商品：{}", currentPrincipal, goodsId);
        cartCacheRepository.deleteByUserAndGoods(currentPrincipal.getId(), goodsId);
    }

    @Override
    public Integer increaseNum(CurrentPrincipal currentPrincipal, Long goodsId) {
        log.debug("開始處理【將購物車中商品的數量增加1】的業務，當事人：{}，商品：{}", currentPrincipal, goodsId);
        return increaseNum(currentPrincipal.getId(), goodsId, 1);
    }

    @Override
    public Integer increaseNum(CurrentPrincipal currentPrincipal, Long goodsId, Integer num) {
        log.debug("開始處理【增加購物車中商品的數量】的業務，當事人：{}，商品：{}，數量：{}", currentPrincipal, goodsId, num);
        return increaseNum(currentPrincipal.getId(), goodsId, num);
    }

    @Override
    public Integer reduceNum(CurrentPrincipal currentPrincipal, Long goodsId) {
        log.debug("開始處理【将購物車中商品的數量減少1】的業務，當事人：{}，商品：{}", currentPrincipal, goodsId);
        return reduceNum(currentPrincipal.getId(), goodsId, 1);
    }

    @Override
    public Integer reduceNum(CurrentPrincipal currentPrincipal, Long goodsId, Integer num) {
        log.debug("開始處理【減少購物車中商品的數量】的業務，當事人：{}，商品：{}，數量：{}", currentPrincipal, goodsId, num);
        return reduceNum(currentPrincipal.getId(), goodsId, num);
    }

    @Override
    public List<CartListItemVO> list(CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【查詢購物車列表】的業務，當事人：{}", currentPrincipal);
        List<CartListItemVO> cartList = new ArrayList<>();
        List<CartPO> cartPOList = cartCacheRepository.listByUser(currentPrincipal.getId());
        for (CartPO cartPO : cartPOList) {
            GoodsStandardVO goods = goodsRepository.getStandardById(cartPO.getGoodsId());
            if (goods != null) {
                CartListItemVO cartListItemVO = GoodsToCartConverter.convertToCart(cartPO, goods);
                cartList.add(cartListItemVO);
            }
        }
        return cartList;
    }

    private Integer increaseNum(Long userId, Long goodsId, Integer num) {
        CartPO cartCache = cartCacheRepository.getByUserAndGoods(userId, goodsId);
        Integer newNum = cartCache.getGoodsNum() + num;
        CartPO cartPO = new CartPO(goodsId, newNum);
        cartCacheRepository.put(userId, cartPO);
        return newNum;
    }

    private Integer reduceNum(Long userId, Long goodsId, Integer num) {
        CartPO cartCache = cartCacheRepository.getByUserAndGoods(userId, goodsId);
        if (cartCache == null) {
            return 0;
        }

        Integer newNum = cartCache.getGoodsNum() - num;
        if (newNum <= 0) {
            cartCacheRepository.deleteByUserAndGoods(userId, goodsId);
            return 0;

        }

        CartPO cartPO = new CartPO(goodsId, newNum);
        cartCacheRepository.put(userId, cartPO);
        return newNum;
    }

}
