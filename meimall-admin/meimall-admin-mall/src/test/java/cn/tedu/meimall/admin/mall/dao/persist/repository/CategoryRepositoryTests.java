package cn.tedu.meimall.admin.mall.dao.persist.repository;

import cn.tedu.meimall.common.pojo.vo.PageData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTests {

    @Autowired
    ICategoryRepository repository;

    @Test
    void countByName() {
        String name = "hehe";
        int countByName = repository.countByName(name);
        System.out.println("統計完成，結果：" + countByName);
    }

    @Test
    void listByParent() {
        Long parentId = 0L;
        Integer pageNum = 1;
        Integer pageSize = 8;
        PageData<?> pageData = repository.listByParent(parentId, pageNum, pageSize);
        System.out.println("每頁資料量：" + pageData.getPageSize());
        System.out.println("總資料量：" + pageData.getTotal());
        System.out.println("總頁數：" + pageData.getMaxPage());
        System.out.println("列表：" + pageData.getList());
    }

}
