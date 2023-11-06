package cn.tedu.meimall.front.mall.dao.cache;

import cn.tedu.meimall.front.mall.pojo.po.CartPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartCacheRepositoryTests {
    @Autowired
    ICartCacheRepository repository;

    @Test
    void put() {
        Long userId = 1L;
        CartPO cartPO = new CartPO();
        cartPO.setGoodsId(5L);
        cartPO.setGoodsNum(3);

        repository.put(userId, cartPO);
        System.out.println("向Redis中寫入資料成功！");
    }

    @Test
    void deleteByUserAndGoods() {
        Long userId = 1L;
        Long goodsId = 100L;

        long deleteCount = repository.deleteByUserAndGoods(userId, goodsId);
        System.out.println("删除資料完成，删除數量：" + deleteCount);
    }

    @Test
    void getByUserAndGoods() {
        Long userId = 1L;
        Long goodsId = 1L;
        CartPO cartPO = repository.getByUserAndGoods(userId, goodsId);
        System.out.println(cartPO);
    }

    @Test
    void listByUser() {
        Long userId = 1L;
        List<CartPO> cartList = repository.listByUser(userId);
        for (CartPO cartPO : cartList) {
            System.out.println(cartPO);
        }
    }
}
