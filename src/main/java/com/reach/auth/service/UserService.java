package com.reach.auth.service;

import com.reach.auth.domain.LogoUploadBO;
import com.reach.auth.domain.po.UserPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reach.auth.domain.vo.LoginVO;
import com.reach.auth.domain.vo.RegisterVO;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface UserService extends IService<UserPO> {
    String auth(String pk);

    RegisterVO register(String publicKey, String userName);

    String findUserIdByPublicKey(String publicKey);

    UserPO findUserByPublicKey(String publicKey);

    UserPO findUserByUserName(String userName);

    String findUserNameById(String userId);

    UserPO addUser(String publicKey,String userName);

    void ifAuth(String token);

    String uploadLogo(LogoUploadBO bo);

    List<UserPO> findUserByIds(List<String> userIds);

    UserPO findUserById(String userId);
}
