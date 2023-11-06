package cn.tedu.meimall.admin.mall.service.impl;

import cn.tedu.meimall.admin.mall.dao.persist.repository.ICheckLogRepository;
import cn.tedu.meimall.admin.mall.pojo.vo.CheckLogListItemVO;
import cn.tedu.meimall.admin.mall.service.ICheckLogService;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *處理審核日誌的業務實現類
 */
@Slf4j
@Service
public class CheckLogServiceImpl implements ICheckLogService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ICheckLogRepository checkLogRepository;

    public CheckLogServiceImpl(){
        log.debug("創建業務類對象: CheckLogServiceImpl");
    }

    @Override
    public PageData<CheckLogListItemVO> listGoodsCheckLog(Integer pageNum) {
        log.debug("開始處理【查詢商品審核日誌列表】的業務，頁碼：{}", pageNum);
        return checkLogRepository.listByResourceType(RESOURCE_TYPE_GOODS, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CheckLogListItemVO> listGoodsCheckLog(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢商品審核日誌列表】的業務，頁碼:{},每頁記錄數:{}",pageNum, pageSize);
        return checkLogRepository.listByResourceType(RESOURCE_TYPE_COMMENT, pageNum, pageSize);
    }

    @Override
    public PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum) {
        log.debug("開始處理【查詢評論審核日誌列表】的業務，頁碼：{}",pageNum);
        return checkLogRepository.listByResourceType(RESOURCE_TYPE_COMMENT, pageNum,defaultQueryPageSize);
    }

    @Override
    public PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢評論審核日誌列表】的業務，頁碼:{},每頁記錄數:{}",pageNum, pageSize);
        return checkLogRepository.listByResourceType(RESOURCE_TYPE_COMMENT, pageNum, pageSize);
    }
}
