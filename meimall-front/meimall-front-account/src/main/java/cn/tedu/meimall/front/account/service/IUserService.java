package cn.tedu.meimall.front.account.service;



import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.front.account.pojo.param.UserRegisterParam;
import cn.tedu.meimall.front.account.pojo.param.UserUpdateInfoParam;
import cn.tedu.meimall.front.account.pojo.vo.UserSimpleInfoVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理用戶資料的業務介面
 */
@Transactional
public interface IUserService {

    /**
     * 用戶註冊
     * @param userRegisterParam 註冊參數
     */
    void  register(UserRegisterParam userRegisterParam);

    /**
     * 修改基本信息
     * @param currentPrincipal    當事人
     * @param userUpdateInfoParam 新的基本信息
     */
    void updateInfo(CurrentPrincipal currentPrincipal, UserUpdateInfoParam userUpdateInfoParam);

    /**
     * 修改密碼
     * @param currentPrincipal  當事人
     * @param oldPassword       舊密碼
     * @param newPassword       新密碼
     */
    void updatePassword(CurrentPrincipal currentPrincipal, String oldPassword, String newPassword);

    /**
     * 修改頭像
     * @param currentPrincipal  當事人
     * @param avatar            新的頭像的URL
     */
    void updateAvatar(CurrentPrincipal currentPrincipal, String avatar);

    /**
     * 修改手機號碼
     * @param currentPrincipal  當事人
     * @param phone             新手機號碼
     */
    void updatePhone(CurrentPrincipal currentPrincipal, String phone);

    /**
     * 修改電子信箱
     * @param currentPrincipal  當事人
     * @param email             新電子信箱
     */
    void updateEmail(CurrentPrincipal currentPrincipal, String email);

    /**
     * 查詢當前用戶基本資料信息
     * @param currentPrincipal 當事人
     * @return  匹配的用戶基本信息,如果沒有匹配的資料,則返回null
     */
    UserSimpleInfoVO getSelfSimpleInfo(CurrentPrincipal currentPrincipal);
}
