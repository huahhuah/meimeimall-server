package cn.tedu.meimall.common.ex;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import lombok.Getter;

/**
 * 業務異常
 */
public class ServiceException extends RuntimeException{

    @Getter
    private ServiceCode serviceCode;

    /**
     * 創建業務異常对象
     *
     * @param serviceCode 業務狀態碼
     * @param message     描述文本
     */
    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }
}
