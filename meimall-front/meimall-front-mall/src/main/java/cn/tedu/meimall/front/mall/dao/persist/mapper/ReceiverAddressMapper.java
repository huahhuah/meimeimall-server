package cn.tedu.meimall.front.mall.dao.persist.mapper;

import cn.tedu.meimall.front.mall.pojo.entity.ReceiverAddress;
import cn.tedu.meimall.front.mall.pojo.vo.ReceiverAddressListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.ReceiverAddressStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理收貨地址資料的Mapper介面
 */
@Repository
public interface ReceiverAddressMapper extends BaseMapper<ReceiverAddress> {

    /**
     * 根據ID查詢收貨地址資料詳情
     * @param id 收貨地址ID
     * @return 匹配的收貨地址資料詳情,如果沒有匹配的資料,則返回null
     */
    ReceiverAddressStandardVO getStandardById(Long id);

    /**
     * 根據用戶查詢收貨第置列表
     * @param userId 用戶ID
     * @return 收貨地址列表
     */
    List<ReceiverAddressListItemVO> listByUser(Long userId);
}
