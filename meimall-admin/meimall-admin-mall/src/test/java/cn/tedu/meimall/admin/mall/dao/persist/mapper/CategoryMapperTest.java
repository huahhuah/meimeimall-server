package cn.tedu.meimall.admin.mall.dao.persist.mapper;

import cn.tedu.meimall.admin.mall.pojo.entity.Category;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    CategoryMapper mapper;

    @Test
    void insert(){
        Category category = new Category();
        category.setName("測試寶盒蛋糕");
        System.out.println("插入資料之前,參數:" + category);
        int rows = mapper.insert(category);
        System.out.println("受影響的行數:" +rows);
        System.out.println("插入資料之後,參數:" + category);
    }

    @Test
    void deleteById(){
        Long id = 14L;
        int rows = mapper.deleteById(id);
        System.out.println("受影響的行數:" +rows);
    }

    @Test
    void deleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 14);
        int rows = mapper.deleteByMap(map);
        System.out.println("受影響的行數: " + rows);
    }

    @Test
    void delete(){
        // eq >>> equals
        // ne >>> not equals
        // lt >>> less than
        // gt >>> greater than
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.lt("id", 14);
        int rows = mapper.delete(wrapper);
        System.out.println("受影響的行數:" +rows);
    }

    @Test
    void deleteBatchIds(){
        List<Long> idList = new ArrayList<>();
        idList.add(3L);
        idList.add(4L);
        idList.add(5L);
        idList.add(6L);
        idList.add(7L);
        int rows = mapper.deleteBatchIds(idList);
        System.out.println("受影響的行數：" + rows);
    }

    @Test
    void updateById() {
        Category category = new Category();
        category.setId(1L);
        category.setName("hehe");
        category.setDepth(2);
        // category.setGmtModified(LocalDateTime.now());
        int rows = mapper.updateById(category); // where id=null
        System.out.println("受影響的行數：" + rows);
    }

    @Test
    void update() {
        Category category = new Category();
        category.setName("hehe");
        category.setDepth(2);

        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 17);
        int rows = mapper.update(category, wrapper);
        System.out.println("受影響的行數：" + rows);
    }

    @Test
    void getStandardById() {
        Long id = 15L;
        CategoryStandardVO result = mapper.getStandardById(id);
        System.out.println("查詢完成，結果：" + result);
    }
}
