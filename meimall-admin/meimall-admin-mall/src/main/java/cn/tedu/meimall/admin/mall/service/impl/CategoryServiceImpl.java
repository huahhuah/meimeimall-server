package cn.tedu.meimall.admin.mall.service.impl;

import cn.tedu.meimall.admin.mall.dao.persist.repository.ICategoryRepository;
import cn.tedu.meimall.admin.mall.dao.persist.repository.IGoodsRepository;
import cn.tedu.meimall.admin.mall.pojo.entity.Category;
import cn.tedu.meimall.admin.mall.pojo.param.CategoryAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryTreeItemVO;
import cn.tedu.meimall.admin.mall.service.ICategoryService;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private IGoodsRepository goodsRepository;

    public CategoryServiceImpl() {
        log.debug("創建業務類對象: CategoryServiceImpl");
    }

    @Override
    public void addNew(CategoryAddNewParam categoryAddNewParam) {
        log.debug("開始處理【添加類別】的業務,參數:{}", categoryAddNewParam);
        //檢查此類別的名稱是否被使用,應該保證此類别的名稱是唯一的
        //如已被使用,則不允許創建
        //調用參數對象的getName()得到嘗試添加的類別的名稱
        String name = categoryAddNewParam.getName();
        //調用參數對象的getName()得到嘗試添加的類別的名稱
        int count = categoryRepository.countByName(name);
        //判斷統計結果是否大於0
        if (count > 0) {
            //是: 名稱被占用,拋出異常
            String message = "添加類別失敗,類別名稱【" + name + "】已經被占用!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        //通過參數對象獲取parentId
        Long parentId = categoryAddNewParam.getParentId();
        //預設depth(深度值)+1
        Integer depth = 1;
        //如果parentId不為0，查詢父級類别（深度） - 根據id查詢vo對象(mapper中getById())
        CategoryStandardVO parentCategory = null;
        //在父級類別的深度上+1,如果沒有父級,則值為1
        if (parentId != 0) {
            //否:調用Mapper對象的getStandardById()查詢父級類別
            parentCategory = categoryRepository.getStandardById(parentId);
            //判斷查詢結果(父級)是否為null
            if (parentCategory == null) {
                //是:拋出異常
                String message = "添加類別失敗,父級類別不存在!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            } else {
                //否: depth(深度值)為: 父級類別的depth +1
                depth = parentCategory.getDepth() + 1;
            }
        }

        //創建實體類對象（Mapper的方法的參數是實體類型）
        Category category = new Category();
        //將當前方法參數的值複製到Brand實體類的對象中
        BeanUtils.copyProperties(categoryAddNewParam, category);
        // 補全Category對象的屬性值: depth >>>
        category.setDepth(depth);
        // 補全Category對象的屬性值: isParent >>> 固定為0
        category.setIsParent(0);
        //調用Mapper對象的insert()方法執行插入
        int rows = categoryRepository.insert(category);
        if (rows != 1) {
            String message = "添加類別失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
        //@Transactional
        //1.正常無注解-新增 2.造異常無注解-新增 3.造異常加註解-新增  4.加註解-新增
        //String s = nll;
        //s.charAt(1);

        //如果parentId不為0，且父級類别的isParent為0，將父級類别的isParent修改為1
        if (parentId != 0 && parentCategory.getIsParent() == 0) {
            //是: 將父級類別的isParent更新為1
            Category updateParentCategory = new Category();
            updateParentCategory.setId(parentId);
            updateParentCategory.setIsParent(1);
            rows = categoryRepository.update(updateParentCategory);
            if (rows != 1) {
                String message = "添加類別失敗,服務器忙,請稍後再嘗試!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
            }
        }
    }

    // 目標:檢查是否有必要將父級類别的isParent改為0
    // 判断：統計當前類别的父級類别有多個子級，判断是否等於0
    // 1.根據此前的檢查结果獲取parentId(判断父級是否存在)
    // 2.開發Mapper層:根據父級類別id統計子級類別的數量
    // 根據父級類别的id統計子級的数量 - (mapper - int countByParentId(Long parentId) )
    // 3.統計當前類别的父級類别有多個子級，判断是否等於0
    //3.1判斷:統計當前類別的父級類別有多少子級類別,判斷是否為0
    //3.2是:將父級類别的isParent改為0
    // 4.檢查當前類别是否存在子級類别
        @Override
        public void delete (Long id) {
            log.debug("開始處理【根據ID刪除類別】的業務,參數:{}", id);
           //根據ID查詢資料,檢查類别是否存在，如果不存在，則抛出異常
            CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
            //判斷查詢結果是否為null,如果是,則拋出異常
            if (currentCategory == null) {
                String message = "刪除類別失敗,嘗試刪除的類別資料（id=" + id + "）不存在!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            }

            //判斷查詢結果中的isParent是否為1,如果是,則拋出異常
            if (currentCategory.getIsParent() == 1) {
                String message = "刪除類別失敗,該類別扔包含子級類別!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }

            //檢查是否有商品關聯到此類別,如果存在,則拋出異常
            int goodCount = goodsRepository.countByCategory(id);
            log.debug("根據分類ID統計匹配的商品類別數量,結果:{}", goodCount);
            if (goodCount > 0) {
                String message = "刪除類別失敗,仍有商品關聯到此類別!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }

            //調用Mapper對象的deleteById()方法執行刪除
            int rows = categoryRepository.deleteById(id);
            if (rows != 1) {
                String message = "刪除類別失敗,服務器忙,請稍後再嘗試!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_DELETE, message);
            }
            log.debug("刪除分類成功!");

            //檢查是否有必要將父級類別的isParent改為0
            //根據此前的檢查結果獲取parentId(判斷父級是否存在)
            Long parentId = currentCategory.getParentId();
            //開發Mapper層:根據父級類別ID統計子級類別的數量
            int count = categoryRepository.countByParent(parentId);
            //判斷:統計當前類別的父級類別有多少子級類別,判斷是否為0
            if (count == 0) {
                //將父級類別的isParent改為0
                Category parentCategory = new Category();
                parentCategory.setId(parentId);
                parentCategory.setIsParent(0);
                rows = categoryRepository.update(parentCategory);
                if (rows != 1) {
                    String message = "刪除類別失敗,服務器忙,請稍後再試!";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
                }
            }
        }

        @Override
        public void setEnable (Long id){
            log.debug("開始處理【啟用類別】的業務,參數;{}", id);
            updateEnableById(id, ENABLE_STATE_ON);
        }

        @Override
        public void setDisable(Long id) {
            log.debug("開始處理【禁用類別】的業務,參數;{}", id);
            updateEnableById(id, ENABLE_STATE_OFF);
        }

        @Override
        public void setDisplay(Long id) {
            log.debug("開始處理【將類別顯示在導航欄】的業務,參數:{}", id);
            updateDisplayById(id ,DISPLAY_STATE_ON);
        }

    @Override
        public void setHidden (Long id){
            log.debug("開始處理【啟用類別】的業務,參數;{}", id);
            updateDisplayById(id, DISPLAY_STATE_OFF);
        }

        @Override
        public void updateInfoById (Long id, CategoryUpdateInfoParam categoryUpdateInfoParam){
            log.debug("開始處理【修改類別詳情】的業務,ID:{},新資料:{}", id, categoryUpdateInfoParam);
            // 調用Mapper對象的getStandardById()持行查詢
            CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
            //判斷查詢結果是否為null
            if(queryResult == null){
                //是: 拋出異常
                String message = "修改類別詳情失敗,嘗試修改的類別資料不存在!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            }
            //調用Mapper對象的countByNameAndNotId()執行統計
            int count = categoryRepository.countByNameAndNotId(id, categoryUpdateInfoParam.getName());
            //判斷統計結果是否大於0
            if( count > 0) {
                //是: 名稱被占用,拋出異常
                String message = "修該類別詳情失敗,類別名稱已經被占用!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }

            //創建Category對象
            Category category = new Category();
            //複製屬性,設置ID
            BeanUtils.copyProperties(categoryUpdateInfoParam, category);
            category.setId(id);
            //調用Mapper對象的update()方法執行修改
            int rows = categoryRepository.update(category);
            if(rows != 1){
                String message = "修該類別詳情失敗,服務器忙,請稍稍後再嘗試!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
            }
            log.debug("修改類别成功!");
        }

        @Override
        public CategoryStandardVO getStandardById (Long id){
            log.debug("開始處理【根據ID查詢類別】的業務,參數: {}",id);
            CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
            if(queryResult == null){
                String message = "查詢類別詳情失敗,類別資料不存在!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            }
            return queryResult;
        }

        @Override
        public List<CategoryTreeItemVO> listTree () {
            log.debug("開始處理【查詢類別】的業務,參數; 無");
            List<CategoryTreeItemVO> categoryTree = new ArrayList<>();

            List<CategoryListItemVO> categoryList = categoryRepository.list();
            Map<Long, CategoryListItemVO> allCategoryMap =  transformListToMap(categoryList);
            for (Long key : allCategoryMap.keySet()) {
                CategoryListItemVO mapItem = allCategoryMap.get(key);
                if(mapItem.getParentId() == 0){
                    CategoryTreeItemVO categoryTreeItemVO = convertListItemToTreeItem(mapItem);
                    categoryTree.add(categoryTreeItemVO);

                    fillChildren(mapItem, categoryTreeItemVO, allCategoryMap);
                }
            }
            return categoryTree;
        }

        private Map<Long, CategoryListItemVO> transformListToMap(List<CategoryListItemVO> categoryList){
            Map<Long, CategoryListItemVO> categoryMap = new LinkedHashMap<>();
            for (CategoryListItemVO categoryListItemVO : categoryList) {
                if(categoryListItemVO.getEnable() == 0) {
                    continue;
                }
                categoryMap.put(categoryListItemVO.getId(), categoryListItemVO);
            }
            return categoryMap;
        }

        private void fillChildren(CategoryListItemVO listItem, CategoryTreeItemVO currentTreeItem, Map<Long, CategoryListItemVO> allCategoryMap) {
            if(listItem.getIsParent() == 1){
                currentTreeItem.setChildren(new ArrayList<>());
                Set<Long> keySet = allCategoryMap.keySet();
                for (Long key : keySet) {
                    CategoryListItemVO mapItem = allCategoryMap.get(key);
                    if(mapItem.getParentId().equals(listItem.getId())){
                        CategoryTreeItemVO categoryTreeSubItemVO = convertListItemToTreeItem(mapItem);
                        currentTreeItem.getChildren().add(categoryTreeSubItemVO);
                        if(mapItem.getParentId() == 1){
                            fillChildren(mapItem, categoryTreeSubItemVO, allCategoryMap);
                        }
                    }
                }
            }
        }

        private CategoryTreeItemVO convertListItemToTreeItem(CategoryListItemVO listItem){
            return new CategoryTreeItemVO()
                    .setValue(listItem.getId())
                    .setLabel(listItem.getName());
        }

        @Override
        public PageData<CategoryListItemVO> listByParent (Long parentId, Integer pageNum){
           log.debug("開始處理【根據父級查詢子級列表】的業務,父級類別:{}, 頁碼:{}", parentId, pageNum);
           return categoryRepository.listByParent(parentId, pageNum, defaultQueryPageSize);
        }

        @Override
        public PageData<CategoryListItemVO> listByParent (Long parentId, Integer pageNum, Integer pageSize){
            log.debug("開始處理【根據父級查詢子級列表】的業務,父級類別:{}, 頁碼:{}, 每頁記錄數:{}", parentId, pageNum, pageSize);
            return categoryRepository.listByParent(parentId, pageNum, pageSize);
        }

        public void updateEnableById(Long id, Integer enable) {
            String enableText[] = {"禁用", "啟用"};
            log.debug("開始處理【{}類別】的業務，ID：{}，目標狀態：{}",enableText[enable], id, enableText);
            //調用Mapper對象的getStandardById()方法執行查詢
            CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
            //判斷查詢結果是否為null,如果是,則拋出異常
            if(currentCategory == null){
                String message = ENABLE_STATE_TEXT[enable] + "類別失敗,類別資料不存在!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            }

            //判斷查詢結果中的enable與enable是否相同,如果是,則拋出異常
            if(currentCategory.getEnable().equals(enable)) {
                String message = ENABLE_STATE_TEXT[enable] + "類別失敗,此類別已經處於" + ENABLE_STATE_TEXT[enable] + "狀態!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }

            //創建Category對象,並將2個參數的值封裝起來
            Category updateCategory = new Category();
            //向Category對象中封裝屬性值: id ,enable, 均來自方法參數
            updateCategory.setId(id);
            updateCategory.setEnable(enable);
            //調用Mapper對象的update()方法執行更新
            int rows = categoryRepository.update(updateCategory);
            if(rows != 1){
                String message = ENABLE_STATE_TEXT[enable] +"類別失敗,服務器忙,請稍後再嘗試!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
            }
        }

        private void updateDisplayById(Long id, Integer isDisplay){
            //調用Mapper對象的getStandardById()方法執行查詢
            CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
            //判斷查詢結果是否為null,如果是,則拋出異常
            if(currentCategory == null){
                String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗,類別資料不存在!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            }

            //判斷查詢結果中的isDisplay與isDisplay是否相同,如果是,則拋出異常
            if(currentCategory.getIsDisplay().equals(isDisplay)){
                String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗,此類別已經處於" + DISPLAY_STATE_TEXT[isDisplay] + "狀態!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }

            //創建Category對象
            Category updateCategory = new Category();
            //向Category對象中封裝屬性值: id ,isDisplay, 均來自方法參數
            updateCategory.setId(id);
            updateCategory.setIsDisplay(isDisplay);
            //調用Mapper對象的update()方法執行更新
            int rows = categoryRepository.update(updateCategory);
            if(rows != 1){
                String message = DISPLAY_STATE_TEXT[isDisplay] +"類別失敗,服務器忙!請稍後再嘗試!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
            }
        }


}
