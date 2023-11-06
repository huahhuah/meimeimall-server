package cn.tedu.meimall.basic.dao.cache.repository;

import cn.tedu.meimall.common.consts.cache.DistrictCacheConsts;
import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;

import java.util.List;

/**
 * 處理縣市資料的緩存訪問介面
 */
public interface IDistrictCacheRepository extends DistrictCacheConsts {

    /**
     * 存儲地區資料
     * @param districtSimplePO 地區資料
     */
    void save(DistrictSimplePO districtSimplePO);

    /**
     * 存儲根據父級劃分的地區列表
     * @param parentId             父級地區ID,如果保存縣市列表,則父級地址ID使用0
     * @param districtSimplePOList 歸屬此父級的地區資料
     */
    void saveListByParent(Long parentId, List<DistrictSimplePO> districtSimplePOList);

    /**
     * 刪除緩存的所有地區資料
     */
    void deleteAll();

    /**
     * 根據父級查詢子級地區列表
     * @param parentId 父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);
}
