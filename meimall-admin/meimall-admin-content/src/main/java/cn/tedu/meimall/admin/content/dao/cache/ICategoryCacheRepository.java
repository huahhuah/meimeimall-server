package cn.tedu.meimall.admin.content.dao.cache;

import cn.tedu.meimall.admin.content.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.common.consts.cache.ContentCacheConsts;

import java.util.List;

/**
 * 處理類別緩存資料的接口
 */
public interface ICategoryCacheRepository extends ContentCacheConsts {

    void saveList(List<CategoryListItemVO> categoryList);

    boolean deleteList();

    List<CategoryListItemVO> list();
}
