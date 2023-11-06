package cn.tedu.meimall.admin.account.dao.persist.repository.impl;

import cn.tedu.meimall.admin.account.dao.persist.mapper.UserRoleMapper;
import cn.tedu.meimall.admin.account.dao.persist.repository.IUserRoleRepository;
import cn.tedu.meimall.admin.account.pojo.entity.UserRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶與角色關聯資料的資料訪問實現類
 */
@Slf4j
@Repository
public class UserRoleRepositoryImpl implements IUserRoleRepository {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserRoleRepositoryImpl(){
        log.debug("創建存儲庫對象: UserRoleRepositoryImpl");
    }

    @Override
    public int insertBatch(List<UserRole> userRoleList) {
        log.debug("開始執行【批量插入用戶與角色的關聯資料】的資料訪問,參數: {}", userRoleList);
        return userRoleMapper.insertBatch(userRoleList);
    }

    @Override
    public int deleteByUserId(Long userId) {
        log.debug("開始執行【批量刪除用戶與角色的關聯資料】的資料訪問,參數: {}",userId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userRoleMapper.delete(queryWrapper);
    }
}
