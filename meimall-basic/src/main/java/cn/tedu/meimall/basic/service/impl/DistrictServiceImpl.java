package cn.tedu.meimall.basic.service.impl;

import cn.tedu.meimall.basic.dao.cache.repository.IDistrictCacheRepository;
import cn.tedu.meimall.basic.dao.persist.repository.IDistrictRepository;
import cn.tedu.meimall.basic.service.IDistrictService;
import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 處理縣市區數據的業務實現類
 */
@Slf4j
@Service
public class DistrictServiceImpl implements IDistrictService {

   @Autowired
   private IDistrictRepository districtRepository;
   @Autowired
   private IDistrictCacheRepository districtCacheRepository;

   public DistrictServiceImpl(){
       log.debug("創建業務類對象: DistrictServiceImpl");
   }

    @Override
    public List<DistrictSimplePO> listByParent(Long parentId) {
       log.debug("開始執行【根據父級查詢子級地區列表】的業務,參數:{}", parentId);
       return districtRepository.listByParent(parentId);
    }

    @Override
    public void rebuildCache() {
       districtCacheRepository.deleteAll();

       Long parentId = 0L ;
        List<DistrictSimplePO> list = districtRepository.listByParent(parentId);

        for (DistrictSimplePO districtSimplePO : list) {
            districtCacheRepository.save(districtSimplePO);
        }

        districtCacheRepository.saveListByParent(parentId, list);
        for (DistrictSimplePO listItem : list) {
            callListRecursion(listItem);
        }

    }

    /**
     * 遞歸得到各地區的子級列表數據
     * @param district 地區數據
     */
    private void callListRecursion(DistrictSimplePO district){
        Long districtId = district.getId();
        List<DistrictSimplePO> list = districtRepository.listByParent(districtId);
        for (DistrictSimplePO districtSimplePO : list) {
            districtCacheRepository.save(districtSimplePO);
            callListRecursion(districtSimplePO);
        }
    }
}
