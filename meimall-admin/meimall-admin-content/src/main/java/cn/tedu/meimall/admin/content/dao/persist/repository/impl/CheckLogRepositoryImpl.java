package cn.tedu.meimall.admin.content.dao.persist.repository.impl;

import cn.tedu.meimall.admin.content.dao.persist.mapper.CheckLogMapper;
import cn.tedu.meimall.admin.content.dao.persist.repository.ICheckLogRepository;
import cn.tedu.meimall.admin.content.pojo.entity.CheckLog;
import cn.tedu.meimall.admin.content.pojo.vo.CheckLogListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理審核日誌資料的存儲庫實現類
 */
@Slf4j
@Repository
public class CheckLogRepositoryImpl implements ICheckLogRepository {

    @Autowired
    private CheckLogMapper checkLogMapper;

    public CheckLogRepositoryImpl(){
        log.info("創建存儲庫對象: CheckLogRepositoryImpl");
    }

    @Override
    public int insert(CheckLog checkLog) {
        log.debug("開始執行【插入審核日誌】的資料訪問,資源類型: {}", checkLog);
        return checkLogMapper.insert(checkLog);
    }

    @Override
    public int deleteByResource(Integer resourceType, Long resourceId) {
        log.debug("開始執行【根据資源删除審核日誌】的資料訪問，資源類型：{}，資源ID：{}", resourceType, resourceId);
        QueryWrapper<CheckLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", resourceType);
        queryWrapper.eq("resource_Id", resourceId);
        return checkLogMapper.delete(queryWrapper);
    }

    @Override
    public PageData<CheckLogListItemVO> listByResourceType(int resourceType, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢審核日誌列表】的資料訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CheckLogListItemVO> list = checkLogMapper.listByResourceType(resourceType);
        PageInfo<CheckLogListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
