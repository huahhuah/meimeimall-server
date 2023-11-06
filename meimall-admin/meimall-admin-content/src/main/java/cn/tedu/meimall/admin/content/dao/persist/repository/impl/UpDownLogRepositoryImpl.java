package cn.tedu.meimall.admin.content.dao.persist.repository.impl;

import cn.tedu.meimall.admin.content.dao.persist.mapper.UpDownLogMapper;
import cn.tedu.meimall.admin.content.dao.persist.repository.IUpDownLogRepository;
import cn.tedu.meimall.admin.content.pojo.entity.UpDownLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 處理頂踩日誌資料的存儲庫實現類
 */
@Slf4j
@Repository
public class UpDownLogRepositoryImpl implements IUpDownLogRepository {

    @Autowired
    private UpDownLogMapper upDownLogMapper;

    public UpDownLogRepositoryImpl(){
        log.info("創建存儲庫對象: UpDownLogRepositoryImpl");
    }

    @Override
    public int deleteByResource(Integer resourceType, Long resourceId) {
        log.debug("開始執行【根據資源刪除頂踩記錄】的資料訪問,資源類型:{},資源ID:{}", resourceType, resourceId);
        QueryWrapper<UpDownLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", resourceType);
        queryWrapper.eq("resource_id", resourceId);
        return upDownLogMapper.delete(queryWrapper);
    }
}
