package com.reach.auth.service;

import com.reach.auth.domain.po.UserPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reach.auth.domain.vo.LoginVO;
import com.reach.auth.domain.vo.RegisterVO;

public interface UserService extends IService<UserPO> {
    String auth(String pk);

    RegisterVO register(String publicKey, String userName);

    String findUserIdByPublicKey(String publicKey);

    UserPO findUserByPublicKey(String publicKey);

    UserPO findUserByUserName(String userName);

    String findUserNameById(String userId);

    UserPO addUser(String publicKey,String userName);

    void ifAuth(String token);
}
