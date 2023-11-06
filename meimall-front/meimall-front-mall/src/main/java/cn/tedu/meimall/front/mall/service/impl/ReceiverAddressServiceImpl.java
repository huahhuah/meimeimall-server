package cn.tedu.meimall.front.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;
import cn.tedu.meimall.front.mall.dao.cache.IDistrictCacheRepository;
import cn.tedu.meimall.front.mall.dao.persist.repository.IReceiverAddressRepository;
import cn.tedu.meimall.front.mall.pojo.entity.ReceiverAddress;
import cn.tedu.meimall.front.mall.pojo.param.ReceiverAddressAddNewParam;
import cn.tedu.meimall.front.mall.pojo.param.ReceiverAddressUpdateParam;
import cn.tedu.meimall.front.mall.pojo.vo.ReceiverAddressListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.ReceiverAddressStandardVO;
import cn.tedu.meimall.front.mall.service.IReceiverAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 處理收貨地址資料的業務實現類
 */
@Slf4j
@Service
public class ReceiverAddressServiceImpl implements IReceiverAddressService {

    @Autowired
    private IReceiverAddressRepository receiverAddressRepository;
    @Autowired
    private IDistrictCacheRepository districtCacheRepository;

    public ReceiverAddressServiceImpl(){
        log.debug("創建業務類對象: ReceiverAddressServiceImpl");
    }

    @Override
    public void addNew(CurrentPrincipal currentPrincipal, ReceiverAddressAddNewParam receiverAddressAddNewParam) {
        log.debug("開始處理【添加收貨地址】的業務，當事人：{}，參數：{}", currentPrincipal, receiverAddressAddNewParam);

        DistrictSimplePO city = districtCacheRepository.getByCode(receiverAddressAddNewParam.getCityCode());
        if ( city == null) {
            String message = "添加收貨地址失敗，選擇的地區資料有誤！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        ReceiverAddress receiverAddress = new ReceiverAddress();
        BeanUtils.copyProperties(receiverAddressAddNewParam, receiverAddress);
        System.out.println(receiverAddress);


        receiverAddress.setUserId(currentPrincipal.getId());

        receiverAddress.setCity(city.getName());
        if (StringUtils.hasText(receiverAddressAddNewParam.getAreaCode())) {
            DistrictSimplePO area = districtCacheRepository.getByCode(receiverAddressAddNewParam.getAreaCode());
            receiverAddress.setArea(area.getName());
        }

        Integer isDefault = receiverAddressAddNewParam.getIsDefault();
        if (isDefault == 1) {
            receiverAddressRepository.updateNonDefaultByUser(currentPrincipal.getId());
        }

        int rows = receiverAddressRepository.insert(receiverAddress);
        if (rows != 1) {
            String message = "添加收貨地址失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(CurrentPrincipal currentPrincipal, Long id) {
        log.debug("開始處理【刪除收貨地址】的業務,當事人:{}, 參數:{}", currentPrincipal, id);
        ReceiverAddressStandardVO queryResult = receiverAddressRepository.getStandardById(id);
        if(queryResult == null){
            String message = "刪除收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(!queryResult.getUserId().equals(currentPrincipal.getId())){
            String message = "刪除收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = receiverAddressRepository.deleteById(id);
        if(rows != 1 ){
            String message = "刪除收貨地址失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_DELETE, message);
        }
    }

    @Override
    public void updateInfoById(CurrentPrincipal currentPrincipal, Long id, ReceiverAddressUpdateParam receiverAddressUpdateParam) {
        log.debug("開始處理【修改收貨地址】的業務,當事人:{},ID:{},新資料:{}", currentPrincipal, id , receiverAddressUpdateParam);
        DistrictSimplePO city = districtCacheRepository.getByCode(receiverAddressUpdateParam.getCityCode());
        if( city == null){
            String message = "修改收貨地址失敗,選擇的地區資料有誤!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        ReceiverAddressStandardVO queryResult = receiverAddressRepository.getStandardById(id);
        if( queryResult == null){
            String message = "修改收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(!queryResult.getUserId().equals(currentPrincipal.getId())){
            String message = "修改收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        ReceiverAddress receiverAddress = new ReceiverAddress();
        BeanUtils.copyProperties(receiverAddressUpdateParam, receiverAddress);
        receiverAddress.setId(id);

        receiverAddress.setCity(city.getName());
        if(StringUtils.hasText(receiverAddressUpdateParam.getAreaCode())){
            DistrictSimplePO area = districtCacheRepository.getByCode(receiverAddressUpdateParam.getAreaCode());
            receiverAddress.setArea(area.getName());
        }

        int rows = receiverAddressRepository.update(receiverAddress);
        if(rows != 1){
            String message = "修改收貨地址失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

    }

    @Override
    public void setDefault(CurrentPrincipal currentPrincipal, Long id) {
        log.debug("開始處理【設置默認收貨地址】的業務，當事人:{},ID:{}", currentPrincipal, id);
        ReceiverAddressStandardVO queryResult = receiverAddressRepository.getStandardById(id);
        if(queryResult == null){
            String message = "設置默認收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(!queryResult.getUserId().equals(currentPrincipal.getId())){
            String message = "設置默認收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = receiverAddressRepository.updateNonDefaultByUser(currentPrincipal.getId());
        if( rows < 1){
            String message = "設置默認收貨地址失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        ReceiverAddress receiverAddress = new ReceiverAddress();
        receiverAddress.setId(id);
        receiverAddress.setIsDefault(1);
        rows = receiverAddressRepository.update(receiverAddress);
        if(rows !=1){
            String message = "設置默認收貨地址失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

    }

    @Override
    public ReceiverAddressStandardVO getStandardById(CurrentPrincipal currentPrincipal, Long id) {
        log.debug("開始處理【根據ID查詢收貨地址】的業務,當事人:{}, 參數:{}", currentPrincipal, id);
        ReceiverAddressStandardVO queryResult = receiverAddressRepository.getStandardById(id);
        if(queryResult == null){
            String message = "查詢收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(!queryResult.getUserId().equals(currentPrincipal.getId())){
            String message = "查詢收貨地址失敗,嘗試訪問的資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public List<ReceiverAddressListItemVO> listByUser(CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【查詢收貨地址列表】的業務,當事人:{}", currentPrincipal);
        return receiverAddressRepository.listByUser(currentPrincipal.getId());
    }
}
