package cn.tedu.meimall.admin.account.service;

import cn.tedu.meimall.admin.account.pojo.param.UserAddNewParam;
import cn.tedu.meimall.admin.account.pojo.param.UserUpdateInfoParam;
import cn.tedu.meimall.admin.account.pojo.vo.UserListItemVO;
import cn.tedu.meimall.admin.account.pojo.vo.UserStandardVO;
import cn.tedu.meimall.common.consts.data.UserConsts;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理用戶資料的業務接口
 */
@Transactional
public interface IUserService extends UserConsts {

    /**
     * 添加用戶
     *
     * @param userAddNewParam 用戶資料
     */
    void addNew(UserAddNewParam userAddNewParam);

    /**
     * 删除用戶
     *
     * @param id 用戶ID
     */
    void delete(Long id);

    /**
     * 修改基本資料
     *
     * @param userId              用戶ID
     * @param userUpdateInfoParam 新的基本資料
     */
    void updateInfo(Long userId, UserUpdateInfoParam userUpdateInfoParam);

    /**
     * 修改密碼
     *
     * @param userId      用戶ID
     * @param newPassword 新密碼
     */
    void updatePassword(Long userId, String newPassword);

    /**
     * 修改頭像
     *
     * @param userId 用戶ID
     * @param avatar 新頭像的URL
     */
    void updateAvatar(Long userId, String avatar);

    /**
     * 修改手機號碼
     *
     * @param userId 用戶ID
     * @param phone  新手機號碼
     */
    void updatePhone(Long userId, String phone);

    /**
     * 修改電子信箱
     *
     * @param userId 用戶ID
     * @param email  新電子信箱
     */
    void updateEmail(Long userId, String email);

    /**
     * 啓用用戶
     *
     * @param userId 用戶ID
     */
    void setEnable(Long userId);

    /**
     * 禁用用戶
     *
     * @param userId 用戶ID
     */
    void setDisable(Long userId);

    /**
     * 根據ID查詢用戶
     *
     * @param userId 用戶ID
     * @return 匹配的用戶信息
     */
    UserStandardVO getStandardById(Long userId);

    /**
     * 查詢用戶列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁記
     * @return 用戶列表
     */
    PageData<UserListItemVO> list(Integer pageNum);

    /**
     * 查詢用戶列表
     *
     * @param pageNum  頁記
     * @param pageSize 每頁記錄數
     * @return 用戶列表
     */
    PageData<UserListItemVO> list(Integer pageNum, Integer pageSize);

}
