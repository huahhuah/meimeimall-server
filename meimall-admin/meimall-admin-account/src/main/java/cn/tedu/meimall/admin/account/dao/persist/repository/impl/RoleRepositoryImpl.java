package cn.tedu.meimall.admin.account.dao.persist.repository.impl;

import cn.tedu.meimall.admin.account.dao.persist.mapper.RoleMapper;
import cn.tedu.meimall.admin.account.dao.persist.repository.IRoleRepository;
import cn.tedu.meimall.admin.account.pojo.vo.RoleListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理角色資料的資料訪問實現類
 */
@Slf4j
@Repository
public class RoleRepositoryImpl implements IRoleRepository {

    @Autowired
    private RoleMapper roleMapper;

    public RoleRepositoryImpl(){
        log.debug("創建存儲庫對象: RoleRepositoryImpl");
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢用戶列表】的資料訪問,頁碼: {},每頁記錄數:{}",pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<RoleListItemVO> list = roleMapper.list();
        PageInfo<RoleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
