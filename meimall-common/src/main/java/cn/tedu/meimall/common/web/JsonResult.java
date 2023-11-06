package cn.tedu.meimall.common.web;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 服務器端的統一響應類型
 *
 */
@Data
public class JsonResult {
    /**
     * 操作結果的狀態碼（狀態標識）
     */
    @ApiModelProperty("業務狀態碼")
    private Integer state;

    /**
     * 操作失敗時的提示文本
     */
    @ApiModelProperty("提示文本")
    private String message;

    /**
     * 操作成功時響應的資料
     */
    @ApiModelProperty("資料")
    private Object data;

    /**
     * 生成表示"成功"的響應對象
     *
     * @return 表示"成功"的響應對象
     */
    public static JsonResult ok() {
        return ok(null);
    }

    /**
     * 生成表示"成功"的響應對象，此對象中将包含響應到客户端的資料
     *
     * @param data 響應到客户端的資料
     * @return 表示"成功"的響應對象
     */
    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = ServiceCode.OK.getValue();
        jsonResult.data = data;
        return jsonResult;
    }

    /**
     * 生成表示"失敗"的響應對象
     *
     * @param e 業務異常
     * @return 表示"失敗"的響應對象
     */
    public static JsonResult fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    /**
     * 生成表示"失敗"的響應對象
     *
     * @param serviceCode 業務狀態碼
     * @param message     提示文本
     * @return 表示"失敗"的響應對象
     */
    public static JsonResult fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = serviceCode.getValue();
        jsonResult.message = message;
        return jsonResult;
    }

}
