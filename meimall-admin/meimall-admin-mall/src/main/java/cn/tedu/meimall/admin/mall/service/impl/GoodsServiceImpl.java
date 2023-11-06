package cn.tedu.meimall.admin.mall.service.impl;

import cn.tedu.meimall.admin.mall.dao.persist.repository.*;
import cn.tedu.meimall.admin.mall.dao.search.IGoodsSearchRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.Category;
import cn.tedu.meimall.admin.mall.pojo.entity.CheckLog;
import cn.tedu.meimall.admin.mall.pojo.entity.Goods;
import cn.tedu.meimall.admin.mall.pojo.entity.GoodsDetail;
import cn.tedu.meimall.admin.mall.pojo.param.GoodsAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.GoodsUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsSearchVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.admin.mall.service.IGoodsService;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 處理商品資料的業務實現類
 */
@Slf4j
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IGoodsDetailRepository goodsDetailRepository;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private ICheckLogRepository checkLogRepository;
    @Autowired
    private IGoodsSearchRepository goodsSearchRepository;

    public GoodsServiceImpl(){
        log.debug("創建業務類對象: GoodsServiceImpl");
    }

    @Override
    public void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, GoodsAddNewParam goodsAddNewParam) {
        log.debug("開始處理【發布商品】的業務,當事人:{},IP地址:{},參數:{}",currentPrincipal, remoteAddr, goodsAddNewParam);

        Long categoryId = goodsAddNewParam.getCategoryId();
        CategoryStandardVO category = categoryRepository.getStandardById(categoryId);
        if(categoryId == null) {
            String message = "發布商品失敗,類別資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(category.getIsParent() !=0){
            String message = "發布商品失敗,選擇的類別必須不包含子級類別!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        if(category.getEnable() != 1){
            String message = "發布商品失敗,選擇的類別已經被禁用!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsAddNewParam, goods);
        goods.setCategoryName(category.getName());
        goods.setCheckState(0);
        goods.setIsRecommend(0);
        goods.setIsPutOn(0);
        goods.setSalesCount(0);
        goods.setCommentCount(0);
        goods.setPositiveCommentCount(0);
        goods.setNegativeCommentCount(0);
        int rows = goodsRepository.insert(goods);
        if(rows != 1){
            String message = "發布商品失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setGoodsId(goods.getId());
        goodsDetail.setDetail(goodsAddNewParam.getDetail());
        rows = goodsDetailRepository.insert(goodsDetail);
        if (rows != 1) {
            String message = "發布商品失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("開始處理【根據ID刪除商品】的業務,參數:{}", id);
        GoodsStandardVO queryResult = goodsRepository.getStandardById(id);
        if(queryResult == null){
            String message = "刪除商品失敗,嘗試刪除的商品資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = goodsRepository.deleteById(id);
        if(rows != 1){
            String message = "刪除商品失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        rows = goodsDetailRepository.deleteByGoods(id);
        if(rows != 1 ){
            String message = "刪除商品失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        commentRepository.deleteById(id);
        checkLogRepository.deleteByResource(RESOURCE_TYPE_GOODS, id);
    }

    @Override
    public void passCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks) {
        log.debug("開始處理【審核通過商品】的業務,參數:{}", goodsId);
        updateCheckById(currentPrincipal, goodsId, CHECK_STATE_PASS, remarks);
    }

    @Override
    public void rejectCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks) {
        log.debug("開始處理【拒絕審核商品】的業務,參數:{}", goodsId);
        updateCheckById(currentPrincipal, goodsId, CHECK_STATE_REJECT, remarks);
    }

    @Override
    public void putOn(Long id) {
        log.debug("開始處理【上架商品】的業務,參數:{}", id);
        updatePutOnById(id, PUT_ON_STATE_ON);
    }

    @Override
    public void pullOff(Long id) {
        log.debug("開始處理【下架商品】的業務,參數:{}", id);
        updatePutOnById(id, PUT_ON_STATE_OFF);
    }

    @Override
    public void setRecommend(Long id) {
        log.debug("開始處理【推薦商品】的業務,參數:{}", id);
        updateRecommendById(id, RECOMMEND_STATE_ON);
    }

    @Override
    public void cancelRecommend(Long id) {
        log.debug("開始處理【取消推薦商品】的業務,參數:{}", id);
        updateRecommendById(id, RECOMMEND_STATE_OFF);
    }

    @Override
    public void updateInfoById(Long id, GoodsUpdateInfoParam goodsUpdateInfoParam) {
        log.debug("開始處理【修改商品詳情詳情】的業務,ID:{},新資料:{}", id, goodsUpdateInfoParam);
        // 調用Mapper對象的getStandardById()持行查詢
        GoodsStandardVO queryResult = goodsRepository.getStandardById(id);
        //判斷查詢結果是否為null
        if(queryResult == null){
            //是: 拋出異常
            String message = "修改商品詳情失敗,嘗試修改的類別資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        //創建goods對象
        Goods goods = new Goods();
        //複製屬性,設置ID
        BeanUtils.copyProperties(goodsUpdateInfoParam, goods);
        goods.setId(id);
        //調用Mapper對象的update()方法執行修改
        int rows = goodsRepository.update(goods);
        if(rows != 1){
            String message = "修改商品失敗,服務器忙,請稍稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setGoodsId(goods.getId());
        goodsDetail.setDetail(goodsUpdateInfoParam.getDetail());
        rows = goodsDetailRepository.updateByGoods(goodsDetail);
        if (rows != 1) {
            String message = "修改商品詳情失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        log.debug("修改商品成功!");
    }

    @Override
    public GoodsStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢商品】的業務,參數:{}", id);
        GoodsStandardVO queryResult = goodsRepository.getStandardById(id);
        if(queryResult == null){
            String message = "查詢商品失敗,商品資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<GoodsListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢商品列表】的業務，頁碼：{}", pageNum);
        return goodsRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<GoodsListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢商品列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return goodsRepository.list(pageNum, pageSize);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum) {
        log.debug("開始處理【根據類别查詢商品列表】的業務，商品類别：{}, 頁碼：{}", categoryId, pageNum);
        return goodsRepository.listByCategory(categoryId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據類别查詢商品列表】的業務，商品類别：{}, 頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        return goodsRepository.listByCategory(categoryId, pageNum, pageSize);
    }

    @Override
    public void rebuildSearch() {
        log.debug("開始處理【重建商品的搜索資料】的業務");
        goodsSearchRepository.deleteAll();
        Integer pageNum = 1;
        Integer pageSize = 3;
        Integer maxPage;
        PageData<GoodsSearchVO> pageData;
        do {
            pageData = goodsRepository.listSearch(pageNum, pageSize);
            maxPage = pageData.getMaxPage();
            goodsSearchRepository.saveAll(pageData.getList());
            pageNum++;
        } while (pageNum <= maxPage);
    }

    public void updateCheckById(CurrentPrincipal currentPrincipal, Long id, Integer checkState, String remarks){
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(id);
        if(currentGoods == null){
            String message = "將商品的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，商品資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        if(currentGoods.getCheckState().equals(checkState)){
            String message = "將商品的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗,此商品已經處於" + CHECK_STATE_TEXT[checkState] + "狀態!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        int subStringEndIndex = currentGoods.getTitle().length() < BRIEF_MAX_LENGTH ? currentGoods.getTitle().length() : BRIEF_MAX_LENGTH;

        CheckLog checkLog = new CheckLog();
        checkLog.setResourceType(RESOURCE_TYPE_GOODS);
        checkLog.setResourceId(id);
        checkLog.setResourceBrief(currentGoods.getTitle().substring(0, subStringEndIndex));
        checkLog.setCheckUserId(currentPrincipal.getId());
        checkLog.setCheckUsername(currentPrincipal.getUsername());
        checkLog.setCheckRemarks(remarks);
        checkLog.setOriginalState(currentGoods.getCheckState());
        checkLog.setNewState(checkState);
        checkLog.setGmtCheck(LocalDateTime.now());
        int rows = checkLogRepository.insert(checkLog);
        if( rows != 1 ){
            String message = "將商品的審核狀態修改為【" +  CHECK_STATE_TEXT[checkState] + "】失敗,服務器忙,請稍後再試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(id);
        updateGoods.setCheckState(checkState);
        rows = goodsRepository.update(updateGoods);
        if(rows != 1){
            String message = "將商品的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] +"】失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updatePutOnById(Long id, Integer isPutOn){
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(id);
        if(currentGoods == null){
            String message = "將商品狀態修改為【"+ PUT_ON_STATE_TEXT[isPutOn] + "】失敗,商品資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(currentGoods.getIsPutOn().equals(isPutOn)){
            String message = "將商品狀態修改為【" + PUT_ON_STATE_TEXT[isPutOn] + "】失敗,此商品已經處於" + PUT_ON_STATE_TEXT[isPutOn] + "狀態!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(id);
        updateGoods.setIsPutOn(isPutOn);
        int rows = goodsRepository.update(updateGoods);
        if(rows != 1) {
            String message = "將商品狀態修改為【"+ PUT_ON_STATE_TEXT[isPutOn] + "】失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void  updateRecommendById(Long id , Integer isRecommend){
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(id);
        if(currentGoods == null){
            String message = "將商品推薦狀態修改為【"+ RECOMMEND_STATE_TEXT[isRecommend]+ "】失敗,商品資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        if(currentGoods.getIsRecommend().equals(isRecommend)){
            String message = "將商品推薦狀態修改為【" + RECOMMEND_STATE_TEXT[isRecommend] +"】失敗,此商品已經處於" + RECOMMEND_STATE_TEXT[isRecommend] +"狀態!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(id);
        updateGoods.setIsRecommend(isRecommend);
        int rows = goodsRepository.update(updateGoods);
        if(rows != 1){
            String message = "將商品推薦狀態修改為【" + RECOMMEND_STATE_TEXT[isRecommend] + "】失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

}
