package cn.tedu.meimall.front.mall.pojo.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 購物車資料
 */
@Data
@NoArgsConstructor
public class CartPO implements Serializable {

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品數量
     */
    private Integer goodsNum;

    public CartPO(Object goodsId, Object goodsNum){
        this.goodsId = Long.valueOf(goodsId.toString());
        this.goodsNum = Integer.valueOf(goodsNum.toString());
    }
}
