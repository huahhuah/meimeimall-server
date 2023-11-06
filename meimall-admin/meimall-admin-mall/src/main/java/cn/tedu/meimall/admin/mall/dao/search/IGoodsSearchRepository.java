package cn.tedu.meimall.admin.mall.dao.search;

import cn.tedu.meimall.admin.mall.pojo.vo.GoodsSearchVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// https://docs.spring.io/spring-data/elasticsearch/docs/4.2.1/reference/html/#reference
@Repository
public interface IGoodsSearchRepository
        extends ElasticsearchRepository<GoodsSearchVO, Long> {

    // 關於抽象方法的返回值：
    // -- List<T>：基礎搜索
    // -- Page<T>：支持分頁，但不支持獲取完整的命中結果
    // -- SearchHits<T>：不支持分頁，但可以獲取完整的命中結果，包括高亮數據
    // -- SearchPage<T>：支持分頁，並可以獲取完整的命中結果，包括高亮數據（如果配置了高亮）
    // -- -- 需要在參數列表上添加Pageable類型的參數，此參數是用於指定分頁參數的
    // -- -- Pageable必須是抽象方法的最後1個參數
    // -- -- 可以通過PageRequest.of(page, size)方法得到Pageable對象
    // -- -- 使用PageRequest.of()時，page表示從0開始編號的頁碼值，即第1頁的值為0

    // 【分頁搜索】
    @Highlight(fields = {@HighlightField(name = "title")},
            parameters = @HighlightParameters(
                    preTags = "<font style='color: red;'>", postTags = "</font>"))
    SearchPage<GoodsSearchVO> queryByTitle(String title, Pageable pageable);

    // 【簡單搜索】
    List<GoodsSearchVO> queryByTitle(String title);

    // 【自定義條件搜索】
    @Query("{" +
            "    \"match\": {\n" +
            "      \"title\": \"?0\"\n" +
            "    }\n" +
            "  }")
    List<GoodsSearchVO> customQuery(String title);

}
