package cn.tedu.meimall.admin.mall.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 類別完整"樹"結構結點項的VO類
 */
@Data
@Accessors(chain = true)
public class CategoryTreeItemVO implements Serializable {

    /**
     * 數據ID，Element UI控件要求名為value
     */
    @ApiModelProperty("類别ID")
    private Long value;

    /**
     * 類别名稱，Element UI控件要求名為label
     */
    @ApiModelProperty("類别名稱")
    private String label;

    /**
     * 子級類别列表，Element UI控件要求名為children
     */
    @ApiModelProperty("子級類别列表")
    private List<CategoryTreeItemVO> children;
}
