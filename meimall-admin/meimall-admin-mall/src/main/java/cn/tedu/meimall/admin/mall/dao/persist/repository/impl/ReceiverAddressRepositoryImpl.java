package cn.tedu.meimall.admin.mall.dao.persist.repository.impl;


import cn.tedu.meimall.admin.mall.dao.persist.mapper.ReceiverAddressMapper;
import cn.tedu.meimall.admin.mall.dao.persist.repository.IReceiverAddressRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.ReceiverAddress;
import cn.tedu.meimall.admin.mall.pojo.vo.ReceiverAddressListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.ReceiverAddressStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理收貨地址資料的存儲庫實現類
 */
@Slf4j
@Repository
public class ReceiverAddressRepositoryImpl implements IReceiverAddressRepository {

    @Autowired
    private ReceiverAddressMapper receiverAddressMapper;

    public ReceiverAddressRepositoryImpl(){
        log.info("創建存儲庫對象: ReceiverAddressRepositoryImpl");
    }

    @Override
    public int insert(ReceiverAddress receiverAddress) {
        log.debug("開始執行【插入收貨地址】的資料訪問，參數：{}", receiverAddress);
        return receiverAddressMapper.insert(receiverAddress);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除收貨地址資料】的資料訪問,參數:{}", id);
        return receiverAddressMapper.deleteById(id);
    }

    @Override
    public int update(ReceiverAddress receiverAddress) {
        log.debug("開始執行【根據ID修改收貨地址資料】的資料訪問,參數:{}", receiverAddress);
        return receiverAddressMapper.updateById(receiverAddress);
    }

    @Override
    public int updateNonDefaultByUser(Long userId) {
        log.debug("開始執行【將用戶的所有收貨地址設置為非默認】的資料訪問,參數:{}", userId);
        ReceiverAddress receiverAddress = new ReceiverAddress();
        receiverAddress.setIsDefault(0);
        QueryWrapper<ReceiverAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return receiverAddressMapper.update(receiverAddress, queryWrapper);
    }

    @Override
    public ReceiverAddressStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢收貨地址資料詳情】的資料訪問,參數:{}", id);
        return receiverAddressMapper.getStandardById(id);
    }

    @Override
    public List<ReceiverAddressListItemVO> listByUser(Long userId) {
        log.debug("開始執行【根據用戶查詢收貨地址列表】的資料訪問,參數:{}", userId);
        return receiverAddressMapper.listByUser(userId);
    }
}
