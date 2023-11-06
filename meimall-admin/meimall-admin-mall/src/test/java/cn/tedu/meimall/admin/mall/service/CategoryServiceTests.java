package cn.tedu.meimall.admin.mall.service;

import cn.tedu.meimall.admin.mall.pojo.param.CategoryAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.common.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    ICategoryService service;

    @Test
    void addNew(){
        CategoryAddNewParam categoryAddNewParam = new CategoryAddNewParam();
        categoryAddNewParam.setParentId(1L);
        categoryAddNewParam.setName("馬卡龍");

        try{
            service.addNew(categoryAddNewParam);
            System.out.println("新增類別成功!");
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void delete(){
        Long id =38L;

        try{
            service.delete(id);
            System.out.println("刪除類別成功!");
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void setEnable(){
        Long id = 1L;

        try{
            service.setEnable(id);
            System.out.println("啟用類別成功!");
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void setDisable(){
        Long id = 1L;

        try{
            service.setDisable(id);
            System.out.println("禁用類別成功!");
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    void updateInfoById(){
        Long id = 12L;
        CategoryUpdateInfoParam categoryUpdateInfoParam = new CategoryUpdateInfoParam();
        categoryUpdateInfoParam.setName("彩繪馬卡龍");
        categoryUpdateInfoParam.setKeywords("彩繪");
        categoryUpdateInfoParam.setIcon("圖標");
        categoryUpdateInfoParam.setSort(99);

        try{
            service.updateInfoById(id, categoryUpdateInfoParam);
            System.out.println("修改類別成功!");
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }
}
