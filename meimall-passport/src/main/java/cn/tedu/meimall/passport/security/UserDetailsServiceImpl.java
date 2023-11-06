package cn.tedu.meimall.passport.security;


import cn.tedu.meimall.passport.dao.persist.repository.IUserRepository;
import cn.tedu.meimall.passport.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security處理認證時使用到的獲取用戶登入詳情的實現類
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    public UserDetailsServiceImpl() {
        log.debug("創建Spring Security的UserDetailsService接口對象：UserDetailsServiceImpl");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security框架自動調用了UserDetailsService對象，將根據用戶名獲取用戶詳情，參數：{}", s);
        UserLoginInfoVO loginInfo = userRepository.getLoginInfoByUsername(s);
        log.debug("根據用戶名【{}】從數據庫中查詢用戶詳情，查詢結果：{}", s, loginInfo);

        if (loginInfo == null) {
            log.debug("即將向Spring Security框架返回null，由框架進行後續的處理");
            return null;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> permissions = loginInfo.getPermissions();
        for (String permission : permissions) {
            GrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }

        CustomUserDetails userDetails = new CustomUserDetails(
                loginInfo.getId(), loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.getAvatar(),
                loginInfo.getEnable() == 1, loginInfo.getLoginCount(), authorities);

        log.debug("即將向Spring Security框架返回UserDetails類型的結果：{}", userDetails);
        log.debug("接下来，將由Spring Security框架自動驗證用戶狀態、密碼等，以判斷是否可以成功登入！");
        return userDetails;
    }

}
