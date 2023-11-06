package cn.tedu.meimall.admin.account.dao.persist.repository.impl;

import cn.tedu.meimall.admin.account.dao.persist.mapper.UserMapper;
import cn.tedu.meimall.admin.account.dao.persist.repository.IUserRepository;
import cn.tedu.meimall.admin.account.pojo.entity.User;
import cn.tedu.meimall.admin.account.pojo.vo.UserListItemVO;
import cn.tedu.meimall.admin.account.pojo.vo.UserStandardVO;
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
 * 處理用戶資料的存儲庫實現類
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        log.debug("開始執行【插入用戶】的資料訪問,參數:{}", user);
        return userMapper.insert(user);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除用戶】的資料訪問,參數:{}", id);
        return userMapper.deleteById(id);
    }

    @Override
    public int updateById(User user) {
        log.debug("開始執行【更新用戶】的資料訪問,參數:{}", user);
        return userMapper.updateById(user);
    }

    @Override
    public int countByUsername(String username) {
        log.debug("開始持行【根據用戶名統計用戶的數量】的資料訪問,參數: {}",username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhone(String phone) {
        log.debug("開始持行【根據手機號碼統計用戶的數量】的資料訪問,參數: {}",phone);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByUsernameAndNotId(Long userId, String username) {
        log.debug("開始執行【根據用戶名統計用戶的數量】的資料訪問，參數：{}", username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).ne("id", userId);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhoneAndNotId(Long userId, String phone) {
        log.debug("開始執行【統計匹配手機號碼但非用户ID的用戶資料的數量】的資料訪問，手機號碼：{}，用戶ID：{}", phone, userId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone).ne("id", userId);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmail(String email) {
        log.debug("開始持行【根據電子信箱統計用戶的數量】的資料訪問,參數: {}",email);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmailAndNotId(Long userId, String email) {
        log.debug("開始執行【統計匹配電子信箱但非用户ID的用戶資料的數量】的資料訪問，手機號碼：{}，用戶ID：{}", email, userId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email).ne("id", userId);
        return userMapper.selectCount(queryWrapper);
    }


    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("開始持行【根據ID查詢用戶詳情】的資料訪問，參數：{}", id);
        return userMapper.getStandardById(id);
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始持行【查詢用戶列表】的資料訪問，頁碼：{}, 每頁記錄數:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<UserListItemVO> list = userMapper.list();
        PageInfo<UserListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
