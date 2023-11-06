package cn.tedu.meimall.front.account.dao.persist.repository;

import cn.tedu.meimall.front.account.pojo.entity.User;
import cn.tedu.meimall.front.account.pojo.vo.UserSimpleInfoVO;
import cn.tedu.meimall.front.account.pojo.vo.UserStandardVO;

/**
 * 處理用戶資料的存儲庫介面
 */
public interface IUserRepository {

    /**
     * 新增用戶資料
     * @param user 用戶資料
     * @return 受影響的行數
     */
    int insert(User user);

    /**
     * 根據ID修改用戶資料
     * @param user 封裝了用戶ID和新資料的對象
     * @return 受影響的行數
     */
    int updateById(User user);

    /**
     * 根據用戶名統計用戶資料的數量
     * @param username 用戶名
     * @return 匹配用戶名的用戶數量
     */
    int countByUsername(String username);

    /**
     * 根據手機號碼統計用戶資料的數量
     * @param phone 手機號碼
     * @return 匹配用戶名的用戶數量
     */
    int countByPhone(String phone);

    /**
     * 統計匹配手機號碼但非用戶ID的用戶資料的數量,通常用於檢查手機號碼是否被其他用戶占用
     * @param phone  手機號碼
     * @param userId 用戶ID
     * @return 匹配的用戶資料的數量
     */
    int countByPhoneAndNotId(String phone, Long userId);

    /**
     * 根據電子信箱統計用戶資料的數量
     * @param email 電子信箱
     * @return 匹配用戶名的用戶數量
     */
    int countByEmail(String email);

    /**
     * 統計匹配電子信箱但非用戶ID的用戶資料的數量,通常用於檢查電子信箱是否被其他用戶占用
     * @param email  電子信箱
     * @param userId 用戶ID
     * @return 匹配的用戶資料的數量
     */
    int countByEmailAndNotId(String email, Long userId);

    /**
     * 根據用戶ID查詢用戶資料詳情
     * @param id 用戶ID
     * @return 匹配的用戶資料詳情,如果沒有匹配的資料,則返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 根據用戶ID查詢用戶基本資料
     * @param id 用戶ID
     * @return 匹配的用戶基本信息,如果沒有匹配的資料,則返回null
     */
    UserSimpleInfoVO getSimpleInfoById(Long id);
}
