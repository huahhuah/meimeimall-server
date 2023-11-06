package cn.tedu.meimall.admin.content.dao.persist.mapper;

import cn.tedu.meimall.admin.content.pojo.entity.UpDownLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 處理頂踩日誌資料的Mapper接口
 */
@Repository
public interface UpDownLogMapper extends BaseMapper<UpDownLog> {
}
