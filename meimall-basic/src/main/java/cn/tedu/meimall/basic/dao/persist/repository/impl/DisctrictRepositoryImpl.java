package cn.tedu.meimall.basic.dao.persist.repository.impl;

import cn.tedu.meimall.basic.dao.persist.mapper.DistrictMapper;
import cn.tedu.meimall.basic.dao.persist.repository.IDistrictRepository;
import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理縣市資料的資料訪問實現類
 */
@Slf4j
@Repository
public class DisctrictRepositoryImpl implements IDistrictRepository {

   @Autowired
   private DistrictMapper districtMapper;

   public DisctrictRepositoryImpl(){
       log.debug("創建存儲庫對象: DisctrictRepositoryImpl");
   }

    @Override
    public List<DistrictSimplePO> listByParent(Long parentId) {
       log.debug("開始執行【根據父級查詢子級地區列表】的資料訪問，參數:{}", parentId);
       return districtMapper.listByParent(parentId);
    }
}
