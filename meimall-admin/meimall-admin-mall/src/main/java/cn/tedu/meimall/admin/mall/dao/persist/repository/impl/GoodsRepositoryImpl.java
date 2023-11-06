package cn.tedu.meimall.admin.mall.dao.persist.repository.impl;

import cn.tedu.meimall.admin.mall.dao.persist.mapper.GoodsMapper;
import cn.tedu.meimall.admin.mall.dao.persist.repository.IGoodsRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.Category;
import cn.tedu.meimall.admin.mall.pojo.entity.Goods;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsSearchVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 處理商品資料存儲庫實現類
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
    public int insert(Goods goods) {
        log.debug("開始執行【插入商品】的資料訪問,參數:{}", goods);
        return goodsMapper.insert(goods);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除商品】的資料訪問,參數:{}", id);
        return goodsMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("開始執行【批量刪除商品】的資料訪問,參數:{}", idList);
        return goodsMapper.deleteBatchIds(idList);
    }

    @Override
    public int update(Goods goods) {
        log.debug("開始執行【更新商品】的資料訪問,參數:{}",goods);
        return goodsMapper.updateById(goods);
    }

    @Override
    public int count() {
        log.debug("開始執行【統計商品的數量】的資料訪問，參數:無");
        return goodsMapper.selectCount(null);
    }

    @Override
    public int countByCategory(Long categoryId) {
        log.debug("開始執行【跟據類別統計商品】的數量訪問,參數:{}", categoryId);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return goodsMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByNameAndNotId(Long id, String categoryName) {
        log.debug("開始執行【統計匹配名稱但不匹配ID的類別的數量】的資料訪問,ID:{},參數:{}",id, categoryName);
        QueryWrapper<Goods> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName).ne("id", id);
        return goodsMapper.selectCount(queryWrapper);
    }

    @Override
    public GoodsStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據類別統計商品數量】的資料訪問,參數:{}",id);
        return goodsMapper.getStandardById(id);
    }

    @Override
    public PageData<GoodsListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢商品列表】資料訪問,頁碼:{},每頁記錄數:{}",pageNum,pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsListItemVO> list = goodsMapper.list();
        PageInfo<GoodsListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<GoodsSearchVO> listSearch(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢用於搜索的商品資料列表】的資料訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsSearchVO> list = goodsMapper.listSearch();
        PageInfo<GoodsSearchVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據類別查詢商品列表】的資料訪問,商品類別:{},頁碼:{},每頁記錄數:{}",categoryId ,pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsListItemVO> list = goodsMapper.listByCategory(categoryId);
        PageInfo<GoodsListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
