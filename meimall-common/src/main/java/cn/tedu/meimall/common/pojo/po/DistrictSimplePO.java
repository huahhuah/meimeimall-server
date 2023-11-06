package cn.tedu.meimall.common.pojo.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 市區VO類
 */
@Data
public class DistrictSimplePO implements Serializable {

    /**
     * 資料ID
     */
    private Long id;
    /**
     * 行政代號
     */
    private String code;
    /**
     * 名稱
     */
    private String name;
    /**
     * 名稱拼音
     */
    private String pinyin;
}
