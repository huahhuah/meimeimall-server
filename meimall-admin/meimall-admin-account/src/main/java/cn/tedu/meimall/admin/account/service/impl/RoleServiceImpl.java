package cn.tedu.meimall.admin.account.service.impl;

import cn.tedu.meimall.admin.account.dao.persist.repository.IRoleRepository;
import cn.tedu.meimall.admin.account.pojo.vo.RoleListItemVO;
import cn.tedu.meimall.admin.account.service.IRoleService;
import cn.tedu.meimall.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理角色資料的業務實現類
 */
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IRoleRepository roleRepository;

    public RoleServiceImpl(){
        log.debug("創建業務類對象: RoleServiceImpl");
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢角色列表】的業務，頁碼：{}", pageNum);
        return roleRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢角色列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return roleRepository.list(pageNum, pageSize);
    }
}
