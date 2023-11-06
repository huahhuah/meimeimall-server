package cn.tedu.meimall.basic.dao.persist.repository;

import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;

import java.util.List;

/**
 * 處理縣市資料的資料訪問介面
 */
public interface IDistrictRepository {

    /**
     * 根據父級查詢子級地區列表
     * @param parentId 父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);
}
