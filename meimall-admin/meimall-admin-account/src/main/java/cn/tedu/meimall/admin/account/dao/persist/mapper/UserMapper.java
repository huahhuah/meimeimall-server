package cn.tedu.meimall.admin.account.dao.persist.mapper;

import cn.tedu.meimall.admin.account.pojo.entity.User;
import cn.tedu.meimall.admin.account.pojo.vo.UserListItemVO;
import cn.tedu.meimall.admin.account.pojo.vo.UserStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶資料的Mapper接口
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
     * 查詢用戶資料
     * @return 用戶資料列表
     */
    List<UserListItemVO> list();
}
