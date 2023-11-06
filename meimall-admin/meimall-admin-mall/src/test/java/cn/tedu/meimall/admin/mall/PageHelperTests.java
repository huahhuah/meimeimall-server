package cn.tedu.meimall.admin.mall;

import cn.tedu.meimall.admin.mall.dao.persist.mapper.CategoryMapper;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.util.PageInfoToPageDataConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PageHelperTests {

    @Autowired
    CategoryMapper mapper;

    @Test
    void listByParent(){
        Integer pageNum = 1; //第幾頁
        Integer pageSize = 6; //每頁幾條
        Long parentId = 0L;
        PageHelper.startPage(pageNum, pageSize); //設置分頁,注意:這句話必須直接出現在查詢之前,否則可能導致線程安全問題
        List<CategoryListItemVO> list = mapper.listByParent(parentId);//Page類型,包含了分頁的相關數據,但不便於訪問
        System.out.println(list.getClass().getName());
        for (Object item : list) {
            System.out.println(item);
        }

        System.out.println("----------------");
        PageInfo<CategoryListItemVO> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
        System.out.println("數據總量:" + pageInfo.getTotal());
        System.out.println("總數頁:" +pageInfo.getPages());

        System.out.println("---------------");
        PageData<CategoryListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        System.out.println(pageData);
    }
}
