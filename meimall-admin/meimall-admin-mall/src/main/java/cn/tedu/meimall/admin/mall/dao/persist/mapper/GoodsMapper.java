package cn.tedu.meimall.admin.mall.dao.persist.mapper;

import cn.tedu.meimall.admin.mall.pojo.entity.Goods;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsSearchVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理商品資料的Mapper介面
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根據ID查詢商品
     * @param id 商品ID
     * @return 匹配的商品,如果沒有匹配的資料,則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢商品資料列表
     * @return  商品資料列表
     */
    List<GoodsListItemVO> list();

    /**
     * 查詢用於搜索的商品資料列表,此搜索結果將用於寫入到ES中
     * @return 商品資料列表
     */
    List<GoodsSearchVO> listSearch();

    /**
     * 跟據類別查詢商品列表
     * @param categoryId 商品類別ID
     * @return 商品列表
     */
    List<GoodsListItemVO> listByCategory(Long categoryId);
}
