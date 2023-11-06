package cn.tedu.meimall.front.account.dao.persist.mapper;

import cn.tedu.meimall.front.account.pojo.entity.User;
import cn.tedu.meimall.front.account.pojo.vo.UserSimpleInfoVO;
import cn.tedu.meimall.front.account.pojo.vo.UserStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 處理用戶資料的Mapper介面
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根據用戶ID查詢用戶資料詳情
     * @param id 用戶ID
     * @return 匹配的用戶資料詳情,如果沒有匹配的資料,則返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 根據用戶I查詢用戶基本資料
     * @param id 用戶ID
     * @return 匹配的用戶基本資料信息,如果沒有匹配的資料,則返回null
     */
    UserSimpleInfoVO getSimpleInfoById(Long id);
}
