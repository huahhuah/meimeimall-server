package cn.tedu.meimall.admin.mall.dao.persist.repository;

import cn.tedu.meimall.admin.mall.pojo.vo.GoodsSearchVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsRepositoryTests {

    @Autowired
    IGoodsRepository repository;

    @Test
    void listSearch() {
        Integer pageNum = 1;
        Integer pageSize = 4;
        PageData<GoodsSearchVO> pageData = repository.listSearch(pageNum, pageSize);
        System.out.println(pageData);
    }

}
