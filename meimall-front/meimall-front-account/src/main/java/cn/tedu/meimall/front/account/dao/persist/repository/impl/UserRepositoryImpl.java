package cn.tedu.meimall.front.account.dao.persist.repository.impl;

import cn.tedu.meimall.front.account.dao.persist.mapper.UserMapper;
import cn.tedu.meimall.front.account.dao.persist.repository.IUserRepository;
import cn.tedu.meimall.front.account.pojo.entity.User;
import cn.tedu.meimall.front.account.pojo.vo.UserSimpleInfoVO;
import cn.tedu.meimall.front.account.pojo.vo.UserStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 處理用戶資料的存儲庫實現類
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserRepositoryImpl(){
        log.info("創建存儲庫對象: UserRepositoryImpl");
    }

    @Override
    public int insert(User user) {
        log.debug("開始執行【新增用戶】的資料訪問,參數:{}", user);
        return userMapper.insert(user);
    }

    @Override
    public int updateById(User user) {
        log.debug("開始執行【根據ID修改用戶資料】的資料放問,參數:{}", user);
        return userMapper.updateById(user);
    }

    @Override
    public int countByUsername(String username) {
        log.debug("開始執行【根據用戶名統計用戶數量】的資料訪問,參數:{}", username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhone(String phone) {
        log.debug("開始執行【根據手機號碼統計用戶數量】的資料訪問,參數:{}", phone);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhoneAndNotId(String phone, Long userId) {
        log.debug("開始執行【統計匹配手機號碼但非用戶ID的用戶資料的數量】的資料訪問,手機號碼:{}, 用戶ID:{}", phone, userId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.ne("id", userId);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmail(String email) {
        log.debug("開始執行【根據電子信箱統計用戶資料的數量】的資料訪問,電子信箱:{}", email);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmailAndNotId(String email, Long userId) {
        log.debug("開始執行【統計匹配電子信箱但非用戶ID的用戶資料的數量】的資料訪問,手機號碼:{}, 用戶ID:{}", email, userId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.ne("id", userId);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢用戶詳情】的資料訪問,參數:{}", id);
        return userMapper.getStandardById(id);
    }

    @Override
    public UserSimpleInfoVO getSimpleInfoById(Long id) {
        log.debug("開始執行【根據ID查詢用戶基本資料信息】的資料訪問,參數:{}", id);
        return userMapper.getSimpleInfoById(id);
    }
}
