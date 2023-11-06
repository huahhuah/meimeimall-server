package cn.tedu.meimall.front.mall.service.impl;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.dao.persist.repository.IGoodsRepository;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.front.mall.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理商品數據的業務實現類
 */
@Slf4j
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IGoodsRepository goodsRepository;

    @Override
    public GoodsStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢商品】的業務,參數:{}", id);
        GoodsStandardVO queryResult = goodsRepository.getStandardById(id);
        log.trace("查詢結果:{}", queryResult);
        if(queryResult == null){
            String message = "查詢商品失敗,商品數據不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<GoodsListItemVO> listByRecommend(Integer pageNum) {
        log.debug("開始處理【查詢推薦的商品列表】的業務,頁碼:{}", pageNum);
        return goodsRepository.listByCommend(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<GoodsListItemVO> listByRecommend(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢推薦的商品列表】的業務,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        return goodsRepository.listByCommend(pageNum, pageSize);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum) {
        log.debug("開始處理【跟據類別查詢商品列表】的業務,商品類別:{},頁碼:{}", categoryId, pageNum);
        return goodsRepository.listByCategory(categoryId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【跟據類別查詢商品列表】的業務,商品類別:{},頁碼:{},每頁記錄數:{}", categoryId, pageNum, pageSize);
        return goodsRepository.listByCategory(categoryId, pageNum, pageSize);
    }
}
