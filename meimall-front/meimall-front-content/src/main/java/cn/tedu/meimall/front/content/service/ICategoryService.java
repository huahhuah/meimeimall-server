package cn.tedu.meimall.front.content.service;

import cn.tedu.meimall.front.content.pojo.vo.CategoryListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理類別業務的接口
 */
@Transactional
public interface ICategoryService {

    /**
     * 查詢類別列表
     * @return 類別列表
     */
    List<CategoryListItemVO> list();
}
