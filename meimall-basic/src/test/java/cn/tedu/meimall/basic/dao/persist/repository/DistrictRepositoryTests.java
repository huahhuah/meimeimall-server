package cn.tedu.meimall.basic.dao.persist.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictRepositoryTests {

    @Autowired
    IDistrictRepository repository;

    @Test
    void listByParent() {
        Long parentId = 0L;
        List<?> list = repository.listByParent(parentId);
        System.out.println("根據列表資料完成，列表長度：" + list.size());
        for (Object item : list) {
            System.out.println("列表項：" + item);
        }
    }

}
