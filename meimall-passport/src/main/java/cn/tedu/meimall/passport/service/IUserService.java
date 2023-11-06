package cn.tedu.meimall.passport.service;


import cn.tedu.meimall.passport.pojo.param.UserLoginInfoParam;
import cn.tedu.meimall.passport.pojo.vo.UserLoginResultVO;
import cn.tedu.meimall.common.consts.web.JwtConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理用戶資料的業務接口
 *
 */
@Transactional
public interface IUserService extends JwtConsts {

    /**
     * 用戶登入
     *
     * @param userLoginInfoParam 封裝了登入信息的對象
     * @param remoteAddr         客戶端的IP地址
     * @param userAgent          客戶端的瀏覽器信息
     * @return 成功登入的用戶的信息，包括：ID、用戶名、頭像、JWT等資料
     */
    UserLoginResultVO login(UserLoginInfoParam userLoginInfoParam, String remoteAddr, String userAgent);

    /**
     * 退出登入
     *
     * @param currentPrincipal 當事人
     */
    void logout(CurrentPrincipal currentPrincipal);

}
