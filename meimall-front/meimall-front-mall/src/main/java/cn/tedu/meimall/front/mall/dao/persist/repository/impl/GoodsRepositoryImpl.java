package cn.tedu.meimall.front.mall.dao.persist.repository.impl;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import cn.tedu.meimall.front.mall.dao.persist.mapper.GoodsMapper;
import cn.tedu.meimall.front.mall.dao.persist.repository.IGoodsRepository;
import cn.tedu.meimall.front.mall.pojo.entity.Goods;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理商品資料的存儲庫實現類
 */
@Slf4j
@Repository
public class GoodsRepositoryImpl implements IGoodsRepository {

    @Autowired
    private GoodsMapper goodsMapper;

    public GoodsRepositoryImpl(){
        log.info("創建存儲庫對象: GoodsRepositoryImpl");
    }

    @Override
    public int update(Goods goods) {
        log.debug("開始執行【更新商品】的資料訪問,參數:{}", goods);
        return goodsMapper.updateById(goods);
    }

    @Override
    public GoodsStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢商品信息】的資料訪問,參數:{}", id);
        return goodsMapper.getStandardById(id);
    }

    @Override
    public PageData<GoodsListItemVO> listByCommend(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢推薦的商品資料列表】的資料訪問,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsListItemVO> list = goodsMapper.listByRecommend();
        PageInfo<GoodsListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【跟據類別查詢商品列表】的資料訪問,商品類別:{},頁碼:{} ,每頁記錄數:{}",categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsListItemVO> list = goodsMapper.listByCategory(categoryId);
        PageInfo<GoodsListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
