package cn.tedu.meimall.front.mall.dao.cache;

import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DistrictCacheRepositoryTests {

    @Autowired
    IDistrictCacheRepository repository;

    @Test
    void getByCode() {
        DistrictSimplePO districtSimplePO = repository.getByCode("710000");
        System.out.println(districtSimplePO);
    }

}
