package cn.tedu.meimall.common.util;

import cn.tedu.meimall.common.pojo.vo.PageData;
import com.github.pagehelper.PageInfo;

/**
 * 將PageInfo轉換成PageData的轉換器工具類
 */
public class PageInfoToPageDataConverter {

    /**
     * 將PageHelper框架中的PageInfo類型對象轉換成自定義的PageData類型對象
     * @param pageInfo PageInfo對象
     * @param <T>      PageInfo對象中的列表資料中的元素資料的類型
     * @return  自定義的PageData類型的對象
     */
    public synchronized static <T> PageData<T> convert(PageInfo<T> pageInfo){
        PageData<T> pageData = new PageData<>();
        pageData.setPageSize(pageInfo.getPageSize())
                .setTotal(pageInfo.getTotal())
                .setCurrentPage(pageInfo.getPageNum())
                .setMaxPage(pageInfo.getPages())
                .setList(pageInfo.getList());
        return pageData;
    }
}
