package cn.tedu.meimall.passport.dao.persist.repository.impl;


import cn.tedu.meimall.passport.dao.persist.mapper.LoginLogMapper;
import cn.tedu.meimall.passport.dao.persist.repository.ILoginLogRepository;
import cn.tedu.meimall.passport.pojo.entity.LoginLog;
import cn.tedu.meimall.passport.pojo.vo.LoginLogListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理登入日誌資料的存儲庫實現類
 *
 */
@Slf4j
@Repository
public class LoginLogRepositoryImpl implements ILoginLogRepository {

    @Autowired
    private LoginLogMapper loginLogMapper;

    public LoginLogRepositoryImpl() {
        log.info("創建存儲庫對象：LoginLogRepositoryImpl");
    }

    @Override
    public int insert(LoginLog loginLog) {
        log.debug("開始執行【插入登入日誌】的資料訪問，參數：{}", loginLog);
        return loginLogMapper.insert(loginLog);
    }

    @Override
    public PageData<LoginLogListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢用戶登入日誌列表】的資料訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<LoginLogListItemVO> list = loginLogMapper.list();
        PageInfo<LoginLogListItemVO> pageInfo = new PageInfo<>(list);
        PageData<LoginLogListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}
