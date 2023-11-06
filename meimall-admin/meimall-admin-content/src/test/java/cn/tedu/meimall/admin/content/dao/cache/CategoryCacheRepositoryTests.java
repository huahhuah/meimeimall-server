package cn.tedu.meimall.admin.content.dao.cache;

import cn.tedu.meimall.admin.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryCacheRepositoryTests {

    @Autowired
    ICategoryCacheRepository repository;

    @Test
    void saveList(){
        List<CategoryListItemVO> categoryList = new ArrayList<>();
        for (int i = 0; i <= 15 ; i++) {
            CategoryListItemVO category = new CategoryListItemVO();
            category.setId(i + 0L);
            category.setName("測試類別-" +i);
            categoryList.add(category);
        }
        repository.saveList(categoryList);
    }

    @Test
    void list(){
        List<CategoryListItemVO> list = repository.list();
        for (CategoryListItemVO listItem : list) {
            System.out.println(listItem);
        }
    }
}
