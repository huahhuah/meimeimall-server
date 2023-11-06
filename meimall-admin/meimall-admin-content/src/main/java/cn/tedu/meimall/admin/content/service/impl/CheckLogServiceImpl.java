package cn.tedu.meimall.admin.content.service.impl;

import cn.tedu.meimall.admin.content.dao.persist.repository.ICheckLogRepository;
import cn.tedu.meimall.admin.content.pojo.vo.CheckLogListItemVO;
import cn.tedu.meimall.admin.content.service.ICheckLogService;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理審核日誌的業務實現類
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
    public PageData<CheckLogListItemVO> listArticleCheckLog(Integer pageNum) {
        log.debug("開始處理【查詢文章審核日誌列表】的業務,頁碼:{}", pageNum);
        return listByResourceType(RESOURCE_TYPE_GOODS, pageNum);
    }

    @Override
    public PageData<CheckLogListItemVO> listArticleCheckLog(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢文章審核日誌列表】的業務,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        return listByResourceType(RESOURCE_TYPE_GOODS, pageNum, pageSize);
    }

    @Override
    public PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum) {
        log.debug("開始處理【查詢評論審核日誌列表】的業務,頁碼:{}", pageNum);
        return listByResourceType(RESOURCE_TYPE_COMMENT, pageNum);
    }

    @Override
    public PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢評論審核日誌列表】的業務,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        return listByResourceType(RESOURCE_TYPE_COMMENT, pageNum, pageSize);
    }

    /**
     * 查詢審核日誌列表,將使用默認的每頁記錄數
     * @param resourceType  資源類型
     * @param pageNum       頁碼
     * @return  審核日誌列表
     */
    private PageData<CheckLogListItemVO> listByResourceType(int resourceType, Integer pageNum){
        return checkLogRepository.listByResourceType(resourceType, pageNum, defaultQueryPageSize);
    }

    /**
     * 查詢審核日誌列表
     * @param resourceType 資源類型
     * @param pageNum      頁碼
     * @param pageSize     每頁記錄數
     * @return 審核日誌列表
     */
    private PageData<CheckLogListItemVO> listByResourceType(int resourceType, Integer pageNum, Integer pageSize){
        return checkLogRepository.listByResourceType(resourceType, pageNum, pageSize);
    }
}
