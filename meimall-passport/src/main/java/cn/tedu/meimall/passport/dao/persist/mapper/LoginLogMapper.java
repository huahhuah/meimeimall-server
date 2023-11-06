package cn.tedu.meimall.passport.dao.persist.mapper;


import cn.tedu.meimall.passport.pojo.entity.LoginLog;
import cn.tedu.meimall.passport.pojo.vo.LoginLogListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶登入日誌的Mapper接口
 */
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    /**
     * 查詢用戶登入日誌列表
     *
     * @return 用戶登入日誌列表
     */
    List<LoginLogListItemVO> list();

}
