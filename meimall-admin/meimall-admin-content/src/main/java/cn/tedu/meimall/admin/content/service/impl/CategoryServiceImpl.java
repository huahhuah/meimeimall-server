package cn.tedu.meimall.admin.content.service.impl;

import cn.tedu.meimall.admin.content.dao.persist.repository.IArticleRepository;
import cn.tedu.meimall.admin.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.meimall.admin.content.pojo.entity.Category;
import cn.tedu.meimall.admin.content.pojo.param.CategoryAddNewParam;
import cn.tedu.meimall.admin.content.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.admin.content.service.ICategoryService;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 處理類別的業務實現類
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IArticleRepository articleRepository;

    public CategoryServiceImpl(){
        log.debug("創建業務類對象: CategoryServiceImpl");
    }

    @Override
    public void addNew(CategoryAddNewParam categoryAddNewParam) {
        log.debug("開始處理【添加類別】的業務,參數: {}", categoryAddNewParam);
        String name = categoryAddNewParam.getName();
        int count = categoryRepository.countByName(name);
        log.debug("根據名稱【{}】統計數量,結果: {}", name, count);
        if(count > 0){
            String message = "添加類別失敗,類別名稱已經被占用!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewParam, category);
        int rows = categoryRepository.insert(category);
        if(rows != 1){
            String message = "添加類別失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("開始處理【刪除類別】的業務,參數: {}", id);
        CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
        if(currentCategory == null){
            String message = "刪除類別失敗,嘗試刪除的類別資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int articleCount = articleRepository.countByCategory(id);
        if(articleCount > 0) {
            String message = "刪除類別失敗,能有文章關聯此類別!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        int rows = categoryRepository.deleteById(id);
        if( rows != 1) {
            String message = "刪除類別失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_DELETE, message);
        }
    }

    @Override
    public void setEnable(Long id) {
        log.debug("開始處理【啓用類別】的業務，參數:{}",id);
        updateEnableById(id , ENABLE_STATE_ON);

    }

    @Override
    public void setDisable(Long id) {
        log.debug("開始處理【禁用類別】的業務，參數:{}",id);
        updateEnableById(id , ENABLE_STATE_OFF);
    }

    @Override
    public void seDisplay(Long id) {
        log.debug("開始處理【將類別顯示在導航欄】的業務，參數:{}",id);
        updateDisplayById(id, DISPLAY_STATE_ON);
    }

    @Override
    public void setHidden(Long id) {
        log.debug("開始處理【將類別不顯示在導航欄】的業務，參數:{}",id);
        updateDisplayById(id, DISPLAY_STATE_OFF);
    }

    @Override
    public void updateInfoById(Long id, CategoryUpdateInfoParam categoryUpdateInfoParam) {
        log.debug("開始處理【修改類别詳情】的業務，ID：{}，新資料：{}", id, categoryUpdateInfoParam);
        // 調用Mapper對象的getStandardById()持行查詢
        CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
        //判斷查詢結果是否為null
        if (queryResult == null) {
            //是: 拋出異常
            String message = "修改類別詳情失敗，嘗試修改的類別資料不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        //調用Mapper對象的countByNameAndNotId()執行統計
        int count = categoryRepository.countByNameAndNotId(id, categoryUpdateInfoParam.getName());
        //判斷統計結果是否大於0
        if (count > 0) {
            //是: 名稱被占用,拋出異常
            String message = "修改類別詳情失敗，類別名稱已經被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        //創建Category對象
        Category category = new Category();
        //複製屬性,設置ID
        BeanUtils.copyProperties(categoryUpdateInfoParam, category);
        category.setId(id);
        category.setGmtModified(LocalDateTime.now());
        //調用Mapper對象的update()方法執行修改
        int rows = categoryRepository.update(category);
        if (rows != 1) {
            String message = "修改類别詳情失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
        log.debug("修改類别成功!");
    }

    @Override
    public CategoryStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢類別】的業務,參數:{}", id);
        CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
        if(queryResult == null){
            String message = "查詢類別失敗,類別資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<CategoryListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢類別列表】的業務,頁碼:{}", pageNum);
        return  categoryRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢類別列表】的業務,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        return  categoryRepository.list(pageNum, pageSize);
    }

    public void updateEnableById(Long id, Integer enable){
        CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
        if(currentCategory == null){
            String message = ENABLE_STATE_TEXT[enable] + "類別失敗,類別資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if ( currentCategory.getEnable().equals(enable)){
            String message = ENABLE_STATE_TEXT[enable] + "類別失敗,此類別已經處於" + ENABLE_STATE_TEXT[enable] + "狀態!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Category updateCategory = new Category();
        updateCategory.setId(id);
        updateCategory.setEnable(enable);
        int rows = categoryRepository.update(updateCategory);
        if(rows != 1) {
            String message = ENABLE_STATE_TEXT[enable] + "類別失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    public  void updateDisplayById(Long id, Integer isDisplay){
        CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
        if(currentCategory == null){
            String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗,類別資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(currentCategory.getIsDisplay().equals(isDisplay)){
            String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗,此類別已經處於" + DISPLAY_STATE_TEXT[isDisplay] + "狀態!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Category updateCategory = new Category();
        updateCategory.setId(id);
        updateCategory.setIsDisplay(isDisplay);
        int rows = categoryRepository.update(updateCategory);
        if( rows != 1) {
            String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

    }
}
