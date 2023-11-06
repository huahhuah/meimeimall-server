package cn.tedu.meimall.admin.account.dao.persist.repository;

import cn.tedu.meimall.admin.account.pojo.entity.User;
import cn.tedu.meimall.admin.account.pojo.vo.UserListItemVO;
import cn.tedu.meimall.admin.account.pojo.vo.UserStandardVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.apache.ibatis.annotations.Param;

/**
 * 處理用戶資料的存儲庫接口
 */
public interface IUserRepository {

    /**
     * 插入用戶資料
     * @param user 用戶資料
     * @return 受影響的行數
     */
    int insert(User user);

    /**
     * 根據用戶ID刪除用戶資料
     * @param id 用戶ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據用戶ID修改用戶的資料
     * @param user 封裝了用戶ID和新的資料的對象
     * @return 受影響的行數
     */
    int updateById(User user);

    /**
     * 根據用戶名統計用戶資料的數量
     * @param username 用戶名
     * @return 匹配用戶名的用戶資料的數量
     */
    int countByUsername(String username);

    /**
     * 根據手機號碼統計用戶資料的數量
     * @param phone 手機號碼
     * @return 匹配手機號碼的用戶資料的數量
     */
    int countByPhone(String phone);

    /**
     * 統計非某id但名稱匹配的用戶名資料的數量，用於檢查是否存在其它資料使用了相同的名稱
     *
     * @param username 用戶名
     * @return 匹配名稱但不匹配ID的資料的數量
     */
    int countByUsernameAndNotId(@Param("id") Long userId, @Param("username") String username);

    /**
     * 統計匹配手機號碼但非用戶ID的用戶資料的數量,通常用於檢查手機號碼是否被其他用戶占用
     * @param phone 手機號碼
     * @param userId 用戶ID
     * @return 匹配的用戶資料的數量
     */
    int countByPhoneAndNotId(@Param("id") Long userId, @Param("phone")String phone);

    /**
     * 根據電子信箱統計用戶資料的數量
     * @param email 電子信箱
     * @return 匹配電子信箱的用戶資料的數量
     */
    int countByEmail(String email);

    /**
     * 統計匹配電子信箱但非用戶ID的用戶資料的數量,通常用於檢查電子信箱是否被其他用戶占用
     * @param email 電子信箱
     * @param userId 用戶ID
     * @return 匹配的用戶資料的數量
     */
    int countByEmailAndNotId(@Param("id") Long userId, @Param("email")String email);

    /**
     * 根據用戶ID查詢用戶資料詳情
     * @param id 用戶ID
     * @return 匹配的用戶資料詳情,如果沒有匹配的資料,則返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查詢用戶資料列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 用戶資料列表
     */
    PageData<UserListItemVO> list(Integer pageNum, Integer pageSize);
}
