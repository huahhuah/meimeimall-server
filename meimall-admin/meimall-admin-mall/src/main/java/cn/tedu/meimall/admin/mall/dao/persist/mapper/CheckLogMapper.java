package cn.tedu.meimall.admin.mall.dao.persist.mapper;

import cn.tedu.meimall.admin.mall.pojo.entity.CheckLog;
import cn.tedu.meimall.admin.mall.pojo.vo.CheckLogListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理審核日誌資料的Mapper介面
 */
@Repository
public interface CheckLogMapper extends BaseMapper<CheckLog> {

    /**
     * 根據資源類型查詢審核日誌列表
     * @param resourceType 資源類型
     * @return 審核日誌列表
     */
    List<CheckLogListItemVO> listByResourceType(int resourceType);
}
