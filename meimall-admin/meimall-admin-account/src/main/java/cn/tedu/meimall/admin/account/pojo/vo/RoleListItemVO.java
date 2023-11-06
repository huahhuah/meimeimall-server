package cn.tedu.meimall.admin.account.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色的列表項VO類
 */
@Data
public class RoleListItemVO implements Serializable {

    /**
     * 資料id
     */
    private Long id;

    /**
     * 名稱
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 自定義排序序號
     */
    private Integer sort;
}
