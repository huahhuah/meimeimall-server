package cn.tedu.meimall.passport.mapper;

import cn.tedu.meimall.passport.dao.persist.mapper.UserMapper;
import cn.tedu.meimall.passport.pojo.vo.UserLoginInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void getLoginInfoByUsername() {
        String username = "root";
        UserLoginInfoVO loginInfo = mapper.getLoginInfoByUsername(username);
        System.out.println(loginInfo);
    }

}
