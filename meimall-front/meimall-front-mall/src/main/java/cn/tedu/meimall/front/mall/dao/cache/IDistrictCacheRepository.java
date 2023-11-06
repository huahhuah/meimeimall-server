package cn.tedu.meimall.front.mall.dao.cache;

import cn.tedu.meimall.common.consts.cache.DistrictCacheConsts;
import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;

/**
 * 處理市區資料的緩存訪問接口
 */
public interface IDistrictCacheRepository extends DistrictCacheConsts {

    /**
     * 根據地區的行政代碼獲取地區資料
     * @param code 地區的行政代碼
     * @return 地區資料
     */
    DistrictSimplePO getByCode(String code);
}
