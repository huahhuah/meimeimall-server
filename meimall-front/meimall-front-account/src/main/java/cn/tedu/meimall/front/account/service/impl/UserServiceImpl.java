package cn.tedu.meimall.front.account.service.impl;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.front.account.dao.persist.mapper.UserRoleMapper;
import cn.tedu.meimall.front.account.dao.persist.repository.IUserRepository;
import cn.tedu.meimall.front.account.pojo.entity.User;
import cn.tedu.meimall.front.account.pojo.entity.UserRole;
import cn.tedu.meimall.front.account.pojo.param.UserRegisterParam;
import cn.tedu.meimall.front.account.pojo.param.UserUpdateInfoParam;
import cn.tedu.meimall.front.account.pojo.vo.UserSimpleInfoVO;
import cn.tedu.meimall.front.account.pojo.vo.UserStandardVO;
import cn.tedu.meimall.front.account.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * 處理用戶資料的業務實現類
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(){
        log.debug("創建業務類對象: UserServiceImpl");
    }

    @Override
    public void register(UserRegisterParam userRegisterParam) {
        log.debug("開始處理【用戶註冊】的業務,參數:{}", userRegisterParam);

        //檢查會員用戶名是否被佔用,如果被佔用,則拋出異常
        //從參數對象中獲取會員的用戶名
        {
             String username = userRegisterParam.getUsername();
            //調用Mapper對象的countByUsername()執行統計
            int count = userRepository.countByUsername(username);
            //判斷統計結果是否大於0
            if(count > 0){
                String message = "添加用戶失敗,用戶名已經被占用!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 檢查會員手機號碼是否被占用，如果被占用，則拋出異常
        {
            String phone = userRegisterParam.getPhone();
            int count = userRepository.countByPhone(phone);
            if(count > 0){
                String message = "添加用戶失敗,手機號碼已經被占用!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 檢查會員電子郵箱是否被占用，如果被占用，则拋出異常
        {
            String email = userRegisterParam.getEmail();
            int count = userRepository.countByEmail(email);
            if(count > 0){
                String message = "添加用戶失敗,電子信箱已經被占用!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        //將會員資料寫入到資料庫中
        //創建User對象
        User user = new User();
        //複製屬性
        BeanUtils.copyProperties(userRegisterParam, user);
        // 補全User對象的屬性值
        user.setLoginCount(0);
        user.setEnable(1);
        // 從User對象中取出原密碼，執行加密，再將密文存入到User對象中
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        // 調用Mapper對象的insert()执行插入
        int rows = userRepository.insert(user);
        if(rows != 1){
            String message = "添加會員失敗,服務器忙,請稍後再試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
        //插入用戶與角色關係表資料（新增用戶綁定權限）
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(3L); //暫時鎖定為3號角色

        rows = userRoleMapper.insert(userRole);
        if (rows != 1) {
            String message = "添加用戶失敗，服務器忙，請稍後再次嘗試！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
        log.debug("將新的會員資料插入到資料庫,完成!");
    }

    @Override
    public void updateInfo(CurrentPrincipal currentPrincipal, UserUpdateInfoParam userUpdateInfoParam) {
        log.debug("開始處理【修改基本資料】的業務，當事人：{}，新基本資料：{}", currentPrincipal,userUpdateInfoParam);
        Long userId = currentPrincipal.getId();
        User user = new User();
        BeanUtils.copyProperties(userUpdateInfoParam, user);
        user.setId(userId);
        int rows = userRepository.updateById(user);
        if(rows != 1){
            String message = "修改基本資料失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
        log.debug("修改基本資料成功!");
    }

    @Override
    public void updatePassword(CurrentPrincipal currentPrincipal, String oldPassword, String newPassword) {
        log.debug("開始處理【修改密碼】的業務,當事人:{},原密碼:{},新密碼:{}",currentPrincipal, oldPassword , newPassword);
        Long userId = currentPrincipal.getId();
        UserStandardVO queryResult = userRepository.getStandardById(userId);
        String dbPassword = queryResult.getPassword();
        boolean matches = passwordEncoder.matches(oldPassword, dbPassword);
        if(!matches){
            String message = "修改密碼失敗,原密碼錯誤!";
            log.warn(message);
            log.trace("用戶提交的原密碼:{}", oldPassword);
            log.trace("資料庫中的密文:{}", dbPassword);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        String encodePassword = passwordEncoder.encode(newPassword);
        User user = new User();
        user.setId(userId);
        user.setPassword(encodePassword);
        int rows = userRepository.updateById(user);
        if(rows != 1) {
            String message = "修改密碼失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

    }

    @Override
    public void updateAvatar(CurrentPrincipal currentPrincipal, String avatar) {
        log.debug("開始處理【修改頭像】的業務,當事人:{}, 新頭像:{}", currentPrincipal, avatar);
        Long userId = currentPrincipal.getId();
        User user = new User();
        user.setId(userId);
        user.setAvatar(avatar);
        int rows = userRepository.updateById(user);
        if(rows !=1){
            String message = "修改頭像失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public void updatePhone(CurrentPrincipal currentPrincipal, String phone) {
        log.debug("開始處理【更新手機號碼】的業務，當事人:{},新手機號碼:{}", currentPrincipal, phone);
        Long userId = currentPrincipal.getId();
        int count = userRepository.countByPhoneAndNotId(phone, userId);
        if(count > 0){
            String message = "修改手機號碼失敗,手機號碼被占用!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        User user = new User();
        user.setId(userId);
        user.setPhone(phone);
        int rows = userRepository.updateById(user);
        if(rows != 1) {
            String message = "修改手機號碼失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public void updateEmail(CurrentPrincipal currentPrincipal, String email) {
        log.debug("開始處理【更新電子信箱】的業務，當事人:{},新電子信箱:{}", currentPrincipal, email);
        Long userId = currentPrincipal.getId();
        int count = userRepository.countByEmailAndNotId(email, userId);
        if(count > 0){
            String message = "修改電子信箱失敗,電子信箱被占用!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        int rows = userRepository.updateById(user);
        if(rows != 1) {
            String message = "修改電子信箱失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public UserSimpleInfoVO getSelfSimpleInfo(CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【查詢當前用戶基本資料】的業務，當事人：{}", currentPrincipal);
        Long userId = currentPrincipal.getId();
        UserSimpleInfoVO queryResult = userRepository.getSimpleInfoById(userId);
        if(queryResult == null){
            String message = "當前查詢用戶基本信息失敗,嘗試訪問的用戶資料不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }
}
