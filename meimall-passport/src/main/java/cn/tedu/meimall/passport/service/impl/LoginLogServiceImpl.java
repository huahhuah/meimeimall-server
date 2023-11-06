package cn.tedu.meimall.passport.service.impl;


import cn.tedu.meimall.passport.dao.persist.repository.ILoginLogRepository;
import cn.tedu.meimall.passport.pojo.vo.LoginLogListItemVO;
import cn.tedu.meimall.passport.service.ILoginLogService;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理用戶登入日誌的業務實現類
 *
 */
@Slf4j
@Service
public class LoginLogServiceImpl implements ILoginLogService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ILoginLogRepository loginLogRepository;

    public LoginLogServiceImpl() {
        log.info("創建業務對象：LoginLogServiceImpl");
    }

    @Override
    public PageData<LoginLogListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢登入日誌列表】的業務，頁碼：{}", pageNum);
        PageData<LoginLogListItemVO> pageData = loginLogRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<LoginLogListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢登入日誌列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageData<LoginLogListItemVO> pageData = loginLogRepository.list(pageNum, pageSize);
        return pageData;
    }

}
