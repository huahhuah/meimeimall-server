package cn.tedu.meimall.passport.dao.persist.repository.impl;


import cn.tedu.meimall.passport.dao.persist.mapper.UserMapper;
import cn.tedu.meimall.passport.dao.persist.repository.IUserRepository;
import cn.tedu.meimall.passport.pojo.entity.User;
import cn.tedu.meimall.passport.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * 處理用戶資料的存儲庫實現類
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserRepositoryImpl() {
        log.info("創建存儲庫對象：UserRepositoryImpl");
    }

    @Override
    public int updateLastLogin(Long id, Integer loginCount, String lastLoginIp, LocalDateTime gmtLastLogin) {
        log.debug("開始執行【更新登入次數】的資料訪問，用戶ID：{}，登入次數：{}", id, loginCount);
        User user = new User();
        user.setId(id);
        user.setLoginCount(loginCount);
        user.setLastLoginIp(lastLoginIp);
        user.setGmtLastLogin(gmtLastLogin);
        return userMapper.updateById(user);
    }

    @Override
    public UserLoginInfoVO getLoginInfoByUsername(String username) {
        log.debug("開始執行【根據用戶名查詢用戶登入信息】的資料訪問，參數：{}", username);
        return userMapper.getLoginInfoByUsername(username);
    }

}
