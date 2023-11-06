package cn.tedu.meimall.passport.dao.persist.mapper;


import cn.tedu.meimall.passport.pojo.entity.User;
import cn.tedu.meimall.passport.pojo.vo.UserLoginInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;

/**
 * 處理用戶數據的Mapper接口
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根據用戶名查詢用戶的登入信息
     *
     * @param username 用戶名
     * @return 匹配的用戶的登入信息，如果没有匹配的數據，則返回null
     */
    UserLoginInfoVO getLoginInfoByUsername(String username);


}
