package cn.tedu.meimall.admin.mall.dao.serach;

import cn.tedu.meimall.admin.mall.dao.search.IGoodsSearchRepository;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsSearchVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GoodsSearchRepositoryTests {

    @Autowired
    IGoodsSearchRepository repository;

    @Test
    void save(){
        GoodsSearchVO goodsSearchVO = new GoodsSearchVO();
        goodsSearchVO.setId(12L);
        goodsSearchVO.setCategoryName("綜合小禮盒");
        goodsSearchVO.setTitle("精選甜點");
        goodsSearchVO.setBrief("送禮必備");
        goodsSearchVO.setKeywords("禮盒,精選甜點");
        goodsSearchVO.setSalePrice(new BigDecimal("599"));
        goodsSearchVO.setCommentCount(68);
        goodsSearchVO.setPositiveCommentCount(65);
        goodsSearchVO.setSalesCount(99);
        goodsSearchVO.setIsRecommend(1);
        goodsSearchVO.setGmtCreate(LocalDateTime.now());
        goodsSearchVO.setGmtCreate(LocalDateTime.now());

        GoodsSearchVO result = repository.save(goodsSearchVO);
        System.out.println("向ES中寫入數據完成!返回的結果: "+ result);
    }

    @Test
    void findById(){
        Optional<GoodsSearchVO> optional = repository.findById(12L);
        GoodsSearchVO goodsSearchVO = optional.get();
        System.out.println(goodsSearchVO);
    }

    @Test
    void queryByTitle(){
        String keywords = "禮盒";
        List<GoodsSearchVO> goodsList = repository.queryByTitle(keywords);
        for (GoodsSearchVO goodsSearchVO : goodsList) {
            System.out.println(goodsSearchVO);
        }
    }

    @Test
    void customQuery(){
        String keywords = "禮盒";
        List<GoodsSearchVO> goodsList = repository.customQuery(keywords);
        for (GoodsSearchVO goodsSearchVO : goodsList) {
            System.out.println(goodsSearchVO);
        }
    }

    @Test
    void pageQueryByTitle(){
        String keywords = "禮盒";
        Integer pageNum = 0;
        Integer pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        SearchPage<GoodsSearchVO> searchPage = repository.queryByTitle(keywords, pageRequest);
        System.out.println(searchPage);

        SearchHits<GoodsSearchVO> searchHits = searchPage.getSearchHits();
        System.out.println(searchHits);
        System.out.println();

        List<SearchHit<GoodsSearchVO>> searchHitList = searchHits.getSearchHits();
        for (SearchHit<GoodsSearchVO> searchHit : searchHitList) {
            GoodsSearchVO goodsSearchVO = searchHit.getContent();
            System.out.println(goodsSearchVO);

            List<String> title = searchHit.getHighlightField("title");
            System.out.println(title);
            System.out.println("----------------------");
        }
    }

}
