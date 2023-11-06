package cn.tedu.meimall.basic.service;

import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理縣市資料的業務介面
 */
@Transactional
public interface IDistrictService {

    /**
     * 根據父級查詢子級地區列表
     * @param parentId 父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);

    /**
     * 重建地區資料的緩存
     */
    void rebuildCache();
}
