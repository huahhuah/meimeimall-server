package cn.tedu.meimall.passport.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
＊自定義用戶詳細信息
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CustomUserDetails extends User {

    /**
     * 用戶ID
     */
    private final Long id;
    /**
     * 用戶頭像
     */
    private final String avatar;
    /**
     * 登入次數
     */
    private final Integer loginCount;

    /**
     * 創建自定義用戶詳情類型的對象
     *
     * @param id          用戶ID
     * @param username    用戶名
     * @param password    密碼（密文）
     * @param avatar      頭像
     * @param enabled     啓用狀態
     * @param loginCount  登入次數
     * @param authorities 權限列表
     */
    public CustomUserDetails(Long id, String username, String password, String avatar, boolean enabled,
                             Integer loginCount, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.id = id;
        this.avatar = avatar;
        this.loginCount = loginCount;
    }

}
