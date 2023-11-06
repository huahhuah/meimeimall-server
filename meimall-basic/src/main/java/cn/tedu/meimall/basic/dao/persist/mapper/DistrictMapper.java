package cn.tedu.meimall.basic.dao.persist.mapper;

import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 處理縣市資料的Mapper介面
 */
@Repository
public interface DistrictMapper {

    /**
     * 根據父級查詢子級地區列表
     * @param parentId  父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);
}
